package controller;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson; //conversão JSON
import database.LanceDAO;
import java.util.ArrayList;
import java.util.List;
import model.Lance;
import javax.servlet.http.HttpServletRequest;
import database.*;

@WebServlet("/erro") //acho que é isso
public class ServletErro extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         //primeiro vamos buscar os lances no BD
         ProdutoDAO produtoDAO = new ProdutoDAO();
         
        int id_produto = Integer.parseInt(request.getParameter("id_produto"));
        double valor_minimo;
        try {
            valor_minimo = produtoDAO.recuperarValorMinimo(id_produto);
            String json = new Gson().toJson(valor_minimo);
            //retornar a resposta para o view
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
