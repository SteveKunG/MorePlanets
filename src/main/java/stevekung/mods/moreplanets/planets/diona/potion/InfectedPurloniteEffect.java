package stevekung.mods.moreplanets.planets.diona.potion;

import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.init.MPPotions;
import stevekung.mods.moreplanets.network.PacketSimpleMP;
import stevekung.mods.moreplanets.network.PacketSimpleMP.EnumSimplePacketMP;
import stevekung.mods.moreplanets.utils.DamageSourceMP;
import stevekung.mods.moreplanets.utils.PotionMP;
import stevekung.mods.stevekunglib.utils.ColorUtils;

public class InfectedPurloniteEffect extends PotionMP
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("moreplanets:textures/potions/infected_purlonite.png");

    public InfectedPurloniteEffect()
    {
        super("infected_purlonite", true, ColorUtils.rgbToDecimal(136, 97, 209));
    }

    @Override
    public void applyAttributesModifiersToEntity(EntityLivingBase living, AbstractAttributeMap attributeMap, int amplifier)
    {
        int id = GCCoreUtil.getDimensionID(living.world);
        PacketSimpleMP.sendToAllAround(new PacketSimpleMP(EnumSimplePacketMP.C_ADD_ENTITY_ID, id, String.valueOf(living.getEntityId())), living.world, id, living.getPosition(), 64);
        super.applyAttributesModifiersToEntity(living, attributeMap, amplifier);
    }

    @Override
    public void removeAttributesModifiersFromEntity(EntityLivingBase living, AbstractAttributeMap attributeMap, int amplifier)
    {
        int id = GCCoreUtil.getDimensionID(living.world);
        PacketSimpleMP.sendToAllAround(new PacketSimpleMP(EnumSimplePacketMP.C_REMOVE_ENTITY_ID, id, String.valueOf(living.getEntityId())), living.world, id, living.getPosition(), 64);
        super.removeAttributesModifiersFromEntity(living, attributeMap, amplifier);
    }

    @Override
    public boolean isReady(int duration, int amplifier)
    {
        if (this == MPPotions.INFECTED_PURLONITE)
        {
            int k = 20 >> amplifier;
            return k > 0 ? duration % k == 0 : true;
        }
        return false;
    }

    @Override
    public void performEffect(EntityLivingBase living, int food)
    {
        if (this == MPPotions.INFECTED_PURLONITE)
        {
            living.attackEntityFrom(DamageSourceMP.INFECTED_PURLONITE, 1.0F);
        }
    }

    @Override
    protected ResourceLocation getPotionIcon()
    {
        return InfectedPurloniteEffect.TEXTURE;
    }
}