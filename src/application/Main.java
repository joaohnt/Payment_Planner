package application;

import model.entities.Contract;
import model.entities.Installment;
import model.services.ContractService;
import model.services.OnlinePaymentService;
import model.services.PaypalService;

import java.text.ParseException;
import java.util.Locale;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");


        System.out.println("Entre os dados do contrato: ");
        System.out.printf("Numero: ");
        int number = sc.nextInt();
        sc.nextLine();
        System.out.printf("Data (dd/MM/yyyy): ");
        Date date;
        try {
            date = sdf.parse(sc.nextLine());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        System.out.printf("Valor do contrato: ");
        double totalValue = sc.nextDouble();
        System.out.printf("Entre com o numero de parcelas: ");
        int months = sc.nextInt();

        Contract contract = new Contract(number, date, totalValue);

        OnlinePaymentService paymentService = new PaypalService();

        ContractService service = new ContractService(paymentService);

        service.processContract(contract, months);

        for(Installment i : contract.getInstallments() ) {
            System.out.println(i.getDueDate() + " - " + i.getAmount());
        }

        sc.close();
    }
}