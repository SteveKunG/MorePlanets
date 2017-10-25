package stevekung.mods.moreplanets.client.gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.Charsets;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiFullChangeLog extends GuiScreen
{
    private List<String> stringList;
    private GuiChangeLogSlot changeLogSlot;
    private Random rand;

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
    public void initGui()
    {
        this.buttonList.clear();
        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 2 + 120, I18n.format("gui.done")));

        if (this.stringList == null)
        {
            this.stringList = new ArrayList<>();

            try
            {
                String s = "";
                InputStream inputstream = this.mc.getResourceManager().getResource(new ResourceLocation("moreplanets:change_log.txt")).getInputStream();
                BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(inputstream, Charsets.UTF_8));

                while ((s = bufferedreader.readLine()) != null)
                {
                    s = s.replaceAll("-Added-", TextFormatting.GREEN + "+" + TextFormatting.RESET);
                    s = s.replaceAll("-Remove-", TextFormatting.RED + "-" + TextFormatting.RESET);
                    s = s.replaceAll("-Fixed-", TextFormatting.GOLD + "*" + TextFormatting.RESET);
                    s = s.replaceAll("-Update-", TextFormatting.YELLOW + "*" + TextFormatting.RESET);
                    this.stringList.addAll(this.mc.fontRendererObj.listFormattedStringToWidth(s, 264));
                    this.rand = new Random();
                }
                inputstream.close();
            }
            catch (Exception e) {}
        }
        this.changeLogSlot = new GuiChangeLogSlot(this.mc, this, this.stringList, this.width, this.height, this.rand.nextBoolean());
        this.changeLogSlot.registerScrollButtons(1, 1);
    }

    @Override
    public void handleMouseInput() throws IOException
    {
        super.handleMouseInput();

        if (this.changeLogSlot != null)
        {
            this.changeLogSlot.handleMouseInput();
        }
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
        if (keyCode == 1 || keyCode == 28)
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
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        if (this.changeLogSlot != null)
        {
            this.changeLogSlot.drawScreen(mouseX, mouseY, partialTicks);
        }
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}