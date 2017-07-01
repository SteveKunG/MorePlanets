package stevekung.mods.moreplanets.module.planets.nibiru.blocks;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.module.planets.nibiru.tileentity.TileEntityVeinPortal;
import stevekung.mods.moreplanets.util.blocks.BlockContainerMP;

public class BlockVeinPortal extends BlockContainerMP
{
    public BlockVeinPortal(String name)
    {
        super(Material.BARRIER);
        this.setBlockUnbreakable();
        this.setLightLevel(1.0F);
        this.setResistance(6000000.0F);
        this.setUnlocalizedName(name);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new TileEntityVeinPortal();
    }

    @Override
    public void addCollisionBoxToList(IBlockState state, World world, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entity, boolean pull) {}

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Override
    public int quantityDropped(Random rand)
    {
        return 0;
    }

    @Override
    public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity)
    {
        if (!world.isRemote)
        {
            if (entity instanceof EntityLivingBase)
            {
                ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 120, 0, false, false));
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand)
    {
        double d0 = pos.getX() + rand.nextFloat();
        double d1 = pos.getY() + 0.8F;
        double d2 = pos.getZ() + rand.nextFloat();
        double d3 = 0.0D;
        double d4 = 0.0D;
        double d5 = 0.0D;
        world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1, d2, d3, d4, d5);
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
    {
        return null;
    }

    @Override
    public MapColor getMapColor(IBlockState state)
    {
        return MapColor.BLACK;
    }
}