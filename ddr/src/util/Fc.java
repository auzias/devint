package util;

import gameStates.Gaming;

import java.awt.Font;

import javazoom.jlgui.basicplayer.BasicController;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;

import org.newdawn.slick.Color;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.tritonus.share.sampled.file.TAudioFileFormat;

import util.Levels.Level;
import util.Mode.Mod;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Classe contenant toutes les couleurs, fonts et effets utilisés souvent
 * 
 * @author auzias
 * 
 */
public class Fc {
	public static final FadeOutTransition fot = new FadeOutTransition(
			Color.black);
	public static final FadeInTransition fit = new FadeInTransition(Color.black);
	public static final Font font = new Font("Verdana", Font.PLAIN, 30);
	public static final UnicodeFont fontEffect = new UnicodeFont(font, 80, false, false);// font, size, bold, italic
	public static final Color noir = new Color(0, 0, 0);
	public static final Color bleu = new Color(0, 0, 255);
	public static final Color vert = new Color(0, 255, 0);
	public static final Color cyan = new Color(0, 255, 255);
	public static final Color rouge = new Color(255, 0, 0);
	public static final Color magenta = new Color(255, 0, 255);
	public static final Color jaune = new Color(255, 255, 0);
	public static final Color blanc = new Color(255, 255, 255);
	public static final Color orange = new Color(237, 127, 16);
	public static final int width = 1024;
	public static final int height = 768;
	public static final int xGauche = 100;
	public static final int xHaut = 300;
	public static final int xBas = 500;
	public static final int xDroite = 700;
	public static final int xAucune = 1024;
	public static final boolean fullScreen = false;
	public static final int maxChar = 21;
	public static final ArrayList<Integer> xOptions = new ArrayList<Integer>(
			Arrays.asList(132, 226, 320, 414, 508, 602, 696));
	public static final String pathMp3 = "./../ressources/mp3/";
	public static final String pathSM = "./../ressources/sm/";
	public static final BasicController basicPlayer = new BasicPlayer();
	public static final BasicController player = (BasicController) new BasicPlayer();
	public static Gamers gamers = new Gamers();
	public static File song;
	public static double bpm;
	public static double gap;
	public static boolean pause;
	private static double vol = 0.8;
	private static double sonMin = 0;
	private static double timeInSec = 0;
	public static KitGraphique kit = KitGraphique.kit1;
	private static int numKit = 0;
	public static double tempsDeplacement = 1.43;
	private static ArrayList<KitGraphique> kits = new ArrayList<KitGraphique>(
			Arrays.asList(KitGraphique.kit1, KitGraphique.kit2,
					KitGraphique.kit3, KitGraphique.kit4));
	public static final float horsEcran = -150;
	public static final int pointsEasy = 35;
	public static final int pointsMiddle = 45;
	public static final int pointsHard = 55;
	public static final int pointsUndoable = 75;
	public static final int tempsMaxEntreDeuxFlechesEasy = 1;
	public static final int tailleFleche = 180;
	public static final int comboUltime = 10;
	public static final float xPrintScore1 = 0;
	public static final float yPrintScore = 600;
	public static final int yFlecheImmobile = 30;
	public static final String root = "scores";
	public static final String score = "score";
	public static final String nom = "nom";
	public static final String resultat = "resultat";
	public static boolean sivox = true;
	public static final String chanson = "chanson";
	public static ArrayList<ScoreToSave> easycoop = new Restaurer(Level.EASY.toString(), Mod.COOP.toString()).restaurer().getListeScores();
	public static ArrayList<ScoreToSave> easysolo = new Restaurer(Level.EASY.toString(), Mod.SOLO.toString()).restaurer().getListeScores();
	public static ArrayList<ScoreToSave> easyvs = new Restaurer(Level.EASY.toString(), Mod.VS.toString()).restaurer().getListeScores();
	public static ArrayList<ScoreToSave> hardcoop = new Restaurer(Level.HARD.toString(), Mod.COOP.toString()).restaurer().getListeScores();
	public static ArrayList<ScoreToSave> hardsolo = new Restaurer(Level.HARD.toString(), Mod.SOLO.toString()).restaurer().getListeScores();
	public static ArrayList<ScoreToSave> hardvs = new Restaurer(Level.HARD.toString(), Mod.VS.toString()).restaurer().getListeScores();
	public static ArrayList<ScoreToSave> middlecoop = new Restaurer(Level.MIDDLE.toString(), Mod.COOP.toString()).restaurer().getListeScores();
	public static ArrayList<ScoreToSave> middlesolo = new Restaurer(Level.MIDDLE.toString(), Mod.SOLO.toString()).restaurer().getListeScores();
	public static ArrayList<ScoreToSave> middlevs = new Restaurer(Level.MIDDLE.toString(), Mod.VS.toString()).restaurer().getListeScores();
	public static ArrayList<ScoreToSave> undoablecoop = new Restaurer(Level.UNDOABLE.toString(), Mod.COOP.toString()).restaurer().getListeScores();
	public static ArrayList<ScoreToSave> undoablesolo = new Restaurer(Level.UNDOABLE.toString(), Mod.SOLO.toString()).restaurer().getListeScores();
	public static ArrayList<ScoreToSave> undoablevs = new Restaurer(Level.UNDOABLE.toString(), Mod.VS.toString()).restaurer().getListeScores();

	public static void raz() {
		gamers = new Gamers();
		song = new File("");
		bpm = 0;
		gap = 0;
		timeInSec = 0;
		
	}

	public static void changeKit() {
		numKit = ((numKit + 1) >= kits.size()) ? 0 : (numKit + 1);
		kit = kits.get(numKit);
	}

	private static void setVolume(double volume) {
		volume = (volume <= Fc.sonMin) ? Fc.sonMin : volume;
		volume = (volume > 1) ? 1 : volume;
		Fc.vol = volume;
		try {
			Fc.player.setGain(Fc.vol);
			//Fc.player.setGain(0);
		} catch (BasicPlayerException e) {
			e.printStackTrace();
		}
	}

	public static double getTimeInSec(String music) {
		File file = new File(music);
		AudioFileFormat fileFormat = null;
		try {
			fileFormat = AudioSystem.getAudioFileFormat(file);
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (fileFormat instanceof TAudioFileFormat) {
			Map<?, ?> properties = ((TAudioFileFormat) fileFormat).properties();
			String key = "duration";
			Long microseconds = (Long) properties.get(key);
			timeInSec = (double) microseconds / 1000000;
		} else
			System.err.println("temps inconnu");
		return Fc.timeInSec;
	}

	public static void restartGame(StateBasedGame game) {
		Fc.gamers.restart();
		Progress.start();
		Progress.stop();
		Gaming.restart();
	}

	private static double getVol() {
		return Fc.vol;
	}

	public static void volUp() {
		Fc.setVolume(Fc.getVol() + 0.1);
	}

	public static void volDown() {
		Fc.setVolume(Fc.getVol() - 0.1);
	}

	public static void volUpdate() {
		Fc.setVolume(Fc.getVol());
	}

	public static void volMin() {
		setVolume(Fc.sonMin);
	}
}