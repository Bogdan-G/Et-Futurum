package ganymedes01.etfuturum.entities;

import cpw.mods.fml.common.eventhandler.Event.Result;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.entity.living.ZombieEvent.SummonAidEvent;

public class EntityHusk extends EntityZombie
{
    public EntityHusk(final World p_i1745_1_) {
    	super(p_i1745_1_);
        getNavigator().setBreakDoors(true);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
        this.tasks.addTask(4, new EntityAIAttackOnCollide(this, EntityVillager.class, 1.0D, true));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(6, new EntityAIMoveThroughVillage(this, 1.0D, false));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityVillager.class, 0, false));
        setSize(0.6F, 1.8F);
        this.isImmuneToFire = true;
    }
    
    
    public boolean attackEntityAsMob(final Entity entity) {
        final boolean flag = super.attackEntityAsMob(entity);
        if (flag) {
            final int i = this.worldObj.difficultySetting.getDifficultyId();
            if (this.getHeldItem() == null && this.isBurning() && this.rand.nextFloat() < i * 0.3f) {
                entity.setFire(0 * i);
            }
            ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.hunger.id, 140 * i, 0));
        }
        return flag;
    }
    
    /*
    public boolean attackEntityFrom(DamageSource p_70097_1_, float p_70097_2_)
    {
        if (!super.attackEntityFrom(p_70097_1_, p_70097_2_))
        {
            return false;
        }
        else
        {
            EntityLivingBase entitylivingbase = this.getAttackTarget();

            if (entitylivingbase == null && this.getEntityToAttack() instanceof EntityLivingBase)
            {
                entitylivingbase = (EntityLivingBase)this.getEntityToAttack();
            }

            if (entitylivingbase == null && p_70097_1_.getEntity() instanceof EntityLivingBase)
            {
                entitylivingbase = (EntityLivingBase)p_70097_1_.getEntity();
            }


            int i = MathHelper.floor_double(this.posX);
            int j = MathHelper.floor_double(this.posY);
            int k = MathHelper.floor_double(this.posZ);

            SummonAidEvent summonAid = ForgeEventFactory.fireZombieSummonAid(this, worldObj, i, j, k, entitylivingbase, this.getEntityAttribute(field_110186_bp).getAttributeValue());
            
            if (summonAid.getResult() == Result.DENY)
            {
                return true;
            }
            else if (summonAid.getResult() == Result.ALLOW || entitylivingbase != null && this.worldObj.difficultySetting == EnumDifficulty.HARD && (double)this.rand.nextFloat() < this.getEntityAttribute(field_110186_bp).getAttributeValue())
            {
                EntityHusk entityzombie;
                if (summonAid.customSummonedAid != null && summonAid.getResult() == Result.ALLOW)
                {
                    entityzombie = (EntityHusk) summonAid.customSummonedAid;
                }
                else
                {
                    entityzombie = new EntityHusk(this.worldObj);
                }

                for (int l = 0; l < 50; ++l)
                {
                    int i1 = i + MathHelper.getRandomIntegerInRange(this.rand, 7, 40) * MathHelper.getRandomIntegerInRange(this.rand, -1, 1);
                    int j1 = j + MathHelper.getRandomIntegerInRange(this.rand, 7, 40) * MathHelper.getRandomIntegerInRange(this.rand, -1, 1);
                    int k1 = k + MathHelper.getRandomIntegerInRange(this.rand, 7, 40) * MathHelper.getRandomIntegerInRange(this.rand, -1, 1);

                    if (World.doesBlockHaveSolidTopSurface(this.worldObj, i1, j1 - 1, k1) && this.worldObj.getBlockLightValue(i1, j1, k1) < 10)
                    {
                        entityzombie.setPosition((double)i1, (double)j1, (double)k1);

                        if (this.worldObj.checkNoEntityCollision(entityzombie.boundingBox) && this.worldObj.getCollidingBoundingBoxes(entityzombie, entityzombie.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(entityzombie.boundingBox))
                        {
                            this.worldObj.spawnEntityInWorld(entityzombie);
                            if (entitylivingbase != null) entityzombie.setAttackTarget(entitylivingbase);
                            entityzombie.onSpawnWithEgg((IEntityLivingData)null);
                            this.getEntityAttribute(field_110186_bp).applyModifier(new AttributeModifier("Zombie reinforcement caller charge", -0.05000000074505806D, 0));
                            entityzombie.getEntityAttribute(field_110186_bp).applyModifier(new AttributeModifier("Zombie reinforcement callee charge", -0.05000000074505806D, 0));
                            break;
                        }
                    }
                }
            }

            return true;
        }
    }
    */
    
    protected String getLivingSound() {
        return "etfuturum:mob.husk.idle";
    }
    
    protected String getHurtSound() {
        return "etfuturum:mob.husk.hurt";
    }
    
    protected String getDeathSound() {
        return "etfuturum:mob.husk.death";
    }
    
    protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
        this.playSound("etfuturum:mob.husk.step", 0.15f, 1.0f);
    }
}
