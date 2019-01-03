package ganymedes01.etfuturum.blocks;

import ganymedes01.etfuturum.EtFuturum;
import ganymedes01.etfuturum.IConfigurable;
import ganymedes01.etfuturum.ModBlocks;
import ganymedes01.etfuturum.configuration.ConfigurationHandler;
import ganymedes01.etfuturum.core.utils.Utils;
import ganymedes01.etfuturum.tileentities.TileEntityNewBeacon;

import java.util.Random;

import net.minecraft.block.BlockBeacon;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class NewBeacon extends BlockBeacon implements IConfigurable {

	public NewBeacon() {
		setLightLevel(1.0F);
		setBlockTextureName("beacon");
		setBlockName(Utils.getUnlocalisedName("beacon"));
		setCreativeTab(ConfigurationHandler.enableColourfulBeacons ? EtFuturum.creativeTab : null);
	}

	@Override
	public Item getItemDropped(int meta, Random rand, int fortune) {
		if (!ConfigurationHandler.enableTileReplacement)
			return Item.getItemFromBlock(ModBlocks.beacon);
		return Item.getItemFromBlock(Blocks.beacon);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getItem(World world, int x, int y, int z) {
		if (!ConfigurationHandler.enableTileReplacement)
			return Item.getItemFromBlock(ModBlocks.beacon);
		return Item.getItemFromBlock(Blocks.beacon);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityNewBeacon();
	}

	@Override
	public boolean isEnabled() {
		return ConfigurationHandler.enableColourfulBeacons;
	}
}