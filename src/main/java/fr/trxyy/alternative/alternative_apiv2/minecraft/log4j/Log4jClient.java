package fr.trxyy.alternative.alternative_apiv2.minecraft.log4j;

public class Log4jClient {

	public String argument;
	public Log4jFile file;
	public String type;

	public Log4jClient() {

	}

	public Log4jClient(Log4jClient o) {
		this.argument = o.argument;
		this.file = o.file;
		this.type = o.type;
	}

	public String getArgument() {
		return argument;
	}

	public Log4jFile getFile() {
		return file;
	}

	public String getType() {
		return type;
	}

}
