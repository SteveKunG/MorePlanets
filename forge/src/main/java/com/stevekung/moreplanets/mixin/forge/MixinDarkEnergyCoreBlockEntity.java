package com.stevekung.moreplanets.mixin.forge;

import org.spongepowered.asm.mixin.Mixin;
import com.stevekung.moreplanets.world.level.block.entity.DarkEnergyCoreBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.extensions.IForgeTileEntity;

@Mixin(DarkEnergyCoreBlockEntity.class)
public abstract class MixinDarkEnergyCoreBlockEntity implements IForgeTileEntity
{
    @Override
    public AABB getRenderBoundingBox()
    {
        BlockPos pos = this.getTileEntity().getBlockPos();
        return new AABB(pos, pos.offset(2, 2, 2));
    }
}