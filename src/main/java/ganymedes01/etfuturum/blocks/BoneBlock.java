package ganymedes01.etfuturum.blocks;

import ganymedes01.etfuturum.EtFuturum;
import ganymedes01.etfuturum.IConfigurable;
import ganymedes01.etfuturum.configuration.ConfigurationHandler;
import ganymedes01.etfuturum.core.utils.Utils;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BoneBlock extends BlockRotatedPillar implements IConfigurable{
	
	public BoneBlock() {
		super(Material.rock);
		setHardness(1.5F);
		setResistance(10.0F);
		setStepSound(soundTypeStone);
		setBlockTextureName("bone_block");
		setBlockName(Utils.getUnlocalisedName("bone"));
		setCreativeTab(ConfigurationHandler.enableBoneBlock ? EtFuturum.creativeTab : null);
	}

	@Override
	public boolean isEnabled() {
		return ConfigurationHandler.enableBoneBlock;
	}

	@Override
	@SideOnly(Side.CLIENT)
	protected IIcon getSideIcon(int side) {
		return blockIcon;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
		super.registerBlockIcons(reg);
		field_150164_N = reg.registerIcon(getTextureName() + "_top");
	}

}
