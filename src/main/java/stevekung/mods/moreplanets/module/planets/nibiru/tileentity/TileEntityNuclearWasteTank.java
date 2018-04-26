package stevekung.mods.moreplanets.module.planets.nibiru.tileentity;

import java.util.List;

import micdoodle8.mods.galacticraft.core.blocks.BlockMulti.EnumBlockMultiType;
import micdoodle8.mods.galacticraft.core.tile.IMultiBlock;
import micdoodle8.mods.miccore.Annotations.NetworkedField;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.BlockNuclearWasteTank;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.BlockNuclearWasteTank.BlockType;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.tileentity.TileEntityDummy;

public class TileEntityNuclearWasteTank extends TileEntityDummy implements IMultiBlock
{
    public int renderTicks;
    @NetworkedField(targetSide = Side.CLIENT)
    public int rodCreateTime;

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        this.rodCreateTime = nbt.getInteger("RodCreateTime");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        nbt.setInteger("RodCreateTime", this.rodCreateTime);
        return nbt;
    }

    @Override
    public void update()
    {
        super.update();
        this.renderTicks++;

        if (!this.world.isRemote)
        {
            int count = this.world.getBlockState(this.pos).getValue(BlockNuclearWasteTank.FLUID_COUNT);

            if (count == 3 && this.rodCreateTime < 100)
            {
                this.rodCreateTime++;

                if (this.rodCreateTime == 100)
                {
                    this.world.setBlockState(this.pos, NibiruBlocks.NUCLEAR_WASTE_TANK.getDefaultState().withProperty(BlockNuclearWasteTank.STATE, BlockType.NONE));
                    this.rodCreateTime = 0;
                }
                System.out.println(this.rodCreateTime);
            }
        }
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
            FMLClientHandler.instance().getClient().effectRenderer.addBlockDestroyEffects(thisBlock.up(), MPBlocks.NWT_MIDDLE_DUMMY.getDefaultState());
            FMLClientHandler.instance().getClient().effectRenderer.addBlockDestroyEffects(thisBlock.up(2), MPBlocks.NWT_TOP_DUMMY.getDefaultState());
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