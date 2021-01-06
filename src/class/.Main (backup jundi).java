import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class Main extends JFrame{
	private static final int REFRESH_RATE = 30;
	private static float ZOOM = 1;
	private static int windowWidth, windowHeight;
	private Character player;
	private Renderer renderer;
	
	public static void mainBackup(String[] args) throws ParserConfigurationException, SAXException, IOException {
		// TODO Auto-generated method stub
		System.out.println("[Main]: Game is launched at " + new Date());
		Timer timer = new Timer(100, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Main window = new Main(1280, 720, 1);
				System.out.println("[Main]: Game is now visible ");
			}
		});;
	}

	public Main(int width, int height, double zoom) {
		Main.ZOOM = (float) (zoom);
		Main.windowHeight = (int) (height);
		Main.windowWidth = (int) (width);

		this.setTitle("Witch Who Speaks");
		this.setSize(this.getWindowWidth(), this.getWindowHeight());
		this.setLayout(new GridLayout(1, 1, 0, 0));
		this.setResizable(false);
		this.setLocationRelativeTo(null);
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		player = new Character(this);
//		this.renderer = new Renderer();
//		this.renderer.addDrawable(player);
//		add(renderer);
//		KeyMapper keyMap = new KeyMapper();
//		addKeyListener(keyMap);
//		try {
//			Place p = new Place("map 2", "mapTile.tmx");
//			this.add(p);
//		} catch (ParserConfigurationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SAXException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		setVisible(true);
		setFocusable(true);
	}
	
	public int getWindowWidth() {
		return (int) (windowWidth * Main.ZOOM);
	}

	public void setWindowWidth(int windowWidth) {
		this.windowWidth = windowWidth;
	}

	public int getWindowHeight() {
		return (int) (windowHeight * Main.ZOOM);
	}

	public void setWindowHeight(int windowHeight) {
		Main.windowHeight = windowHeight;
	}

	public static float getZoom() {
		return ZOOM;
	}

	public static void setZoom(double zoom) {
		Main.ZOOM = (float) zoom;
	}

	public void startThread() {
		Thread gameThread = new Thread() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				super.run();
			}
		};
	}
}
