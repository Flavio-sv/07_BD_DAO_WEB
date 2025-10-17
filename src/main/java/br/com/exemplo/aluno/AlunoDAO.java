package br.com.exemplo.aluno;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    public List<Aluno> listar() throws SQLException {
        String sql = "SELECT id, nome, email, data_nascimento FROM aluno ORDER BY id";
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            List<Aluno> lista = new ArrayList<>();
            while (rs.next()) {
                Aluno a = new Aluno();
                a.setId(rs.getInt("id"));
                a.setNome(rs.getString("nome"));
                a.setEmail(rs.getString("email"));
                Date d = rs.getDate("data_nascimento");
                a.setDataNascimento(d != null ? d.toLocalDate() : null);
                lista.add(a);
            }
            return lista;
        }
    }

    public Aluno buscarPorId(int id) throws SQLException {
        String sql = "SELECT id, nome, email, data_nascimento FROM aluno WHERE id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Aluno a = new Aluno();
                    a.setId(rs.getInt("id"));
                    a.setNome(rs.getString("nome"));
                    a.setEmail(rs.getString("email"));
                    Date d = rs.getDate("data_nascimento");
                    a.setDataNascimento(d != null ? d.toLocalDate() : null);
                    return a;
                }
                return null;
            }
        }
    }

    public void inserir(Aluno aluno) throws SQLException {
        String sql = "INSERT INTO aluno (nome, email, data_nascimento) VALUES (?, ?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, aluno.getNome());
            ps.setString(2, aluno.getEmail());
            ps.setDate(3, aluno.getDataNascimento() != null ? Date.valueOf(aluno.getDataNascimento()) : null);
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    aluno.setId(rs.getInt(1));
                }
            }
        }
    }

    public void atualizar(Aluno aluno) throws SQLException {
        String sql = "UPDATE aluno SET nome = ?, email = ?, data_nascimento = ? WHERE id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, aluno.getNome());
            ps.setString(2, aluno.getEmail());
            ps.setDate(3, aluno.getDataNascimento() != null ? Date.valueOf(aluno.getDataNascimento()) : null);
            ps.setInt(4, aluno.getId());
            ps.executeUpdate();
        }
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM aluno WHERE id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
