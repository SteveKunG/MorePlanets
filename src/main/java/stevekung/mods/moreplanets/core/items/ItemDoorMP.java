/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.blocks.BlockDoorMP.DoorType;
import stevekung.mods.moreplanets.moons.europa.blocks.EuropaBlocks;
import stevekung.mods.moreplanets.moons.koentus.blocks.KoentusBlocks;
import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;
import stevekung.mods.moreplanets.planets.nibiru.blocks.NibiruBlocks;

public class ItemDoorMP extends ItemMorePlanet
{
    private DoorType doorType;

    public ItemDoorMP(String name, DoorType type)
    {
        this.doorType = type;
        this.setUnlocalizedName(name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        if (this.doorType == DoorType.ANCIENT_DARK)
        {
            this.itemIcon = iconRegister.registerIcon("nibiru:ancient_dark_door");
        }
        else if (this.doorType == DoorType.ORANGE)
        {
            this.itemIcon = iconRegister.registerIcon("nibiru:orange_door");
        }
        else if (this.doorType == DoorType.COCONUT)
        {
            this.itemIcon = iconRegister.registerIcon("fronos:coconut_door");
        }
        else if (this.doorType == DoorType.MAPLE)
        {
            this.itemIcon = iconRegister.registerIcon("fronos:maple_door");
        }
        else if (this.doorType == DoorType.CRYSTAL)
        {
            this.itemIcon = iconRegister.registerIcon("koentus:crystal_door");
        }
        else if (this.doorType == DoorType.EUROPA)
        {
            this.itemIcon = iconRegister.registerIcon("europa:europa_door");
        }
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float par8, float par9, float par10)
    {
        y++;
        Block block = null;

        if (this.doorType == DoorType.ANCIENT_DARK)
        {
            block = NibiruBlocks.ancient_dark_door;
        }
        else if (this.doorType == DoorType.ORANGE)
        {
            block = NibiruBlocks.orange_door;
        }
        else if (this.doorType == DoorType.COCONUT)
        {
            block = FronosBlocks.coconut_door;
        }
        else if (this.doorType == DoorType.MAPLE)
        {
            block = FronosBlocks.maple_door;
        }
        else if (this.doorType == DoorType.CRYSTAL)
        {
            block = KoentusBlocks.crystal_door;
        }
        else if (this.doorType == DoorType.EUROPA)
        {
            block = EuropaBlocks.europa_door;
        }

        if (side != 1)
        {
            return false;
        }
        if (!player.canPlayerEdit(x, y, z, side, stack) || !player.canPlayerEdit(x, y + 1, z, side, stack))
        {
            return false;
        }
        if (!block.canPlaceBlockAt(world, x, y, z))
        {
            return false;
        }

        int i = MathHelper.floor_double((player.rotationYaw + 180.0F) * 4.0F / 360.0F - 0.5D) & 0x3;
        this.placeDoorBlock(world, x, y, z, i, block);
        stack.stackSize -= 1;
        return true;
    }

    private void placeDoorBlock(World world, int x, int y, int z, int side, Block block)
    {
        int i = 0;
        int j = 0;

        if (side == 0)
        {
            j = 1;
        }
        if (side == 1)
        {
            i = -1;
        }
        if (side == 2)
        {
            j = -1;
        }
        if (side == 3)
        {
            i = 1;
        }

        int k = (world.getBlock(x - i, y, z - j).isNormalCube() ? 1 : 0) + (world.getBlock(x - i, y + 1, z - j).isNormalCube() ? 1 : 0);
        int m = (world.getBlock(x + i, y, z + j).isNormalCube() ? 1 : 0) + (world.getBlock(x + i, y + 1, z + j).isNormalCube() ? 1 : 0);
        int n = world.getBlock(x - i, y, z - j) == block || world.getBlock(x - i, y + 1, z - j) == block ? 1 : 0;
        int i1 = world.getBlock(x + i, y, z + j) == block || world.getBlock(x + i, y + 1, z + j) == block ? 1 : 0;
        int i2 = 0;

        if (n != 0 && i1 == 0)
        {
            i2 = 1;
        }
        else if (m > k)
        {
            i2 = 1;
        }

        world.setBlock(x, y, z, block, side, 2);
        world.setBlock(x, y + 1, z, block, 0x8 | (i2 != 0 ? 1 : 0), 2);
        world.notifyBlocksOfNeighborChange(x, y, z, block);
        world.notifyBlocksOfNeighborChange(x, y + 1, z, block);
        world.playSoundEffect(x, y, z, block.stepSound.func_150496_b(), (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);
    }
}