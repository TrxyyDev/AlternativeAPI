package fr.trxyy.alternative.alternative_api;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Trxyy
 */
public class GameArguments {

	/**
	 * Custom arguments registered inside a List<String>
	 */
	private List<String> gameArguments;

	/**
	 * The Constructor
	 * @param args The Arguments
	 */
	public GameArguments(String[] args) {
		this.gameArguments = new ArrayList<String>();
		for (int i = 0; i < args.length; i++) {
			this.gameArguments.add(args[i]);
		}
	}

	/**
	 * @return arguments as a List<String>
	 */
	public List<String> getGameArguments() {
		return this.gameArguments;
	}

}
