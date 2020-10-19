package fr.trxyy.alternative.alternative_api.updater;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import fr.trxyy.alternative.alternative_api.GameEngine;
import fr.trxyy.alternative.alternative_api.GameVerifier;
import fr.trxyy.alternative.alternative_api.utils.Logger;
import fr.trxyy.alternative.alternative_api.utils.file.FileUtil;
import fr.trxyy.alternative.alternative_api.utils.file.LauncherFile;

/**
 * @author Trxyy
 */
public class GameParser {

	/**
	 * Get custom files to download
	 * @param engine The GameEngine instance
	 */
	public static void getFilesToDownload(GameEngine engine) {
		Logger.log("Preparation de la mise a jour.");
		downloadXMLFile(engine);
		try {
			final URL resourceUrl = new URL(engine.getGameLinks().getCustomFilesUrl());
			final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			final DocumentBuilder db = dbf.newDocumentBuilder();
			final Document doc = db.parse(resourceUrl.openConnection(Proxy.NO_PROXY).getInputStream());
			final NodeList nodeLst = doc.getElementsByTagName("Contents");

			final long start = System.nanoTime();
			for (int i = 0; i < nodeLst.getLength(); i++) {
				final Node node = nodeLst.item(i);
				if (node.getNodeType() == 1) {
	                  final Element element = (Element) node;
	                  final String key = element.getElementsByTagName("Key").item(0).getChildNodes().item(0).getNodeValue().replace("\n", ""); // .replace(" ", "%20")
	                  String etag = element.getElementsByTagName("ETag") != null ? element.getElementsByTagName("ETag").item(0).getChildNodes().item(0).getNodeValue() : "-";
	                  final long size = Long.parseLong(element.getElementsByTagName("Size").item(0).getChildNodes().item(0).getNodeValue());

					File localFile = new File(engine.getGameFolder().getGameDir(), key);
					GameVerifier.addToFileList(localFile.getAbsolutePath().replace(engine.getGameFolder().getGameDir().getAbsolutePath(), "").replace('/', File.separatorChar));
					if (key.contains("minecraft.jar")) {
						engine.getGameUpdater().hasCustomJar = true;
					}
					if (!localFile.isDirectory()) {
						if (etag.length() > 1) {
							etag = FileUtil.getEtag(etag);
							if (localFile.exists()) {
								if (localFile.isFile() && localFile.length() == size) {
									final String localMd5 = FileUtil.getMD5(localFile);
									if (!localMd5.equals(etag)) {
										if (!(engine.getGameLinks().getCustomFilesUrl() + key).endsWith("/")) {
												engine.getGameUpdater().files.put(key, new LauncherFile(size, engine.getGameLinks().getCustomFilesUrl() + key, localFile.getAbsolutePath()));
												engine.getGameUpdater().filesToDownload++;
										}
									}
								} else {
									if (!(engine.getGameLinks().getCustomFilesUrl() + key).endsWith("/")) {
										engine.getGameUpdater().files.put(key, new LauncherFile(size, engine.getGameLinks().getCustomFilesUrl() + key, localFile.getAbsolutePath()));
										engine.getGameUpdater().filesToDownload++;
									}
								}
							} else {
								if (!(engine.getGameLinks().getCustomFilesUrl() + key).endsWith("/")) {
									engine.getGameUpdater().files.put(key, new LauncherFile(size, engine.getGameLinks().getCustomFilesUrl() + key, localFile.getAbsolutePath()));
									engine.getGameUpdater().filesToDownload++;
								}
							}

						}
					} else {
						localFile.mkdir();
						localFile.mkdirs();
					}
				}
			}
			final long end = System.nanoTime();
			final long delta = end - start;
			Logger.log("Temps (delta) pour comparer les ressources: " + delta / 1000000L + " ms");
			Logger.log("Depuis: " + resourceUrl);
		} catch (final Exception ex) {
			Logger.log("Impossible de telecharger les ressources (" + ex + ")");
			ex.printStackTrace();
		}
	}
	
	/**
	 * get files to download (offline)
	 * @param engine The GameEngine instance
	 */
	public static void getFilesToDownloadOffline(GameEngine engine) {
		Logger.log("Preparation de la mise a jour.");
		try {
			File f = new File(engine.getGameFolder().getCacheDir(), "downloads.xml");
			final URL resourceUrl = new URL(f.toURL().toString());
			final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			final DocumentBuilder db = dbf.newDocumentBuilder();
			final Document doc = db.parse(resourceUrl.openConnection(Proxy.NO_PROXY).getInputStream());
			final NodeList nodeLst = doc.getElementsByTagName("Contents");

			final long start = System.nanoTime();
			for (int i = 0; i < nodeLst.getLength(); i++) {
				final Node node = nodeLst.item(i);
				if (node.getNodeType() == 1) {
	                  final Element element = (Element) node;
	                  final String key = element.getElementsByTagName("Key").item(0).getChildNodes().item(0).getNodeValue().replace("\n", "");
	                  String etag = element.getElementsByTagName("ETag") != null ? element.getElementsByTagName("ETag").item(0).getChildNodes().item(0).getNodeValue() : "-";
	                  final long size = Long.parseLong(element.getElementsByTagName("Size").item(0).getChildNodes().item(0).getNodeValue());

					File localFile = new File(engine.getGameFolder().getGameDir(), key);
					GameVerifier.addToFileList(localFile.getAbsolutePath().replace(engine.getGameFolder().getGameDir().getAbsolutePath(), "").replace('/', File.separatorChar));
					if (key.contains("minecraft.jar")) {
						engine.getGameUpdater().hasCustomJar = true;
					}
					if (!localFile.isDirectory()) {
						if (etag.length() > 1) {
							etag = FileUtil.getEtag(etag);
							if (localFile.exists()) {
								if (localFile.isFile() && localFile.length() == size) {
									final String localMd5 = FileUtil.getMD5(localFile);
									if (!localMd5.equals(etag)) {
										if (!(engine.getGameLinks().getCustomFilesUrl() + key).endsWith("/")) {
												engine.getGameUpdater().files.put(key, new LauncherFile(size, engine.getGameLinks().getCustomFilesUrl() + key, localFile.getAbsolutePath()));
												engine.getGameUpdater().filesToDownload++;
										}
									}
								} else {
									if (!(engine.getGameLinks().getCustomFilesUrl() + key).endsWith("/")) {
										engine.getGameUpdater().files.put(key, new LauncherFile(size, engine.getGameLinks().getCustomFilesUrl() + key, localFile.getAbsolutePath()));
										engine.getGameUpdater().filesToDownload++;
									}
								}
							} else {
								if (!(engine.getGameLinks().getCustomFilesUrl() + key).endsWith("/")) {
									engine.getGameUpdater().files.put(key, new LauncherFile(size, engine.getGameLinks().getCustomFilesUrl() + key, localFile.getAbsolutePath()));
									engine.getGameUpdater().filesToDownload++;
								}
							}

						}
					} else {
						localFile.mkdir();
						localFile.mkdirs();
					}
				}
			}
			final long end = System.nanoTime();
			final long delta = end - start;
			Logger.log("Temps (delta) pour comparer les ressources: " + delta / 1000000L + " ms");
			Logger.log("Depuis: " + resourceUrl);
		} catch (final Exception ex) {
			Logger.log("Impossible de telecharger les ressources (" + ex + ")");
			ex.printStackTrace();
		}
	}

	/**
	 * Download the XML files
	 * @param engine The GameEngine instance
	 */
	public static void downloadXMLFile(GameEngine engine) {
		File theFile = new File(engine.getGameFolder().getCacheDir(), "downloads.xml");
		GameVerifier.addToFileList(theFile.getAbsolutePath().replace(engine.getGameFolder().getCacheDir().getAbsolutePath(), "").replace('/', File.separatorChar));
		try {
			URL url = new URL(engine.getGameLinks().getCustomFilesUrl());
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			float totalDataRead = 0;
			BufferedInputStream in = new BufferedInputStream(connection.getInputStream());
			FileOutputStream fos = new FileOutputStream(theFile);
			BufferedOutputStream bout = new BufferedOutputStream(fos, 1024);
			byte[] data = new byte[1024];
			int i = 0;
			while ((i = in.read(data, 0, 1024)) >= 0) {
				totalDataRead = totalDataRead + i;
				bout.write(data, 0, i);
			}
			bout.close();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

}
