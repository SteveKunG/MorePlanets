/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.nei;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import codechicken.nei.PositionedStack;
import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
import stevekung.mods.moreplanets.planets.fronos.items.FronosItems;
import stevekung.mods.moreplanets.planets.nibiru.items.NibiruItems;

public class NEIFronosConfig implements IConfigureNEI
{
    private static HashMap<ArrayList<PositionedStack>, PositionedStack> rocketBenchRecipes = new HashMap<ArrayList<PositionedStack>, PositionedStack>();

    @Override
    public void loadConfig()
    {
        API.registerRecipeHandler(new FuelRecipeHandlerMP());
        API.registerUsageHandler(new FuelRecipeHandlerMP());
        API.registerRecipeHandler(new CandyExtractorRecipeHandler());
        API.registerUsageHandler(new CandyExtractorRecipeHandler());

        if (ConfigManagerMP.enableTier7RocketRecipe)
        {
            API.registerRecipeHandler(new Tier7RocketRecipeHandlerMP());
            API.registerUsageHandler(new Tier7RocketRecipeHandlerMP());
            this.addRocketRecipes();
        }
    }

    @Override
    public String getName()
    {
        return "More Planet's : Fronos NEI Plugin";
    }

    @Override
    public String getVersion()
    {
        return MorePlanetsCore.VERSION;
    }

    public void registerRocketBenchRecipe(ArrayList<PositionedStack> input, PositionedStack output)
    {
        NEIFronosConfig.rocketBenchRecipes.put(input, output);
    }

    public static Set<Map.Entry<ArrayList<PositionedStack>, PositionedStack>> getRocketBenchRecipes()
    {
        return NEIFronosConfig.rocketBenchRecipes.entrySet();
    }

    public void addRocketRecipes()
    {
        final int changeY = 15;
        final ArrayList<PositionedStack> input1 = new ArrayList<PositionedStack>();

        input1.add(new PositionedStack(new ItemStack(NibiruItems.tier7_rocket_module, 1, 4), 45, -8 + changeY));
        input1.add(new PositionedStack(new ItemStack(NibiruItems.tier7_rocket_module, 1, 2), 36, -6 + 16 + changeY));
        input1.add(new PositionedStack(new ItemStack(NibiruItems.tier7_rocket_module, 1, 2), 36, -6 + 18 + 16 + changeY));
        input1.add(new PositionedStack(new ItemStack(NibiruItems.tier7_rocket_module, 1, 2), 36, -6 + 36 + 16 + changeY));
        input1.add(new PositionedStack(new ItemStack(NibiruItems.tier7_rocket_module, 1, 2), 36, -6 + 54 + 16 + changeY));
        input1.add(new PositionedStack(new ItemStack(NibiruItems.tier7_rocket_module, 1, 2), 36, -6 + 72 + 16 + changeY));
        input1.add(new PositionedStack(new ItemStack(NibiruItems.tier7_rocket_module, 1, 2), 54, -6 + 16 + changeY));
        input1.add(new PositionedStack(new ItemStack(NibiruItems.tier7_rocket_module, 1, 2), 54, -6 + 18 + 16 + changeY));
        input1.add(new PositionedStack(new ItemStack(NibiruItems.tier7_rocket_module, 1, 2), 54, -6 + 36 + 16 + changeY));
        input1.add(new PositionedStack(new ItemStack(NibiruItems.tier7_rocket_module, 1, 2), 54, -6 + 54 + 16 + changeY));
        input1.add(new PositionedStack(new ItemStack(NibiruItems.tier7_rocket_module, 1, 2), 54, -6 + 72 + 16 + changeY));
        input1.add(new PositionedStack(new ItemStack(NibiruItems.tier7_rocket_module, 1, 0), 45, 100 + changeY));//Engine
        input1.add(new PositionedStack(new ItemStack(NibiruItems.tier7_rocket_module, 1, 1), 18, 64 + changeY));//Booster
        input1.add(new PositionedStack(new ItemStack(NibiruItems.tier7_rocket_module, 1, 1), 72, 64 + changeY));//Booster
        input1.add(new PositionedStack(new ItemStack(NibiruItems.tier7_rocket_module, 1, 3), 18, 82 + changeY));//Fins
        input1.add(new PositionedStack(new ItemStack(NibiruItems.tier7_rocket_module, 1, 3), 18, 100 + changeY));
        input1.add(new PositionedStack(new ItemStack(NibiruItems.tier7_rocket_module, 1, 3), 72, 82 + changeY));
        input1.add(new PositionedStack(new ItemStack(NibiruItems.tier7_rocket_module, 1, 3), 72, 100 + changeY));
        this.registerRocketBenchRecipe(input1, new PositionedStack(new ItemStack(FronosItems.tier7_rocket, 1, 0), 139, 87 + changeY));

        ArrayList<PositionedStack> input2 = new ArrayList<PositionedStack>(input1);
        input2.add(new PositionedStack(new ItemStack(Blocks.chest), 90, -15 + changeY));
        this.registerRocketBenchRecipe(input2, new PositionedStack(new ItemStack(FronosItems.tier7_rocket, 1, 1), 139, 87 + changeY));

        input2 = new ArrayList<PositionedStack>(input1);
        input2.add(new PositionedStack(new ItemStack(Blocks.chest), 90 + 26, -15 + changeY));
        this.registerRocketBenchRecipe(input2, new PositionedStack(new ItemStack(FronosItems.tier7_rocket, 1, 1), 139, 87 + changeY));

        input2 = new ArrayList<PositionedStack>(input1);
        input2.add(new PositionedStack(new ItemStack(Blocks.chest), 90 + 52, -15 + changeY));
        this.registerRocketBenchRecipe(input2, new PositionedStack(new ItemStack(FronosItems.tier7_rocket, 1, 1), 139, 87 + changeY));

        input2 = new ArrayList<PositionedStack>(input1);
        input2.add(new PositionedStack(new ItemStack(Blocks.chest), 90, -15 + changeY));
        input2.add(new PositionedStack(new ItemStack(Blocks.chest), 90 + 26, -15 + changeY));
        this.registerRocketBenchRecipe(input2, new PositionedStack(new ItemStack(FronosItems.tier7_rocket, 1, 2), 139, 87 + changeY));

        input2 = new ArrayList<PositionedStack>(input1);
        input2.add(new PositionedStack(new ItemStack(Blocks.chest), 90 + 26, -15 + changeY));
        input2.add(new PositionedStack(new ItemStack(Blocks.chest), 90 + 52, -15 + changeY));
        this.registerRocketBenchRecipe(input2, new PositionedStack(new ItemStack(FronosItems.tier7_rocket, 1, 2), 139, 87 + changeY));

        input2 = new ArrayList<PositionedStack>(input1);
        input2.add(new PositionedStack(new ItemStack(Blocks.chest), 90, -15 + changeY));
        input2.add(new PositionedStack(new ItemStack(Blocks.chest), 90 + 52, -15 + changeY));
        this.registerRocketBenchRecipe(input2, new PositionedStack(new ItemStack(FronosItems.tier7_rocket, 1, 2), 139, 87 + changeY));

        input2 = new ArrayList<PositionedStack>(input1);
        input2.add(new PositionedStack(new ItemStack(Blocks.chest), 90, -15 + changeY));
        input2.add(new PositionedStack(new ItemStack(Blocks.chest), 90 + 26, -15 + changeY));
        input2.add(new PositionedStack(new ItemStack(Blocks.chest), 90 + 52, -15 + changeY));
        this.registerRocketBenchRecipe(input2, new PositionedStack(new ItemStack(FronosItems.tier7_rocket, 1, 3), 139, 87 + changeY));
    }
}