package ganymedes01.etfuturum.blocks;

import java.lang.reflect.Field;

import ganymedes01.etfuturum.EtFuturum;
import ganymedes01.etfuturum.IConfigurable;
import ganymedes01.etfuturum.ModBlocks;
import ganymedes01.etfuturum.ModBlocks.ISubBlocksBlock;
import ganymedes01.etfuturum.configuration.ConfigurationHandler;
import ganymedes01.etfuturum.core.utils.Utils;
import ganymedes01.etfuturum.items.block.ItemBlockGeneric;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockNewLog;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;

public class BlockStrippedNewLog extends BlockNewLog implements IConfigurable, ISubBlocksBlock {
	
	public static final String[] field_150169_M = new String[] {"acacia", "dark_oak"};
	
	public BlockStrippedNewLog()
    {
		setBlockName(Utils.getUnlocalisedName("log2_stripped"));
        //this.setCreativeTab(CreativeTabs.tabBlock);
        setCreativeTab(ConfigurationHandler.enableStrippedLogs ? EtFuturum.creativeTab : null);
        
    }
	
	@SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_)
    {
        this.field_150167_a = new IIcon[field_150169_M.length];
        this.field_150166_b = new IIcon[field_150169_M.length];

        for (int i = 0; i < this.field_150167_a.length; ++i)
        {
            this.field_150167_a[i] = p_149651_1_.registerIcon("stripped_" + field_150169_M[i] + "_log");
            this.field_150166_b[i] = p_149651_1_.registerIcon("stripped_" + field_150169_M[i] + "_log" + "_top");
        }
    }
	
	@Override
	public boolean isEnabled() {
		return ConfigurationHandler.enableStrippedLogs;
	}
	
	@Override
	public Class<? extends ItemBlock> getItemBlockClass() {
		return ItemBlockGeneric.class;
	}
	
	
	public static void onPlayerInteract(PlayerInteractEvent event) {
		if (ConfigurationHandler.enableStrippedLogs)
			if (event.entityPlayer != null) {
				World world = event.entityPlayer.worldObj;
				if (event.action == Action.RIGHT_CLICK_BLOCK)
					if (world.getBlock(event.x, event.y, event.z) == Blocks.log2) {
						int logMeta = world.getBlockMetadata(event.x, event.y, event.z);
						ItemStack stack = event.entityPlayer.getCurrentEquippedItem();
						if (stack != null && (stack.getItem() instanceof ItemAxe || isTinkersAxe(stack))) {
							world.setBlock(event.x, event.y, event.z, ModBlocks.log2_stripped, logMeta, 2);
							//world.setBlock(x, y, z, block, meta, notify)
							event.entityPlayer.swingItem();
							stack.damageItem(1, event.entityPlayer);
							world.playSoundEffect(event.x + 0.5F, event.y + 0.5F, event.z + 0.5F, Block.soundTypeWood.getStepResourcePath(), 1.0F, 0.8F);
						}
					}
			}
	}
	
	private static Item tinkersAxe;

	private static boolean isTinkersAxe(ItemStack stack) {
		if (EtFuturum.isTinkersConstructLoaded) {
			if (tinkersAxe == null)
				try {
					Class<?> TinkerTools = Class.forName("tconstruct.tools.TinkerTools");
					Field field = TinkerTools.getDeclaredField("axe");
					field.setAccessible(true);
					tinkersAxe = (Item) field.get(null);
				} catch (Exception e) {
				}
			return tinkersAxe == stack.getItem();
		}

		return false;
	}
	

}
