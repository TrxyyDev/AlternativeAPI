package fr.trxyy.alternative.alternative_apiv2.settings;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import fr.trxyy.alternative.alternative_apiv2.base.GameEngine;

public class UsernameSaver {
	public String username;
	public File settingsFile;
	private GameEngine engine;
	
	public UsernameSaver(GameEngine engin) {
		this.engine = engin;
		String fileName = "offline_user";
		File folder = new File(this.engine.getGameFolder().getGameDir(), "private/settings");
		if (!folder.exists()) {
			folder.mkdirs();
		}
		this.settingsFile = new File(this.engine.getGameFolder().getGameDir(), "private/settings/" + fileName);
		if (!this.settingsFile.exists()) {
			try {
				settingsFile.createNewFile();
				writeConfig("");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String s = getUsername();
		System.out.println("username loaded: " + s);
	}
	
	public UsernameSaver(String names) {
		this.username = names;
	}
	
	public void saveSettings(String name) {
		String fileName = "offline_user";
		File folder = new File(this.engine.getGameFolder().getGameDir(), "private/settings");
		if (!folder.exists()) {
			folder.mkdirs();
		}
		this.settingsFile = new File(this.engine.getGameFolder().getGameDir(), "private/settings/" + fileName);
		if (!this.settingsFile.exists()) {
			try {
				settingsFile.createNewFile();
				writeConfig(name);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			writeConfig(name);
		}
		System.out.println("Wrote: " + name);
	}
	
	public void writeConfig(String s) {
		try {
			FileWriter fw = new FileWriter(settingsFile);
			fw.write(s);
			fw.close();
		} catch (IOException e) {
			System.out.println(e.toString());
		}
	}
	
	public String getUsername() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(settingsFile));
			String line = br.readLine();
			br.close();
			return line;
		} catch (IOException e) {
			System.out.println(e.toString());
		}
		return "";
	}
}
