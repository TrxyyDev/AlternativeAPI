package fr.trxyy.alternative.alternative_api.minecraft.utils;

import java.util.Map;

/**
 * @author Trxyy
 */
public class CompatibilityRule {
	/**
	 * The Action
	 */
	private Action action;
	/**
	 * The os restriction
	 */
	private OSRestriction os;
	/**
	 * The features as a Map
	 */
	private Map<String, Object> features;

	/**
	 * The Constructor
	 */
	public CompatibilityRule() {
		this.setAction(Action.allow);
	}

	/**
	 * The Constructor
	 * @param rule The rules
	 */
	public CompatibilityRule(CompatibilityRule rule) {
		this.setAction(Action.allow);
		this.setAction(rule.getAction());
		if (rule.os != null) {
			this.setOs(new OSRestriction(rule.getOs()));
		}
		if (rule.features != null) {
			this.features = rule.features;
		}
	}

	/**
	 * toString()
	 */
	public String toString() {
		return "Rule{action=" + this.getAction() + ", os=" + this.getOs() + '}';
	}

	/**
	 * @return The OS restriction
	 */
	public OSRestriction getOs() {
		return os;
	}

	/**
	 * Set the OS
	 * @param os The OS restriction
	 */
	public void setOs(OSRestriction os) {
		this.os = os;
	}

	/**
	 * @return The Action
	 */
	public Action getAction() {
		return action;
	}

	/**
	 * Set the Action
	 * @param action The action
	 */
	public void setAction(Action action) {
		this.action = action;
	}

	/**
	 * @author Trxyy
	 */
	public enum Action {
		allow("allow", 0), disallow("disallow", 1);

		/**
		 * The Action
		 * @param s The name
		 * @param n The number
		 */
		private Action(final String s, final int n) {
		}
	}

	/**
	 * @author Trxyy
	 */
	public static interface FeatureMatcher {
		boolean hasFeature(String param1String, Object param1Object);
	}

	/**
	 * @return The supplied Action
	 */
	public Action getAppliedAction() {
		if ((this.os != null) && (!this.os.isCurrentOperatingSystem())) {
			return null;
		}
		return this.action;
	}
}