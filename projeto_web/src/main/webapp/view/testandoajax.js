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
let tabela = document.getElementById("tabela_de_lances"); 
tabela.innerHTML = `<tr>
    <th>Nome do Produto</th>
    <th>ID do Produto</th>
    <th>Maior Lance</th>
</tr>`;

lances.forEach(lance => {
    let linha = "<tr><td>" + lance.nome_produto + "</td><td>" + lance.id_produto + "</td><td>" +lance.valor + "</td></tr>";
    tabela.innerHTML += linha;
});
}


function ajax_pedir_produtos(){
    const produtos_xhttp = new XMLHttpRequest();
    produtos_xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            atualizar_tabela_produtos(JSON.parse(this.responseText)); // Converte JSON para objeto
        }
    };
    
    
    produtos_xhttp.open("GET", "produtos", true);
    produtos_xhttp.send();
}
function atualizar_tabela_produtos(produtos){
    let tabela_de_produtos = document.getElementById("tabela_de_produtos");

    //para a tabela de produtos:
    tabela_de_produtos.innerHTML = "<tr><th>Nome do Produto</th><th>ID do Produto</th><th>Lance Minimo</th></tr>";

    produtos.forEach(produto => {
        let linha = "<tr><td>" + produto.nome + "</td><td>" + produto.id + "</td><td>" +produto.lance_minimo + "</td></tr>";
        console.log(linha);
        tabela_de_produtos.innerHTML += linha;
    });
}

setInterval(loadDoc, 15000);
window.onload = function() {
    ajax_pedir_produtos();
    loadDoc()
};
