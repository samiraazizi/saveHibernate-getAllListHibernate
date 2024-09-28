package database;

import java.util.List;

public class TestHibernate {

    public static void main(String[] args) {

        BankAccountManagerHibernate bam = new BankAccountManagerHibernate();
        List list = bam.getAll();
        // List list = bam.getUnderMinimum();
        //List list = bam.findByMinimum(3000);

        for (int i=0; i < list.size(); i++) {
            BankAccount ba = ((BankAccount)list.get(i));
            String format = "Number: %d Owner: %-8s Balance: %d\n";
            System.out.printf(format, ba.getNumber(), ba.getOwner(), ba.getBalance());
        }


//        BankAccount ba = bam.findByNumber(1234);
//        System.out.println( ba.getOwner() );

    }

}
