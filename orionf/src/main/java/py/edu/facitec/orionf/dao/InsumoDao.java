
package py.edu.facitec.orionf.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import py.edu.facitec.orionf.entidades.Insumos;
import py.edu.facitec.orionf.utilidades.ConexionHelper;

public class InsumoDao {

    public void guardar(Insumos insumo) {
        Transaction transaccion = null;

        try (Session session = ConexionHelper.getSessionFactory().openSession()) {
            transaccion = session.beginTransaction();

            session.persist(insumo);

            transaccion.commit();

        } catch (Exception e) {
            if (transaccion != null && transaccion.isActive()) {
                transaccion.rollback();
            }

            throw new RuntimeException("Error al guardar el insumo", e);
        }
    }

    public List<Insumos> recuperarTodos() {
        try (Session session = ConexionHelper.getSessionFactory().openSession()) {

            return session.createQuery(
                "FROM tb_insumos i ORDER BY i.nombre",
                Insumos.class
            ).getResultList();

        } catch (Exception e) {
            throw new RuntimeException("Error al listar los insumos", e);
        }
    }

    public Insumos recuperarPorId(Integer id) {
        try (Session session = ConexionHelper.getSessionFactory().openSession()) {

            return session.get(Insumos.class, id);

        } catch (Exception e) {
            throw new RuntimeException("Error al buscar el insumo", e);
        }
    }

    public void actualizar(Insumos insumo) {
        ejecutarTransaccion(session -> session.merge(insumo));
    }

    public void eliminar(Integer id) {
        ejecutarTransaccion(session -> {
            Insumos insumo = session.get(Insumos.class, id);

            if (insumo != null) {
                session.remove(insumo);
            }
        });
    }

    private void ejecutarTransaccion(OperacionHibernate operacion) {
        Transaction transaccion = null;

        try (Session session = ConexionHelper.getSessionFactory().openSession()) {
            transaccion = session.beginTransaction();

            operacion.ejecutar(session);

            transaccion.commit();

        } catch (Exception e) {
            if (transaccion != null && transaccion.isActive()) {
                transaccion.rollback();
            }

            throw new RuntimeException(
                "Error al realizar la operación de insumos", e
            );
        }
    }

    @FunctionalInterface
    private interface OperacionHibernate {
        void ejecutar(Session session);
    }
}
