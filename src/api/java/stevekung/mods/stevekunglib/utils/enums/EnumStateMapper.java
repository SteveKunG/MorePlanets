package stevekung.mods.stevekunglib.utils.enums;

import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockFire;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.properties.IProperty;
import net.minecraftforge.fluids.BlockFluidBase;
import stevekung.mods.stevekunglib.utils.BlockStateProperty;

public enum EnumStateMapper
{
    FENCE_GATE(BlockFenceGate.POWERED),
    DOOR(BlockDoor.POWERED),
    FORGE_LEVEL(BlockFluidBase.LEVEL),
    VANILLA_LEVEL(BlockLiquid.LEVEL),
    FIRE(BlockFire.AGE),
    TNT(BlockStateProperty.EXPLODE),
    LEAVES(BlockStateProperty.CHECK_DECAY, BlockStateProperty.DECAYABLE),
    PLANT_AGE_15(BlockStateProperty.AGE_15);

    private final IProperty[] property;

    private EnumStateMapper(IProperty... property)
    {
        this.property = property;
    }

    public IProperty[] getProperty()
    {
        return this.property;
    }
}