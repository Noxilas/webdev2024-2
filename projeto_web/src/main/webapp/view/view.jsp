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
    <h1>Leilão</h1>

    <h2>Faça seu lance</h2>


    <%
        List<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto("Carro", 1));
        produtos.add(new Produto("Moto", 2));
        produtos.add(new Produto("Lancha", 3));
        produtos.add(new Produto("Avião", 4));
    %>
    
    <p>Os produtos disponiveis para leilão são:</p>

    <table>
        <thead>
            <tr>
                <th>nome do produto</th>
                <th>id do produto</th>
                <th>lance minimo</th>
            </tr>
        </thead>
        <%for (Produto produto_recuperado : produtos){%>
        <tr>
            <td><%= produto_recuperado.get_nome() %></td>
            <td><%= produto_recuperado.get_id() %></td>
            <td><%= produto_recuperado.get_lance_minimo() %></td>
            <br/>
        </tr>
        <% }%>
    </table>

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

    <h2>Lista de lances (atualizada a cada X segundos)</h2>

    <% List<Lance> lances = (List<Lance>)request.getAttribute("lista_de_lances"); %>
    <%if (lances!=null){%>
        <table>
            <thead>
                <tr>
                    <th>nome do produto</th>
                    <th>id do produto</th>
                    <th>valor do produto</th>
                </tr>
            </thead>
            <tbody>
            <%for (Lance lance_recuperado : lances){%>
                <tr>
                    <td><%= lance_recuperado.get_nome_produto() %></td>
                    <td><%= lance_recuperado.get_id() %></td>
                    <td><%= lance_recuperado.get_valor() %></td>
                    <br/>
                </tr>
            <% }}%>
            </tbody>
        </table>

        <p>testando ajax: <span id="ajax">não fez nada</span></p>

        <p><input id="btatualizar" value="Atualizar" type="button" onclick="atualizar()"/> </p>

        <script>
            function atualizar() {
                document.getElementById("ajax").innerHTML = "funfou";
            }
        </script>

</body>
</html>

