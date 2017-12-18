/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.koentus.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import stevekung.mods.moreplanets.core.blocks.BlockDoorMP.DoorType;
import stevekung.mods.moreplanets.core.items.ItemDoorMP;
import stevekung.mods.moreplanets.core.util.RegisterHelper;

public class KoentusItems
{
    public static Item koentus_item;
    public static Item koentus_meteor_chunk;
    public static Item crystal_door;

    public static void init()
    {
        KoentusItems.initItems();
        KoentusItems.registerItems();
    }

    private static void initItems()
    {
        KoentusItems.koentus_item = new ItemBasicKoentus("koentus_item");
        KoentusItems.koentus_meteor_chunk = new ItemKoentusMeteorChunk("koentus_meteor_chunk");
        KoentusItems.crystal_door = new ItemDoorMP("crystal_door", DoorType.CRYSTAL);
    }

    private static void registerItems()
    {
        RegisterHelper.registerItem(KoentusItems.koentus_item);
        RegisterHelper.registerItem(KoentusItems.koentus_meteor_chunk);
        RegisterHelper.registerItem(KoentusItems.crystal_door);

        OreDictionary.registerOre("ingotKoentusMeteoricIron", new ItemStack(KoentusItems.koentus_item, 1, 4));
        OreDictionary.registerOre("compressedKoentusMeteoricIron", new ItemStack(KoentusItems.koentus_item, 1, 6));
        OreDictionary.registerOre("compressedWhiteCrystal", new ItemStack(KoentusItems.koentus_item, 1, 5));
    }
}