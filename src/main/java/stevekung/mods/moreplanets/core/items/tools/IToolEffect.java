package stevekung.mods.moreplanets.core.items.tools;

import net.minecraft.entity.EntityLivingBase;

public interface IToolEffect
{
    void addEffect(EntityLivingBase living);
}