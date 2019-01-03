package ganymedes01.etfuturum.entities;

import ganymedes01.etfuturum.ModItems;
import ganymedes01.etfuturum.items.TippedArrow;

import java.util.Calendar;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIFleeSun;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIRestrictSun;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderHell;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class EntityStray extends EntitySkeleton {
	
	private EntityAIArrowAttack aiArrowAttack = new EntityAIArrowAttack(this, 1.0D, 20, 60, 15.0F);
	private EntityAIAttackOnCollide aiAttackOnCollide = new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.2D, false);
	
    public EntityStray(final World p_i1741_1_) {
    	super(p_i1741_1_);
    	this.tasks.addTask(1, new EntityAISwimming(this));
    	this.tasks.addTask(2, new EntityAIRestrictSun(this));
    	this.tasks.addTask(3, new EntityAIFleeSun(this, 1.0D));
    	this.tasks.addTask(5, new EntityAIWander(this, 1.0D));
    	this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
    	this.tasks.addTask(6, new EntityAILookIdle(this));
    	this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
    	this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
    	if ((p_i1741_1_ != null) && (!p_i1741_1_.isRemote)) {
    		setCombatTask();
    	}
    }
    
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData p_110161_1_)
    {
        p_110161_1_ = super.onSpawnWithEgg(p_110161_1_);
        
        if (this.getRNG().nextInt(4) > 3) //20% chance
        {
            this.tasks.addTask(4, this.aiAttackOnCollide);
            this.setCurrentItemOrArmor(0, new ItemStack(Items.stone_sword));
            this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0D); //wither skel values
            this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.27D); //0.25D default
        }
        else
        {
            this.tasks.addTask(4, this.aiArrowAttack);
            this.addRandomArmor();
            this.enchantEquipment();
        }
        
        /*
        this.tasks.addTask(4, this.aiArrowAttack );
        this.addRandomArmor();
        this.enchantEquipment();
        */

        this.setCanPickUpLoot(this.rand.nextFloat() < 0.55F * this.worldObj.func_147462_b(this.posX, this.posY, this.posZ));

        if (this.getEquipmentInSlot(4) == null)
        {
            Calendar calendar = this.worldObj.getCurrentDate();

            if (calendar.get(2) + 1 == 10 && calendar.get(5) == 31 && this.rand.nextFloat() < 0.25F)
            {
                this.setCurrentItemOrArmor(4, new ItemStack(this.rand.nextFloat() < 0.1F ? Blocks.lit_pumpkin : Blocks.pumpkin));
                this.equipmentDropChances[4] = 0.0F;
            }
        }

        return p_110161_1_;
    }
    
    protected void addRandomArmor()
    {
        super.addRandomArmor();
        this.setCurrentItemOrArmor(0, new ItemStack(Items.bow));
    }
    
    public boolean attackEntityAsMob(final Entity entity) {
        final boolean flag = super.attackEntityAsMob(entity);
        if (flag) {
            final int i = this.worldObj.difficultySetting.getDifficultyId();
            if (this.getHeldItem() == null && this.isBurning() && this.rand.nextFloat() < i * 0.3f) {
                entity.setFire(0 * i);
            }
            ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 140 * i, 0));
        }
        return flag;
    }
    
    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase p_82196_1_, float p_82196_2_)
    {
        EntityTippedArrow entityarrow = new EntityTippedArrow(this.worldObj, this, p_82196_1_, 1.6F, (float)(14 - this.worldObj.difficultySetting.getDifficultyId() * 4));
        int i = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, this.getHeldItem());
        int j = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, this.getHeldItem());
        entityarrow.setDamage((double)(p_82196_2_ * 2.0F) + this.rand.nextGaussian() * 0.25D + (double)((float)this.worldObj.difficultySetting.getDifficultyId() * 0.11F));
        
        final int diff = this.worldObj.difficultySetting.getDifficultyId();
        entityarrow.setEffect(new PotionEffect(Potion.moveSlowdown.getId(), 600, 0) );
        
        if (i > 0)
        {
            entityarrow.setDamage(entityarrow.getDamage() + (double)i * 0.5D + 0.5D);
        }

        if (j > 0)
        {
            entityarrow.setKnockbackStrength(j);
        }

        if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, this.getHeldItem()) > 0 || this.getSkeletonType() == 1)
        {
            entityarrow.setFire(100);
        }

        this.playSound("random.bow", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        this.worldObj.spawnEntityInWorld(entityarrow);
    }
    
    protected void dropFewItems(boolean p_70628_1_, int p_70628_2_) {
        int j;
        int k;

        j = this.rand.nextInt(3 + p_70628_2_);

        for (k = 0; k < j; ++k) {
            this.dropItem(Items.arrow, 1);
        }
        for (k = 0; k < j; ++k) {
            this.dropItem(Items.bone, 1);
        }
        /*//doesn't work, don't know why yet
        for (k = 0; k < j; ++k) {
        	
            this.dropItem(TippedArrow.setEffect(new ItemStack(ModItems.tipped_arrow), Potion.potionTypes[Potion.moveSlowdown.getId()], 20*45, 0 ).getItem(), 1); 
        }*/
    }
    
    protected String getLivingSound() {
        return "etfuturum:mob.stray.idle";
    }
    
    protected String getHurtSound() {
        return "etfuturum:mob.stray.hurt";
    }
    
    protected String getDeathSound() {
        return "etfuturum:mob.stray.death";
    }
    
    protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
        this.playSound("etfuturum:mob.stray.step", 0.15f, 1.0f);
    }
}
