package ganymedes01.etfuturum.items;

import ganymedes01.etfuturum.EtFuturum;
import ganymedes01.etfuturum.IConfigurable;
import ganymedes01.etfuturum.configuration.ConfigurationHandler;
import ganymedes01.etfuturum.core.utils.Utils;
import net.minecraft.item.Item;

public class PrismarineCrystals extends Item implements IConfigurable {

	public PrismarineCrystals() {
		setTextureName("prismarine_crystals");
		setUnlocalizedName(Utils.getUnlocalisedName("prismarine_crystals"));
		setCreativeTab(ConfigurationHandler.enablePrismarine ? EtFuturum.creativeTab : null);
	}

	@Override
	public boolean isEnabled() {
		return ConfigurationHandler.enablePrismarine;
	}
}