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
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.integration.jei.black_hole_storage.BlackHoleStorageRecipeCategory;
import stevekung.mods.moreplanets.integration.jei.black_hole_storage.BlackHoleStorageRecipeWrapper;
import stevekung.mods.moreplanets.integration.jei.dark_energy_transform.DarkEnergyTransformRecipeCategory;
import stevekung.mods.moreplanets.integration.jei.dark_energy_transform.DarkEnergyTransformRecipeWrapper;
import stevekung.mods.moreplanets.planets.chalos.blocks.ChalosBlocks;
import stevekung.mods.moreplanets.planets.diona.blocks.DionaBlocks;
import stevekung.mods.moreplanets.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.recipe.BlackHoleStorageRecipes;
import stevekung.mods.moreplanets.recipe.DarkEnergyRecipeData;

@JEIPlugin
public class MorePlanetsJEIPlugin implements IModPlugin
{
    public static final Map<ItemStack, Item> ILLENIUM = new HashMap<>();
    public static final Map<ItemStack, Item> DIREMSIUM = new HashMap<>();
    public static final Map<ItemStack, Item> CHEESE_SPORE = new HashMap<>();
    public static final Map<ItemStack, Item> MULTALIC_CRYSTAL = new HashMap<>();
    public static final Map<ItemStack, Item> INFECTED_WOOD = new HashMap<>();
    public static final Map<ItemStack, Item> NIBIRU_STONE = new HashMap<>();

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
        JEIRegistryHelper.registerStackDisplayRecipe(new ItemStack(DionaBlocks.DARK_ENERGY_CORE), MPJEIRecipes.DARK_ENERGY_TRANSFORM);
        JEIRegistryHelper.registerStackDisplayRecipe(new ItemStack(MPBlocks.BLACK_HOLE_STORAGE), MPJEIRecipes.BLACK_HOLE_STORAGE);
        JEIRegistryHelper.registerStackDisplayRecipe(new ItemStack(ChalosBlocks.CHEESE_SPORE_CRAFTING_TABLE), VanillaRecipeCategoryUid.CRAFTING);
        JEIRegistryHelper.registerStackDisplayRecipe(new ItemStack(NibiruBlocks.INFECTED_CRAFTING_TABLE), VanillaRecipeCategoryUid.CRAFTING);
        JEIRegistryHelper.registerStackDisplayRecipe(new ItemStack(NibiruBlocks.ALIEN_BERRY_CRAFTING_TABLE), VanillaRecipeCategoryUid.CRAFTING);

        Item item = MorePlanetsJEIPlugin.ILLENIUM.values().iterator().next();
        List<ItemStack> list = new ArrayList<>(MorePlanetsJEIPlugin.ILLENIUM.keySet());
        JEIRegistryHelper.registerAnvilRecipe(item, list);

        item = MorePlanetsJEIPlugin.DIREMSIUM.values().iterator().next();
        list = new ArrayList<>(MorePlanetsJEIPlugin.DIREMSIUM.keySet());
        JEIRegistryHelper.registerAnvilRecipe(item, list);

        item = MorePlanetsJEIPlugin.CHEESE_SPORE.values().iterator().next();
        list = new ArrayList<>(MorePlanetsJEIPlugin.CHEESE_SPORE.keySet());
        JEIRegistryHelper.registerAnvilRecipe(item, list);

        item = MorePlanetsJEIPlugin.MULTALIC_CRYSTAL.values().iterator().next();
        list = new ArrayList<>(MorePlanetsJEIPlugin.MULTALIC_CRYSTAL.keySet());
        JEIRegistryHelper.registerAnvilRecipe(item, list);

        item = MorePlanetsJEIPlugin.INFECTED_WOOD.values().iterator().next();
        list = new ArrayList<>(MorePlanetsJEIPlugin.INFECTED_WOOD.keySet());
        JEIRegistryHelper.registerAnvilRecipe(item, list);

        item = MorePlanetsJEIPlugin.NIBIRU_STONE.values().iterator().next();
        list = new ArrayList<>(MorePlanetsJEIPlugin.NIBIRU_STONE.keySet());
        JEIRegistryHelper.registerAnvilRecipe(item, list);
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry)
    {
        JEIRegistryHelper.categoryRegistration = registry;
        JEIRegistryHelper.guiHelper = registry.getJeiHelpers().getGuiHelper();

        JEIRegistryHelper.registerRecipeCategories(new DarkEnergyTransformRecipeCategory());
        JEIRegistryHelper.registerRecipeCategories(new BlackHoleStorageRecipeCategory());
    }

    public static void collectAnvilList(String name, Item toAdd, Item toRepair)
    {
        if (name.contains("illenium"))
        {
            MorePlanetsJEIPlugin.ILLENIUM.put(new ItemStack(toAdd), toRepair);
        }
        if (name.contains("diremsium"))
        {
            MorePlanetsJEIPlugin.DIREMSIUM.put(new ItemStack(toAdd), toRepair);
        }
        if (name.contains("cheese_spore"))
        {
            MorePlanetsJEIPlugin.CHEESE_SPORE.put(new ItemStack(toAdd), toRepair);
        }
        if (name.contains("multalic_crystal"))
        {
            MorePlanetsJEIPlugin.MULTALIC_CRYSTAL.put(new ItemStack(toAdd), toRepair);
        }
        if (name.contains("infected_wood"))
        {
            MorePlanetsJEIPlugin.INFECTED_WOOD.put(new ItemStack(toAdd), toRepair);
        }
        if (name.contains("nibiru_stone"))
        {
            MorePlanetsJEIPlugin.NIBIRU_STONE.put(new ItemStack(toAdd), toRepair);
        }
    }
}