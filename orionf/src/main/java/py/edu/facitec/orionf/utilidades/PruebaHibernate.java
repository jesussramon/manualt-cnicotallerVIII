package py.edu.facitec.orionf.utilidades;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PruebaHibernate {

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure()
                .buildSessionFactory();

        Session session = sessionFactory.openSession();

        System.out.println("==============================");
        System.out.println("HIBERNATE FUNCIONANDO");
        System.out.println("==============================");

        session.close();
        sessionFactory.close();
    }
}