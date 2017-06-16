/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.blocks;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.planets.fronos.items.FronosItems;
import stevekung.mods.moreplanets.planets.fronos.tileentities.TileEntityCaramelCup;

public class BlockCaramelCup extends BlockFilledCup
{
    public BlockCaramelCup(String name)
    {
        super();
        this.setBlockName(name);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        if (player.isPotionActive(Potion.regeneration.id))
        {
            return false;
        }
        else
        {
            player.getFoodStats().addStats(6, 0.6F);
            player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 1200, 2));
            world.setBlock(x, y, z, FronosBlocks.cup, world.getBlockMetadata(x, y, z), 3);
            world.playSoundEffect(x, y, z, "random.drink", 0.5F, world.rand.nextFloat() * 0.1F + 1.2F);
            return true;
        }
    }

    @Override
    public Item getItemDropped(int par1, Random par2Random, int par3)
    {
        return FronosItems.cup;
    }

    @Override
    public int damageDropped(int meta)
    {
        return 6;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new TileEntityCaramelCup();
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
    {
        return new ItemStack(FronosItems.cup, 1, 6);
    }
}