package br.ufjf.dcc192;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ServletFruta", urlPatterns = {"/index.html"})
public class ServletFruta extends HttpServlet {

    private ArrayList<String> frutas;
    
    private List<String> metodo ()
    {       
        List<String> frutas = new ArrayList<String>(){
        {
            add("Abacaxi");
            add("Limão");
            add("Laranja");
            add("Banan");
            add("Goiaba");
            add("Manga");
         }
        };
        return frutas;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        String comando = req.getParameter("comando");
        try (PrintWriter out = resp.getWriter()) {
            if("ordNome".equals(comando))
            {
                frutas = (ArrayList<String>) metodo();
                Collections.sort(frutas);
            }
            if("ordTam".equals(comando))
            {
                frutas = (ArrayList<String>) metodo();
                TamanhoComparator tamanho = new TamanhoComparator();
                Collections.sort(frutas, tamanho);
            }
            if((!"ordTam".equals(comando) && !"ordNome".equals(comando) || "ordAle".equals(comando)) )
            {
                frutas = (ArrayList<String>) metodo();
                Collections.shuffle(frutas);
            }
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Frutas</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1> Frutas </h1>");
            out.println("<ul>");
            for (String fruta : frutas) {
                out.println ("<li>  " + fruta + " </li>");
            }
            out.println("</ul>");
            out.println("<p> <a href='?comando=ordNome'> Ordenar Nome </a> </p>");
            out.println("<p> <a href='?comando=ordTam'> Ordenar Tamanho </a> </p>");
            out.println("<p> <a href='?comando=ordAle'> Ordenar Padrão </a> </p>");
            out.println("</body>");
            out.println("</html>");
        }

    }
        
}
