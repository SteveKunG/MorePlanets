package stevekung.mods.moreplanets.module.planets.chalos.blocks;

import stevekung.mods.moreplanets.util.ItemDescription;
import stevekung.mods.moreplanets.util.blocks.BlockCakeMP;
import stevekung.mods.moreplanets.util.blocks.IBlockDescription;
import stevekung.mods.moreplanets.util.helper.ItemDescriptionHelper;

public class BlockCheeseOfMilkCake extends BlockCakeMP implements IBlockDescription
{
    public BlockCheeseOfMilkCake(String name)
    {
        super();
        this.setUnlocalizedName(name);
    }

    @Override
    public int getFoodAmount()
    {
        return 4;
    }

    @Override
    public float getSaturationAmount()
    {
        return 0.6F;
    }

    @Override
    public ItemDescription getDescription()
    {
        return (itemStack, list) -> list.addAll(ItemDescriptionHelper.getDescription(BlockCheeseOfMilkCake.this.getUnlocalizedName() + ".description"));
    }

    @Override
    public String getName()
    {
        return "cheese_of_milk_cake";
    }
}