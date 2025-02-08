////////////////////////////////////////////////////////
//para ir atualizando a lista direita:
function desabilitar_botao(){
    $("#botaoenviar").prop("disabled", true);
    setTimeout(habilitar_botao, 2000); // Aguarda 2 segundos antes de chamar a segunda função
}

function habilitar_botao(){
    $("#botaoenviar").prop("disabled", false);
}


function loadDoc(produto_selecionado){
    if(!produto_selecionado)return;
    const xhttp = new XMLHttpRequest();
    desabilitar_botao();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            atualizar(JSON.parse(this.responseText)); // Converte JSON para objeto
        }
    };

    // Adiciona o produto_selecionado como parâmetro na URL
    const url = `leilao?id_produto=${encodeURIComponent(produto_selecionado)}`;

    xhttp.open("GET", url, true);
    xhttp.send();
}

function atualizar(lances) {
    let tabela = document.getElementById("tabela_de_lances"); 
    tabela.innerHTML = `<tr>
        <th>ID do Produto</th>
        <th>ID do Usuario</th>
        <th>Valor Lance</th>
    </tr>`;

    lances.forEach(lance => {
        console.log(lance)
        let linha = "<tr><td>" + lance.id_produto + "</td><td>" + lance.id_usuario + "</td><td>" +lance.valor_lance + "</td></tr>";
        tabela.innerHTML += linha;
    });
}

//////////////////////////////////////////////////////////////////////////////
//para ir atualizando a lista esquerda:
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
    tabela_de_produtos.innerHTML = "<tr><th>Nome do Produto</th><th>ID do Produto</th><th>Lance Mínimo</th></tr>";

    produtos.forEach(produto => {
        console.log(produto)
        let linha = "<tr><td>" + produto.nome + "</td><td>" + produto.id + "</td><td>" +produto.lance_minimo + "</td></tr>";
        console.log(linha);
        tabela_de_produtos.innerHTML += linha;
    });
}
//////////////////////////////////////////////////////////////////////////////
//para a mensagem de erro:
function mensagem_erro(produto_selecionado){
    if(!produto_selecionado)return;
    const xhttp = new XMLHttpRequest();
    desabilitar_botao();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            atualizar_mensagem_erro(JSON.parse(this.responseText)); // Converte JSON para objeto
        }
    };

    // Adiciona o produto_selecionado como parâmetro na URL
    const url = `erro?id_produto=${encodeURIComponent(produto_selecionado)}`;

    xhttp.open("GET", url, true);
    xhttp.send();
}

function atualizar_mensagem_erro(minimo){
    $("#formulario").submit(function(event) {
        console.log("entrou dentro de dentro da funcao");
    
        let valorLance = parseFloat($("#valor").val());
        let lanceMinimo = minimo; 
        $("#erro-lance").show();
        //verificação
        if (valorLance < lanceMinimo) {
            $("#erro-lance").text("O lance deve ser maior ou igual a " + lanceMinimo + ".").css("color", "red");
            
            
            event.preventDefault(); //aqui o lance nem é enviado ao controller
            setTimeout(() => {
                $("#erro-lance").fadeOut(500);
            }, 2000);
        } else {
            $("#erro-lance").text(""); //caso o valor seja válido, a mensagem nao eh exibida
        }
    });

}

//////////////////////////////////////////////////////////////////////////////
//para atualizar a tabela de lances assim que o produto é selecionado
let produto_selecionado = null;

document.addEventListener("DOMContentLoaded", function() {
    document.getElementById("id_produto").addEventListener("blur", function() {
        produto_selecionado = this.value;
        loadDoc(produto_selecionado);
        mensagem_erro(produto_selecionado);
    });
});

    
//////////////////////////////////////////////////////////////////////////////
setInterval(function() {
    loadDoc(produto_selecionado);
}, 15000);

window.onload = function() {
    ajax_pedir_produtos();
    //loadDoc();
};
