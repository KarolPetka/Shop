package project.architecture;

import org.hibernate.Session;
import project.database.entity.Lookbook;
import project.database.utils.HibernateUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Basket {

    Scanner scanner = new Scanner(System.in);

    List<Lookbook> basket = new ArrayList<>();

    void showBasket() {
        if (basket.size() == 0) {
            System.out.println("\nBasket is empty...");
        } else {
            System.out.println("Basket:");
            for (Lookbook lookbook : basket) {
                System.out.println(lookbook.toString());
            }
        }
    }

    void addToBasket(String id) {
        Session session = HibernateUtils
                .instance()
                .getSessionFactory()
                .getCurrentSession();

        session.beginTransaction();

        Lookbook lookbook = (Lookbook) session.createQuery("from Lookbook where id=:id")
                .setParameter("id", Long.parseLong(id))
                .getSingleResult();

        basket.add(lookbook);

        session.getTransaction().commit();
        session.close();

        System.out.println("\nProduct added to basket!");
    }
}