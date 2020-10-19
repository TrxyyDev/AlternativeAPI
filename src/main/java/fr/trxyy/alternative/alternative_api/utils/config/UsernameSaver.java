package fr.trxyy.alternative.alternative_api.utils.config;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import fr.trxyy.alternative.alternative_api.GameEngine;

/**
 * @author Trxyy
 */
public class UsernameSaver {

	/**
	 * The username
	 */
	public String userName;
	/**
	 * The lastlogin file
	 */
	public File lastLogin;
	/**
	 * The GameEngine instance
	 */
	public GameEngine gameEngine;

	/**
	 * The Cnstructor
	 * @param engine The GameEngine instance
	 */
	public UsernameSaver(GameEngine engine) {
		this.gameEngine = engine;
		this.lastLogin = new File(this.gameEngine.getGameFolder().getBinDir(), "lastlogin.cfg");
		if (!this.lastLogin.exists()) {
			try {
				this.lastLogin.createNewFile();
				this.writeUsername("");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		readUsername();
	}

	/**
	 * Read the username from the lastlogin file
	 */
	public void readUsername() {
		try {
			DataInputStream dis;
			Cipher cipher = getCipher(2, "passwordfile");
			if (cipher != null) {
				dis = new DataInputStream(new CipherInputStream(new FileInputStream(this.lastLogin), cipher));
			} else {
				dis = new DataInputStream(new FileInputStream(this.lastLogin));
			}
			this.userName = dis.readUTF();
			dis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Write the username in the lastlogin file
	 * @param name The username to write
	 */
	public void writeUsername(String name) {
		try {
			DataOutputStream dos;
			Cipher cipher = getCipher(1, "passwordfile");
			if (cipher != null) {
				dos = new DataOutputStream(new CipherOutputStream(new FileOutputStream(this.lastLogin), cipher));
			} else {
				dos = new DataOutputStream(new FileOutputStream(this.lastLogin));
			}
			dos.writeUTF(name);
			dos.writeUTF("");
			dos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return The username
	 */
	public String getUsername() {
		return this.userName;
	}

	/**
	 * @param mode The mode
	 * @param password The password
	 * @return The Cipher
	 * @throws Exception
	 */
	public Cipher getCipher(int mode, String password) throws Exception {
		Random random = new Random(43287234L);
		byte[] salt = new byte[8];
		random.nextBytes(salt);
		PBEParameterSpec pbeParamSpec = new PBEParameterSpec(salt, 5);

		SecretKey pbeKey = SecretKeyFactory.getInstance("PBEWithMD5AndDES")
				.generateSecret(new PBEKeySpec(password.toCharArray()));
		Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES");
		cipher.init(mode, pbeKey, pbeParamSpec);
		return cipher;
	}

	/**
	 * @return The GameEngine instance
	 */
	public GameEngine getGameEngine() {
		return gameEngine;
	}

}
