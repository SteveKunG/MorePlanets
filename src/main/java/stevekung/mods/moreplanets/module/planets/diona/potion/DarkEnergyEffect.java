package stevekung.mods.moreplanets.module.planets.diona.potion;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.init.MPPotions;
import stevekung.mods.moreplanets.util.DamageSourceMP;
import stevekung.mods.moreplanets.util.PotionMP;
import stevekung.mods.moreplanets.util.helper.ColorHelper;

public class DarkEnergyEffect extends PotionMP
{
    public DarkEnergyEffect()
    {
        super("dark_energy", true, ColorHelper.rgbToDecimal(20, 20, 20));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getStatusIconIndex()
    {
        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("moreplanets:textures/potion/PotionFX.png"));
        return 2;
    }

    @Override
    public boolean isReady(int duration, int amplifier)
    {
        if (this.id == MPPotions.DARK_ENERGY.id)
        {
            int k = 20 >> amplifier;
            return k > 0 ? duration % k == 0 : true;
        }
        return false;
    }

    @Override
    public void performEffect(EntityLivingBase living, int food)
    {
        if (this.id == MPPotions.DARK_ENERGY.id)
        {
            living.attackEntityFrom(DamageSourceMP.DARK_ENERGY, 1.0F);
        }
    }
}