package model;
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Usuario;


public class UsuarioDAO {
    public int registrarUsuario(Usuario usr) throws ClassNotFoundException {
        String insert_user_SQL = "INSERT INTO user (name, budget) " +
            "VALUES (?, ?);";
        int result = 0;

        try{
            Class.forName("com.mysql.jdbc.Driver");

            String connection = "jdbc:mysql://localhost/projeto_web";
            String login = "root", senha = "";

            Connection connection = DriverManager.getConnection(connection, user, senha);
            PreparedStatement preparedStatement = connection.prepareStatement(insert_user_SQL);

            preparedStatement.setString(1, usr.get_name());
            preparedStatement.setString(2, usr.get_budget());

            result = preparedStatement.executeUpdate();
        } catch (SQLException e){
            System.err.println(e);
        }
        return result;
    }
    

}
