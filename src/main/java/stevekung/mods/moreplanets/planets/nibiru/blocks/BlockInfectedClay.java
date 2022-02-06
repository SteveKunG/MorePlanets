package stevekung.mods.moreplanets.planets.nibiru.blocks;

import java.util.Random;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import stevekung.mods.moreplanets.init.MPItems;
import stevekung.mods.moreplanets.utils.blocks.BlockBaseMP;

public class BlockInfectedClay extends BlockBaseMP
{
    public BlockInfectedClay(String name)
    {
        super(Material.CLAY);
        this.setTranslationKey(name);
        this.setHardness(0.6F);
        this.setSoundType(SoundType.GROUND);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return MPItems.INFECTED_CLAY_BALL;
    }

    @Override
    public int quantityDropped(Random rand)
    {
        return 4;
    }
}