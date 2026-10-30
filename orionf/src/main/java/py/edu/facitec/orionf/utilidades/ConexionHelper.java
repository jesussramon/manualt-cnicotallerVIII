
package py.edu.facitec.orionf.utilidades;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ConexionHelper {

    private static final SessionFactory sessionFactory =
            crearSessionFactory();

    private static SessionFactory crearSessionFactory() {
        try {
            return new Configuration()
                    .configure()
                    .buildSessionFactory();

        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void cerrarSessionFactory() {
        if (!sessionFactory.isClosed()) {
            sessionFactory.close();
        }
    }
}
