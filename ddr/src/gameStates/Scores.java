package gameStates;

import java.util.ArrayList;
import java.util.Arrays;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import util.Fc;
import util.PrintList;
import util.PrintSelection;
import util.ScoreToSave;
import util.Levels.Level;
import util.Mode.Mod;

public class Scores extends BasicGameState {
	private static final int ID = 11;
	private int step = 0;
	protected int sel = 0;
	private PrintList choixMod = new PrintList("Choix du mode",	new ArrayList<String>(Arrays.asList("Solo", "Coop", "VS")));
	private PrintList choixLevel = new PrintList("Choix du niveau", new ArrayList<String>(Arrays.asList("Facile", "Moyen", "Dur", "Unfaisable")));
	private PrintList scores = new PrintList("Scores", new ArrayList<String>());
	private ArrayList<ScoreToSave> scoreList = new ArrayList<ScoreToSave>();
	private PrintSelection selection;
	private Mod mod;
	private Level level;
	private int debut = 1;

	@Override
	public void init(GameContainer container, StateBasedGame game)
	throws SlickException {
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
	throws SlickException {
		switch (step) {
		case 0:
			chooseLevel(g);
			break;
		case 1:
			chooseMod(g);
			break;
		case 2:
			PrintScores(g);
			break;
		default:
			break;
		}
	}

	private void chooseLevel(Graphics g) {
		this.selection = new PrintSelection(choixLevel);
		this.choixLevel.print(g, sel);
		this.selection.print(g, sel);
	}

	private void chooseMod(Graphics g) {
		this.selection = new PrintSelection(choixMod);
		this.choixMod.print(g, sel);
		this.selection.print(g, sel);
	}

	private void PrintScores(Graphics g) {
		g.setColor(Fc.blanc);
		g.fillRect(0, 0, Fc.width, Fc.height);
		String title = new String();
		title = "Scores : " + this.mod.toString() + " " + this.level.toString();
		Fc.fontEffect.drawString((Fc.width - Fc.fontEffect.getWidth(title)) / 2, 5, title, Fc.kit.getTitleColor());
		for (int i = debut, z = 100; i < debut + 8; i++, z += 80)
			Fc.fontEffect.drawString(5, z, "" + i + " - " + scoreList.get(i - 1).toString(), Fc.kit.getTextColor());
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
	throws SlickException {
		Input input = container.getInput();
		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			this.quit(game);
		} if (input.isKeyPressed(Input.KEY_ENTER)) {
			if (this.step == 0) {
				switch (this.sel) {
				case 0:
				default:
					this.level = Level.EASY;
					break;
				case 1:
					this.level = Level.MIDDLE;
					break;
				case 2:
					this.level = Level.HARD;
					break;
				case 3:
					this.level = Level.UNDOABLE;
					break;
				}
			}
			if (this.step == 1) {
				switch (this.sel) {
				case 0:
				default:
					this.mod = mod.SOLO;
					break;
				case 1:
					this.mod = mod.COOP;
					break;
				case 2:
					this.mod = mod.VS;
					break;
				}
				switch (this.level) {
				case EASY:
				default:
					switch (this.mod) {
					case COOP:
					default:
						this.scoreList = Fc.easycoop;
						break;
					case SOLO:
						this.scoreList = Fc.easysolo;
						break;
					case VS:
						this.scoreList = Fc.easyvs;
						break;
					}
					break;
				case MIDDLE:
					switch (this.mod) {
					case COOP:
					default:
						this.scoreList = Fc.middlecoop;
						break;
					case SOLO:
						this.scoreList = Fc.middlesolo;
						break;
					case VS:
						this.scoreList = Fc.middlevs;
						break;
					}
					break;
				case HARD:
					switch (this.mod) {
					case COOP:
					default:
						this.scoreList = Fc.hardcoop;
						break;
					case SOLO:
						this.scoreList = Fc.hardsolo;
						break;
					case VS:
						this.scoreList = Fc.hardvs;
						break;
					}
					break;
				case UNDOABLE:
					switch (this.mod) {
					case COOP:
					default:
						this.scoreList = Fc.undoablecoop;
						break;
					case SOLO:
						this.scoreList = Fc.undoablesolo;
						break;
					case VS:
						this.scoreList = Fc.undoablevs;
						break;
					}
					break;
				}
			}
			if(step == 2) {
				this.quit(game);
			}
			step++;
		}
		if(step == 2) {
			if (input.isKeyPressed(Input.KEY_DOWN)) {
				if (debut >= this.scoreList.size() - 7)
					debut = this.scoreList.size() - 7;
				else
					debut++;
			}
			if (input.isKeyPressed(Input.KEY_UP)) {
				if (debut <= 1)
					debut = 1;
				else
					debut--;
			}
		}
		if (input.isKeyPressed(Input.KEY_DOWN))
			this.sel++;
		if (input.isKeyPressed(Input.KEY_UP))
			this.sel--;
		if (this.step == 0 && this.sel > choixLevel.getOptions().size() - 1
				|| this.step == 1
				&& this.sel > choixMod.getOptions().size() - 1)
			this.sel = 0;
		if (this.step == 0 && this.sel < 0)
			this.sel = choixLevel.getOptions().size() - 1;
		if (this.step == 1 && this.sel < 0)
			this.sel = choixMod.getOptions().size() - 1;
	}

	private void quit(StateBasedGame game) {
		this.step = 0;
		game.enterState(new Menu().getID(), Fc.fot, Fc.fit);
	}

	@Override
	public int getID() {
		return Scores.ID;
	}
}