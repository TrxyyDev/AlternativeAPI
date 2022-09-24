package fr.trxyy.alternative.alternative_apiv2.minecraft.log4j;

public class Log4jFile {

	public String id;
	public String sha1;
	public int size;
	public String url;

	public Log4jFile() {

	}

	public Log4jFile(Log4jFile o) {
		this.id = o.id;
		this.sha1 = o.sha1;
		this.size = o.size;
		this.url = o.url;
	}

	public String getId() {
		return id;
	}

	public String getSha1() {
		return sha1;
	}

	public int getSize() {
		return size;
	}

	public String getUrl() {
		return url;
	}

}
