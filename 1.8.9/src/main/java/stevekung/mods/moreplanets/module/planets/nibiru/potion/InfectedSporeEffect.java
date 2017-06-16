package stevekung.mods.moreplanets.module.planets.nibiru.potion;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.init.MPPotions;
import stevekung.mods.moreplanets.util.DamageSourceMP;
import stevekung.mods.moreplanets.util.PotionMP;
import stevekung.mods.moreplanets.util.helper.ColorHelper;

public class InfectedSporeEffect extends PotionMP
{
    public InfectedSporeEffect()
    {
        super("infected_spore", true, ColorHelper.rgbToDecimal(205, 85, 50));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getStatusIconIndex()
    {
        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("moreplanets:textures/potion/PotionFX.png"));
        return 1;
    }

    @Override
    public boolean isReady(int duration, int amplifier)
    {
        if (this.id == MPPotions.INFECTED_SPORE.id)
        {
            int k = 20 >> amplifier;
            return k > 0 ? duration % k == 0 : true;
        }
        return false;
    }

    @Override
    public void performEffect(EntityLivingBase living, int food)
    {
        if (this.id == MPPotions.INFECTED_SPORE.id)
        {
            living.attackEntityFrom(DamageSourceMP.INFECTED_GAS, 1.0F);

            if (living instanceof EntityPlayer)
            {
                ((EntityPlayer)living).addExhaustion(0.025F * (food + 1));
            }
        }
    }
}