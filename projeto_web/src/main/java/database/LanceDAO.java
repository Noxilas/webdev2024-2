package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Lance;


public class LanceDAO {
    public int registrarLance(Lance lnc) throws ClassNotFoundException {
        // To na duvida quanto a timestamp, pq tem como setar pelo horario de inserção.
        // Poderiamos deixar pelo horario de inserção e hardcoded pra população dos dados padrões
        // Aqui está sem o time_stamp
        String insert_bid_SQL = "INSERT INTO bid (id_user, id_product, price) VALUES (?, ?, ?);";
        int result = 0;

        try{
            Class.forName("com.mysql.jdbc.Driver");

            String login = "root", senha = "";

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/projeto_web", login, senha);
            PreparedStatement preparedStatement = connection.prepareStatement(insert_bid_SQL);

            preparedStatement.setInt(1, lnc.get_id_usuario());
            preparedStatement.setInt(2, lnc.get_id_produto());
            preparedStatement.setDouble(3, lnc.get_valor_lance());

            result = preparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }
  
    // O lance minimo é o maior valor já definido. 
    public double getLanceMinimo(int id_product) throws ClassNotFoundException {
        double minValue = -1;
        String select_min_value_bid = "SELECT MAX(price) FROM bid WHERE id_product = (?)";

        try{
            Class.forName("com.mysql.jdbc.Driver");

            String login = "root", senha = "";

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/projeto_web", login, senha);
            PreparedStatement preparedStatement = connection.prepareStatement(select_min_value_bid);

            preparedStatement.setInt(1, id_product);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                minValue = resultSet.getDouble(1);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        
        return minValue;

    }

    public List<Lance> recuperarLances(int idProduto) throws ClassNotFoundException {
        String select_bid_product_user_SQL = "SELECT user.name AS username, user.id AS id_user, product.name AS productname, product.id AS id_product, bid.price, bid.time_stamp FROM (bid INNER JOIN user ON bid.id_user=user.id) INNER JOIN product ON bid.id_product=product.id WHERE id_product= (?)";
        List<Lance> retorno = new ArrayList<>();

        try{
            Class.forName("com.mysql.jdbc.Driver");

            String login = "root", senha = "";

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/projeto_web", login, senha);
            PreparedStatement preparedStatement = connection.prepareStatement(select_bid_product_user_SQL);

            preparedStatement.setInt(1, idProduto);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                Lance aux = new Lance(resultSet.getInt(4), resultSet.getInt(2), resultSet.getDouble(5));

                // para display
                aux.set_nome_produto(resultSet.getString(3));
                aux.set_nome_usuario(resultSet.getString(1));
                aux.set_time_stamp(resultSet.getString(6));

                retorno.add(aux);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        
        return retorno;
        
    }

}
