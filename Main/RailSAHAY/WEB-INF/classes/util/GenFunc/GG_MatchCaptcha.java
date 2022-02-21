package util.GenFunc;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nl.captcha.Captcha;


public class GG_MatchCaptcha extends HttpServlet {
    private static final String CONTENT_TYPE = "text/plain; charset=UTF-8";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    /**Process the HTTP doGet request.
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    /**Process the HTTP doPost request.
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(CONTENT_TYPE);
        PrintWriter out = response.getWriter();

            HttpSession session = request.getSession(false);
            String result = (String)request.getParameter("result");
          //  System.out.println("GET CAPTCHA NAME::"+Captcha.NAME);
            Captcha captcha = (Captcha)session.getAttribute("simpleCaptcha");
            request.setCharacterEncoding("UTF-8");
         
            if (captcha.isCorrect(result)) {
                out.write("Y");
                out.close();
            } else {
                out.write("N");
                out.close();
            }



    }
}
