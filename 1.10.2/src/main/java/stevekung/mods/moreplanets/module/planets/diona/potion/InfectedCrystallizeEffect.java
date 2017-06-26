package stevekung.mods.moreplanets.module.planets.diona.potion;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import stevekung.mods.moreplanets.init.MPPotions;
import stevekung.mods.moreplanets.util.DamageSourceMP;
import stevekung.mods.moreplanets.util.PotionMP;
import stevekung.mods.moreplanets.util.helper.ColorHelper;

public class InfectedCrystallizeEffect extends PotionMP
{
    public static final IAttribute CRYSTALLIZE_EFFECT = new RangedAttribute((IAttribute)null, "generic.crystallize_effect", 0.0D, 0.0D, 0.0D).setShouldWatch(true);

    public InfectedCrystallizeEffect()
    {
        super("infected_crystallize", true, ColorHelper.rgbToDecimal(136, 97, 209), 0);
        this.registerPotionAttributeModifier(InfectedCrystallizeEffect.CRYSTALLIZE_EFFECT, "0B0BC323-E263-4EF8-9108-4B6503129B16", 0.0D, 0);
    }

    @Override
    public boolean isReady(int duration, int amplifier)
    {
        if (this == MPPotions.INFECTED_CRYSTALLIZE)
        {
            int k = 20 >> amplifier;
            return k > 0 ? duration % k == 0 : true;
        }
        return false;
    }

    @Override
    public void performEffect(EntityLivingBase living, int food)
    {
        if (this == MPPotions.INFECTED_CRYSTALLIZE)
        {
            living.attackEntityFrom(DamageSourceMP.INFECTED_CRYSTALLIZE, 1.0F);
        }
    }
}