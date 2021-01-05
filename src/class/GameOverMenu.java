

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GameOverMenu extends JPanel
{
	
private int areaWidth;
private int areaHeight;
private BufferedImage[] buttonMenu = new BufferedImage[3];
private Image[] imageButton = new Image[3];
private Image imageGameOver;
private JButton buttonRestart, buttonExit;

	public GameOverMenu(int width, int height)
	{
		//harap ganti path file
		this.areaWidth = width;
		this.areaHeight = height;
		this.setBackground(Color.BLACK);
		this.setLayout(null);
		try{
			BufferedImage GameOver = ImageIO.read(new File("C:/Users/Rafki/workspace/fp-pbo-2021/res/ui/UserInterface/Kalah.png"));	
			imageGameOver = GameOver.getScaledInstance(1600, 900, Image.SCALE_DEFAULT);
	
			BufferedImage button = ImageIO.read(new File("C:/Users/Rafki/workspace/fp-pbo-2021/res/ui/UserInterface/Tombol.png"));
			
			buttonMenu[0]  = button.getSubimage(0, 84, 100, 12);
			buttonMenu [1] = button.getSubimage(0, 96, 100, 12);
			buttonMenu [2] = button.getSubimage(0, 108, 100, 12);
			
			imageButton[0] = buttonMenu[0].getScaledInstance(300,36, Image.SCALE_DEFAULT);
			imageButton[1] = buttonMenu[1].getScaledInstance(300,36, Image.SCALE_DEFAULT);
			imageButton[2] = buttonMenu[2].getScaledInstance(300,36, Image.SCALE_DEFAULT);
			
			
		}catch(Exception e){
		}
		
		buttonRestart = new JButton();
		buttonExit = new JButton();
		
		buttonRestart.setBorder(null);
		buttonRestart.setBorderPainted(false);
		buttonRestart.setContentAreaFilled(false);
		
		buttonExit.setBorder(null);
		buttonExit.setBorderPainted(false);
		buttonExit.setContentAreaFilled(false);
		
		buttonRestart.setIcon(new ImageIcon(imageButton[1]));
		buttonExit.setIcon(new ImageIcon(imageButton[2]));
		
		ButtonHandler buttonHandler = new ButtonHandler();
		
		buttonRestart.addActionListener(buttonHandler);
		buttonExit.addActionListener(buttonHandler);
		
		JPanel panelC = new JPanel();
		panelC.setBounds(areaWidth/2-80, 300, 300, 36);
		panelC.setOpaque(false);
		panelC.add(buttonRestart);
		this.add(panelC);
	
		JPanel panelE = new JPanel();
		panelE.setBounds(areaWidth/2-40, 350, 300, 36);
		panelE.setOpaque(false);
		panelE.add(buttonExit);
		this.add(panelE);
		this.setPreferredSize(new Dimension(areaWidth, areaHeight));
	
	}
	
	private class ButtonHandler implements ActionListener
	{
		public void actionPerformed( ActionEvent event )
		{
			if(event.getSource() == buttonRestart){
				continuePlay();
				System.out.println("Restart");
			}else if(event.getSource() == buttonExit){
				goToMainMenu();
				System.out.println("Exit");
			}
		}
	}
		
	public void continuePlay() 
	{
		// stateGame	
	}
	
	public void goToMainMenu() 
	{
		// stateMenu
	}
		
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(imageGameOver, 0, 0, this);
		g.drawImage(imageButton[0],areaWidth/2- 125,100,this);
	}
}
