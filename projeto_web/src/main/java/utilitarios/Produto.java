package utilitarios;
import java.util.List;

public class Produto {
    String nome;
    int id;
    double lance_minimo = 10;

    public Produto(String nome, int id){
        this.nome = nome;
        this.id = id;
    }

    // Getter para lance_minimo
    public double get_lance_minimo() {
        return lance_minimo;
    }

    // Setter para nome
    public void set_lance_minimo(double lance_minimo) {
        this.lance_minimo = lance_minimo;
    }


    // Getter para nome
    public String get_nome() {
        return nome;
    }

    // Setter para nome
    public void set_nome(String nome) {
        this.nome = nome;
    }

    // Getter para id
    public int get_id() {
        return id;
    }

    // Setter para id
    public void set_id(int id) {
        this.id = id;
    }
}
