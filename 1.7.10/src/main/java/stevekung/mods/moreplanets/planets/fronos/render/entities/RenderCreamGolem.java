/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.render.entities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelSnowMan;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.planets.fronos.entities.EntityCreamGolem;

@SideOnly(Side.CLIENT)
public class RenderCreamGolem extends RenderLiving
{
    private static final ResourceLocation vanillaTextures = new ResourceLocation("fronos:textures/model/cream_golem/vanilla.png");
    private static final ResourceLocation chocolateTextures = new ResourceLocation("fronos:textures/model/cream_golem/chocolate.png");
    private static final ResourceLocation strawberryTextures = new ResourceLocation("fronos:textures/model/cream_golem/strawberry.png");
    private static final ResourceLocation orangeTextures = new ResourceLocation("fronos:textures/model/cream_golem/orange.png");
    private static final ResourceLocation teaTextures = new ResourceLocation("fronos:textures/model/cream_golem/tea.png");
    private static final ResourceLocation lemonTextures = new ResourceLocation("fronos:textures/model/cream_golem/lemon.png");

    public RenderCreamGolem()
    {
        super(new ModelSnowMan(), 0.5F);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return this.func_110874_a((EntityCreamGolem)par1Entity);
    }

    protected ResourceLocation func_110874_a(EntityCreamGolem par1Entity)
    {
        switch (par1Entity.getCreamGolemType())
        {
        case 0:
        default:
            return RenderCreamGolem.vanillaTextures;
        case 1:
            return RenderCreamGolem.chocolateTextures;
        case 2:
            return RenderCreamGolem.strawberryTextures;
        case 3:
            return RenderCreamGolem.orangeTextures;
        case 4:
            return RenderCreamGolem.teaTextures;
        case 5:
            return RenderCreamGolem.lemonTextures;
        }
    }
}