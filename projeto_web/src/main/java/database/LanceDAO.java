package model;
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Lance;


public class LanceDAO {
    public int registrarLance(Lance lnc) throws ClassNotFoundException {
        // To na duvida quanto a timestamp, pq tem como setar pelo horario de inserção.
        // Poderiamos deixar pelo horario de inserção e hardcoded pra população dos dados padrões
        // Aqui está sem o time_stamp
        String insert_bid_SQL = "INSERT INTO bid (id_user, id_product, price) " +
            "VALUES (?, ?, ?);";
        int result = 0;

        try{
            Class.forName("com.mysql.jdbc.Driver");

            String connection = "jdbc:mysql://localhost/projeto_web";
            String login = "root", senha = "";

            Connection connection = DriverManager.getConnection(connection, user, senha);
            PreparedStatement preparedStatement = connection.prepareStatement(insert_bid_SQL);

            preparedStatement.setString(1, lnc.get_id_usuario());
            preparedStatement.setString(2, lnc.get_id_produto());
            preparedStatement.setString(3, lnc.get_price());

            result = preparedStatement.executeUpdate();
        } catch (SQLException e){
            System.err.println(e);
        }
        return result;
    }
    

}
