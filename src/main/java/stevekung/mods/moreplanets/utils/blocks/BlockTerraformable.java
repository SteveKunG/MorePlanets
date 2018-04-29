package stevekung.mods.moreplanets.utils.blocks;

import micdoodle8.mods.galacticraft.api.block.ITerraformableBlock;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockTerraformable extends BlockBaseMP implements ITerraformableBlock
{
    public BlockTerraformable(String name)
    {
        super(Material.ROCK);
        this.setUnlocalizedName(name);
    }

    public BlockTerraformable(String name, Material material)
    {
        super(material);
        this.setUnlocalizedName(name);
    }

    @Override
    public boolean isTerraformable(World world, BlockPos pos)
    {
        return !world.getBlockState(pos.up()).isOpaqueCube();
    }
}