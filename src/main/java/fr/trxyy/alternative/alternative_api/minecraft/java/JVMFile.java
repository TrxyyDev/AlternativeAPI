package fr.trxyy.alternative.alternative_api.minecraft.java;

public class JVMFile {
	private DownloadJVMFile downloads;

	private boolean executable;
	private String type;
	private String target;

	public JVMFile(JVMFile o) {
		this.executable = o.executable;
		this.type = o.type;
		this.target = o.target;
	}

	public DownloadJVMFile getDownloads() {
		return this.downloads;
	}

	public boolean isExecutable() {
		return this.executable;
	}

	public String getType() {
		return this.type;
	}

	public String getTarget() {
		return this.target;
	}
}
