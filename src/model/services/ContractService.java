package model.services;

import model.entities.Contract;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class ContractService {
    private OnlinePaymentService onlinePaymentService;

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    public ContractService(OnlinePaymentService onlinePaymentService) {
        this.onlinePaymentService = onlinePaymentService;
    }

    public void processContract(Contract contract, Integer months) {

        LocalDate startDate = contract.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        double installments = contract.getTotalValue()/months;

        startDate = startDate.plusMonths(1);

        for(int i = 1; i <= months; i++) {

            double v1 = onlinePaymentService.interest(installments, i);
            double v2 = onlinePaymentService.paymentFee(v1);
            contract.addInstallment(dtf.format(startDate),v2);
            startDate = startDate.plusMonths(1);
        }

    }
}
