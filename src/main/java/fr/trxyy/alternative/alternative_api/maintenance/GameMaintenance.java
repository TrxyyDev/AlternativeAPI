package fr.trxyy.alternative.alternative_api.maintenance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import fr.trxyy.alternative.alternative_api.GameEngine;

/**
 * @author Trxyy
 */
public class GameMaintenance {

	/**
	 * The Maintenance enum
	 */
	public Maintenance maintenance;
	/**
	 * The GameEngine instance
	 */
	public GameEngine engine;
	/**
	 * Block access to launcher
	 */
	public boolean block_access = false;

	/**
	 * The Constructor
	 * @param enumMaintenance The enum of Maintenance
	 * @param eng The gameEngine instance
	 */
	public GameMaintenance(Maintenance enumMaintenance, GameEngine eng) {
		this.maintenance = enumMaintenance;
		this.engine = eng;
	}

	/**
	 * Read the status.cfg file in url
	 * If "Ok", launcher will continue
	 * If Other text but not "Ok", the launcher will display a Alert with the content of the status.cfg file.
	 * @return The content of the status.cfg
	 * @throws IOException
	 */
	public String readMaintenance() throws IOException {
		URL oracle = new URL(this.engine.getGameLinks().getMaintenanceUrl());
		BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));
		String s = "";
		String inputLine;
		while ((inputLine = in.readLine()) != null) {
			s = inputLine;
		}
		in.close();
		return s;
	}

	/**
	 * @return if is in Maintenance or not
	 */
	public Maintenance getMaintenance() {
		return this.maintenance;
	}

	/**
	 * Set the maintenance
	 * @param maintenance_ The maintenance enum
	 */
	public void setMaintenance(Maintenance maintenance_) {
		this.maintenance = maintenance_;
	}

	/**
	 * @return Is access blocked ?
	 */
	public boolean isAccessBlocked() {
		return block_access;
	}

	/**
	 * Set if the access to the launcher is blocked or not
	 * @param blckd The boolean to set if blocked or not
	 */
	public void setAccessBlocked(boolean blckd) {
		this.block_access = blckd;
	}

}
