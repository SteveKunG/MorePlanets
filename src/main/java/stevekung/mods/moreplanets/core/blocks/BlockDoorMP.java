/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.blocks;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.IconFlipped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.moons.europa.items.EuropaItems;
import stevekung.mods.moreplanets.moons.koentus.items.KoentusItems;
import stevekung.mods.moreplanets.planets.fronos.items.FronosItems;
import stevekung.mods.moreplanets.planets.nibiru.items.NibiruItems;

public class BlockDoorMP extends BlockDoor
{
    @SideOnly(Side.CLIENT)
    private IIcon[] icon_upper;
    @SideOnly(Side.CLIENT)
    private IIcon[] icon_lower;
    private DoorType doorType;

    public static enum DoorType
    {
        ANCIENT_DARK, ORANGE, COCONUT, MAPLE, CRYSTAL, EUROPA;
    }

    public BlockDoorMP(String name, String texture, DoorType type)
    {
        super(Material.wood);
        this.setStepSound(soundTypeWood);
        this.doorType = type;
        this.setBlockName(name);
        this.setBlockTextureName(texture);
        this.setHardness(3.0F);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        return this.icon_lower[0];
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(IBlockAccess access, int x, int y, int z, int direction)
    {
        if (direction != 1 && direction != 0)
        {
            int i1 = this.func_150012_g(access, x, y, z);
            int j1 = i1 & 3;
            boolean flag = (i1 & 4) != 0;
            boolean flag1 = false;
            boolean flag2 = (i1 & 8) != 0;

            if (flag)
            {
                if (j1 == 0 && direction == 2)
                {
                    flag1 = !flag1;
                }
                else if (j1 == 1 && direction == 5)
                {
                    flag1 = !flag1;
                }
                else if (j1 == 2 && direction == 3)
                {
                    flag1 = !flag1;
                }
                else if (j1 == 3 && direction == 4)
                {
                    flag1 = !flag1;
                }
            }
            else
            {
                if (j1 == 0 && direction == 5)
                {
                    flag1 = !flag1;
                }
                else if (j1 == 1 && direction == 3)
                {
                    flag1 = !flag1;
                }
                else if (j1 == 2 && direction == 4)
                {
                    flag1 = !flag1;
                }
                else if (j1 == 3 && direction == 2)
                {
                    flag1 = !flag1;
                }
                if ((i1 & 16) != 0)
                {
                    flag1 = !flag1;
                }
            }
            return flag2 ? this.icon_upper[flag1 ? 1 : 0] : this.icon_lower[flag1 ? 1 : 0];
        }
        else
        {
            return this.icon_lower[0];
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister icon)
    {
        this.icon_upper = new IIcon[2];
        this.icon_lower = new IIcon[2];
        this.icon_upper[0] = icon.registerIcon(this.getTextureName() + "_upper");
        this.icon_lower[0] = icon.registerIcon(this.getTextureName() + "_lower");
        this.icon_upper[1] = new IconFlipped(this.icon_upper[0], true, false);
        this.icon_lower[1] = new IconFlipped(this.icon_lower[0], true, false);
    }

    @Override
    public Item getItemDropped(int meta, Random rand, int fortune)
    {
        if (this.doorType == DoorType.ANCIENT_DARK)
        {
            return (meta & 8) != 0 ? null : NibiruItems.ancient_dark_door;
        }
        else if (this.doorType == DoorType.ORANGE)
        {
            return (meta & 8) != 0 ? null : NibiruItems.orange_door;
        }
        else if (this.doorType == DoorType.COCONUT)
        {
            return (meta & 8) != 0 ? null : FronosItems.coconut_door;
        }
        else if (this.doorType == DoorType.MAPLE)
        {
            return (meta & 8) != 0 ? null : FronosItems.maple_door;
        }
        else if (this.doorType == DoorType.CRYSTAL)
        {
            return (meta & 8) != 0 ? null : KoentusItems.crystal_door;
        }
        else if (this.doorType == DoorType.EUROPA)
        {
            return (meta & 8) != 0 ? null : EuropaItems.europa_door;
        }
        return null;
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition mov, World world, int x, int y, int z)
    {
        if (this.doorType == DoorType.ANCIENT_DARK)
        {
            return new ItemStack(NibiruItems.ancient_dark_door);
        }
        else if (this.doorType == DoorType.ORANGE)
        {
            return new ItemStack(NibiruItems.orange_door);
        }
        else if (this.doorType == DoorType.COCONUT)
        {
            return new ItemStack(FronosItems.coconut_door);
        }
        else if (this.doorType == DoorType.MAPLE)
        {
            return new ItemStack(FronosItems.maple_door);
        }
        else if (this.doorType == DoorType.CRYSTAL)
        {
            return new ItemStack(KoentusItems.crystal_door);
        }
        else if (this.doorType == DoorType.EUROPA)
        {
            return new ItemStack(EuropaItems.europa_door);
        }
        return null;
    }
}