package stevekung.mods.moreplanets.util.world.gen.structure;

import net.minecraft.world.gen.structure.StructureComponent;

public abstract class StructureComponentMP extends StructureComponent
{
    protected StructureComponentMP() {}

    protected StructureComponentMP(int type)
    {
        super(type);
    }
}