/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * Credit to Galacticraft
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.nei;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import codechicken.nei.PositionedStack;
import micdoodle8.mods.galacticraft.core.nei.CircuitFabricatorRecipeHandler;

public class CircuitFabricatorRecipeHandlerMP extends CircuitFabricatorRecipeHandler
{
    @Override
    public Set<Entry<ArrayList<PositionedStack>, PositionedStack>> getRecipes()
    {
        HashMap<ArrayList<PositionedStack>, PositionedStack> recipes = new HashMap<ArrayList<PositionedStack>, PositionedStack>();

        for (Entry<HashMap<Integer, PositionedStack>, PositionedStack> stack : NEIMorePlanetsConfig.getCircuitFabricatorRecipes())
        {
            ArrayList<PositionedStack> inputStacks = new ArrayList<PositionedStack>();

            for (Map.Entry<Integer, PositionedStack> input : stack.getKey().entrySet())
            {
                inputStacks.add(input.getValue());
            }
            recipes.put(inputStacks, stack.getValue());
        }
        return recipes.entrySet();
    }
}