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
        <h1>Cadastro Cliente</h1>
        <table>
            <form id="cadastroForm" name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/ClienteControlador" method="get">
                <input type="hidden" name="opcao" value="${opcao}" />
                <input type="hidden" name="codigoCliente" value="${codigoCliente}" />
                <p><label>Nome:</label> <input type="text" name="nomeCliente" value="${nomeCliente}" size="40" /> </p>
                <p><label>E-mail:</label> <input type="text" name="email" value="${email}" size="40" /> </p>
                <p><label>Telefone:</label> <input type="text" name="telefone" value="${telefone}" size="50" /> </p>
                <p><label>Endereço:</label> <input type="text" name="endereco" value="${endereco}" size="50" /> </p>
                <td> 
                    <input type="submit" value="Salvar" name="Salvar"  /> 
                </td>
            </form>

            <form  name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/ClienteControlador" method="get">
                <td>
                    <input type="submit" value="Cancelar" name="Cancelar"  />
                </td>
                <input type="hidden" name="opcao" value="cancelar" />
            </form>
        </table>
        ${mensagem}

        <table border="1">
            <c:if test="${not empty clientes}">
                <tr>
                    <th>Código</th>
                    <th>Nome</th>
                    <th>E-mail</th>
                    <th>Telefone</th>
                    <th>Endereço</th>
                    <th>Alterar</th>
                    <th>Excluir</th>
                </tr> 
            </c:if>

            <c:forEach var="cliente" items="${clientes}">
                <tr>
                    <td>${cliente.codigoCliente}</td>
                    <td>${cliente.nomeCliente}</td>
                    <td>${cliente.email}</td>
                    <td>${cliente.telefone}</td>
                    <td>${cliente.endereco}</td>
                    <td>
                        <form name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/ClienteControlador" method="get">
                            <input type="hidden" name="codigoCliente" value="${cliente.codigoCliente}" >
                            <input type="hidden" name="nomeCliente" value="${cliente.nomeCliente}" >
                            <input type="hidden" name="email" value="${cliente.email}" >
                            <input type="hidden" name="telefone" value="${cliente.telefone}" >
                            <input type="hidden" name="endereco" value="${cliente.endereco}" >
                            <input type="hidden" name="opcao" value="editar" >
                            <button type="submit">Editar</button>
                        </form>    
                    </td>
                    <td>
                        <form name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/ClienteControlador" method="get">
                            <input type="hidden" name="codigoCliente" value="${cliente.codigoCliente}" >
                            <input type="hidden" name="nomeCliente" value="${cliente.nomeCliente}" >
                            <input type="hidden" name="email" value="${cliente.email}" >
                            <input type="hidden" name="telefone" value="${cliente.telefone}" >
                            <input type="hidden" name="endereco" value="${cliente.endereco}" >
                            <input type="hidden" name="opcao" value="excluir" >
                            <button type="submit">Excluir</button>
                        </form>    
                    </td>
                </tr>
            </c:forEach>



        </table>

    </body>
</html>
