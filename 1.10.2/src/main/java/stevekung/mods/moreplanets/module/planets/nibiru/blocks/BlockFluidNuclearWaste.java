package stevekung.mods.moreplanets.module.planets.nibiru.blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.util.EnumParticleTypesMP;
import stevekung.mods.moreplanets.util.blocks.BlockFluidLavaBaseMP;

public class BlockFluidNuclearWaste extends BlockFluidLavaBaseMP
{
    public BlockFluidNuclearWaste(String name)
    {
        super(NibiruBlocks.NUCLEAR_WASTE_FLUID);
        this.setUnlocalizedName(name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, BlockPos pos, IBlockState state, Random rand)
    {
        if (this.blockMaterial == Material.lava && world.getBlockState(pos.up()).getBlock().getMaterial() == Material.air && !world.getBlockState(pos.up()).getBlock().isOpaqueCube())
        {
            if (rand.nextInt(50) == 0)
            {
                double d5 = pos.getX() + rand.nextFloat();
                double d6 = pos.getY() + this.maxY;
                double d7 = pos.getZ() + rand.nextFloat();
                world.playSound(d5, d6, d7, "liquid.lavapop", 0.2F + rand.nextFloat() * 0.2F, 0.9F + rand.nextFloat() * 0.15F, false);
            }
            if (rand.nextInt(200) == 0)
            {
                world.playSound(pos.getX(), pos.getY(), pos.getZ(), "liquid.lava", 0.2F + rand.nextFloat() * 0.2F, 0.00001F + rand.nextFloat() * 0.5F, false);
            }
        }
        if (rand.nextInt(10) == 0 && World.doesBlockHaveSolidTopSurface(world, pos.down()) && !world.getBlockState(pos.down(2)).getBlock().getMaterial().blocksMovement())
        {
            double d5 = pos.getX() + rand.nextFloat();
            double d6 = pos.getY() - 1.05D;
            double d7 = pos.getZ() + rand.nextFloat();
            MorePlanetsCore.PROXY.spawnParticle(EnumParticleTypesMP.NUCLEAR_WASTE_DRIP, d5, d6, d7);
        }
    }

    @Override
    protected IBlockState getBlockFromWaterTo()
    {
        return Blocks.obsidian.getDefaultState();
    }

    @Override
    protected IBlockState getObsidianBlock()
    {
        return Blocks.obsidian.getDefaultState();
    }

    @Override
    protected IBlockState getCobblestoneBlock()
    {
        return Blocks.obsidian.getDefaultState();
    }

    @Override
    protected IBlockState getFireBlock()
    {
        return Blocks.fire.getDefaultState();
    }

    @Override
    protected boolean enableFire()
    {
        return false;
    }

    @Override
    public String getName()
    {
        return "nuclear_waste_fluid";
    }
}