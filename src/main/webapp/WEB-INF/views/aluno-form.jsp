<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8"/>
    <title>Aluno - Formulário</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 24px; }
        form { max-width: 520px; }
        label { display: block; margin-top: 12px; }
        input[type=text], input[type=email], input[type=date] { width: 100%; padding: 8px; box-sizing: border-box; }
        .actions { margin-top: 16px; }
        a.button, button { padding: 6px 10px; background: #1976d2; color: #fff; text-decoration: none; border: 0; border-radius: 4px; cursor: pointer; }
        a.button { display: inline-block; }
    </style>
</head>
<body>
<h1>${aluno.id == null ? 'Novo Aluno' : 'Editar Aluno'}</h1>
<form method="post" action="${pageContext.request.contextPath}/alunos">
    <input type="hidden" name="id" value="${aluno.id}"/>

    <label>Nome</label>
    <input type="text" name="nome" value="${aluno.nome}" required/>

    <label>Email</label>
    <input type="email" name="email" value="${aluno.email}" required/>

    <label>Data de Nascimento</label>
    <input type="date" name="dataNascimento" value="${aluno.dataNascimento}" required/>

    <div class="actions">
        <button type="submit">Salvar</button>
        <a class="button" href="${pageContext.request.contextPath}/alunos">Cancelar</a>
    </div>
</form>
</body>
</html>
