package ganymedes01.etfuturum.core.utils;
/*
import com.cricketcraft.chisel.api.carving.CarvingUtils;
import com.cricketcraft.chisel.api.carving.ICarvingVariation;

import cpw.mods.fml.common.Loader;

import ganymedes01.etfuturum.blocks.ConcreteRegistry;
import net.minecraft.block.Block;
import team.chisel.carving.Carving;

public class ChiselModCompat {

	public static void init(){
		if(Loader.isModLoaded("chisel")){
			for(int i = 0; i < 16; i++){
				EnumDyeColor dye = EnumDyeColor.byMetadata(i);
				Carving.chisel.addVariation("concretePowder", new CarvVar(ConcreteRegistry.getPowderFromDye(dye), i));
				Carving.chisel.addVariation("concreteSolid", new CarvVar(ConcreteRegistry.getSolidFromDye(dye), i));
			}
			CarvingUtils.getChiselRegistry().registerOre("concretePowder", "concretePowder");
			CarvingUtils.getChiselRegistry().registerOre("concreteSolid", "concreteSolid");
		}
	}
	
	public static class CarvVar implements ICarvingVariation {

		private Block block;
		private int order;
		
		private CarvVar(Block blk, int order){
			this.block = blk;
			this.order = order;
		}
		
		@Override
		public Block getBlock() {
			return this.block;
		}

		@Override
		public int getBlockMeta() {
			return 0;
		}

		@Override
		public int getItemMeta() {
			return 0;
		}

		@Override
		public int getOrder() {
			return this.order;
		}
		
	}
	
}
*/