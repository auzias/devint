package entities;

import org.newdawn.slick.*;

public class MovingThing {
	protected Animation sprite;
	protected float x;
	protected float y;
	protected Image[] pic = new Image[5];

	protected MovingThing(float x, float y) {
		this.x = x;
		this.y = y;
	}

	protected Animation getSprite() {
		return sprite;
	}

	protected void setSprite(Animation sprite) {
		this.sprite = sprite;
	}

	public float getX() {
		return x;
	}

	protected void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	protected void setY(float y) {
		this.y = y;
	}
}
