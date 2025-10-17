package br.com.exemplo.aluno;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static final String JDBC_URL = "jdbc:h2:~/cadastro_aluno_db;AUTO_SERVER=TRUE";
    private static final String USER = "sa";
    private static final String PASS = "";

    static {
        try {
            initSchema();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inicializar o schema do H2", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USER, PASS);
    }

    private static void initSchema() throws SQLException {
        try (Connection conn = getConnection(); Statement st = conn.createStatement()) {
            st.execute("CREATE TABLE IF NOT EXISTS aluno (" +
                    "id IDENTITY PRIMARY KEY, " +
                    "nome VARCHAR(100) NOT NULL, " +
                    "email VARCHAR(150) NOT NULL, " +
                    "data_nascimento DATE NOT NULL" +
                    ")");
        }
    }
}
