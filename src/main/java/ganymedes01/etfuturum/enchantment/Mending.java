package ganymedes01.etfuturum.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class Mending extends Enchantment {

	public static int ID = 37;

	public Mending() {
		super(ID, 1, EnumEnchantmentType.breakable);
		Enchantment.addToBookList(this);
		setName("mending");
	}
	
	public int getMinEnchantability(int enchantmentLevel)
    {
        return enchantmentLevel * 25;
    }

    public int getMaxEnchantability(int enchantmentLevel)
    {
        return this.getMinEnchantability(enchantmentLevel) + 50;
    }

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack) {
		return stack != null && stack.getItem() == Items.book;
	}
}