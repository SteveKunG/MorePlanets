/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.polongnius.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import micdoodle8.mods.galacticraft.api.block.IPartialSealableBlock;
import micdoodle8.mods.galacticraft.api.vector.BlockVec3;
import micdoodle8.mods.galacticraft.core.blocks.BlockTileGC;
import micdoodle8.mods.galacticraft.core.energy.tile.TileBaseUniversalElectrical;
import micdoodle8.mods.galacticraft.core.items.ItemBlockDesc.IBlockShiftDesc;
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
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.planets.polongnius.tileentities.TileEntityUltraVioletSolarPanel;

public class BlockUltraVioletSolarPanel extends BlockTileGC implements IBlockShiftDesc, IPartialSealableBlock
{
    private IIcon[] icons = new IIcon[6];

    public BlockUltraVioletSolarPanel(String name)
    {
        super(Material.iron);
        this.setHardness(1.0F);
        this.setStepSound(soundTypeMetal);
        this.setBlockName(name);
    }

    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.icons[0] = par1IconRegister.registerIcon("polongnius:ultra_violet_solar_top");
        this.icons[1] = par1IconRegister.registerIcon("polongnius:ultra_violet_solar_side");
        this.icons[4] = par1IconRegister.registerIcon("galacticraftasteroids:machine");
        this.icons[5] = par1IconRegister.registerIcon("galacticraftasteroids:machine_output");
        this.blockIcon = this.icons[0];
    }

    @Override
    public CreativeTabs getCreativeTabToDisplayOn()
    {
        return MorePlanetsCore.mpBlocksTab;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        if (meta >= 0)
        {
            int shiftedMeta = meta -= 0;

            if (side == ForgeDirection.getOrientation(shiftedMeta + 2).getOpposite().ordinal())
            {
                return this.icons[5];
            }
            else if (side == ForgeDirection.UP.ordinal())
            {
                return this.icons[0];
            }
            else if (side == ForgeDirection.DOWN.ordinal())
            {
                return this.icons[4];
            }
            else
            {
                return this.icons[1];
            }
        }
        return this.blockIcon;
    }

    @Override
    public boolean canPlaceBlockOnSide(World world, int x1, int y1, int z1, int side)
    {
        for (int y = 1; y <= 2; y++)
        {
            for (int x = -1; x <= 1; x++)
            {
                for (int z = -1; z <= 1; z++)
                {
                    Block block = world.getBlock(x1 + (y == 2 ? x : 0), y1 + y, z1 + (y == 2 ? z : 0));

                    if (block.getMaterial() != Material.air && !block.isReplaceable(world, x1 + x, y1 + y, z1 + z))
                    {
                        return false;
                    }
                }
            }
        }
        return new BlockVec3(x1, y1, z1).newVecSide(side ^ 1).getBlock(world) != PolongniusBlocks.ultra_violet_solar_fake;
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack itemStack)
    {
        int metadata = world.getBlockMetadata(x, y, z);

        int angle = MathHelper.floor_double(entityLiving.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
        int change = 0;

        switch (angle)
        {
        case 0:
            change = 1;
            break;
        case 1:
            change = 2;
            break;
        case 2:
            change = 0;
            break;
        case 3:
            change = 3;
            break;
        }

        if (metadata >= 4)
        {
            world.setBlockMetadataWithNotify(x, y, z, 4 + change, 3);
        }
        else
        {
            world.setBlockMetadataWithNotify(x, y, z, 0 + change, 3);
        }

        TileEntity tile = world.getTileEntity(x, y, z);

        if (tile instanceof TileEntityUltraVioletSolarPanel)
        {
            ((TileEntityUltraVioletSolarPanel) tile).onCreate(new BlockVec3(x, y, z));
        }
    }

    @Override
    public void breakBlock(World var1, int var2, int var3, int var4, Block var5, int var6)
    {
        TileEntity var9 = var1.getTileEntity(var2, var3, var4);

        if (var9 instanceof TileEntityUltraVioletSolarPanel)
        {
            ((TileEntityUltraVioletSolarPanel) var9).onDestroy(var9);
        }
        super.breakBlock(var1, var2, var3, var4, var5, var6);
    }

    @Override
    public boolean onUseWrench(World par1World, int x, int y, int z, EntityPlayer par5EntityPlayer, int side, float hitX, float hitY, float hitZ)
    {
        int metadata = par1World.getBlockMetadata(x, y, z);
        int original = metadata;
        int change = 0;

        if (metadata >= 4)
        {
            original -= 4;
        }

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

        if (metadata >= 4)
        {
            change += 4;
        }

        TileEntity te = par1World.getTileEntity(x, y, z);

        if (te instanceof TileBaseUniversalElectrical)
        {
            ((TileBaseUniversalElectrical) te).updateFacing();
        }
        par1World.setBlockMetadataWithNotify(x, y, z, change, 3);
        return true;
    }

    @Override
    public boolean onMachineActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int side, float hitX, float hitY, float hitZ)
    {
        entityPlayer.openGui(MorePlanetsCore.instance, -1, world, x, y, z);
        return true;
    }

    @Override
    public int damageDropped(int metadata)
    {
        if (metadata >= 4)
        {
            return 4;
        }
        else
        {
            return 0;
        }
    }

    @Override
    public TileEntity createTileEntity(World world, int metadata)
    {
        return new TileEntityUltraVioletSolarPanel(5);
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public String getShiftDescription(int meta)
    {
        return StatCollector.translateToLocal(this.getUnlocalizedName() + ".desc");
    }

    @Override
    public boolean showDescription(int meta)
    {
        return true;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public boolean isSealed(World world, int x, int y, int z, ForgeDirection direction)
    {
        return true;
    }
}