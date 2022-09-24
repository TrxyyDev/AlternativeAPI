package fr.trxyy.alternative.alternative_apiv2.settings;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import fr.trxyy.alternative.alternative_apiv2.base.GameEngine;

public class GameSaver {
	public GameInfos infos;
	public File settingsFile;
	private GameEngine engine;
	
	public GameSaver(GameEngine engin) {
		this.engine = engin;
		String fileName = "game_settings";
		File folder = new File(this.engine.getGameFolder().getGameDir(), "private/settings");
		if (!folder.exists()) {
			folder.mkdirs();
		}
		this.settingsFile = new File(this.engine.getGameFolder().getGameDir(), "private/settings/" + fileName);
		if (!this.settingsFile.exists()) {
			try {
				settingsFile.createNewFile();
				writeConfig("854x480", "-Xmx1G -XX:-UseAdaptiveSizePolicy -Xmn128M");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		readConfig();
	}
	
	public GameSaver(GameInfos info, GameEngine engin) {
		this.infos = info;
		this.engine = engin;
	}
	
	public void saveSettings() {
		String fileName = "game_settings";
		File folder = new File(this.engine.getGameFolder().getGameDir(), "private/settings");
		if (!folder.exists()) {
			folder.mkdirs();
		}
		this.settingsFile = new File(this.engine.getGameFolder().getGameDir(), "private/settings/" + fileName);
		if (!this.settingsFile.exists()) {
			try {
				settingsFile.createNewFile();
				writeConfig("854x480", "-Xmx1G -XX:-UseAdaptiveSizePolicy -Xmn128M");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			writeConfig(infos.getResolution(), infos.getVmArguments());
		}
	}
	
	public void writeConfig(String... s) {
		try {
			FileWriter fw = new FileWriter(settingsFile);
			fw.write(Encoder.encryptString(s[0]) + ";");
			fw.write(Encoder.encryptString(s[1]));
			fw.close();
		} catch (IOException e) {
			System.out.println(e.toString());
		}
	}
	
	public GameInfos readConfig() {
		GameInfos accountRead = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(settingsFile));
			String line = br.readLine();
			String[] result = line.split(";");
			accountRead = new GameInfos(Encoder.decryptString(result[0]), Encoder.decryptString(result[1]));
			br.close();
		} catch (IOException e) {
			System.out.println(e.toString());
		}
		return accountRead;
	}

}
