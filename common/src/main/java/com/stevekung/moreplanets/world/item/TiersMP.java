package com.stevekung.moreplanets.world.item;

import java.util.function.Supplier;

import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

public enum TiersMP implements Tier
{
    GLOWING_IRON(2, 375, 6.5F, 2.5F, 14, () -> Ingredient.of(MPItems.GLOWING_IRON_INGOT));

    private final int level;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final LazyLoadedValue<Ingredient> repairIngredient;

    TiersMP(int level, int uses, float speed, float damage, int enchantment, Supplier<Ingredient> supplier)
    {
        this.level = level;
        this.uses = uses;
        this.speed = speed;
        this.damage = damage;
        this.enchantmentValue = enchantment;
        this.repairIngredient = new LazyLoadedValue<>(supplier);
    }

    public int getUses()
    {
        return this.uses;
    }

    public float getSpeed()
    {
        return this.speed;
    }

    public float getAttackDamageBonus()
    {
        return this.damage;
    }

    public int getLevel()
    {
        return this.level;
    }

    public int getEnchantmentValue()
    {
        return this.enchantmentValue;
    }

    public Ingredient getRepairIngredient()
    {
        return this.repairIngredient.get();
    }
}
