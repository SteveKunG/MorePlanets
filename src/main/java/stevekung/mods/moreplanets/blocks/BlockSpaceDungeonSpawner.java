package stevekung.mods.moreplanets.blocks;

import java.util.Locale;
import java.util.Random;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.planets.chalos.tileentity.TileEntityChalosDungeonSpawner;
import stevekung.mods.moreplanets.planets.diona.tileentity.TileEntityDionaDungeonSpawner;
import stevekung.mods.moreplanets.planets.nibiru.tileentity.TileEntityNibiruDungeonSpawner;
import stevekung.mods.moreplanets.utils.blocks.BlockBaseMP;

public class BlockSpaceDungeonSpawner extends BlockBaseMP implements ITileEntityProvider
{
    private final DungeonType type;

    public BlockSpaceDungeonSpawner(String name, DungeonType type)
    {
        super(Material.AIR);
        this.setBlockUnbreakable();
        this.setResistance(6000001.0F);
        this.translucent = true;
        this.setUnlocalizedName(name);
        this.type = type;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        return null;
    }

    @Override
    public RayTraceResult collisionRayTrace(IBlockState state, World world, BlockPos pos, Vec3d start, Vec3d end)
    {
        return null;
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.INVISIBLE;
    }

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
    @SideOnly(Side.CLIENT)
    public float getAmbientOcclusionLightValue(IBlockState state)
    {
        return 1.0F;
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
    {
        return ItemStack.EMPTY;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        switch (this.type)
        {
        case DIONA:
            return new TileEntityDionaDungeonSpawner();
        case CHALOS:
            return new TileEntityChalosDungeonSpawner();
        case NIBIRU:
            return new TileEntityNibiruDungeonSpawner();
        default:
            return null;
        }
    }

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess world, IBlockState state, BlockPos pos, EnumFacing facing)
    {
        return BlockFaceShape.UNDEFINED;
    }

    @Override
    public boolean canHarvestBlock(IBlockAccess world, BlockPos pos, EntityPlayer player)
    {
        return false;
    }

    @Override
    public int quantityDropped(IBlockState state, int fortune, Random rand)
    {
        return 0;
    }

    public static enum DungeonType
    {
        DIONA,
        CHALOS,
        NIBIRU;

        @Override
        public String toString()
        {
            return this.name().toLowerCase(Locale.ROOT);
        }
    }
}