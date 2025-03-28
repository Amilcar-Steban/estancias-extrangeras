package persistency;

import entity.Estancia;
import java.util.ArrayList;
import java.util.List;

public class EstanciaDAO  extends DAO{

    public EstanciaDAO() {
    }
    //agregar, eliminar, listar, buscar, modificar
    public void agregarEstancia(Estancia estancia) throws Exception {
        try {
            String sql = "INSERT INTO estancia (id_estancia, id_cliente, id_casa, nombreHuesped, fechaDesde, fechaHasta) VALUES ("
                    + estancia.getIdEstancia() + "', "
                    + estancia.getIdCliente() + ", "
                    + estancia.getIdCasa() + ", '"
                    + estancia.getNombreHuesped() + "', '"
                    + estancia.getFechaDesde() + "', '"
                    + estancia.getFechaHasta() + ")";
            crud(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void listarEstancias() throws Exception {
        try {
            String sql = "SELECT * FROM estancias";
            consultarDataBase(sql);
            List<Estancia> estancias = new ArrayList<>();
            while (resultSet.next()) {
                Estancia estancia = new Estancia();
                estancia.setIdEstancia(resultSet.getInt("id_estancia"));
                estancia.setIdCliente(resultSet.getInt("id_cliente"));
                estancia.setIdCasa(resultSet.getInt("id_casa"));
                estancia.setNombreHuesped(resultSet.getString("nombreHuesped"));
                estancia.setFechaDesde(resultSet.getString("fechaDesde"));
                estancia.setFechaHasta(resultSet.getString("fechaHasta"));
                estancias.add(estancia);
            }
            closeDataBase();
            for (Estancia est : estancias) {
                System.out.println(est.toString());
                System.out.println("--------------------");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void eliminarEstancia(int id) throws Exception {
        try {
            String sql = "DELETE FROM estancia WHERE id_estancia = " + id;
            crud(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void buscarEstancia(int id) throws Exception {
        try {
            String sql = "SELECT * FROM estancia WHERE id_estancia = " + id;
            consultarDataBase(sql);
            Estancia estancia = new Estancia();
            while (resultSet.next()) {
                estancia.setIdEstancia(resultSet.getInt("id_estancia"));
                estancia.setIdCliente(resultSet.getInt("id_cliente"));
                estancia.setIdCasa(resultSet.getInt("id_casa"));
                estancia.setNombreHuesped(resultSet.getString("nombreHuesped"));
                estancia.setFechaDesde(resultSet.getString("fechaDesde"));
                estancia.setFechaHasta(resultSet.getString("fechaHasta"));
            }
            closeDataBase();
            System.out.println(estancia.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
