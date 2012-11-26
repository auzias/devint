package devint.projet;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.openal.Audio;
import java.util.Random;

public class Enemy extends MovingThing {
	private Audio monster;
	private boolean moveLeft;
	private int number;
	private float speed;
	private int side;
	private static int counter = 0;
	private int ID;

	public Enemy(int niveau) throws SlickException {
		super(1100f, 350f);
		this.lastDir = "left";
		ID = counter;
		counter++;

		Random random = new Random();
		moveLeft = random.nextBoolean();

		if (!moveLeft) {
			this.setX(-100f);
			this.lastDir = "right";
		}

		speed = random.nextFloat();
		if (speed < 0.3f)
			speed = 0.3f;
		if (speed > 0.6f)
			speed = 0.6f;

		side = random.nextInt(100);
		if (side % 2 == 1)
			this.setY(238f);
		else
			this.setY(445f);
		
		if(niveau < 3)
			this.setY(350f);
		
		String dir = "";
		
		if(moveLeft==true)
		{dir = "g";}
		else{dir = "d";}
		
		
		number = random.nextInt(16)+1;
		
		this.movementUp[0] = new Image("../ressources/data/enemy" + number
				+ dir +".png");
		this.movementDown[0] = new Image("../ressources/data/enemy" + number
				+ dir +".png");
		this.movementLeft[0] = new Image("../ressources/data/enemy" + number
				+ dir +".png");
		this.movementRight[0] = new Image("../ressources/data/enemy" + number
				+ dir +".png");

		this.up = new Animation(movementUp, 300, false);
		this.down = new Animation(movementDown, 300, false);
		this.left = new Animation(movementLeft, 300, false);
		this.right = new Animation(movementRight, 300, false);
		this.sprite = this.left;
	}

	public int getSide() {
		return side;
	}

	public int getID() {
		return ID;
	}

	/**
	 * @return the monster
	 */
	public Audio getMonster() {
		return monster;
	}

	/**
	 * @param monster
	 *            the monster to set
	 */
	public void setMonster(Audio monster) {
		this.monster = monster;
	}

	/**
	 * @return the monster
	 */
	public float getSpeed() {
		return speed;
	}

	/**
	 * @param monster
	 *            the monster to set
	 */
	public void setSpeed(float speed) {
		this.speed = speed;
	}

	/**
	 * @return the moveLeft
	 */
	public boolean isMoveLeft() {
		return moveLeft;
	}

	/**
	 * @param moveLeft
	 *            the moveLeft to set
	 */
	public void setMoveLeft(boolean moveLeft) {
		this.moveLeft = moveLeft;
	}

	public void setDirection(GameContainer container, Map map, float fdeltaE,
			int delta) {
		if ((this.getLastDir().equals("up"))) {
			this.goUp();
			this.getSprite().update(delta);
			this.setY(this.getY() - fdeltaE);
		} else if ((this.getLastDir().equals("left"))) {
			this.goLeft();
			this.getSprite().update(delta);
			this.setX(this.getX() - fdeltaE);
		} else if ((this.getLastDir().equals("down"))) {
			this.goDown();
			this.getSprite().update(delta);
			this.setY(this.getY() + fdeltaE);
		} else if ((this.getLastDir().equals("right"))) {
			this.goRight();
			this.getSprite().update(delta);
			this.setX(this.getX() + fdeltaE);
		}
	}
}