/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.items;

import java.util.Iterator;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList.EntityEggInfo;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Facing;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.init.MPEntities;

public class ItemMonsterPlacerMP extends ItemMorePlanet
{
    public ItemMonsterPlacerMP(String name)
    {
        super();
        this.setUnlocalizedName(name);
        this.setHasSubtypes(true);
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float par8, float par9, float par10)
    {
        if (world.isRemote)
        {
            return true;
        }
        else
        {
            Block block = world.getBlock(x, y, z);
            x += Facing.offsetsXForSide[side];
            y += Facing.offsetsYForSide[side];
            z += Facing.offsetsZForSide[side];
            double d0 = 0.0D;

            if (side == 1 && block.getRenderType() == 11)
            {
                d0 = 0.5D;
            }

            Entity entity = this.spawnEntity(world, itemStack.getItemDamage(), x + 0.5D, y + d0, z + 0.5D);

            if (entity != null)
            {
                if (entity instanceof EntityLivingBase && itemStack.hasDisplayName())
                {
                    ((EntityLiving)entity).setCustomNameTag(itemStack.getDisplayName());
                }
                if (!player.capabilities.isCreativeMode)
                {
                    --itemStack.stackSize;
                }
            }
            return true;
        }
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
        if (world.isRemote)
        {
            return itemStack;
        }
        else
        {
            MovingObjectPosition moving = this.getMovingObjectPositionFromPlayer(world, player, true);

            if (moving == null)
            {
                return itemStack;
            }
            else
            {
                if (moving.typeOfHit == MovingObjectType.BLOCK)
                {
                    int x = moving.blockX;
                    int y = moving.blockY;
                    int z = moving.blockZ;

                    if (!world.canMineBlock(player, x, y, z))
                    {
                        return itemStack;
                    }
                    if (!player.canPlayerEdit(x, y, z, moving.sideHit, itemStack))
                    {
                        return itemStack;
                    }
                    if (world.getBlock(x, y, z) instanceof BlockLiquid)
                    {
                        Entity entity = this.spawnEntity(world, itemStack.getItemDamage(), x, y, z);

                        if (entity != null)
                        {
                            if (entity instanceof EntityLivingBase && itemStack.hasDisplayName())
                            {
                                ((EntityLiving)entity).setCustomNameTag(itemStack.getDisplayName());
                            }
                            if (!player.capabilities.isCreativeMode)
                            {
                                --itemStack.stackSize;
                            }
                        }
                    }
                }
                return itemStack;
            }
        }
    }

    public Entity spawnEntity(World world, int meta, double x, double y, double z)
    {
        if (!world.isRemote)
        {
            if (!MPEntities.entityEggs.containsKey(Integer.valueOf(meta)))
            {
                return null;
            }

            Entity entityToSpawn = MPEntities.createEntityByID(meta, world);

            if (entityToSpawn != null && entityToSpawn instanceof EntityLivingBase)
            {
                EntityLiving entityliving = (EntityLiving)entityToSpawn;

                entityToSpawn.setLocationAndAngles(x, y, z, world.rand.nextFloat() * 360.0F, 0.0F);
                entityliving.onSpawnWithEgg((IEntityLivingData)null);
                world.spawnEntityInWorld(entityToSpawn);
                ((EntityLiving)entityToSpawn).playLivingSound();
            }
            return entityToSpawn;
        }
        return null;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs creativeTabs, List list)
    {
        Iterator<EntityEggInfo> eggInfo = MPEntities.entityEggs.values().iterator();

        while (eggInfo.hasNext())
        {
            EntityEggInfo eggInfo2 = eggInfo.next();
            list.add(new ItemStack(this, 1, eggInfo2.spawnedID));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack itemStack, int render)
    {
        EntityEggInfo info = MPEntities.entityEggs.get(Integer.valueOf(itemStack.getItemDamage()));
        return info != null ? render == 0 ? info.primaryColor : info.secondaryColor : 16777215;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean requiresMultipleRenderPasses()
    {
        return true;
    }

    @Override
    public String getItemStackDisplayName(ItemStack itemStack)
    {
        String s = StatCollector.translateToLocal(Items.spawn_egg.getUnlocalizedName() + ".name");
        return s + " " + StatCollector.translateToLocal("entity.MorePlanet." + MPEntities.getStringFromID(itemStack.getItemDamage()) + ".name");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister icon) {}

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamageForRenderPass(int meta, int render)
    {
        return Items.spawn_egg.getIconFromDamageForRenderPass(meta, render);
    }
}