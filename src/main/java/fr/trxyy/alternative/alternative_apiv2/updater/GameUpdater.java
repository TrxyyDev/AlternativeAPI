package fr.trxyy.alternative.alternative_apiv2.updater;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import fr.trxyy.alternative.alternative_apiv2.assets.AssetIndex;
import fr.trxyy.alternative.alternative_apiv2.assets.AssetObject;
import fr.trxyy.alternative.alternative_apiv2.base.GameEngine;
import fr.trxyy.alternative.alternative_apiv2.base.GameFolder;
import fr.trxyy.alternative.alternative_apiv2.build.GameRunner;
import fr.trxyy.alternative.alternative_apiv2.minecraft.java.JVMFile;
import fr.trxyy.alternative.alternative_apiv2.minecraft.java.JVMManifest;
import fr.trxyy.alternative.alternative_apiv2.minecraft.java.JavaManifest;
import fr.trxyy.alternative.alternative_apiv2.minecraft.java.JavaRuntime;
import fr.trxyy.alternative.alternative_apiv2.minecraft.json.MinecraftLibrary;
import fr.trxyy.alternative.alternative_apiv2.minecraft.json.MinecraftVersion;
import fr.trxyy.alternative.alternative_apiv2.minecraft.utils.Arch;
import fr.trxyy.alternative.alternative_apiv2.minecraft.utils.CompatibilityRule;
import fr.trxyy.alternative.alternative_apiv2.minecraft.utils.Duplicator;
import fr.trxyy.alternative.alternative_apiv2.minecraft.utils.EnumJavaFileType;
import fr.trxyy.alternative.alternative_apiv2.minecraft.utils.FileUtil;
import fr.trxyy.alternative.alternative_apiv2.minecraft.utils.GameUtils;
import fr.trxyy.alternative.alternative_apiv2.minecraft.utils.JsonUtil;
import fr.trxyy.alternative.alternative_apiv2.minecraft.utils.OperatingSystem;
import fr.trxyy.alternative.alternative_apiv2.minecraft.utils.CompatibilityRule.Action;
import fr.trxyy.alternative.alternative_authv2.base.Session;

public class GameUpdater {
	
	private GameFolder workDir;
	/**
	 * The libraries Executor
	 */
	private ExecutorService jarsExecutor = Executors.newFixedThreadPool(5);
	/**
	 * The assets Executor
	 */
	private ExecutorService assetsExecutor = Executors.newFixedThreadPool(5);
	/**
	 * The log4j Executor
	 */
	private ExecutorService log4jExecutor = Executors.newFixedThreadPool(5);
	/**
	 * The java Executor
	 */
	private ExecutorService javaExecutor = Executors.newFixedThreadPool(5);
	
	/**
	 * The Minecraft JVM manifest
	 */
	public JVMManifest jvmManifest;
	/**
	 * The Minecraft Java Manifest
	 */
	public JavaManifest javaManifest;
	/**
	 * The Java style
	 */
	public String javaStyle;
	/**
	 * The current Info text of the progressbar
	 */
	private String currentInfoText = "";
	/**
	 * The current file of the progressbar
	 */
	private String currentFile = "";
	/**
	 * The AssetIndex
	 */
	public AssetIndex assetsList;
	private MinecraftVersion minecraftVersion;
	private GameEngine engine;
	/**
	 * The downloaded custom files
	 */
	public int downloadedFiles = 0;
	/**
	 * The custom files to download
	 */
	public int filesToDownload = 0;
	private String updateText;
	/**
	 * The Assets Url
	 */
	private static final String ASSETS_URL = "https://resources.download.minecraft.net/";
	
	public GameUpdater(MinecraftVersion mcVersion, GameEngine engin) {
		this.minecraftVersion = mcVersion;
		this.engine = engin;
		this.workDir = engin.getGameFolder();
		this.updateText = "Mise a Jour...";
	}
	
	/**
	 * Index minecraft assets json
	 */
	public void indexAssets() {
		String json = null;
		String assetUrl = minecraftVersion.getAssetIndex().getUrl().toString();
		try {
			json = JsonUtil.loadJSON(assetUrl);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			assetsList = (AssetIndex) JsonUtil.getGson().fromJson(json, AssetIndex.class);
		}
	}
	
	/**
	 * Update minecraft assets
	 */
	public void updateAssets() {
		String json = null;
		String assetUrl = minecraftVersion.getAssetIndex().getUrl().toString();
		AssetIndex assetsList;
		try {
			json = JsonUtil.loadJSON(assetUrl);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			assetsList = (AssetIndex) JsonUtil.getGson().fromJson(json, AssetIndex.class);
		}
		Map<String, AssetObject> objects = assetsList.getObjects();
		for (String assetKey : objects.keySet()) {
			AssetObject asset = (AssetObject) objects.get(assetKey);
			File mc = getAssetInMcFolder(asset.getHash());
			File local = getAsset(asset.getHash());

			local.getParentFile().mkdirs();
			if ((!local.exists()) || (!FileUtil.matchSHA1(local, asset.getHash()))) {
				if ((!local.exists()) && (mc.exists()) && (FileUtil.matchSHA1(mc, asset.getHash()))) {
					this.assetsExecutor.submit(new Duplicator(mc, local));
					System.out.println("Copying asset " + local.getName());
				} else {
					Downloader downloadTask = new Downloader(local, toURL(asset.getHash()), asset.getHash(), engine);
					if (downloadTask.requireUpdate()) {
						this.assetsExecutor.submit(downloadTask);
						this.filesToDownload++;
						System.out.println("Downloading asset " + local.getName());
					}
				}
			}
		}
		this.assetsExecutor.shutdown();
		File indexes = new File(workDir.getAssetsDir(), "indexes");
		indexes.mkdirs();
		File index = new File(indexes, minecraftVersion.getAssets() + ".json");

		if (!index.exists()) {
			try {
				index.createNewFile();
				BufferedWriter writer = new BufferedWriter(new FileWriter(index));
				writer.write(JsonUtil.getGson().toJson(assetsList));
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			this.assetsExecutor.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @return The assetsList
	 */
	public AssetIndex getAssetsList() {
		return assetsList;
	}
	
	/**
	 * @param hash The hash
	 * @return The asset File
	 */
	private File getAsset(String hash) {
		File assetsDir = workDir.getAssetsDir();
		File mcObjectsDir = new File(assetsDir, "objects");
		File hex = new File(mcObjectsDir, hash.substring(0, 2));
		return new File(hex, hash);
	}

	/**
	 * @param hash The hash
	 * @return The asset file in minecraft folder
	 */
	private File getAssetInMcFolder(String hash) {
		File minecraftAssetsDir = new File(GameUtils.getWorkingDirectory("minecraft"), "assets");
		File minecraftObjectsDir = new File(minecraftAssetsDir, "objects");
		File hex = new File(minecraftObjectsDir, hash.substring(0, 2));
		return new File(hex, hash);
	}

	/**
	 * @param hash The hash
	 * @return The hash url of the assets
	 */
	private String toURL(String hash) {
		return ASSETS_URL + hash.substring(0, 2) + "/" + hash;
	}
	
	/**
	 * Update minecraft libraries
	 */
	@SuppressWarnings({ "unlikely-arg-type", "unused" })
	public void updateJars() {
		FileUtil.deleteFolder(workDir.getNativesCacheDir());
		for (MinecraftLibrary lib : minecraftVersion.getLibraries()) {
			File libPath = new File(workDir.getLibsDir(), lib.getArtifactPath());

			if (lib.getCompatibilityRules() != null) {
				for (final CompatibilityRule rule : lib.getCompatibilityRules()) {
					if (rule.getOs() != null && rule.getAction() != null) {
						for (final String os : rule.getOs().getName().getAliases()) {
							if (lib.appliesToCurrentEnvironment()) {
								if (rule.getAction().equals(Action.DISALLOW.getName())) {
									lib.setSkipped(true);
								} else {
									lib.setSkipped(false);
								}
							} else {
								if (rule.getAction().equals(Action.ALLOW.getName())) {
									lib.setSkipped(false);
								} else {
									lib.setSkipped(true);
								}
							}
						}
					}
				}
			}

			if (!lib.isSkipped()) {
				if (lib.appliesToCurrentEnvironment()) {
					if (lib.getArtifact() != null) {
							final Downloader downloadTask = new Downloader(libPath, lib.getArtifact().getUrl().toString(), lib.getArtifact().getSha1(), engine);
							if (downloadTask.requireUpdate()) {
								this.jarsExecutor.submit(downloadTask);
								this.filesToDownload++;
							}
						}
					
					// Without GetDownloads();
					if (lib.getClassifiers() != null) {
					final Map<OperatingSystem, String> natives = lib.getNatives();
					if (natives != null && natives.containsKey(OperatingSystem.getCurrent())) {
						String nativesName = natives.get(OperatingSystem.getCurrent()).replace("natives-", "");
						final File nativePath = new File(workDir.getNativesCacheDir(), lib.getArtifactNatives(nativesName));
						final Downloader downloadTask8 = new Downloader(nativePath, lib.getClassifiers().get(nativesName).getUrl().toString(), lib.getClassifiers().get(nativesName).getSha1(), engine);
						if (downloadTask8.requireUpdate()) {
							this.jarsExecutor.submit(downloadTask8);
							this.filesToDownload++;
						}
					}
				}
					
					if (lib.getDownloads() != null) {
						if (lib.getDownloads().getArtifact() != null) {
							final Downloader downloadTask = new Downloader(libPath, lib.getDownloads().getArtifact().getUrl().toString(), lib.getDownloads().getArtifact().getSha1(), engine);
							if (downloadTask.requireUpdate()) {
								this.jarsExecutor.submit(downloadTask);
								this.filesToDownload++;
							}
						}
						
						// With GetDownloads();
						if (lib.getDownloads().getClassifiers() != null) {
							final Map<OperatingSystem, String> nativesClassifier = lib.getNatives();
							if (nativesClassifier != null && nativesClassifier.containsKey(OperatingSystem.getCurrent())) {
								String nativesName = nativesClassifier.get(OperatingSystem.getCurrent()).replace("${arch}", Arch.CURRENT.getBit());
								final File nativePath = new File(workDir.getNativesCacheDir(), lib.getArtifactNatives(nativesName));
								final Downloader downloadTask8 = new Downloader(nativePath, lib.getDownloads().getClassifiers().get(nativesName).getUrl().toString(), lib.getDownloads().getClassifiers().get(nativesName).getSha1(), engine);
								if (downloadTask8.requireUpdate()) {
									this.jarsExecutor.submit(downloadTask8);
									this.filesToDownload++;
								}
							}
						}
					}
				}
			}
		}
		File versionFolder = new File(workDir.getVersionsDir(), minecraftVersion.getId());
		final Downloader versionsJar = new Downloader(new File(versionFolder, minecraftVersion.getId() + ".jar"), minecraftVersion.getDownloads().getClient().getUrl().toString(), minecraftVersion.getDownloads().getClient().getSha1(), engine);

		if (versionsJar.requireUpdate()) {
			this.jarsExecutor.submit(versionsJar);
			this.filesToDownload++;
		}
		this.jarsExecutor.shutdown();
		try {
			this.jarsExecutor.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void downloadJavaManifest() {
		if (minecraftVersion.getJavaVersion() != null) {
			String json = null;
			String manifestUrl = "https://launchermeta.mojang.com/v1/products/java-runtime/2ec0cc96c44e5a76b9c8b7c39df7210883d12871/all.json";
			try {
				json = JsonUtil.loadJSON(manifestUrl);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				this.javaManifest = (JavaManifest) JsonUtil.getGson().fromJson(json, JavaManifest.class);
				System.out.println("CurrentRuntime: " + this.javaManifest.getCurrentOS()); // windows-x64
				Map<String, List<JavaRuntime>> r = this.javaManifest.getCurrentJava();
				for (String run : r.keySet()) {
					if (run.equals(minecraftVersion.getJavaVersion().getComponent())) {
						System.out.println("Choosen: " + run);
						ArrayList<JavaRuntime> s = (ArrayList<JavaRuntime>) r.get(run);
						this.indexJava(s.get(0).getManifest().getUrl().toString());
						break;
					}
				}
			}
		}
	}
	
	public void indexJava(String url) {
		String javaManifestJson = null;
		try {
			javaManifestJson = JsonUtil.loadJSON(url);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			jvmManifest = (JVMManifest) JsonUtil.getGson().fromJson(javaManifestJson, JVMManifest.class);
			updateJava();
		}
	}
	
	public void updateJava() {
		if (minecraftVersion.getJavaVersion() != null) {
		Map<String, JVMFile> objects = this.jvmManifest.getFiles();

		for (String javaFile : objects.keySet()) {
			JVMFile jvmFile = (JVMFile) objects.get(javaFile);
			File localFolder = new File(workDir.getRuntimeDir(), this.minecraftVersion.getJavaVersion().getComponent());
			localFolder.mkdirs();
			File local = new File(localFolder, javaFile);
			
			if (!jvmFile.getType().equals(EnumJavaFileType.DIRECTORY.getName())) {
				Downloader downloadTask = new Downloader(local, jvmFile.getDownloads().getRaw().getUrl().toString(), jvmFile.getDownloads().getRaw().getSha1(), engine);
				if (downloadTask.requireUpdate()) {
					this.javaExecutor.submit(downloadTask);
					this.filesToDownload++;
				}
			}
			else {
				local.mkdirs();
			}
		}
		
		this.javaExecutor.shutdown();
		try {
			this.javaExecutor.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Jre Update finished.");
		}
	}

	public void updateLog4j() {
		if (minecraftVersion.getLogging() != null) {
			File log4jfolder = workDir.getLogConfigsDir();
			log4jfolder.mkdirs();
			File log4jfile = new File(log4jfolder, minecraftVersion.getLogging().getClient().getFile().getId());
			
				Downloader downloadTask = new Downloader(log4jfile, minecraftVersion.getLogging().getClient().getFile().getUrl().toString(), minecraftVersion.getLogging().getClient().getFile().getSha1(), engine);
				if (downloadTask.requireUpdate()) {
					this.log4jExecutor.submit(downloadTask);
					this.filesToDownload++;
				}
			}
			
			this.log4jExecutor.shutdown();
			try {
				this.log4jExecutor.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Log4j Update finished.");
	}

	public void runGame(Session session) {
		this.updateText = "Demarrage...";
		GameRunner runner = new GameRunner(session, this.minecraftVersion, this.engine);
		try {
			runner.launch();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @return Get current Info text
	 */
	public String getCurrentInfo() {
		return this.currentInfoText;
	}

	/**
	 * Set current info text
	 * @param name The text of the info
	 */
	public void setCurrentInfoText(String name) {
		this.currentInfoText = name;
	}

	/**
	 * @return The current File name
	 */
	public String getCurrentFile() {
		return this.currentFile;
	}

	/**
	 * Set current File name
	 * @param name The name
	 */
	public void setCurrentFile(String name) {
		this.currentFile = name;
	}

	public String getUpdateText() {
		return this.updateText;
	}
}
