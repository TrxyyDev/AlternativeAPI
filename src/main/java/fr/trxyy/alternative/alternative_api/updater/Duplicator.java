package fr.trxyy.alternative.alternative_api.updater;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Trxyy
 */
public class Duplicator extends Thread {
	/**
	 * The source file
	 */
	private File source;
	/**
	 * The destination file
	 */
	private File dest;

	/**
	 * The Constructor
	 * @param source The source file
	 * @param dest The destination file
	 */
	public Duplicator(File source, File dest) {
		this.source = source;
		this.dest = dest;
	}

	/**
	 * Run the Thread
	 */
	public void run() {
		try {
			startCloning();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Start Cloning the file in question
	 * @throws IOException
	 */
	private void startCloning() throws IOException {
		InputStream input = null;
		OutputStream output = null;
		try {
			input = new FileInputStream(this.source);
			output = new FileOutputStream(this.dest);
			byte[] buf = new byte[1024];
			int bytesRead;
			while ((bytesRead = input.read(buf)) > 0) {
				output.write(buf, 0, bytesRead);
			}
		} finally {
			input.close();
			output.close();
		}
	}
}