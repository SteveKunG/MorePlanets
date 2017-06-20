package stevekung.mods.moreplanets.items.capsule.effect;

import java.util.Collection;

import com.google.common.collect.Ordering;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.init.MPPotions;
import stevekung.mods.moreplanets.util.PotionMP;
import stevekung.mods.moreplanets.util.helper.ColorHelper;

public class InfectedSporeProtectionEffect extends PotionMP
{
    public InfectedSporeProtectionEffect()
    {
        super("infected_spore_protection", false, ColorHelper.rgbToDecimal(200, 200, 200));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getStatusIconIndex()
    {
        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("moreplanets:textures/potion/PotionFX.png"));
        return 3;
    }

    @Override
    public void performEffect(EntityLivingBase living, int food)
    {
        if (this == MPPotions.INFECTED_SPORE_PROTECTION)
        {
            living.removePotionEffect(MPPotions.INFECTED_SPORE);
        }
    }

    @Override
    public boolean shouldRender(PotionEffect effect)
    {
        return true;
    }

    @Override
    public boolean shouldRenderInvText(PotionEffect effect)
    {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc)
    {
        Collection<PotionEffect> collection = mc.thePlayer.getActivePotionEffects();

        for (PotionEffect potioneffect : Ordering.natural().sortedCopy(collection))
        {
            Potion potion = potioneffect.getPotion();
            String s1 = I18n.format(potion.getName(), new Object[0]);

            if (effect.getAmplifier() == 1)
            {
                s1 = s1 + " " + I18n.format("enchantment.level.2", new Object[0]);
            }
            else if (effect.getAmplifier() == 2)
            {
                s1 = s1 + " " + I18n.format("enchantment.level.3", new Object[0]);
            }
            else if (effect.getAmplifier() == 3)
            {
                s1 = s1 + " " + I18n.format("enchantment.level.4", new Object[0]);
            }
            mc.fontRendererObj.drawStringWithShadow(s1, x + 10 + 18, y + 6, 16777215);
            String s = this.getDurationTime(effect);
            mc.fontRendererObj.drawStringWithShadow(s, x + 10 + 18, y + 6 + 10, 8355711);
        }
    }

    @SideOnly(Side.CLIENT)
    private String getDurationTime(PotionEffect effect)
    {
        if (effect.getDuration() > 36020)
        {
            return "**:**";
        }
        else
        {
            return StringUtils.ticksToElapsedTime(effect.getDuration());
        }
    }
}