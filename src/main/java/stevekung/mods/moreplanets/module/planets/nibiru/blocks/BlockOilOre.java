package stevekung.mods.moreplanets.module.planets.nibiru.blocks;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

import micdoodle8.mods.galacticraft.api.block.IDetectableResource;
import micdoodle8.mods.galacticraft.api.block.ITerraformableBlock;
import micdoodle8.mods.galacticraft.core.GCBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import stevekung.mods.moreplanets.util.blocks.BlockBaseMP;
import stevekung.mods.moreplanets.util.blocks.EnumSortCategoryBlock;

public class BlockOilOre extends BlockBaseMP implements IDetectableResource, ITerraformableBlock
{
    public BlockOilOre(String name)
    {
        super(Material.rock);
        this.setHardness(1.5F);
        this.setResistance(4.0F);
        this.setUnlocalizedName(name);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(World world, BlockPos pos, IBlockState state)
    {
        float f = 0.125F;
        return AxisAlignedBB.fromBounds(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1, pos.getY() + 1 - f, pos.getZ() + 1);
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
    public int getMobilityFlag()
    {
        return 0;
    }

    @Override
    public boolean isValueable(IBlockState state)
    {
        return true;
    }

    @Override
    public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity te)
    {
        player.addExhaustion(0.025F);

        if (this.canSilkHarvest(world, pos, world.getBlockState(pos), player) && EnchantmentHelper.getSilkTouchModifier(player))
        {
            List<ItemStack> items = Lists.newArrayList();
            ItemStack itemstack = this.createStackedBlock(state);

            if (itemstack != null)
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
    public void onEntityCollidedWithBlock(World world, BlockPos pos, Entity entity)
    {
        entity.motionX *= 0.4D;
        entity.motionZ *= 0.4D;
    }

    @Override
    public boolean isTerraformable(World world, BlockPos pos)
    {
        return true && !world.getBlockState(pos.up()).getBlock().isOpaqueCube();
    }

    @Override
    public EnumSortCategoryBlock getBlockCategory(int meta)
    {
        return EnumSortCategoryBlock.ORE;
    }

    @Override
    public String getName()
    {
        return "oil_ore";
    }
}