package stevekung.mods.moreplanets.util.client.model;

import micdoodle8.mods.galacticraft.core.client.model.ModelBipedGC;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelBipedTranslucent extends ModelBipedGC
{
    private boolean renderingEnchantment;

    public ModelBipedTranslucent(float modelSize)
    {
        super(modelSize);
    }

    @Override
    public void setLivingAnimations(EntityLivingBase entity, float limbSwing, float limbSwingAmount, float partialTicks)
    {
        super.setLivingAnimations(entity, limbSwing, limbSwingAmount, partialTicks);
        this.renderingEnchantment = false;
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        boolean renderEnchant = true;

        if (this.renderingEnchantment && entity instanceof EntityLivingBase)
        {
            for (int i = 0; i < 4; i++)
            {
                ItemStack stack = ((EntityLivingBase)entity).getEquipmentInSlot(i + 1);

                if (stack != null && stack.getItem() instanceof ItemArmor && stack.hasTagCompound())
                {
                    if (stack.getTagCompound().getTagList("ench", 10) != null)
                    {
                        renderEnchant = false;
                    }
                }
            }
        }

        if (renderEnchant)
        {
            this.actualRender(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        }
        this.renderingEnchantment = true;
    }

    private void actualRender(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);

        if (!this.renderingEnchantment)
        {
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        }

        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);

        if (this.isChild)
        {
            float f = 2.0F;
            GlStateManager.scale(1.5F / f, 1.5F / f, 1.5F / f);
            GlStateManager.translate(0.0F, 16.0F * scale, 0.0F);
            this.bipedHead.render(scale);
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            GlStateManager.scale(1.0F / f, 1.0F / f, 1.0F / f);
            GlStateManager.translate(0.0F, 24.0F * scale, 0.0F);
            this.bipedBody.render(scale);
            this.bipedRightArm.render(scale);
            this.bipedLeftArm.render(scale);
            this.bipedRightLeg.render(scale);
            this.bipedLeftLeg.render(scale);
            this.bipedHeadwear.render(scale);
        }
        else
        {
            if (entity.isSneaking())
            {
                GlStateManager.translate(0.0F, 0.2F, 0.0F);
            }
            this.bipedHead.render(scale);
            this.bipedBody.render(scale);
            this.bipedRightArm.render(scale);
            this.bipedLeftArm.render(scale);
            this.bipedRightLeg.render(scale);
            this.bipedLeftLeg.render(scale);
            this.bipedHeadwear.render(scale);
        }
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }
}