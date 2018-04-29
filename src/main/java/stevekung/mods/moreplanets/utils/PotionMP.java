package stevekung.mods.moreplanets.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PotionMP extends Potion
{
    private int iconIndex;

    public PotionMP(String name, boolean badEffect, int color, int index)
    {
        super(badEffect, color);
        this.setPotionName("potion." + name);
        this.iconIndex = index;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc)
    {
        this.render(x + 6, y + 7, 1);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void renderHUDEffect(int x, int y, PotionEffect effect, Minecraft mc, float alpha)
    {
        this.render(x + 3, y + 3, alpha);
    }

    @SideOnly(Side.CLIENT)
    private void render(int x, int y, float alpha)
    {
        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("moreplanets:textures/potion/icon.png"));
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buf = tessellator.getBuffer();
        buf.begin(7, DefaultVertexFormats.POSITION_TEX);
        GlStateManager.color(1.0F, 1.0F, 1.0F, alpha);
        int textureX = this.iconIndex % 8 * 18;
        int textureY = 198 + this.iconIndex / 8 * 18;
        buf.pos(x, y + 18, 0).tex(textureX * 0.00390625, (textureY + 18) * 0.00390625).endVertex();
        buf.pos(x + 18, y + 18, 0).tex((textureX + 18) * 0.00390625, (textureY + 18) * 0.00390625).endVertex();
        buf.pos(x + 18, y, 0).tex((textureX + 18) * 0.00390625, textureY * 0.00390625).endVertex();
        buf.pos(x, y, 0).tex(textureX * 0.00390625, textureY * 0.00390625).endVertex();
        tessellator.draw();
    }
}