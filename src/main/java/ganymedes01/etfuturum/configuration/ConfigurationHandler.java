package ganymedes01.etfuturum.configuration;

import java.io.File;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import ganymedes01.etfuturum.EtFuturum;
import ganymedes01.etfuturum.enchantment.FrostWalker;
import ganymedes01.etfuturum.enchantment.Mending;
import ganymedes01.etfuturum.lib.Reference;
import net.minecraftforge.common.config.Configuration;

public class ConfigurationHandler {

	public static ConfigurationHandler INSTANCE = new ConfigurationHandler();
	public Configuration cfg;
	public String[] usedCategories = { "client", "blocks", "items", "enchants", "entity", "replacement", "function"};
	
	public static boolean enableStones;
	public static boolean enableIronTrapdoor;
	public static boolean enableMutton;
	public static boolean enableSponge;
	public static boolean enablePrismarine;
	public static boolean enableDoors;
	public static boolean enableInvertedDaylightSensor;
	public static boolean enableCoarseDirt;
	public static boolean enableRedSandstone;
	public static boolean enableEnchants;
	public static boolean enableAnvil;
	public static boolean enableFences;
	public static boolean enableSilkTouchingMushrooms;
	public static boolean enableBanners;
	public static boolean enableSlimeBlock;
	public static boolean enableArmourStand;
	public static boolean enableRabbit;
	public static boolean enableOldGravel;
	public static boolean enableRecipeForPrismarine;
	public static boolean enableEndermite;
	public static boolean enableBeetroot;
	public static boolean enableChorusFruit;
	public static boolean enableGrassPath;
	public static boolean enableSticksFromDeadBushes;
	public static boolean enableBowRendering;
	public static boolean enableTippedArrows;
	public static boolean enableLingeringPotions;
	public static boolean enableBurnableBlocks;
	public static boolean enableFancySkulls;
	public static boolean enableSkullDrop;
	public static boolean enableDmgIndicator;
	public static boolean enableTransparentAmour;
	public static boolean enableCryingObsidian;
	public static boolean enableUpdatedFoodValues;
	public static boolean enableUpdatedHarvestLevels;
	public static boolean enableVillagerZombies;
	public static boolean enableStoneBrickRecipes;
	public static boolean enableBabyGrowthBoost;
	public static boolean enableVillagerTurnsIntoWitch;
	public static boolean enableElytra;
	public static boolean enableFrostWalker;
	public static boolean enableMending;
	public static boolean enableBrewingStands;
	public static boolean enableDragonRespawn;
	public static boolean enableRoses;
	public static boolean enableColourfulBeacons;
	public static boolean enablePlayerSkinOverlay;
	public static boolean enableShearableGolems;
	public static boolean enableShearableCobwebs;
	
	public static boolean enableNetherBlocks;
	public static boolean enableBoneBlock;
	public static boolean enableConcrete;
	
	public static boolean enableHusk;
	public static boolean enableStray;
	public static boolean enableNetherEndermen;
	public static boolean enableTotemUndying;
	public static boolean enableRecipeForTotem;
	
	public static boolean enableWoodRedstone;
	public static boolean enableStrippedLogs;
	public static boolean enableBarkLogs;
	
	public static boolean enableTileReplacement;

	public static int maxStonesPerCluster = 33;

	/*
	private int configInteger(String name, boolean requireRestart, int def) {
		return configInteger(name, null, requireRestart, def);
	}

	private int configInteger(String name, String tooltip, boolean requireRestart, int def) {
		int config = configFile.get(Configuration.CATEGORY_GENERAL, name, def, tooltip).getInt(def);
		return config >= 0 ? config : def;
	}

	private boolean configBoolean(String name, String tooltip, boolean requireRestart, boolean def) {
		return configFile.get(Configuration.CATEGORY_GENERAL, name, def, tooltip).getBoolean(def);
	}

	private boolean configBoolean(String name, boolean requireRestart, boolean def) {
		return configBoolean(name, null, requireRestart, def);
	}
	
	configFile.get("general", "", true, null).getBoolean(true);
	
	*/

	public void init(File file) {
		cfg = new Configuration(file);

		syncConfigs();
	}

	private void syncConfigs() {
		//cfg.getBoolean("", "general", true, null);
		//cfg.getInt("", "general", 32, 0, 64, null);
		
		//blocks
		enableStones = cfg.getBoolean("enableStones", "blocks", true, "Enable Granite/Andesite/Diorite");//configBoolean("", true, EtFuturum.enableStones);
		maxStonesPerCluster = cfg.getInt("stoneClusterSize", "blocks", 32, 0, 64, "Max number of Granite/Andesite/Diorite/Magma blocks in a cluster");//configInteger("Max number of Granite/Andesite/Diorite/Magma blocks in a cluster", true, EtFuturum.maxStonesPerCluster);
		
		enableIronTrapdoor = cfg.getBoolean("enableIronTrapdoor", "blocks", true, "");//configBoolean("Iron Trapdoor", true, EtFuturum.enableIronTrapdoor);
		enableSponge = cfg.getBoolean("enableSponge", "blocks", true, "");//configBoolean("Sponge", true, EtFuturum.enableSponge);
		enablePrismarine = cfg.getBoolean("enablePrismarine", "blocks", true, "");//configBoolean("Prismarine", true, EtFuturum.enablePrismarine);
		enableDoors = cfg.getBoolean("enableDoors", "blocks", true, "Enables wood variant doors");//configBoolean("Doors", true, EtFuturum.enableDoors);
		enableInvertedDaylightSensor = cfg.getBoolean("enableInvertedSensor", "blocks", true, "Inverted Daylight Sensor");//configBoolean("Inverted Daylight Sensor", true, EtFuturum.enableInvertedDaylightSensor);
		enableCoarseDirt = cfg.getBoolean("enableCoarseDirt", "blocks", true, "");//configBoolean("Coarse Dirt", true, EtFuturum.enableCoarseDirt);
		enableRedSandstone = cfg.getBoolean("enableRedSandstone", "blocks", true, "");//configBoolean("Red Sandstone", true, EtFuturum.enableRedSandstone);
		enableFences = cfg.getBoolean("enableFences", "blocks", true, "Enables wood variant fences and gates");//configBoolean("Fences and Gates", true, EtFuturum.enableFences);
		enableBanners = cfg.getBoolean("enableBanners", "blocks", true, "");//configBoolean("Banners", true, EtFuturum.enableBanners);
		enableSlimeBlock = cfg.getBoolean("enableSlimeBlock", "blocks", true, "Just bouncy, does not pull blocks.");//configBoolean("Slime Block", true, EtFuturum.enableSlimeBlock);
		enableOldGravel = cfg.getBoolean("enableOldGravel", "blocks", true, "");//configBoolean("Old Gravel", true, EtFuturum.enableOldGravel);
		enableChorusFruit = cfg.getBoolean("enableChorusBlocks", "blocks", true, "Enables chorus plants and purpur blocks");//configBoolean("Chorus Fruit (and related blocks)", true, EtFuturum.enableChorusFruit);
		enableGrassPath = cfg.getBoolean("enableGrassPath", "blocks", true, "");//configBoolean("Grass Path", true, EtFuturum.enableGrassPath);
		enableCryingObsidian = cfg.getBoolean("enableCryingObsidian", "blocks", true, "");//configBoolean("Crying Obsidian", true, EtFuturum.enableCryingObsidian);
		enableRoses = cfg.getBoolean("enableOldRoses", "blocks", true, "");//configBoolean("Old Roses", true, EtFuturum.enableRoses);
		
		enableNetherBlocks = cfg.getBoolean("enableNetherBlocks", "blocks", true, "Enables magma/netherwart blocks and red netherbrick");//configBoolean("Enable magma/netherwart blocks and red netherbrick", true, EtFuturum.enableNetherBlocks);
		enableBoneBlock = cfg.getBoolean("enableBoneBlock", "blocks", true, "");//configBoolean("Enable bone block", true, EtFuturum.enableBoneBlock);
		enableConcrete = cfg.getBoolean("enableConcrete", "blocks", true, "");//configBoolean("Enable concrete", true, EtFuturum.enableConcrete);
		
		enableWoodRedstone = cfg.getBoolean("enableWoodRedstone", "blocks", true, "Enables wood variant buttons and pressure plates");//
		enableStrippedLogs = cfg.getBoolean("enableStrippedLogs", "blocks", true, "Enables stripped log blocks");//
		enableBarkLogs = cfg.getBoolean("enableBarkLogs", "blocks", true, "Enables log blocks with bark on all sides");//
		
		//items
		enableMutton = cfg.getBoolean("enableMutton", "items", true, "");//configBoolean("Mutton", true, EtFuturum.enableMutton);
		enableBeetroot = cfg.getBoolean("enableBeetroot", "items", true, "");//configBoolean("Beetroot", true, EtFuturum.enableBeetroot);
		enableElytra = cfg.getBoolean("enableElytra", "items", true, "");//configBoolean("Elytra", true, EtFuturum.enableElytra);
		enableTippedArrows = cfg.getBoolean("enableTippedArrows", "items", true, "");//configBoolean("Tipped Arrows", true, EtFuturum.enableTippedArrows);
		enableLingeringPotions = cfg.getBoolean("enableLingeringPotions", "items", true, "");//configBoolean("Lingering Potions", true, EtFuturum.enableLingeringPotions);
		
		enableTotemUndying = cfg.getBoolean("enableTotemUndying", "items", true, "");
		
		//enchants
		enableFrostWalker = cfg.getBoolean("frostWalker", "enchants", true, "");//configBoolean("Frost Walker", true, EtFuturum.enableFrostWalker);
		FrostWalker.ID = cfg.getInt("frostWalkerID", "enchants", 36, 0, 1023, "");//configInteger("Frost Walker ID", true, FrostWalker.ID);
		
		enableMending = cfg.getBoolean("mending", "enchants", true, "");//configBoolean("Mending", true, EtFuturum.enableMending);
		Mending.ID = cfg.getInt("mendingID", "enchants", 37, 0, 1023, "");//configInteger("Mending ID", true, Mending.ID);
		//configFile.get("enchantment", "", true, null).getBoolean(true);
		//configFile.get("enchantment", "", def, null).getInt(def);
		
		//mobs
		enableRabbit = cfg.getBoolean("enableRabbits", "entity", true, "");//configBoolean("Rabbits", true, EtFuturum.enableRabbit);
		enableArmourStand = cfg.getBoolean("enableArmorStand", "entity", true, "");//configBoolean("Armour Stand", true, EtFuturum.enableArmourStand);
		enableEndermite = cfg.getBoolean("enableEndermite", "entity", true, "");//configBoolean("Endermite", true, EtFuturum.enableEndermite);
		enableVillagerZombies = cfg.getBoolean("enableZombieVillager", "entity", true, "");//configBoolean("Villager Zombies", true, EtFuturum.enableVillagerZombies);
		
		enableHusk = cfg.getBoolean("enableHusks", "entity", true, "Desert zombie variant");//configBoolean("Husks", true, EtFuturum.enableConcrete);
		enableStray = cfg.getBoolean("enableStrays", "entity", true, "Tundra skeleton variant");//configBoolean("Strays", true, EtFuturum.enableConcrete);
		
		//function
		enableSilkTouchingMushrooms = cfg.getBoolean("enableSilkMushroom", "function", true, "Mushroom blocks can be silk-touched");//configBoolean("Mushroom Blocks", true, EtFuturum.enableSilkTouchingMushrooms);
		enableRecipeForPrismarine = cfg.getBoolean("enablePrismarineRecipes", "function", true, "");//configBoolean("Recipes for prismarine", true, EtFuturum.enableRecipeForPrismarine);
		enableSticksFromDeadBushes = cfg.getBoolean("enableBushSticks", "function", true, "Dead Bushes drop sticks");//configBoolean("Dead Bushes drop sticks", true, EtFuturum.enableSticksFromDeadBushes);
		enableSkullDrop = cfg.getBoolean("enableSkullDrop", "function", true, "Skulls drop from charged creeper kills");//configBoolean("Skulls drop from charged creeper kills", true, EtFuturum.enableSkullDrop);
		enableBurnableBlocks = cfg.getBoolean("enableBurnables", "function", true, "Fences, gates and dead bushes burn");//configBoolean("Fences, gates and dead bushes burn", true, EtFuturum.enableBurnableBlocks);
		enableUpdatedFoodValues = cfg.getBoolean("enableUpdatedFood", "function", true, "Use updated food values");//configBoolean("Use updated food values", true, EtFuturum.enableUpdatedFoodValues);
		enableUpdatedHarvestLevels = cfg.getBoolean("enableUpdatedHarvestLevels", "function", true, "Packed Ice, ladders and melons have preferred tools");//configBoolean("Use updated harvest levels", true, EtFuturum.enableUpdatedHarvestLevels);
		enableShearableGolems = cfg.getBoolean("enableShearableSnowGolems", "function", true, "");//configBoolean("Shearing Snow Golems", true, EtFuturum.enableShearableGolems);
		enableShearableCobwebs = cfg.getBoolean("enableShearableCobwebs", "function", true, "");//configBoolean("Shears harvest cobwebs", true, EtFuturum.enableShearableCobwebs);
		enableStoneBrickRecipes = cfg.getBoolean("enableStoneBrickRecipes", "function", true, "Makes mossy, cracked and chiseled stone brick craftable");//configBoolean("Stone Brick Recipes", true, EtFuturum.enableStoneBrickRecipes);
		enableBabyGrowthBoost = cfg.getBoolean("enableBabyGrowthBoost", "function", true, "");//configBoolean("Baby growth boost", true, EtFuturum.enableBabyGrowthBoost);
		enableVillagerTurnsIntoWitch = cfg.getBoolean("enableVillagerTurnsIntoWitch", "function", true, "Villagers turn into Witches when struck by lightning");//configBoolean("Villagers turn into Witches when struck by lightning", true, EtFuturum.enableVillagerTurnsIntoWitch);
		
		enableDragonRespawn = cfg.getBoolean("enableDragonRespawn", "function", true, "");//configBoolean("Dragon respawning", true, EtFuturum.enableDragonRespawn);
		enableNetherEndermen = cfg.getBoolean("enableNetherEndermen", "function", true, "Allow endermen to rarely spawn in the Nether");
		//enableRecipeForTotem = cfg.getBoolean("enableTotemRecipe", "function", false, "");
		
		//replacement
		enableTileReplacement = cfg.getBoolean("enableTileReplacement", "replacement", false, "Replace old Brewing Stands/Enchanting Tables/Daylight Sensors/Beacons with new one on the fly. Very update tick intensive.");//configBoolean("Replace old Brewing Stands/Enchanting Tables/Daylight Sensors/Beacons with new one on the fly", false, EtFuturum.enableTileReplacement);
		
		enableEnchants = cfg.getBoolean("enableEnchantingTable", "replacement", true, "");//configBoolean("Enchanting Table", true, EtFuturum.enableEnchants);
		enableAnvil = cfg.getBoolean("enableAnvil", "replacement", true, "");//configBoolean("Anvil", true, EtFuturum.enableAnvil);
		enableBrewingStands = cfg.getBoolean("enableBrewingStand", "replacement", true, "");//configBoolean("Brewing Stands", true, EtFuturum.enableBrewingStands);
		enableColourfulBeacons = cfg.getBoolean("enableBeacon", "replacement", true, "Beacon beam can be colored using stained glass");//configBoolean("Colourful Beacon Beams", true, EtFuturum.enableColourfulBeacons);
		
		//client
		enableDmgIndicator = cfg.getBoolean("enableDmgIndicator", "client", true, "Heart Damage Indicator");//configBoolean("Heart Damage Indicator", true, EtFuturum.enableDmgIndicator);
		enableTransparentAmour = cfg.getBoolean("enableTransparentAmour", "client", true, "Allow non-opaque armour");//configBoolean("Allow non-opaque armour", true, EtFuturum.enableTransparentAmour);
		enableBowRendering = cfg.getBoolean("enableBowRendering", "client", true, "Bows render pulling animation on inventory");//configBoolean("Bows render pulling animation on inventory", true, EtFuturum.enableBowRendering);
		enableFancySkulls = cfg.getBoolean("enableFancySkulls", "client", true, "Skulls render 3D in inventory");//configBoolean("Fancy Skulls", true, EtFuturum.enableFancySkulls);
		enablePlayerSkinOverlay = cfg.getBoolean("enablePlayerSkinOverlay", "client", false, "Allows use of 1.8 skin format. Disable if skin is displaying oddly.");//configBoolean("Skin overlays", true, EtFuturum.enablePlayerSkinOverlay);
		
		
		if (cfg.hasChanged())
			cfg.save();
	}

	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
		if (Reference.MOD_ID.equals(eventArgs.modID))
			syncConfigs();
	}
}
