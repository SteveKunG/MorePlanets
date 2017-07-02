package stevekung.mods.moreplanets.integration.jei;

import mezz.jei.api.BlankModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import micdoodle8.mods.galacticraft.api.recipe.INasaWorkbenchRecipe;
import micdoodle8.mods.galacticraft.core.GCBlocks;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.integration.jei.black_hole_storage.BlackHoleStorageRecipeCategory;
import stevekung.mods.moreplanets.integration.jei.black_hole_storage.BlackHoleStorageRecipeWrapper;
import stevekung.mods.moreplanets.integration.jei.dark_energy_transform.DarkEnergyTransformRecipeCategory;
import stevekung.mods.moreplanets.integration.jei.dark_energy_transform.DarkEnergyTransformRecipeWrapper;
import stevekung.mods.moreplanets.integration.jei.rocket_crusher.RocketCrusherRecipeCategory;
import stevekung.mods.moreplanets.integration.jei.rocket_crusher.RocketCrusherRecipesWrapper;
import stevekung.mods.moreplanets.integration.jei.rockett4.Tier4RocketRecipeCategory;
import stevekung.mods.moreplanets.integration.jei.rockett4.Tier4RocketRecipeWrapper;
import stevekung.mods.moreplanets.integration.jei.rockett5.Tier5RocketRecipeCategory;
import stevekung.mods.moreplanets.integration.jei.rockett5.Tier5RocketRecipeWrapper;
import stevekung.mods.moreplanets.integration.jei.rockett6.Tier6RocketRecipeCategory;
import stevekung.mods.moreplanets.integration.jei.rockett6.Tier6RocketRecipeWrapper;
import stevekung.mods.moreplanets.module.planets.chalos.recipe.Tier5RocketRecipes;
import stevekung.mods.moreplanets.module.planets.diona.blocks.DionaBlocks;
import stevekung.mods.moreplanets.module.planets.diona.recipe.Tier4RocketRecipes;
import stevekung.mods.moreplanets.module.planets.nibiru.recipe.Tier6RocketRecipes;
import stevekung.mods.moreplanets.recipe.BlackHoleStorageRecipes;
import stevekung.mods.moreplanets.recipe.DarkEnergyRecipeData;
import stevekung.mods.moreplanets.recipe.RocketCrusherRecipes;
import stevekung.mods.moreplanets.util.recipes.ShapedRecipesMP;

@JEIPlugin
public class MorePlanetsJEIPlugin extends BlankModPlugin
{
    @Override
    public void register(IModRegistry registry)
    {
        JEIRegistryHelper.registry = registry;
        JEIRegistryHelper.guiHelper = registry.getJeiHelpers().getGuiHelper();

        JEIRegistryHelper.registerRecipeHandlers(DarkEnergyRecipeData.class, recipe -> new DarkEnergyTransformRecipeWrapper(recipe), MPJEIRecipes.DARK_ENERGY_TRANSFORM);
        JEIRegistryHelper.registerRecipeHandlers(ShapedRecipesMP.class, RocketCrusherRecipesWrapper::new, MPJEIRecipes.ROCKET_CRUSHER);
        JEIRegistryHelper.registerRecipeHandlers(INasaWorkbenchRecipe.class, recipe -> new Tier4RocketRecipeWrapper(recipe), MPJEIRecipes.TIER_4_ROCKET);
        JEIRegistryHelper.registerRecipeHandlers(INasaWorkbenchRecipe.class, recipe -> new Tier5RocketRecipeWrapper(recipe), MPJEIRecipes.TIER_5_ROCKET);
        JEIRegistryHelper.registerRecipeHandlers(INasaWorkbenchRecipe.class, recipe -> new Tier6RocketRecipeWrapper(recipe), MPJEIRecipes.TIER_6_ROCKET);
        JEIRegistryHelper.registerRecipeHandlers(INasaWorkbenchRecipe.class, recipe -> new BlackHoleStorageRecipeWrapper(recipe), MPJEIRecipes.BLACK_HOLE_STORAGE);
        JEIRegistryHelper.registerRecipe(DarkEnergyRecipeData.getRecipeList(), MPJEIRecipes.DARK_ENERGY_TRANSFORM);
        JEIRegistryHelper.registerRecipe(RocketCrusherRecipes.getRecipeList(), MPJEIRecipes.ROCKET_CRUSHER);
        JEIRegistryHelper.registerRecipe(Tier4RocketRecipes.getRecipesList(), MPJEIRecipes.TIER_4_ROCKET);
        JEIRegistryHelper.registerRecipe(Tier5RocketRecipes.getRecipesList(), MPJEIRecipes.TIER_5_ROCKET);
        JEIRegistryHelper.registerRecipe(Tier6RocketRecipes.getRecipesList(), MPJEIRecipes.TIER_6_ROCKET);
        JEIRegistryHelper.registerRecipe(BlackHoleStorageRecipes.getRecipesList(), MPJEIRecipes.BLACK_HOLE_STORAGE);
        JEIRegistryHelper.registerStackDisplayRecipe(new ItemStack(MPBlocks.ROCKET_CRUSHER), MPJEIRecipes.ROCKET_CRUSHER);
        JEIRegistryHelper.registerStackDisplayRecipe(new ItemStack(DionaBlocks.DARK_ENERGY_CORE), MPJEIRecipes.DARK_ENERGY_TRANSFORM);
        JEIRegistryHelper.registerStackDisplayRecipe(new ItemStack(MPBlocks.BLACK_HOLE_STORAGE), MPJEIRecipes.BLACK_HOLE_STORAGE);
        JEIRegistryHelper.registerStackDisplayRecipe(new ItemStack(GCBlocks.nasaWorkbench), MPJEIRecipes.TIER_4_ROCKET, MPJEIRecipes.TIER_5_ROCKET, MPJEIRecipes.TIER_6_ROCKET);
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry)
    {
        JEIRegistryHelper.categoryRegistration = registry;
        JEIRegistryHelper.registerRecipeCategories(new DarkEnergyTransformRecipeCategory());
        JEIRegistryHelper.registerRecipeCategories(new RocketCrusherRecipeCategory());
        JEIRegistryHelper.registerRecipeCategories(new Tier4RocketRecipeCategory());
        JEIRegistryHelper.registerRecipeCategories(new Tier5RocketRecipeCategory());
        JEIRegistryHelper.registerRecipeCategories(new Tier6RocketRecipeCategory());
        JEIRegistryHelper.registerRecipeCategories(new BlackHoleStorageRecipeCategory());
    }
}