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
    <th>Usuário</th>
    <th>Produto</th>
    <th>ID Produto</th>
    <th>Valor Lance</th>
    <th>Horário</th>
</tr>`;

lances.forEach(lance => {
    let linha = "<tr><td>" + lance.nome_usuario + "</td><td>" + lance.nome_produto + "</td><td>" + lance.id_produto + "</td><td>" + lance.valor_lance + "</td><td>" + lance.time_stamp + "</td></tr>";
    tabela.innerHTML += linha;
});
}

setInterval(loadDoc, 15000);
