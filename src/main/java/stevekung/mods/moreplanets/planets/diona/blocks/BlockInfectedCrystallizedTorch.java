package stevekung.mods.moreplanets.planets.diona.blocks;

import java.util.Random;

import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
import stevekung.mods.moreplanets.utils.EnumParticleTypesMP;
import stevekung.mods.moreplanets.utils.blocks.BlockTorchMP;

public class BlockInfectedCrystallizedTorch extends BlockTorchMP
{
    public BlockInfectedCrystallizedTorch(String name)
    {
        this.setLightLevel(0.9375F);
        this.setSoundType(SoundType.WOOD);
        this.setUnlocalizedName(name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand)
    {
        EnumFacing facing = state.getValue(FACING);
        double d0 = pos.getX() + 0.5D;
        double d1 = pos.getY() + 0.7D;
        double d2 = pos.getZ() + 0.5D;

        if (facing.getAxis().isHorizontal())
        {
            EnumFacing facing1 = facing.getOpposite();
            world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + 0.27D * facing1.getFrontOffsetX(), d1 + 0.22D, d2 + 0.27D * facing1.getFrontOffsetZ(), 0.0D, 0.0D, 0.0D);
            MorePlanetsMod.PROXY.spawnParticle(EnumParticleTypesMP.CRYSTALLIZED_FLAME, d0 + 0.27D * facing1.getFrontOffsetX(), d1 + 0.22D, d2 + 0.27D * facing1.getFrontOffsetZ(), 0.0D, 0.0D, 0.0D);
        }
        else
        {
            world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1, d2, 0.0D, 0.0D, 0.0D);
            MorePlanetsMod.PROXY.spawnParticle(EnumParticleTypesMP.CRYSTALLIZED_FLAME, d0, d1, d2);
        }
    }

    @Override
    public String getName()
    {
        return ConfigManagerMP.moreplanets_general.use3DTorchItemModel ? "infected_crystallized_torch" : "infected_crystallized_torch_vanilla";
    }
}