package stevekung.mods.moreplanets.integration.jei;

import javax.annotation.Nonnull;

import mezz.jei.api.BlankModPlugin;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeHandler;
import stevekung.mods.moreplanets.integration.jei.black_hole_storage.BlackHoleStorageRecipeCategory;
import stevekung.mods.moreplanets.integration.jei.black_hole_storage.BlackHoleStorageRecipeHandler;
import stevekung.mods.moreplanets.integration.jei.black_hole_storage.BlackHoleStorageRecipeMaker;
import stevekung.mods.moreplanets.integration.jei.dark_energy.DarkEnergyTransformRecipeCategory;
import stevekung.mods.moreplanets.integration.jei.dark_energy.DarkEnergyTransformRecipeHandler;
import stevekung.mods.moreplanets.integration.jei.rocket_crusher.RocketCrusherRecipeCategory;
import stevekung.mods.moreplanets.integration.jei.rocket_crusher.RocketCrusherRecipesHandler;
import stevekung.mods.moreplanets.integration.jei.rockett4.Tier4RocketRecipeCategory;
import stevekung.mods.moreplanets.integration.jei.rockett4.Tier4RocketRecipeHandler;
import stevekung.mods.moreplanets.integration.jei.rockett4.Tier4RocketRecipeMaker;
import stevekung.mods.moreplanets.integration.jei.rockett5.Tier5RocketRecipeCategory;
import stevekung.mods.moreplanets.integration.jei.rockett5.Tier5RocketRecipeHandler;
import stevekung.mods.moreplanets.integration.jei.rockett5.Tier5RocketRecipeMaker;
import stevekung.mods.moreplanets.integration.jei.rockett6.Tier6RocketRecipeCategory;
import stevekung.mods.moreplanets.integration.jei.rockett6.Tier6RocketRecipeHandler;
import stevekung.mods.moreplanets.integration.jei.rockett6.Tier6RocketRecipeMaker;
import stevekung.mods.moreplanets.recipe.DarkEnergyRecipeData;
import stevekung.mods.moreplanets.recipe.RocketCrusherRecipes;

@JEIPlugin
public class MorePlanetsJEIPlugin extends BlankModPlugin
{
    @Override
    public void register(@Nonnull IModRegistry registry)
    {
        IGuiHelper helper = registry.getJeiHelpers().getGuiHelper();
        this.registerRecipeCategories(registry, new DarkEnergyTransformRecipeCategory(helper));
        this.registerRecipeCategories(registry, new RocketCrusherRecipeCategory(helper));
        this.registerRecipeCategories(registry, new Tier4RocketRecipeCategory(helper));
        this.registerRecipeCategories(registry, new Tier5RocketRecipeCategory(helper));
        this.registerRecipeCategories(registry, new Tier6RocketRecipeCategory(helper));
        this.registerRecipeCategories(registry, new BlackHoleStorageRecipeCategory(helper));
        this.registerRecipeHandlers(registry, new DarkEnergyTransformRecipeHandler());
        this.registerRecipeHandlers(registry, new RocketCrusherRecipesHandler());
        this.registerRecipeHandlers(registry, new Tier4RocketRecipeHandler());
        this.registerRecipeHandlers(registry, new Tier5RocketRecipeHandler());
        this.registerRecipeHandlers(registry, new Tier6RocketRecipeHandler());
        this.registerRecipeHandlers(registry, new BlackHoleStorageRecipeHandler());
        registry.addRecipes(DarkEnergyRecipeData.darkEnergyTransformRecipes);
        registry.addRecipes(RocketCrusherRecipes.getRecipeList());
        registry.addRecipes(Tier4RocketRecipeMaker.getRecipesList());
        registry.addRecipes(Tier5RocketRecipeMaker.getRecipesList());
        registry.addRecipes(Tier6RocketRecipeMaker.getRecipesList());
        registry.addRecipes(BlackHoleStorageRecipeMaker.getRecipesList());
    }

    private void registerRecipeCategories(IModRegistry registry, IRecipeCategory recipe)
    {
        registry.addRecipeCategories(recipe);
    }

    private void registerRecipeHandlers(IModRegistry registry, IRecipeHandler handler)
    {
        registry.addRecipeHandlers(handler);
    }
}