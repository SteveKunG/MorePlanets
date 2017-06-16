/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.nei;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import codechicken.nei.PositionedStack;
import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import micdoodle8.mods.galacticraft.core.items.GCItems;
import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
import stevekung.mods.moreplanets.core.init.MPBlocks;
import stevekung.mods.moreplanets.core.init.MPItems;
import stevekung.mods.moreplanets.core.util.MPLog;
import stevekung.mods.moreplanets.planets.polongnius.items.PolongniusItems;

public class NEIMorePlanetsConfig implements IConfigureNEI
{
    private static HashMap<HashMap<Integer, PositionedStack>, PositionedStack> circuitFabricatorRecipes = new HashMap<HashMap<Integer, PositionedStack>, PositionedStack>();

    @Override
    public void loadConfig()
    {
        API.registerRecipeHandler(new CircuitFabricatorRecipeHandlerMP());
        API.registerUsageHandler(new CircuitFabricatorRecipeHandlerMP());

        for (Block block : MPBlocks.highlightBlockList)
        {
            API.registerHighlightIdentifier(block, new NEIHighlightHandlerMP());
            MPLog.debug("Register NEI Highlight %s [%s]", block.getClass().getSimpleName(), block.getUnlocalizedName().substring(5));
        }
        this.registerRecipe();
    }

    @Override
    public String getName()
    {
        return "More Planets NEI Plugin";
    }

    @Override
    public String getVersion()
    {
        return MorePlanetsCore.VERSION;
    }

    public void registerCircuitFabricatorRecipe(HashMap<Integer, PositionedStack> input, PositionedStack output)
    {
        NEIMorePlanetsConfig.circuitFabricatorRecipes.put(input, output);
    }

    public static Set<Entry<HashMap<Integer, PositionedStack>, PositionedStack>> getCircuitFabricatorRecipes()
    {
        return NEIMorePlanetsConfig.circuitFabricatorRecipes.entrySet();
    }

    public void registerRecipe()
    {
        this.addPurpleCrystalWaferRecipes();
        this.addPurpleCrystalSolarWaferRecipes();
        this.registerHideBlocks();
        this.registerHideItems();
    }

    private void addPurpleCrystalWaferRecipes()
    {
        HashMap<Integer, PositionedStack> input1 = new HashMap<Integer, PositionedStack>();
        int siliconCount = OreDictionary.getOres(ConfigManagerCore.otherModsSilicon).size();
        ItemStack[] silicons = new ItemStack[siliconCount + 1];
        silicons[0] = new ItemStack(GCItems.basicItem, 1, 2);

        for (int j = 0; j < siliconCount; j++)
        {
            silicons[j + 1] = OreDictionary.getOres("itemSilicon").get(j);
        }

        input1.put(0, new PositionedStack(new ItemStack(PolongniusItems.polongnius_item, 1, 1), 10, 22));
        input1.put(1, new PositionedStack(silicons, 69, 51));
        input1.put(2, new PositionedStack(silicons, 69, 69));
        input1.put(3, new PositionedStack(new ItemStack(Items.redstone), 117, 51));
        input1.put(4, new PositionedStack(new ItemStack(Items.repeater), 140, 25));
        this.registerCircuitFabricatorRecipe(input1, new PositionedStack(new ItemStack(PolongniusItems.purple_crystal_solar_module, ConfigManagerCore.quickMode ? 2 : 1, 0), 147, 91));
    }

    private void addPurpleCrystalSolarWaferRecipes()
    {
        HashMap<Integer, PositionedStack> input1 = new HashMap<Integer, PositionedStack>();
        int siliconCount = OreDictionary.getOres(ConfigManagerCore.otherModsSilicon).size();
        ItemStack[] silicons = new ItemStack[siliconCount + 1];
        silicons[0] = new ItemStack(GCItems.basicItem, 1, 2);

        for (int j = 0; j < siliconCount; j++)
        {
            silicons[j + 1] = OreDictionary.getOres("itemSilicon").get(j);
        }

        input1.put(0, new PositionedStack(new ItemStack(Items.diamond), 10, 22));
        input1.put(1, new PositionedStack(silicons, 69, 51));
        input1.put(2, new PositionedStack(silicons, 69, 69));
        input1.put(3, new PositionedStack(new ItemStack(Items.redstone), 117, 51));
        input1.put(4, new PositionedStack(new ItemStack(PolongniusItems.polongnius_item, 1, 1), 140, 25));
        this.registerCircuitFabricatorRecipe(input1, new PositionedStack(new ItemStack(PolongniusItems.purple_crystal_solar_module, 9, 1), 147, 91));
    }

    private void registerHideBlocks()
    {
        for (Block block : MPBlocks.hideBlockList)
        {
            API.hideItem(new ItemStack(block, 1, OreDictionary.WILDCARD_VALUE));
            MPLog.debug("Register NEI Hide Block %s [%s]", block.getClass().getSimpleName(), block.getUnlocalizedName().substring(5));
        }
    }

    private void registerHideItems()
    {
        if (ConfigManagerMP.enableThaiFlagAndCanvas == false)
        {
            API.hideItem(new ItemStack(MPItems.flag, 1, OreDictionary.WILDCARD_VALUE));
            API.hideItem(new ItemStack(MPItems.achievement_temp, 1, OreDictionary.WILDCARD_VALUE));
        }
    }
}