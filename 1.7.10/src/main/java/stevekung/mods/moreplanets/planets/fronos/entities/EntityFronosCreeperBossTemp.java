package stevekung.mods.moreplanets.planets.fronos.entities;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import micdoodle8.mods.galacticraft.api.GalacticraftRegistry;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.entities.EntityAIArrowAttack;
import micdoodle8.mods.galacticraft.core.network.PacketSimple;
import micdoodle8.mods.galacticraft.core.network.PacketSimple.EnumSimplePacket;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraftforge.common.ChestGenHooks;
import stevekung.mods.moreplanets.core.init.MPItems;
import stevekung.mods.moreplanets.planets.diona.entities.EntityDionaCreeperBoss;
import stevekung.mods.moreplanets.planets.fronos.items.FronosItems;
import stevekung.mods.moreplanets.planets.fronos.tileentities.TileEntityFronosTreasureChest;

public class EntityFronosCreeperBossTemp extends EntityDionaCreeperBoss
{
    public EntityFronosCreeperBossTemp(World world)
    {
        super(world);
        this.setSize(1.5F, 8.5F);
        this.isImmuneToFire = true;
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIArrowAttack(this, 1.0D, 25, 20.0F));
        this.tasks.addTask(2, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(3, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
    }

    public EntityFronosCreeperBossTemp(World world, Vector3 vec)
    {
        this(world);
        this.setPosition(vec.x, vec.y, vec.z);
    }

    @Override
    public ItemStack getPickedResult(MovingObjectPosition target)
    {
        return new ItemStack(MPItems.spawn_egg_mp, 1, 1038);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void onDeathUpdate()
    {
        ++this.deathTicks;

        this.healRemaining = 0;

        if (this.deathTicks >= 180 && this.deathTicks <= 200)
        {
            float f = (this.rand.nextFloat() - 0.5F) * 1.5F;
            float f1 = (this.rand.nextFloat() - 0.5F) * 2.0F;
            float f2 = (this.rand.nextFloat() - 0.5F) * 1.5F;
            this.worldObj.spawnParticle("hugeexplosion", this.posX + f, this.posY + 2.0D + f1, this.posZ + f2, 0.0D, 0.0D, 0.0D);
        }

        int i;
        int j;

        if (!this.worldObj.isRemote)
        {
            if (this.deathTicks >= 180 && this.deathTicks % 5 == 0)
            {
                GalacticraftCore.packetPipeline.sendToAllAround(new PacketSimple(EnumSimplePacket.C_PLAY_SOUND_EXPLODE, new Object[] { }), new TargetPoint(this.worldObj.provider.dimensionId, this.posX, this.posY, this.posZ, 40.0D));
            }

            if (this.deathTicks > 150 && this.deathTicks % 5 == 0)
            {
                i = 30;

                while (i > 0)
                {
                    j = EntityXPOrb.getXPSplit(i);
                    i -= j;
                    this.worldObj.spawnEntityInWorld(new EntityXPOrb(this.worldObj, this.posX, this.posY, this.posZ, j));
                }
            }

            if (this.deathTicks == 1)
            {
                GalacticraftCore.packetPipeline.sendToAllAround(new PacketSimple(EnumSimplePacket.C_PLAY_SOUND_BOSS_DEATH, new Object[] {}), new TargetPoint(this.worldObj.provider.dimensionId, this.posX, this.posY, this.posZ, 40.0D));
            }
        }

        this.moveEntity(0.0D, 0.10000000149011612D, 0.0D);
        this.renderYawOffset = this.rotationYaw += 20.0F;

        if (this.deathTicks == 200 && !this.worldObj.isRemote)
        {
            i = 20;

            while (i > 0)
            {
                j = EntityXPOrb.getXPSplit(i);
                i -= j;
                this.worldObj.spawnEntityInWorld(new EntityXPOrb(this.worldObj, this.posX, this.posY, this.posZ, j));
            }

            for (TileEntity tile : (List<TileEntity>) this.worldObj.loadedTileEntityList)
            {
                if (tile instanceof TileEntityFronosTreasureChest)
                {
                    double d3 = tile.xCoord + 0.5D - this.posX;
                    double d4 = tile.yCoord + 0.5D - this.posY;
                    double d5 = tile.zCoord + 0.5D - this.posZ;
                    double dSq = d3 * d3 + d4 * d4 + d5 * d5;
                    TileEntityFronosTreasureChest chest = (TileEntityFronosTreasureChest) tile;

                    if (dSq < 10000)
                    {
                        if (!chest.locked)
                        {
                            chest.locked = true;
                        }

                        for (int k = 0; k < chest.getSizeInventory(); k++)
                        {
                            chest.setInventorySlotContents(k, null);
                        }
                        ChestGenHooks info = ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST);
                        WeightedRandomChestContent.generateChestContents(this.rand, info.getItems(this.rand), chest, info.getCount(this.rand));
                        WeightedRandomChestContent.generateChestContents(this.rand, info.getItems(this.rand), chest, info.getCount(this.rand));
                        WeightedRandomChestContent.generateChestContents(this.rand, info.getItems(this.rand), chest, info.getCount(this.rand));
                        chest.setInventorySlotContents(this.rand.nextInt(chest.getSizeInventory()), this.getGuaranteedLoot(this.rand));
                        break;
                    }
                }
            }

            this.entityDropItem(new ItemStack(FronosItems.fronos_dungeon_key, 1, 0), 0.5F);
            super.setDead();

            if (this.spawner != null)
            {
                this.spawner.isBossDefeated = true;
                this.spawner.boss = null;
                this.spawner.spawned = false;
            }
        }
    }

    @Override
    public ItemStack getGuaranteedLoot(Random rand)
    {
        List<ItemStack> stackList = GalacticraftRegistry.getDungeonLoot(6);
        return stackList.get(rand.nextInt(stackList.size())).copy();
    }
}