package stevekung.mods.moreplanets.recipe;

import java.util.function.BooleanSupplier;

import com.google.gson.JsonObject;

import net.minecraft.util.JsonUtils;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IConditionFactory;
import net.minecraftforge.common.crafting.JsonContext;
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;

public class SurvivalPlanetCondition implements IConditionFactory
{
    @Override
    public BooleanSupplier parse(JsonContext context, JsonObject json)
    {
        if (JsonUtils.hasField(json, "survival_planet"))
        {
            boolean value = JsonUtils.getBoolean(json, "survival_planet");

            if (value)
            {
                return () -> ConfigManagerMP.moreplanets_general.enableSurvivalPlanetSelection;
            }
        }
        throw new IllegalStateException("JSON Recipe condition error in recipe for " + CraftingHelper.getItemStack(json, context));
    }
}