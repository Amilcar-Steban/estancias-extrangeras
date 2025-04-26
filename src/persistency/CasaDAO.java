package persistency;

import entity.Casa;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CasaDAO  extends DAO{

    public CasaDAO() {

    }
    //agregar, eliminar, listar, buscar, modificar
    public void agregarCasa(Casa casa)throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO casa (id_casa, calle, numero, codigo_postal, ciudad, pais, fecha_Desde, fecha_Hasta, tiempo_Minimo, tiempo_Maximo, precio_Habitacion, tipo_Vivienda) "
               + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        crud(sql, 
            casa.getIdCasa(),
            casa.getCalle(),
            casa.getNumero(),
            casa.getCodigoPostal(),
            casa.getCiudad(),
            casa.getPais(),
            casa.getFechaDesde(),
            casa.getFechaHasta(),
            casa.getTiempoMinimo(),
            casa.getTiempoMaximo(),
            casa.getPrecioHabitacion(),
            casa.getTipoVivienda()
        );
    }
    public List<Casa> listarCasas() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM casas";
        
        consultarDataBase(sql);
        List<Casa> casas = new ArrayList<>();
        
        try {
            while (resultSet.next()) {
                Casa casa = new Casa();
                casa.setIdCasa(resultSet.getInt("id_casa"));
                casa.setCalle(resultSet.getString("calle"));
                casa.setNumero(resultSet.getInt("numero"));
                casa.setCodigoPostal(resultSet.getString("codigo_postal"));
                casa.setCiudad(resultSet.getString("ciudad"));
                casa.setPais(resultSet.getString("pais"));
                casa.setFechaDesde(resultSet.getString("fecha_Desde"));
                casa.setFechaHasta(resultSet.getString("fecha_Hasta"));
                casa.setTiempoMinimo(resultSet.getInt("tiempo_Minimo"));
                casa.setTiempoMaximo(resultSet.getInt("tiempo_Maximo"));
                casa.setPrecioHabitacion(resultSet.getDouble("precio_Habitacion"));
                casa.setTipoVivienda(resultSet.getString("tipo_Vivienda"));
                casas.add(casa);
            }
            return casas;
        } finally {
            closeDataBase();
        }
    }

    public void eliminarCasa(int id) throws Exception {
        String sql = "DELETE FROM casa WHERE id_casa = ?";
        crud(sql, id);
    }

    public Casa buscarCasa(int id)throws SQLException, ClassNotFoundException{
        String sql = "SELECT * FROM casas WHERE id_casa = ?";
        consultarDataBase(sql, id);
        Casa casa = new Casa();
        try {
            if(resultSet.next()) {
                casa.setIdCasa(resultSet.getInt("id_casa"));
                casa.setCalle(resultSet.getString("calle"));
                casa.setNumero(resultSet.getInt("numero"));
                casa.setCodigoPostal(resultSet.getString("codigo_postal"));
                casa.setCiudad(resultSet.getString("ciudad"));
                casa.setPais(resultSet.getString("pais"));
                casa.setFechaDesde(resultSet.getString("fecha_Desde"));
                casa.setFechaHasta(resultSet.getString("fecha_Hasta"));
                casa.setTiempoMinimo(resultSet.getInt("tiempo_Minimo"));
                casa.setTiempoMaximo(resultSet.getInt("tiempo_Maximo"));
                casa.setPrecioHabitacion(resultSet.getDouble("precio_Habitacion"));
                casa.setTipoVivienda(resultSet.getString("tipo_Vivienda"));
            }
            return casa;
        }finally {
            closeDataBase();
        }
    }

     public List<Casa> buscarCasasDisponibles(String desde, String hasta, String pais) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM casa WHERE fechaDesde <= ? AND fechaHasta >= ? AND pais = ?";
        consultarDataBase(sql, desde, hasta, pais);
        List<Casa> casas = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Casa casa = new Casa();
                casa.setIdCasa(resultSet.getInt("id_casa"));
                casa.setCalle(resultSet.getString("calle"));
                casa.setNumero(resultSet.getInt("numero"));
                casa.setCodigoPostal(resultSet.getString("codigo_postal"));
                casa.setCiudad(resultSet.getString("ciudad"));
                casa.setPais(resultSet.getString("pais"));
                casa.setFechaDesde(resultSet.getString("fecha_Desde"));
                casa.setFechaHasta(resultSet.getString("fecha_Hasta"));
                casa.setTiempoMinimo(resultSet.getInt("tiempo_Minimo"));
                casa.setTiempoMaximo(resultSet.getInt("tiempo_Maximo"));
                casa.setPrecioHabitacion(resultSet.getDouble("precio_Habitacion"));
                casa.setTipoVivienda(resultSet.getString("tipo_Vivienda"));
                casas.add(casa);
            }
            return casas;
        } finally {
            closeDataBase();
        }
     }

}
