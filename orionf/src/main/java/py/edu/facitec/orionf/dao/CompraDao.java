package py.edu.facitec.orionf.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import py.edu.facitec.orionf.entidades.Compras;
import py.edu.facitec.orionf.entidades.DetalleCompra;
import py.edu.facitec.orionf.entidades.Insumos;
import py.edu.facitec.orionf.utilidades.ConexionHelper;

public class CompraDao {

    public void guardar(
            Compras compra,
            List<DetalleCompra> detallesCompra) {

        Transaction transaccion = null;

        try (Session session =
                ConexionHelper.getSessionFactory().openSession()) {

            transaccion = session.beginTransaction();

            if (detallesCompra == null || detallesCompra.isEmpty()) {
                throw new RuntimeException(
                        "La compra debe tener al menos un detalle."
                );
            }

            double totalCompra = 0;

            for (DetalleCompra detalle : detallesCompra) {
                totalCompra += detalle.getCantidad()
                        * detalle.getPrecioUnitario();
            }

            compra.setTotalCompra(totalCompra);

            session.persist(compra);

            for (DetalleCompra detalle : detallesCompra) {
                Insumos insumo = session.get(
                        Insumos.class,
                        detalle.getInsumo().getId()
                );

                if (insumo == null) {
                    throw new RuntimeException(
                            "No se encontró el insumo de la compra."
                    );
                }

                insumo.setCantidad(
                        insumo.getCantidad() + detalle.getCantidad()
                );

                detalle.setCompra(compra);
                detalle.setInsumo(insumo);

                session.persist(detalle);
            }

            transaccion.commit();

        } catch (Exception e) {
            if (transaccion != null && transaccion.isActive()) {
                transaccion.rollback();
            }

            throw new RuntimeException(
                    "No se pudo guardar la compra.",
                    e
            );
        }
    }

    public List<Compras> recuperarTodos() {
        try (Session session =
                ConexionHelper.getSessionFactory().openSession()) {

            return session.createQuery(
                    "FROM tb_compras c ORDER BY c.fechaCompra DESC",
                    Compras.class
            ).getResultList();

        } catch (Exception e) {
            throw new RuntimeException(
                    "No se pudieron listar las compras.",
                    e
            );
        }
    }
}