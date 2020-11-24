package stevekung.mods.moreplanets.core.mixin.client;

import javax.annotation.Nullable;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.ParticleDigging;
import net.minecraft.util.math.BlockPos;
import stevekung.mods.moreplanets.init.MPBlocks;

@Mixin(ParticleDigging.class)
public class ParticleDiggingMixin
{
    @Shadow
    @Final
    private IBlockState sourceState;

    @Inject(method = "multiplyColor(Lnet/minecraft/util/math/BlockPos;)V", cancellable = true, at = @At("HEAD"))
    private void multiplyColor(@Nullable BlockPos pos, CallbackInfo info)
    {
        if (this.sourceState.getBlock() == MPBlocks.FRONOS_GRASS_BLOCK)
        {
            info.cancel();
        }
    }
}