package fr.trxyy.alternative.alternative_apiv2.settings;

public class GameInfos {
	public String resolution;
	public String vmArguments;

	public GameInfos() {}

	public GameInfos(String a, String e) {
		this.resolution = a;
		this.vmArguments = e;
	}

	public GameInfos(GameInfos o) {
		this.resolution = o.resolution;
		this.vmArguments = o.vmArguments;
	}

	public String getResolution() {
		return resolution;
	}
	
	public String getResolutionWidth() {
		String[] parts = this.resolution.split("x");
		return parts[0];
	}
	
	public String getResolutionHeight() {
		String[] parts = this.resolution.split("x");
		return parts[1];
	}

	public void setResolution(String reso) {
		this.resolution = reso;
	}

	public String getVmArguments() {
		return vmArguments;
	}

	public void setVmArguments(String receiveFriendsNotifications) {
		this.vmArguments = receiveFriendsNotifications;
	}
}
