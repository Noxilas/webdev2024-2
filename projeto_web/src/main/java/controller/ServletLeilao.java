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
         
         int id_produto = Integer.parseInt(request.getParameter("id_produto"));
 
         try {
             lances = lanceDAO.recuperarLances(id_produto);
         } catch (ClassNotFoundException e) {
             e.printStackTrace();
         } 

         // Simula a recuperação dos produtos (idealmente, isso viria de um banco de dados)
        List<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto("Carro", 1, 100));
        produtos.add(new Produto("Moto", 2, 500));
        produtos.add(new Produto("Lancha", 3, 20));
        produtos.add(new Produto("Avião", 4, 90));

        // Adiciona a lista de produtos ao request
        request.setAttribute("lista_de_produtos", produtos);

        // Encaminha a requisição para a página JSP
        request.getRequestDispatcher("index.jsp").forward(request, response);
  
 
 
         //agora que recuperamos os lances, precisamos ordená-los em ordem descrescente
         //vou usar um sort para isso
         //aqui, vamos usar o método sort da interface list de java, que ordena os elementos da lista
         //vamos colocar um comparador como parâmetro para comparar as variaveis do tipo double
         //esse comparativo vai retornar 1 se lance1 > lance2; -1 se lance1 < lance2 e 0 se lance1 == lance2
         //estamos usando o lance1.getLance e lance2.getLance como lance1 e lance2 no compare
         //como é pra ser descrescente, podemos inverter lance1 e lance2, como eu fiz
         //desse modo, se lance2 > lance1, compare = 1, então lance2 deveria vir antes de lance1, mas com a inversão, o menor virá antes
         //isso fez sentido na minha cabeça!!!
         //lances.sort((lance1,lance2)-> Double.compare(lance2.getLance(), lance1.getLance()));
 
         //depois de ordenar, precisamos retornar a lista com os lances para o view
         //como estamos usando o AJAX, vou enviar o arquivo JSON
         String json = new Gson().toJson(lances);
         System.err.println(json);
         //retornar a resposta para o view
         response.setContentType("application/json");
         response.setCharacterEncoding("UTF-8");
         response.getWriter().write(json);
     }
}
