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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <!--<link rel="stylesheet" type="text/css"  href="personalizacao.css">-->
    <link rel="stylesheet" type="text/css"  href="view/personalizacao.css">
    <script src="view/testandoajax.js"></script>

    <!-- <link rel="stylesheet" href="/meuProjeto/css/styles.css">-->
</head>
<body>
    <h1>Leilão</h1>
    <div class = "container">
        <div class="esquerda">

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
                    <th>Nome do Produto</th>
                        <th>ID do Produto</th>
                        <th>Valor do Produto</th>
                </tr>
            </thead>
            <%for (Produto produto_recuperado : produtos){%>
            <tr>
                <td><%= produto_recuperado.get_nome() %></td>
                <td><%= produto_recuperado.get_id() %></td>
                <td><%= produto_recuperado.get_lance_minimo() %></td>
                
            </tr>
            <% }%>
        </table>

        
        

        <form id = "formulario" action="leilao" method="post">
            <label for="produto">Nome do Produto:</label>
        <select id="id_produto" name="id_produto">
            <% for (Produto produto_recuperado : produtos) { %>
                <option value="<%= produto_recuperado.get_id() %>">
                    <%= produto_recuperado.get_nome() %>
                </option>
            <% } %>
        </select>
        
        
        <label for="id_usuario">Id do usuário:</label>
        <input type="text" id="id_usuario" name="id_usuario" > <%-- contém o ID do cliente, que vou supor o numero fixo "123"por enquanto --%>
        

            <label for="valor">Valor do Produto:</label>
            <input type="number" id="valor" name="valor">

            <input type="submit" id = "botaoenviar" value="Enviar">
        </form>


    </div>



    <div class = "direita">
        <h2>Lista de lances</h2>

        <% List<Lance> lances = (List<Lance>)request.getAttribute("lista_de_lances"); %>
        <%if(lances==null){%>
            <table>
                <thead>
                    <tr>
                        <th>Nome do Produto</th>
                            <th>ID do Produto</th>
                            <th>Valor do Produto</th>
                    </tr>
                </thead>
                <%for (Produto produto_recuperado : produtos){%>
                <tr>
                    <td><%= produto_recuperado.get_nome() %></td>
                    <td><%= produto_recuperado.get_id() %></td>
                    <td><%= produto_recuperado.get_lance_minimo() %></td>
                    
                </tr>
                <% }%>
            </table>
        <%}%>
        <%if (lances!=null){%>
            <table id="tabela_de_lances">
                <thead>
                    <tr>
                        <th>Nome do Produto</th>
                        <th>ID do Produto</th>
                        <th>Valor do Produto</th>
                    </tr>
                </thead>
                <tbody>
                <%for (Lance lance_recuperado : lances){%>
                    <tr>
                        <td><%= lance_recuperado.get_nome_produto() %></td>
                        <td><%= lance_recuperado.get_id() %></td>
                        <td><%= lance_recuperado.get_valor() %></td>
                                            </tr>
                <% }}%>
                </tbody>
            </table>

    </div>
</div>
</body>
</html>

