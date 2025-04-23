package persistency;

import entity.Familia;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FamiliaDAO extends DAO {

    public FamiliaDAO() {
    }

    public void agregarFamilia(Familia familia) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO familia (id_familia, nombre, edad_Minima, edad_Maxima, num_Hijos, email, id_Casa_Familia) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        crud(sql, 
            familia.getIdFamilia(),
            familia.getNombre(),
            familia.getEdadMinima(),
            familia.getEdadMaxima(),
            familia.getNumHijos(),
            familia.getEmail(),
            familia.getIdCasaFamilia()
        );
    }

    // Método para listar familias con filtros
    public List<Familia> listarFamiliasHijosYEdad(int edadMax, int minHijos) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM familia WHERE numHijos >= ? AND edadMaxima < ?";
        
        consultarDataBase(sql, minHijos, edadMax);
        List<Familia> familias = new ArrayList<>();
        
        try {
            while (resultSet.next()) {
                Familia familia = new Familia();
                familia.setIdFamilia(resultSet.getInt("id_familia"));
                familia.setNombre(resultSet.getString("nombre"));
                familia.setEdadMinima(resultSet.getInt("edad_Minima"));
                familia.setEdadMaxima(resultSet.getInt("edad_Maxima"));
                familia.setNumHijos(resultSet.getInt("num_Hijos"));
                familia.setEmail(resultSet.getString("email"));
                familia.setIdCasaFamilia(resultSet.getInt("id_Casa_Familia"));
                familias.add(familia);
            }
            return familias;
        } finally {
            closeDataBase();
        }
    }

    // Método para listar todas las familias
    public List<Familia> listarFamilias() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM familia";
        
        consultarDataBase(sql);
        List<Familia> familias = new ArrayList<>();
        
        try {
            while (resultSet.next()) {
                Familia familia = new Familia();
                familia.setIdFamilia(resultSet.getInt("id_familia"));
                familia.setNombre(resultSet.getString("nombre"));
                familia.setEdadMinima(resultSet.getInt("edad_Minima"));
                familia.setEdadMaxima(resultSet.getInt("edad_Maxima"));
                familia.setNumHijos(resultSet.getInt("num_Hijos"));
                familia.setEmail(resultSet.getString("email"));
                familia.setIdCasaFamilia(resultSet.getInt("id_Casa_Familia"));
                familias.add(familia);
            }
            return familias;
        } finally {
            closeDataBase();
        }
    }

    // Método para eliminar una familia
    public void eliminarFamilia(int id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM familia WHERE id_familia = ?";
        crud(sql, id);
    }

    // Método para buscar una familia por ID
    public Familia buscarFamilia(int id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM familia WHERE id_familia = ?";
        
        consultarDataBase(sql, id);
        Familia familia = null;
        
        try {
            if (resultSet.next()) {
                familia = new Familia();
                familia.setIdFamilia(resultSet.getInt("id_familia"));
                familia.setNombre(resultSet.getString("nombre"));
                familia.setEdadMinima(resultSet.getInt("edad_Minima"));
                familia.setEdadMaxima(resultSet.getInt("edad_Maxima"));
                familia.setNumHijos(resultSet.getInt("num_Hijos"));
                familia.setEmail(resultSet.getString("email"));
                familia.setIdCasaFamilia(resultSet.getInt("id_Casa_Familia"));
            }
            return familia;
        } finally {
            closeDataBase();
        }
    }
}