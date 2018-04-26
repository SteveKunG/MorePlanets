package stevekung.mods.stevekunglib.client.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;

public class ClientEventHandler
{
    private Minecraft mc;
    public static int ticks;

    public ClientEventHandler()
    {
        this.mc = Minecraft.getMinecraft();
    }

    @SubscribeEvent
    public void onClientTick(ClientTickEvent event)
    {
        if (this.mc.currentScreen instanceof GuiMainMenu)
        {
            ClientEventHandler.ticks = 0;
        }
        if (event.phase == Phase.START)
        {
            ClientEventHandler.ticks++;
            ClientEventHandler.ticks %= 1200;
        }
    }
}