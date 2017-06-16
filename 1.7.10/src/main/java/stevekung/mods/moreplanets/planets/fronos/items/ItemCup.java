/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.items.ItemFoodMP;
import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;
import stevekung.mods.moreplanets.planets.polongnius.blocks.PolongniusBlocks;

public class ItemCup extends ItemFoodMP
{
    public ItemCup(String name)
    {
        super();
        this.setMaxStackSize(16);
        this.setTextureName("mpcore:blank");
        this.setUnlocalizedName(name);
        this.setHasSubtypes(true);
    }

    @Override
    public ItemStack onEaten(ItemStack itemStack, World world, EntityPlayer player)
    {
        int meta = itemStack.getItemDamage();

        if (!player.capabilities.isCreativeMode)
        {
            --itemStack.stackSize;
        }
        if (!world.isRemote)
        {
            if (meta == 1)
            {
                if (!player.isPotionActive(Potion.regeneration) || !player.isPotionActive(Potion.moveSpeed))
                {
                    player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 1200, 2));
                    player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 2400, 3));
                }
            }
            else if (meta == 2)
            {
                player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 2400, 2));
            }
            else if (meta == 3)
            {
                player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 1200, 2));
                player.addPotionEffect(new PotionEffect(Potion.jump.id, 2400, 2));
            }
            else if (meta == 4)
            {
                player.addPotionEffect(new PotionEffect(Potion.field_76443_y.id, 120, 3));
            }
            else if (meta == 5)
            {
                player.addPotionEffect(new PotionEffect(Potion.resistance.id, 1200, 1));
                player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 2400, 3));
            }
            else if (meta == 6)
            {
                player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 1200, 2));
            }
        }

        world.playSoundAtEntity(player, "random.drink", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
        this.onFoodEaten(itemStack, world, player);
        player.getFoodStats().func_151686_a(this, itemStack);

        if (meta >= 1)
        {
            if (!player.inventory.addItemStackToInventory(new ItemStack(FronosItems.cup)))
            {
                player.dropPlayerItemWithRandomChoice(new ItemStack(FronosItems.cup, 1, 0), false);
            }
        }
        return itemStack;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack itemStack)
    {
        if (itemStack.getItemDamage() >= 1)
        {
            return 32;
        }
        return 0;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
        MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(world, player, true);

        if (itemStack.getItemDamage() >= 1)
        {
            player.setItemInUse(itemStack, this.getMaxItemUseDuration(itemStack));
        }
        if (movingobjectposition == null)
        {
            return itemStack;
        }
        if (movingobjectposition.typeOfHit == MovingObjectType.BLOCK)
        {
            if (itemStack.getItemDamage() == 0)
            {
                int i = movingobjectposition.blockX;
                int j = movingobjectposition.blockY;
                int k = movingobjectposition.blockZ;

                if (!world.canMineBlock(player, i, j, k))
                {
                    return itemStack;
                }
                if (!player.canPlayerEdit(i, j, k, movingobjectposition.sideHit, itemStack))
                {
                    return itemStack;
                }

                if (world.getBlock(i, j, k) == FronosBlocks.mineral_water && world.getBlockMetadata(i, j, k) == 0)
                {
                    --itemStack.stackSize;
                    world.setBlockToAir(i, j, k);

                    if (itemStack.stackSize <= 0)
                    {
                        return new ItemStack(FronosItems.cup, 1, 1);
                    }
                    if (!player.inventory.addItemStackToInventory(new ItemStack(FronosItems.cup, 1, 1)))
                    {
                        player.dropPlayerItemWithRandomChoice(new ItemStack(FronosItems.cup, 1, 1), false);
                    }
                }
                else if (world.getBlock(i, j, k) == FronosBlocks.ovantine && world.getBlockMetadata(i, j, k) == 0)
                {
                    --itemStack.stackSize;
                    world.setBlockToAir(i, j, k);

                    if (itemStack.stackSize <= 0)
                    {
                        return new ItemStack(FronosItems.cup, 1, 2);
                    }
                    if (!player.inventory.addItemStackToInventory(new ItemStack(FronosItems.cup, 1, 2)))
                    {
                        player.dropPlayerItemWithRandomChoice(new ItemStack(FronosItems.cup, 1, 2), false);
                    }
                }
                else if (world.getBlock(i, j, k) == FronosBlocks.coconut_milk && world.getBlockMetadata(i, j, k) == 0)
                {
                    --itemStack.stackSize;
                    world.setBlockToAir(i, j, k);

                    if (itemStack.stackSize <= 0)
                    {
                        return new ItemStack(FronosItems.cup, 1, 3);
                    }
                    if (!player.inventory.addItemStackToInventory(new ItemStack(FronosItems.cup, 1, 3)))
                    {
                        player.dropPlayerItemWithRandomChoice(new ItemStack(FronosItems.cup, 1, 3), false);
                    }
                }
                else if (world.getBlock(i, j, k) == PolongniusBlocks.cheese_of_milk && world.getBlockMetadata(i, j, k) == 0)
                {
                    --itemStack.stackSize;
                    world.setBlockToAir(i, j, k);

                    if (itemStack.stackSize <= 0)
                    {
                        return new ItemStack(FronosItems.cup, 1, 4);
                    }
                    if (!player.inventory.addItemStackToInventory(new ItemStack(FronosItems.cup, 1, 4)))
                    {
                        player.dropPlayerItemWithRandomChoice(new ItemStack(FronosItems.cup, 1, 4), false);
                    }
                }
                else if (world.getBlock(i, j, k) == FronosBlocks.tea && world.getBlockMetadata(i, j, k) == 0)
                {
                    --itemStack.stackSize;
                    world.setBlockToAir(i, j, k);

                    if (itemStack.stackSize <= 0)
                    {
                        return new ItemStack(FronosItems.cup, 1, 5);
                    }
                    if (!player.inventory.addItemStackToInventory(new ItemStack(FronosItems.cup, 1, 5)))
                    {
                        player.dropPlayerItemWithRandomChoice(new ItemStack(FronosItems.cup, 1, 5), false);
                    }
                }
                else if (world.getBlock(i, j, k) == FronosBlocks.caramel && world.getBlockMetadata(i, j, k) == 3)
                {
                    --itemStack.stackSize;
                    world.setBlockToAir(i, j, k);

                    if (itemStack.stackSize <= 0)
                    {
                        return new ItemStack(FronosItems.cup, 1, 6);
                    }
                    if (!player.inventory.addItemStackToInventory(new ItemStack(FronosItems.cup, 1, 6)))
                    {
                        player.dropPlayerItemWithRandomChoice(new ItemStack(FronosItems.cup, 1, 6), false);
                    }
                }
            }
        }
        return itemStack;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack itemStack)
    {
        if (itemStack.getItemDamage() >= 1)
        {
            return EnumAction.drink;
        }
        return EnumAction.none;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs creativeTab, List subTypes)
    {
        for (int meta = 0; meta < this.getItemVariantsName().length; ++meta)
        {
            subTypes.add(new ItemStack(this, 1, meta));
        }
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float par8, float par9, float par10)
    {
        Block block = world.getBlock(x, y, z);
        int meta = world.getBlockMetadata(x, y, z);

        int angle = MathHelper.floor_double(player.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
        byte change = 0;

        switch (angle)
        {
        case 0:
            change = 0;
            break;
        case 1:
            change = 3;
            break;
        case 2:
            change = 1;
            break;
        case 3:
            change = 2;
            break;
        }

        if (player.canPlayerEdit(x, y, z, side, itemStack) && block == FronosBlocks.space_oyster_close)
        {
            if (itemStack.getItemDamage() == 1)
            {
                if (world.isRemote)
                {
                    return true;
                }
                else
                {
                    if (world.rand.nextInt(5) == 0)
                    {
                        world.setBlock(x, y, z, FronosBlocks.space_oyster_open, meta, 3);
                    }
                    --itemStack.stackSize;
                    player.dropPlayerItemWithRandomChoice(new ItemStack(FronosItems.cup, 1, 0), false);
                    return true;
                }
            }
            else
            {
                return false;
            }
        }
        else if (player.canPlayerEdit(x, y, z, side, itemStack) && block == FronosBlocks.cavern_oyster_close)
        {
            if (itemStack.getItemDamage() == 5)
            {
                if (world.isRemote)
                {
                    return true;
                }
                else
                {
                    if (world.rand.nextInt(7) == 0)
                    {
                        world.setBlock(x, y, z, FronosBlocks.cavern_oyster_open, meta, 3);
                    }
                    --itemStack.stackSize;
                    player.dropPlayerItemWithRandomChoice(new ItemStack(FronosItems.cup, 1, 0), false);
                    return true;
                }
            }
            else
            {
                return false;
            }
        }

        if (world.getBlock(x, y, z) != Blocks.snow_layer)
        {
            if (side == 0)
            {
                y--;
            }
            if (side == 1)
            {
                y++;
            }
            if (side == 2)
            {
                z--;
            }
            if (side == 3)
            {
                z++;
            }
            if (side == 4)
            {
                x--;
            }
            if (side == 5)
            {
                x++;
            }
            if (!world.isAirBlock(x, y, z))
            {
                return false;
            }
        }
        if (!player.canPlayerEdit(x, y, z, side, itemStack))
        {
            return false;
        }
        else
        {
            if (itemStack.getItemDamage() == 0)
            {
                if (FronosBlocks.cup.canPlaceBlockAt(world, x, y, z))
                {
                    --itemStack.stackSize;
                    world.playSoundEffect(x, y, z, FronosBlocks.cup.stepSound.func_150496_b(), (FronosBlocks.cup.stepSound.getVolume() + 1.0F) / 2.0F, FronosBlocks.cup.stepSound.getPitch() * 0.8F);
                    world.setBlock(x, y, z, FronosBlocks.cup, change, 3);
                    return true;
                }
            }
            else if (itemStack.getItemDamage() == 1)
            {
                if (FronosBlocks.mineral_water_cup.canPlaceBlockAt(world, x, y, z))
                {
                    --itemStack.stackSize;
                    world.playSoundEffect(x, y, z, FronosBlocks.cup.stepSound.func_150496_b(), (FronosBlocks.cup.stepSound.getVolume() + 1.0F) / 2.0F, FronosBlocks.cup.stepSound.getPitch() * 0.8F);
                    world.setBlock(x, y, z, FronosBlocks.mineral_water_cup, change, 3);
                    return true;
                }
            }
            else if (itemStack.getItemDamage() == 2)
            {
                if (FronosBlocks.ovantine_cup.canPlaceBlockAt(world, x, y, z))
                {
                    --itemStack.stackSize;
                    world.playSoundEffect(x, y, z, FronosBlocks.cup.stepSound.func_150496_b(), (FronosBlocks.cup.stepSound.getVolume() + 1.0F) / 2.0F, FronosBlocks.cup.stepSound.getPitch() * 0.8F);
                    world.setBlock(x, y, z, FronosBlocks.ovantine_cup, change, 3);
                    return true;
                }
            }
            else if (itemStack.getItemDamage() == 3)
            {
                if (FronosBlocks.coconut_milk_cup.canPlaceBlockAt(world, x, y, z))
                {
                    --itemStack.stackSize;
                    world.playSoundEffect(x, y, z, FronosBlocks.cup.stepSound.func_150496_b(), (FronosBlocks.cup.stepSound.getVolume() + 1.0F) / 2.0F, FronosBlocks.cup.stepSound.getPitch() * 0.8F);
                    world.setBlock(x, y, z, FronosBlocks.coconut_milk_cup, change, 3);
                    return true;
                }
            }
            else if (itemStack.getItemDamage() == 4)
            {
                if (FronosBlocks.cheese_of_milk_cup.canPlaceBlockAt(world, x, y, z))
                {
                    --itemStack.stackSize;
                    world.playSoundEffect(x, y, z, FronosBlocks.cup.stepSound.func_150496_b(), (FronosBlocks.cup.stepSound.getVolume() + 1.0F) / 2.0F, FronosBlocks.cup.stepSound.getPitch() * 0.8F);
                    world.setBlock(x, y, z, FronosBlocks.cheese_of_milk_cup, change, 3);
                    return true;
                }
            }
            else if (itemStack.getItemDamage() == 5)
            {
                if (FronosBlocks.tea_cup.canPlaceBlockAt(world, x, y, z))
                {
                    --itemStack.stackSize;
                    world.playSoundEffect(x, y, z, FronosBlocks.cup.stepSound.func_150496_b(), (FronosBlocks.cup.stepSound.getVolume() + 1.0F) / 2.0F, FronosBlocks.cup.stepSound.getPitch() * 0.8F);
                    world.setBlock(x, y, z, FronosBlocks.tea_cup, change, 3);
                    return true;
                }
            }
            else if (itemStack.getItemDamage() == 6)
            {
                if (FronosBlocks.caramel_cup.canPlaceBlockAt(world, x, y, z))
                {
                    --itemStack.stackSize;
                    world.playSoundEffect(x, y, z, FronosBlocks.cup.stepSound.func_150496_b(), (FronosBlocks.cup.stepSound.getVolume() + 1.0F) / 2.0F, FronosBlocks.cup.stepSound.getPitch() * 0.8F);
                    world.setBlock(x, y, z, FronosBlocks.caramel_cup, change, 3);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int getFoodAmount(ItemStack itemStack)
    {
        return 6;
    }

    @Override
    public float getFoodSaturation(ItemStack itemStack)
    {
        return 0.6F;
    }

    @Override
    public String[] getItemVariantsName()
    {
        return new String[] { "empty", "mineral_water", "ovantine_cup", "coconut_milk", "cheese_of_milk", "tea", "caramel" };
    }

    @Override
    public String getResourceLocation()
    {
        return null;
    }
}