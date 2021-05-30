package psk.phone.operator.service;


import com.nimbusds.jose.util.JSONObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import psk.phone.operator.database.entities.PhoneHistory;
import psk.phone.operator.database.repository.PhoneHistoryRepository;
import psk.phone.operator.database.repository.SmsHistoryRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;

import static java.util.stream.Collectors.toList;


public class InvoiceService {


    public String generateInvoiceNumber(){

        LocalDate localDate = LocalDate.now();


        DecimalFormat decimalFormat = new DecimalFormat("00");

        return "OP" + "/" + localDate.getDayOfMonth() + "/" + decimalFormat.format(localDate.getMonth().getValue()) + "/" + localDate.getYear();
    }    
}
