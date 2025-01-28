package controller;

//Fazer os imports 
import java.io.IOException;
import javax.servlet.ServletException; //exceção espeficica para servlets
import javax.servlet.annotation.WebServlet; //para mapear a url do servlet
import javax.servlet.http.HttpServlet; //para mexer com o http
import javax.servlet.http.HttpServletRequest; //http solicitação
import javax.servlet.http.HttpServletResponse; //http resposta
import javax.servlet.RequestDispatcher; //para encaminhar ou inserir outra pagina

//imports que ainda nao tenho!!! (coloquei uns genericos, nao deve precisar de tudo)
//import model.leilaoService;
//import model.lance;
//import model.produto;
//import database.lanceDAO;

//colocar a anotação
@WebServlet("/leilao")
public class leilaoController extends HttpServlet{
    //criar uma variável DAO
    private lanceDAO LanceDAO; //temporário enqt nao tem o model

    @Override
    public void init() {
        //inicializar o DAO
        lanceDAO = new LanceDAO(); //temporário enqt nao tem o model
    }

    @Override 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //lidar com requisições POST
        //vamos começar recuperando dados do formulário
        int idUsuario = request.getParameter("idUsuario");
        int idProduto = request.getParameter("idProduto");
        double valorLance = request.getParameter("valorLance");

        //precisamos verificar se o valor do lance é maior que o mínimo
        //recuperar o valor mínimo
        double minLance = lanceDAO.getLanceMinimo(idProduto);
        if(valorLance <= minLance) {
            //usar o sendError, mas também poderia usar o setStatus
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "O lance deve ser maior que o valor mínimo estipulado.");
            return;
        }

        //caso o valor do lance esteja OK, vamos inserir o lance no BD
        //para isso, vamos fazer um try
        try {
            //criar o objeto Lance
            Lance lance = new Lance(idUsuario, idProduto, valorLance);
            //chamar o método do DAO que insere os dados no BD encapsulando os dados
            lanceDAO.registraLance(lance);
        } catch(Exception e) {
            //caso não consiga inserir, mostrar exception
            e.printStackTrace();
        }

        //vou devolver a mensagem de sucesso via setStatus para não mudar a URL com um sendRedirect
        //aqui, o setStatus envia um 200 OK diretamente
        response.setStatus(HttpServletResponse.SC_OK);
        //escrever a mensagem
        response.getWriter().write("Lance registrado com sucesso!");

    }
    
}
