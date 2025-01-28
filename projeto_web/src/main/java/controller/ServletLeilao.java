package controller;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.Cookie; // classe java que permite usar cookies
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

@WebServlet("/leilao") //acho que é isso
public class ServletLeilao extends HttpServlet {

     
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //para pegar os dados da requisição:

        String produto = request.getParameter("produto");
        String valor = request.getParameter("valor");

        //vamos enviar os dados ao JSP:
        request.setAttribute("produto", produto);
        request.setAttribute("valor", valor);
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/view.jsp");
        dispatcher.forward(request, response);
    }



}
