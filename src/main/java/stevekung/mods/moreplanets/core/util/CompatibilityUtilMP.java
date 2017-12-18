/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.util;

import java.lang.reflect.Method;

import cpw.mods.fml.common.Loader;
import net.minecraft.block.Block;

public class CompatibilityUtilMP
{
    @Deprecated //TODO: Removed in 1.8
    public static boolean is4SpaceVenusLoaded()
    {
        return Loader.isModLoaded("SpaceCore") && Loader.isModLoaded("SpaceVenus");
    }

    public static void registerMicroBlocks(Block block, int meta, String unlocalized)
    {
        try
        {
            Class clazz = Class.forName("codechicken.microblock.MicroMaterialRegistry");

            if (clazz != null)
            {
                Method registerMethod = null;
                Method[] method = clazz.getMethods();

                for (Method m : method)
                {
                    if (m.getName().equals("registerMaterial"))
                    {
                        registerMethod = m;
                        break;
                    }
                }
                Class clazzbm = Class.forName("codechicken.microblock.BlockMicroMaterial");
                registerMethod.invoke(null, clazzbm.getConstructor(Block.class, int.class).newInstance(block, meta), unlocalized);
            }
        }
        catch (Exception e) {}
    }
}