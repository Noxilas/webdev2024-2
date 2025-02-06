package utilitarios;
//esta classe serve para ter os lances que serão recebido pelo view, ou seja, os lances devem conter:
// -nome do produto
// -valor do produto
// -id do produto

public class Lance {
    int id_produto;
    int id_usuario;
    float valor_lance;
    String time_stamp;

    // Construtor
    public Lance(int id_produto, int id_usuario, float valor_lance, String time_stamp) {
        this.id_produto = id_produto;
        this.id_usuario = id_usuario;
        this.valor_lance = valor_lance;
        this.time_stamp = time_stamp;
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

}