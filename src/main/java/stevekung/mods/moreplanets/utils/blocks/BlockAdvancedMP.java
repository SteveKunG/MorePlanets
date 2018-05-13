package stevekung.mods.moreplanets.utils.blocks;

import java.lang.reflect.Method;

import micdoodle8.mods.galacticraft.core.GCItems;
import micdoodle8.mods.galacticraft.core.util.CompatibilityManager;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class BlockAdvancedMP extends BlockBaseMP implements ITileEntityProvider
{
    public BlockAdvancedMP(Material material)
    {
        super(material);
        this.setHardness(0.6F);
        this.setResistance(2.5F);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (hand != EnumHand.MAIN_HAND)
        {
            return false;
        }

        ItemStack heldItem = player.getHeldItem(hand);

        if (this.useWrench(world, pos, player, hand, heldItem, side, hitX, hitY, hitZ))
        {
            return true;
        }
        if (player.isSneaking())
        {
            if (this.onSneakMachineActivated(world, pos, player, hand, heldItem, side, hitX, hitY, hitZ))
            {
                return true;
            }
        }
        return this.onMachineActivated(world, pos, state, player, hand, heldItem, side, hitX, hitY, hitZ);
    }

    protected boolean useWrench(World world, BlockPos pos, EntityPlayer player, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (this.isUsableWrench(player, heldItem, pos))
        {
            this.damageWrench(player, heldItem, pos);

            if (player.isSneaking())
            {
                if (this.onSneakUseWrench(world, pos, player, hand, heldItem, side, hitX, hitY, hitZ))
                {
                    player.swingArm(hand);
                    return true;
                }
            }
            if (this.onUseWrench(world, pos, player, hand, heldItem, side, hitX, hitY, hitZ))
            {
                player.swingArm(hand);
                return true;
            }
        }
        return false;
    }

    public boolean isUsableWrench(EntityPlayer entityPlayer, ItemStack itemStack, BlockPos pos)
    {
        if (entityPlayer != null && !itemStack.isEmpty())
        {
            Item item = itemStack.getItem();

            if (item == GCItems.wrench)
            {
                return true;
            }

            Class<? extends Item> wrenchClass = item.getClass();

            /**
             * Buildcraft
             */
            try
            {
                Method methodCanWrench = wrenchClass.getMethod("canWrench", EntityPlayer.class, BlockPos.class);
                return (Boolean) methodCanWrench.invoke(item, entityPlayer, pos);
            }
            catch (Exception e) {}

            if (CompatibilityManager.isIc2Loaded())
            {
                /**
                 * Industrialcraft
                 */
                if (wrenchClass == CompatibilityManager.classIC2wrench || wrenchClass == CompatibilityManager.classIC2wrenchElectric )
                {
                    return itemStack.getItemDamage() < itemStack.getMaxDamage();
                }
            }
        }
        return false;
    }

    public boolean damageWrench(EntityPlayer entityPlayer, ItemStack itemStack, BlockPos pos)
    {
        if (this.isUsableWrench(entityPlayer, itemStack, pos))
        {
            Class<? extends Item> wrenchClass = itemStack.getItem().getClass();

            /**
             * Buildcraft
             */
            try
            {
                Method methodWrenchUsed = wrenchClass.getMethod("wrenchUsed", EntityPlayer.class, BlockPos.class);
                methodWrenchUsed.invoke(itemStack.getItem(), entityPlayer, pos);
                return true;
            }
            catch (Exception e) {}

            /**
             * Industrialcraft
             */
            try
            {
                if (wrenchClass == CompatibilityManager.classIC2wrench || wrenchClass == CompatibilityManager.classIC2wrenchElectric )
                {
                    Method methodWrenchDamage = wrenchClass.getMethod("damage", ItemStack.class, Integer.TYPE, EntityPlayer.class);
                    methodWrenchDamage.invoke(itemStack.getItem(), itemStack, 1, entityPlayer);
                    return true;
                }
            }
            catch (Exception e) {}
        }
        return false;
    }

    public boolean onMachineActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        return false;
    }

    public boolean onSneakMachineActivated(World world, BlockPos pos, EntityPlayer entityPlayer, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        return false;
    }

    public boolean onUseWrench(World world, BlockPos pos, EntityPlayer entityPlayer, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        return false;
    }

    public boolean onSneakUseWrench(World world, BlockPos pos, EntityPlayer entityPlayer, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        return this.onUseWrench(world, pos, entityPlayer, hand, heldItem, side, hitX, hitY, hitZ);
    }

    @Override
    public boolean isSideSolid(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side)
    {
        return this.isNormalCube(state, world, pos);
    }
}