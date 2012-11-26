package entities;

import org.newdawn.slick.*;

import util.*;

import java.util.Random;

public class Fleche extends entities.MovingThing {
	private float speed;
	private DIRECTION dir;
	private int momentRef;
	private int ID;
	private static int Number = 0;

	public Fleche() throws SlickException {
		this(3);
	}

	public Fleche(DIRECTION dir, int momentRef) throws SlickException {
		super(DIRECTION.getX(dir), (float) Fc.height-180);
		this.dir = dir;
		this.momentRef = momentRef;
		this.constructor();
	}

	public Fleche(int momentRef) throws SlickException {
		super(400f, (float) Fc.height);
		this.momentRef = momentRef;
		int number = new Random().nextInt(4) + 1;
		if (number == 1) {
			this.setX(Fc.xGauche);
			this.dir = DIRECTION.GAUCHE;
		} else if (number == 2) {
			this.setX(Fc.xGauche);
			this.dir = DIRECTION.GAUCHE;
		} else if (number == 3) {
			this.setX(Fc.xGauche);
			this.dir = DIRECTION.GAUCHE;
		} else {
			this.setX(Fc.xGauche);
			this.dir = DIRECTION.GAUCHE;
		}
		this.constructor();
	}

	private void constructor() throws SlickException {
		this.ID = Number++;
		this.speed = 0.40f;
		for(int i = 0; i <= 4; i++)
			this.pic[i] = new Image("./../ressources/" + dir.toString(dir) + (5-i) + ".png");
		this.sprite = new Animation(pic, 150);
	}

	public float getSpeed() {
		return speed;
	}

	public int getMomentRef() {
		return momentRef;
	}

	public void setMomentRef(int momentRef) {
		this.momentRef = momentRef;
	}

	public DIRECTION getDir() {
		return dir;
	}

	public void setDir(DIRECTION dir) {
		this.dir = dir;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public Animation getSprite() {
		return super.getSprite();
	}

	public void setPosition(float fdeltaE, int delta) throws SlickException {
		this.getSprite().update(delta);
		this.setY(this.getY() - fdeltaE);
	}

	public String toString() {
		return "dir = " + this.dir.toString(dir) + ",\ttemps = "
				+ this.momentRef + ",\tID = " + this.ID;
	}
}