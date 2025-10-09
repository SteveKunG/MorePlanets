package com.stevekung.moreplanets.items;

import com.stevekung.moreplanets.utils.items.ItemFoodMP;

import net.minecraft.item.ItemStack;

public class ItemSpaceFish extends ItemFoodMP
{
    private final ItemType type;

    public ItemSpaceFish(String name, ItemType type)
    {
        super(name);
        this.type = type;
    }

    @Override
    public int getHealAmount(ItemStack itemStack)
    {
        return this.type.getHunger();
    }

    @Override
    public float getSaturationModifier(ItemStack itemStack)
    {
        return this.type.getSaturation();
    }

    public enum ItemType
    {
        ZELIUS_FISH(2, 0.1F),
        GLOWING_ALIEN_FISH(2, 0.1F),
        CHEESE_FISH(2, 0.1F);

        private final int hunger;
        private final float saturation;

        ItemType(int hunger, float saturation)
        {
            this.hunger = hunger;
            this.saturation = saturation;
        }

        public int getHunger()
        {
            return this.hunger;
        }

        public float getSaturation()
        {
            return this.saturation;
        }
    }
}