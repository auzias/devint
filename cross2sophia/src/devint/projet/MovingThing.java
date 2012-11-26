package devint.projet;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;

public class MovingThing {

	/**
	 * Possible actions
	 */
	protected Animation sprite;
	protected Animation up;
	protected Animation down;
	protected Animation left;
	protected Animation right;
	protected Animation none;

	/**
	 * Coordinations
	 */
	protected float x;
	protected float y;

	/**
	 * Last moving direction
	 */
	protected String lastDir;
	
	/**
	 * Pictures
	 */
	protected Image[] movementUp = new Image[1];
	protected Image[] movementDown = new Image[1];
	protected Image[] movementLeft = new Image[1];
	protected Image[] movementRight = new Image[1];
	protected Image[] movementNone = new Image[1];
	
	/**
	 * 
	 * @param x
	 * @param y
	 */
	protected MovingThing(float x, float y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * @return the sprite
	 */
	protected Animation getSprite() {
		return sprite;
	}

	/**
	 * @param up the up to set
	 */
	public void setUp(Animation up) {
		this.up = up;
	}

	/**
	 * @param down the down to set
	 */
	public void setDown(Animation down) {
		this.down = down;
	}

	/**
	 * @param left the left to set
	 */
	public void setLeft(Animation left) {
		this.left = left;
	}

	/**
	 * @param right the right to set
	 */
	public void setRight(Animation right) {
		this.right = right;
	}

	/**
	 * @param sprite the sprite to set
	 */
	protected void setSprite(Animation sprite) {
		this.sprite = sprite;
	}

	/**
	 * @return the up
	 */
	protected Animation getUp() {
		return up;
	}

	/**
	 * @param up the up to set
	 */
	protected void goUp() {
		this.sprite = this.up;
		this.lastDir = "up";
	}

	/**
	 * @return the down
	 */
	protected Animation getDown() {
		return down;
	}

	/**
	 * @param down the down to set
	 */
	protected void goDown() {
		this.sprite = this.down;
		this.lastDir = "down";
	}

	/**
	 * @return the left
	 */
	protected Animation getLeft() {
		return left;
	}

	/**
	 * @param left the left to set
	 */
	protected void goLeft() {
		this.sprite = this.left;
		this.lastDir = "left";
	}

	/**
	 * @return the right
	 */
	protected Animation getRight() {
		return right;
	}

	/**
	 * @param right the right to set
	 */
	protected void goRight() {
		this.sprite = this.right;
		this.lastDir = "right";
	}

	/**
	 * @return the x
	 */
	protected float getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	protected void setX(float x) {
		this.x = x;
	}

	
	
	
	/**
	 * @return the y
	 */
	protected float getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	protected void setY(float y) {
		this.y = y;
	}

	/**
	 * @return the lastDir
	 */
	protected String getLastDir() {
		return lastDir;
	}

	/**
	 * @param lastDir the lastDir to set
	 */
	protected void setLastDir(String lastDir) {
		this.lastDir = lastDir;
	}

}
