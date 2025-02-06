package model;
package utilitarios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import utilitarios.Produto;


public class ProdutoDAO {
    public int registrarProduto(Produto prd) throws ClassNotFoundException {
        String insert_product_SQL = "INSERT INTO product (name, description) " +
            "VALUES (?, ?);";
        int result = 0;

        try{
            Class.forName("com.mysql.jdbc.Driver");

            String connection = "jdbc:mysql://localhost/projeto_web";
            String login = "root", senha = "";

            Connection connection = DriverManager.getConnection(connection, user, senha);
            PreparedStatement preparedStatement = connection.prepareStatement(insert_product_SQL);

            preparedStatement.setString(1, prd.get_name());
            preparedStatement.setString(2, prd.get_descricao());

            result = preparedStatement.executeUpdate();
        } catch (SQLException e){
            System.err.println(e);
        }
        return result;
    }
    

}
