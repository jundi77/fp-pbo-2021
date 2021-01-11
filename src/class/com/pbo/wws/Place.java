package com.pbo.wws;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.pbo.wws.frame.Main;

public class Place implements Renderable, Movable{
	private static final String placeRootDir = Main.resourcePath + "/tile/map";
	private static final String tmxFileName = "mapTile.tmx";

	private static final int WALL_UP = 0;
	private static final int WALL_DOWN = 1;
	private static final int WALL_LEFT = 2;
	private static final int WALL_RIGHT = 3;
	private static final int CHECKPOINT = 4;

	private static DocumentBuilderFactory documentBuilderFactory = null;
	private static DocumentBuilder documentBuilder;

	private java.net.URL tmxPath = null, tsxPath = null, imgPath = null;

	private BufferedImage img;
	private String name;
	private int tileWidth, tileHeight, srcTileColumns, srcTileRows, mapWidth, mapHeight, width, height;
	private ArrayList<int[][]> layers;
	private int[] noMovableArea;
//	private HashMap<int[], > placesAround
//	private HashMap<Enemy, int[]> enemies;
	
	public Place(String name) throws ParserConfigurationException, SAXException, IOException {
		if (documentBuilderFactory == null) {
			documentBuilderFactory= DocumentBuilderFactory.newDefaultInstance();
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
		}

		this.name = name;
		this.tmxPath = getClass().getResource(placeRootDir + "/" + name + "/" + tmxFileName);

		this.layers = new ArrayList<int[][]>();
		this.fillAttribute();
	}

	public void fillAttribute() throws IOException, SAXException {
		Document tmxDoc = documentBuilder.parse(this.tmxPath.getFile());
		
		Element map = tmxDoc.getDocumentElement();
		Node tileset = map.getElementsByTagName("tileset").item(0);
		NodeList layers = map.getElementsByTagName("layer");

		this.mapWidth  = Integer.parseInt(map.getAttributes().getNamedItem("width").getNodeValue());
		this.mapHeight = Integer.parseInt(map.getAttributes().getNamedItem("height").getNodeValue());
		this.tileWidth  = Integer.parseInt(map.getAttributes().getNamedItem("tilewidth").getNodeValue());
		this.tileHeight = Integer.parseInt(map.getAttributes().getNamedItem("tileheight").getNodeValue());

		// untuk sekarang, cukup menyimpan satu layer saja
		for (int i = 0; i < layers.getLength(); ++i) {
			Element layer = (Element) layers.item(i);
			Node data = layer.getElementsByTagName("data").item(0);

			String[] dataHeightContent = data.getTextContent().split("\n");
			int[][] layerData = new int[mapHeight][mapWidth];

			for (int j = 1; j < mapHeight; ++j)  {
				String[] dataRowContent = dataHeightContent[j].split(",");
				for (int k = 0; k < mapWidth - 1; ++k)  {
					layerData[j - 1][k] = Integer.parseInt(dataRowContent[k]);
				}
			}

			this.layers.add(layerData);
		}
		

		String tmxSrc = tileset.getAttributes().getNamedItem("source").getNodeValue();
		this.tsxPath = getClass().getResource(placeRootDir + "/" + name + "/" + tmxSrc);
		
		Document tsxDoc = documentBuilder.parse(tsxPath.getFile());

		Element tsxTileset = tsxDoc.getDocumentElement();
		Node image = tsxTileset.getElementsByTagName("image").item(0);

		NamedNodeMap tsxTilesetAttr = tsxTileset.getAttributes();
		NamedNodeMap imageAttr = image.getAttributes();

		this.srcTileColumns = Integer.parseInt(tsxTilesetAttr.getNamedItem("columns").getNodeValue());
		this.srcTileRows = Integer.parseInt(tsxTilesetAttr.getNamedItem("tilecount").getNodeValue()) / this.srcTileColumns;

		String tsxImgSrc = imageAttr.getNamedItem("source").getNodeValue();
		this.imgPath = getClass().getResource( placeRootDir + "/" + name + "/" + tsxImgSrc);
		this.img = ImageIO.read(this.imgPath);
	}
	
	@Override
	public void render(Graphics g) {
		int[][] firstLayer = layers.get(0);
		for (int i = 0; i < firstLayer.length; ++i) {
			for (int j = 0; j < firstLayer[i].length; ++j) {
				if (firstLayer[i][j] == 0) continue;
				int curr = firstLayer[i][j] - 1;
				int dx1 = tileWidth * j;
				int dy1 = tileHeight * i + 100;
				int dx2 = dx1 + tileWidth;
				int dy2 = dy1 + tileHeight;

				int currx1 = ((firstLayer[i][j] - 1) % this.srcTileColumns);
				int curry1 = ((firstLayer[i][j] - 1) / this.srcTileColumns);
				int sx1 = tileWidth * ((firstLayer[i][j] - 1) % this.srcTileColumns);
				int sy1 = tileHeight * ((firstLayer[i][j] - 1) / this.srcTileColumns); // salah
//				int sx1 = tileWidth * ((16) % this.srcTileColumns);
//				int sy1 = tileHeight * ((16) / this.srcTileColumns); // salah
//				int sx1 = 32;
//				int sy1 = 0;
				int sx2 = sx1 + tileWidth;
				int sy2 = sy1 + tileHeight;
				g.drawImage(this.img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, null, null);
			}
		}
	}

	@Override
	public void setMovingStatus(boolean move) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getMovingStatus() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setVisible(boolean visible) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getVisibility() {
		// TODO Auto-generated method stub
		return false;
	}
}
