package util;

import java.io.*;
import java.util.*;

import entities.Fleche;

public class SMFile {
	private static String bpm;
	private static String gap;

	public static int getNbLignes(String unFichier) throws Exception {
		BufferedReader buffer = new BufferedReader(new FileReader(
				"./../ressources/sm/" + unFichier + ".sm"));
		int i = 0;
		@SuppressWarnings("unused")
		String str;
		while ((str = buffer.readLine()) != null)
			i++;
		return i;
	}

	public static List<String> readFile(String fichier) throws Exception {
		int nbLignes = getNbLignes(fichier);
		int i;
		List<String> list = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(
					"./../ressources/sm/" + fichier + ".sm"));

			// lecture du fichier texte
			for (i = 0; i < nbLignes; i++) {
				String line = br.readLine();
				String s = line;
				if (s.startsWith(",") || s.startsWith("#A")
						|| s.startsWith("#BA") || s.startsWith("#BG")
						|| s.startsWith("#T") || s.startsWith("#S")
						|| s.startsWith("#C") || s.startsWith("#L")
						|| s.startsWith("#M") || s.startsWith("#N")
						|| s.startsWith("\n") || s.startsWith(" ")
						|| s.startsWith("/")) {
					i++;
				} else if (s.startsWith("#O"))
					gap = (s.substring(9, 14));
				else if (s.startsWith("#BP"))
					bpm = (s.substring(12, 15));
				else {
					if (s.startsWith(";")) {
						break;
					} else {
						list.add(s);
					}
				}

			}
			br.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return list;
	}

	public static void createSMFile(String name) {
		try {
			File fichier = new File(name + ".sm");
			fichier.createNewFile();
		} catch (IOException e) {
			System.out.println("Impossible de créer le fichier : " + e.getMessage());
		}

		try {
			PrintWriter out = new PrintWriter(new FileWriter("ressources/sm/"
					+ name + ".sm"));
			out.println("#TITLE:"
					+ name
					+ ";\n#SUBTITLE:;\n#ARTIST:;\n#TITLETRANSLIT:;\n#SUBTITLETRANSLIT:;\n#ARTISTTRANSLIT:;\n#MENUCOLOR:;\n#NEWMETERSYSTEM:NO;\n#BANNER:;\n#BACKGROUND:;\n#LYRICSPATH:;\n#CDTITLE:;\n#MUSIC:"
					+ name
					+ ".mp3;\n#OFFSET:-"
					+ gap
					+ ";\n#SAMPLESTART:;\n#SAMPLELENGTH:;\n#SELECTABLE:YES;\n#BPMS:0.000="
					+ bpm
					+ ";\n//---------------dance-single----------------\n#NOTES:\n dance-single:\n:\n Beginner:\n 2:\n 0.171,0.198,0.000,0.000,0.000:");
			List<String> args = new ArrayList<String>(readFile(name));
			for (int i = 0; i < args.size(); i++) {
				out.println(args.get(i));
				if (i % 4 == 1 && i != 1 && i != args.size() - 1)
					out.println(",");
			}
			out.println(";");
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<Fleche> loadSM(String file) throws Exception {
		ArrayList<Fleche> liste = new ArrayList<Fleche>();
		List<String> args = SMFile.readFile(file);
		double bps = new Double(bpm).doubleValue()/ 60;
		double momentRef = (new Double(gap)).doubleValue();
		Fc.gap = new Double(gap).doubleValue();
		Fc.bpm = new Double(bpm).doubleValue();
		int i = 0;
		while(args.get(i).length() <= 0 || !(args.get(i).charAt(0) == '1' || args.get(i).charAt(0) == '0' ))
			i++;
		for (; i < args.size(); i++, momentRef = momentRef + 1 / bps) {
			if(args.get(i).length() <= 0)
				i++;
			if(args.get(i).charAt(0) == ';')
				break;
			if ("0000".equals(args.get(i)))
				liste.add(new Fleche(DIRECTION.AUCUNE, (int)momentRef));
			if (args.get(i).charAt(0) == '1')
				liste.add(new Fleche(DIRECTION.GAUCHE, (int)momentRef));
			if (args.get(i).charAt(1) == '1')
				liste.add(new Fleche(DIRECTION.HAUT, (int)momentRef));
			if (args.get(i).charAt(2) == '1')
				liste.add(new Fleche(DIRECTION.BAS, (int)momentRef));
			if (args.get(i).charAt(3) == '1')
				liste.add(new Fleche(DIRECTION.DROITE, (int)momentRef));
		}
		return liste;
	}
}