package psk.phone.operator.transport.converter;

import psk.phone.operator.database.entities.Invoices;
import psk.phone.operator.database.entities.Package;
import psk.phone.operator.transport.dto.InvoiceDto;
import psk.phone.operator.transport.dto.PackageDto;

public class InvoiceConverter {
    public static InvoiceDto toDto(Invoices invoices){
        return InvoiceDto.builder().invoiceNumber(invoices.getInvoiceNumber())
                .price(invoices.getPrice())
                .invoiceStatusEnum(invoices.getStatusInvoice())
                .dateInvoice(invoices.getDateInvoice().toString())
                .user(invoices.getUser())
                .build();
    }

}
