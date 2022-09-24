package fr.trxyy.alternative.alternative_authv2.microsoft.model;

public class DisplayClaims {
	public Xui[] xui;

	public Xui[] getXui() {
		return xui;
	}
	
	public DisplayClaims(DisplayClaims o) {
		if (o.xui != null) {
			this.xui = o.xui;
		}
	}
	
	public Xui[] getUsers() {
		return this.xui;
	}
	
}
