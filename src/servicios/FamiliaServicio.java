package servicios;

import entity.Familia;
import java.sql.SQLException;
import java.util.List;
import persistency.FamiliaDAO;

public class FamiliaServicio {
    private final FamiliaDAO familiaDAO = new FamiliaDAO();
    
    public void insertarFamilia(String nombre, int edadMinima, int edadMaxima, 
                              int numHijos, String email, int idCasaFamilia) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacío.");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("El email no puede ser nulo o vacío.");
        }
        if (edadMinima < 0 || edadMaxima < 0 || numHijos < 0 || idCasaFamilia < 0) {
            throw new IllegalArgumentException("Los valores numéricos no pueden ser negativos.");
        }

        try {
            Familia familia = new Familia(nombre, edadMinima, edadMaxima, numHijos, email, idCasaFamilia);
            familiaDAO.agregarFamilia(familia);
            System.out.println("Familia agregada correctamente: " + familia);
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error al guardar la familia: " + e.getMessage());
            throw new RuntimeException("Error al guardar la familia", e);
        }
    }

    public void listarFamiliasHijosYEdad(int edadMaxima, int hijosMinimos) {
        if (edadMaxima < 0 || hijosMinimos < 0) {
            throw new IllegalArgumentException("La edad máxima y el número de hijos no pueden ser negativos.");
        }
        
        try {
            List<Familia> familias = familiaDAO.listarFamiliasHijosYEdad(edadMaxima, hijosMinimos);
            System.out.println("\n--- Familias con al menos " + hijosMinimos + 
                             " hijos y edad máxima menor a " + edadMaxima + " ---");
            imprimirFamilias(familias);
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error al listar familias: " + e.getMessage());
            throw new RuntimeException("Error al listar familias", e);
        }
    }

    public void listarTodasLasFamilias() {
        try {
            List<Familia> familias = familiaDAO.listarFamilias();
            System.out.println("\n--- Todas las familias registradas ---");
            imprimirFamilias(familias);
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error al listar familias: " + e.getMessage());
            throw new RuntimeException("Error al listar familias", e);
        }
    }

    public void eliminarFamilia(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("El ID de la familia no puede ser negativo.");
        }

        try {
            familiaDAO.eliminarFamilia(id);
            System.out.println("Familia con ID " + id + " eliminada correctamente.");
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error al eliminar familia con ID " + id + ": " + e.getMessage());
            throw new RuntimeException("Error al eliminar familia", e);
        }
    }

    public Familia buscarFamiliaPorId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("El ID de la familia no puede ser negativo.");
        }

        try {
            Familia familia = familiaDAO.buscarFamilia(id);
            if (familia != null) {
                System.out.println("\n--- Familia encontrada ---");
                System.out.println(familia);
                return familia;
            } else {
                System.out.println("No se encontró familia con ID " + id);
                return null;
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error al buscar familia con ID " + id + ": " + e.getMessage());
            throw new RuntimeException("Error al buscar familia", e);
        }
    }

    // Método auxiliar para imprimir listas de familias
    private void imprimirFamilias(List<Familia> familias) {
        if (familias.isEmpty()) {
            System.out.println("No se encontraron familias.");
        } else {
            System.out.println("--------------------");
            for (Familia familia : familias) {
                System.out.println(familia.toString());
            }
            System.out.println("--------------------");
        }
    }
}