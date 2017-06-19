package stevekung.mods.moreplanets.util;

import net.minecraft.util.text.ITextComponent;

public interface IMorePlanetsBossDisplayData
{
    float getBossMaxHealth();
    float getBossHealth();
    ITextComponent getBossDisplayName();
}