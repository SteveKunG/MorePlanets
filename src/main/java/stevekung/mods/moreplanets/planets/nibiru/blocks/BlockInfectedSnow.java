package stevekung.mods.moreplanets.planets.nibiru.blocks;

import java.util.Random;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.planets.nibiru.items.NibiruItems;
import stevekung.mods.moreplanets.utils.blocks.BlockBaseMP;

public class BlockInfectedSnow extends BlockBaseMP
{
    protected BlockInfectedSnow(String name)
    {
        super(Material.CRAFTED_SNOW);
        this.setTickRandomly(true);
        this.setUnlocalizedName(name);
        this.setHardness(0.2F);
        this.setSoundType(SoundType.SNOW);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return NibiruItems.INFECTED_SNOWBALL;
    }

    @Override
    public int quantityDropped(Random rand)
    {
        return 4;
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
    {
        if (world.getLightFor(EnumSkyBlock.BLOCK, pos) > 11)
        {
            this.dropBlockAsItem(world, pos, world.getBlockState(pos), 0);
            world.setBlockToAir(pos);
        }
    }
}