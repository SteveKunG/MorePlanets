package stevekung.mods.moreplanets.asteroids.darkasteroids.blocks;

import java.util.Random;

import net.minecraft.block.BlockAir;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockDarkAir extends BlockAir
{
    public BlockDarkAir(String name)
    {
        this.setResistance(100000.0F);
        this.setHardness(0.0F);
        this.setBlockName(name);
    }

    @Override
    public boolean canReplace(World world, int x, int y, int z, int side, ItemStack stack)
    {
        return true;
    }

    @Override
    public boolean canPlaceBlockAt(World world, int var2, int var3, int var4)
    {
        return true;
    }

    @Override
    public int getRenderBlockPass()
    {
        return 1;
    }

    @Override
    public int getMobilityFlag()
    {
        return 1;
    }

    @Override
    public Item getItemDropped(int state, Random rand, int fortune)
    {
        return null;
    }

    @Override
    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int facing)
    {
        return false;
    }

    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z)
    {
        return 5 - world.getBlockMetadata(x, y, z);
    }
}