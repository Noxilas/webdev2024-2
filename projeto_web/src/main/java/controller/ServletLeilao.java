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

@WebServlet("/leilao") //acho que é isso
public class ServletLeilao extends HttpServlet {

     
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         //para pegar os dados do formulário:
         int idUsuario = Integer.parseInt(request.getParameter("id_usuario"));
         int idProduto = Integer.parseInt(request.getParameter("id_produto"));
         double valorLance = Double.parseDouble(request.getParameter("valor"));
         LanceDAO lanceDAO = new LanceDAO();
 
         try {
             //criar o objeto Lance
             Lance lance = new Lance(idProduto, idUsuario, valorLance);
             //chamar o método do DAO que insere os dados no BD encapsulando os dados
             lanceDAO.registrarLance(lance);
         } catch(Exception e) {
             //caso não consiga inserir, mostrar exception
             e.printStackTrace();
         }
 
         //vamos gerar a lista com os lances já feitos:
         // Criando a lista de lances
         List<Lance> lances = new ArrayList<>();
 
         try {
             lances = lanceDAO.recuperarLances(idProduto);
         } catch (ClassNotFoundException e) {
             e.printStackTrace();
         }
 
         //vamos enviar a lista ao JSP:
         request.setAttribute("lista_de_lances", lances);
         RequestDispatcher dispatcher = request.getRequestDispatcher("view/view.jsp");
         dispatcher.forward(request, response);
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         //primeiro vamos buscar os lances no BD
         LanceDAO lanceDAO = new LanceDAO();
         
        int id_produto = Integer.parseInt(request.getParameter("id_produto"));
        //int id_produto = 2; //por enquanto vamos deixar fixo
        List<Lance> lances = new ArrayList<>();
 
         try {
            lances = lanceDAO.recuperarLances(id_produto);
         } catch (ClassNotFoundException e) {
            e.printStackTrace();
         } 

         //vamos fazer uma lista so pra teste


        String json = new Gson().toJson(lances);

        //retornar a resposta para o view
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
     }
}
