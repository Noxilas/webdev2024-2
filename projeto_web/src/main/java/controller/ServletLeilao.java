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

import model.Lance;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

@WebServlet("/leilao") //acho que é isso
public class ServletLeilao extends HttpServlet {

     
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //para pegar os dados do formulário:
        int idUsuario = Integer.parseInt(request.getParameter("id_usuario"));
        int idProduto = Integer.parseInt(request.getParameter("id_produto"));
        double valorLance = Double.parseDouble(request.getParameter("valor"));

        //precisamos verificar se o valor do lance é maior que o mínimo:
        double minLance = 10; //só para testar. Depois vamos substituir esse trecho pelo abaixo
        //ALTERAR: double minLance = lanceDAO.getLanceMinimo(idProduto);//recuperar o valor mínimo
        if(valorLance <= minLance) {
            //usar o sendError, mas também poderia usar o setStatus
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "O lance deve ser maior que o valor mínimo estipulado.");
            return;
        }

        //caso o valor do lance esteja OK, vamos inserir o lance no BD
        //para isso, vamos fazer um try

        /*
        try {
            //criar o objeto Lance
            Lance lance = new Lance(idUsuario, idProduto, valorLance);
            //chamar o método do DAO que insere os dados no BD encapsulando os dados
            lanceDAO.registraLance(lance);
        } catch(Exception e) {
            //caso não consiga inserir, mostrar exception
            e.printStackTrace();
        }
        */

        //vamos gerar a lista com os lances já feitos:
        // Criando a lista de lances
        List<Lance> lances = new ArrayList<>();

        // Adicionando objetos à lista
        lances.add(new Lance("carro", 15, 15));
        lances.add(new Lance("moto", 10, 5));
        lances.add(new Lance("lancha", 1, 1));
        
        //vamos enviar a lista ao JSP:
        request.setAttribute("lista_de_lances", lances);
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/view.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //primeiro vamos buscar os lances no BD
        //List<Lance> lances = lanceDAO.buscarLances(); //inventei o nome da função, podemos mudar dps
 
        //vamos fazer uma lista so pra teste
        // Criando a lista de lances
        List<Lance> lances = new ArrayList<>();

        // Adicionando objetos à lista
        lances.add(new Lance("sorvete de cupuaçu", 5, 15));
        lances.add(new Lance("sorvete de açaí", 10, 5));
        lances.add(new Lance("sorvete da ufscar", 100, 1));



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

        //retornar a resposta para o view
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }


}
