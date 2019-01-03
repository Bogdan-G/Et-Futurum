package ganymedes01.etfuturum.core.handlers;

import ganymedes01.etfuturum.EtFuturum;
import ganymedes01.etfuturum.ModBlocks;
import ganymedes01.etfuturum.ModEnchantments;
import ganymedes01.etfuturum.ModItems;
import ganymedes01.etfuturum.blocks.BlockStrippedNewLog;
import ganymedes01.etfuturum.blocks.BlockStrippedOldLog;
import ganymedes01.etfuturum.blocks.CoarseDirt;
import ganymedes01.etfuturum.blocks.GrassPath;
import ganymedes01.etfuturum.blocks.NewAnvil;
import ganymedes01.etfuturum.command.SetPlayerModelCommand;
import ganymedes01.etfuturum.configuration.ConfigurationHandler;
import ganymedes01.etfuturum.entities.EntityEndermite;
import ganymedes01.etfuturum.entities.EntityNewSnowGolem;
import ganymedes01.etfuturum.entities.EntityRabbit;
import ganymedes01.etfuturum.entities.EntityTippedArrow;
import ganymedes01.etfuturum.entities.EntityZombieVillager;
import ganymedes01.etfuturum.entities.ai.EntityAIOpenCustomDoor;
import ganymedes01.etfuturum.inventory.ContainerEnchantment;
import ganymedes01.etfuturum.items.TippedArrow;
import ganymedes01.etfuturum.lib.Reference;
import ganymedes01.etfuturum.network.BlackHeartParticlesMessage;
import ganymedes01.etfuturum.network.SetPlayerModelMessage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAITargetNonTamed;
import net.minecraft.entity.ai.EntityAITasks.EntityAITaskEntry;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.EntityStruckByLightningEvent;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerPickupXpEvent;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import net.minecraftforge.event.world.BlockEvent;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import cpw.mods.fml.relauncher.Side;

public class ServerEventHandler {

	public static final ServerEventHandler INSTANCE = new ServerEventHandler();

	private ServerEventHandler() {
	}

	private Integer playerLoggedInCooldown = null;

	@SubscribeEvent
	public void onPlayerLoggedIn(PlayerLoggedInEvent event) {
		if (ConfigurationHandler.enablePlayerSkinOverlay)
			playerLoggedInCooldown = 20;
	}

	@SubscribeEvent
	@SuppressWarnings("unchecked")
	public void onWorldTick(TickEvent.ServerTickEvent event) {
		if (event.phase != TickEvent.Phase.END || event.side != Side.SERVER)
			return;

		if (ConfigurationHandler.enablePlayerSkinOverlay)
			if (playerLoggedInCooldown != null)
				if (--playerLoggedInCooldown <= 0) {
					for (World world : MinecraftServer.getServer().worldServers)
						for (EntityPlayer player : (List<EntityPlayer>) world.playerEntities) {
							NBTTagCompound nbt = player.getEntityData();
							if (nbt.hasKey(SetPlayerModelCommand.MODEL_KEY, Constants.NBT.TAG_BYTE)) {
								boolean isAlex = nbt.getBoolean(SetPlayerModelCommand.MODEL_KEY);
								EtFuturum.networkWrapper.sendToAll(new SetPlayerModelMessage(player, isAlex));
							}
						}
					playerLoggedInCooldown = null;
				}
	}

	@SubscribeEvent
	public void onPlayerPickXP(PlayerPickupXpEvent event) {
		ModEnchantments.onPlayerPickupXP(event);
	}

	@SubscribeEvent
	public void livingUpdate(LivingUpdateEvent event) {
		ModEnchantments.onLivingUpdate(event.entityLiving);

		if (ConfigurationHandler.enableVillagerZombies)
			if (!event.entityLiving.worldObj.isRemote && event.entityLiving.getClass() == EntityZombie.class) {
				EntityZombie zombie = (EntityZombie) event.entityLiving;
				if (zombie.isVillager()) {
					EntityZombieVillager villagerZombie = new EntityZombieVillager(zombie.worldObj);
					villagerZombie.copyLocationAndAnglesFrom(zombie);
					villagerZombie.onSpawnWithEgg(null);
					villagerZombie.worldObj.spawnEntityInWorld(villagerZombie);

					zombie.setDead();
				}
			}

		if (ConfigurationHandler.enableShearableGolems)
			if (!event.entityLiving.worldObj.isRemote && event.entityLiving.getClass() == EntitySnowman.class) {
				EntityNewSnowGolem golen = new EntityNewSnowGolem(event.entityLiving.worldObj);
				golen.copyLocationAndAnglesFrom(event.entityLiving);
				golen.onSpawnWithEgg(null);
				golen.worldObj.spawnEntityInWorld(golen);

				event.entityLiving.setDead();
			}
	}

	@SubscribeEvent
	public void livingAttack(LivingAttackEvent event) {
		if (event.source instanceof EntityDamageSourceIndirect) {
			EntityDamageSourceIndirect dmgSrc = (EntityDamageSourceIndirect) event.source;
			if (dmgSrc.getSourceOfDamage() instanceof EntityTippedArrow) {
				EntityTippedArrow tippedArrow = (EntityTippedArrow) dmgSrc.getSourceOfDamage();
				if (!tippedArrow.worldObj.isRemote)
					event.entityLiving.addPotionEffect(tippedArrow.getEffect());
			}
		}
	}

	@SubscribeEvent
	public void arrowNock(ArrowNockEvent event) {
		if (event.result == null)
			return;
		IInventory invt = event.entityPlayer.inventory;
		for (int i = 0; i < invt.getSizeInventory(); i++) {
			ItemStack stack = invt.getStackInSlot(i);
			if (stack == null || stack.stackSize <= 0)
				continue;
			if (stack.getItem() == Items.arrow)
				return;
			if (stack.getItem() == ModItems.tipped_arrow) {
				event.setCanceled(true);
				event.entityPlayer.setItemInUse(event.result, event.result.getItem().getMaxItemUseDuration(event.result));
				return;
			}
		}
	}

	@SubscribeEvent
	public void arrowLoose(ArrowLooseEvent event) {
		if (event.bow == null)
			return;

		IInventory invt = event.entityPlayer.inventory;
		for (int i = 0; i < invt.getSizeInventory(); i++) {
			ItemStack arrow = invt.getStackInSlot(i);
			if (arrow != null && arrow.stackSize > 0 && arrow.getItem() == ModItems.tipped_arrow) {
				float charge = event.charge / 20.0F;
				charge = (charge * charge + charge * 2.0F) / 3.0F;

				if (charge < 0.1D)
					return;
				if (charge > 1.0F)
					charge = 1.0F;

				EntityTippedArrow arrowEntity = new EntityTippedArrow(event.entityPlayer.worldObj, event.entityPlayer, charge * 2.0F);
				arrowEntity.setEffect(TippedArrow.getEffect(arrow));

				if (charge == 1.0F)
					arrowEntity.setIsCritical(true);

				int power = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, event.bow);
				if (power > 0)
					arrowEntity.setDamage(arrowEntity.getDamage() + power * 0.5D + 0.5D);

				int punch = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, event.bow);
				if (punch > 0)
					arrowEntity.setKnockbackStrength(punch);

				if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, event.bow) > 0)
					arrowEntity.setFire(100);

				event.bow.damageItem(1, event.entityPlayer);
				event.entityPlayer.worldObj.playSoundAtEntity(event.entityPlayer, "random.bow", 1.0F, 1.0F / (event.entityPlayer.worldObj.rand.nextFloat() * 0.4F + 1.2F) + charge * 0.5F);

				if (!event.entityPlayer.capabilities.isCreativeMode && --arrow.stackSize <= 0)
					event.entityPlayer.inventory.setInventorySlotContents(i, null);

				if (!event.entityPlayer.worldObj.isRemote)
					event.entityPlayer.worldObj.spawnEntityInWorld(arrowEntity);
				event.setCanceled(true);
				return;
			}
		}
	}

	@SubscribeEvent
	public void onPlayerLoadFromFileEvent(PlayerEvent.LoadFromFile event) {
		if (!ConfigurationHandler.enableEnchants)
			return;
		try {
			File file = event.getPlayerFile(Reference.MOD_ID);
			if (!file.exists()) {
				file.createNewFile();
				return;
			}

			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = br.readLine();
			if (line != null) {
				int seed = Integer.parseInt(line);
				ContainerEnchantment.seeds.put(event.playerUUID, seed);
				br.close();
			}
		} catch (Exception e) {
		}
	}

	@SubscribeEvent
	public void onPlayerSaveFromFileEvent(PlayerEvent.SaveToFile event) {
		if (!ConfigurationHandler.enableEnchants)
			return;
		try {
			File file = event.getPlayerFile(Reference.MOD_ID);
			if (!file.exists()) {
				file.createNewFile();
				return;
			}

			Integer seed = ContainerEnchantment.seeds.get(event.playerUUID);
			if (seed != null) {
				BufferedWriter bw = new BufferedWriter(new FileWriter(file));
				bw.write(seed.toString());
				bw.close();
			}
		} catch (IOException e) {
		}
	}

	@SubscribeEvent
	public void harvestEvent(BlockEvent.HarvestDropsEvent event) {
		if (ConfigurationHandler.enableSilkTouchingMushrooms && event.isSilkTouching)
			if (event.block == Blocks.brown_mushroom_block) {
				event.drops.clear();
				event.drops.add(new ItemStack(ModBlocks.brown_mushroom_block));
			} else if (event.block == Blocks.red_mushroom_block) {
				event.drops.clear();
				event.drops.add(new ItemStack(ModBlocks.red_mushroom_block));
			}

		if (ConfigurationHandler.enableSticksFromDeadBushes)
			if (event.block == Blocks.deadbush) {
				boolean isShears = event.harvester != null && event.harvester.getCurrentEquippedItem() != null && event.harvester.getCurrentEquippedItem().getItem() instanceof ItemShears;
				if (event.harvester == null || event.harvester.getCurrentEquippedItem() == null || !isShears)
					for (int i = 0; i < event.world.rand.nextInt(3); i++)
						event.drops.add(new ItemStack(Items.stick));
			}

		if (ConfigurationHandler.enableShearableCobwebs)
			if (event.block == Blocks.web && event.harvester != null) {
				ItemStack stack = event.harvester.getCurrentEquippedItem();
				if (stack != null && stack.getItem() instanceof ItemShears) {
					event.drops.clear();
					event.drops.add(new ItemStack(Blocks.web));
				}
			}
	}

	@SubscribeEvent
	public void onPlayerInteract(PlayerInteractEvent event) {
		NewAnvil.onPlayerInteract(event);
		GrassPath.onPlayerInteract(event);
		
		BlockStrippedOldLog.onPlayerInteract(event);
		BlockStrippedNewLog.onPlayerInteract(event);
	}

	@SubscribeEvent
	public void onHoeUseEvent(UseHoeEvent event) {
		CoarseDirt.onHoeEvent(event);
	}

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void dropEvent(LivingDropsEvent event) {
		if (event.entityLiving.worldObj.isRemote)
			return;

		if (ConfigurationHandler.enableSkullDrop)
			dropHead(event.entityLiving, event.source, event.lootingLevel, event.drops);

		Random rand = event.entityLiving.worldObj.rand;
		if (ConfigurationHandler.enableMutton && event.entityLiving instanceof EntitySheep) {
			int amount = rand.nextInt(3) + 1 + rand.nextInt(1 + event.lootingLevel);
			for (int i = 0; i < amount; i++)
				if (event.entityLiving.isBurning())
					addDrop(new ItemStack(ModItems.cooked_mutton), event.entityLiving, event.drops);
				else
					addDrop(new ItemStack(ModItems.raw_mutton), event.entityLiving, event.drops);
		}
	}

	private void dropHead(EntityLivingBase entity, DamageSource source, int looting, List<EntityItem> drops) {
		if (isPoweredCreeper(source)) {
			int meta = getHeadMetadata(entity);
			if (meta >= 0)
				addDrop(new ItemStack(Items.skull, 1, meta), entity, drops);
		}
	}

	private void addDrop(ItemStack stack, EntityLivingBase entity, List<EntityItem> list) {
		if (stack.stackSize <= 0)
			return;

		EntityItem entityItem = new EntityItem(entity.worldObj, entity.posX, entity.posY, entity.posZ, stack);
		entityItem.delayBeforeCanPickup = 10;
		list.add(entityItem);
	}

	private boolean isPoweredCreeper(DamageSource source) {
		if (source.isExplosion() && source instanceof EntityDamageSource) {
			Entity entity = ((EntityDamageSource) source).getEntity();
			if (entity != null && entity instanceof EntityCreeper)
				return ((EntityCreeper) entity).getPowered();
		}

		return false;
	}

	private int getHeadMetadata(EntityLivingBase entity) {
		if (entity.getClass() == EntityZombie.class)
			return 2;
		else if (entity.getClass() == EntitySkeleton.class && ((EntitySkeleton) entity).getSkeletonType() == 0)
			return 0;
		else if (entity.getClass() == EntityCreeper.class)
			return 4;

		return -1;
	}

	@SubscribeEvent
	public void teleportEvent(EnderTeleportEvent event) {
		if (ConfigurationHandler.enableEndermite) {
			EntityLivingBase entity = event.entityLiving;
			if (entity instanceof EntityPlayerMP)
				if (entity.getRNG().nextFloat() < 0.05F && entity.worldObj.getGameRules().getGameRuleBooleanValue("doMobSpawning")) {
					EntityEndermite entityendermite = new EntityEndermite(entity.worldObj);
					entityendermite.setSpawnedByPlayer(true);
					entityendermite.setLocationAndAngles(entity.posX, entity.posY, entity.posZ, entity.rotationYaw, entity.rotationPitch);
					entity.worldObj.spawnEntityInWorld(entityendermite);
				}
		}
	}

	@SubscribeEvent
	public void spawnEvent(EntityJoinWorldEvent event) {
		if (event.entity instanceof EntityPig) {
			EntityPig pig = (EntityPig) event.entity;
			if (ConfigurationHandler.enableBeetroot)
				pig.tasks.addTask(4, new EntityAITempt(pig, 1.2, ModItems.beetroot, false));
		} else if (event.entity instanceof EntityChicken) {
			EntityChicken chicken = (EntityChicken) event.entity;
			if (ConfigurationHandler.enableBeetroot)
				chicken.tasks.addTask(3, new EntityAITempt(chicken, 1.0D, ModItems.beetroot_seeds, false));
		} else if (event.entity instanceof EntityWolf) {
			EntityWolf wolf = (EntityWolf) event.entity;
			if (ConfigurationHandler.enableRabbit)
				wolf.targetTasks.addTask(4, new EntityAITargetNonTamed(wolf, EntityRabbit.class, 200, false));
		} else if (event.entity instanceof EntityVillager) {
			EntityVillager villager = (EntityVillager) event.entity;
			for (Object obj : villager.tasks.taskEntries) {
				EntityAITaskEntry entry = (EntityAITaskEntry) obj;
				if (entry.action instanceof EntityAIOpenDoor) {
					villager.tasks.removeTask(entry.action);
					villager.tasks.addTask(entry.priority, new EntityAIOpenCustomDoor(villager, true));
					break;
				}
			}
		}
	}

	@SubscribeEvent
	public void interactEntityEvent(EntityInteractEvent event) {
		ItemStack stack = event.entityPlayer.getCurrentEquippedItem();
		if (stack == null)
			return;
		if (!(event.target instanceof EntityAnimal))
			return;

		EntityAnimal animal = (EntityAnimal) event.target;
		if (!animal.isChild()) {
			if (animal instanceof EntityPig) {
				if (stack.getItem() == ModItems.beetroot && ConfigurationHandler.enableBeetroot)
					setAnimalInLove(animal, event.entityPlayer, stack);
			} else if (animal instanceof EntityChicken)
				if (stack.getItem() == ModItems.beetroot_seeds && ConfigurationHandler.enableBeetroot)
					setAnimalInLove(animal, event.entityPlayer, stack);
		} else if (ConfigurationHandler.enableBabyGrowthBoost && isFoodItem(animal, stack))
			feedBaby(animal, event.entityPlayer, stack);
	}

	private void setAnimalInLove(EntityAnimal animal, EntityPlayer player, ItemStack stack) {
		if (!animal.isInLove()) {
			animal.func_146082_f(player);
			if (!player.capabilities.isCreativeMode)
				if (--stack.stackSize <= 0)
					player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
		}
	}

	private void feedBaby(EntityAnimal animal, EntityPlayer player, ItemStack stack) {
		int currentAge = animal.getGrowingAge();
		int age = (int) (-currentAge * 0.1F);
		animal.setGrowingAge(currentAge + age);
		player.swingItem();

		Random itemRand = animal.worldObj.rand;
		for (int i = 0; i < 3; i++) {
			double d0 = itemRand.nextGaussian() * 0.02D;
			double d1 = itemRand.nextGaussian() * 0.02D;
			double d2 = itemRand.nextGaussian() * 0.02D;
			animal.worldObj.spawnParticle("happyVillager", animal.posX + itemRand.nextFloat() * 0.5, animal.posY + 0.5 + itemRand.nextFloat() * 0.5, animal.posZ + itemRand.nextFloat() * 0.5, d0, d1, d2);
		}

		if (!player.capabilities.isCreativeMode)
			if (--stack.stackSize <= 0)
				player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
	}

	private boolean isFoodItem(EntityAnimal animal, ItemStack food) {
		if (animal.isBreedingItem(food))
			return true;
		else if (animal instanceof EntityPig && food.getItem() == ModItems.beetroot && ConfigurationHandler.enableBeetroot)
			return true;
		else if (animal instanceof EntityChicken && food.getItem() == ModItems.beetroot_seeds && ConfigurationHandler.enableBeetroot)
			return true;
		else
			return false;
	}

	@SubscribeEvent
	public void entityHurtEvent(LivingHurtEvent event) {
		if (!ConfigurationHandler.enableDmgIndicator)
			return;
		int amount = MathHelper.floor_float(Math.min(event.entityLiving.getHealth(), event.ammount) / 2F);
		if (amount <= 0)
			return;

		// If the attacker is a player spawn the hearts aligned and facing it
		if (event.source instanceof EntityDamageSource) {
			EntityDamageSource src = (EntityDamageSource) event.source;
			Entity attacker = src.getSourceOfDamage();
			if (attacker instanceof EntityPlayer && !(attacker instanceof FakePlayer)) {
				EntityPlayer player = (EntityPlayer) attacker;
				Vec3 look = player.getLookVec();
				look.rotateAroundY((float) Math.PI / 2);
				for (int i = 0; i < amount; i++) {
					double x = event.entityLiving.posX - amount * 0.35 * look.xCoord / 2 + i * 0.35 * look.xCoord;
					double y = event.entityLiving.posY + 1.5 + event.entityLiving.worldObj.rand.nextGaussian() * 0.05;
					double z = event.entityLiving.posZ - amount * 0.35 * look.zCoord / 2 + i * 0.35 * look.zCoord;
					EtFuturum.networkWrapper.sendToAllAround(new BlackHeartParticlesMessage(x, y, z), new TargetPoint(player.worldObj.provider.dimensionId, x, y, z, 64));
				}
			}
		}
	}

	@SubscribeEvent
	public void entityStruckByLightning(EntityStruckByLightningEvent event) {
		if (ConfigurationHandler.enableVillagerTurnsIntoWitch && event.entity instanceof EntityVillager) {
			EntityVillager villager = (EntityVillager) event.entity;
			if (!villager.worldObj.isRemote) {
				EntityWitch witch = new EntityWitch(villager.worldObj);
				witch.copyLocationAndAnglesFrom(villager);
				witch.onSpawnWithEgg(null);
				villager.worldObj.spawnEntityInWorld(witch);
				villager.setDead();
			}
		}
	}
	
	//TODO totem
	@SubscribeEvent
	public void beforePlayerHurt(LivingAttackEvent event) {
		Entity entity = event.entity;
		if ((entity == null) || (!(entity instanceof EntityPlayer))) {
			return;
		}
		EntityPlayer player = (EntityPlayer)entity;
		
		handleTotemCheck(player, event);
		
		if (event.isCanceled()) {
	    	event.setResult(null);
		}
		
	}
	
	public void handleTotemCheck(final EntityPlayer player, final LivingAttackEvent event) {
        if (player.getHealth() > Math.round(event.ammount)) {
            return;
        }
        if (!this.playerHasItem(player, new ItemStack(ModItems.totem), false)) {
            return;
        }
        this.decreaseItemByOne(player, ModItems.totem);
        //this.spawnTotemParticles(player);
        player.worldObj.playSoundEffect(player.posX + 0.5, player.posY + 0.5, player.posZ + 0.5, "etfuturum:item.totem_use", 1.0f, player.worldObj.rand.nextFloat() * 0.1f + 0.9f);
        
        player.clearActivePotions();
		player.setHealth(1.0F);
		player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 900, 1));
		player.addPotionEffect(new PotionEffect(Potion.field_76444_x.id, 100, 1));
		
		player.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("util.totemBreak")));

        event.setCanceled(true);
    }
	
	private boolean playerHasItem(final EntityPlayer player, final ItemStack ist, final boolean checkEnabled) {
        for (int slot = 0; slot < player.inventory.mainInventory.length; ++slot) {
            if (player.inventory.mainInventory[slot] != null && player.inventory.mainInventory[slot].isItemEqual(ist)) {
                return true;
            }
        }
        return false;
    }
	
	private void decreaseItemByOne(final EntityPlayer player, final Item item) {
        for (int slot = 0; slot < player.inventory.mainInventory.length; ++slot) {
            if (player.inventory.mainInventory[slot] != null) {
                if (player.inventory.mainInventory[slot].getItem() == item) {
                    player.inventory.decrStackSize(slot, 1);
                    return;
                }
            }
        }
    }
	
}