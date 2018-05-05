package stevekung.mods.moreplanets.planets.nibiru.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import micdoodle8.mods.galacticraft.api.block.IDetectableResource;
import micdoodle8.mods.galacticraft.api.block.ITerraformableBlock;
import micdoodle8.mods.galacticraft.core.GCBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import stevekung.mods.moreplanets.utils.blocks.BlockBaseMP;
import stevekung.mods.moreplanets.utils.blocks.EnumSortCategoryBlock;

public class BlockOilOre extends BlockBaseMP implements IDetectableResource, ITerraformableBlock
{
    private static final AxisAlignedBB AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.875D, 1.0D);

    public BlockOilOre(String name)
    {
        super(Material.ROCK);
        this.setHardness(1.5F);
        this.setResistance(4.0F);
        this.setUnlocalizedName(name);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        return AABB;
    }

    @Override
    public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player)
    {
        return true;
    }

    @Override
    public int quantityDropped(Random rand)
    {
        return 0;
    }

    @Override
    public EnumPushReaction getMobilityFlag(IBlockState state)
    {
        return EnumPushReaction.NORMAL;
    }

    @Override
    public boolean isValueable(IBlockState state)
    {
        return true;
    }

    @Override
    public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity tile, ItemStack heldStack)
    {
        player.addExhaustion(0.025F);

        if (this.canSilkHarvest(world, pos, world.getBlockState(pos), player) && EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH, heldStack) > 0)
        {
            List<ItemStack> items = new ArrayList<>();
            ItemStack itemstack = this.getSilkTouchDrop(state);

            if (!itemstack.isEmpty())
            {
                items.add(itemstack);
            }

            ForgeEventFactory.fireBlockHarvesting(items, world, pos, world.getBlockState(pos), 0, 1.0f, true, player);

            for (ItemStack is : items)
            {
                Block.spawnAsEntity(world, pos, is);
            }
        }
        else
        {
            world.setBlockState(pos, GCBlocks.crudeOil.getDefaultState());
        }
    }

    @Override
    public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity)
    {
        entity.motionX *= 0.4D;
        entity.motionZ *= 0.4D;
    }

    @Override
    public boolean isTerraformable(World world, BlockPos pos)
    {
        return !world.getBlockState(pos.up()).isOpaqueCube();
    }

    @Override
    public EnumSortCategoryBlock getBlockCategory()
    {
        return EnumSortCategoryBlock.ORE;
    }
}