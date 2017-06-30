package stevekung.mods.moreplanets.util;

import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockFire;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.properties.IProperty;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.util.helper.BlockStateHelper;

@SideOnly(Side.CLIENT)
public enum EnumStateMapper
{
    FENCE_GATE(BlockFenceGate.POWERED),
    DOOR(BlockDoor.POWERED),
    FORGE_LEVEL(BlockFluidBase.LEVEL),
    VANILLA_LEVEL(BlockLiquid.LEVEL),
    FIRE(BlockFire.AGE),
    TNT(BlockStateHelper.EXPLODE),
    LEAVES(BlockStateHelper.CHECK_DECAY, BlockStateHelper.DECAYABLE),
    PLANT_AGE(BlockStateHelper.AGE);

    private IProperty[] property;

    private EnumStateMapper(IProperty... property)
    {
        this.property = property;
    }

    public IProperty[] getProperty()
    {
        return this.property;
    }
}