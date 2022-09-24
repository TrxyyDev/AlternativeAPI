package fr.trxyy.alternative.alternative_apiv2.minecraft.json;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class VersionsLister {
	public ArrayList<Version> versions = new ArrayList<Version>();

	public ArrayList<Version> getVersions() {
		return versions;
	}

	public Version getLastVersion() {
		return versions.get(0);
	}

	public class Version {

		public String id;
		public String type;
		public String url;
		public String time;
		public String releaseTime;
		
		public Version() {
		}
		
		public Version(String id_, String type_, String url_, String time_, String releaseTime_) {
			this.id = id_;
			this.type = type_;
			this.url = url_;
			this.time = time_;
			this.releaseTime = releaseTime_;
		}

		public String getId() {
			return id;
		}

		public String getType() {
			return type;
		}

		public String getUrl() {
			return url;
		}

		public String getTime() {
			return time;
		}
		
		public String getTimeSimple() {
			Calendar calendar = javax.xml.bind.DatatypeConverter.parseDateTime(time);
			Date date = calendar.getTime();
			SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy à HH:mm");
			String formatDateTime = format1.format(date);
			return formatDateTime;
		}

		public String getReleaseTime() {
			return releaseTime;
		}

	}

}