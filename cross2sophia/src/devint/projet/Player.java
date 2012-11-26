package devint.projet;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Player extends MovingThing {
	private boolean ramene;
	private boolean plein;
	private int score;
	private int life;
	private int coins;
	private int question;
	private boolean stayed;
	private boolean perte;
	private static final int LIFEMAX = 4;
	private int lastEnemyWhoTouched;
	private int lastEnemyWhoMadePlayerLooseCoins;

	public Player() throws SlickException {
		super(463f, 575f);
		this.ramene = false;
		this.score = 0;
		this.question = 0;
		this.life = LIFEMAX;
		this.lastDir = "Down";
		this.stayed = false;
		this.lastEnemyWhoTouched = 0;
		this.lastEnemyWhoMadePlayerLooseCoins = 0;

		movementUp[0] = new Image("../ressources/data/Character Boy.png");
		movementDown[0] = new Image("../ressources/data/Character Boy.png");
		movementLeft[0] = new Image("../ressources/data/Character Boy.png");
		movementRight[0] = new Image("../ressources/data/Character Boy.png");

		this.up = new Animation(movementUp, 300, false);
		this.down = new Animation(movementDown, 300, false);
		this.left = new Animation(movementLeft, 300, false);
		this.right = new Animation(movementRight, 300, false);
		this.sprite = this.up;
	}

	public int getCoins() {
		return coins;
	}

	public boolean isPerte() {
		return perte;
	}

	public void setPerte(boolean perte) {
		this.perte = perte;
	}

	public void setCoins(int coins) {
		this.coins = coins;
	}

	public int getLastEnemyWhoTouched() {
		return lastEnemyWhoTouched;
	}

	public void setLastEnemyWhoTouched(int lastEnemyWhoTouched) {
		this.lastEnemyWhoTouched = lastEnemyWhoTouched;
	}

	/**
	 * @return the lastEnemyWhoMadePlayerLostCoins
	 */
	public int getLastEnemyWhoMadePlayerLostCoins() {
		return lastEnemyWhoMadePlayerLooseCoins;
	}

	/**
	 * @param lastEnemyWhoMadePlayerLostCoins
	 *            the lastEnemyWhoMadePlayerLostCoins to set
	 */
	public void setLastEnemyWhoMadePlayerLostCoins(
			int lastEnemyWhoMadePlayerLostCoins) {
		this.lastEnemyWhoMadePlayerLooseCoins = lastEnemyWhoMadePlayerLostCoins;
	}

	/**
	 * @return the ramene
	 */
	public boolean isRamene() {
		return ramene;
	}

	/**
	 * @param ramene
	 *            the ramene to set
	 */
	public void setRamene(boolean ramene) {
		this.ramene = ramene;
	}

	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * @param score
	 *            the score to set
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * @return the life
	 */
	public int getLife() {
		return life;
	}

	/**
	 * @param life
	 *            the life to set
	 */
	public void setLife(int life) {
		this.life = life;
	}

	public boolean isPlein() {
		return plein;
	}

	/**
	 * @return the question
	 */
	public int getQuestion() {
		return question;
	}

	/**
	 * @param question
	 *            the question to set
	 */
	public void setQuestion(int question) {
		this.question = question;
	}

	public void setPlein(boolean plein) {
		this.plein = plein;
	}

	public boolean isTouched(Enemy e) {
		float dx = Math.abs(x - e.getX());
		float dy = Math.abs(y - e.getY());

		if ((dx <= 60) && (dy <= 60))
			return true;
		return false;
	}

	public boolean isStayed() {
		return stayed;
	}

	public void setStayed(boolean stayed) {
		this.stayed = stayed;
	}

	public static int getLifemax() {
		return LIFEMAX;
	}

	public void setDirection(GameContainer container, Map map, float fdelta,
			int delta, int niveau) {
		Input input = container.getInput();
		if (input.isKeyDown(Input.KEY_UP))
			this.setLastDir("up");
		if (input.isKeyDown(Input.KEY_DOWN))
			this.setLastDir("down");
		/*		if (input.isKeyDown(Input.KEY_LEFT))
			this.setLastDir("left");
		if (input.isKeyDown(Input.KEY_RIGHT))
			this.setLastDir("right");
		 */

		if ((this.getLastDir().equals("up") || this.getLastDir().equals("down"))
				&& (this.getY() > 350 && this.getY() < 354) && niveau >= 3) {
			this.setLastDir("none");
			this.getSprite().update(delta);
			this.setY(this.getY());
		}

		if ((this.getLastDir().equals("up") && !map.isTreasure(this.getX(),
				this.getY())) || input.isKeyDown(Input.KEY_UP)) {
			this.goUp();
			if (!(map.isTreasure(this.getX(), this.getY())
					|| map.isBlocked(this.getX(), this.getY() - fdelta) || map
					.isBlocked(this.getX() + map.getSizex() - 1, this.getY()
							- fdelta))) {
				// Plus delta est faible, plus l'anim l'est aussi
				this.getSprite().update(delta);
				this.setY(this.getY() - fdelta);
			}
		} else if ((this.getLastDir().equals("down") && !map.isBlocked(
				this.getX(), this.getY()))
				|| input.isKeyDown(Input.KEY_DOWN)) {
			this.goDown();
			if (!(map.isBlocked(this.getX(), this.getY() + map.getSizey() + 40
					+ fdelta) || map.isBlocked(
							this.getX() + map.getSizex() - 1,
							this.getY() + map.getSizey() + 40 + fdelta))) {
				this.getSprite().update(delta);
				this.setY(this.getY() + fdelta);
			}
			/*
		} else if (this.getLastDir().equals("left")
				|| input.isKeyDown(Input.KEY_LEFT)) {
			this.goLeft();
			if (!(map.isBlocked(this.getX() - fdelta, this.getY()) || map
					.isBlocked(this.getX() - fdelta,
							this.getY() + map.getSizey() - 1))) {
				this.getSprite().update(delta);
				this.setX(this.getX() - fdelta);

			}
		} else if (this.getLastDir().equals("right")
				|| input.isKeyDown(Input.KEY_RIGHT)) {
			this.goRight();
			if (!(map.isBlocked(this.getX() + map.getSizex() + fdelta,
					this.getY()) || map.isBlocked(this.getX() + map.getSizex()
					+ fdelta, this.getY() + map.getSizey() - 1))) {
				this.getSprite().update(delta);
				this.setX(this.getX() + fdelta);
			}
		}*/

			if (this.isPlein()) {
				try {
					movementDown[0] = new Image("../ressources/data/characterPlein"
							+ new Random().nextInt(5) + ".png");
					movementUp[0] = new Image("../ressources/data/characterPlein"
							+ new Random().nextInt(5) + ".png");
					this.down = new Animation(movementDown, 300, false);
					this.up = new Animation(movementUp, 300, false);
				} catch (SlickException e) {
					e.printStackTrace();
				}
			} else {
				try {
					movementUp[0] = new Image(
					"../ressources/data/Character Boy.png");
					movementDown[0] = new Image(
					"../ressources/data/Character Boy.png");
					this.down = new Animation(movementDown, 300, false);
					this.up = new Animation(movementUp, 300, false);
				} catch (SlickException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void doAction(GameContainer container, Graphics g,
			ArrayList<Enemy> ennemies, Map map) {
		// Player au tresor ?
		if (map.isTreasure(this.getX(), this.getY()) && !this.isStayed()) {
			this.setPlein(true);
			this.setStayed(true);
			this.setCoins(1000);
			Map.getFxPickUpCoins().play();
		}

		// Player en retour :
		if (this.getY() > 560) {
			this.setRamene(true);
			this.setStayed(false);
		} else
			this.setRamene(false);
		// Player depose or :
		if (this.isPlein() && this.isRamene()) {
			Map.getFxDropCoins().play();
			this.setScore(this.getScore() + this.getCoins());
			this.setQuestion(this.getQuestion() + 1);
			this.setCoins(0);
			this.setRamene(false);
			this.setPlein(false);
		}
		this.enemiesActions(ennemies, g);
	}

	private void enemiesActions(ArrayList<Enemy> enemies, Graphics g) {
		// Perte de vie :
		for (int i = 0; i < enemies.size(); i++) {
			if (this.isTouched(enemies.get(i))
					&& enemies.get(i).getID() > lastEnemyWhoTouched) {
				this.setLife(this.getLife() - 1);
				this.setLastEnemyWhoTouched(enemies.get(i).getID());
				g.drawString("Touche!", this.getX(), this.getY());
				Map.getFxTouched().play();
			}
			// Joueur en haut + plein et ennemi passe devant => perte de
			// pieces
			if (enemies.get(i).getX() > 350
					&& enemies.get(i).getX() < 550
					&& this.isPlein()
					&& this.getY() < 260
					&& this.isPerte()
					&& enemies.get(i).getID() != lastEnemyWhoMadePlayerLooseCoins) {
				if (this.getCoins() <= 0 || this.getCoins() == 500)
					this.setCoins(500);
				else {
					Map.getFxLostCoins().play();
					if (this.getCoins() == 600)
						this.setCoins(500);
					else
						this.setCoins(this.getCoins() - 100);
				}
				this.setPerte(false);
				this.setLastEnemyWhoMadePlayerLostCoins(enemies.get(i).getID());
			}
			// Player en haut et ennemis passe sur le pont :
			if (!(enemies.get(i).getX() > 350 && enemies.get(i).getX() < 550))
				this.setPerte(true);
		}
	}
}