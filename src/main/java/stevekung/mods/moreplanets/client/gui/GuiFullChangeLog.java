package stevekung.mods.moreplanets.client.gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.commons.io.Charsets;

import com.google.common.collect.Lists;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.MorePlanetsCore;

@SideOnly(Side.CLIENT)
public class GuiFullChangeLog extends GuiScreen
{
    private List<String> stringList;

    public void display()
    {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onClientTick(ClientTickEvent event)
    {
        Minecraft.getMinecraft().displayGuiScreen(this);
        MinecraftForge.EVENT_BUS.unregister(this);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException
    {
        if (button.id == 0)
        {
            this.mc.displayGuiScreen((GuiScreen)null);
        }
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException
    {
        if (keyCode == 1)
        {
            this.mc.displayGuiScreen((GuiScreen)null);
        }
    }

    @Override
    public boolean doesGuiPauseGame()
    {
        return true;
    }

    @Override
    public void initGui()
    {
        this.buttonList.clear();
        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 2 + 120, I18n.format("gui.done")));

        if (this.stringList == null)
        {
            this.stringList = Lists.<String>newArrayList();

            try
            {
                String s = "";
                InputStream inputstream = this.mc.getResourceManager().getResource(new ResourceLocation("moreplanets:change_log.txt")).getInputStream();
                BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(inputstream, Charsets.UTF_8));

                while ((s = bufferedreader.readLine()) != null)
                {
                    s = s.replaceAll("-Added-", EnumChatFormatting.GREEN + "+" + EnumChatFormatting.RESET);
                    s = s.replaceAll("-Remove-", EnumChatFormatting.RED + "-" + EnumChatFormatting.RESET);
                    s = s.replaceAll("-Fixed-", EnumChatFormatting.GOLD + "*" + EnumChatFormatting.RESET);
                    s = s.replaceAll("-Update-", EnumChatFormatting.YELLOW + "*" + EnumChatFormatting.RESET);
                    this.stringList.addAll(this.mc.fontRendererObj.listFormattedStringToWidth(s, 360));
                    this.stringList.add("");
                }
                inputstream.close();
            }
            catch (Exception e) {}
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, "More Planets " + MorePlanetsCore.VERSION + " Change Log", this.width / 2, 16, 16777215);
        int i = 274;
        int j = this.width / 2 - i / 2;
        int k = 90;
        int l = k - 50;

        for (int i1 = 0; i1 < this.stringList.size(); ++i1)
        {
            String s = this.stringList.get(i1);
            this.fontRendererObj.drawStringWithShadow(s, j, l, 16777215);
            l += 6;
        }
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}