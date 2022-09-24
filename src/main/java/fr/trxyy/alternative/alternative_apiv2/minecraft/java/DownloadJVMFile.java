package fr.trxyy.alternative.alternative_apiv2.minecraft.java;

import fr.trxyy.alternative.alternative_apiv2.minecraft.json.DownloadInfo;

public class DownloadJVMFile {
	private DownloadInfo lzma;
	private DownloadInfo raw;

	public DownloadJVMFile(DownloadJVMFile o) {
		this.lzma = o.lzma;
		this.raw = o.raw;
	}

	public DownloadInfo getLzma() {
		return this.lzma;
	}

	public DownloadInfo getRaw() {
		return this.raw;
	}
}
