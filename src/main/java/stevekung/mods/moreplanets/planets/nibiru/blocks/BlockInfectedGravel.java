package stevekung.mods.moreplanets.planets.nibiru.blocks;

import java.util.Random;

import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.utils.blocks.BlockFallingMP;
import stevekung.mods.stevekunglib.utils.ColorUtils;

public class BlockInfectedGravel extends BlockFallingMP
{
    public BlockInfectedGravel(String name)
    {
        super();
        this.setUnlocalizedName(name);
        this.setHardness(0.6F);
        this.setSoundType(SoundType.GROUND);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        if (fortune > 3)
        {
            fortune = 3;
        }
        return rand.nextInt(10 - fortune * 3) == 0 ? Items.FLINT : Item.getItemFromBlock(this);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getDustColor(IBlockState state)
    {
        return ColorUtils.rgbToDecimal(131, 78, 65);
    }
}