package stevekung.mods.moreplanets.integration.jei.black_hole_storage;

import java.util.Collections;
import java.util.List;

import javax.annotation.Nonnull;

import com.google.common.collect.Lists;

import mezz.jei.api.recipe.BlankRecipeWrapper;
import mezz.jei.api.recipe.wrapper.ICraftingRecipeWrapper;
import micdoodle8.mods.galacticraft.api.recipe.INasaWorkbenchRecipe;
import net.minecraft.item.ItemStack;

public class BlackHoleStorageRecipeWrapper extends BlankRecipeWrapper implements ICraftingRecipeWrapper
{
    @Nonnull
    private INasaWorkbenchRecipe recipe;

    public BlackHoleStorageRecipeWrapper(@Nonnull INasaWorkbenchRecipe recipe)
    {
        this.recipe = recipe;
    }

    @Nonnull
    @Override
    public List getInputs()
    {
        List<ItemStack> list = Lists.newArrayList();
        list.addAll(this.recipe.getRecipeInput().values());
        return list;
    }

    @Nonnull
    @Override
    public List<ItemStack> getOutputs()
    {
        return Collections.singletonList(this.recipe.getRecipeOutput());
    }
}