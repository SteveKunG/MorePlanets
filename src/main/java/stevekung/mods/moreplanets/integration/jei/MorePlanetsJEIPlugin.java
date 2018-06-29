package stevekung.mods.moreplanets.integration.jei;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;
import micdoodle8.mods.galacticraft.api.recipe.INasaWorkbenchRecipe;
import micdoodle8.mods.galacticraft.core.GCBlocks;
import micdoodle8.mods.galacticraft.planets.mars.items.MarsItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.init.MPItems;
import stevekung.mods.moreplanets.integration.jei.black_hole_storage.BlackHoleStorageRecipeCategory;
import stevekung.mods.moreplanets.integration.jei.black_hole_storage.BlackHoleStorageRecipeWrapper;
import stevekung.mods.moreplanets.integration.jei.dark_energy_transform.DarkEnergyTransformRecipeCategory;
import stevekung.mods.moreplanets.integration.jei.dark_energy_transform.DarkEnergyTransformRecipeWrapper;
import stevekung.mods.moreplanets.recipe.BlackHoleStorageRecipes;
import stevekung.mods.moreplanets.recipe.DarkEnergyRecipeData;

@JEIPlugin
public class MorePlanetsJEIPlugin implements IModPlugin
{
    @Override
    public void register(IModRegistry registry)
    {
        JEIRegistryHelper.registry = registry;
        JEIRegistryHelper.guiHelper = registry.getJeiHelpers().getGuiHelper();
        JEIRegistryHelper.vanillaRecipe = registry.getJeiHelpers().getVanillaRecipeFactory();

        ItemDescription.init();

        JEIRegistryHelper.registerRecipeHandlers(DarkEnergyRecipeData.class, DarkEnergyTransformRecipeWrapper::new, MPJEIRecipes.DARK_ENERGY_TRANSFORM);
        JEIRegistryHelper.registerRecipeHandlers(INasaWorkbenchRecipe.class, BlackHoleStorageRecipeWrapper::new, MPJEIRecipes.BLACK_HOLE_STORAGE);
        JEIRegistryHelper.registerRecipe(DarkEnergyRecipeData.getRecipeList(), MPJEIRecipes.DARK_ENERGY_TRANSFORM);
        JEIRegistryHelper.registerRecipe(BlackHoleStorageRecipes.getRecipesList(), MPJEIRecipes.BLACK_HOLE_STORAGE);
        JEIRegistryHelper.registerStackDisplayRecipe(new ItemStack(MPBlocks.DARK_ENERGY_CORE), MPJEIRecipes.DARK_ENERGY_TRANSFORM);
        JEIRegistryHelper.registerStackDisplayRecipe(new ItemStack(GCBlocks.nasaWorkbench), MPJEIRecipes.BLACK_HOLE_STORAGE);
        JEIRegistryHelper.registerStackDisplayRecipe(new ItemStack(MPBlocks.CHEESE_SPORE_CRAFTING_TABLE), VanillaRecipeCategoryUid.CRAFTING);
        JEIRegistryHelper.registerStackDisplayRecipe(new ItemStack(MPBlocks.INFECTED_CRAFTING_TABLE), VanillaRecipeCategoryUid.CRAFTING);
        JEIRegistryHelper.registerStackDisplayRecipe(new ItemStack(MPBlocks.ALIEN_BERRY_CRAFTING_TABLE), VanillaRecipeCategoryUid.CRAFTING);
        JEIRegistryHelper.registerStackDisplayRecipe(new ItemStack(MPBlocks.INFECTED_FURNACE), VanillaRecipeCategoryUid.SMELTING);
        JEIRegistryHelper.registerStackDisplayRecipe(new ItemStack(MPBlocks.TERRASTONE_FURNACE), VanillaRecipeCategoryUid.SMELTING);
        JEIRegistryHelper.registerStackDisplayRecipe(new ItemStack(MPBlocks.INFECTED_FURNACE), VanillaRecipeCategoryUid.FUEL);
        JEIRegistryHelper.registerStackDisplayRecipe(new ItemStack(MPBlocks.TERRASTONE_FURNACE), VanillaRecipeCategoryUid.FUEL);

        Item item = MPJEIRecipes.ILLENIUM.values().iterator().next();
        List<ItemStack> list = new ArrayList<>(MPJEIRecipes.ILLENIUM.keySet());
        JEIRegistryHelper.registerAnvilRecipe(item, list);

        item = MPJEIRecipes.DIREMSIUM.values().iterator().next();
        list = new ArrayList<>(MPJEIRecipes.DIREMSIUM.keySet());
        JEIRegistryHelper.registerAnvilRecipe(item, list);

        item = MPJEIRecipes.CHEESE_SPORE.values().iterator().next();
        list = new ArrayList<>(MPJEIRecipes.CHEESE_SPORE.keySet());
        JEIRegistryHelper.registerAnvilRecipe(item, list);

        item = MPJEIRecipes.MULTALIC_CRYSTAL.values().iterator().next();
        list = new ArrayList<>(MPJEIRecipes.MULTALIC_CRYSTAL.keySet());
        JEIRegistryHelper.registerAnvilRecipe(item, list);

        item = MPJEIRecipes.INFECTED_WOOD.values().iterator().next();
        list = new ArrayList<>(MPJEIRecipes.INFECTED_WOOD.keySet());
        JEIRegistryHelper.registerAnvilRecipe(item, list);

        item = MPJEIRecipes.NIBIRU_STONE.values().iterator().next();
        list = new ArrayList<>(MPJEIRecipes.NIBIRU_STONE.keySet());
        JEIRegistryHelper.registerAnvilRecipe(item, list);

        Map<ItemStack, ItemStack> spaceFishingRod = new HashMap<>();
        spaceFishingRod.put(new ItemStack(MPItems.SPACE_FISHING_ROD), new ItemStack(MarsItems.marsItemBasic, 1, 5));
        ItemStack stack = spaceFishingRod.values().iterator().next();
        list = new ArrayList<>(spaceFishingRod.keySet());
        JEIRegistryHelper.registerAnvilRecipe(stack, list);

        Map<ItemStack, ItemStack> spaceBow = new HashMap<>();
        spaceBow.put(new ItemStack(MPItems.SPACE_BOW), new ItemStack(MarsItems.marsItemBasic, 1, 5));
        stack = spaceBow.values().iterator().next();
        list = new ArrayList<>(spaceBow.keySet());
        JEIRegistryHelper.registerAnvilRecipe(stack, list);
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry)
    {
        JEIRegistryHelper.categoryRegistration = registry;
        JEIRegistryHelper.guiHelper = registry.getJeiHelpers().getGuiHelper();

        JEIRegistryHelper.registerRecipeCategories(new DarkEnergyTransformRecipeCategory());
        JEIRegistryHelper.registerRecipeCategories(new BlackHoleStorageRecipeCategory());
    }
}