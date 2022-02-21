package util.GenFunc;

import java.awt.Color;
import java.awt.Font;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.captcha.Captcha;
import nl.captcha.backgrounds.GradiatedBackgroundProducer;
import nl.captcha.servlet.CaptchaServletUtil;
import nl.captcha.text.renderer.DefaultWordRenderer;


public class GG_RailSahayCaptcha extends HttpServlet {
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Color> colors = new ArrayList<Color> ();
        colors.add(Color.black);
        colors.add(Color.red);
         
        List<Font> fonts = new ArrayList<Font>();
        fonts.add(new Font("SansSerif", Font.BOLD, 40));
         
        Captcha captcha = new Captcha.Builder(200, 50)
                .addText(new DefaultWordRenderer(colors, fonts))
                .addBackground(new GradiatedBackgroundProducer(Color.white, Color.white))
                //.gimp()
                .addBorder()
                .build();
 
        // display the image produced
        CaptchaServletUtil.writeImage(response, captcha.getImage());
 
        // save the captcha value on session
        request.getSession().setAttribute("simpleCaptcha", captcha);
    }
}