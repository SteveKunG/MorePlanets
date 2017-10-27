package stevekung.mods.moreplanets.util.blocks;

import java.util.Random;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockIceMP extends BlockBreakableMP
{
    public BlockIceMP()
    {
        super(Material.ICE);
        this.setDefaultSlipperiness(0.98F);
        this.setHardness(0.5F);
        this.setResistance(0.1F);
        this.setLightOpacity(3);
        this.setTickRandomly(true);
        this.setSoundType(SoundType.GLASS);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    public int quantityDropped(Random rand)
    {
        return 0;
    }

    @Override
    public EnumPushReaction getMobilityFlag(IBlockState state)
    {
        return EnumPushReaction.NORMAL;
    }

    @Override
    protected boolean isTranslucent()
    {
        return true;
    }

    @Override
    protected boolean renderSideWithState()
    {
        return false;
    }
}