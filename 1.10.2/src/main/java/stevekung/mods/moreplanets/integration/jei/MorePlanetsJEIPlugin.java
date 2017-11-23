package stevekung.mods.moreplanets.integration.jei;

import mezz.jei.api.BlankModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import micdoodle8.mods.galacticraft.core.GCBlocks;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.client.gui.GuiRocketCrusher;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.integration.jei.black_hole_storage.BlackHoleStorageRecipeCategory;
import stevekung.mods.moreplanets.integration.jei.black_hole_storage.BlackHoleStorageRecipeHandler;
import stevekung.mods.moreplanets.integration.jei.dark_energy_transform.DarkEnergyTransformRecipeCategory;
import stevekung.mods.moreplanets.integration.jei.dark_energy_transform.DarkEnergyTransformRecipeHandler;
import stevekung.mods.moreplanets.integration.jei.rocket_crusher.RocketCrusherRecipeCategory;
import stevekung.mods.moreplanets.integration.jei.rocket_crusher.RocketCrusherRecipesHandler;
import stevekung.mods.moreplanets.integration.jei.rockett4.Tier4RocketRecipeCategory;
import stevekung.mods.moreplanets.integration.jei.rockett4.Tier4RocketRecipeHandler;
import stevekung.mods.moreplanets.integration.jei.rockett5.Tier5RocketRecipeCategory;
import stevekung.mods.moreplanets.integration.jei.rockett5.Tier5RocketRecipeHandler;
import stevekung.mods.moreplanets.integration.jei.rockett6.Tier6RocketRecipeCategory;
import stevekung.mods.moreplanets.integration.jei.rockett6.Tier6RocketRecipeHandler;
import stevekung.mods.moreplanets.module.planets.chalos.recipe.Tier5RocketRecipes;
import stevekung.mods.moreplanets.module.planets.diona.blocks.DionaBlocks;
import stevekung.mods.moreplanets.module.planets.diona.recipe.Tier4RocketRecipes;
import stevekung.mods.moreplanets.module.planets.nibiru.recipe.Tier6RocketRecipes;
import stevekung.mods.moreplanets.recipe.BlackHoleStorageRecipes;
import stevekung.mods.moreplanets.recipe.DarkEnergyRecipeData;
import stevekung.mods.moreplanets.recipe.RocketCrusherRecipes;

@JEIPlugin
public class MorePlanetsJEIPlugin extends BlankModPlugin
{
    @Override
    public void register(IModRegistry registry)
    {
        JEIRegistryHelper.registry = registry;
        JEIRegistryHelper.guiHelper = registry.getJeiHelpers().getGuiHelper();

        ItemDescription.init();

        registry.addRecipeClickArea(GuiRocketCrusher.class, 80, 30, 52, 25, MPJEIRecipes.ROCKET_CRUSHER);
        JEIRegistryHelper.registerRecipeCategories(new DarkEnergyTransformRecipeCategory());
        JEIRegistryHelper.registerRecipeCategories(new RocketCrusherRecipeCategory());
        JEIRegistryHelper.registerRecipeCategories(new Tier4RocketRecipeCategory());
        JEIRegistryHelper.registerRecipeCategories(new Tier5RocketRecipeCategory());
        JEIRegistryHelper.registerRecipeCategories(new Tier6RocketRecipeCategory());
        JEIRegistryHelper.registerRecipeCategories(new BlackHoleStorageRecipeCategory());
        JEIRegistryHelper.registerRecipeHandlers(new DarkEnergyTransformRecipeHandler());
        JEIRegistryHelper.registerRecipeHandlers(new RocketCrusherRecipesHandler());
        JEIRegistryHelper.registerRecipeHandlers(new Tier4RocketRecipeHandler());
        JEIRegistryHelper.registerRecipeHandlers(new Tier5RocketRecipeHandler());
        JEIRegistryHelper.registerRecipeHandlers(new Tier6RocketRecipeHandler());
        JEIRegistryHelper.registerRecipeHandlers(new BlackHoleStorageRecipeHandler());
        JEIRegistryHelper.registerRecipe(DarkEnergyRecipeData.getRecipeList());
        JEIRegistryHelper.registerRecipe(RocketCrusherRecipes.getRecipeList());
        JEIRegistryHelper.registerRecipe(Tier4RocketRecipes.getRecipesList());
        JEIRegistryHelper.registerRecipe(Tier5RocketRecipes.getRecipesList());
        JEIRegistryHelper.registerRecipe(Tier6RocketRecipes.getRecipesList());
        JEIRegistryHelper.registerRecipe(BlackHoleStorageRecipes.getRecipesList());
        JEIRegistryHelper.registerStackDisplayRecipe(new ItemStack(MPBlocks.ROCKET_CRUSHER), MPJEIRecipes.ROCKET_CRUSHER);
        JEIRegistryHelper.registerStackDisplayRecipe(new ItemStack(DionaBlocks.DARK_ENERGY_CORE), MPJEIRecipes.DARK_ENERGY_TRANSFORM);
        JEIRegistryHelper.registerStackDisplayRecipe(new ItemStack(GCBlocks.nasaWorkbench), MPJEIRecipes.BLACK_HOLE_STORAGE);
        JEIRegistryHelper.registerStackDisplayRecipe(new ItemStack(GCBlocks.nasaWorkbench), MPJEIRecipes.TIER_4_ROCKET);
        JEIRegistryHelper.registerStackDisplayRecipe(new ItemStack(GCBlocks.nasaWorkbench), MPJEIRecipes.TIER_5_ROCKET);
        JEIRegistryHelper.registerStackDisplayRecipe(new ItemStack(GCBlocks.nasaWorkbench), MPJEIRecipes.TIER_6_ROCKET);
    }
}