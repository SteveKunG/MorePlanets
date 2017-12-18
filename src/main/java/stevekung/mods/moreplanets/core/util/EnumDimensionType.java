/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.util;

import stevekung.mods.moreplanets.core.config.ConfigManagerMP;

public enum EnumDimensionType
{
    @Deprecated // Using for template only
    NULL(0),
    NIBIRU(ConfigManagerMP.idDimensionNibiru),
    SIRIUS_B(ConfigManagerMP.idDimensionSiriusB),

    ;public int dimID;

    private EnumDimensionType(int dimID)
    {
        this.dimID = dimID;
    }
}