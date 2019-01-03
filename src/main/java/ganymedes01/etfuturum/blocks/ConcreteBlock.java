package ganymedes01.etfuturum.blocks;

import ganymedes01.etfuturum.EtFuturum;
import ganymedes01.etfuturum.IConfigurable;
import ganymedes01.etfuturum.ModBlocks.ISubBlocksBlock;
import ganymedes01.etfuturum.configuration.ConfigurationHandler;
import ganymedes01.etfuturum.items.block.ItemBlockGeneric;
import net.minecraft.block.BlockColored;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;

public class ConcreteBlock extends BlockColored implements IConfigurable, ISubBlocksBlock{
	
	public ConcreteBlock()
	{
		super(Material.rock);
		setHardness(1.5F);
		setResistance(10F);
		setStepSound(soundTypeStone);
		setHarvestLevel("pickaxe", 0);
		setBlockTextureName("concrete");
		setBlockName("etfuturum.concrete");
		setCreativeTab(ConfigurationHandler.enableConcrete ? EtFuturum.creativeTab : null);
	}
	
	@Override
	public boolean isEnabled() {
		return ConfigurationHandler.enableConcrete;
	}
	
	@Override
	public Class<? extends ItemBlock> getItemBlockClass() {
		return ItemBlockGeneric.class;
	}

}
