<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Leilao</title>
</head>
<body>
    <h1>leilao</h1>
    

    <p>produtos disponiveis para leilão:</p>
    <ul> 
        <li>carro</li>
        <li>pintura</li>
        <li>escultura</li>
    </ul>

    <form action="leilao" method="post">
        <label for="produto">Nome do Produto:</label>
    <select id="id_produto" name="id_produto">
        <option value="1">Produto 1</option> <%--produto 1 de valor id=1--%>
        <option value="2">Produto 2</option>
        <option value="3">Produto 3</option>
        <option value="4">Produto 4</option>
    </select><br><br>
    <input type="hidden" id="id_cliente" name="id_cliente" value="123"> <%-- contém o ID do cliente, que vou supor o numero fixo "123"por enquanto --%>


        <label for="valor">Valor do Produto:</label>
        <input type="number" id="valor" name="valor"><br><br>

        <input type="submit" value="Enviar">
    </form>


    <p>valor recebido do ultimo lance enviado:</p>
    <p>produto: <%= request.getAttribute("produto") %></p> 
    <p>valor: <%= request.getAttribute("valor") %></p> 

    <p>-----------------------------------------</p>

    <%--<% List<Lance> lances = (List<Lance>)request.getAttribute("lances"); %>--%>
    <%for (Lance lance_recuperado : lances){%>
    <tr>
        <td><%= lance_recuperado.get_nome_produto() %></td>
        <td><%= lance_recuperado.get_id() %></td>
        <td><%= lance_recuperado.valor() %></td>
    </tr>

</body>
</html>

