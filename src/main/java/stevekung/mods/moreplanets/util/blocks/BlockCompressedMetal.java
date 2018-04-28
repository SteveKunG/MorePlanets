package stevekung.mods.moreplanets.util.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockCompressedMetal extends BlockBaseMP
{
    public BlockCompressedMetal(String name)
    {
        super(Material.IRON);
        this.setUnlocalizedName(name);
    }

    @Override
    public EnumSortCategoryBlock getBlockCategory()
    {
        return EnumSortCategoryBlock.INGOT_BLOCK;
    }

    @Override
    public boolean isBeaconBase(IBlockAccess world, BlockPos pos, BlockPos beacon)
    {
        return true;
    }

    @Override
    public BlockCompressedMetal setSoundType(SoundType sound)
    {
        this.blockSoundType = sound;
        return this;
    }
}