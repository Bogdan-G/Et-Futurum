package ganymedes01.etfuturum.blocks;

import ganymedes01.etfuturum.EtFuturum;
import ganymedes01.etfuturum.IConfigurable;
import ganymedes01.etfuturum.ModBlocks.ISubBlocksBlock;
import ganymedes01.etfuturum.configuration.ConfigurationHandler;
import ganymedes01.etfuturum.core.utils.Utils;
import ganymedes01.etfuturum.items.block.ItemBlockGeneric;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockWood;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.IIcon;

public class BlockWoodBark extends BlockWood implements IConfigurable, ISubBlocksBlock{
	
	public BlockWoodBark()
    {
		setCreativeTab(ConfigurationHandler.enableBarkLogs ? EtFuturum.creativeTab : null);
        this.setBlockTextureName("log");
        setBlockName(Utils.getUnlocalisedName("bark"));
    }
	
	private IIcon[] field_150095_b;
	
	@SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int p_149691_2_)
    {
        if (p_149691_2_ < 0 || p_149691_2_ >= this.field_150095_b.length)
        {
            p_149691_2_ = 0;
        }

        return this.field_150095_b[p_149691_2_];
    }
	
	@SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_)
    {
        this.field_150095_b = new IIcon[field_150096_a.length];

        for (int i = 0; i < this.field_150095_b.length; ++i)
        {
            this.field_150095_b[i] = p_149651_1_.registerIcon(this.getTextureName() + "_" + field_150096_a[i]);
        }
    }
	
	@Override
	public boolean isEnabled() {
		return ConfigurationHandler.enableBarkLogs;
	}
	
	@Override
	public Class<? extends ItemBlock> getItemBlockClass() {
		return ItemBlockGeneric.class;
	}

}
