
package py.edu.facitec.orionf.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import py.edu.facitec.orionf.entidades.Categoria;
import py.edu.facitec.orionf.utilidades.ConexionHelper;

public class CategoriaDao {

    public void guardar(Categoria categoria) {

        Transaction transaccion = null;

        try (Session session = ConexionHelper.getSessionFactory().openSession()) {

            transaccion = session.beginTransaction();

            session.persist(categoria);

            transaccion.commit();

        } catch (Exception e) {

            if (transaccion != null && transaccion.isActive()) {
                transaccion.rollback();
            }

            throw new RuntimeException(
                "Error al guardar la categoría", e
            );
        }
    }

    public List<Categoria> recuperarTodos() {

        try (Session session = ConexionHelper.getSessionFactory().openSession()) {

            return session.createQuery(
                "FROM tb_categorias c ORDER BY c.nombre",
                Categoria.class
            ).getResultList();

        } catch (Exception e) {

            throw new RuntimeException(
                "Error al listar las categorías", e
            );
        }
    }

    public Categoria recuperarPorId(int id) {

        try (Session session = ConexionHelper.getSessionFactory().openSession()) {

            return session.get(Categoria.class, id);

        } catch (Exception e) {

            throw new RuntimeException(
                "Error al buscar la categoría", e
            );
        }
    }

    // metodo para filtrar categorías por nombre
    public List<Categoria> filtrarCategorias(String filtro) {

        try (Session session = ConexionHelper.getSessionFactory().openSession()) {

            return session.createQuery(
                "FROM tb_categorias c " +
                "WHERE UPPER(c.nombre) LIKE :filtro " +
                "ORDER BY c.nombre",
                Categoria.class
            )
            .setParameter(
                "filtro",
                "%" + filtro.trim().toUpperCase() + "%"
            )
            .getResultList();

        } catch (Exception e) {

            throw new RuntimeException(
                "Error al filtrar las categorías", e
            );
        }
    }

    public void actualizar(Categoria categoria) {

        ejecutarTransaccion(session -> session.merge(categoria));
    }

    public void eliminar(int id) {

        ejecutarTransaccion(session -> {

            Categoria categoria = session.get(Categoria.class, id);

            if (categoria != null) {
                session.remove(categoria);
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
                "Error al realizar la operación de categorías", e
            );
        }
    }

    @FunctionalInterface
    private interface OperacionHibernate {

        void ejecutar(Session session);
    }
}
