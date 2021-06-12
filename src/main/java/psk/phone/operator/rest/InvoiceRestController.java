package psk.phone.operator.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import psk.phone.operator.database.entities.Invoices;
import psk.phone.operator.database.entities.User;
import psk.phone.operator.database.repository.InvoicesRepository;
import psk.phone.operator.database.repository.UserRepository;
import psk.phone.operator.transport.converter.InvoiceConverter;
import psk.phone.operator.transport.dto.InvoiceDto;
import psk.phone.operator.transport.dto.UserDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/invoice")
public class InvoiceRestController {

    private final UserRepository userRepository;
    private final InvoicesRepository invoicesRepository;

    @Autowired
    public InvoiceRestController(UserRepository userRepository, InvoicesRepository invoicesRepository) {
        this.userRepository = userRepository;
        this.invoicesRepository = invoicesRepository;
    }

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
}
