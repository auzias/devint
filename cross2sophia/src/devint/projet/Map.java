package devint.projet;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Map {
	private TiledMap tiledMap;
	private static final int SIZEX = 101;
	private static final int SIZEY = 44;
	private boolean[][] blocked;
	private boolean[][] treasure;
	private Image kit;
	private Image kit2;
	private Image fKP;
	private Image fKE;
	public static Image sac;
	private Boolean fKitMode = false;
	private Boolean kitMode = false;
	private static Sound fxDropCoins;
	private static Sound fxLostCoins;
	private static Sound fxPickUpCoins;
	private static Sound fxTouched;
	private Sound looser;
	private int kitModeGraphic = 0;

	public Map(String name) throws SlickException {
		super();
		this.tiledMap = new TiledMap("../ressources/data/" + name + ".tmx");
		blocked = new boolean[tiledMap.getWidth()][tiledMap.getHeight()];
		treasure = new boolean[tiledMap.getWidth()][tiledMap.getHeight()];

		this.kit = new Image("../ressources/data/kit.png");
		this.kit2 = new Image("../ressources/data/kit2.png");
		this.fKE = new Image("../ressources/data/rondE.png");
		this.fKP = new Image("../ressources/data/rondP.png");

		sac = new Image("../ressources/data/sac.png");

		fxTouched = new Sound("../ressources/data/sons/touched.wav");
		fxDropCoins = new Sound("../ressources/data/sons/ramene.wav");
		fxLostCoins = new Sound("../ressources/data/sons/perte.wav");
		fxPickUpCoins = new Sound("../ressources/data/sons/coins.wav");
		looser = new Sound("../ressources/data/sons/perdu.wav");

		blocked = new boolean[tiledMap.getWidth()][tiledMap.getHeight()];
		treasure = new boolean[tiledMap.getWidth()][tiledMap.getHeight()];

		// On applique les propriétés aux coordonnées voulues
		for (int xAxis = 0; xAxis < tiledMap.getWidth(); xAxis++) {
			for (int yAxis = 0; yAxis < tiledMap.getHeight(); yAxis++) {
				int tileID = tiledMap.getTileId(xAxis, yAxis, 0);
				String value = tiledMap.getTileProperty(tileID, "blocked",
						"false");

				if ("true".equals(value))
					blocked[xAxis][yAxis] = true;

				tileID = tiledMap.getTileId(xAxis, yAxis, 2);
				value = tiledMap.getTileProperty(tileID, "treasure", "false");

				if ("true".equals(value))
					treasure[xAxis][yAxis] = true;
			}
		}
	}

	/**
	 * @return the looser
	 */
	public Sound getLooser() {
		return looser;
	}

	/**
	 * @return the cross2Sophia
	 */
	public TiledMap getTiledMap() {
		return tiledMap;
	}

	/**
	 * @param cross2Sophia
	 *            the cross2Sophia to set
	 */
	public void setTiledMap(TiledMap tiledMap) {
		this.tiledMap = tiledMap;
	}

	/**
	 * @return the blocked
	 */
	public boolean[][] getBlocked() {
		return blocked;
	}

	public boolean isBlocked(int x, int y) {
		return blocked[x][y];
	}

	/**
	 * @param blocked
	 *            the blocked to set
	 */
	public void setBlocked(boolean[][] blocked) {
		this.blocked = blocked;
	}

	/**
	 * @return the treasure
	 */
	public boolean[][] getTreasure() {
		return treasure;
	}

	public boolean isTreasure(int x, int y) {
		return treasure[x][y];
	}

	/**
	 * @param treasure
	 *            the treasure to set
	 */
	public void setTreasure(boolean[][] treasure) {
		this.treasure = treasure;
	}

	/**
	 * @return the kit
	 */
	public Image getKit() {
		return kit;
	}

	public Image getKit2() {
		return kit2;
	}

	/**
	 * @param kit
	 *            the kit to set
	 */
	public void setKit(Image kit) {
		this.kit = kit;
	}

	/**
	 * @return the fKP
	 */
	public Image getfKP() {
		return fKP;
	}

	/**
	 * @param fKP
	 *            the fKP to set
	 */
	public void setfKP(Image fKP) {
		this.fKP = fKP;
	}

	/**
	 * @return the fKE
	 */
	public Image getfKE() {
		return fKE;
	}

	/**
	 * @param fKE
	 *            the fKE to set
	 */
	public void setfKE(Image fKE) {
		this.fKE = fKE;
	}

	/**
	 * @return the fKitMode
	 */
	public Boolean getfKitMode() {
		return fKitMode;
	}

	/**
	 * @param fKitMode
	 *            the fKitMode to set
	 */
	public void setfKitMode(Boolean fKitMode) {
		this.fKitMode = fKitMode;
	}

	/**
	 * @return the kitMode
	 */
	public Boolean getKitMode() {
		return kitMode;
	}

	/**
	 * @param kitMode
	 *            the kitMode to set
	 */
	public void setKitMode(Boolean kitMode) {
		this.kitMode = kitMode;
	}

	/**
	 * @return the sizex
	 */
	public int getSizex() {
		return SIZEX;
	}

	/**
	 * @return the sizey
	 */
	public int getSizey() {
		return SIZEY;
	}

	public static Sound getFxPickUpCoins() {
		return fxPickUpCoins;
	}

	public static Sound getFxDropCoins() {
		return fxDropCoins;
	}

	public static Sound getFxTouched() {
		return fxTouched;
	}

	public static Sound getFxLostCoins() {
		return fxLostCoins;
	}

	public void setKitGraphic(GameContainer container) {
		Input input = container.getInput();
		if (input.isKeyPressed(Input.KEY_F4)) {
			kitModeGraphic++;
			if (kitModeGraphic > 2)
				kitModeGraphic = 0;
		}
		if (kitModeGraphic == 0) {
			this.setfKitMode(false);
			this.setKitMode(false);
		} else if (kitModeGraphic == 1) {
			this.setfKitMode(false);
			this.setKitMode(true);
		} else {
			this.setfKitMode(true);
			this.setKitMode(false);
		}
	}

	/**
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isBlocked(float x, float y) {
		int xBlock = (int) x / this.getSizex();
		int yBlock = (int) y / this.getSizey();
		return this.isBlocked(xBlock, yBlock);
	}

	/**
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isTreasure(float x, float y) {
		int xTreasure = (int) x / this.getSizex();
		int yTreasure = (int) y / this.getSizey();
		return this.isTreasure(xTreasure, yTreasure);
	}

	public void displayKit(Graphics g, Player player, ArrayList<Enemy> enemies) {

		player.getSprite().draw((int) player.getX(), (int) player.getY());

		if (this.getKitMode() && Gaming.niveau < 3) {
			g.drawImage(this.getKit(), 1, 1);
			player.getSprite().draw((int) player.getX(), (int) player.getY());
		}
		if (this.getfKitMode() && Gaming.niveau < 3) {
			g.drawImage(this.getKit(), 1, 1); // Contraste map
			// player.getSprite().draw((int) player.getX(), (int)
			// player.getY());
			g.drawImage(this.getfKP(), player.getX(), player.getY()); // Contraste
			// player
		}

		if (this.getKitMode() && Gaming.niveau >= 3) {
			g.drawImage(this.getKit2(), 1, 1);
			player.getSprite().draw((int) player.getX(), (int) player.getY());
		}
		if (this.getfKitMode() && Gaming.niveau >= 3) {
			g.drawImage(this.getKit2(), 1, 1); // Contraste map
			g.drawImage(this.getfKP(), player.getX(), player.getY()); // Contraste
			// player
		}

		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i)
					.getSprite()
					.draw((int) enemies.get(i).getX(),
							(int) enemies.get(i).getY());
			if (this.getfKitMode()) {
				g.drawImage(this.getfKE(), enemies.get(i).getX(), enemies
						.get(i).getY()); // Contraste enemy
			}
		}
	}
}