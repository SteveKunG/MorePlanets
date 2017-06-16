/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.entities.ai;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;
import stevekung.mods.moreplanets.planets.fronos.entities.EntityCreamCat;
import stevekung.mods.moreplanets.planets.fronos.tileentities.TileEntityFronosAncientChest;

public class EntityAICreamCatSit extends EntityAIBase
{
    private EntityCreamCat cat;
    private double field_75404_b;
    private int currentTick;
    private int field_75402_d;
    private int maxSittingTicks;
    private int sitableBlockX;
    private int sitableBlockY;
    private int sitableBlockZ;

    public EntityAICreamCatSit(EntityCreamCat cat, double par2)
    {
        this.cat = cat;
        this.field_75404_b = par2;
        this.setMutexBits(5);
    }

    @Override
    public boolean shouldExecute()
    {
        return this.cat.isTamed() && !this.cat.isSitting() && this.cat.getRNG().nextDouble() <= 0.006500000134110451D && this.getNearbySitableBlockDistance();
    }

    @Override
    public boolean continueExecuting()
    {
        return this.currentTick <= this.maxSittingTicks && this.field_75402_d <= 60 && this.isSittableBlock(this.cat.worldObj, this.sitableBlockX, this.sitableBlockY, this.sitableBlockZ);
    }

    @Override
    public void startExecuting()
    {
        this.cat.getNavigator().tryMoveToXYZ(this.sitableBlockX + 0.5D, this.sitableBlockY + 1, this.sitableBlockZ + 0.5D, this.field_75404_b);
        this.currentTick = 0;
        this.field_75402_d = 0;
        this.maxSittingTicks = this.cat.getRNG().nextInt(this.cat.getRNG().nextInt(1200) + 1200) + 1200;
        this.cat.func_70907_r().setSitting(false);
    }

    @Override
    public void resetTask()
    {
        this.cat.setSitting(false);
    }

    @Override
    public void updateTask()
    {
        ++this.currentTick;
        this.cat.func_70907_r().setSitting(false);

        if (this.cat.getDistanceSq(this.sitableBlockX, this.sitableBlockY + 1, this.sitableBlockZ) > 1.0D)
        {
            this.cat.setSitting(false);
            this.cat.getNavigator().tryMoveToXYZ(this.sitableBlockX + 0.5D, this.sitableBlockY + 1, this.sitableBlockZ + 0.5D, this.field_75404_b);
            ++this.field_75402_d;
        }
        else if (!this.cat.isSitting())
        {
            this.cat.setSitting(true);
        }
        else
        {
            --this.field_75402_d;
        }
    }

    protected boolean getNearbySitableBlockDistance()
    {
        int i = (int)this.cat.posY;
        double d0 = 2.147483647E9D;

        for (int j = (int)this.cat.posX - 8; j < this.cat.posX + 8.0D; ++j)
        {
            for (int k = (int)this.cat.posZ - 8; k < this.cat.posZ + 8.0D; ++k)
            {
                if (this.isSittableBlock(this.cat.worldObj, j, i, k) && this.cat.worldObj.isAirBlock(j, i + 1, k))
                {
                    double d1 = this.cat.getDistanceSq(j, i, k);

                    if (d1 < d0)
                    {
                        this.sitableBlockX = j;
                        this.sitableBlockY = i;
                        this.sitableBlockZ = k;
                        d0 = d1;
                    }
                }
            }
        }
        return d0 < 2.147483647E9D;
    }

    private boolean isSittableBlock(World par1World, int par2, int par3, int par4)
    {
        Block block = par1World.getBlock(par2, par3, par4);
        int meta = par1World.getBlockMetadata(par2, par3, par4);

        if (block == Blocks.chest)
        {
            TileEntityChest tileentitychest = (TileEntityChest)par1World.getTileEntity(par2, par3, par4);

            if (tileentitychest.numPlayersUsing < 1)
            {
                return true;
            }
        }
        else if (block == FronosBlocks.fronos_ancient_chest)
        {
            TileEntityFronosAncientChest tileentitychest = (TileEntityFronosAncientChest)par1World.getTileEntity(par2, par3, par4);

            if (tileentitychest.numUsingPlayers < 1)
            {
                return true;
            }
        }
        else
        {
            if (block == Blocks.lit_furnace || block == FronosBlocks.candy_extractor_active || block == FronosBlocks.pink_cake || block == FronosBlocks.white_cake || block == FronosBlocks.chocolate_cake || block == Blocks.cake || block == FronosBlocks.golden_grass || block == FronosBlocks.cream_block)
            {
                return true;
            }
            if (block == FronosBlocks.frosted_cake && meta >= 3)
            {
                return true;
            }
            if (block == Blocks.bed && !BlockBed.isBlockHeadOfBed(meta))
            {
                return true;
            }
            if (block == FronosBlocks.tea_cream_layer && meta <= 5 || block == FronosBlocks.orange_cream_layer && meta <= 5 || block == FronosBlocks.strawberry_cream_layer && meta <= 5 || block == FronosBlocks.chocolate_cream_layer && meta <= 5 || block == FronosBlocks.vanilla_cream_layer && meta <= 5)
            {
                return true;
            }
        }
        return false;
    }
}