package utilitarios;
//esta classe serve para ter os lances que serão recebido pelo view, ou seja, os lances devem conter:
// -nome do produto
// -valor do produto
// -id do produto

public class Lance {
    String nome_produto;
    double valor;
    int id_produto;

    // Construtor
    public Lance(String nome_produto, double valor, int id_produto) {
        this.nome_produto = nome_produto;
        this.valor = valor;
        this.id_produto = id_produto;
    }

    // Getter e Setter para nome_produto
    public String get_nome_produto() {
        return nome_produto;
    }

    public void set_nome_produto(String nome_produto) {
        this.nome_produto = nome_produto;
    }

    // Getter e Setter para valor
    public double get_valor() {
        return valor;
    }

    public void set_valor(double valor) {
        this.valor = valor;
    }

    // Getter e Setter para id_produto
    public int get_id() {
        return id_produto;
    }

    public void set_id(int id_produto) {
        this.id_produto = id_produto;
    }
}