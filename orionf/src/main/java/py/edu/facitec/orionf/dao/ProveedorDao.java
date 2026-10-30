
package py.edu.facitec.orionf.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import py.edu.facitec.orionf.entidades.Proveedores;
import py.edu.facitec.orionf.utilidades.ConexionHelper;

public class ProveedorDao {

    public void guardar(Proveedores proveedor) {
        Transaction transaccion = null;

        try (Session session =
                ConexionHelper.getSessionFactory().openSession()) {

            transaccion = session.beginTransaction();

            session.persist(proveedor);

            transaccion.commit();

        } catch (Exception e) {
            if (transaccion != null && transaccion.isActive()) {
                transaccion.rollback();
            }

            throw new RuntimeException(
                    "Error al guardar el proveedor", e);
        }
    }

    public List<Proveedores> recuperarTodos() {
        try (Session session =
                ConexionHelper.getSessionFactory().openSession()) {

            return session.createQuery(
                    "FROM Proveedores p ORDER BY p.razonSocial",
                    Proveedores.class
            ).getResultList();

        } catch (Exception e) {
            throw new RuntimeException(
                    "Error al listar los proveedores", e);
        }
    }

    public Proveedores recuperarPorId(Integer id) {
        try (Session session =
                ConexionHelper.getSessionFactory().openSession()) {

            return session.get(Proveedores.class, id);

        } catch (Exception e) {
            throw new RuntimeException(
                    "Error al buscar el proveedor", e);
        }
    }

    public void actualizar(Proveedores proveedor) {
        ejecutarTransaccion(
                session -> session.merge(proveedor));
    }

    public void eliminar(Integer id) {
        ejecutarTransaccion(session -> {
            Proveedores proveedor =
                    session.get(Proveedores.class, id);

            if (proveedor != null) {
                session.remove(proveedor);
            }
        });
    }

    private void ejecutarTransaccion(
            OperacionHibernate operacion) {

        Transaction transaccion = null;

        try (Session session =
                ConexionHelper.getSessionFactory().openSession()) {

            transaccion = session.beginTransaction();

            operacion.ejecutar(session);

            transaccion.commit();

        } catch (Exception e) {
            if (transaccion != null && transaccion.isActive()) {
                transaccion.rollback();
            }

            throw new RuntimeException(
                    "Error al realizar la operación de proveedores", e);
        }
    }

    @FunctionalInterface
    private interface OperacionHibernate {
        void ejecutar(Session session);
    }
}
