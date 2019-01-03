package ganymedes01.etfuturum.items;

import ganymedes01.etfuturum.EtFuturum;
import ganymedes01.etfuturum.IConfigurable;
import ganymedes01.etfuturum.ModBlocks;
import ganymedes01.etfuturum.configuration.ConfigurationHandler;
import ganymedes01.etfuturum.core.utils.Utils;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;

public class BeetrootSeeds extends ItemSeeds implements IConfigurable {

	public BeetrootSeeds() {
		super(ModBlocks.beetroot, Blocks.farmland);
		setTextureName("beetroot_seeds");
		setUnlocalizedName(Utils.getUnlocalisedName("beetroot_seeds"));
		setCreativeTab(ConfigurationHandler.enableBeetroot ? EtFuturum.creativeTab : null);

		if (ConfigurationHandler.enableBeetroot) {
			ChestGenHooks.addItem(ChestGenHooks.MINESHAFT_CORRIDOR, new WeightedRandomChestContent(new ItemStack(this), 1, 2, 5));
			ChestGenHooks.addItem(ChestGenHooks.PYRAMID_DESERT_CHEST, new WeightedRandomChestContent(new ItemStack(this), 1, 2, 5));
			ChestGenHooks.addItem(ChestGenHooks.PYRAMID_JUNGLE_CHEST, new WeightedRandomChestContent(new ItemStack(this), 1, 2, 5));
			ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_CORRIDOR, new WeightedRandomChestContent(new ItemStack(this), 1, 2, 5));
			ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_LIBRARY, new WeightedRandomChestContent(new ItemStack(this), 1, 2, 5));
			ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_CROSSING, new WeightedRandomChestContent(new ItemStack(this), 1, 2, 5));
			ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new WeightedRandomChestContent(new ItemStack(this), 1, 2, 5));
		}
	}

	@Override
	public boolean isEnabled() {
		return ConfigurationHandler.enableBeetroot;
	}
}