package stevekung.mods.moreplanets.utils.blocks;

import java.util.Random;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockBookshelfMP extends BlockBaseMP
{
    public BlockBookshelfMP(String name)
    {
        super(Material.WOOD);
        this.setHardness(1.5F);
        this.setSoundType(SoundType.WOOD);
        this.setTranslationKey(name);
    }

    @Override
    public int quantityDropped(Random rand)
    {
        return 3;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Items.BOOK;
    }

    @Override
    public float getEnchantPowerBonus(World world, BlockPos pos)
    {
        return 1;
    }
}