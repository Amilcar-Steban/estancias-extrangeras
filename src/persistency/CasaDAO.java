package persistency;

import entity.Casa;
import java.util.ArrayList;
import java.util.List;

public class CasaDAO  extends DAO{

    public CasaDAO() {

    }
    //agregar, eliminar, listar, buscar, modificar
    public void agregarCasa(Casa casa) throws Exception {
        try {
            String sql = "INSERT INTO casa (id_casa, calle, numero, codigo_postal, ciudad, pais, fechaDesde, fechaHasta, tiempoMinimo, tiempoMaximo, precioHabitacion, tipoVivienda) VALUES ("
                    + casa.getIdCasa() + "', '"
                    + casa.getCalle() + "', "
                    + casa.getNumero() + ", '"
                    + casa.getCodigoPostal() + "', '"
                    + casa.getCiudad() + "', '"
                    + casa.getPais() + "', '"
                    + casa.getFechaDesde() + "', '"
                    + casa.getFechaHasta() + "', "
                    + casa.getTiempoMinimo() + ", "
                    + casa.getTiempoMaximo() + ", "
                    + casa.getPrecioHabitacion() + ", '"
                    + casa.getTipoVivienda() + "')";
            crud(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void listarCasas() throws Exception {
        try {
            String sql = "SELECT * FROM casas";
            consultarDataBase(sql);
            List<Casa> casas = new ArrayList<>();
            while (resultSet.next()) {
                Casa casa = new Casa();
                casa.setIdCasa(resultSet.getInt("id_casa"));
                casa.setCalle(resultSet.getString("calle"));
                casa.setNumero(resultSet.getInt("numero"));
                casa.setCodigoPostal(resultSet.getString("codigo_postal"));
                casa.setCiudad(resultSet.getString("ciudad"));
                casa.setPais(resultSet.getString("pais"));
                casa.setFechaDesde(resultSet.getString("fechaDesde"));
                casa.setFechaHasta(resultSet.getString("fechaHasta"));
                casa.setTiempoMinimo(resultSet.getInt("tiempoMinimo"));
                casa.setTiempoMaximo(resultSet.getInt("tiempoMaximo"));
                casa.setPrecioHabitacion(resultSet.getDouble("precioHabitacion"));
                casa.setTipoVivienda(resultSet.getString("tipoVivienda"));
                casas.add(casa);
            }
            closeDataBase();
            for (Casa cas : casas) {
                System.out.println(cas.toString());
                System.out.println("--------------------");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void eliminarCasa(int id) throws Exception {
        try {
            String sql = "DELETE FROM casa WHERE id_casa = " + id;
            crud(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void buscarCasa(int id) throws Exception {
        try {
            String sql = "SELECT * FROM casa WHERE id_casa = " + id;
            consultarDataBase(sql);
            Casa casa = new Casa();
            if(resultSet.next()) {
                casa.setIdCasa(resultSet.getInt("id_casa"));
                casa.setCalle(resultSet.getString("calle"));
                casa.setNumero(resultSet.getInt("numero"));
                casa.setCodigoPostal(resultSet.getString("codigo_postal"));
                casa.setCiudad(resultSet.getString("ciudad"));
                casa.setPais(resultSet.getString("pais"));
                casa.setFechaDesde(resultSet.getString("fechaDesde"));
                casa.setFechaHasta(resultSet.getString("fechaHasta"));
                casa.setTiempoMinimo(resultSet.getInt("tiempoMinimo"));
                casa.setTiempoMaximo(resultSet.getInt("tiempoMaximo"));
                casa.setPrecioHabitacion(resultSet.getDouble("precioHabitacion"));
                casa.setTipoVivienda(resultSet.getString("tipoVivienda"));
                System.out.println(casa.toString());
            }
            closeDataBase();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
