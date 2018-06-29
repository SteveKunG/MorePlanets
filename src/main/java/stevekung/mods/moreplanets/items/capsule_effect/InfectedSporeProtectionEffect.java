package stevekung.mods.moreplanets.items.capsule_effect;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.init.MPPotions;
import stevekung.mods.moreplanets.utils.PotionMP;
import stevekung.mods.stevekunglib.utils.ColorUtils;
import stevekung.mods.stevekunglib.utils.CommonUtils;
import stevekung.mods.stevekunglib.utils.LangUtils;

public class InfectedSporeProtectionEffect extends PotionMP
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("moreplanets:textures/potions/infected_spore_protection.png");

    public InfectedSporeProtectionEffect()
    {
        super("infected_spore_protection", false, ColorUtils.rgbToDecimal(200, 200, 200));
        this.setBeneficial();
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
    protected ResourceLocation getPotionIcon()
    {
        return InfectedSporeProtectionEffect.TEXTURE;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc)
    {
        super.renderInventoryEffect(x, y, effect, mc);

        String potionName = LangUtils.translate(effect.getEffectName());

        if (effect.getAmplifier() == 1)
        {
            potionName = potionName + " " + LangUtils.translate("enchantment.level.2");
        }
        else if (effect.getAmplifier() == 2)
        {
            potionName = potionName + " " + LangUtils.translate("enchantment.level.3");
        }
        else if (effect.getAmplifier() == 3)
        {
            potionName = potionName + " " + LangUtils.translate("enchantment.level.4");
        }
        mc.fontRenderer.drawStringWithShadow(potionName, x + 10 + 18, y + 6, 16777215);
        int i = MathHelper.floor(effect.getDuration() * 1.0F);
        String s = CommonUtils.ticksToElapsedTime(i);
        mc.fontRenderer.drawStringWithShadow(s, x + 10 + 18, y + 6 + 10, 8355711);
    }

    @Override
    public boolean shouldRenderInvText(PotionEffect effect)
    {
        return false;
    }

    @Override
    public List<ItemStack> getCurativeItems()
    {
        return new ArrayList<>();
    }
}