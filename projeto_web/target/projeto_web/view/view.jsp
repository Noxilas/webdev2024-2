<%@page contentType="text/html" pageEncoding="UTF-8"%>

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
        <input type="text" id="produto" name="produto"><br><br>

        <label for="valor">Valor do Produto:</label>
        <input type="number" id="valor" name="valor"><br><br>

        <input type="submit" value="Enviar">
    </form>


</body>
</html>

