/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.blocks;

import micdoodle8.mods.galacticraft.core.blocks.BlockTileGC;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.planets.fronos.tileentities.TileEntityMineralWaterGenerator;

public class BlockMineralWaterGenerator extends BlockTileGC
{
    private IIcon iconMachineSide;
    private IIcon iconOutput;
    private IIcon iconMineralWaterGenerator;

    public BlockMineralWaterGenerator(String name)
    {
        super(Material.iron);
        this.setHardness(2.0F);
        this.setStepSound(Block.soundTypeMetal);
        this.setBlockName(name);
    }

    @Override
    public CreativeTabs getCreativeTabToDisplayOn()
    {
        return MorePlanetsCore.mpBlocksTab;
    }

    @Override
    public int getRenderType()
    {
        return MorePlanetsCore.proxy.getBlockRender(this);
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.blockIcon = iconRegister.registerIcon("galacticraftasteroids:machine");
        this.iconOutput = iconRegister.registerIcon("galacticraftasteroids:machine_output");
        this.iconMachineSide = iconRegister.registerIcon("galacticraftasteroids:machine_side_warning");
        this.iconMineralWaterGenerator = iconRegister.registerIcon("fronos:mineral_water_generator");
    }

    @Override
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side)
    {
        final int metadata = world.getBlockMetadata(x, y, z);
        return this.getIcon(side, metadata);
    }

    @Override
    public IIcon getIcon(int side, int metadata)
    {
        if (side == 0)
        {
            return this.iconOutput;
        }
        if (side == 1)
        {
            return this.blockIcon;
        }
        if (metadata == 0 && side == 4 || metadata == 1 && side == 5 || metadata == 2 && side == 3 || metadata == 3 && side == 2)
        {
            return this.iconMineralWaterGenerator;
        }
        return this.iconMachineSide;
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack itemStack)
    {
        final int metadata = world.getBlockMetadata(x, y, z);

        final int angle = MathHelper.floor_double(entityLiving.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
        int change = 0;

        switch (angle)
        {
        case 0:
            change = 3;
            break;
        case 1:
            change = 1;
            break;
        case 2:
            change = 2;
            break;
        case 3:
            change = 0;
            break;
        }
        world.setBlockMetadataWithNotify(x, y, z, (metadata & 4) + change, 3);
    }

    @Override
    public boolean onUseWrench(World par1World, int x, int y, int z, EntityPlayer par5EntityPlayer, int side, float hitX, float hitY, float hitZ)
    {
        final int metadata = par1World.getBlockMetadata(x, y, z);
        final int original = metadata;

        int change = 0;

        switch (original)
        {
        case 0:
            change = 3;
            break;
        case 3:
            change = 1;
            break;
        case 1:
            change = 2;
            break;
        case 2:
            change = 0;
            break;
        }
        par1World.setBlockMetadataWithNotify(x, y, z, change, 3);
        return true;
    }

    @Override
    public boolean onMachineActivated(World par1World, int x, int y, int z, EntityPlayer par5EntityPlayer, int side, float hitX, float hitY, float hitZ)
    {
        if (!par1World.isRemote)
        {
            //par5EntityPlayer.openGui(MorePlanetsCore.instance, -1, par1World, x, y, z);
            return true;
        }
        return true;
    }

    @Override
    public TileEntity createTileEntity(World world, int metadata)
    {
        return new TileEntityMineralWaterGenerator();
    }
}