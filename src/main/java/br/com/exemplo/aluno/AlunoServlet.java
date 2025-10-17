package br.com.exemplo.aluno;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "AlunoServlet", urlPatterns = {"/alunos"})
public class AlunoServlet extends HttpServlet {

    private final AlunoDAO dao = new AlunoDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";
        try {
            switch (action) {
                case "new":
                    mostrarFormulario(req, resp, new Aluno());
                    break;
                case "edit":
                    int id = Integer.parseInt(req.getParameter("id"));
                    Aluno a = dao.buscarPorId(id);
                    if (a == null) {
                        resp.sendRedirect(req.getContextPath() + "/alunos");
                        return;
                    }
                    mostrarFormulario(req, resp, a);
                    break;
                case "delete":
                    deletar(req, resp);
                    break;
                default:
                    listar(req, resp);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String idStr = req.getParameter("id");
        String nome = req.getParameter("nome");
        String email = req.getParameter("email");
        String dataStr = req.getParameter("dataNascimento");
        LocalDate data = (dataStr == null || dataStr.isEmpty()) ? null : LocalDate.parse(dataStr);

        Aluno aluno = new Aluno(nome, email, data);
        try {
            if (idStr == null || idStr.isEmpty()) {
                dao.inserir(aluno);
            } else {
                aluno.setId(Integer.parseInt(idStr));
                dao.atualizar(aluno);
            }
            resp.sendRedirect(req.getContextPath() + "/alunos");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void listar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        List<Aluno> alunos = dao.listar();
        req.setAttribute("alunos", alunos);
        req.getRequestDispatcher("/WEB-INF/views/alunos.jsp").forward(req, resp);
    }

    private void mostrarFormulario(HttpServletRequest req, HttpServletResponse resp, Aluno aluno) throws ServletException, IOException {
        req.setAttribute("aluno", aluno);
        req.getRequestDispatcher("/WEB-INF/views/aluno-form.jsp").forward(req, resp);
    }

    private void deletar(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        dao.deletar(id);
        resp.sendRedirect(req.getContextPath() + "/alunos");
    }
}
