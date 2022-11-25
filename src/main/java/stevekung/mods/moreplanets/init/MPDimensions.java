package stevekung.mods.moreplanets.init;

import micdoodle8.mods.galacticraft.core.util.WorldUtil;
import net.minecraft.world.DimensionType;
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;

public class MPDimensions
{
    public static DimensionType DIONA;
    public static DimensionType CHALOS;
    public static DimensionType NIBIRU;
    public static DimensionType FRONOS;
    public static DimensionType KOENTUS;

    public static void init()
    {
        MPDimensions.DIONA = WorldUtil.getDimensionTypeById(ConfigManagerMP.moreplanets_dimension.idDimensionDiona);
        MPDimensions.CHALOS = WorldUtil.getDimensionTypeById(ConfigManagerMP.moreplanets_dimension.idDimensionChalos);
        MPDimensions.NIBIRU = WorldUtil.getDimensionTypeById(ConfigManagerMP.moreplanets_dimension.idDimensionNibiru);
        MPDimensions.FRONOS = WorldUtil.getDimensionTypeById(ConfigManagerMP.moreplanets_dimension.idDimensionFronos);
        MPDimensions.KOENTUS = WorldUtil.getDimensionTypeById(ConfigManagerMP.moreplanets_dimension.idDimensionKoentus);
    }
}