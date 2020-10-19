package fr.trxyy.alternative.alternative_api.minecraft.utils;

/**
 * @author Trxyy
 */
public enum Arch {
	x86(32), x64(64), UNKNOWN(0);

	/**
	 * The current Arch
	 */
	public static final Arch CURRENT = getCurrent();
	/**
	 * The minimum memory
	 */
	public static final int MIN_MEMORY = 512;
	/**
	 * The bit
	 */
	private final int bit;
	/**
	 * The arc
	 */
	private final int arch;
	/**
	 * The sBit
	 */
	private final String sBit;
	/**
	 * The sArch
	 */
	private final String sArch;

	/**
	 * The Constructor
	 * @param bit The bit
	 */
	private Arch(int bit) {
		this.bit = bit;
		this.sBit = String.valueOf(bit);
		if (bit == 0) {
			this.sArch = toString();
			this.arch = 0;
		} else {
			this.sArch = toString().substring(1);
			this.arch = Integer.parseInt(this.sArch);
		}
	}
	
	/**
	 * @return Get the sBit
	 */
	public String getBit() {
		return this.sBit;
	}

	/**
	 * @return is Current
	 */
	public boolean isCurrent() {
		return this == CURRENT;
	}

	/**
	 * @return The current Arch
	 */
	private static Arch getCurrent() {
		String curArch = System.getProperty("sun.arch.data.model");
		Arch[] var4;
		int var3 = (var4 = values()).length;
		for (int var2 = 0; var2 < var3; var2++) {
			Arch arch = var4[var2];
			if (arch.sBit.equals(curArch)) {
				return arch;
			}
		}
		return UNKNOWN;
	}
}