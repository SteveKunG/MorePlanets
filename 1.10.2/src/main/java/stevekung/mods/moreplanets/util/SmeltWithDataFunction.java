package stevekung.mods.moreplanets.util;

import java.util.Random;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

import net.minecraft.item.ItemStack;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;

public class SmeltWithDataFunction extends LootFunction
{
    private RandomValueRange metaRange;

    public SmeltWithDataFunction(LootCondition[] conditions, RandomValueRange metaRange)
    {
        super(conditions);
        this.metaRange = metaRange;
    }

    @Override
    public ItemStack apply(ItemStack itemStack, Random rand, LootContext context)
    {
        ItemStack dropStack = new ItemStack(itemStack.getItem(), itemStack.stackSize, this.metaRange.generateInt(rand));
        return dropStack;
    }

    public static class Serializer extends LootFunction.Serializer<SmeltWithDataFunction>
    {
        public Serializer()
        {
            super(new ResourceLocation("moreplanets:furnace_smelt_with_data"), SmeltWithDataFunction.class);
        }

        @Override
        public void serialize(JsonObject object, SmeltWithDataFunction functionClazz, JsonSerializationContext serializationContext) {}

        @Override
        public SmeltWithDataFunction deserialize(JsonObject object, JsonDeserializationContext deserializationContext, LootCondition[] conditions)
        {
            return new SmeltWithDataFunction(conditions, JsonUtils.deserializeClass(object, "data", deserializationContext, RandomValueRange.class));
        }
    }
}