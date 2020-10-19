package fr.trxyy.alternative.alternative_api;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;

import fr.trxyy.alternative.alternative_api.utils.Logger;
import fr.trxyy.alternative.alternative_api.utils.file.FileUtil;

/**
 * @author Trxyy
 */
public class GameVerifier {
	
	/**
	 * The GameEngine instance
	 */
	public GameEngine engine;
	/**
	 * The allowed files list
	 */
	public static List<String> allowedFiles = new ArrayList<String>();
	/**
	 * The files list (existing)
	 */
	public List<File> filesList;
	/**
	 * The ignore list (files not to be deleted)
	 */
	public List<String> ignoreList = new ArrayList<String>();
	/**
	 * The ignore list (entire folder not to be deleted)
	 */
	public List<String> ignoreListFolder = new ArrayList<String>();
	/**
	 * The delete list (files forced to be deleted)
	 */
	public List<String> deleteList = new ArrayList<String>();
	
	/**
	 * The Constructor
	 * @param gameEngine The instance of GameEngine
	 */
	public GameVerifier(GameEngine gameEngine) {
		this.engine = gameEngine;
	}
	
	/**
	 * Verify files to ignore/delete
	 */
	public void verify() {
		this.filesList = (List<File>)FileUtils.listFiles(this.engine.getGameFolder().getGameDir(), TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
		for (File file : this.filesList) {
			
			if (file.getAbsolutePath().endsWith(engine.getGameLinks().getJsonName())) {
				continue;
			}
			
			if (file.getAbsolutePath().endsWith("downloads.xml")) {
				continue;
			}
			
			if (existInDeleteList(file.getAbsolutePath().replace(this.engine.getGameFolder().getGameDir().getAbsolutePath(), ""))) {
				FileUtil.deleteSomething(file.getAbsolutePath());
			}
			
			if (!existInAllowedFiles(file.getAbsolutePath().replace(this.engine.getGameFolder().getGameDir().getAbsolutePath(), ""))) {
				if (existInIgnoreListFolder(file.getParent().replace(this.engine.getGameFolder().getGameDir().getAbsolutePath(), ""))) {
					continue;
				}
				else if (existInIgnoreList(file.getAbsolutePath().replace(this.engine.getGameFolder().getGameDir().getAbsolutePath(), ""))) {
					continue;
				}
				else {
					FileUtil.deleteSomething(file.getAbsolutePath());
				}
			}
		}
	}
	
	/**
	 * Add files to addlowed files list
	 * @param allowed The file to add
	 */
	public static void addToFileList(String allowed) {
		allowedFiles.add(allowed);
	}
	
	/**
	 * Check if the file exist in the ignore list
	 * @param search The file path to search
	 * @return If the file exist
	 */
	public boolean existInIgnoreList(String search) {
		for(String str: this.ignoreList) {
		    if(str.trim().contains(search))
		       return true;
		}
		return false;
	}
	
	/**
	 * Check if the file exist in the ignore folder
	 * @param search The file path to search
	 * @return If the file exist
	 */
	public boolean existInIgnoreListFolder(String search) {
		String newSearch = search + "\\";
		for(String str: this.ignoreListFolder) {
		    if(newSearch.contains(str)) {
			       return true;
		    }
		}
		return false;
	}
	
	/**
	 * Check if the file exist in allowed files
	 * @param search The file path to search
	 * @return If the file exist
	 */
	public boolean existInAllowedFiles(String search) {
		for (String str : allowedFiles) {
			if (str.trim().contains(search))
				return true;
		}
		return false;
	}
	
	/**
	 * Check if the file existe in the delete list
	 * @param search The file path to search
	 * @return If the file exist
	 */
	public boolean existInDeleteList(String search) {
		for(String str: this.deleteList) {
		    if(str.trim().contains(search))
		       return true;
		}
		return false;
	}

	/**
	 * Getting the ignore list from URL
	 */
	public void getIgnoreList() {
		URL url = null;
		BufferedReader read = null;
		try {
			url = new URL(this.engine.getGameLinks().getIgnoreListUrl());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		try {
			read = new BufferedReader(new InputStreamReader(url.openStream()));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		String i;
		try {
			while ((i = read.readLine()) != null) {
				String correctName = i.replace('/', File.separatorChar);
				if (correctName.endsWith("\\") || correctName.endsWith("/")) {
//					Logger.log(correctName + " is a folder.");
					this.ignoreListFolder.add(correctName);
				}
				else {
					this.ignoreList.add("" + this.engine.getGameFolder().getGameDir() + File.separatorChar + correctName);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			read.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Getting the delete list from URL
	 */
	public void getDeleteList() {
		URL url = null;
		BufferedReader read = null;
		try {
			url = new URL(this.engine.getGameLinks().getDeleteListUrl());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		try {
			read = new BufferedReader(new InputStreamReader(url.openStream()));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		String i;
		try {
			while ((i = read.readLine()) != null) {
				String correctName = i.replace('/', File.separatorChar);
				this.deleteList.add("" + this.engine.getGameFolder().getGameDir() + File.separatorChar + correctName);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			read.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
