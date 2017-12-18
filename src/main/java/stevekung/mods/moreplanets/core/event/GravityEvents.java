package stevekung.mods.moreplanets.core.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import micdoodle8.mods.galacticraft.api.event.ZeroGravityEvent;
import net.minecraft.entity.player.EntityPlayer;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.planets.pluto.items.PlutoItems;

public class GravityEvents
{
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onZeroGravity(ZeroGravityEvent.Motion event)
    {
        this.runCancelGravityBoots(event);
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onZeroGravity(ZeroGravityEvent.InFreefall event)
    {
        this.runCancelGravityBoots(event);
    }

    @SideOnly(Side.CLIENT)
    private void runCancelGravityBoots(ZeroGravityEvent event)
    {
        if (!(event.entityLiving instanceof EntityPlayer))
        {
            return;
        }
        if (((EntityPlayer)event.entityLiving).inventory.armorInventory[0] != null && ((EntityPlayer)event.entityLiving).inventory.armorInventory[0].getItem() == PlutoItems.gravity_boots)
        {
            event.setCanceled(true);
            MorePlanetsCore.proxy.fixJumping((EntityPlayer) event.entityLiving);
        }
    }
}