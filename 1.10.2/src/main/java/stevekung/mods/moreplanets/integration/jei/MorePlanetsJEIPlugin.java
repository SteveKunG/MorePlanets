package stevekung.mods.moreplanets.integration.jei;

import javax.annotation.Nonnull;

import mezz.jei.api.BlankModPlugin;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeHandler;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.integration.jei.black_hole_storage.BlackHoleStorageRecipeCategory;
import stevekung.mods.moreplanets.integration.jei.black_hole_storage.BlackHoleStorageRecipeHandler;
import stevekung.mods.moreplanets.integration.jei.black_hole_storage.BlackHoleStorageRecipeMaker;
import stevekung.mods.moreplanets.integration.jei.dark_energy_transform.DarkEnergyTransformRecipeCategory;
import stevekung.mods.moreplanets.integration.jei.dark_energy_transform.DarkEnergyTransformRecipeHandler;
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
import stevekung.mods.moreplanets.module.planets.diona.blocks.DionaBlocks;
import stevekung.mods.moreplanets.recipe.DarkEnergyRecipeData;
import stevekung.mods.moreplanets.recipe.RocketCrusherRecipes;

@JEIPlugin
public class MorePlanetsJEIPlugin extends BlankModPlugin
{
    private static IModRegistry registry;

    @Override
    public void register(@Nonnull IModRegistry registry)
    {
        MorePlanetsJEIPlugin.registry = registry;
        IGuiHelper helper = registry.getJeiHelpers().getGuiHelper();

        this.registerRecipeCategories(new DarkEnergyTransformRecipeCategory(helper));
        this.registerRecipeCategories(new RocketCrusherRecipeCategory(helper));
        this.registerRecipeCategories(new Tier4RocketRecipeCategory(helper));
        this.registerRecipeCategories(new Tier5RocketRecipeCategory(helper));
        this.registerRecipeCategories(new Tier6RocketRecipeCategory(helper));
        this.registerRecipeCategories(new BlackHoleStorageRecipeCategory(helper));
        this.registerRecipeHandlers(new DarkEnergyTransformRecipeHandler());
        this.registerRecipeHandlers(new RocketCrusherRecipesHandler());
        this.registerRecipeHandlers(new Tier4RocketRecipeHandler());
        this.registerRecipeHandlers(new Tier5RocketRecipeHandler());
        this.registerRecipeHandlers(new Tier6RocketRecipeHandler());
        this.registerRecipeHandlers(new BlackHoleStorageRecipeHandler());
        registry.addRecipes(DarkEnergyRecipeData.getRecipeList());
        registry.addRecipes(RocketCrusherRecipes.getRecipeList());
        registry.addRecipes(Tier4RocketRecipeMaker.getRecipesList());
        registry.addRecipes(Tier5RocketRecipeMaker.getRecipesList());
        registry.addRecipes(Tier6RocketRecipeMaker.getRecipesList());
        registry.addRecipes(BlackHoleStorageRecipeMaker.getRecipesList());
        this.registerStackDisplayRecipe(new ItemStack(MPBlocks.ROCKET_CRUSHER), MPJEIRecipes.ROCKET_CRUSHER);
        this.registerStackDisplayRecipe(new ItemStack(DionaBlocks.DARK_ENERGY_CORE), MPJEIRecipes.DARK_ENERGY_TRANSFORM);
    }

    private void registerRecipeCategories(IRecipeCategory recipe)
    {
        MorePlanetsJEIPlugin.registry.addRecipeCategories(recipe);
    }

    private void registerRecipeHandlers(IRecipeHandler handler)
    {
        MorePlanetsJEIPlugin.registry.addRecipeHandlers(handler);
    }

    private void registerStackDisplayRecipe(ItemStack itemStack, String recipe)
    {
        MorePlanetsJEIPlugin.registry.addRecipeCategoryCraftingItem(itemStack, recipe);
    }
}