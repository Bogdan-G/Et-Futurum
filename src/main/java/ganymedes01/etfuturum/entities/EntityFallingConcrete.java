package ganymedes01.etfuturum.entities;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.world.World;

public class EntityFallingConcrete extends EntityFallingBlock {

	private Block fallTile;
	private int meta;
	
	public EntityFallingConcrete(World worldIn) {
		super(worldIn);
	}
	
	public EntityFallingConcrete(World worldIn, double x, double y, double z, Block block) {
		this(worldIn, x, y, z, block, 0);
	}
	
	public EntityFallingConcrete(World worldIn, double x, double y, double z, Block block, int meta) {
		super(worldIn, x, y, z, block, meta);
		this.fallTile = block;
		this.meta = meta;
	}	

	@Override
	public void onUpdate() {
		super.onUpdate();
		if(this.worldObj.getBlock((int)this.posX, (int)this.posY, (int)this.posZ).getMaterial() == Material.water
		//|| this.worldObj.getBlock((int)this.posX + 1, (int)this.posY, (int)this.posZ).getMaterial() == Material.water
		//|| this.worldObj.getBlock((int)this.posX - 1, (int)this.posY, (int)this.posZ).getMaterial() == Material.water
		//|| this.worldObj.getBlock((int)this.posX, (int)this.posY, (int)this.posZ + 1).getMaterial() == Material.water
		//|| this.worldObj.getBlock((int)this.posX, (int)this.posY, (int)this.posZ - 1).getMaterial() == Material.water
		|| this.worldObj.getBlock((int)this.posX-1, (int)this.posY, (int)this.posZ - 1).getMaterial() == Material.water 
				) {
			this.worldObj.setBlock((int)this.posX-1, (int)this.posY, (int)this.posZ-1, this.fallTile, this.meta, 3);
			this.setDead();
		}
	}
}
	
