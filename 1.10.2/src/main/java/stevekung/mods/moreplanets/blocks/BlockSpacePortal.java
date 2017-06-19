package stevekung.mods.moreplanets.blocks;

import java.util.Random;

import micdoodle8.mods.galacticraft.core.util.WorldUtil;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
import stevekung.mods.moreplanets.core.event.ClientEventHandler;
import stevekung.mods.moreplanets.core.event.WorldTickEventHandler;
import stevekung.mods.moreplanets.util.TeleporterMP;
import stevekung.mods.moreplanets.util.blocks.BlockBreakableMP;
import stevekung.mods.moreplanets.util.blocks.EnumSortCategoryBlock;
import stevekung.mods.moreplanets.util.blocks.ISingleBlockRender;

public class BlockSpacePortal extends BlockBreakableMP implements ISingleBlockRender
{
    public BlockSpacePortal(String name)
    {
        super(Material.ROCK);
        this.setResistance(2000.0F);
        this.setHardness(35.0F);
        this.setUnlocalizedName(name);
        this.setLightLevel(0.75F);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, ItemStack heldItem, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (ConfigManagerMP.enableStartedPlanet && !(ConfigManagerMP.startedPlanet.equals("planet.") || ConfigManagerMP.startedPlanet.equals("moon.") || ConfigManagerMP.startedPlanet.equals("satellite.")))
        {
            if (!player.isRiding() && !player.isBeingRidden() && player.isNonBoss() && player instanceof EntityPlayerMP)
            {
                EntityPlayerMP playerMP = (EntityPlayerMP)player;

                if (playerMP.dimension != -1)
                {
                    playerMP.mcServer.getConfigurationManager().transferPlayerToDimension(playerMP, -1, new TeleporterMP(playerMP.mcServer.worldServerForDimension(-1)));
                    ClientEventHandler.loadRenderers = true;
                }
                else
                {
                    int dimID = WorldUtil.getProviderForNameServer(WorldTickEventHandler.startedDimensionData.planetToBack).getDimension();
                    playerMP.mcServer.getConfigurationManager().transferPlayerToDimension(playerMP, dimID, new TeleporterMP(playerMP.mcServer.worldServerForDimension(dimID)));
                    ClientEventHandler.loadRenderers = true;
                }
            }
        }
        return true;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, World world, BlockPos pos)
    {
        return new AxisAlignedBB(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1.0F, pos.getY() + 0.75F, pos.getZ() + 1.0F);
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand)
    {
        if (rand.nextInt(20) == 0)
        {
            world.playSound(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, SoundEvents.BLOCK_PORTAL_AMBIENT, SoundCategory.BLOCKS, 0.5F, rand.nextFloat() * 0.4F + 0.8F, false);
        }

        double d0 = pos.getX() + rand.nextFloat();
        double d1 = pos.getY() + rand.nextFloat();
        double d2 = pos.getZ() + rand.nextFloat();
        double d3 = (rand.nextFloat() - 0.5D) * 0.5D;
        double d4 = (rand.nextFloat() - 0.5D) * 0.5D;
        double d5 = (rand.nextFloat() - 0.5D) * 0.5D;
        int j = rand.nextInt(2) * 2 - 1;

        for (int k = 0; k < 16; k++)
        {
            d2 = pos.getZ() + 0.5D + 0.25D * j;
            d5 = rand.nextFloat() * 2.0F * j;
            world.spawnParticle(EnumParticleTypes.PORTAL, d0, d1, d2, d3, d4, d5);
        }
        for (int k = 0; k < 16; k++)
        {
            d2 = pos.getZ() + rand.nextFloat();
            d5 = (rand.nextFloat() - 0.5D) * 0.5D;
            d0 = pos.getX() + 0.5D + 0.25D * j;
            d3 = rand.nextFloat() * 2.0F * j;
            world.spawnParticle(EnumParticleTypes.PORTAL, d0, d1, d2, d3, d4, d5);
        }
    }

    @Override
    public EnumSortCategoryBlock getBlockCategory(int meta)
    {
        return EnumSortCategoryBlock.MACHINE_BLOCK;
    }

    @Override
    protected boolean isTranslucentBlock()
    {
        return false;
    }

    @Override
    protected boolean renderSideOnOtherState()
    {
        return false;
    }

    @Override
    public String getName()
    {
        return "space_portal";
    }

    @Override
    public Block getBlock()
    {
        return this;
    }
}