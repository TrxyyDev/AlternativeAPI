package fr.trxyy.alternative.alternative_apiv2.minecraft.log4j;

public class Log4jLogging {

	public Log4jClient client;

	public Log4jLogging() {

	}

	public Log4jLogging(Log4jLogging o) {
		this.client = o.client;
	}

	public Log4jClient getClient() {
		return this.client;
	}
}
