package ganymedes01.etfuturum.blocks;

import ganymedes01.etfuturum.EtFuturum;
import ganymedes01.etfuturum.configuration.ConfigurationHandler;
import ganymedes01.etfuturum.core.utils.EnumDyeColor;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockConcrete extends Block {

	private EnumDyeColor color;
	
	public BlockConcrete(EnumDyeColor color) {
		super(Material.rock);
		this.color = color;
		this.setBlockName("etfuturum.concrete_" + this.color);
		this.setCreativeTab(ConfigurationHandler.enableConcrete ? EtFuturum.creativeTab : null);
		this.setHardness(1.8F);
		this.setHarvestLevel("pickaxe", 0);
		this.setResistance(9.0F);
		this.setBlockTextureName("concrete_" + this.color.getName());
	}
	
	public EnumDyeColor getColor() {
		return this.color;
	}
	
}
