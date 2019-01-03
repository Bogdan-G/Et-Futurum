package ganymedes01.etfuturum.items.block;

import ganymedes01.etfuturum.blocks.BlockConcrete;
import ganymedes01.etfuturum.blocks.BlockConcretePowder;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockConcrete extends ItemBlock {

	public ItemBlockConcrete(Block p_i45328_1_) {
		super(p_i45328_1_);
	}
	
	@Override
	public String getItemStackDisplayName(ItemStack p_77653_1_) {
		if(FMLCommonHandler.instance().getSide() == Side.CLIENT){
			if(this.field_150939_a instanceof BlockConcretePowder){
				return I18n.format("tile.concrete_powder.name", new Object[] {I18n.format("color." + ((BlockConcretePowder)this.field_150939_a).getColor().getUnlocalizedName())});
			} else if(this.field_150939_a instanceof BlockConcrete){
				return I18n.format("tile.concrete.name", new Object[] {I18n.format("color." + ((BlockConcrete)this.field_150939_a).getColor().getUnlocalizedName())});
			} else {
				return I18n.format(this.field_150939_a.getUnlocalizedName(), new Object[0]);
			}
		}
		return "";
	}
	
}
