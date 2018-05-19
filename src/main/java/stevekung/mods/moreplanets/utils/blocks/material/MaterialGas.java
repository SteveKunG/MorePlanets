package stevekung.mods.moreplanets.utils.blocks.material;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.MaterialLiquid;

public class MaterialGas extends MaterialLiquid
{
    public MaterialGas()
    {
        super(MapColor.AIR);
    }

    @Override
    public boolean blocksLight()
    {
        return false;
    }
}