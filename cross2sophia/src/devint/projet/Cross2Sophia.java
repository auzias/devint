package devint.projet;

import java.awt.Font;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import t2s.SIVOXDevint;

public class Cross2Sophia extends StateBasedGame {
	public static final FadeOutTransition fot = new FadeOutTransition(
			Color.black);
	public static final FadeInTransition fit = new FadeInTransition(Color.black);
	public static final Font font = new Font("Verdana", Font.PLAIN, 30);
	public static final UnicodeFont fUnicodeFont = new UnicodeFont(font, 80,
			false, false);
	public static final int highScore = 1;
	public static final Color jaune = new Color(255, 255, 0);
	public static final Color bleu = new Color(0, 0, 255);
	public static final Color noir = new Color(0, 0, 0);
	public static final Color rouge = new Color(255, 0, 0);

	public static SIVOXDevint voix = new SIVOXDevint(3);

	public Cross2Sophia() throws SlickException {
		super("Cross2Sophia");
	}

	public static void main(String[] args) throws SlickException {
		Cross2Sophia c2s = new Cross2Sophia();
		AppGameContainer app = new AppGameContainer(c2s);

		app.setDisplayMode(1024, 768, false);
		app.start();
	}

	@Override
	public void initStatesList(GameContainer gameContainer)
			throws SlickException {
		this.addState(new Menu()); // ID = 0
		this.addState(new Gaming()); // ID = 1
		this.addState(new Pause()); // ID = 2
		this.addState(new Questions()); // ID = 3
		this.addState(new Scores()); // ID = 4
		this.addState(new GameOver()); // ID = 5
	}
}