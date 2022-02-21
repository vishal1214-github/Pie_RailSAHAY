package util.GenFunc;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Random;
import javax.imageio.ImageIO;

public class GG_HelperFunc 
{
	public String createCaptchaImage()
	{
		 System.out.println("IN CREATE CATCHA LOGIC-FOISWEBPORTAL");
		String strImgPath="";
		//String strImgName=this.getImageName();
		//String strText=this.getRandomString();
		//BufferedImage image = convertTextToGraphic(strText, new Font("Arial", Font.BOLD, 18));
		//write BufferedImage to file
		/* try
		 {
			ImageIO.write(image, "png", new File("C:\\"+strImgName));
			 System.out.println("IN CREATE CATCHA LOGIC-FOISWEBPORTAL");
		 }
		 catch(Exception e)
		 {
			 System.out.println("Exception in image creation");
		 }
		strImgPath="C:\\"+strImgName;*/
		 strImgPath="";
		 return strImgPath;
	}
	public BufferedImage convertTextToGraphic(String text, Font font) 
	{
        BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        
        int wid = img.getWidth();
        int ht = img.getHeight();
        g2d.setFont(font);
        g2d.setBackground(new Color(201,102,0));
        g2d.setPaint(Color.CYAN);
        g2d.fillRect(0, 0, wid, ht);

        FontMetrics fm = g2d.getFontMetrics();
        int width = fm.stringWidth(text);
        int height = fm.getHeight();
        g2d.dispose();

        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        int rgb=new Color(201,102,0).getRGB();
       
        for (int i = 0; i < wid; i++) {
            for (int j = 0; j < ht; j++) {
                img.setRGB(i, j, rgb);
            }
        }
        
        g2d = img.createGraphics();
        
        g2d.fillRect(0, 0, 100, 80);
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        g2d.setFont(font);
        fm = g2d.getFontMetrics();
        g2d.setColor(Color.BLACK);
        g2d.drawString(text, 0, fm.getAscent());
        g2d.dispose();
        return img;
    }
	public String getImageName()
	{
		String strImgName="";
		DateFormat dateFormat = new SimpleDateFormat("ddMMyyyyHHmmss");
		Date date = new Date();
		//strImgName="Cap"+dateFormat.format(date)+".png";
		strImgName="";
		return strImgName;
	}
	public String getRandomString()
	{
		String strRandomString="";
		char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqrstuvwxyz".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 6; i++) {
		    char c = chars[random.nextInt(chars.length)];
		    sb.append(c);
		}
		strRandomString = sb.toString();
		return strRandomString;
	}

}
