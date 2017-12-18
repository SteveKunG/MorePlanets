package stevekung.mods.moreplanets.client.gui.test;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiListEntityFilterEntry implements GuiListExtended.IGuiListEntry
{
    private final GuiListEntityFilter containingListSel;
    private final Minecraft mc = Minecraft.getMinecraft();
    private final String entityName;

    public GuiListEntityFilterEntry(GuiListEntityFilter list, String entityName)
    {
        this.containingListSel = list;
        this.entityName = entityName;
    }

    @Override
    public void drawEntry(int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX, int mouseY, boolean isSelected)
    {
        this.mc.fontRendererObj.drawString(this.entityName, x + 32 + 3, y + 1, 16777215);
        drawEntityOnScreen(x + 51, y + 75, 20, mouseX, mouseY, EntityList.createEntityByIDFromName(this.entityName, this.mc.theWorld));
    }

    public static void drawEntityOnScreen(int posX, int posY, int scale, float mouseX, float mouseY, Entity entity)
    {
        if (entity instanceof EntityItem)
        {
            ((EntityItem)entity).setEntityItemStack(new ItemStack(Blocks.STONE));
        }
        GlStateManager.enableColorMaterial();
        GlStateManager.pushMatrix();
        GlStateManager.translate(posX, posY, 50.0F);
        GlStateManager.scale(-scale, scale, scale);
        GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
        //        float f = ent.renderYawOffset;
        float f1 = entity.rotationYaw;
        float f2 = entity.rotationPitch;
        //        float f3 = ent.prevRotationYawHead;
        //        float f4 = ent.rotationYawHead;
        GlStateManager.rotate(135.0F, 0.0F, 1.0F, 0.0F);
        RenderHelper.enableStandardItemLighting();
        GlStateManager.rotate(-135.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(-((float)Math.atan(mouseY / 40.0F)) * 20.0F, 1.0F, 0.0F, 0.0F);
        //        ent.renderYawOffset = (float)Math.atan((double)(mouseX / 40.0F)) * 20.0F;
        entity.rotationYaw = (float)Math.atan(mouseX / 40.0F) * 40.0F;
        entity.rotationPitch = -((float)Math.atan(mouseY / 40.0F)) * 20.0F;
        //        ent.rotationYawHead = ent.rotationYaw;
        //        ent.prevRotationYawHead = ent.rotationYaw;
        GlStateManager.translate(0.0F, 0.0F, 0.0F);
        RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
        rendermanager.setPlayerViewY(180.0F);
        rendermanager.setRenderShadow(false);
        rendermanager.doRenderEntity(entity, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, false);
        rendermanager.setRenderShadow(true);
        //        ent.renderYawOffset = f;
        entity.rotationYaw = f1;
        entity.rotationPitch = f2;
        //        ent.prevRotationYawHead = f3;
        //        ent.rotationYawHead = f4;
        GlStateManager.popMatrix();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableRescaleNormal();
        GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GlStateManager.disableTexture2D();
        GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
    }

    @Override
    public boolean mousePressed(int slotIndex, int mouseX, int mouseY, int mouseEvent, int relativeX, int relativeY)
    {
        this.containingListSel.selectEntity(slotIndex);
        return true;
        //
        //        if (relativeX <= 32 && relativeX < 32)
        //        {
        //            this.joinWorld();
        //            return true;
        //        }
        //        else if (Minecraft.getSystemTime() - this.lastClickTime < 250L)
        //        {
        //            this.joinWorld();
        //            return true;
        //        }
        //        else
        //        {
        //            this.lastClickTime = Minecraft.getSystemTime();
        //            return false;
        //        }
    }

    @Override
    public void mouseReleased(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY)
    {
    }

    @Override
    public void setSelected(int entryID, int insideLeft, int yPos)
    {
    }
}