package stevekung.mods.moreplanets.module.planets.nibiru.tileentity;

import java.util.List;

import micdoodle8.mods.galacticraft.core.blocks.BlockMulti.EnumBlockMultiType;
import micdoodle8.mods.galacticraft.core.tile.IMultiBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.blocks.BlockDummy;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.tileentity.TileEntityDummy;

public class TileEntityNuclearWasteTank extends TileEntityDummy implements IMultiBlock
{
    public int renderTicks;

    @Override
    public void update()
    {
        super.update();
        this.renderTicks++;
    }

    @Override
    public boolean onActivated(EntityPlayer player)
    {
        return false;
    }

    @Override
    public void onCreate(World world, BlockPos pos)
    {
        this.mainBlockPosition = pos;
    }

    @Override
    public void onDestroy(TileEntity tile)
    {
        BlockPos thisBlock = this.getPos();

        if (this.world.isRemote && this.world.rand.nextDouble() < 0.1D)
        {
            FMLClientHandler.instance().getClient().effectRenderer.addBlockDestroyEffects(thisBlock.up(), MPBlocks.DUMMY_BLOCK.getDefaultState().withProperty(BlockDummy.VARIANT, BlockDummy.BlockType.NUCLEAR_WASTE_TANK_MIDDLE));
            FMLClientHandler.instance().getClient().effectRenderer.addBlockDestroyEffects(thisBlock.up(2), MPBlocks.DUMMY_BLOCK.getDefaultState().withProperty(BlockDummy.VARIANT, BlockDummy.BlockType.NUCLEAR_WASTE_TANK_TOP));
        }
        this.world.destroyBlock(this.getPos(), true);
        this.world.destroyBlock(thisBlock.up(), false);
        this.world.destroyBlock(thisBlock.up(2), false);
    }

    @Override
    public void getPositions(BlockPos placedPosition, List<BlockPos> positions) {}

    @Override
    public boolean canRenderBreaking()
    {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox()
    {
        return new AxisAlignedBB(this.pos, this.pos.add(1, 3, 1));
    }

    @Override
    public EnumBlockMultiType getMultiType()
    {
        return null;
    }
}