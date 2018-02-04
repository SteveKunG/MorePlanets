package stevekung.mods.moreplanets.util.debug;

import java.io.IOException;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiGetItemName extends GuiScreen
{
    private GuiTextField textField;

    @Override
    public void updateScreen()
    {
        this.textField.updateCursorCounter();
    }

    @Override
    public void initGui()
    {
        this.textField = new GuiTextField(0, this.fontRendererObj, this.width / 2 - 150, 50, 300, 20);
        this.textField.setMaxStringLength(Integer.MAX_VALUE);

        if (!this.mc.player.getHeldItemMainhand().isEmpty())
        {
            this.textField.setText(this.mc.player.getHeldItemMainhand().getDisplayName());
            GuiScreen.setClipboardString(this.mc.player.getHeldItemMainhand().getDisplayName());
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
    {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        this.textField.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        this.textField.drawTextBox();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean doesGuiPauseGame()
    {
        return false;
    }
}