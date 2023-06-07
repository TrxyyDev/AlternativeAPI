package fr.trxyy.alternative.alternative_apiv2.build;

import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.text.StrSubstitutor;

import fr.trxyy.alternative.alternative_apiv2.base.GameEngine;
import fr.trxyy.alternative.alternative_apiv2.base.GameFolder;
import fr.trxyy.alternative.alternative_apiv2.minecraft.json.Argument;
import fr.trxyy.alternative.alternative_apiv2.minecraft.json.ArgumentType;
import fr.trxyy.alternative.alternative_apiv2.minecraft.json.MinecraftVersion;
import fr.trxyy.alternative.alternative_apiv2.minecraft.utils.FileUtil;
import fr.trxyy.alternative.alternative_apiv2.minecraft.utils.GameUtils;
import fr.trxyy.alternative.alternative_apiv2.minecraft.utils.OperatingSystem;
import fr.trxyy.alternative.alternative_apiv2.settings.GameInfos;
import fr.trxyy.alternative.alternative_apiv2.settings.GameSaver;
import fr.trxyy.alternative.alternative_authv2.base.Session;
import javafx.application.Platform;

public class GameRunner {

	private MinecraftVersion minecraftVersion;
	private GameEngine engine;
	private GameFolder workDir;
	private Session session;
	private GameInfos savedInfos;
	
	public GameRunner(Session ses, MinecraftVersion mcVersion, GameEngine engin) {
		this.session = ses;
		this.minecraftVersion = mcVersion;
		this.engine = engin;
		this.workDir = engine.getGameFolder();
		FileUtil.deleteFolder(workDir.getNativesDir());
		this.unpackNatives();
		GameSaver saver = new GameSaver(engine);
		this.savedInfos = saver.readConfig();
		if (engine.getStage() != null) {
			Platform.runLater(new Runnable() {
				public void run() {
					engine.getStage().hide();
				}
			});
		}
	}
	
	/**
	 * Launch the game
	 * @throws Exception
	 */
    public void launch() throws Exception
    {
    	ArrayList<String> commands = this.getLaunchCommand();
        ProcessBuilder processBuilder = new ProcessBuilder(commands);
        processBuilder.redirectInput(Redirect.INHERIT);
        processBuilder.redirectOutput(Redirect.INHERIT);
        processBuilder.redirectError(Redirect.INHERIT);
		processBuilder.directory(workDir.getGameDir());
		processBuilder.redirectErrorStream(true);
		String cmds = "";
		for (String command : commands) {
			cmds += command + " ";
		}
		String[] ary = cmds.split(" ");
		System.out.println("Launching: " + hideAccessToken(ary));
		try {
			Process process = processBuilder.start();
			process.waitFor();
			int exitVal = process.exitValue();
			if (exitVal != 0) {
				System.out.println("Minecraft has crashed.");
			}
		} catch (IOException e) {
			throw new Exception("Cannot launch !", e);
		}
	}

	private ArrayList<String> getLaunchCommand() {
		ArrayList<String> commands = new ArrayList<String>();
		OperatingSystem os = OperatingSystem.getCurrentPlatform();

		if (minecraftVersion.getJavaVersion() != null) {
			String component = minecraftVersion.getJavaVersion().getComponent();
			if (component != null) {
				commands.add(OperatingSystem.getJavaPath(minecraftVersion, engine));
			} else {
				commands.add(OperatingSystem.getJavaPath());
			}
		} else {
			commands.add(OperatingSystem.getJavaPath());
		}

		if (os.equals(OperatingSystem.OSX)) {
			commands.add("-Xdock:name=Minecraft");
			commands.add("-Xdock:icon=" + workDir.getAssetsDir() + "icons/minecraft.icns");
		} else if (os.equals(OperatingSystem.WINDOWS)) {
			if (!(minecraftVersion.getJavaVersion() != null)) {
				commands.add("-XX:+UseConcMarkSweepGC");
			}
			if (minecraftVersion.getArguments() == null) {
				commands.add("-XX:HeapDumpPath=MojangTricksIntelDriversForPerformance_javaw.exe_minecraft.exe.heapdump");
			}
		}
		
		if (minecraftVersion.getJavaVersion() != null) {
			commands.add("-XX:+UnlockExperimentalVMOptions");
			commands.add("-XX:+UseG1GC");
			commands.add("-XX:G1NewSizePercent=20");
			commands.add("-XX:G1ReservePercent=20");
			commands.add("-XX:MaxGCPauseMillis=50");
			commands.add("-XX:G1HeapRegionSize=32M");
			commands.add("-Dfml.ignoreInvalidMinecraftCertificates=true");
			commands.add("-Dfml.ignorePatchDiscrepancies=true");
			commands.add("-Djava.net.preferIPv4Stack=true");
			commands.add("-Dminecraft.applet.TargetDirectory=" + workDir.getGameDir());
		}

		if (minecraftVersion.getLogging() != null) {
			File log4jFile = new File(workDir.getLogConfigsDir(), minecraftVersion.getLogging().getClient().getFile().getId());
			commands.add(minecraftVersion.getLogging().getClient().getArgument().replace("${path}", log4jFile.getAbsolutePath()));
		}
		
		String arguments1 = this.savedInfos.getVmArguments();
		String str2[] = arguments1.split(" ");
		List<String> args2 = Arrays.asList(str2);
		commands.addAll(args2);
		
		if (minecraftVersion.getArguments() != null) {
			List<Argument> jvmArguments = minecraftVersion.getArguments().get(ArgumentType.JVM);
			System.out.println("jvmArgs: " + jvmArguments);
			final String[] anotherArgs = getJvmArguments(jvmArguments);
			System.out.println("other Args: " + anotherArgs);
			StringBuffer stringBuffer = new StringBuffer();
			for (int index = 0; index < anotherArgs.length; index++) {
				if (anotherArgs[index] != null && !anotherArgs[index].equals("")){
					stringBuffer.append(anotherArgs[index] + " ");
				}
			}
			String splittedString[] = stringBuffer.toString().split(" ");
			List<String> finaliseList = Arrays.asList(splittedString);
			System.out.println("final Args: " + finaliseList);
			commands.addAll(finaliseList);
		}
		else {
			commands.add("-Djava.library.path=" + workDir.getNativesDir().getAbsolutePath());
			commands.add("-Dfml.ignoreInvalidMinecraftCertificates=true");
			commands.add("-Dfml.ignorePatchDiscrepancies=true");
			commands.add("-Dminecraft.launcher.brand=Minecraft");
			commands.add("-Dminecraft.launcher.version=999");
			
			commands.add("-cp");
			commands.add("\"" + GameUtils.constructClasspath(minecraftVersion, engine) + "\"");
			System.out.println("Using Old Arguments, Putting -cp argument.");
		}

		commands.add(minecraftVersion.getMainClass());

		/** ----- Minecraft Arguments ----- */
		if (minecraftVersion.getMinecraftArguments() != null) {
			final String[] argsD = getArgumentsOlder(minecraftVersion.getMinecraftArguments());
			List<String> arguments = Arrays.asList(argsD);
			commands.addAll(arguments);
		}
		/** ----- Minecraft Arguments 1.13+ ----- */
		if (minecraftVersion.getArguments() != null) {
			List<Argument> argsNewer = minecraftVersion.getArguments().get(ArgumentType.GAME);
			final String[] defaultArguments = getArgumentsNewer(argsNewer);
			
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < defaultArguments.length; i++) {
				sb.append(defaultArguments[i] + " ");
			}
			String sub = sb.toString().replace("--demo", "").replace("--width", "").replace("--height", "");
			String strcs[] = sub.split(" ");
			List<String> newerList = Arrays.asList(strcs);
			commands.addAll(newerList);
		}
		
		if (engine.getGameConnect() != null) {
			commands.add("--server");
			commands.add(engine.getGameConnect().getIp());
			commands.add("--port");
			commands.add(engine.getGameConnect().getPort());
		}
		
		/** ----- Size of window ----- */
		if (engine.getGameSize() != null) {
			commands.add("--width");
			commands.add(this.savedInfos.getResolutionWidth());
			commands.add("--height");
			commands.add(this.savedInfos.getResolutionHeight());
		}
		
		return commands;
	}

	/**
	 * Get minecraft launch arguments for old versions of Minecraft
	 * @return a String[] with multiples arguments
	 */
	private String[] getArgumentsOlder(String argum) {
		final Map<String, String> map = new HashMap<String, String>();
		final StrSubstitutor substitutor = new StrSubstitutor(map);
		final String[] split = argum.split(" ");
		map.put("auth_player_name", session.getUsername());
		map.put("auth_uuid", session.getUuid());
		map.put("auth_access_token", session.getToken());
		map.put("user_type", "legacy");
		map.put("version_name", minecraftVersion.getId());
		map.put("version_type", "release");
		map.put("game_directory", workDir.getGameDir().getAbsolutePath());
		map.put("assets_root", workDir.getAssetsDir().getAbsolutePath());
		map.put("assets_index_name", minecraftVersion.getAssets());
		map.put("user_properties", "{}");

		for (int i = 0; i < split.length; i++)
			split[i] = substitutor.replace(split[i]);

		return split;
	}

	/**
	 * Get minecraft launch arguments for new versions of Minecraft
	 * @param args The arguments from json as a List
	 * @return a String[] with multiples arguments
	 */
	private String[] getArgumentsNewer(List<Argument> args) {
		final Map<String, String> map = new HashMap<String, String>();
		final StrSubstitutor substitutor = new StrSubstitutor(map);
		final String[] split = new String[args.size()];
		for (int i = 0; i < args.size(); i++) {
				split[i] = args.get(i).getArguments();
		}
		map.put("auth_player_name", session.getUsername());
		map.put("auth_uuid", session.getUuid());
		map.put("auth_access_token", session.getToken());
		map.put("user_type", "msa"); // legacy, mojang, msa
		map.put("version_name", minecraftVersion.getId());
		map.put("version_type", "release");
		map.put("game_directory", workDir.getGameDir().getAbsolutePath());
		map.put("assets_root", workDir.getAssetsDir().getAbsolutePath());
		map.put("assets_index_name", minecraftVersion.getAssets());
		map.put("user_properties", "{}");
		
		map.put("clientid", "2535464861463420");
		map.put("auth_xuid", session.getUuid());

		for (int i = 0; i < split.length; i++)
			split[i] = substitutor.replace(split[i]);

		return split;
	}
	
	/**
	 * Get minecraft jvm arguments for new versions of Minecraft
	 * @param args The arguments from json as a List
	 * @return a String[] with multiples arguments
	 */
	private String[] getJvmArguments(List<Argument> args) {
		final Map<String, String> map = new HashMap<String, String>();
		final StrSubstitutor substitutor = new StrSubstitutor(map);
		final String[] split = new String[args.size()];
		for (int i = 0; i < args.size(); i++) {
				split[i] = args.get(i).getArguments();
		}
		map.put("natives_directory", workDir.getNativesDir().getAbsolutePath());
		map.put("launcher_name", "Minecraft");
		map.put("launcher_version", "999");
		map.put("classpath", GameUtils.constructClasspath(this.minecraftVersion, engine));
		map.put("launcher_version", "999");
		map.put("library_directory", workDir.getLibsDir().getAbsolutePath());
		map.put("classpath_separator", ";");
		map.put("version_name", minecraftVersion.getId());

		for (int i = 0; i < split.length; i++)
			split[i] = substitutor.replace(split[i]);

		return split;
	}
	
	/**
	 * Unpack natives before launching the game
	 */
	private void unpackNatives() {
		try {
			FileUtil.unpackNatives(workDir.getNativesDir(), engine);
		} catch (IOException e) {
			System.out.println("Couldn't unpack natives!");
			e.printStackTrace();
			return;
		}
	}
	
	/**
	 * Hide the access token inside the console
	 * @param arguments The Token
	 * @return The arguments List<String> with the hidden token
	 */
	public static List<String> hideAccessToken(String[] arguments) {
        final ArrayList<String> output = new ArrayList<String>();
        for (int i = 0; i < arguments.length; i++) {
            if (i > 0 && Objects.equals(arguments[i-1], "--accessToken")) {
                output.add("????????");
            } else {
                output.add(arguments[i]);
            }
        }
        return output;
    }

}
