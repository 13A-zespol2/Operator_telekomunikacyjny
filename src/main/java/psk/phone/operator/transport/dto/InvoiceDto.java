package psk.phone.operator.transport.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import psk.phone.operator.database.entities.Invoices;
import psk.phone.operator.database.entities.User;
import psk.phone.operator.database.enumeration.InvoiceStatusEnum;

import java.time.LocalDate;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvoiceDto {
    private String invoiceNumber;
    private Double price;
    private InvoiceStatusEnum invoiceStatusEnum;
    private String dateInvoice;
    private User user;
}

