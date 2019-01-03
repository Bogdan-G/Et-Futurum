package ganymedes01.etfuturum.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ganymedes01.etfuturum.EtFuturum;
import ganymedes01.etfuturum.IConfigurable;
import ganymedes01.etfuturum.ModBlocks.IBurnableBlock;
import ganymedes01.etfuturum.configuration.ConfigurationHandler;
import ganymedes01.etfuturum.core.utils.Utils;
import net.minecraft.block.BlockButtonWood;
import net.minecraft.block.BlockPressurePlate;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;

public class BlockWoodButton extends BlockButtonWood implements IBurnableBlock, IConfigurable {

	private final int meta;

	public BlockWoodButton(int meta) {
		this.meta = meta;
		setBlockName(Utils.getUnlocalisedName("button_" + BlockWoodDoor.names[meta]));
		setCreativeTab(ConfigurationHandler.enableWoodRedstone ? EtFuturum.creativeTab : null);
		
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return Blocks.planks.getIcon(side, this.meta);
	}
	
	@Override
	public boolean isEnabled() {
		return ConfigurationHandler.enableWoodRedstone;
	}

}
