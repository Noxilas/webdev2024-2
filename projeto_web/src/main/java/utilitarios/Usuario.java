package utilitarios;

// Bean Usuario
public class Usuario {
    String nome;
    int id;
    float budget;

    public Usuario(String nome, int id, float budget){
        this.nome = nome;
        this.id = id;
        this.budget = budget;
    }

    public String get_name(){
        return this.nome;
    }
    public void set_name(String nome){
        this.nome = nome;
    }

    public int get_id() {
        return id;
    }
    public void set_id(int id) {
        this.id = id;
    }
    
    public float get_budget() {
        return this.budget;
    }
    public void set_budget(float budget) {
        this.budget = budget;
    }

}
