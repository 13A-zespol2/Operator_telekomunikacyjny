package psk.phone.operator.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import psk.phone.operator.transport.dto.UserDto;

@RestController
@RequestMapping("/menu")
public class MenuRestController {
    //TODO może zamiast DTO to String email
    @GetMapping("/invoices")
    public ResponseEntity<?> getUserInvoices(@RequestBody UserDto userDto) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/numbers")
    public ResponseEntity<?> getUserNumbers(@RequestBody UserDto userDto) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/history")
    public ResponseEntity<?> getHistoryNumber(@RequestBody UserDto userDto) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/packages")
    public ResponseEntity<?> getPackages() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/usage")
    public ResponseEntity<?> getUsage() {
        return ResponseEntity.ok().build();
    }


}
