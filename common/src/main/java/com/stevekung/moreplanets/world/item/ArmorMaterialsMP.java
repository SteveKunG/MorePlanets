package com.stevekung.moreplanets.world.item;

import java.util.function.Supplier;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

public enum ArmorMaterialsMP implements ArmorMaterial
{
    GLOWING_IRON("glowing_iron", 16, new int[] {3, 6, 7, 3}, 9, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> Ingredient.of(MPItems.GLOWING_IRON_INGOT));

    private static final int[] HEALTH_PER_SLOT = new int[] {13, 15, 16, 11};
    private final String name;
    private final int durabilityMultiplier;
    private final int[] slotProtections;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final LazyLoadedValue<Ingredient> repairIngredient;

    ArmorMaterialsMP(String name, int durabilityMultiplier, int[] slotProtections, int enchantment, SoundEvent soundEvent, float toughness, float knockbackResistance, Supplier<Ingredient> supplier)
    {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.slotProtections = slotProtections;
        this.enchantmentValue = enchantment;
        this.sound = soundEvent;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = new LazyLoadedValue<>(supplier);
    }

    public int getDurabilityForSlot(EquipmentSlot equipmentSlot)
    {
        return HEALTH_PER_SLOT[equipmentSlot.getIndex()] * this.durabilityMultiplier;
    }

    public int getDefenseForSlot(EquipmentSlot equipmentSlot)
    {
        return this.slotProtections[equipmentSlot.getIndex()];
    }

    public int getEnchantmentValue()
    {
        return this.enchantmentValue;
    }

    public SoundEvent getEquipSound()
    {
        return this.sound;
    }

    public Ingredient getRepairIngredient()
    {
        return this.repairIngredient.get();
    }

    public String getName()
    {
        return this.name;
    }

    public float getToughness()
    {
        return this.toughness;
    }

    public float getKnockbackResistance()
    {
        return this.knockbackResistance;
    }
}