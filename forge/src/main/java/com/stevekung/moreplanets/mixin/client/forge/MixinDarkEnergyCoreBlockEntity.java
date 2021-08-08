//package com.stevekung.moreplanets.mixin.forge;
//
//import org.spongepowered.asm.mixin.Mixin;
//import com.stevekung.moreplanets.world.level.block.entity.DarkEnergyCoreBlockEntity;
//import net.minecraft.core.BlockPos;
//import net.minecraft.world.level.block.entity.BlockEntity;
//import net.minecraft.world.phys.AABB;
//import net.minecraftforge.common.extensions.IForgeBlockEntity;
//
//@Mixin(DarkEnergyCoreBlockEntity.class)TODO
//public abstract class MixinDarkEnergyCoreBlockEntity implements IForgeBlockEntity
//{
//    @Override
//    public AABB getRenderBoundingBox()
//    {
//        BlockPos pos = this.self().getBlockPos();
//        return new AABB(pos, pos.offset(2, 2, 2));
//    }
//
//    private BlockEntity self()
//    {
//        return (DarkEnergyCoreBlockEntity) (Object) this;
//    }
//}