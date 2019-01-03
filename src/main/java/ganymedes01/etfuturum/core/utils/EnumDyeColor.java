package ganymedes01.etfuturum.core.utils;

/**
 * Literally copied from vanilla 1.10.2
 */

public enum EnumDyeColor {
	WHITE(0, 15, "white", "white"),
	ORANGE(1, 14, "orange", "orange"),
	MAGENTA(2, 13, "magenta", "magenta"),
	LIGHT_BLUE(3, 12, "light_blue", "lightBlue"),
	YELLOW(4, 11, "yellow", "yellow"),
	LIME(5, 10, "lime", "lime"),
	PINK(6, 9, "pink", "pink"),
	GRAY(7, 8, "gray", "gray"),
	SILVER(8, 7, "silver", "silver"),
	CYAN(9, 6, "cyan", "cyan"),
	PURPLE(10, 5, "purple", "purple"),
	BLUE(11, 4, "blue", "blue"),
	BROWN(12, 3, "brown", "brown"),
	GREEN(13, 2, "green", "green"),
	RED(14, 1, "red", "red"),
	BLACK(15, 0, "black", "black");

	private static final EnumDyeColor[] META_LOOKUP = new EnumDyeColor[values().length];
	private static final EnumDyeColor[] DYE_DMG_LOOKUP = new EnumDyeColor[values().length];
	private final int meta;
	private final int dyeDamage;
	private final String name;
	private final String unlocalizedName;

	private EnumDyeColor(int meta, int dyeDamage, String name, String unlocalizedName) {
		this.meta = meta;
		this.dyeDamage = dyeDamage;
		this.name = name;
		this.unlocalizedName = unlocalizedName;
	}

	public int getMetadata() {
		return this.meta;
	}

	public int getDyeDamage() {
		return this.dyeDamage;
	}

	public String getUnlocalizedName() {
		return this.unlocalizedName;
	}

	public static EnumDyeColor byDyeDamage(int damage) {
		if(damage < 0 || damage >= DYE_DMG_LOOKUP.length) {
			damage = 0;
		}

		return DYE_DMG_LOOKUP[damage];
	}

	public static EnumDyeColor byMetadata(int meta) {
		if(meta < 0 || meta >= META_LOOKUP.length) {
			meta = 0;
		}

		return META_LOOKUP[meta];
	}

	public String toString() {
		return this.unlocalizedName;
	}

	public String getName() {
		return this.name;
	}

	static {
		for(EnumDyeColor enumdyecolor : values()) {
			META_LOOKUP[enumdyecolor.getMetadata()] = enumdyecolor;
			DYE_DMG_LOOKUP[enumdyecolor.getDyeDamage()] = enumdyecolor;
		}
	}
}
