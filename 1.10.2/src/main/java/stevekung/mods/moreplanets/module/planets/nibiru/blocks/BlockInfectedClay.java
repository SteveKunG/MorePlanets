package stevekung.mods.moreplanets.module.planets.nibiru.blocks;

import java.util.Random;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import stevekung.mods.moreplanets.module.planets.nibiru.items.NibiruItems;
import stevekung.mods.moreplanets.util.blocks.BlockBaseMP;

public class BlockInfectedClay extends BlockBaseMP
{
    public BlockInfectedClay(String name)
    {
        super(Material.CLAY);
        this.setUnlocalizedName(name);
        this.setHardness(0.6F);
        this.setSoundType(SoundType.GROUND);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return NibiruItems.INFECTED_CLAY_BALL;
    }

    @Override
    public int quantityDropped(Random rand)
    {
        return 4;
    }

    @Override
    public String getName()
    {
        return "infected_clay";
    }
}