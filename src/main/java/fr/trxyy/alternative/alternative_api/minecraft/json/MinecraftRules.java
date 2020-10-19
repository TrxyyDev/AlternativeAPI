package fr.trxyy.alternative.alternative_api.minecraft.json;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @author Trxyy
 */
public class MinecraftRules {
	/**
	 * The excludes in a List
	 */
	private List<String> exclude = new ArrayList();

	/**
	 * The Constructor
	 */
	public MinecraftRules() {
	}

	/**
	 * The Constructor
	 * @param exclude The excludes
	 */
	public MinecraftRules(String[] exclude) {
		if (exclude != null) {
			Collections.addAll(this.exclude, exclude);
		}
	}

	/**
	 * The Constructor
	 * @param rules The minecraft rules
	 */
	public MinecraftRules(MinecraftRules rules) {
		Iterator var3 = rules.exclude.iterator();
		while (var3.hasNext()) {
			String exclude = (String) var3.next();
			this.exclude.add(exclude);
		}
	}

	/**
	 * @return The excludes in a List
	 */
	public List<String> getExcludes() {
		return this.exclude;
	}

	public boolean shouldExtract(String path) {
		if (this.exclude == null) {
			return true;
		}
		Iterator var3 = this.exclude.iterator();
		while (var3.hasNext()) {
			String rule = (String) var3.next();
			if (path.startsWith(rule)) {
				return false;
			}
		}
		return true;
	}
}