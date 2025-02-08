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

@WebServlet("/produtos") //acho que é isso
public class ServletListaProdutos extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //vai tratar de retornar a lista de produtos
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");


        //por enquanto vou criar a lista, mas depois vou usar o DAO:
        List<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto( "Notebook",15, 3500.00));
        produtos.add(new Produto( "Mouse",5, 50.00));
        produtos.add(new Produto( "Teclado",5, 120.00));
        
        String json = new Gson().toJson(produtos);
        
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();

    }
    
}