package psk.phone.operator.transport.converter;

import psk.phone.operator.database.entities.Invoices;
import psk.phone.operator.transport.dto.InvoiceDto;


/**
 * Klasa odpowiedzialna za konwersję obiektów.
 */
public class InvoiceConverter {

    /**
     * Metoda odpowiedzialna za konwersję obiektu klasy Invoice do obiektu klasy InvoiceDto.
     */
    public static InvoiceDto toDto(Invoices invoices){
        return InvoiceDto.builder().invoiceNumber(invoices.getInvoiceNumber())
                .price(invoices.getPrice())
                .invoiceStatusEnum(invoices.getStatusInvoice())
                .dateInvoice(invoices.getDateInvoice().toString())
                .user(invoices.getUser())
                .build();
    }
}
