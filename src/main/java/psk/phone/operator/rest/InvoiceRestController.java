package psk.phone.operator.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import psk.phone.operator.database.entities.Invoices;
import psk.phone.operator.database.entities.User;
import psk.phone.operator.database.repository.InvoicesRepository;
import psk.phone.operator.database.repository.UserRepository;
import psk.phone.operator.service.InvoiceService;
import psk.phone.operator.transport.converter.InvoiceConverter;
import psk.phone.operator.transport.dto.InvoiceDto;
import psk.phone.operator.transport.dto.UserDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * Klasa obsługująca widok ,,Invoices". Zawiera metody obsługujące widok aplikacji wyświetlający faktury użytkownika.
 */
@RestController
@RequestMapping("/invoice")
public class InvoiceRestController {

    private final UserRepository userRepository;
    private final InvoicesRepository invoicesRepository;
    private final InvoiceService invoiceService;
    @Autowired
    public InvoiceRestController(UserRepository userRepository, InvoicesRepository invoicesRepository, InvoiceService invoiceService) {
        this.userRepository = userRepository;
        this.invoicesRepository = invoicesRepository;
        this.invoiceService = invoiceService;
    }


    /**
     * Metoda odpowiedzialna za odebranie danych użytkownika i przekazanie ich do serwisu odpowiedzialnego
     * za uzyskanie odpowiednich danych w celu wyświetlenia faktur danego użytkownika.
     * @param userDto
     * @return
     */
    @PostMapping
    public ResponseEntity<List<InvoiceDto>> getAllUserInvoice(@RequestBody UserDto userDto) {
        Optional<User> userByEmail = userRepository.findByEmail(userDto.getEmail());


        if (userByEmail.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        List<Invoices> invoicesList = invoicesRepository.findAllByUser(userByEmail.get());
        List<InvoiceDto> invoiceDtoList = invoicesList.stream().map(InvoiceConverter::toDto).collect(Collectors.toList());

        return ResponseEntity.ok(invoiceDtoList);
    }


    /**
     * Metoda odpowiedzialna za odebranie danych o fakturze i przekazanie ich do serwisu odpowiedzialnego
     * za zmianę statusu.
     * @param invoiceDto
     * @return
     */
    @PutMapping(path = "/payment")
    public ResponseEntity<?> changeStatusInvoice(@RequestBody InvoiceDto invoiceDto) {
        invoiceService.changeInvoiceStatus(invoiceDto.getInvoiceNumber());
        return ResponseEntity.ok().build();
    }
}
