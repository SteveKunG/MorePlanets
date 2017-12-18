/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.render.entities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelCow;
import net.minecraft.client.renderer.entity.RenderCow;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderMilkCow extends RenderCow
{
    private ResourceLocation textures = new ResourceLocation("fronos:textures/model/milk_cow.png");

    public RenderMilkCow()
    {
        super(new ModelCow(), 0.7F);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return this.textures;
    }
}