package fr.trxyy.alternative.alternative_apiv2.minecraft.java;

import java.util.List;
import java.util.Map;

import com.google.gson.annotations.SerializedName;

import fr.trxyy.alternative.alternative_apiv2.minecraft.utils.Arch;
import fr.trxyy.alternative.alternative_apiv2.minecraft.utils.OperatingSystem;

public class JavaManifest {

	public Map<String, List<JavaRuntime>> gamecore;
	public Map<String, List<JavaRuntime>> linux;
	@SerializedName("linux-i386")
	public Map<String, List<JavaRuntime>> linuxI386;
	@SerializedName("mac-os")
	public Map<String, List<JavaRuntime>> macos;
	@SerializedName("windows-x64")
	public Map<String, List<JavaRuntime>> windows64;
	@SerializedName("windows-x86")
	public Map<String, List<JavaRuntime>> windows86;

	public JavaManifest(JavaManifest o) {
		this.gamecore = o.gamecore;
		this.linux = o.linux;
		this.linuxI386 = o.linuxI386;
		this.macos = o.macos;
		this.windows64 = o.windows64;
		this.windows86 = o.windows86;
	}

	public String getCurrentOS() {
		switch (OperatingSystem.getCurrentPlatform()) {
		case WINDOWS:
			if (!OperatingSystem.getJavaBit().equals(Arch.x64)) {
				return "windows-x86";
			}
			return "windows-x64";
		case OSX:
			return "mac-os";
		case LINUX:
			if (!OperatingSystem.getJavaBit().equals(Arch.x64)) {
				return "linux-i386";
			}
			return "linux";
		default:
			return "unknown";
		}
	}

	public Map<String, List<JavaRuntime>> getCurrentJava() {
		switch (OperatingSystem.getCurrentPlatform()) {
		case WINDOWS:
			if (!OperatingSystem.getJavaBit().equals(Arch.x64)) {
				return windows86;
			}
			return windows64;
		case OSX:
			return macos;
		case LINUX:
			if (!OperatingSystem.getJavaBit().equals(Arch.x64)) {
				return linuxI386;
			}
			return linux;
		default:
			return gamecore;
		}
	}

	public Map<String, List<JavaRuntime>> getGamecore() {
		return gamecore;
	}

	public Map<String, List<JavaRuntime>> getLinux() {
		return linux;
	}

	public Map<String, List<JavaRuntime>> getLinuxI386() {
		return linuxI386;
	}

	public Map<String, List<JavaRuntime>> getMacos() {
		return macos;
	}

	public Map<String, List<JavaRuntime>> getWindows64() {
		return windows64;
	}

	public Map<String, List<JavaRuntime>> getWindows86() {
		return windows86;
	}
}
