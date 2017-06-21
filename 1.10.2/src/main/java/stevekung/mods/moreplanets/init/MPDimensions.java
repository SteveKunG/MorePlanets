package stevekung.mods.moreplanets.init;

import micdoodle8.mods.galacticraft.core.util.WorldUtil;
import net.minecraft.world.DimensionType;
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;

public class MPDimensions
{
    public static DimensionType DIONA;
    public static DimensionType CHALOS;
    public static DimensionType NIBIRU;

    public static void init()
    {
        MPDimensions.DIONA = WorldUtil.getDimensionTypeById(ConfigManagerMP.idDimensionDiona);
        MPDimensions.CHALOS = WorldUtil.getDimensionTypeById(ConfigManagerMP.idDimensionChalos);
        MPDimensions.NIBIRU = WorldUtil.getDimensionTypeById(ConfigManagerMP.idDimensionNibiru);
    }
}