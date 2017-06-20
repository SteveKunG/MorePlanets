package stevekung.mods.moreplanets.util.blocks;

import micdoodle8.mods.galacticraft.api.block.ITerraformableBlock;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class BlockGrassMP extends BlockBaseMP implements ITerraformableBlock
{
    public BlockGrassMP()
    {
        super(Material.GRASS);
        this.setTickRandomly(true);
        this.setStepSound(soundTypeGrass);
        this.setHardness(0.6F);
    }

    @Override
    public boolean isTerraformable(World world, BlockPos pos)
    {
        return true && !world.getBlockState(pos.up()).isOpaqueCube();
    }
}