<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8"/>
    <title>Alunos</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 24px; }
        table { border-collapse: collapse; width: 100%; }
        th, td { border: 1px solid #ddd; padding: 8px; }
        th { background: #f2f2f2; }
        a.button { padding: 6px 10px; background: #1976d2; color: #fff; text-decoration: none; border-radius: 4px; }
        .danger { background: #d32f2f; }
    </style>
</head>
<body>
<h1>Cadastro de Alunos</h1>
<p>
    <a class="button" href="${pageContext.request.contextPath}/alunos?action=new">Novo Aluno</a>
</p>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Nome</th>
        <th>Email</th>
        <th>Data de Nascimento</th>
        <th>Ações</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="a" items="${alunos}">
        <tr>
            <td>${a.id}</td>
            <td>${a.nome}</td>
            <td>${a.email}</td>
            <td><c:out value="${a.dataNascimento}"/></td>
            <td>
                <a class="button" href="${pageContext.request.contextPath}/alunos?action=edit&id=${a.id}">Editar</a>
                <a class="button danger" href="${pageContext.request.contextPath}/alunos?action=delete&id=${a.id}" onclick="return confirm('Excluir este aluno?');">Excluir</a>
            </td>
        </tr>
    </c:forEach>
    <c:if test="${empty alunos}">
        <tr>
            <td colspan="5" style="text-align:center">Nenhum aluno cadastrado.</td>
        </tr>
    </c:if>
    </tbody>
</table>
</body>
</html>
