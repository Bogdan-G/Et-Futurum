package ganymedes01.etfuturum.blocks;

import ganymedes01.etfuturum.EtFuturum;
import ganymedes01.etfuturum.IConfigurable;
import ganymedes01.etfuturum.ModBlocks;
import ganymedes01.etfuturum.configuration.ConfigurationHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockPrismarineStairs extends BlockStairs implements IConfigurable
{
    Block refBlock;
    int refMeta;
    
    public BlockPrismarineStairs(final Block block, final int meta) {
        super(block, meta);
        this.refBlock = block;
        this.refMeta = meta;
        this.setLightOpacity(0);
        this.setCreativeTab(ConfigurationHandler.enablePrismarine ? EtFuturum.creativeTab : null);
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(final IBlockAccess ba, final int x, final int y, final int z, final int side) {
        /*if (this.refBlock == ModBlocks.prismarine && this.refMeta == 5) {
            return this.refBlock.getIcon(ba, x, y, z, side + 100);
        }*/
        return this.refBlock.getIcon(side, this.refMeta);
    }
    
    @Override
	public boolean isEnabled() {
		return ConfigurationHandler.enablePrismarine;
	}

}
