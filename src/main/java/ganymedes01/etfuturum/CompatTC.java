package ganymedes01.etfuturum;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cpw.mods.fml.common.registry.GameRegistry;
import ganymedes01.etfuturum.blocks.BlockConcrete;
import ganymedes01.etfuturum.blocks.BlockConcretePowder;
import ganymedes01.etfuturum.blocks.BlockWoodTrapdoor;
import ganymedes01.etfuturum.blocks.ConcreteRegistry;
import ganymedes01.etfuturum.core.utils.EnumDyeColor;
import ganymedes01.etfuturum.items.block.ItemBlockConcrete;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionHelper;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

public class CompatTC {
	
	public static void doAspects() {
		//ThaumcraftApi.registerObjectTag(new ItemStack(), new AspectList() );
		//ThaumcraftApi.registerEntityTag("etfuturum.endermite", new AspectList(), new ThaumcraftApi.EntityTagsNBT[0] );
		
		//entity
		ThaumcraftApi.registerEntityTag("etfuturum.rabbit", new AspectList().add(Aspect.BEAST, 1).add(Aspect.EARTH, 1).add(Aspect.MOTION, 1), new ThaumcraftApi.EntityTagsNBT[0] );
		
		ThaumcraftApi.registerEntityTag("etfuturum.endermite", new AspectList().add(Aspect.BEAST, 1).add(Aspect.ELDRITCH, 1).add(Aspect.TRAVEL, 1), new ThaumcraftApi.EntityTagsNBT[0]);
		ThaumcraftApi.registerEntityTag("etfuturum.snow_golem", new AspectList().add(Aspect.COLD, 3).add(Aspect.WATER, 1), new ThaumcraftApi.EntityTagsNBT[0] );
		
		ThaumcraftApi.registerEntityTag("etfuturum.end_crystal", new AspectList().add(Aspect.ELDRITCH, 3).add(Aspect.MAGIC, 3).add(Aspect.HEAL, 3), new ThaumcraftApi.EntityTagsNBT[0] );
		ThaumcraftApi.registerEntityTag("etfuturum.ender_dragon", new AspectList().add(Aspect.ELDRITCH, 20).add(Aspect.BEAST, 20).add(Aspect.ENTROPY, 20), new ThaumcraftApi.EntityTagsNBT[0] );
		
		ThaumcraftApi.registerEntityTag("etfuturum.villager_zombie", new AspectList().add(Aspect.UNDEAD, 2).add(Aspect.MAN, 1).add(Aspect.EARTH, 1), new ThaumcraftApi.EntityTagsNBT[0] );
		
		ThaumcraftApi.registerEntityTag("etfuturum.husk", new AspectList().add(Aspect.UNDEAD, 2).add(Aspect.MAN, 1).add(Aspect.FIRE, 1), new ThaumcraftApi.EntityTagsNBT[0] );
		ThaumcraftApi.registerEntityTag("etfuturum.stray", new AspectList().add(Aspect.UNDEAD, 3).add(Aspect.MAN, 1).add(Aspect.TRAP, 1), new ThaumcraftApi.EntityTagsNBT[0] );
		
		
		//items
		//ThaumcraftApi.registerObjectTag(new ItemStack(ModItems), new AspectList() );
		ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.beetroot), new AspectList().add(Aspect.CROP, 1).add(Aspect.HUNGER, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.beetroot_seeds), new AspectList().add(Aspect.PLANT, 1) );
		ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.chorus_fruit), new AspectList().add(Aspect.ELDRITCH, 1).add(Aspect.SENSES, 1).add(Aspect.PLANT, 1) );
		ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.popped_chorus_fruit), new AspectList().add(Aspect.ELDRITCH, 1).add(Aspect.SENSES, 1).add(Aspect.PLANT, 1).add(Aspect.FIRE, 1) );
		ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.dragon_breath), new AspectList().add(Aspect.DARKNESS, 2).add(Aspect.ENTROPY, 2).add(Aspect.FIRE, 2) );
		ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.elytra), new AspectList().add(Aspect.FLIGHT, 4).add(Aspect.MOTION, 3) );
		ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.prismarine_crystals), new AspectList().add(Aspect.WATER, 1).add(Aspect.CRYSTAL, 1).add(Aspect.LIGHT, 1) );
		ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.prismarine_shard), new AspectList().add(Aspect.WATER, 1).add(Aspect.EARTH, 1) );
		ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.totem), new AspectList().add(Aspect.ORDER, 2).add(Aspect.ENTROPY, 2).add(Aspect.LIFE, 5).add(Aspect.UNDEAD, 2) );

		ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.raw_mutton, 1, 32767), new AspectList().add(Aspect.BEAST, 2).add(Aspect.LIFE, 1).add(Aspect.EARTH, 1));
	    ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.cooked_mutton, 1, 32767), new AspectList().add(Aspect.CRAFT, 1).add(Aspect.BEAST, 1).add(Aspect.LIFE, 2));
	    ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.raw_rabbit, 1, 32767), new AspectList().add(Aspect.BEAST, 2).add(Aspect.LIFE, 1).add(Aspect.EARTH, 1));
	    ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.cooked_rabbit, 1, 32767), new AspectList().add(Aspect.CRAFT, 1).add(Aspect.BEAST, 1).add(Aspect.LIFE, 2));
	    ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.rabbit_hide, 1, 32767), new AspectList().add(Aspect.BEAST, 1).add(Aspect.ARMOR, 1));
	    ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.rabbit_foot, 1, 32767), new AspectList().add(Aspect.BEAST, 1).add(Aspect.ARMOR, 1).add(Aspect.MOTION, 2));
		
	    ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.tipped_arrow), new AspectList().add(Aspect.WEAPON, 1).add(Aspect.MAGIC, 2) );
	    
	    ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.lingering_potion,1,32767), new AspectList().add(Aspect.TRAP, 1).add(Aspect.WATER, 1).add(Aspect.MAGIC, 2) );
	    ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.lingering_potion,1,0), new AspectList().add(Aspect.CRYSTAL, 1).add(Aspect.WATER, 1) );
	    //regen
	    ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.lingering_potion,1,8193), new AspectList(new ItemStack(ModItems.lingering_potion,1,32767)).add(Aspect.HEAL, 3) );
	    ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.lingering_potion,1,8225), new AspectList(new ItemStack(ModItems.lingering_potion,1,32767)).add(Aspect.HEAL, 6).add(Aspect.MAGIC, 2) );
	    ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.lingering_potion,1,8257), new AspectList(new ItemStack(ModItems.lingering_potion,1,32767)).add(Aspect.HEAL, 3) );
	    //speed
	    ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.lingering_potion,1,8194), new AspectList(new ItemStack(ModItems.lingering_potion,1,32767)).add(Aspect.MOTION, 3) );
	    ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.lingering_potion,1,8226), new AspectList(new ItemStack(ModItems.lingering_potion,1,32767)).add(Aspect.MOTION, 6).add(Aspect.MAGIC, 2) );
	    ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.lingering_potion,1,8258), new AspectList(new ItemStack(ModItems.lingering_potion,1,32767)).add(Aspect.MOTION, 3) );
	    //fire res
	    ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.lingering_potion,1,8227), new AspectList(new ItemStack(ModItems.lingering_potion,1,32767)).add(Aspect.FIRE, 2).add(Aspect.ARMOR, 1) );
	    ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.lingering_potion,1,8259), new AspectList(new ItemStack(ModItems.lingering_potion,1,32767)).add(Aspect.FIRE, 2).add(Aspect.ARMOR, 1) );
	    //poison
	    ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.lingering_potion,1,8196), new AspectList(new ItemStack(ModItems.lingering_potion,1,32767)).add(Aspect.POISON, 3) );
	    ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.lingering_potion,1,8228), new AspectList(new ItemStack(ModItems.lingering_potion,1,32767)).add(Aspect.POISON, 6).add(Aspect.MAGIC, 2) );
	    ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.lingering_potion,1,8260), new AspectList(new ItemStack(ModItems.lingering_potion,1,32767)).add(Aspect.POISON, 3) );
	    //healing
	    ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.lingering_potion,1,8229), new AspectList(new ItemStack(ModItems.lingering_potion,1,32767)).add(Aspect.HEAL, 3) );
	    ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.lingering_potion,1,8261), new AspectList(new ItemStack(ModItems.lingering_potion,1,32767)).add(Aspect.HEAL, 3) );
	    //NV
	    ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.lingering_potion,1,8230), new AspectList(new ItemStack(ModItems.lingering_potion,1,32767)).add(Aspect.SENSES, 3) );
	    ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.lingering_potion,1,8262), new AspectList(new ItemStack(ModItems.lingering_potion,1,32767)).add(Aspect.SENSES, 3) );
	    //weakness
	    ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.lingering_potion,1,8232), new AspectList(new ItemStack(ModItems.lingering_potion,1,32767)).add(Aspect.DEATH, 3) );
	    ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.lingering_potion,1,8264), new AspectList(new ItemStack(ModItems.lingering_potion,1,32767)).add(Aspect.DEATH, 3) );
	    //strength
	    ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.lingering_potion,1,8201), new AspectList(new ItemStack(ModItems.lingering_potion,1,32767)).add(Aspect.WEAPON, 3) );
	    ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.lingering_potion,1,8233), new AspectList(new ItemStack(ModItems.lingering_potion,1,32767)).add(Aspect.WEAPON, 6).add(Aspect.MAGIC, 2) );
	    ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.lingering_potion,1,8264), new AspectList(new ItemStack(ModItems.lingering_potion,1,32767)).add(Aspect.WEAPON, 3) );
	    //slowness
	    ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.lingering_potion,1,8234), new AspectList(new ItemStack(ModItems.lingering_potion,1,32767)).add(Aspect.TRAP, 3) );
	    ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.lingering_potion,1,8266), new AspectList(new ItemStack(ModItems.lingering_potion,1,32767)).add(Aspect.TRAP, 3) );
	    //leaping
	    ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.lingering_potion,1,8235), new AspectList(new ItemStack(ModItems.lingering_potion,1,32767)).add(Aspect.FLIGHT, 3) );
	    ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.lingering_potion,1,8267), new AspectList(new ItemStack(ModItems.lingering_potion,1,32767)).add(Aspect.FLIGHT, 3) );
	    //damage
	    ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.lingering_potion,1,8236), new AspectList(new ItemStack(ModItems.lingering_potion,1,32767)).add(Aspect.DEATH, 3) );
	    ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.lingering_potion,1,8268), new AspectList(new ItemStack(ModItems.lingering_potion,1,32767)).add(Aspect.DEATH, 3) );
	    //water breathing
	    ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.lingering_potion,1,8237), new AspectList(new ItemStack(ModItems.lingering_potion,1,32767)).add(Aspect.AIR, 3) );
	    ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.lingering_potion,1,8269), new AspectList(new ItemStack(ModItems.lingering_potion,1,32767)).add(Aspect.AIR, 3) );
	    //invis
	    ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.lingering_potion,1,8238), new AspectList(new ItemStack(ModItems.lingering_potion,1,32767)).add(Aspect.SENSES, 3) );
	    ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.lingering_potion,1,8270), new AspectList(new ItemStack(ModItems.lingering_potion,1,32767)).add(Aspect.SENSES, 3) );
	    
	    
		//blocks
		//ThaumcraftApi.registerObjectTag(new ItemStack(ModBlocks.), new AspectList() );
	    ThaumcraftApi.registerObjectTag(new ItemStack(ModBlocks.beacon), new AspectList(new ItemStack(Blocks.beacon)) );
	    ThaumcraftApi.registerObjectTag(new ItemStack(ModBlocks.beetroot), new AspectList().add(Aspect.PLANT, 1) );
	    ThaumcraftApi.registerObjectTag(new ItemStack(ModBlocks.brewing_stand), new AspectList(new ItemStack(Blocks.brewing_stand)) );
	    ThaumcraftApi.registerObjectTag(new ItemStack(ModBlocks.chorus_flower), new AspectList().add(Aspect.ELDRITCH, 1).add(Aspect.SENSES, 1).add(Aspect.PLANT, 1) );
	    ThaumcraftApi.registerObjectTag(new ItemStack(ModBlocks.chorus_plant), new AspectList().add(Aspect.ELDRITCH, 1).add(Aspect.PLANT, 1) );
	    ThaumcraftApi.registerObjectTag(new ItemStack(ModBlocks.daylight_sensor), new AspectList(new ItemStack(Blocks.daylight_detector)) );
	    ThaumcraftApi.registerObjectTag(new ItemStack(ModBlocks.enchantment_table), new AspectList(new ItemStack(Blocks.enchanting_table)) );
	    ThaumcraftApi.registerObjectTag(new ItemStack(ModBlocks.end_rod), new AspectList().add(Aspect.LIGHT, 2).add(Aspect.FIRE, 1) );
	    ThaumcraftApi.registerObjectTag(new ItemStack(ModBlocks.grass_path), new AspectList(new ItemStack(Blocks.grass)).add(Aspect.ORDER, 1) );
	    ThaumcraftApi.registerObjectTag(new ItemStack(ModBlocks.frosted_ice), new AspectList(new ItemStack(Blocks.ice)).add(Aspect.ENTROPY, 1) );
	    ThaumcraftApi.registerObjectTag(new ItemStack(ModBlocks.log_stripped,1,32767), new AspectList(new ItemStack(Blocks.log)) );
	    ThaumcraftApi.registerObjectTag(new ItemStack(ModBlocks.log2_stripped,1,32767), new AspectList(new ItemStack(Blocks.log2)) );
	    ThaumcraftApi.registerObjectTag(new ItemStack(ModBlocks.magma), new AspectList().add(Aspect.FIRE, 2).add(Aspect.EARTH, 1) );
	    
	    ThaumcraftApi.registerObjectTag(new ItemStack(ModBlocks.brown_mushroom_block), new AspectList(new ItemStack(Blocks.brown_mushroom)) );
	    ThaumcraftApi.registerObjectTag(new ItemStack(ModBlocks.red_mushroom_block), new AspectList(new ItemStack(Blocks.red_mushroom)) );
	    
	    ThaumcraftApi.registerObjectTag(new ItemStack(ModBlocks.red_sandstone, 1, 0), new AspectList(new ItemStack(Blocks.sandstone)) );
	    //ThaumcraftApi.registerObjectTag(new ItemStack(ModBlocks.red_sandstone, 1, 1), new AspectList(new ItemStack(Blocks.sandstone,1,1)) );
	    //ThaumcraftApi.registerObjectTag(new ItemStack(ModBlocks.red_sandstone, 1, 2), new AspectList(new ItemStack(Blocks.sandstone,1,2)) );
	    
	    ThaumcraftApi.registerObjectTag(new ItemStack(ModBlocks.sponge,1,0), new AspectList().add(Aspect.EARTH, 1).add(Aspect.TRAP, 1).add(Aspect.VOID, 1) );
	    ThaumcraftApi.registerObjectTag(new ItemStack(ModBlocks.sponge,1,1), new AspectList().add(Aspect.EARTH, 1).add(Aspect.TRAP, 1).add(Aspect.WATER, 1) );
	    
	    ThaumcraftApi.registerObjectTag(new ItemStack(ModBlocks.stone,1,32767), new AspectList(new ItemStack(Blocks.stone)) );
	    //ThaumcraftApi.registerObjectTag(new ItemStack(ModBlocks.stone,1,3), new AspectList(new ItemStack(Blocks.stone)) );
	    //ThaumcraftApi.registerObjectTag(new ItemStack(ModBlocks.stone,1,5), new AspectList(new ItemStack(Blocks.stone)) );
	    
	    
	    for (int i = 0; i < ModBlocks.trapdoors.length; i++)
			ThaumcraftApi.registerObjectTag(new ItemStack(ModBlocks.trapdoors[i]), new AspectList(new ItemStack(Blocks.trapdoor)) );
	    
	    for (int i = 0; i < ModBlocks.gates.length; i++)
			ThaumcraftApi.registerObjectTag(new ItemStack(ModBlocks.gates[i]), new AspectList(new ItemStack(Blocks.fence_gate)) );
	    
	    for (int i = 0; i < ModBlocks.doors.length; i++)
			ThaumcraftApi.registerObjectTag(new ItemStack(ModBlocks.doors[i]), new AspectList(new ItemStack(Blocks.wooden_door)) );
	    
	    
	    for(EnumDyeColor color : EnumDyeColor.values()){
			//ConcreteRegistry.concretePowders.put(color, new BlockConcretePowder(color));
			//GameRegistry.registerBlock(ConcreteRegistry.concretePowders.get(color), ItemBlockConcrete.class, "concrete_powder_" + color.getUnlocalizedName());
			//ConcreteRegistry.concretes.put(color, new BlockConcrete(color));
			//GameRegistry.registerBlock(ConcreteRegistry.concretes.get(color), ItemBlockConcrete.class, "concrete_" + color.getUnlocalizedName());
	    	
	    	ThaumcraftApi.registerObjectTag(new ItemStack(ConcreteRegistry.concretePowders.get(color)), new AspectList().add(Aspect.EARTH, 3).add(Aspect.ENTROPY, 2) );
	    	ThaumcraftApi.registerObjectTag(new ItemStack(ConcreteRegistry.concretes.get(color)), new AspectList(new ItemStack(ConcreteRegistry.concretePowders.get(color))).add(Aspect.WATER, 1).add(Aspect.ORDER, 1) );
	    	
		}
	    
	}
	
	
	public static void doRecipes() {
		
	}

}
