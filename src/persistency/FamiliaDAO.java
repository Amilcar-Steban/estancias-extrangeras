package persistency;

import entity.Familia;
import java.util.ArrayList;
import java.util.List;

public class FamiliaDAO  extends DAO{

    public FamiliaDAO() {
    }
    //agregar, eliminar, listar, buscar, modificar
    public void agregarFamilia(Familia familia) throws Exception {
        try {
            String sql = "INSERT INTO familia (id_familia, nombre, edadMinima, edadMaxima, numHijos, email, idCasaFamilia) VALUES ("
                    + familia.getIdFamilia() + ", "
                    + familia.getNombre() + ", "
                    + familia.getEdadMinima() + ", "
                    + familia.getEdadMaxima() + ", "
                    + familia.getNumHijos() + ", "
                    + familia.getEmail() + ", "
                    + familia.getIdCasaFamilia() + ")";
            crud(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void listarFamilias() throws Exception {
        try {
            String sql = "SELECT * FROM familias";
            consultarDataBase(sql);
            List<Familia> familias = new ArrayList<>();
            while (resultSet.next()) {
                Familia familia = new Familia();
                familia.setIdFamilia(resultSet.getInt("id_familia"));
                familia.setNombre(resultSet.getString("nombre"));
                familia.setEdadMinima(resultSet.getInt("edadMinima"));
                familia.setEdadMaxima(resultSet.getInt("edadMaxima"));
                familia.setNumHijos(resultSet.getInt("numHijos"));
                familia.setEmail(resultSet.getString("email"));
                familia.setIdCasaFamilia(resultSet.getInt("idCasaFamilia"));
                familias.add(familia);
            }
            closeDataBase();
            for (Familia fam : familias) {
                System.out.println(fam.toString());
                System.out.println("--------------------");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void eliminarFamilia(int id) throws Exception {
        try {
            String sql = "DELETE FROM familia WHERE id_familia = " + id;
            crud(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void buscarFamilia(int id) throws Exception {
        try {
            String sql = "SELECT * FROM familia WHERE id_familia = " + id;
            consultarDataBase(sql);
            Familia familia = new Familia();
            while (resultSet.next()) {
                familia.setIdFamilia(resultSet.getInt("id_familia"));
                familia.setNombre(resultSet.getString("nombre"));
                familia.setEdadMinima(resultSet.getInt("edadMinima"));
                familia.setEdadMaxima(resultSet.getInt("edadMaxima"));
                familia.setNumHijos(resultSet.getInt("numHijos"));
                familia.setEmail(resultSet.getString("email"));
                familia.setIdCasaFamilia(resultSet.getInt("idCasaFamilia"));
            }
            closeDataBase();
            System.out.println(familia.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }



}
