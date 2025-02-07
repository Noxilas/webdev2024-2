package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Produto;


public class ProdutoDAO {
    public int registrarProduto(Produto prd) throws ClassNotFoundException {
        String insert_product_SQL = "INSERT INTO product (name, description, reserve_price) VALUES (?, ?, ?);";
        int result = 0;

        try{
            Class.forName("com.mysql.jdbc.Driver");

            String login = "root", senha = "";

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/projeto_web", login, senha);
            PreparedStatement preparedStatement = connection.prepareStatement(insert_product_SQL);

            preparedStatement.setString(1, prd.get_nome());
            preparedStatement.setString(2, prd.get_descricao());
            preparedStatement.setDouble(3, prd.get_lance_minimo());


            result = preparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }
    
        public List<Produto> recuperarProdutos() throws ClassNotFoundException {
        String select_products_SQL = "SELECT * FROM produto";
        List<Produto> retorno = new ArrayList<>();

        try{
            Class.forName("com.mysql.jdbc.Driver");

            String login = "root", senha = "";

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/projeto_web", login, senha);
            PreparedStatement preparedStatement = connection.prepareStatement(select_products_SQL);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                Produto aux = new Produto(resultSet.getString(2), 0, resultSet.getDouble(4));

                aux.set_id(resultSet.getInt(1));
                aux.set_descricao(resultSet.getString(3));

                retorno.add(aux);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        
        return retorno;
        
    }


}
