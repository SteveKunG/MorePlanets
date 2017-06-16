package stevekung.mods.moreplanets.util;

import net.minecraft.util.IChatComponent;

public interface IMorePlanetsBossDisplayData
{
    float getBossMaxHealth();
    float getBossHealth();
    IChatComponent getBossDisplayName();
}