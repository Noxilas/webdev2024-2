package controller;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.Cookie; // classe java que permite usar cookies
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson; //conversão JSON


import database.LanceDAO;

import java.util.ArrayList;
import java.util.List;

import model.Lance;
import model.Produto;

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
 
         //precisamos verificar se o valor do lance é maior que o mínimo:
         double minLance = 10;
         
         try {
             minLance = lanceDAO.getLanceMinimo(idProduto);
         } catch (ClassNotFoundException e) {
             e.printStackTrace();
         }   // recuperar o valor mínimo
          
 
         if(valorLance < minLance) {
             //usar o sendError, mas também poderia usar o setStatus
             response.sendError(HttpServletResponse.SC_BAD_REQUEST, "O lance deve ser maior que o valor mínimo estipulado.");
             return;
         }
 
 
         //caso o valor do lance esteja OK, vamos inserir o lance no BD
         //para isso, vamos fazer um try
 
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
         List<Lance> lances = new ArrayList<>();
         
        /* int id_produto = Integer.parseInt(request.getParameter("id_produto"));
 
         try {
             lances = lanceDAO.recuperarLances(id_produto);
         } catch (ClassNotFoundException e) {
             e.printStackTrace();
         } */

         //vamos fazer uma lista so pra teste

        // Adicionando objetos à lista
        lances.add(new Lance(1, 20, 1));
        lances.add(new Lance(2, 15, 2));
        lances.add(new Lance(3, 100, 3));
        lances.add(new Lance(4, 100, 4));

        String json = new Gson().toJson(lances);

        //retornar a resposta para o view
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
     }
}
