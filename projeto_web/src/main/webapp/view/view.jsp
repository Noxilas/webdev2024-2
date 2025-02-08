<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Lance"%>
<%@page import="model.Produto"%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Leilao</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <!--<link rel="stylesheet" type="text/css"  href="personalizacao.css">-->
    <link rel="stylesheet" type="text/css"  href="view/personalizacao.css">
    <script src="view/testandoajax.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

    <!--testando a verificação de lances-->
    <!--acho que quando for puxar do bd, vai precisar de algumas outras alterações-->
    <script>
        //pra puxar pelo controller, vamos usar um request get attribute, mas deve ser fácil
        $(document).ready(function() {
            $("#formulario").submit(function(event) {
                let valorLance = parseFloat($("#valor").val());
                let lanceMinimo = 10; //testando com valor min = 10
                //verificação
                if (valorLance < lanceMinimo) {
                    $("#erro-lance").text("O lance deve ser maior ou igual a " + lanceMinimo + ".").css("color", "red");
                    event.preventDefault(); //aqui o lance nem é enviado ao controller
                } else {
                    $("#erro-lance").text(""); //caso o valor seja válido, a mensagem nao eh exibida
                }
            });
        });
    </script>

    <!-- <link rel="stylesheet" href="/meuProjeto/css/styles.css">-->
</head>
<!--<body onload="loadDoc()">-->
<body>
    <h1>Leilão</h1>
    <div class = "container">
        <div class="esquerda">

        <h2>Faça seu lance</h2>

        
        <p>Os produtos disponiveis para leilão são:</p>

        <table id="tabela_de_produtos">
            
            <thead>
                <tr>
                    <th>Nome do Produto</th>
                    <th>ID do Produto</th>
                    <th>Lance Mínimo</th>
                </tr>
            </thead>

        </table>

        <form id = "formulario" action="leilao" method="post">
            <label for="id_produto">Id do produto:</label>
        <input type="text" id="id_produto" name="id_produto" > 
        
        <br>
        
        <label for="id_usuario">Id do usuário:</label>
        <input type="text" id="id_usuario" name="id_usuario" > 
        
            <br>
            <label for="valor">Valor do Lance:</label>
            <input type="number" id="valor" name="valor">

            <!--mensagem de erro caso o lance seja menor-->
            <br>
            <span id="erro-lance"></span>
            <br>

            <input type="submit" id = "botaoenviar" value="Enviar">
        </form>


    </div>



    <div class = "direita">
        <h2>Lista de lances</h2>

        <table id="tabela_de_lances">
            
        </table>

    </div>
</div>
</body>
</html>

