package ganymedes01.etfuturum.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ganymedes01.etfuturum.EtFuturum;
import ganymedes01.etfuturum.ModBlocks;
import ganymedes01.etfuturum.configuration.ConfigurationHandler;
import ganymedes01.etfuturum.core.utils.Utils;
import net.minecraft.block.material.Material;
import net.minecraft.util.IIcon;

public class PrismarineSlabBrick extends GenericSlab {

	public PrismarineSlabBrick() {
		super(Material.rock, ModBlocks.prismarine);
		setHardness(1.5F);
		setResistance(10.0F);
		setBlockName(Utils.getUnlocalisedName("prismarine_slab_brick"));
		setCreativeTab(ConfigurationHandler.enablePrismarine ? EtFuturum.creativeTab : null);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return base.getIcon(side, 1);
	}

	@Override
	public boolean isEnabled() {
		return ConfigurationHandler.enablePrismarine;
	}
}