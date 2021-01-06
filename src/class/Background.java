import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Background 
{
	private BufferedImage image;
	
	private double x;
	private double y;
	
	public Background(String s, double ms)
	{
		
		try{
			
			image = ImageIO.read(getClass().getResourceAsStream(s));
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void draw(Graphics2D g){
		
		g.drawImage(image, (int)x, (int)y, null);
	}
}
