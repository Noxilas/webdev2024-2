package model;
//esta classe serve para ter os lances que serão recebido pelo view, ou seja, os lances devem conter:
// -nome do produto
// -valor do produto
// -id do produto

public class Lance {
    int id_produto, id_usuario;
    String nome_usuario, nome_produto, time_stamp;
    double valor_lance;

    // Construtor
    public Lance(int id_produto, int id_usuario, double valor_lance) {
        this.id_produto = id_produto;
        this.id_usuario = id_usuario;
        this.valor_lance = valor_lance;
    }

    // Getter e Setter para valor
    public double get_valor_lance() {
        return valor_lance;
    }

    public void set_valor_lance(double valor_lance) {
        this.valor_lance = valor_lance;
    }

    // Getter e Setter para id_produto
    public int get_id_produto() {
        return id_produto;
    }

    public void set_id_produto(int id_produto) {
        this.id_produto = id_produto;
    }

    public int get_id_usuario() {
        return id_usuario;
    }

    public void set_id_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String get_time_stamp() {
        return time_stamp;
    }

    public void set_time_stamp(String time_stamp) {
        this.time_stamp = time_stamp;
    }

    public String get_nome_produto(){
        return this.nome_produto;
    }

    public void set_nome_produto(String nome_produto){
        this.nome_produto = nome_produto;
    }

    public String get_nome_usuario(){
        return this.nome_usuario;
    }

    public void set_nome_usuario(String nome_usuario){
        this.nome_usuario = nome_usuario;
    }

}