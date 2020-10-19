package fr.trxyy.alternative.alternative_api;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Trxyy
 */
public class JVMArguments {

	/**
	 * Custom arguments registered inside a List<String>
	 */
	private List<String> jvmArguments;

	/**
	 * The Constructor
	 * @param args The Arguments
	 */
	public JVMArguments(String[] args) {
		this.jvmArguments = new ArrayList<String>();
		for (int i = 0; i < args.length; i++) {
			this.jvmArguments.add(args[i]);
		}
	}

	/**
	 * @return arguments as a List<String>
	 */
	public List<String> getJVMArguments() {
		return this.jvmArguments;
	}

}
