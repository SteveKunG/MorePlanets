package com.stevekung.moreplanets.forge.mixin;

import org.spongepowered.asm.mixin.Mixin;
import com.stevekung.moreplanets.world.level.block.entity.DarkEnergyCoreBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.extensions.IForgeBlockEntity;

@Mixin(DarkEnergyCoreBlockEntity.class)
public abstract class MixinDarkEnergyCoreBlockEntity implements IForgeBlockEntity
{
    @Override
    public AABB getRenderBoundingBox()
    {
        var pos = this.self().getBlockPos();
        return new AABB(pos, pos.offset(2, 2, 2));
    }

    private BlockEntity self()
    {
        return (DarkEnergyCoreBlockEntity) (Object) this;
    }
}