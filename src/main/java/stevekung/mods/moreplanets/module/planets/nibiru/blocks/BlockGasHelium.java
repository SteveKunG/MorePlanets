package stevekung.mods.moreplanets.module.planets.nibiru.blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidBase;
import stevekung.mods.moreplanets.util.blocks.BlockFluidFiniteMP;
import stevekung.mods.moreplanets.util.blocks.material.MaterialsBase;

public class BlockGasHelium extends BlockFluidFiniteMP
{
    public BlockGasHelium(String name)
    {
        super(NibiruBlocks.HELIUM_GAS, MaterialsBase.GAS);
        this.setRenderLayer(EnumWorldBlockLayer.TRANSLUCENT);
        this.setLightOpacity(3);
        this.setUnlocalizedName(name);
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack itemStack)
    {
        world.setBlockState(pos, this.getDefaultState().withProperty(BlockFluidBase.LEVEL, 7), 3);
    }

    @Override
    public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity)
    {
        if (entity instanceof EntityLivingBase)
        {
            EntityLivingBase living = (EntityLivingBase) entity;

            if (living instanceof EntityPlayer)
            {
                if (((EntityPlayer)living).capabilities.isFlying)
                {
                    return;
                }
            }
            entity.motionY = 0.28F;
            entity.fallDistance = 0.0F;
        }
        else if (entity instanceof EntityItem)
        {
            entity.motionY = 0.32F;
            entity.fallDistance = 0.0F;
        }
        else
        {
            entity.motionY = 0.28F;
            entity.fallDistance = 0.0F;
        }
    }

    @Override
    public String getName()
    {
        return "helium_gas";
    }
}