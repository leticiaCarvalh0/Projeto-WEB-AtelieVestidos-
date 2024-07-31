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
        <h1>Cadastro Modelo</h1>
        <table>
            <form id="cadastroForm" name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/ModeloControlador" method="get">
                <input type="hidden" name="opcao" value="${opcao}" />
                <input type="hidden" name="codigoModelo" value="${codigoModelo}" />
                <p><label>Nome:</label> <input type="text" name="nomeModelo" value="${nomeModelo}" size="40" /> </p>
                <td> 
                    <input type="submit" value="Salvar" name="Salvar"  /> 
                </td>
            </form>

            <form  name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/ModeloControlador" method="get">
                <td>
                    <input type="submit" value="Cancelar" name="Cancelar"  />
                </td>
                <input type="hidden" name="opcao" value="cancelar" />
            </form>
        </table>
        ${mensagem}

        <table border="1">
            <c:if test="${not empty modelos}">
                <tr>
                    <th>Código</th>
                    <th>Modelo</th>
                    <th>Alterar</th>
                    <th>Excluir</th>
                </tr> 
            </c:if>

            <c:forEach var="modelo" items="${modelo}">
                <tr>
                    <td>${modelo.codigoModelo}</td>
                    <td>${modelo.nomeModelo}</td>
                    
                    <td>
                        <form name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/ModeloControlador" method="get">
                            <input type="hidden" name="codigoModelo" value="${modelo.codigoModelo}" >
                            <input type="hidden" name="nomeModelo" value="${modelo.nomeModelo}" >
                            <input type="hidden" name="opcao" value="editar" >
                            <button type="submit">Editar</button>
                        </form>    
                    </td>
                    <td>
                        <form name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/ModeloControlador" method="get">
                            <input type="hidden" name="codigoModelo" value="${modelo.codigoModelo}" >
                            <input type="hidden" name="nomeModelo" value="${modelo.nomeModelo}" >
                            <input type="hidden" name="opcao" value="excluir" >
                            <button type="submit">Excluir</button>
                        </form>    
                    </td>
                </tr>
            </c:forEach>



        </table>

    </body>
</html>
