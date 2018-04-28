package stevekung.mods.moreplanets.integration.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;
import micdoodle8.mods.galacticraft.api.recipe.INasaWorkbenchRecipe;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.client.gui.GuiRocketCrusher;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.integration.jei.black_hole_storage.BlackHoleStorageRecipeCategory;
import stevekung.mods.moreplanets.integration.jei.black_hole_storage.BlackHoleStorageRecipeWrapper;
import stevekung.mods.moreplanets.integration.jei.dark_energy_transform.DarkEnergyTransformRecipeCategory;
import stevekung.mods.moreplanets.integration.jei.dark_energy_transform.DarkEnergyTransformRecipeWrapper;
import stevekung.mods.moreplanets.integration.jei.rocket_crusher.RocketCrusherRecipeCategory;
import stevekung.mods.moreplanets.integration.jei.rocket_crusher.RocketCrusherRecipesWrapper;
import stevekung.mods.moreplanets.module.planets.chalos.blocks.ChalosBlocks;
import stevekung.mods.moreplanets.module.planets.diona.blocks.DionaBlocks;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.recipe.BlackHoleStorageRecipes;
import stevekung.mods.moreplanets.recipe.DarkEnergyRecipeData;
import stevekung.mods.moreplanets.recipe.RocketCrusherRecipes;
import stevekung.mods.moreplanets.util.recipes.ShapedRecipesMP;

@JEIPlugin
public class MorePlanetsJEIPlugin implements IModPlugin
{
    @Override
    public void register(IModRegistry registry)
    {
        JEIRegistryHelper.registry = registry;
        JEIRegistryHelper.guiHelper = registry.getJeiHelpers().getGuiHelper();

        ItemDescription.init();

        registry.addRecipeClickArea(GuiRocketCrusher.class, 80, 30, 52, 25, MPJEIRecipes.ROCKET_CRUSHER);
        JEIRegistryHelper.registerRecipeHandlers(DarkEnergyRecipeData.class, DarkEnergyTransformRecipeWrapper::new, MPJEIRecipes.DARK_ENERGY_TRANSFORM);
        JEIRegistryHelper.registerRecipeHandlers(ShapedRecipesMP.class, RocketCrusherRecipesWrapper::new, MPJEIRecipes.ROCKET_CRUSHER);
        JEIRegistryHelper.registerRecipeHandlers(INasaWorkbenchRecipe.class, BlackHoleStorageRecipeWrapper::new, MPJEIRecipes.BLACK_HOLE_STORAGE);
        JEIRegistryHelper.registerRecipe(DarkEnergyRecipeData.getRecipeList(), MPJEIRecipes.DARK_ENERGY_TRANSFORM);
        JEIRegistryHelper.registerRecipe(RocketCrusherRecipes.getRecipeList(), MPJEIRecipes.ROCKET_CRUSHER);
        JEIRegistryHelper.registerRecipe(BlackHoleStorageRecipes.getRecipesList(), MPJEIRecipes.BLACK_HOLE_STORAGE);
        JEIRegistryHelper.registerStackDisplayRecipe(new ItemStack(MPBlocks.ROCKET_CRUSHER), MPJEIRecipes.ROCKET_CRUSHER);
        JEIRegistryHelper.registerStackDisplayRecipe(new ItemStack(DionaBlocks.DARK_ENERGY_CORE), MPJEIRecipes.DARK_ENERGY_TRANSFORM);
        JEIRegistryHelper.registerStackDisplayRecipe(new ItemStack(MPBlocks.BLACK_HOLE_STORAGE), MPJEIRecipes.BLACK_HOLE_STORAGE);
        JEIRegistryHelper.registerStackDisplayRecipe(new ItemStack(ChalosBlocks.CHEESE_SPORE_CRAFTING_TABLE), VanillaRecipeCategoryUid.CRAFTING);
        JEIRegistryHelper.registerStackDisplayRecipe(new ItemStack(NibiruBlocks.NIBIRU_CRAFTING_TABLE), VanillaRecipeCategoryUid.CRAFTING);
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry)
    {
        JEIRegistryHelper.categoryRegistration = registry;
        JEIRegistryHelper.guiHelper = registry.getJeiHelpers().getGuiHelper();

        JEIRegistryHelper.registerRecipeCategories(new DarkEnergyTransformRecipeCategory());
        JEIRegistryHelper.registerRecipeCategories(new RocketCrusherRecipeCategory());
        JEIRegistryHelper.registerRecipeCategories(new BlackHoleStorageRecipeCategory());
    }
}