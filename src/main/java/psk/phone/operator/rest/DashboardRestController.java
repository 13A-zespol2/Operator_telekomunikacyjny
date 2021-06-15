package psk.phone.operator.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import psk.phone.operator.database.entities.Invoices;
import psk.phone.operator.database.entities.PhoneNumber;
import psk.phone.operator.database.entities.User;
import psk.phone.operator.database.entities.UserPhoneNumber;
import psk.phone.operator.database.repository.InvoicesRepository;
import psk.phone.operator.database.repository.UserPhoneNumberRepository;
import psk.phone.operator.database.repository.UserRepository;
import psk.phone.operator.service.BalanceNumberService;
import psk.phone.operator.service.DefaultUserService;
import psk.phone.operator.service.PhoneNumberGeneratorService;
import psk.phone.operator.transport.dto.DashboardDto;
import psk.phone.operator.transport.dto.NumberBalanceDto;
import psk.phone.operator.transport.dto.UserDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * Klasa obsługująca widok ,,Dashboard". Zawiera metody obsługujące główny widok aplikacji.
 */
@RestController
@RequestMapping("/dashboard")
public class DashboardRestController {
    private UserPhoneNumberRepository userPhoneNumberRepository;
    private UserRepository userRepository;
    private BalanceNumberService balanceNumberService;
    private DefaultUserService defaultUserService;
    private InvoicesRepository invoicesRepository;

    @Autowired
    public DashboardRestController(UserPhoneNumberRepository userPhoneNumberRepository, UserRepository userRepository, BalanceNumberService balanceNumberService, DefaultUserService defaultUserService, InvoicesRepository invoicesRepository) {
        this.userPhoneNumberRepository = userPhoneNumberRepository;
        this.userRepository = userRepository;
        this.balanceNumberService = balanceNumberService;
        this.defaultUserService = defaultUserService;
        this.invoicesRepository = invoicesRepository;
    }


    /**
     * Metoda odpowiedzialna za odebranie danych zalogowanego użytkownika. Na ich podstawie wyszukuje odpowiednie dane
     * z innych tabel (np. wyświetlenie odpowiednich numerów czy faktur).
     * @param userDto
     * @return
     */
    @PostMapping
    public ResponseEntity<DashboardDto> getDataToDashboard(@RequestBody UserDto userDto) {
        Optional<User> userByEmail = userRepository.findByEmail(userDto.getEmail());
        if (userByEmail.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        List<UserPhoneNumber> dashboardDto = userPhoneNumberRepository.findByUser(userByEmail.get());
        List<String> collectPhones = dashboardDto.stream().map(e -> e.getPhoneNumber().getNumber()).collect(Collectors.toList());

        List<PhoneNumber> collect = dashboardDto.stream().map(UserPhoneNumber::getPhoneNumber).collect(Collectors.toList());
        List<NumberBalanceDto> balanceForAllNumbers = balanceNumberService.findBalanceForAllNumbers(collect);
        List<Invoices> invoice = invoicesRepository.findOneInvoiceByUser(userByEmail.get());
        Invoices lastInvoice;
        if(invoice.isEmpty()) {
            return ResponseEntity.ok(new DashboardDto(collectPhones, null));
        }else {
            lastInvoice = invoice.get(invoice.size()-1);
            return ResponseEntity.ok(new DashboardDto(collectPhones, lastInvoice));
        }

    }

    /**
     * Metoda odpowiedzialna za odebranie danych użytkownika i przekazanie ich do odpowiedniego serwisu odpowiedzialnego
     * za wygenerowanie nowego numeru dla danego użytkownika. Zwraca status operacji.
     * @param userDto
     * @return
     */
    @PostMapping("/registerNewNumber")
    public ResponseEntity<?> registerNewNumber(@RequestBody UserDto userDto) {
        Optional<User> userByEmail = userRepository.findByEmail(userDto.getEmail());
        if (userByEmail.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        User user = userByEmail.get();
        if (userPhoneNumberRepository.countByUser(user) < 3) {
            UserPhoneNumber userPhoneNumber = defaultUserService.registerUserNumber(user);
            return ResponseEntity.ok(userPhoneNumber);
        }
        return ResponseEntity.badRequest().build();
    }
}
