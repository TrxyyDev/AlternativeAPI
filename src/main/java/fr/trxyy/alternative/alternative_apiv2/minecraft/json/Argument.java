package fr.trxyy.alternative.alternative_apiv2.minecraft.json;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import fr.trxyy.alternative.alternative_apiv2.minecraft.utils.CompatibilityRule;

/**
 * @author Trxyy
 */
public class Argument {
	/**
	 * The values
	 */
	public final String[] values;
	/**
	 * The compatibility Rules in a List
	 */
	private final List<CompatibilityRule> rules;

	/**
	 * 
	 * @param values
	 * @param compatibilityRules
	 */
	public Argument(String[] values, List<CompatibilityRule> compatibilityRules) {
		this.values = values;
		this.rules = compatibilityRules;
	}

	/**
	 * @return the argument values
	 */
	public String[] getValues() {
		return values;
	}
	
//	public String[] getValues() {
//		
//		for (int i = 0; i < rules.size(); i++) {
//			if (rules.get(i).getOs().isCurrentOperatingSystem()) {
//				Logger.log("RULE ADDED ! " + values);
//				return this.values;
//			}
//		}
//		
//		return new String[] { "NOPE" };
//	}

	/**
	 * @return The Arguments
	 */
//	public String getArguments() {
//		
//		for (int i = 0; i < rules.size(); i++) {
//		if (rules.get(i).getOs().isCurrentOperatingSystem()) {
//			Logger.log("RULE ADDED ! " + values[i]);
//		}
//	}
//		
//		for (String value : this.values) {
//			return value;
//		}
//		return "null";
//	}
	
//	public String getArguments() {
//		for (String value : this.values) {
//			return value;
//		}
//		return "null";
//	}
	
	public String getArguments() {
		if (this.appliesToCurrentEnvironment())
			for (String value : this.values) {
				return value;
			}
		return "";
	}
	
	/**
	 * @return If allow or not compatibility
	 */
	public boolean appliesToCurrentEnvironment() {
		if (this.rules == null)
			return true;
		CompatibilityRule.Action lastAction = CompatibilityRule.Action.DISALLOW;
		for (CompatibilityRule compatibilityRule : this.rules) {
			CompatibilityRule.Action action = compatibilityRule.getAppliedAction();
			if (action != null)
				lastAction = action;
		}
		return (lastAction == CompatibilityRule.Action.ALLOW);
	}
	
	/**
	 * @author Trxyy
	 */
	public static class Serializer implements JsonDeserializer<Argument> {
		public Argument deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException {
			if (json.isJsonPrimitive())
				return new Argument(new String[] { json.getAsString() }, null);
			try {
				if (json.isJsonObject()) {
					String[] values;
					JsonObject obj = json.getAsJsonObject();
					JsonElement value = obj.get("values");
					if (value.isJsonPrimitive()) {
						values = new String[] { value.getAsString() };
					} else {
						JsonArray array = value.getAsJsonArray();
						values = new String[array.size()];
						for (int i = 0; i < array.size(); i++)
							values[i] = array.get(i).getAsString();
					}
					List<CompatibilityRule> rules = new ArrayList<CompatibilityRule>();
					if (obj.has("compatibilityRules")) {
						JsonArray array = obj.getAsJsonArray("compatibilityRules");
						for (JsonElement element : array)
							rules.add((CompatibilityRule) context.deserialize(element, CompatibilityRule.class));
					}
					return new Argument(values, rules);
				}
			} catch (Exception e) {
				if (json.isJsonObject()) {
					String[] values;
					JsonObject obj = json.getAsJsonObject();
					JsonElement value = obj.get("value");
					if (value.isJsonPrimitive()) {
						values = new String[] { value.getAsString() };
					} else {
						JsonArray array = value.getAsJsonArray();
						values = new String[array.size()];
						for (int i = 0; i < array.size(); i++)
							values[i] = array.get(i).getAsString();
					}
					List<CompatibilityRule> rules = new ArrayList<CompatibilityRule>();
					if (obj.has("rules")) {
						JsonArray array = obj.getAsJsonArray("rules");
						for (JsonElement element : array)
							rules.add((CompatibilityRule) context.deserialize(element, CompatibilityRule.class));
					}
					return new Argument(values, rules);
				}
			}
			throw new JsonParseException("Invalid argument, must be object or string");
		}
	}
}
