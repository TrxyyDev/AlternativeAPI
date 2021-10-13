package fr.trxyy.alternative.alternative_api.updater;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import fr.trxyy.alternative.alternative_api.GameEngine;
import fr.trxyy.alternative.alternative_api.GameVerifier;
import fr.trxyy.alternative.alternative_api.utils.Logger;
import fr.trxyy.alternative.alternative_api.utils.file.FileUtil;

/**
 * @author Trxyy
 */
public class Downloader extends Thread {
	/**
	 * The download url
	 */
	private final String url;
	/**
	 * The Sha1
	 */
	private final String sha1;
	/**
	 * The file location
	 */
	private final File file;
	/**
	 * The gameEngine instance
	 */
	private GameEngine engine;

	/**
	 * Run the Thread
	 */
	public void run() {
		try {
			download();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * The Constructor
	 * @param file The file
	 * @param url The Url
	 * @param sha1 The Sha1
	 * @param engine_ The gameEngine instance
	 */
	public Downloader(File file, String url, String sha1, GameEngine engine_) {
		this.file = file;
		this.url = url;
		this.sha1 = sha1;
		this.engine = engine_;
		GameVerifier.addToFileList(file.getAbsolutePath().replace(engine.getGameFolder().getGameDir().getAbsolutePath(), "").replace("\\", "/"));
		file.getParentFile().mkdirs();
	}

	/**
	 * Download the file ion question
	 * @throws IOException
	 */
	public void download() throws IOException {
		Logger.log("Acquiring file '" + this.file.getName() + "'");
		engine.getGameUpdater().setCurrentFile(this.file.getName());
		if (this.file.getAbsolutePath().contains("assets")) {
			engine.getGameUpdater().setCurrentInfoText("Telechargement d'une ressource.");
		}
		else if (this.file.getAbsolutePath().contains("jre-legacy") || this.file.getAbsolutePath().contains("java-runtime-alpha")) {
			engine.getGameUpdater().setCurrentInfoText("Telechargement de java.");
		}
		else {
			engine.getGameUpdater().setCurrentInfoText("Telechargement d'une librairie.");
		}
		BufferedInputStream bufferedInputStream = null;
		FileOutputStream fileOutputStream = null;
		try {
			URL downloadUrl = new URL(this.url.replace(" ", "%20"));
			URLConnection urlConnection = downloadUrl.openConnection();
			urlConnection.addRequestProperty("User-Agent", "Mozilla/5.0 AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.124 Safari/537.36");
			bufferedInputStream = new BufferedInputStream(urlConnection.getInputStream());
			fileOutputStream = new FileOutputStream(this.file);
			

			byte[] data = new byte[1024];
			int read;

			while ((read = bufferedInputStream.read(data, 0, 1024)) != -1) {
				fileOutputStream.write(data, 0, read);
			}
			engine.getGameUpdater().downloadedFiles++;
		} finally {
			if (bufferedInputStream != null) {
				bufferedInputStream.close();
			}
			if (fileOutputStream != null) {
				fileOutputStream.close();
			}
		}
	}

	/**
	 * @return If the file require a update
	 */
	public boolean requireUpdate() {
		if ((this.file.exists()) && (FileUtil.matchSHA1(this.file, this.sha1))) {
			return false;
		}
		return true;
	}
}