package servlet.AppData;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PageReDirect extends HttpServlet {
    public PageReDirect() {
        super();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                String toPage = request.getParameter("toPage");
                System.out.println("toPage:"+toPage);
                RequestDispatcher dispatcher = request.getRequestDispatcher("pages/"+toPage+".jsp");
                dispatcher.forward(request, response);
        
            }
    public void doGet(HttpServletRequest request, HttpServletResponse response)
                                                    throws ServletException, IOException
            {
                    doPost(request, response);
            }
}
