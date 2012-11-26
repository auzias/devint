package gameStates;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class GameStates extends BasicGameState {
	private int stateId = 0;

	public enum STATES { // ID:description
		MENU_STATE, // 1 :menu principal (entrée du jeu)
		MODE_STATE, // 2 :choix du jeu en : solo, coop ou VS
		MULTI_STATE, // 3 :choix du jeu en multi
		// COOP_STATE, //4 :choix du jeu en COOP
		// VS_STATE, //5 :choix du jeu en VS
		MUSIC_STATE, // 6 :choix de la musique
		LEVEL_STATE, // 7 :choix du niveau de difficulté
		GAMING_STATE, // 8 :jeu en cours
		PAUSE_GAME_STATE, // 9 :jeu en pause
		SAVE_SCORE_STATE, // 10:sauvegarde du pseudo et du score
		SCORE_STATE, // 11:affichage de la liste des scores
		GAME_OVER_STATE, // 12:fin du jeu
		OPTION_STATE, // 13:choix des couleurs, volume...
		LAUNCH_STATE, // 14:lancement du jeu
		INSTRUCTION_STATE, // 15:choix des options
		OPTIONS_GAMINE_STATE, // 16:choix des options durant le jeu
		DANCEMAT_CONF_STATE, // 17:etalonnage du tapis
		MUSIC_FOR_NEW_STATE, // 18:choix de la musique pour laquelle on veut
								// créer un fichier sm
		GAP_SELECTION_STATE, // 19: insertion du gap
		BPM_SELECTION_STATE, // 20: insertion du bpm
		CREATE_SM_STATE,		// 21: interface de création de fichier sm
		END_GAMING,			//22 : state après la fin de la partie
		PRINT_SCORE			//23 : state pour afficher les scores
	}

	private STATES currentState = STATES.MENU_STATE;

	public GameStates(int stateId) {
		this.stateId = stateId;
	}

	@Override
	public int getID() {
		return stateId;
	}

	@Override
	public void init(GameContainer container, StateBasedGame sb)
			throws SlickException {
	}

	@Override
	public void enter(GameContainer container, StateBasedGame sb)
			throws SlickException {
		super.enter(container, sb);
		currentState = STATES.LAUNCH_STATE;
	}

	@Override
	public void render(GameContainer container, StateBasedGame sb, Graphics g)
			throws SlickException {
	}

	@Override
	public void update(GameContainer container, StateBasedGame sb, int delta)
			throws SlickException {
		switch (currentState) {
		case LAUNCH_STATE:
			// game.enterState(Menu.ID);
			break;
		case MODE_STATE:
			// game.enterState(ModeChoice.ID);
			break;
		case MUSIC_STATE:
			break;
		case LEVEL_STATE:
			break;
		case GAMING_STATE:
			break;
		case MENU_STATE:
			break;
		case PAUSE_GAME_STATE:
			/*
			 * do { if (input.isKeyPressed(Input.KEY_ESCAPE)) System.exit(0); if
			 * (input.isKeyPressed(Input.KEY_ENTER)) {
			 * game.enterState(Gaming.ID); endLoop = true; } } while (endLoop);
			 */
			break;
		case SAVE_SCORE_STATE:
			break;
		case SCORE_STATE:
			break;
		case GAME_OVER_STATE:
			break;
		case OPTION_STATE:
			/*
			 * do { if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			 * game.enterState(Menu.ID); endLoop = true; } } while (endLoop);
			 */
			break;
		case MUSIC_FOR_NEW_STATE:
			break;
		case GAP_SELECTION_STATE:
			break;
		case BPM_SELECTION_STATE:
			break;
		case CREATE_SM_STATE:
			break;
		}
	}
}