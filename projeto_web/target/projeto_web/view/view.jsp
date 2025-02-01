<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="utilitarios.Lance"%>
<%@page import="utilitarios.Produto"%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Leilao</title>
</head>
<body>
    <h1>leilao</h1>

    <%
        List<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto("Carro", 1));
        produtos.add(new Produto("Moto", 2));
        produtos.add(new Produto("Lancha", 3));
        produtos.add(new Produto("Avião", 4));
    %>
    
    <p>Os produtos disponiveis para leilão são:</p>

    <%for (Produto produto_recuperado : produtos){%>
    <tr>
        <td><%= produto_recuperado.get_nome() %></td>
        <td><%= produto_recuperado.get_id() %></td>
        <br/>
    </tr>
    <% }%>

    <br>
    

    <form action="leilao" method="post">
        <label for="produto">Nome do Produto:</label>
    <select id="id_produto" name="id_produto">
        <% for (Produto produto_recuperado : produtos) { %>
            <option value="<%= produto_recuperado.get_id() %>">
                <%= produto_recuperado.get_nome() %>
            </option>
        <% } %>
    </select>
    
    <br><br>
    <label for="id_usuario">Id do usuário:</label>
    <input type="text" id="id_usuario" name="id_usuario" > <%-- contém o ID do cliente, que vou supor o numero fixo "123"por enquanto --%>
    <br><br>

        <label for="valor">Valor do Produto:</label>
        <input type="number" id="valor" name="valor"><br><br>

        <input type="submit" value="Enviar">
    </form>



    <p>-----------------------------------------</p>

    <% List<Lance> lances = (List<Lance>)request.getAttribute("lista_de_lances"); %>
    <%if (lances!=null){%>
    <%for (Lance lance_recuperado : lances){%>
    <tr>
        <td><%= lance_recuperado.get_nome_produto() %></td>
        <td><%= lance_recuperado.get_id() %></td>
        <td><%= lance_recuperado.get_valor() %></td>
        <br/>
    </tr>
    <% }}%>

</body>
</html>

