package ganymedes01.etfuturum.blocks;

import ganymedes01.etfuturum.EtFuturum;
import ganymedes01.etfuturum.IConfigurable;
import ganymedes01.etfuturum.ModBlocks.IBurnableBlock;
import ganymedes01.etfuturum.configuration.ConfigurationHandler;
import ganymedes01.etfuturum.core.utils.Utils;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockPressurePlate;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockWoodPressurePlate extends BlockPressurePlate implements IConfigurable {

	private final int meta;

	public BlockWoodPressurePlate(int meta) {
		super("planks_oak", Material.wood, BlockPressurePlate.Sensitivity.everything);
		this.meta = meta;
		disableStats();
		setHardness(0.5F);
		setStepSound(soundTypeWood);
		setBlockName(Utils.getUnlocalisedName("pressure_plate_" + BlockWoodDoor.names[meta]));
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