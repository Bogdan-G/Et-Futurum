package ganymedes01.etfuturum.blocks;

import ganymedes01.etfuturum.EtFuturum;
import ganymedes01.etfuturum.configuration.ConfigurationHandler;
import ganymedes01.etfuturum.core.utils.EnumDyeColor;
import ganymedes01.etfuturum.entities.EntityFallingConcrete;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockConcretePowder extends BlockFalling {

	private EnumDyeColor color;
	
	public BlockConcretePowder(EnumDyeColor color) {
		super(Material.sand);
		this.color = color;
		this.setBlockName("etfuturum.concrete_powder_" + this.color);
		this.setStepSound(soundTypeSand);
		this.setCreativeTab(ConfigurationHandler.enableConcrete ? EtFuturum.creativeTab : null);
		this.setHardness(0.5F);
		this.setResistance(2.5F);
		this.setBlockTextureName("concrete_powder_" + this.color.getName());
	}
	
	public void onBlockAdded(World worldIn, int x, int y, int z) {
		if( worldIn.getBlock(x + 1, y, z).getMaterial() == Material.water
		|| worldIn.getBlock(x - 1, y, z).getMaterial() == Material.water
		|| worldIn.getBlock(x, y, z + 1).getMaterial() == Material.water
		|| worldIn.getBlock(x, y, z - 1).getMaterial() == Material.water
		|| worldIn.getBlock(x, y + 1, z).getMaterial() == Material.water
		|| worldIn.getBlock(x, y - 1, z).getMaterial() == Material.water ){
			worldIn.setBlock(x, y, z, ConcreteRegistry.getSolidFromDye(this.color));
		} else {
			worldIn.scheduleBlockUpdate(x, y, z, this, this.tickRate(worldIn));
		}
	}
	
	public void onNeighborChanged(IBlockAccess blkAcc, int x, int y, int z, int tileX, int tileY, int tileZ) {
		if(blkAcc instanceof World){
			World worldIn = (World) blkAcc;
			if( worldIn.getBlock(x + 1, y, z).getMaterial() == Material.water
			|| worldIn.getBlock(x - 1, y, z).getMaterial() == Material.water
			|| worldIn.getBlock(x, y, z + 1).getMaterial() == Material.water
			|| worldIn.getBlock(x, y, z - 1).getMaterial() == Material.water
			|| worldIn.getBlock(x, y + 1, z).getMaterial() == Material.water
			|| worldIn.getBlock(x, y - 1, z).getMaterial() == Material.water ){
				worldIn.setBlock(x, y, z, ConcreteRegistry.getSolidFromDye(this.color));
			} else {
				worldIn.scheduleBlockUpdate(x, y, z, this, this.tickRate(worldIn));
			}
		}
	}
	
	public void updateTick(World worldIn, int x, int y, int z, Random rand) {
		if(!worldIn.isRemote) {
			this.func_149830_m(worldIn, x, y, z);
		}
	}
	
	private void func_149830_m(World p_149830_1_, int p_149830_2_, int p_149830_3_, int p_149830_4_) {
		if(func_149831_e(p_149830_1_, p_149830_2_, p_149830_3_ - 1, p_149830_4_) && p_149830_3_ >= 0) {
			byte b0 = 32;

			if(!fallInstantly && p_149830_1_.checkChunksExist(p_149830_2_ - b0, p_149830_3_ - b0, p_149830_4_ - b0, p_149830_2_ + b0, p_149830_3_ + b0, p_149830_4_ + b0)) {
				if(!p_149830_1_.isRemote) {
					EntityFallingConcrete entityfallingblock = new EntityFallingConcrete(p_149830_1_, (double)((float)p_149830_2_ + 0.5F), (double)((float)p_149830_3_ + 0.5F), (double)((float)p_149830_4_ + 0.5F), this, p_149830_1_.getBlockMetadata(p_149830_2_, p_149830_3_, p_149830_4_));
					this.func_149829_a(entityfallingblock);
					p_149830_1_.spawnEntityInWorld(entityfallingblock);
				}
			} else {
				p_149830_1_.setBlockToAir(p_149830_2_, p_149830_3_, p_149830_4_);

				while(func_149831_e(p_149830_1_, p_149830_2_, p_149830_3_ - 1, p_149830_4_) && p_149830_3_ > 0) {
					--p_149830_3_;
				}

				if(p_149830_3_ > 0) {
					p_149830_1_.setBlock(p_149830_2_, p_149830_3_, p_149830_4_, this);
				}
			}
		}
	}
	
	protected void onStartFalling(EntityFallingBlock fallingEntity){}
	
	public static boolean canFallThrough(World worldIn, int x, int y, int z) {
		Block block = worldIn.getBlock(x, y, z);
		Material material = block.getMaterial();
		return block == Blocks.fire || material == Material.air || material == Material.water || material == Material.lava;
	}
	
	public void onEndFalling(World worldIn, int x, int y, int z){}

	public EnumDyeColor getColor() {
		return this.color;
	}

}
