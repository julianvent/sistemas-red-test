package uv.mx.sistemasredproject.server.models;

import uv.mx.sistemasredproject.model.Cita;
import uv.mx.sistemasredproject.model.Medico;
import uv.mx.sistemasredproject.model.Paciente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseDriver {

    private final Connection connection;

    public DatabaseDriver() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:schema.db");
            System.out.println("Conectado a la base de datos");
            initializeDatabase();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void initializeDatabase() {
        try {

            Statement checkStmt = connection.createStatement();
            ResultSet rs = checkStmt.executeQuery(
                    "SELECT COUNT(*) as registros FROM sqlite_master WHERE type='table' AND name IN('medico', " +
                            "'paciente', 'cita')");
            rs.next();
            int tableExists = rs.getInt("registros");
            rs.close();

            checkStmt.close();

            if (tableExists > 0) {
                System.out.println("La base de datos ya está inicializada");
                return;
            }

            var inputStream = getClass().getClassLoader().getResourceAsStream("schema.sql");
            System.out.println(getClass().getClassLoader().getResource("schema.sql"));

            if (inputStream == null) {
                throw new RuntimeException("No encontré el schema.sql");
            }

            StringBuilder sql = new StringBuilder();
            try (var reader = new java.io.BufferedReader(new java.io.InputStreamReader(inputStream))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    sql.append(line).append("\n");
                }
            }

            Statement stmt = connection.createStatement();
            stmt.executeUpdate(sql.toString());
            stmt.close();

            System.out.println("Base de datos inicializada del schema");

        } catch (Exception e) {
            throw new RuntimeException("Error al inicializar base de datos", e);
        }
    }

    /* Médicos */

    public void agregarMedico(String nombre, String especialidad, String cedula, String correoElectronico) {
        String sql = "INSERT INTO medico (nombre, especialidad, cedula, correo_electronico) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setString(2, especialidad);
            pstmt.setString(3, cedula);
            pstmt.setString(4, correoElectronico);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al agregar médico", e);
        }
    }

    public List<Medico> obtenerMedicos() {
        List<Medico> medicos = new ArrayList<>();
        String sql = "SELECT * FROM medico";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                medicos.add(new Medico(
                        rs.getInt("medico_id"),
                        rs.getString("nombre"),
                        rs.getString("especialidad"),
                        rs.getString("cedula"),
                        rs.getString("correo_electronico")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener médicos", e);
        }
        return medicos;
    }

    public Medico getMedico(int medicoId) {
        Medico medico = null;
        String sql = "SELECT * FROM medico WHERE medico_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, medicoId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                medico = new Medico(
                        rs.getInt("medico_id"),
                        rs.getString("nombre"),
                        rs.getString("especialidad"),
                        rs.getString("cedula"),
                        rs.getString("correo_electronico")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener médico", e);
        }
        return medico;
    }

    public void actualizarMedico(
            int medicoId,
            String nombre,
            String especialidad,
            String cedula,
            String correoElectronico
    ) {

        String sql = "UPDATE medico SET nombre = ?, especialidad = ?, cedula = ?, correo_electronico = ? WHERE " +
                "medico_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setString(2, especialidad);
            pstmt.setString(3, cedula);
            pstmt.setString(4, correoElectronico);
            pstmt.setInt(5, medicoId);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar médico", e);
        }
    }


    public void eliminarMedico(int medicoId) {

        String sql = "DELETE FROM medico WHERE medico_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, medicoId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar médico", e);
        }
    }

    /* Paciente */
    /*
        paciente_id INTEGER PRIMARY KEY AUTOINCREMENT
    , nombre TEXT NOT NULL
    , curp TEXT NOT NULL UNIQUE
    , telefono TEXT NOT NULL
    , correo_electronico TEXT NOT NULL
     */
    public void agregarPaciente(String nombre, String curp, String telefono, String correo_electronico) {
        String sql = "INSERT INTO paciente(nombre, curp, telefono, correo_electronico) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setString(2, curp);
            pstmt.setString(3, telefono);
            pstmt.setString(4, correo_electronico);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al agregar paciente", e);
        }
    }

    public List<Paciente> obtenerPacientes() {
        List<Paciente> pacientes = new ArrayList<>();
        String sql = "SELECT * FROM paciente";

        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                pacientes.add(new Paciente(
                        rs.getInt("paciente_id"),
                        rs.getString("nombre"),
                        rs.getString("curp"),
                        rs.getString("telefono"),
                        rs.getString("correo_electronico")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener pacientes", e);
        }
        return pacientes;
    }

    public Paciente getPaciente(int pacienteId) {
        Paciente paciente = null;
        String sql = "SELECT * FROM paciente WHERE paciente_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, pacienteId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                paciente = new Paciente(
                        rs.getInt("paciente_id"),
                        rs.getString("nombre"),
                        rs.getString("curp"),
                        rs.getString("telefono"),
                        rs.getString("correo_electronico")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener médico", e);
        }
        return paciente;
    }

    public void actualizarPaciente(int pacienteId, String nombre, String curp, String telefono) {
        String sql = "UPDATE paciente SET nombre = ?, curp = ?, telefono = ? WHERE paciente_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setString(2, curp);
            pstmt.setString(3, telefono);
            pstmt.setInt(4, pacienteId);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void eliminarPaciente(int pacienteId) {
        String sql = "DELETE FROM paciente WHERE paciente_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, pacienteId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /* Citas */
    public void agregarCita(String fechaHora, String motivo, int medicoId, int pacienteId) {
        String sql = "INSERT INTO cita(fecha_hora, motivo, medico_id, paciente_id) VALUES(?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, fechaHora);
            pstmt.setString(2, motivo);
            pstmt.setInt(3, medicoId);
            pstmt.setInt(4, pacienteId);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al agregar paciente", e);
        }
    }

    public List<Cita> obtenerCitas() {
        List<Cita> citas = new ArrayList<>();
        String sql = "SELECT * FROM cita";

        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                citas.add(new Cita(
                        rs.getInt("cita_id"),
                        rs.getString("fecha_hora"),
                        rs.getString("motivo"),
                        rs.getInt("medico_id"),
                        rs.getInt("paciente_id")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener pacientes", e);
        }
        return citas;
    }

    public void actualizarCita(int citaId, String fechaHora, String motivo, int medicoId) {
        String sql = "UPDATE cita SET fecha_hora = ?, motivo = ?, medico_id = ? WHERE cita_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, fechaHora);
            pstmt.setString(2, motivo);
            pstmt.setInt(3, medicoId);
            pstmt.setInt(4, citaId);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al editar cita", e);
        }
    }

    public void eliminarCita(int citaId) {
        String sql = "DELETE FROM cita WHERE cita_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, citaId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar paciente", e);
        }
    }

    public ResultSet executeQuery(String query) throws SQLException {
        return connection.createStatement().executeQuery(query);
    }
}
