package ganymedes01.etfuturum.blocks;

import ganymedes01.etfuturum.EtFuturum;
import ganymedes01.etfuturum.IConfigurable;
import ganymedes01.etfuturum.configuration.ConfigurationHandler;
import ganymedes01.etfuturum.core.utils.Utils;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class NetherwartBlock extends Block implements IConfigurable {

	public NetherwartBlock()
	{
		super(Material.grass);
		setHardness(1F);
		setResistance(5F);
		setStepSound(soundTypeWood);
		setBlockTextureName("nether_wart_block");
		setBlockName(Utils.getUnlocalisedName("nether_wart"));
		setCreativeTab(ConfigurationHandler.enableNetherBlocks ? EtFuturum.creativeTab : null);
	}
	
	@Override
	public boolean isEnabled() {
		return ConfigurationHandler.enableNetherBlocks;
	}
	
}
