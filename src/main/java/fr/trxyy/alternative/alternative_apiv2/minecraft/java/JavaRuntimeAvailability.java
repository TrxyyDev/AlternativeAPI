package fr.trxyy.alternative.alternative_apiv2.minecraft.java;

public class JavaRuntimeAvailability {
	public int group;
	public int progress;

	public JavaRuntimeAvailability(JavaRuntimeAvailability o) {
		this.group = o.group;
		this.progress = o.progress;
	}

	public int getGroup() {
		return group;
	}

	public int getProgress() {
		return progress;
	}
}