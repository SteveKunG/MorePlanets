package com.stevekung.moreplanets.core.mixin.block;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import com.llamalad7.mixinextras.sugar.Local;
import com.stevekung.moreplanets.utils.blocks.chest.CustomChestContainer;

import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;

@Mixin(BlockChest.class)
public class MixinBlockChest implements CustomChestContainer
{
    @ModifyArg(method = "getContainer", at = @At(value = "INVOKE", target = "net/minecraft/inventory/InventoryLargeChest.<init>(Ljava/lang/String;Lnet/minecraft/world/ILockableContainer;Lnet/minecraft/world/ILockableContainer;)V"))
    private String moreplanets$injectCustomChestContainerName(String original, @Local Block block)
    {
        String customName = this.getCustomContainerName(block);
        return customName != null ? customName : original;
    }
}