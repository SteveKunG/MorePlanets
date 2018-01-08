package stevekung.mods.moreplanets.util.recipes;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.common.crafting.IShapedRecipe;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class ShapedRecipesMP extends IForgeRegistryEntry.Impl<IRecipe> implements IShapedRecipe
{
    public int recipeWidth;
    public int recipeHeight;
    public ItemStack[] recipeItems;
    private ItemStack recipeOutput;

    public ShapedRecipesMP(int width, int height, ItemStack[] input, ItemStack output)
    {
        this.recipeWidth = width;
        this.recipeHeight = height;
        this.recipeItems = input;
        this.recipeOutput = output;
    }

    @Override
    public ItemStack getRecipeOutput()
    {
        return this.recipeOutput;
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv)
    {
        NonNullList<ItemStack> aitemstack = NonNullList.withSize(inv.getSizeInventory(), ItemStack.EMPTY);

        for (int i = 0; i < aitemstack.size(); ++i)
        {
            ItemStack itemstack = inv.getStackInSlot(i);
            aitemstack.set(i, net.minecraftforge.common.ForgeHooks.getContainerItem(itemstack));
        }
        return aitemstack;
    }

    @Override
    public boolean matches(InventoryCrafting inv, World worldIn)
    {
        for (int i = 0; i <= 3 - this.recipeWidth; ++i)
        {
            for (int j = 0; j <= 3 - this.recipeHeight; ++j)
            {
                if (this.checkMatch(inv, i, j, true))
                {
                    return true;
                }
                if (this.checkMatch(inv, i, j, false))
                {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkMatch(InventoryCrafting inventory, int width, int height, boolean invert)
    {
        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 3; ++j)
            {
                int k = i - width;
                int l = j - height;
                ItemStack itemstack = ItemStack.EMPTY;

                if (k >= 0 && l >= 0 && k < this.recipeWidth && l < this.recipeHeight)
                {
                    if (invert)
                    {
                        itemstack = this.recipeItems[this.recipeWidth - k - 1 + l * this.recipeWidth];
                    }
                    else
                    {
                        itemstack = this.recipeItems[k + l * this.recipeWidth];
                    }
                }

                ItemStack itemstack1 = inventory.getStackInRowAndColumn(i, j);

                if (!itemstack1.isEmpty() || !itemstack.isEmpty())
                {
                    if (itemstack1.isEmpty() && !itemstack.isEmpty() || !itemstack1.isEmpty() && itemstack.isEmpty())
                    {
                        return false;
                    }
                    if (itemstack.getItem() != itemstack1.getItem())
                    {
                        return false;
                    }
                    if (itemstack.getMetadata() != 32767 && itemstack.getMetadata() != itemstack1.getMetadata())
                    {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv)
    {
        return this.getRecipeOutput().copy();
    }

    @Override
    public boolean canFit(int width, int height)
    {
        return width >= this.recipeWidth && height >= this.recipeHeight;
    }

    @Override
    public int getRecipeWidth()
    {
        return this.recipeWidth;
    }

    @Override
    public int getRecipeHeight()
    {
        return this.recipeHeight;
    }
}