package stevekung.mods.moreplanets.module.planets.diona.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.module.planets.diona.entity.EntityZeliusCreeper;
import stevekung.mods.moreplanets.module.planets.diona.entity.EntityZeliusZombie;
import stevekung.mods.moreplanets.module.planets.diona.tileentity.TileEntityZeliusEgg;
import stevekung.mods.moreplanets.util.blocks.BlockBaseMP;
import stevekung.mods.moreplanets.util.blocks.EnumSortCategoryBlock;
import stevekung.mods.moreplanets.util.helper.BlockSoundHelper;

public class BlockZeliusEgg extends BlockBaseMP implements ITileEntityProvider
{
    public BlockZeliusEgg(String name)
    {
        super(Material.ground);
        this.setResistance(0.0F);
        this.setHardness(0.5F);
        this.setUnlocalizedName(name);
        this.setStepSound(BlockSoundHelper.ALIEN_EGG);
        this.slipperiness = 0.8F;
    }

    @Override
    public int getRenderType()
    {
        return 3;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public boolean isFullCube()
    {
        return false;
    }

    @Override
    public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player)
    {
        return true;
    }

    @Override
    public void onFallenUpon(World world, BlockPos pos, Entity entity, float fallDistance)
    {
        if (entity.isSneaking())
        {
            super.onFallenUpon(world, pos, entity, fallDistance);
        }
        else
        {
            entity.fall(fallDistance, 0.0F);
        }
    }

    @Override
    public void onLanded(World world, Entity entity)
    {
        if (entity.isSneaking())
        {
            super.onLanded(world, entity);
        }
        else if (entity.motionY < 0.0D)
        {
            entity.motionY = -entity.motionY;
        }
    }

    @Override
    public void onEntityCollidedWithBlock(World world, BlockPos pos, Entity entity)
    {
        if (Math.abs(entity.motionY) < 0.1D && !entity.isSneaking())
        {
            double d0 = 0.4D + Math.abs(entity.motionY) * 0.2D;
            entity.motionX *= d0;
            entity.motionZ *= d0;
        }
    }

    @Override
    public void onBlockExploded(World world, BlockPos pos, Explosion explosion)
    {
        if (!world.isRemote)
        {
            if (world.rand.nextInt(5) == 0)
            {
                EntityZeliusCreeper creeper = new EntityZeliusCreeper(world);
                creeper.setPosition(pos.getX() + 0.5D, pos.getY() + 1, pos.getZ() + 0.5D);
                creeper.getDataWatcher().updateObject(17, Byte.valueOf((byte)1));
                world.spawnEntityInWorld(creeper);
            }
            else
            {
                EntityZeliusZombie zombie = new EntityZeliusZombie(world);
                zombie.setPosition(pos.getX() + 0.5D, pos.getY() + 1, pos.getZ() + 0.5D);
                world.spawnEntityInWorld(zombie);
            }
            world.setBlockToAir(pos);
        }
        world.playSoundEffect(pos.getX(), pos.getY(), pos.getZ(), "moreplanets:block.egg.destroy", 1.0F, 1.0F);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new TileEntityZeliusEgg();
    }

    @Override
    public EnumSortCategoryBlock getBlockCategory(int meta)
    {
        return EnumSortCategoryBlock.DECORATION_NON_BLOCK;
    }

    @Override
    public String getName()
    {
        return "zelius_egg";
    }

    @Override
    public Block getBlock()
    {
        return this;
    }
}