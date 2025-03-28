package persistency;

import entity.Comentario;
import java.util.ArrayList;
import java.util.List;

public class ComentarioDAO  extends DAO{

    public ComentarioDAO() {
    }
    //agregar, eliminar, listar, buscar, modificar
    public void agregarComentario(Comentario comentario) throws Exception {
        try {
            String sql = "INSERT INTO comentario (id_comentario, id_casa, comentario) VALUES ("
                    + comentario.getIdComentario() + ", "
                    + comentario.getIdCasa() + ", '"
                    + comentario.getComentario() + "')";
            crud(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void listarComentarios() throws Exception {
        try {
            String sql = "SELECT * FROM comentarios";
            consultarDataBase(sql);
            List<Comentario> comentarios = new ArrayList<>();
            while (resultSet.next()) {
                Comentario comentario = new Comentario();
                comentario.setIdComentario(resultSet.getInt("id_comentario"));
                comentario.setIdCasa(resultSet.getInt("id_casa"));
                comentario.setComentario(resultSet.getString("comentario"));
                comentarios.add(comentario);
            }
            closeDataBase();
            for (Comentario com : comentarios) {
                System.out.println(com.toString());
                System.out.println("--------------------");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void eliminarComentario(int id) throws Exception {
        try {
            String sql = "DELETE FROM comentario WHERE id_comentario = " + id;
            crud(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void buscarComentario(int id) throws Exception {
        try {
            String sql = "SELECT * FROM comentario WHERE id_comentario = " + id;
            consultarDataBase(sql);
            Comentario comentario = new Comentario();
            while (resultSet.next()) {
                comentario.setIdComentario(resultSet.getInt("id_comentario"));
                comentario.setIdCasa(resultSet.getInt("id_casa"));
                comentario.setComentario(resultSet.getString("comentario"));
            }
            closeDataBase();
            System.out.println(comentario.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
