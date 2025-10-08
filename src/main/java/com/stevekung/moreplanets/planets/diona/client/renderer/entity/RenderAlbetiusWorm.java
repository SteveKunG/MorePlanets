package com.stevekung.moreplanets.planets.diona.client.renderer.entity;

import com.stevekung.moreplanets.planets.diona.entity.EntityAlbetiusWorm;
import com.stevekung.moreplanets.utils.client.renderer.entity.layer.LayerGlowingTexture;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import micdoodle8.mods.galacticraft.planets.mars.client.model.ModelSludgeling;

@SideOnly(Side.CLIENT)
public class RenderAlbetiusWorm extends RenderLiving<EntityAlbetiusWorm>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("moreplanets:textures/entity/albetius_worm.png");

    public RenderAlbetiusWorm(RenderManager manager)
    {
        super(manager, new ModelSludgeling(), 0.2F);
        this.addLayer(new LayerGlowingTexture<>(this, "albetius_worm_eye", true));
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityAlbetiusWorm entity)
    {
        return RenderAlbetiusWorm.TEXTURE;
    }
}