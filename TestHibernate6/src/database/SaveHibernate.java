package database;

import java.util.Scanner;

public class SaveHibernate {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        BankAccount account=new BankAccount();

        System.out.println("number : ");
        account.setNumber(sc.nextInt());

        System.out.print("Owner: ");
        account.setOwner( sc.next() );

        System.out.print("Balance: ");
        account.setBalance( sc.nextLong() );

        BankAccountManagerHibernate bam=new BankAccountManagerHibernate();
        bam.insert(account);
    }
}
