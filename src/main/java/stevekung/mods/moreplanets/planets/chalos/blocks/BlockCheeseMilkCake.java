package stevekung.mods.moreplanets.planets.chalos.blocks;

import stevekung.mods.moreplanets.utils.BlocksItemsRegistry;
import stevekung.mods.moreplanets.utils.IDescription;
import stevekung.mods.moreplanets.utils.ItemDescription;
import stevekung.mods.moreplanets.utils.blocks.BlockCakeMP;

public class BlockCheeseMilkCake extends BlockCakeMP implements IDescription
{
    public BlockCheeseMilkCake(String name)
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
        return (itemStack, list) -> list.addAll(BlocksItemsRegistry.getDescription(this.getUnlocalizedName() + ".description"));
    }
}