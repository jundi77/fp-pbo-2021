
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
public class MainMenu extends JPanel
{
	//harap ganti path file
	private int areaWidth;
	private int areaHeight;
	private BufferedImage[] buttonMenu = new BufferedImage[3];
	private Image[] imageButton = new Image[3];
	private Image imageMenu;
	private JButton buttonNewGame, buttonLoad, buttonQuit;
	
	public MainMenu(int width, int height)
	{
		this.areaWidth = width;
		this.areaHeight = height;
		this.setBackground(Color.BLACK);
		this.setLayout(null);
		
		try{
			BufferedImage Menu = ImageIO.read(new File("C:/Users/Rafki/workspace/fp-pbo-2021/res/ui/UserInterface/Menu.png"));	
			imageMenu = Menu.getScaledInstance(1600, 900, Image.SCALE_DEFAULT);

			BufferedImage button = ImageIO.read(new File("C:/Users/Rafki/workspace/fp-pbo-2021/res/ui/UserInterface/Tombol.png"));
			
			buttonMenu [0]  = button.getSubimage(0, 50, 100, 12);
			buttonMenu [1] = button.getSubimage(0, 62, 100, 12);
			buttonMenu [2] = button.getSubimage(0, 74, 100, 12);
			
			imageButton[0] = buttonMenu[0].getScaledInstance(300,36, Image.SCALE_DEFAULT);
			imageButton[1] = buttonMenu[1].getScaledInstance(300,36, Image.SCALE_DEFAULT);
			imageButton[2] = buttonMenu[2].getScaledInstance(300,36, Image.SCALE_DEFAULT);
			
			
		}catch(Exception e){
		}
		
		buttonNewGame = new JButton();
		buttonLoad = new JButton();
		buttonQuit = new JButton();
		
		buttonNewGame.setBorder(null);
		buttonNewGame.setBorderPainted(false);
		buttonNewGame.setContentAreaFilled(false);
		
		buttonLoad.setBorder(null);
		buttonLoad.setBorderPainted(false);
		buttonLoad.setContentAreaFilled(false);
		
		buttonQuit.setBorder(null);
		buttonQuit.setBorderPainted(false);
		buttonQuit.setContentAreaFilled(false);
		
		buttonNewGame.setIcon(new ImageIcon(imageButton[0]));
		buttonLoad.setIcon(new ImageIcon(imageButton[1]));
		buttonQuit.setIcon(new ImageIcon(imageButton[2]));
		
		buttonNewGame.setLocation(100, 1000);
		
		ButtonHandler buttonHandler = new ButtonHandler();
		
		buttonNewGame.addActionListener(buttonHandler);
		buttonLoad.addActionListener(buttonHandler);
		buttonQuit.addActionListener(buttonHandler);
		
		JPanel panelNG = new JPanel();
		panelNG.setBounds(100, 675, 300, 36);
		panelNG.setOpaque(false);
		panelNG.add(buttonNewGame);
		this.add(panelNG);
		
		JPanel panelL = new JPanel();
		panelL.setBounds(100, 725, 300, 36);
		panelL.setOpaque(false);
		panelL.add(buttonLoad);
		this.add(panelL);
		
		JPanel panelQ = new JPanel();
		panelQ.setBounds(100, 775, 300, 36);
		panelQ.setOpaque(false);
		panelQ.add(buttonQuit);
		this.add(panelQ);
		this.setPreferredSize(new Dimension(areaWidth, areaHeight));
	
	}
	
	private class ButtonHandler implements ActionListener
	{
		public void actionPerformed( ActionEvent event )
		{
			if(event.getSource() == buttonNewGame){
				newGame();
				System.out.println("NewGame");
			}
			else if(event.getSource() == buttonLoad){
				Load();
				System.out.println("Load");
			}
			else if(event.getSource() == buttonQuit){
				System.out.println("Quit");
				System.exit(0);
			}
		}
	}
	
	public void newGame()
	{
		//stateGame
	}
	
	public void Load()
	{
		//stateLoad
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(imageMenu, 0, 0, this);
	}
}
