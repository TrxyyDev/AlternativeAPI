package fr.trxyy.alternative.alternative_apiv2.minecraft.java;

import fr.trxyy.alternative.alternative_apiv2.minecraft.json.DownloadInfo;

public class JavaRuntime {
	public JavaRuntimeAvailability availability;
	public DownloadInfo manifest;
	public JavaRuntimeVersion version;

	public JavaRuntime() {

	}

	public JavaRuntime(JavaRuntime o) {
		this.availability = o.availability;
		this.manifest = o.manifest;
		this.version = o.version;
	}

	public JavaRuntimeAvailability getAvailability() {
		return availability;
	}

	public DownloadInfo getManifest() {
		return manifest;
	}

	public JavaRuntimeVersion getVersion() {
		return version;
	}
}