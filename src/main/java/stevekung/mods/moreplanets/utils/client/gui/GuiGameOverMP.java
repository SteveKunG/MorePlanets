package stevekung.mods.moreplanets.utils.client.gui;

import java.io.IOException;

import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import net.minecraft.client.gui.*;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.network.PacketSimpleMP;
import stevekung.mods.moreplanets.network.PacketSimpleMP.EnumSimplePacketMP;
import stevekung.mods.stevekunglib.utils.LangUtils;

@SideOnly(Side.CLIENT)
public class GuiGameOverMP extends GuiScreen implements GuiYesNoCallback
{
    private int enableButtonsTimer;

    @Override
    public void initGui()
    {
        this.buttonList.clear();

        if (this.mc.world.getWorldInfo().isHardcoreModeEnabled())
        {
            if (this.mc.isIntegratedServerRunning())
            {
                this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 96, LangUtils.translate("deathScreen.deleteWorld", new Object[0])));
            }
            else
            {
                this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 96, LangUtils.translate("deathScreen.leaveServer", new Object[0])));
            }
        }
        else
        {
            this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 72, LangUtils.translate("deathScreen.respawn", new Object[0])));
            this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 96, LangUtils.translate("deathScreen.titleScreen", new Object[0])));

            if (this.mc.getSession() == null)
            {
                this.buttonList.get(1).enabled = false;
            }
        }

        for (GuiButton guibutton : this.buttonList)
        {
            guibutton.enabled = false;
        }
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {}

    @Override
    protected void actionPerformed(GuiButton button) throws IOException
    {
        switch (button.id)
        {
        case 0:
            GalacticraftCore.packetPipeline.sendToServer(new PacketSimpleMP(EnumSimplePacketMP.S_RESPAWN_PLAYER_NETHER, GCCoreUtil.getDimensionID(this.mc.world)));
            this.mc.displayGuiScreen((GuiScreen)null);
            break;
        case 1:
            if (this.mc.world.getWorldInfo().isHardcoreModeEnabled())
            {
                this.mc.displayGuiScreen(new GuiMainMenu());
            }
            else
            {
                GuiYesNo guiyesno = new GuiYesNo(this, LangUtils.translate("deathScreen.quit.confirm"), "", LangUtils.translate("deathScreen.titleScreen"), LangUtils.translate("deathScreen.respawn"), 0);
                this.mc.displayGuiScreen(guiyesno);
                guiyesno.setButtonDelay(20);
            }
        }
    }

    @Override
    public void confirmClicked(boolean result, int id)
    {
        if (result)
        {
            this.mc.world.sendQuittingDisconnectingPacket();
            this.mc.loadWorld((WorldClient)null);
            this.mc.displayGuiScreen(new GuiMainMenu());
        }
        else
        {
            GalacticraftCore.packetPipeline.sendToServer(new PacketSimpleMP(EnumSimplePacketMP.S_RESPAWN_PLAYER_NETHER, GCCoreUtil.getDimensionID(this.mc.world)));
            this.mc.displayGuiScreen((GuiScreen)null);
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawGradientRect(0, 0, this.width, this.height, 1615855616, -1602211792);
        GlStateManager.pushMatrix();
        GlStateManager.scale(2.0F, 2.0F, 2.0F);
        boolean flag = this.mc.world.getWorldInfo().isHardcoreModeEnabled();
        String s = flag ? LangUtils.translate("deathScreen.title.hardcore") : LangUtils.translate("deathScreen.title");
        this.drawCenteredString(this.fontRenderer, s, this.width / 2 / 2, 30, 16777215);
        GlStateManager.popMatrix();

        if (flag)
        {
            this.drawCenteredString(this.fontRenderer, LangUtils.translate("deathScreen.hardcoreInfo"), this.width / 2, 144, 16777215);
        }
        this.drawCenteredString(this.fontRenderer, LangUtils.translate("deathScreen.score") + ": " + TextFormatting.YELLOW + this.mc.player.getScore(), this.width / 2, 100, 16777215);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean doesGuiPauseGame()
    {
        return false;
    }

    @Override
    public void updateScreen()
    {
        super.updateScreen();
        ++this.enableButtonsTimer;

        if (this.enableButtonsTimer == 20)
        {
            for (GuiButton guibutton : this.buttonList)
            {
                guibutton.enabled = true;
            }
        }
    }
}