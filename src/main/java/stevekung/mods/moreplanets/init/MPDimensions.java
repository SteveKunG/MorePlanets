package stevekung.mods.moreplanets.init;

import micdoodle8.mods.galacticraft.core.util.WorldUtil;
import net.minecraft.world.DimensionType;
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;

public class MPDimensions
{
    public static DimensionType SPACE_NETHER;
    public static DimensionType DIONA;
    public static DimensionType CHALOS;
    public static DimensionType NIBIRU;

    public static void init()
    {
        MPDimensions.SPACE_NETHER = WorldUtil.getDimensionTypeById(ConfigManagerMP.moreplanets_dimension.idDimensionSpaceNether);
        MPDimensions.DIONA = WorldUtil.getDimensionTypeById(ConfigManagerMP.moreplanets_dimension.idDimensionDiona);
        MPDimensions.CHALOS = WorldUtil.getDimensionTypeById(ConfigManagerMP.moreplanets_dimension.idDimensionChalos);
        MPDimensions.NIBIRU = WorldUtil.getDimensionTypeById(ConfigManagerMP.moreplanets_dimension.idDimensionNibiru);
    }
}