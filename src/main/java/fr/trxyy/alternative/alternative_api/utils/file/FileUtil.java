package fr.trxyy.alternative.alternative_api.utils.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import fr.trxyy.alternative.alternative_api.GameEngine;

/**
 * @author Trxyy
 */
public class FileUtil {

	/**
	 * Skip folders in extractions
	 */
	public static String skipFoldersInExtraction = "META-INF/";

	/**
	 * Delete fake natives
	 * @param targetDir The target directory to delete files
	 * @param engine The GameEngine instance
	 * @throws IOException
	 */
	public static void deleteFakeNatives(File targetDir, GameEngine engine) throws IOException {
		File[] listOfFiles = engine.getGameFolder().getNativesDir().listFiles();
		for (int index = 0; index < listOfFiles.length; index++) {
			if (listOfFiles[index].isFile()) {
				if (!listOfFiles[index].getName().endsWith(".dll")) {
					if (!listOfFiles[index].getName().endsWith(".dylib")) {
						if (!listOfFiles[index].getName().endsWith(".so")) {
							listOfFiles[index].delete();
						}
					}
				}
			} else {
				deleteFolder(listOfFiles[index]);
			}
		}
	}

	/**
	 * Unpack natives in a designed folder
	 * @param targetDir The target directory
	 * @param engine The GameEngine instance
	 * @throws IOException
	 */
	public static void unpackNatives(File targetDir, GameEngine engine) throws IOException {
		File[] listOfFiles = engine.getGameFolder().getNativesCacheDir().listFiles();
		for (int index = 0; index < listOfFiles.length; index++) {
			if (listOfFiles[index].isFile()) {
				ZipFile zip = new ZipFile(listOfFiles[index]);
				try {
					Enumeration<? extends ZipEntry> entries = zip.entries();
					while (entries.hasMoreElements()) {
						ZipEntry entry = (ZipEntry) entries.nextElement();
						File targetFile = new File(targetDir, entry.getName());
						if (targetFile.getParentFile() != null) {
							targetFile.getParentFile().mkdirs();
						}
						if (!entry.isDirectory()) {
							BufferedInputStream inputStream = new BufferedInputStream(zip.getInputStream(entry));

							byte[] buffer = new byte[2048];
							FileOutputStream outputStream = new FileOutputStream(targetFile);
							BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
							try {
								int length;
								while ((length = inputStream.read(buffer, 0, buffer.length)) != -1) {
									bufferedOutputStream.write(buffer, 0, length);
								}
							} finally {
								closeSilently(bufferedOutputStream);
								closeSilently(outputStream);
								closeSilently(inputStream);
							}
						}
					}
				} finally {
					zip.close();
				}
			}
		}
	}

	/**
	 * Force delete on exit
	 * @param file The file in question
	 * @throws IOException
	 */
	public static void forceDeleteOnExit(File file) throws IOException {
		if (file.isDirectory()) {
			deleteDirectoryOnExit(file);
		} else {
			file.deleteOnExit();
		}
	}

	/**
	 * Delete a directory on exit
	 * @param directory The directory in question
	 * @throws IOException
	 */
	private static void deleteDirectoryOnExit(File directory) throws IOException {
		if (!directory.exists()) {
			return;
		}
		directory.deleteOnExit();
		if (!isSymlink(directory)) {
			cleanDirectoryOnExit(directory);
		}
	}

	/**
	 * 
	 * @param file The File
	 * @return
	 * @throws IOException
	 */
	public static boolean isSymlink(File file) throws IOException {
		if (file == null) {
			throw new NullPointerException("File must not be null");
		}
		char WINDOWS_SEPARATOR = '\\';
		if (File.separatorChar == WINDOWS_SEPARATOR) {
			return false;
		}
		File fileInCanonicalDir = null;
		if (file.getParent() == null) {
			fileInCanonicalDir = file;
		} else {
			File canonicalDir = file.getParentFile().getCanonicalFile();
			fileInCanonicalDir = new File(canonicalDir, file.getName());
		}
		if (fileInCanonicalDir.getCanonicalFile().equals(fileInCanonicalDir.getAbsoluteFile())) {
			return false;
		}
		return true;
	}

	/**
	 * Clean a directory on exit
	 * @param directory The directory to clean
	 * @throws IOException
	 */
	private static void cleanDirectoryOnExit(File directory) throws IOException {
		if (!directory.exists()) {
			String message = directory + " does not exist";
			throw new IllegalArgumentException(message);
		}
		if (!directory.isDirectory()) {
			String message = directory + " is not a directory";
			throw new IllegalArgumentException(message);
		}
		File[] files = directory.listFiles();
		if (files == null) {
			throw new IOException("Failed to list contents of " + directory);
		}
		IOException exception = null;
		for (File file : files) {
			try {
				forceDeleteOnExit(file);
			} catch (IOException ioe) {
				exception = ioe;
			}
		}
		if (null != exception) {
			throw exception;
		}
	}

	/**
	 * @return The Charset
	 */
	public static Charset getCharset() {
		try {
			return Charset.forName("UTF-8");
		} catch (Exception var1) {
			throw new Error("UTF-8 is not supported", var1);
		}
	}

	/**
	 * @param file The file
	 * @param algorithm The algorithm to use
	 * @param hashLength The hash lenght
	 * @return A new String
	 */
	public static String getDigest(File file, String algorithm, int hashLength) {
		DigestInputStream stream = null;
		try {
			stream = new DigestInputStream(new FileInputStream(file), MessageDigest.getInstance(algorithm));
			byte[] ignored = new byte[65536];
			int read;
			do {
				read = stream.read(ignored);
			} while (read > 0);
			return String.format("%1$0" + hashLength + "x",
					new Object[] { new BigInteger(1, stream.getMessageDigest().digest()) });
		} catch (Exception localException) {
		} finally {
			close(stream);
		}
		return null;
	}

	/**
	 * Close
	 * @param a The Closeable
	 */
	private static void close(Closeable a) {
		try {
			a.close();
		} catch (Exception var2) {
			var2.printStackTrace();
		}
	}

	public static String getSHA(File file) {
		return getDigest(file, "SHA", 40);
	}

	/**
	 * @param file The File
	 * @param sha1 The Sha1
	 * @return True if the file Md5 Match with the Sha1 String
	 */
	public static boolean matchSHA1(final File file, final String sha1) {
		try {
			return getSHA(file).equals(sha1);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Close silently 
	 * @param closeable The Closeable
	 */
	public static void closeSilently(Closeable closeable) {
		if (closeable != null) {
			try {
				closeable.close();
			} catch (IOException localIOException) {
			}
		}
	}

	/**
	 * Copy a file from mc directory to our directory
	 * @param mc The mc folder
	 * @param local The directory in question
	 * @throws IOException
	 */
	public static void copy(File mc, File local) throws IOException {
		InputStream input = null;
		OutputStream output = null;
		try {
			input = new FileInputStream(mc);
			output = new FileOutputStream(local);
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

	/**
	 * Delete a folder
	 * @param folder The folder in question
	 */
	public static void deleteFolder(File folder) {
		File[] files = folder.listFiles();
		if (files != null) { // some JVMs return null for empty dirs
			for (File f : files) {
				if (f.isDirectory()) {
					deleteFolder(f);
				} else {
					f.delete();
				}
			}
		}
		folder.delete();
	}

	/**
	 * Delete something
	 * @param path The pathto the file to delete
	 */
	public static void deleteSomething(String path) {
		Path filePath_1 = Paths.get(path);
		try {
			Files.delete(filePath_1);
		} catch (NoSuchFileException x) {
			System.err.format("%s: no such" + " file or directory%n", path);
		} catch (DirectoryNotEmptyException x) {
			System.err.format("%s not empty%n", path);
		} catch (IOException x) {
			System.err.println(x);
		}
	}

	/**
	 * @param etag The etag
	 * @return The etag as a String
	 */
	public static String getEtag(String etag) {
		if (etag == null)
			etag = "-";
		else if (etag.startsWith("\"") && etag.endsWith("\""))
			etag = etag.substring(1, etag.length() - 1);

		return etag;
	}

	/**
	 * @param file The file to get the MD5
	 * @return The MD5 as a String
	 */
	public static String getMD5(final File file) {
		DigestInputStream stream = null;
		try {
			stream = new DigestInputStream(new FileInputStream(file), MessageDigest.getInstance("MD5"));
			final byte[] buffer = new byte[65536];

			int read = stream.read(buffer);
			while (read >= 1)
				read = stream.read(buffer);
		} catch (final Exception ignored) {
			return null;
		} finally {
			closeSilently(stream);
		}

		return String.format("%1$032x", new Object[] { new BigInteger(1, stream.getMessageDigest().digest()) });
	}
	
	/**
	 * @param url The URL to read
	 * @return The result of the url
	 */
	public static String readMD5(String url) {
		String result = "";
		try {
			Scanner scan = new Scanner((new URL(url)).openStream(), "UTF-8");
			if (!scan.hasNextLine()) {
				scan.close();
			}
			result = scan.nextLine();
			scan.close();
			return result;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}
