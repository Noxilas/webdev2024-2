package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Usuario;


public class UsuarioDAO {
    public int registrarUsuario(Usuario usr) throws ClassNotFoundException {
        String insert_user_SQL = "INSERT INTO user (name, budget) VALUES (?, ?);";
        int result = 0;

        try{
            Class.forName("com.mysql.jdbc.Driver");

            String login = "root", senha = "";

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/projeto_web", login, senha);
            PreparedStatement preparedStatement = connection.prepareStatement(insert_user_SQL);

            preparedStatement.setString(1, usr.get_name());
            preparedStatement.setDouble(2, usr.get_budget());

            result = preparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }
    

}
