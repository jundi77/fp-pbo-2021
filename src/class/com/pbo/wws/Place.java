package com.pbo.wws;

import java.awt.Color;
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

import com.pbo.wws.entity.Character;
import com.pbo.wws.entity.FightingCharacter;
import com.pbo.wws.entity.Movable;
import com.pbo.wws.frame.Main;
import com.pbo.wws.io.KeyMapper;
import com.pbo.wws.io.Renderable;

public class Place implements Renderable, Movable{
	private static final String placeRootDir = Main.resourcePath + "/tile/map"; // Direktori utama map tile
	private static final String tmxFileName = "mapTile.tmx"; // Nama file tmx yang akan dibaca

	public static final int WALL_UP = 0;
	public static final int WALL_DOWN = 1;
	public static final int WALL_LEFT = 2;
	public static final int WALL_RIGHT = 3;
	public static final int IS_WALL = 4;
	public static final int IS_CHECKPOINT = 5;

	private static DocumentBuilderFactory documentBuilderFactory = null;
	private static DocumentBuilder documentBuilder;

	private java.net.URL tmxPath = null, // URL file tmx
						 tsxPath = null, // URL file tsx
						 imgPath = null; // URL file image sumber tile

	private String name; 		// nama dari Place
	private BufferedImage img; 	// Image sumber tile
	private int tileWidth, 		// Ukuran width satu tile di image sumber
				tileHeight, 	// Ukuran height satu tile di image sumber
				srcTileColumns, // Banyak kolom tile di image sumber
				srcTileRows, 	// Banyak baris tile di image sumber
				mapWidth, 		// Banyak width tile di peta
				mapHeight, 		// Banyak height tile di peta
				width, height, 	// Ukuran width dan height satu tile di layar saat rendered
				x=0, y=0,
				playerTileStart = 59,
				xStart=0, yStart=0; // tempat player summon untuk map utama
	
	private Character player = null;

	/**
	 * Menyimpan layer yang ada, namun untuk sekarang satu layer saja yang akan digunakan
	 */
	private ArrayList<int[][]> layers;
	
	/**
	 * Menyimpan informasi tile, seperti apakah karakter tidak bisa bergerak
	 * ke atas, tidak bisa ke samping, atau tile adalah tembok.
	 */
	private HashMap<Integer, Integer[]> tileDetails;
	
	/**
	 * Menyimpan informasi enemy dan lokasinya
	 */
	private HashMap<Integer, FightingCharacter> enemies;

	/**
	 * 
	 * @param name
	 * @param tileDetails, urutan tile di src image dan detail wall checkpoint
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public Place(String name, HashMap<Integer, Integer[]> tileDetails, int tileDisplayedSize, int xUpperLeft, int yUpperLeft, Character player) throws ParserConfigurationException, SAXException, IOException {
		if (documentBuilderFactory == null) {
			documentBuilderFactory= DocumentBuilderFactory.newDefaultInstance();
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
		}

		this.player = player;
		this.tileDetails = tileDetails;
		this.name = name;
		this.tmxPath = getClass().getResource(placeRootDir + "/" + name + "/" + tmxFileName);
		this.width = this.height = tileDisplayedSize;
		this.x = xUpperLeft;
		this.y = yUpperLeft;

		this.layers = new ArrayList<int[][]>();
		this.enemies = new HashMap<Integer, FightingCharacter>();
		this.fillTilesDetails();
		
		int halfScreen = (Main.getHeight() / 2 / this.height + 1) * this.mapWidth;
		int leakScreenX = this.width - (Main.getWidth() / 2) % this.width;
		int leakScreenY = this.height - (Main.getHeight() / 2) % this.height;
		int centerTileX = this.width / 2;
		int centerTileY = this.height / 2;

		this.xStart -= this.width * (((this.playerTileStart - 1) % this.mapWidth) - Main.getWidth() / 2 / this.width) + leakScreenX - centerTileX;
		this.yStart -= this.height * ((this.playerTileStart - halfScreen - 1) / this.mapWidth) + leakScreenY - centerTileY - this.height / 2;
		
		this.x = this.xStart;
		this.y = this.yStart - this.height / 3;
	}

	public void fillTilesDetails() throws IOException, SAXException {
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

			for (int j = 1; j <= mapHeight; ++j)  { // <= karena indeksnya 1 - 15, bukan 0 - 14
				String[] dataRowContent = dataHeightContent[j].split(",");
				for (int k = 0; k < mapWidth; ++k)  {
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
		if (KeyMapper.isPressed(KeyMapper.KEY_W)) {
			y += 3;
		} else if (KeyMapper.isPressed(KeyMapper.KEY_S)) {
			y -= 3;
		} else if (KeyMapper.isPressed(KeyMapper.KEY_A)) {
			x += 3;
		} else if (KeyMapper.isPressed(KeyMapper.KEY_D)) {
			x -= 3;
		}
		
		
//		System.out.println("[Place] " + this.x + "," + this.y);
		int[][] firstLayer = layers.get(0);
		for (int i = 0; i < firstLayer.length; ++i) {
			for (int j = 0; j < firstLayer[i].length; ++j) {
				if (firstLayer[i][j] == 0) continue; // transparent

				// posisi di layar
				int dx1 = this.x + this.width * j;
				int dy1 = this.y + this.height * i;
				int dx2 = dx1 + this.width;
				int dy2 = dy1 + this.height;

				// posisi di image source
				int sx1 = tileWidth * ((firstLayer[i][j] - 1) % this.srcTileColumns);
				int sy1 = tileHeight * ((firstLayer[i][j] - 1) / this.srcTileColumns);
				int sx2 = sx1 + tileWidth;
				int sy2 = sy1 + tileHeight;

				g.drawImage(this.img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, null, null);
				g.setColor(Color.blue);
				g.drawRect(dx1, dy1, this.width, this.height);
				g.drawLine(this.x + this.width * 18, this.y + this.height * 2, this.x + this.width * 18, this.y + this.height * 3);


				FightingCharacter e = this.enemies.get((i - 1) * this.mapWidth + j);
				if (e != null) {
					e.setX(dx1 - this.width / 2);
					e.setY(dy1 - this.height / 2);
					e.render(g);
				}
			}
		}

//		this.detectEnemy();
		

		this.collideHandling();

		int diffX = this.xStart - this.x;
		int diffY = this.yStart - this.y;
		int diffCol = (diffX + ((diffX > 0)? this.width/2 : -this.width/2)) / this.width;
		int diffRow = (diffY + ((diffY > 0)? this.height : (this.y >= this.yStart)? 0 : -this.height)) / this.height;
		int currentTile = this.playerTileStart + diffCol + (diffRow - 1) * this.mapWidth;

		int ulCurrentCol = currentTile % this.mapWidth;
		int ulCurrentRow = currentTile / this.mapWidth;

		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				g.setColor(new Color(255, 255, 255, 80));
				g.fillRect(this.x + (ulCurrentCol - 1 + j) * this.width, this.y + (ulCurrentRow + i) * this.height, this.width, this.height);
				FightingCharacter e = this.enemies.get(ulCurrentCol + j + (ulCurrentRow + i) * this.mapWidth);
				if (e != null) {
					System.out.println("[Place] enemy detected");
				}
			}
		}

		int ulDiffCol = (ulCurrentCol - 1 < 0)? this.mapWidth - 1 : ulCurrentCol - 1;
		int ulDiffRow = (ulCurrentCol % this.mapWidth == 0)? ulCurrentRow - 1 : ulCurrentRow;

		int ulDiffX = this.x + ulDiffCol * this.width;
		int ulDiffY = this.y + ulDiffRow * this.height;
		int drDiffX = this.x + (ulDiffCol + 1) * this.width;
		int drDiffY = this.y + (ulDiffRow + 1) * this.height;

		int distanceToLeft = Main.getWidth() / 2 - ulDiffX - player.getWidth() / 2;
		int distanceToRight = drDiffX - Main.getWidth() / 2 - player.getWidth() / 2;
		int distanceToUp = Main.getHeight() / 2 - ulDiffY;
		int distanceToDown = drDiffY - Main.getHeight() / 2 - player.getHeight() / 2;

		System.out.println(this.getCurrentTile());
	}

	protected int getCurrentTile() {
		int diffX = this.xStart - this.x;
		int diffY = this.yStart - this.y;
		int diffCol = (diffX + ((diffX > 0)? this.width/2 : -this.width/2)) / this.width;
		int diffRow = (diffY + ((diffY > 0)? this.height : (this.y >= this.yStart)? 0 : -this.height)) / this.height;

		return this.playerTileStart + diffCol + (diffRow - 1) * this.mapWidth;
	}

	protected int getTileFromRowCol(int row, int col) {
		return row * this.mapWidth - (this.mapWidth - col);
	}

	protected int getRowFromTile(int tile) {
		return (tile % this.mapWidth == 0)? tile / this.mapWidth : tile / this.mapWidth + 1;
	}

	protected int getColFromTile(int tile) {
		return tile - (this.getRowFromTile(tile) - 1) * this.mapWidth;
	}

	protected void collideHandling() {
		int currentTile = this.getCurrentTile();

		int ulCurrentCol = currentTile % this.mapWidth;
		int ulCurrentRow = currentTile / this.mapWidth;

		int ulDiffCol = (ulCurrentCol - 1 < 0)? this.mapWidth - 1 : ulCurrentCol - 1;
		int ulDiffRow = (ulCurrentCol % this.mapWidth == 0)? ulCurrentRow - 1 : ulCurrentRow;
		
//		ul itu maksudnya upper left, dr itu down right
		int ulDiffX = this.x + ulDiffCol * this.width;
		int ulDiffY = this.y + ulDiffRow * this.height;
		int drDiffX = this.x + (ulDiffCol + 1) * this.width;
		int drDiffY = this.y + (ulDiffRow + 1) * this.height;

		
		int distanceToLeft = Main.getWidth() / 2 - ulDiffX - player.getWidth() / 2;
		int distanceToRight = drDiffX - Main.getWidth() / 2 - player.getWidth() / 2;
		int distanceToUp = Main.getHeight() / 2 - ulDiffY;
		int distanceToDown = drDiffY - Main.getHeight() / 2 - player.getHeight() / 2;
		
		System.out.println("[Place] dToL=" + distanceToLeft);

		// cek left/right
		// left
		boolean horCollideFound = false, vertCollideFound = false;

		int leftTileToBeChecked;
		if (distanceToLeft < 0) {
			leftTileToBeChecked = currentTile - 1;
		} else {
			leftTileToBeChecked = currentTile;
		}

		if (leftTileToBeChecked >= 0 && leftTileToBeChecked < this.mapWidth * this.mapHeight ) {
			Integer leftTile = this.layers.get(0)[this.getRowFromTile(leftTileToBeChecked) - 1]
												 [this.getColFromTile(leftTileToBeChecked) - 1];
			Integer[] leftTileDetails = this.tileDetails.get(leftTile - 1);
			if (leftTileDetails != null)
			for (Integer t : leftTileDetails) {
				if (t == Place.IS_WALL || t == Place.WALL_LEFT) {
					if ((distanceToLeft < 0 && currentTile == leftTileToBeChecked)? distanceToLeft >= -5 : distanceToLeft <= 5 && distanceToLeft >= 0) {
						System.out.println("[Place] Collide left");
						this.x -= 3;
						horCollideFound = true;
					}
				}
			}
			if (!horCollideFound && distanceToDown < 0) {
				int leftTileBottom = leftTileToBeChecked + this.mapWidth;
				if (leftTileBottom < this.mapWidth * this.mapHeight) {
					leftTile = this.layers.get(0)[this.getRowFromTile(leftTileBottom) - 1]
							[this.getColFromTile(leftTileBottom) - 1];
					leftTileDetails = this.tileDetails.get(leftTile - 1);
					if (leftTileDetails != null)
					for (Integer t : leftTileDetails) {
						if (t == Place.IS_WALL || t == Place.WALL_LEFT) {
							if ((distanceToLeft < 0 && currentTile == leftTileToBeChecked)? distanceToLeft >= -5 : distanceToLeft <= 5 && distanceToLeft >= 0) {
								System.out.println("[Place] Collide left bottom");
								this.x -= 3;
								horCollideFound = true;
							}
						}
					}
				}
			}
		}
		
		if (!horCollideFound) {
			int rightTileToBeChecked;
			if (distanceToRight < 0) {
				rightTileToBeChecked = currentTile - 1;
			} else {
				rightTileToBeChecked = currentTile;
			}

			if (rightTileToBeChecked >= 0 && rightTileToBeChecked < this.mapWidth * this.mapHeight ) {
				Integer rightTile = this.layers.get(0)[this.getRowFromTile(rightTileToBeChecked) - 1]
													 [this.getColFromTile(rightTileToBeChecked) - 1];
				Integer[] rightTileDetails = this.tileDetails.get(rightTile - 1);
				if (rightTileDetails != null)
				for (Integer t : rightTileDetails) {
					if (t == Place.IS_WALL || t == Place.WALL_RIGHT) {
						if ((distanceToRight < 0 && currentTile == rightTileToBeChecked)? distanceToRight >= -5 : distanceToRight <= 5 && distanceToRight >= 0) {
							System.out.println("[Place] Collide right");
							this.x += 3;
							horCollideFound = true;
						}
					}
				}
				if (!horCollideFound && distanceToDown < 0) {
					int rightTileBottom = rightTileToBeChecked + this.mapWidth;
					if (rightTileBottom < this.mapWidth * this.mapHeight) {
						rightTile = this.layers.get(0)[this.getRowFromTile(rightTileBottom) - 1]
								[this.getColFromTile(rightTileBottom) - 1];
						rightTileDetails = this.tileDetails.get(rightTile - 1);
						if (rightTileDetails != null)
						for (Integer t : rightTileDetails) {
							if (t == Place.IS_WALL || t == Place.WALL_RIGHT) {
								if ((distanceToRight < 0 && currentTile == rightTileToBeChecked)? distanceToRight >= -5 : distanceToRight <= 5 && distanceToRight >= 0) {
									System.out.println("[Place] Collide right");
									this.x += 3;
									horCollideFound = true;
								}
							}
						}
					}
				}
			}
		}
//		// cek top/bottom
		int bottomTileToBeChecked;
		if (distanceToDown < 0) {
			bottomTileToBeChecked = currentTile - 1;
		} else {
			bottomTileToBeChecked = currentTile;
		}

		if (bottomTileToBeChecked >= 0 && bottomTileToBeChecked < this.mapWidth * this.mapHeight ) {
			Integer bottomTile = this.layers.get(0)[this.getRowFromTile(bottomTileToBeChecked) - 1]
												 [this.getColFromTile(bottomTileToBeChecked) - 1];
			Integer[] bottomTileDetails = this.tileDetails.get(bottomTile - 1);

			if (bottomTileDetails != null)
			for (Integer t : bottomTileDetails) {
				if (t == Place.IS_WALL || t == Place.WALL_DOWN) {
					if ((distanceToDown < 0 && currentTile == bottomTileToBeChecked)? distanceToDown >= -10 : distanceToDown <= 10 && distanceToDown >= 0) {
						System.out.println("[Place] Collide bottom");
						this.y += 3;
						vertCollideFound = true;
					}
				}
			}
			
			if (!vertCollideFound && distanceToLeft < 0) {
				int bottomTileLeft = bottomTileToBeChecked - 1;
				if (bottomTileLeft < this.mapWidth * this.mapHeight) {
					bottomTile = this.layers.get(0)[this.getRowFromTile(bottomTileLeft) - 1]
												   [this.getColFromTile(bottomTileLeft) - 1];
//					System.out.println("[Place] botleft=" + bottomTile);
					bottomTileDetails = this.tileDetails.get(bottomTile - 1);
					if (bottomTileDetails != null)
					for (Integer t : bottomTileDetails) {
						if (t == Place.IS_WALL || t == Place.WALL_DOWN) {
							if ((distanceToDown < 0 && currentTile == bottomTileToBeChecked)? distanceToDown >= -10 : distanceToDown <= 10 && distanceToDown >= 0) {
								this.y += 3;
								vertCollideFound = true;
							}
						}
					}
				}
			}

			if (!vertCollideFound && distanceToRight < 0) {
				// cek bottom kiri
				int bottomTileRight = bottomTileToBeChecked + 1;
				if (bottomTileRight < this.mapWidth * this.mapHeight) {
					bottomTile = this.layers.get(0)[this.getRowFromTile(bottomTileRight) - 1]
												   [this.getColFromTile(bottomTileRight) - 1];
					bottomTileDetails = this.tileDetails.get(bottomTile - 1);
					if (bottomTileDetails != null)
					for (Integer t : bottomTileDetails) {
						if (t == Place.IS_WALL || t == Place.WALL_DOWN) {
							if ((distanceToDown < 0 && currentTile == bottomTileToBeChecked)? distanceToDown >= -10 : distanceToDown <= 10 && distanceToDown >= 0) {
								this.y += 3;
								vertCollideFound = true;
							}
						}
					}
				}
			}
		}

		if (!vertCollideFound) {
			int upTileToBeChecked;
			if (distanceToUp < 0) {
				upTileToBeChecked = currentTile - 1;
			} else {
				upTileToBeChecked = currentTile;
			}

			if (upTileToBeChecked >= 0 && upTileToBeChecked < this.mapWidth * this.mapHeight ) {
				Integer upTile = this.layers.get(0)[this.getRowFromTile(upTileToBeChecked) - 1]
												   [this.getColFromTile(upTileToBeChecked) - 1];
				Integer[] upTileDetails = this.tileDetails.get(upTile - 1);
				if (upTileDetails != null)
				for (Integer t : upTileDetails) {
					if (t == Place.IS_WALL || t == Place.WALL_UP) {
						if ((distanceToUp < 0 && currentTile == upTileToBeChecked)? distanceToUp >= -10 : distanceToUp <= 10 && distanceToUp >= 0) {
							System.out.println("[Place] Collide up");
							this.y -= 3;
							vertCollideFound = true;
						}
					}
				}
				
				if (!vertCollideFound && distanceToLeft < 0) {
					// cek up kiri
					int upTileLeft = upTileToBeChecked - 1;
					if (upTileLeft < this.mapWidth * this.mapHeight) {
						upTile = this.layers.get(0)[this.getRowFromTile(upTileLeft) - 1]
												   [this.getColFromTile(upTileLeft) - 1];
						System.out.println("[Place] upleft=" + upTile);
						upTileDetails = this.tileDetails.get(upTile - 1);
						if (upTileDetails != null)
						for (Integer t : upTileDetails) {
							if (t == Place.IS_WALL || t == Place.WALL_UP) {
								if ((distanceToUp < 0 && currentTile == upTileToBeChecked)? distanceToUp >= -10 : distanceToUp <= 10 && distanceToUp >= 0) {
									System.out.println("[Place] Collide up left");
									this.y -= 3;
									vertCollideFound = true;
								}
							}
						}
					}
				}
				
				if (!vertCollideFound && distanceToRight < 0) {
					// cek up kiri
					int upTileRight = upTileToBeChecked + 1;
					if (upTileRight < this.mapWidth * this.mapHeight) {
						upTile = this.layers.get(0)[this.getRowFromTile(upTileRight) - 1]
													   [this.getColFromTile(upTileRight) - 1];
//						System.out.println("[Place] botleft=" + upTile);
						upTileDetails = this.tileDetails.get(upTile - 1);
						if (upTileDetails != null)
						for (Integer t : upTileDetails) {
							if (t == Place.IS_WALL || t == Place.WALL_UP) {
								if ((distanceToUp < 0 && currentTile == upTileToBeChecked)? distanceToUp >= -10 : distanceToUp <= 10 && distanceToUp >= 0) {
									System.out.println("[Place] Collide up right");
									this.y -= 3;
									vertCollideFound = true;
								}
							}
						}
					}
				}
			}
		}
	}

	public void detectEnemy() {
		int currenTile = this.getCurrentTile();
		for (int i = 0; i < 9; i++) {
			FightingCharacter e = this.enemies.get(this.getCurrentTile() + (i / 3) * this.mapWidth + (i % 3));
			if (e != null) {
				System.out.println("[Place] enemy detected");
			}
		}
	}

	public void addEnemy(FightingCharacter enemy, Integer tilePosition) {
		this.enemies.put(tilePosition, enemy);
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
