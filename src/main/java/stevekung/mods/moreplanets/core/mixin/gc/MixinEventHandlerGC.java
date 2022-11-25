package stevekung.mods.moreplanets.core.mixin.gc;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

import micdoodle8.mods.galacticraft.core.event.EventHandlerGC;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import stevekung.mods.moreplanets.core.event.WorldTickEventHandler;

@Mixin(EventHandlerGC.class)
public class MixinEventHandlerGC
{
    @Redirect(method = "onPlayerRightClickedBlock", remap = false, slice = @Slice(from = @At(value = "FIELD", target = "net/minecraft/init/Blocks.TNT:Lnet/minecraft/block/Block;")), at = @At(value = "INVOKE", target = "net/minecraftforge/event/entity/player/PlayerInteractEvent$RightClickBlock.setCanceled(Z)V", remap = false))
    private void moreplanets$enableIgnitedItem(PlayerInteractEvent.RightClickBlock event, boolean defaultValue)
    {
        if (WorldTickEventHandler.survivalPlanetData != null && WorldTickEventHandler.survivalPlanetData.hasSurvivalPlanetData)
        {
            event.setCanceled(false);
        }
        else
        {
            event.setCanceled(defaultValue);
        }
    }
}