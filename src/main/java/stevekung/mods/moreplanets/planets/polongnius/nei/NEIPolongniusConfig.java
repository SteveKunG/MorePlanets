/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.polongnius.nei;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import codechicken.nei.PositionedStack;
import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import micdoodle8.mods.galacticraft.planets.asteroids.items.AsteroidsItems;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
import stevekung.mods.moreplanets.planets.diona.items.DionaItems;
import stevekung.mods.moreplanets.planets.polongnius.items.PolongniusItems;

public class NEIPolongniusConfig implements IConfigureNEI
{
    private static HashMap<ArrayList<PositionedStack>, PositionedStack> rocketBenchRecipes = new HashMap<ArrayList<PositionedStack>, PositionedStack>();
    private static HashMap<ArrayList<PositionedStack>, PositionedStack> rocketBenchNoFlagRecipes = new HashMap<ArrayList<PositionedStack>, PositionedStack>();

    @Override
    public void loadConfig()
    {
        if (ConfigManagerMP.enableTier5RocketRecipe)
        {
            this.addRocketRecipes();
            this.addRocketNoFlagRecipes();
            API.registerRecipeHandler(new Tier5RocketRecipeHandlerMP());
            API.registerUsageHandler(new Tier5RocketRecipeHandlerMP());
            API.registerRecipeHandler(new Tier5RocketNoFlagRecipeHandlerMP());
            API.registerUsageHandler(new Tier5RocketNoFlagRecipeHandlerMP());
        }
    }

    @Override
    public String getName()
    {
        return "More Planet's : Polongnius NEI Plugin";
    }

    @Override
    public String getVersion()
    {
        return MorePlanetsCore.VERSION;
    }

    public void registerRocketBenchRecipe(ArrayList<PositionedStack> input, PositionedStack output)
    {
        NEIPolongniusConfig.rocketBenchRecipes.put(input, output);
    }

    public static Set<Map.Entry<ArrayList<PositionedStack>, PositionedStack>> getRocketBenchRecipes()
    {
        return NEIPolongniusConfig.rocketBenchRecipes.entrySet();
    }

    public void registerRocketBenchNoFlagRecipe(ArrayList<PositionedStack> input, PositionedStack output)
    {
        NEIPolongniusConfig.rocketBenchNoFlagRecipes.put(input, output);
    }

    public static Set<Map.Entry<ArrayList<PositionedStack>, PositionedStack>> getRocketBenchNoFlagRecipes()
    {
        return NEIPolongniusConfig.rocketBenchNoFlagRecipes.entrySet();
    }

    public void addRocketRecipes()
    {
        int changeY = 15;

        ArrayList<PositionedStack> input1 = new ArrayList<PositionedStack>();

        input1.add(new PositionedStack(new ItemStack(DionaItems.tier4_rocket_module, 1, 0), 45, -8 + changeY));
        input1.add(new PositionedStack(new ItemStack(DionaItems.diona_item, 1, 4), 36, -6 + 16 + changeY));
        input1.add(new PositionedStack(new ItemStack(DionaItems.diona_item, 1, 4), 36, -6 + 18 + 16 + changeY));
        input1.add(new PositionedStack(new ItemStack(DionaItems.diona_item, 1, 4), 36, -6 + 36 + 16 + changeY));
        input1.add(new PositionedStack(new ItemStack(DionaItems.diona_item, 1, 4), 36, -6 + 54 + 16 + changeY));
        input1.add(new PositionedStack(new ItemStack(DionaItems.diona_item, 1, 4), 36, -6 + 72 + 16 + changeY));
        input1.add(new PositionedStack(new ItemStack(DionaItems.diona_item, 1, 4), 54, -6 + 16 + changeY));
        input1.add(new PositionedStack(new ItemStack(DionaItems.diona_item, 1, 4), 54, -6 + 18 + 16 + changeY));
        input1.add(new PositionedStack(new ItemStack(DionaItems.diona_item, 1, 4), 54, -6 + 36 + 16 + changeY));
        input1.add(new PositionedStack(new ItemStack(DionaItems.diona_item, 1, 4), 54, -6 + 54 + 16 + changeY));
        input1.add(new PositionedStack(new ItemStack(DionaItems.diona_item, 1, 4), 54, -6 + 72 + 16 + changeY));
        input1.add(new PositionedStack(new ItemStack(DionaItems.tier4_rocket_module, 1, 5), 45, 100 + changeY));
        input1.add(new PositionedStack(new ItemStack(DionaItems.tier4_rocket_module, 1, 6), 18, 64 + changeY));
        input1.add(new PositionedStack(new ItemStack(DionaItems.tier4_rocket_module, 1, 6), 72, 64 + changeY));
        input1.add(new PositionedStack(new ItemStack(AsteroidsItems.basicItem, 1, 2), 18, 82 + changeY));
        input1.add(new PositionedStack(new ItemStack(AsteroidsItems.basicItem, 1, 2), 18, 100 + changeY));
        input1.add(new PositionedStack(new ItemStack(AsteroidsItems.basicItem, 1, 2), 72, 82 + changeY));
        input1.add(new PositionedStack(new ItemStack(AsteroidsItems.basicItem, 1, 2), 72, 100 + changeY));
        this.registerRocketBenchRecipe(input1, new PositionedStack(new ItemStack(PolongniusItems.tier5_rocket, 1, 0), 139, 87 + changeY));

        ArrayList<PositionedStack> input2 = new ArrayList<PositionedStack>(input1);
        input2.add(new PositionedStack(new ItemStack(Blocks.chest), 90, -15 + changeY));
        this.registerRocketBenchRecipe(input2, new PositionedStack(new ItemStack(PolongniusItems.tier5_rocket, 1, 1), 139, 87 + changeY));

        input2 = new ArrayList<PositionedStack>(input1);
        input2.add(new PositionedStack(new ItemStack(Blocks.chest), 90 + 26, -15 + changeY));
        this.registerRocketBenchRecipe(input2, new PositionedStack(new ItemStack(PolongniusItems.tier5_rocket, 1, 1), 139, 87 + changeY));

        input2 = new ArrayList<PositionedStack>(input1);
        input2.add(new PositionedStack(new ItemStack(Blocks.chest), 90 + 52, -15 + changeY));
        this.registerRocketBenchRecipe(input2, new PositionedStack(new ItemStack(PolongniusItems.tier5_rocket, 1, 1), 139, 87 + changeY));

        input2 = new ArrayList<PositionedStack>(input1);
        input2.add(new PositionedStack(new ItemStack(Blocks.chest), 90, -15 + changeY));
        input2.add(new PositionedStack(new ItemStack(Blocks.chest), 90 + 26, -15 + changeY));
        this.registerRocketBenchRecipe(input2, new PositionedStack(new ItemStack(PolongniusItems.tier5_rocket, 1, 2), 139, 87 + changeY));

        input2 = new ArrayList<PositionedStack>(input1);
        input2.add(new PositionedStack(new ItemStack(Blocks.chest), 90 + 26, -15 + changeY));
        input2.add(new PositionedStack(new ItemStack(Blocks.chest), 90 + 52, -15 + changeY));
        this.registerRocketBenchRecipe(input2, new PositionedStack(new ItemStack(PolongniusItems.tier5_rocket, 1, 2), 139, 87 + changeY));

        input2 = new ArrayList<PositionedStack>(input1);
        input2.add(new PositionedStack(new ItemStack(Blocks.chest), 90, -15 + changeY));
        input2.add(new PositionedStack(new ItemStack(Blocks.chest), 90 + 52, -15 + changeY));
        this.registerRocketBenchRecipe(input2, new PositionedStack(new ItemStack(PolongniusItems.tier5_rocket, 1, 2), 139, 87 + changeY));

        input2 = new ArrayList<PositionedStack>(input1);
        input2.add(new PositionedStack(new ItemStack(Blocks.chest), 90, -15 + changeY));
        input2.add(new PositionedStack(new ItemStack(Blocks.chest), 90 + 26, -15 + changeY));
        input2.add(new PositionedStack(new ItemStack(Blocks.chest), 90 + 52, -15 + changeY));
        this.registerRocketBenchRecipe(input2, new PositionedStack(new ItemStack(PolongniusItems.tier5_rocket, 1, 3), 139, 87 + changeY));
    }

    public void addRocketNoFlagRecipes()
    {
        int changeY = 15;

        ArrayList<PositionedStack> input1 = new ArrayList<PositionedStack>();

        input1.add(new PositionedStack(new ItemStack(DionaItems.tier4_rocket_module, 1, 4), 45, -8 + changeY));
        input1.add(new PositionedStack(new ItemStack(DionaItems.diona_item, 1, 4), 36, -6 + 16 + changeY));
        input1.add(new PositionedStack(new ItemStack(DionaItems.diona_item, 1, 4), 36, -6 + 18 + 16 + changeY));
        input1.add(new PositionedStack(new ItemStack(DionaItems.diona_item, 1, 4), 36, -6 + 36 + 16 + changeY));
        input1.add(new PositionedStack(new ItemStack(DionaItems.diona_item, 1, 4), 36, -6 + 54 + 16 + changeY));
        input1.add(new PositionedStack(new ItemStack(DionaItems.diona_item, 1, 4), 36, -6 + 72 + 16 + changeY));
        input1.add(new PositionedStack(new ItemStack(DionaItems.diona_item, 1, 4), 54, -6 + 16 + changeY));
        input1.add(new PositionedStack(new ItemStack(DionaItems.diona_item, 1, 4), 54, -6 + 18 + 16 + changeY));
        input1.add(new PositionedStack(new ItemStack(DionaItems.diona_item, 1, 4), 54, -6 + 36 + 16 + changeY));
        input1.add(new PositionedStack(new ItemStack(DionaItems.diona_item, 1, 4), 54, -6 + 54 + 16 + changeY));
        input1.add(new PositionedStack(new ItemStack(DionaItems.diona_item, 1, 4), 54, -6 + 72 + 16 + changeY));
        input1.add(new PositionedStack(new ItemStack(DionaItems.tier4_rocket_module, 1, 5), 45, 100 + changeY));
        input1.add(new PositionedStack(new ItemStack(DionaItems.tier4_rocket_module, 1, 6), 18, 64 + changeY));
        input1.add(new PositionedStack(new ItemStack(DionaItems.tier4_rocket_module, 1, 6), 72, 64 + changeY));
        input1.add(new PositionedStack(new ItemStack(AsteroidsItems.basicItem, 1, 2), 18, 82 + changeY));
        input1.add(new PositionedStack(new ItemStack(AsteroidsItems.basicItem, 1, 2), 18, 100 + changeY));
        input1.add(new PositionedStack(new ItemStack(AsteroidsItems.basicItem, 1, 2), 72, 82 + changeY));
        input1.add(new PositionedStack(new ItemStack(AsteroidsItems.basicItem, 1, 2), 72, 100 + changeY));
        this.registerRocketBenchNoFlagRecipe(input1, new PositionedStack(new ItemStack(PolongniusItems.tier5_rocket, 1, 10), 139, 87 + changeY));

        ArrayList<PositionedStack> input2 = new ArrayList<PositionedStack>(input1);
        input2.add(new PositionedStack(new ItemStack(Blocks.chest), 90, -15 + changeY));
        this.registerRocketBenchNoFlagRecipe(input2, new PositionedStack(new ItemStack(PolongniusItems.tier5_rocket, 1, 11), 139, 87 + changeY));

        input2 = new ArrayList<PositionedStack>(input1);
        input2.add(new PositionedStack(new ItemStack(Blocks.chest), 90 + 26, -15 + changeY));
        this.registerRocketBenchNoFlagRecipe(input2, new PositionedStack(new ItemStack(PolongniusItems.tier5_rocket, 1, 11), 139, 87 + changeY));

        input2 = new ArrayList<PositionedStack>(input1);
        input2.add(new PositionedStack(new ItemStack(Blocks.chest), 90 + 52, -15 + changeY));
        this.registerRocketBenchNoFlagRecipe(input2, new PositionedStack(new ItemStack(PolongniusItems.tier5_rocket, 1, 11), 139, 87 + changeY));

        input2 = new ArrayList<PositionedStack>(input1);
        input2.add(new PositionedStack(new ItemStack(Blocks.chest), 90, -15 + changeY));
        input2.add(new PositionedStack(new ItemStack(Blocks.chest), 90 + 26, -15 + changeY));
        this.registerRocketBenchNoFlagRecipe(input2, new PositionedStack(new ItemStack(PolongniusItems.tier5_rocket, 1, 12), 139, 87 + changeY));

        input2 = new ArrayList<PositionedStack>(input1);
        input2.add(new PositionedStack(new ItemStack(Blocks.chest), 90 + 26, -15 + changeY));
        input2.add(new PositionedStack(new ItemStack(Blocks.chest), 90 + 52, -15 + changeY));
        this.registerRocketBenchNoFlagRecipe(input2, new PositionedStack(new ItemStack(PolongniusItems.tier5_rocket, 1, 12), 139, 87 + changeY));

        input2 = new ArrayList<PositionedStack>(input1);
        input2.add(new PositionedStack(new ItemStack(Blocks.chest), 90, -15 + changeY));
        input2.add(new PositionedStack(new ItemStack(Blocks.chest), 90 + 52, -15 + changeY));
        this.registerRocketBenchNoFlagRecipe(input2, new PositionedStack(new ItemStack(PolongniusItems.tier5_rocket, 1, 12), 139, 87 + changeY));

        input2 = new ArrayList<PositionedStack>(input1);
        input2.add(new PositionedStack(new ItemStack(Blocks.chest), 90, -15 + changeY));
        input2.add(new PositionedStack(new ItemStack(Blocks.chest), 90 + 26, -15 + changeY));
        input2.add(new PositionedStack(new ItemStack(Blocks.chest), 90 + 52, -15 + changeY));
        this.registerRocketBenchNoFlagRecipe(input2, new PositionedStack(new ItemStack(PolongniusItems.tier5_rocket, 1, 13), 139, 87 + changeY));
    }
}