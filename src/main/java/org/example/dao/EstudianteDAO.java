package org.example.dao;

import org.example.model.Estudiante;
import org.example.model.Programa;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EstudianteDAO {
    private Connection conexion;
    private static final String FILE_NAME = "data/Estudiantes.dat";

    public EstudianteDAO(Connection conexion) {
        this.conexion = conexion;
    }


    public void guardarEstudiante(Estudiante estudiante) {

        String query = "INSERT INTO PERSONA (nombre, apellidos, email, tipo, codigo, id_programa, activo, promedio) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, estudiante.getNombres());
            statement.setString(2, estudiante.getApellidos());
            statement.setString(3, estudiante.getEmail());
            statement.setString(4, "ESTUDIANTE");
            statement.setDouble(5, estudiante.getCodigo());
            statement.setInt(6, estudiante.getPrograma().getID());
            statement.setBoolean(7, estudiante.getActivo());
            statement.setDouble(8, estudiante.getPromedio());

            int filasAfectadas = statement.executeUpdate();
            if (filasAfectadas > 0) {
                try (ResultSet keys = statement.getGeneratedKeys()) {
                    if (keys.next()) {
                        estudiante.setID(keys.getInt(1));
                    }
                }
            }
        }catch (SQLException e){
            System.out.println("Error al guardar estudiante: " + e.getMessage());
        }

    }

    public Optional<Estudiante> obtenerEstudiantePorId(int id) {
        String query = "SELECT * FROM PERSONA WHERE id_persona = ? AND tipo = 'ESTUDIANTE'";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return Optional.of(new Estudiante(
                        resultSet.getInt("id_persona"),
                        resultSet.getString("nombre"),
                        resultSet.getString("apellidos"),
                        resultSet.getString("email"),
                        resultSet.getDouble("codigo"),
                        new Programa(resultSet.getInt("id_programa")),
                        resultSet.getBoolean("activo"),
                        resultSet.getDouble("promedio")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar estudiante: " + e.getMessage());
        }
        return Optional.empty();
    }

    public Optional<Estudiante> obtenerEstudiantePorCodigo(Double codigo) {
        String query = "SELECT * FROM PERSONA WHERE codigo = ? AND tipo = 'ESTUDIANTE'";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setDouble(1, codigo);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return Optional.of(new Estudiante(
                        resultSet.getInt("id_persona"),
                        resultSet.getString("nombre"),
                        resultSet.getString("apellidos"),
                        resultSet.getString("email"),
                        resultSet.getDouble("codigo"),
                        new Programa(resultSet.getInt("id_programa")),
                        resultSet.getBoolean("activo"),
                        resultSet.getDouble("promedio")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar estudiante: " + e.getMessage());
        }
        return Optional.empty();
    }


    public List<Estudiante> obtenerListaEstudiantes(){
        List<Estudiante> estudiantes = new ArrayList<>();
        String query = "SELECT * FROM PERSONA WHERE tipo = 'ESTUDIANTE'";
        try (Statement statement = conexion.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                estudiantes.add(new Estudiante(
                        resultSet.getInt("id_persona"),
                        resultSet.getString("nombre"),
                        resultSet.getString("apellidos"),
                        resultSet.getString("email"),
                        resultSet.getDouble("codigo"),
                        new Programa(resultSet.getInt("id_programa")),
                        resultSet.getBoolean("activo"),
                        resultSet.getDouble("promedio")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener cursos: " + e.getMessage());
        }
        return estudiantes;
    }

    public boolean actualizarEstudiante(Estudiante estudiante) {
        String query = "UPDATE PERSONA SET nombre = ?, apellidos = ?, email = ?, codigo = ?, id_programa = ?, activo = ?, promedio = ? WHERE id_persona = ? AND tipo = 'ESTUDIANTE'";
        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, estudiante.getNombres());
            statement.setString(2, estudiante.getApellidos());
            statement.setString(3, estudiante.getEmail());
            statement.setDouble(4, estudiante.getCodigo());
            statement.setInt(5, estudiante.getPrograma().getID());
            statement.setBoolean(6, estudiante.getActivo());
            statement.setDouble(7, estudiante.getPromedio());
            statement.setInt(8, estudiante.getID());

            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar estudiante: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarEstudiante(int id) {
        String query = "DELETE FROM PERSONA WHERE id_persona = ? AND tipo = 'ESTUDIANTE'";
        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setInt(1, id);
            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            if (e.getSQLState().equals("23000")) {
                System.err.println("No se puede eliminar el estudiante: está relacionado con otros registros.");
            } else {
                System.err.println("Error al eliminar estudiante: " + e.getMessage());
            }
            return false;
        }
    }


    public void guardarEstudianteBinario(Estudiante estudiante){
        List<Estudiante> estudiantes = cargarEstudiantes();
        estudiantes.add(estudiante);


        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(estudiantes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public List<Estudiante> cargarEstudiantes() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (List<Estudiante>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

}
