////////////////////////////////////////////////////////
//para ir atualizando a lista:
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
    let tabela_de_lances = document.getElementById("tabela_de_lances"); 
    let tabela_de_produtos = document.getElementsById("tabela_de_produtos");

    //para a tabela de produtos:
    tabela_de_produtos.innerHTML = `<tr>
        <th>Nome do Produto</th>
        <th>ID do Produto</th>
        <th>Maior Lance</th>
    </tr>`;

    lances.forEach(lance => {
        //let linha = "<tr><td>" + lance.nome_produto + "</td><td>" + lance.id_produto + "</td><td>" +lance.valor + "</td></tr>";
       // tabela_de_lances.innerHTML += linha;
    });

    //////////////////////////////////////////////////////////////////////
    //para a tabela de produtos:
    tabela_de_lances.innerHTML = `<tr>
        <th>Usuário</th>
        <th>Produto</th>
        <th>ID Produto</th>
        <th>Valor Lance</th>
        <th>Horário</th>
    </tr>`;

    lances.forEach(lance => {
        //let linha = "<tr><td>" + lance.nome_produto + "</td><td>" + lance.id_produto + "</td><td>" +lance.valor + "</td></tr>";
        //tabela_de_lances.innerHTML += linha;
    });


}
////////////////////////////////////////////////////////
//intervalo para ir chamando a função:
setInterval(loadDoc, 15000);



////////////////////////////////////////////////////////



