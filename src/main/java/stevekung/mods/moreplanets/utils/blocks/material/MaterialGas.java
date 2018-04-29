package stevekung.mods.moreplanets.utils.blocks.material;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class MaterialGas extends Material
{
    public MaterialGas()
    {
        super(MapColor.AIR);
        this.setReplaceable();
        this.setNoPushMobility();
    }

    @Override
    public boolean isSolid()
    {
        return false;
    }

    @Override
    public boolean blocksLight()
    {
        return false;
    }

    @Override
    public boolean blocksMovement()
    {
        return false;
    }
}