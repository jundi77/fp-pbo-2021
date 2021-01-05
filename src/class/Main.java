
import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) 
	{
		
		
		
		JFrame window = new JFrame("Wizard Who Speaks");
		window.setContentPane(new Menu());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.pack();
		window.setVisible(true);
		
//		javax.swing.SwingUtilities.invokeLater(new Runnable() 
//		{	
//			@Override
//			public void run() 
//			{
//				JFrame frame = new JFrame("Menu");
//				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//				frame.setContentPane(new MainMenu(1600, 900));
//				frame.setContentPane(new GameOverMenu(1600, 900));
//				frame.pack();
//				frame.setResizable(false);
//				frame.setLayout(null);
//				frame.setVisible(true);		
//			}
//		});
	}

}
