/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.asteroids.darkasteroids.items;

import net.minecraft.item.Item;
import stevekung.mods.moreplanets.core.util.RegisterHelper;

public class DarkAsteroidsItems
{
    public static Item alphere;

    public static void init()
    {
        // Init
        DarkAsteroidsItems.alphere = new ItemAlphere("alphere");

        // Register
        RegisterHelper.registerItem(DarkAsteroidsItems.alphere);
    }
}