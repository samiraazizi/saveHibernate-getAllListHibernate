
package database;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;
import org.hibernate.*;
import org.hibernate.cfg.*;

public class BankAccountManagerHibernate {

//    public List getUnderMinimum() {
//        List list = null;
//        Session session = getSession();
//        Transaction tx = session.beginTransaction();
//        list = session.createQuery("select ba from BankAccount ba where ba.balance < 5").list();
//        tx.commit();
//        session.close();
//        return list;
//    }


    public List getAll() {
        List list = null;
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        list = session.createQuery("from BankAccount").list();
        tx.commit();
        session.close();
        return list;
    }

    public void insert(BankAccount ba) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        session.save(ba);
        tx.commit();
        session.close();
    }

//    List findByMinimum(int min) {
//        List list = null;
//        Session session = getSession();
//        Transaction tx = session.beginTransaction();
//        Query q = session.createQuery("select ba from BankAccount ba where ba.balance < :min");
//        q.setLong("min", min);
//        list = q.list();
//        tx.commit();
//        session.close();
//        return list;
//    }

    BankAccount findByNumber(int number) {
        BankAccount ba = null;
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("select ba from BankAccount ba where ba.number = :num");
        q.setLong("num", number);
        ba = (BankAccount) ((Query) q).uniqueResult();
        tx.commit();
        session.close();
        return ba;
    }

    //    void transferByNumber(int source, int target, long amount) {
//
//        Session session = getSession();
//        Transaction tx = session.beginTransaction();
//
//        BankAccount s = findByNumber(source);
//        BankAccount t = findByNumber(target);
//        s.withdraw(amount);
//        t.deposit(amount);
//        session.merge(s);
//        session.merge(t);
//
//        tx.commit();
//        session.close();
//
//    }
    void transferByObject(BankAccount source, BankAccount target, long amount) {

        Session session = getSession();
        Transaction tx = session.beginTransaction();

        source.withdraw(amount);
        target.deposit(amount);
        session.update(source);
        session.update(target);

        tx.commit();
        session.close();

    }

    private Session getSession() {
        Configuration config = new Configuration();
        config.configure();
        config.addClass(BankAccount.class);
        SessionFactory factory;
        factory = config.buildSessionFactory();
        Session session = factory.openSession();
        return session;
    }

}
