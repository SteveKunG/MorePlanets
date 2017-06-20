package stevekung.mods.moreplanets.init;

import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.util.helper.CommonRegisterHelper;

public class MPLootTables
{
    public static ResourceLocation CHEESE_COW = MPLootTables.register("entities/cheese_cow");

    private static ResourceLocation register(String name)
    {
        return CommonRegisterHelper.registerLootTable(new ResourceLocation("moreplanets:" + name));
    }
}