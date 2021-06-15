package psk.phone.operator.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import psk.phone.operator.database.entities.User;
import psk.phone.operator.database.repository.UserRepository;
import psk.phone.operator.service.InvoiceService;

import java.util.List;


/**
 * Klasa odpowiedzialna za cykliczne generowanie faktur dla danego użytkownika.
 */
@Configuration
@EnableScheduling
@Slf4j
public class InvoiceScheduling {
    private final UserRepository userRepository;
    private final InvoiceService invoiceService;

    @Autowired
    public InvoiceScheduling(UserRepository userRepository, InvoiceService invoiceService) {
        this.userRepository = userRepository;
        this.invoiceService = invoiceService;
    }

    /**
     * Metoda odpowiedzialna za cykliczne wywoływanie funkcji odpowiedzialnej za generowanie faktur dla wszystkich użytkowników.
     */
    @Scheduled(cron = "1 0 4 10 * *", zone = "Europe/Warsaw")
    public void generateNewInvoice() {
        List<User> all = userRepository.findAll();
        all.forEach(invoiceService::creatingInvoice);
    }
}
