package stevekung.mods.moreplanets.items.capsule.effect;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.init.MPPotions;
import stevekung.mods.moreplanets.util.PotionMP;
import stevekung.mods.moreplanets.util.helper.ColorHelper;

public class DarkEnergyProtectionEffect extends PotionMP
{
    public DarkEnergyProtectionEffect()
    {
        super("dark_energy_protection", false, ColorHelper.rgbToDecimal(200, 200, 200));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getStatusIconIndex()
    {
        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("moreplanets:textures/potion/PotionFX.png"));
        return 4;
    }

    @Override
    public void performEffect(EntityLivingBase living, int food)
    {
        if (this.id == MPPotions.DARK_ENERGY_PROTECTION.id)
        {
            living.removePotionEffect(MPPotions.DARK_ENERGY.id);
        }
    }
}