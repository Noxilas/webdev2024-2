////////////////////////////////////////////////////////
//para ir ataulizando a lista:
function desabilitar_botao(){
    $("#botaoenviar").prop("disabled", true);
    setTimeout(habilitar_botao, 2000); // Aguarda 2 segundos antes de chamar a segunda função
}

function habilitar_botao(){
    $("#botaoenviar").prop("disabled", false);
}


function loadDoc(){
const xhttp = new XMLHttpRequest();
desabilitar_botao();
xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
        atualizar(JSON.parse(this.responseText)); // Converte JSON para objeto
    }
};


xhttp.open("GET", "leilao", true);
xhttp.send();
}

function atualizar(lances) {
let tabela = document.getElementById("tabela_de_lances"); 
tabela.innerHTML = `<tr>
    <th>Nome do Produto</th>
    <th>ID do Produto</th>
    <th>Valor do Produto</th>
</tr>`;

lances.forEach(lance => {
    let linha = "<tr><td>" + lance.nome_produto + "</td><td>" + lance.id_produto + "</td><td>" +lance.valor + "</td></tr>";
    tabela.innerHTML += linha;
});
}

setInterval(loadDoc, 15000);

////////////////////////////////////////////////////////
//para carregar a lista inicial:


