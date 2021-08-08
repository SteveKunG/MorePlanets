//package com.stevekung.moreplanets.mixin.forge;
//
//import org.spongepowered.asm.mixin.Mixin;
//import com.stevekung.moreplanets.world.item.GlowingIronArmorItem;
//import net.minecraft.world.entity.Entity;
//import net.minecraft.world.entity.EquipmentSlot;
//import net.minecraft.world.item.ItemStack;
//import net.minecraftforge.common.extensions.IForgeItem;
//
//@Mixin(GlowingIronArmorItem.class)TODO
//public abstract class MixinGlowingIronArmor implements IForgeItem
//{
//    @Override
//    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type)
//    {
//        return "moreplanets:textures/models/armor/glowing_iron_layer_" + (slot == EquipmentSlot.LEGS ? 2 : 1) + ".png";
//    }
//}