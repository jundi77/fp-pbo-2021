

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
public class PauseMenu extends JPanel{
	private int areaWidth;
	private int areaHeight;
	private BufferedImage[] buttonMenu = new BufferedImage[4];
	private Image[] imageButton = new Image[4];
	private Image imagePause;
	private JButton buttonContinue, buttonSave, buttonExit;
	
	public PauseMenu(int width, int height)
	{
		//harap ganti path file
		this.areaWidth = width;
		this.areaHeight = height;
		this.setBackground(Color.BLACK);
		this.setLayout(null);
		try{
			BufferedImage Pause = ImageIO.read(new File("C:/Users/Rafki/workspace/fp-pbo-2021/res/ui/UserInterface/Pause.png"));	
			imagePause = Pause.getScaledInstance(1600, 900, Image.SCALE_DEFAULT);

			BufferedImage button = ImageIO.read(new File("C:/Users/Rafki/workspace/fp-pbo-2021/res/ui/UserInterface/Tombol.png"));
			
			buttonMenu[0]  = button.getSubimage(0, 0, 100, 12);
			buttonMenu [1] = button.getSubimage(0, 12, 100, 12);
			buttonMenu [2] = button.getSubimage(0, 24, 100, 12);
			buttonMenu [3] = button.getSubimage(0, 120, 100, 12);
			
			imageButton[0] = buttonMenu[0].getScaledInstance(300,36, Image.SCALE_DEFAULT);
			imageButton[1] = buttonMenu[1].getScaledInstance(300,36, Image.SCALE_DEFAULT);
			imageButton[2] = buttonMenu[2].getScaledInstance(300,36, Image.SCALE_DEFAULT);
			imageButton[3] = buttonMenu[3].getScaledInstance(300,36, Image.SCALE_DEFAULT);
			
			
		}catch(Exception e){
		}
		
		buttonContinue = new JButton();
		buttonSave = new JButton();
		buttonExit = new JButton();
		
		buttonContinue.setBorder(null);
		buttonContinue.setBorderPainted(false);
		buttonContinue.setContentAreaFilled(false);
		
		buttonSave.setBorder(null);
		buttonSave.setBorderPainted(false);
		buttonSave.setContentAreaFilled(false);
		
		buttonExit.setBorder(null);
		buttonExit.setBorderPainted(false);
		buttonExit.setContentAreaFilled(false);
		
		buttonContinue.setIcon(new ImageIcon(imageButton[1]));
		buttonSave.setIcon(new ImageIcon(imageButton[3]));
		buttonExit.setIcon(new ImageIcon(imageButton[2]));
		
		ButtonHandler buttonHandler = new ButtonHandler();
		
		buttonContinue.addActionListener(buttonHandler);
		buttonSave.addActionListener(buttonHandler);
		buttonExit.addActionListener(buttonHandler);
		
		JPanel panelC = new JPanel();
		panelC.setBounds(100, 675, 300, 36);
		panelC.setOpaque(false);
		panelC.add(buttonContinue);
		this.add(panelC);
		
		JPanel panelS = new JPanel();
		panelS.setBounds(100, 725, 300, 36);
		panelS.setOpaque(false);
		panelS.add(buttonSave);
		this.add(panelS);
		
		JPanel panelE = new JPanel();
		panelE.setBounds(100, 775, 300, 36);
		panelE.setOpaque(false);
		panelE.add(buttonExit);
		this.add(panelE);
		this.setPreferredSize(new Dimension(areaWidth, areaHeight));
	
	}
	private class ButtonHandler implements ActionListener
	{
		public void actionPerformed( ActionEvent event )
		{
			if(event.getSource() == buttonContinue){
				continuePlay();
				System.out.println("Continue");
			}else if(event.getSource() == buttonSave){
				save();
				System.out.println("Save");
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
	
	public void save() 
	{
		// stateSave
	}
	
	public void goToMainMenu() 
	{
		// stateMenu
	}
	
	
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(imagePause, 0, 0, this);
		g.drawImage(imageButton[0],100,100,this);
	}
}
