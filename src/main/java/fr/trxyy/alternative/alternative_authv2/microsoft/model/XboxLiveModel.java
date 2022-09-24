package fr.trxyy.alternative.alternative_authv2.microsoft.model;

public class XboxLiveModel {
	
	public String IssueInstant;
	public String NotAfter;
	public String Token;
	public DisplayClaims DisplayClaims;
	
	public XboxLiveModel(XboxLiveModel o) {
		if (o.IssueInstant != null) {
			this.IssueInstant = o.IssueInstant;
		}
		if (o.NotAfter != null) {
			this.NotAfter = o.NotAfter;
		}
		if (o.Token != null) {
			this.Token = o.Token;
		}
		if (o.DisplayClaims != null) {
			this.DisplayClaims = o.DisplayClaims;
		}
	}
	
	public String getIssueInstant() {
		return IssueInstant;
	}
	public String getNotAfter() {
		return NotAfter;
	}
	public String getToken() {
		return Token;
	}
	public DisplayClaims getDisplayClaims() {
		return DisplayClaims;
	}
	
}
