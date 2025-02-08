package controller;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson; //conversão JSON


import database.LanceDAO;

import java.util.ArrayList;
import java.util.List;

import model.Produto;
import database.*;

import javax.servlet.http.HttpServletRequest;

@WebServlet("/produtos") //acho que é isso
public class ServletListaProdutos extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //vai tratar de retornar a lista de produtos
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        ProdutoDAO produto_dao = new ProdutoDAO();
        List<Produto> produtos;
        try {
            produtos = produto_dao.recuperarProdutos();

   
            
            String json = new Gson().toJson(produtos);
            
            PrintWriter out = response.getWriter();
            out.print(json);
            out.flush();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    
}