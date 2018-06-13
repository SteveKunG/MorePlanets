package stevekung.mods.moreplanets.planets.nibiru.potion;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.init.MPPotions;
import stevekung.mods.moreplanets.utils.DamageSourceMP;
import stevekung.mods.moreplanets.utils.PotionMP;
import stevekung.mods.stevekunglib.utils.ColorUtils;

public class InfectedSporeEffect extends PotionMP
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("moreplanets:textures/potions/infected_spore.png");

    public InfectedSporeEffect()
    {
        super("infected_spore", true, ColorUtils.rgbToDecimal(205, 85, 50));
    }

    @Override
    public boolean isReady(int duration, int amplifier)
    {
        if (this == MPPotions.INFECTED_SPORE)
        {
            int k = 20 >> amplifier;
            return k > 0 ? duration % k == 0 : true;
        }
        return false;
    }

    @Override
    public void performEffect(EntityLivingBase living, int food)
    {
        if (this == MPPotions.INFECTED_SPORE)
        {
            living.attackEntityFrom(DamageSourceMP.INFECTED_GAS, 1.0F);

            if (living instanceof EntityPlayer)
            {
                ((EntityPlayer)living).addExhaustion(0.025F * (food + 1));
            }
        }
    }

    @Override
    protected ResourceLocation getPotionIcon()
    {
        return InfectedSporeEffect.TEXTURE;
    }
}