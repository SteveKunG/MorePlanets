package stevekung.mods.moreplanets.core.mixin.gc;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.core.command.CommandGCHouston;
import micdoodle8.mods.galacticraft.core.entities.player.GCPlayerStats;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import stevekung.mods.moreplanets.core.event.WorldTickEventHandler;

@Mixin(CommandGCHouston.class)
public class CommandGCHoustonMixin
{
    @Inject(method = "execute", at = @At(value = "INVOKE", target = "net/minecraft/command/CommandBase.notifyCommandListener(Lnet/minecraft/command/ICommandSender;Lnet/minecraft/command/ICommand;Ljava/lang/String;[Ljava/lang/Object;)V", ordinal = 3), locals = LocalCapture.CAPTURE_FAILSOFT)
    private void execute(MinecraftServer server, ICommandSender sender, String[] args, CallbackInfo info, EntityPlayerMP playerBase, boolean isOp, int dimID, WorldServer worldserver, BlockPos spawnPoint, BlockPos bedPos, GCPlayerStats stats, Vector3 spawnPos)
    {
        if (WorldTickEventHandler.survivalPlanetData != null && WorldTickEventHandler.survivalPlanetData.hasSurvivalPlanetData)
        {
            playerBase.inventory.clearMatchingItems(null, -1, -1, null);
            playerBase.inventoryContainer.detectAndSendChanges();

            for (int i = stats.getExtendedInventory().getSizeInventory() - 1; i >= 0; i--)
            {
                ItemStack itemStack = stats.getExtendedInventory().getStackInSlot(i);

                if (!itemStack.isEmpty())
                {
                    stats.getExtendedInventory().setInventorySlotContents(i, ItemStack.EMPTY);
                }
            }
        }
    }
}