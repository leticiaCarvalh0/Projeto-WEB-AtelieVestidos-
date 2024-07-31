<%-- 
    Document   : CadastroEstilista
    Created on : 23 de jul de 2024, 19:30:34
    Author     : 11151024600
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="menu.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>

    <script>

        function submitForm(opcaoValue) {

            document.getElementById("opcao").value = opcaoValue;
            document.getElementById("cadastroForm").submit();
        }


    </script>


    <body>
        <h1>Cadastro Estilista</h1>
        <table>
            <form id="cadastroForm" name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/EstilistaControlador" method="get">
                <input type="hidden" name="opcao" value="${opcao}" />
                <input type="hidden" name="codigoEstilista" value="${codigoEstilista}" />
                <p><label>Nome:</label> <input type="text" name="nomeEstilista" value="${nomeEstilista}" size="40" /> </p>
                <p><label>E-mail:</label> <input type="text" name="email" value="${email}" size="50" /> </p>
                <p><label>Telefone:</label> <input type="text" name="telefone" value="${telefone}" size="15" /> </p>
                <p><label>Endereço:</label> <input type="text" name="especialidade" value="${especialidade}" size="10" /> </p>
                <td> 
                    <input type="submit" value="Salvar" name="Salvar"  /> 
                </td>
            </form>

            <form  name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/EstilistaControlador" method="get">
                <td>
                    <input type="submit" value="Cancelar" name="Cancelar"  />
                </td>
                <input type="hidden" name="opcao" value="cancelar" />
            </form>
        </table>
        ${mensagem}

        <table border="1">
            <c:if test="${not empty estilistas}">
                <tr>
                    <th>Código</th>
                    <th>Nome</th>
                    <th>E-mail</th>
                    <th>Telefone</th>
                    <th>Especialidade</th>
                    <th>Alterar</th>
                    <th>Excluir</th>
                </tr> 
            </c:if>

            <c:forEach var="estilista" items="${estilistas}">
                <tr>
                    <td>${cliente.codigoEstilista}</td>
                    <td>${cliente.nomeEstilista}</td>
                    <td>${cliente.email}</td>
                    <td>${cliente.telefone}</td>
                    <td>${cliente.especialidade}</td>
                    <td>
                        <form name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/EstilistaControlador" method="get">
                            <input type="hidden" name="codigoEstilista" value="${estilista.codigoEstilista}" >
                            <input type="hidden" name="nomeEstilista" value="${estilista.nomeEstilista}" >
                            <input type="hidden" name="email" value="${estilista.email}" >
                            <input type="hidden" name="telefone" value="${estilista.telefone}" >
                            <input type="hidden" name="especialidade" value="${estilista.especialidade}" >
                            <input type="hidden" name="opcao" value="editar" >
                            <button type="submit">Editar</button>
                        </form>    
                    </td>
                    <td>
                        <form name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/EstilistaControlador" method="get">
                            <input type="hidden" name="codigoEstilista" value="${estilista.codigoEstilista}" >
                            <input type="hidden" name="nomeEstilista" value="${estilista.nomeEstilista}" >
                            <input type="hidden" name="email" value="${estilista.email}" >
                            <input type="hidden" name="telefone" value="${estilista.telefone}" >
                            <input type="hidden" name="especialidade" value="${estilista.especialidade}" >
                            <input type="hidden" name="opcao" value="excluir" >
                            <button type="submit">Excluir</button>
                        </form>    
                    </td>
                </tr>
            </c:forEach>



        </table>

    </body>
</html>
