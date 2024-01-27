package stevekung.mods.moreplanets.core.mixin.gc;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import com.llamalad7.mixinextras.sugar.Local;

import micdoodle8.mods.galacticraft.core.event.EventHandlerGC;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import stevekung.mods.moreplanets.utils.SurvivalPlanetUtils;

@Mixin(EventHandlerGC.class)
public class MixinEventHandlerGC
{
    @ModifyArg(method = "onPlayerRightClickedBlock", remap = false, at = @At(value = "INVOKE", target = "net/minecraftforge/event/entity/player/PlayerInteractEvent$RightClickBlock.setCanceled(Z)V", ordinal = 4, remap = false))
    private boolean moreplanets$enableIgnitedItem(boolean original, @Local Block blockClicked)
    {
        if (blockClicked == Blocks.OBSIDIAN && (SurvivalPlanetUtils.hasSurvivalPlanetData() || SurvivalPlanetUtils.hasSurvivalPlanetDataForServer()))
        {
            return false;
        }
        else
        {
            return original;
        }
    }
}