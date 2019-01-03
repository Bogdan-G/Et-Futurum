package ganymedes01.etfuturum.blocks;

import ganymedes01.etfuturum.EtFuturum;
import ganymedes01.etfuturum.IConfigurable;
import ganymedes01.etfuturum.ModBlocks;
import ganymedes01.etfuturum.configuration.ConfigurationHandler;
import ganymedes01.etfuturum.core.utils.Utils;
import ganymedes01.etfuturum.lib.GUIsID;

import java.util.Random;

import net.minecraft.block.BlockEnchantmentTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class NewEnchantmentTable extends BlockEnchantmentTable implements IConfigurable {

	public NewEnchantmentTable() {
		setHardness(5.0F);
		setResistance(2000.0F);
		setBlockTextureName("enchanting_table");
		setBlockName(Utils.getUnlocalisedName("enchantment_table"));
		setCreativeTab(ConfigurationHandler.enableEnchants ? EtFuturum.creativeTab : null);
	}

	@Override
	public Item getItemDropped(int meta, Random rand, int fortune) {
		if (!ConfigurationHandler.enableTileReplacement)
			return Item.getItemFromBlock(ModBlocks.enchantment_table);
		return Item.getItemFromBlock(Blocks.enchanting_table);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getItem(World world, int x, int y, int z) {
		if (!ConfigurationHandler.enableTileReplacement)
			return Item.getItemFromBlock(ModBlocks.enchantment_table);
		return Item.getItemFromBlock(Blocks.enchanting_table);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (world.isRemote)
			return true;
		else {
			player.openGui(EtFuturum.instance, GUIsID.ENCHANTING_TABLE, world, x, y, z);
			return true;
		}
	}

	@Override
	public boolean isEnabled() {
		return ConfigurationHandler.enableEnchants;
	}
}