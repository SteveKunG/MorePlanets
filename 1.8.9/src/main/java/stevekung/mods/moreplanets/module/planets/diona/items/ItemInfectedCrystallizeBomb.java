package stevekung.mods.moreplanets.module.planets.diona.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.module.planets.diona.entity.EntityInfectedCrystallizeBomb;
import stevekung.mods.moreplanets.util.items.EnumSortCategoryItem;
import stevekung.mods.moreplanets.util.items.ItemBaseMP;

public class ItemInfectedCrystallizeBomb extends ItemBaseMP
{
    public ItemInfectedCrystallizeBomb(String name)
    {
        super();
        this.setMaxStackSize(32);
        this.setUnlocalizedName(name);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
        if (!player.capabilities.isCreativeMode)
        {
            --itemStack.stackSize;
        }

        world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (world.rand.nextFloat() * 0.4F + 0.8F));
        player.swingItem();

        if (!world.isRemote)
        {
            world.spawnEntityInWorld(new EntityInfectedCrystallizeBomb(world, player));
        }
        return itemStack;
    }

    @Override
    public EnumSortCategoryItem getItemCategory(int meta)
    {
        return EnumSortCategoryItem.PROJECTILE;
    }

    @Override
    public String getName()
    {
        return "infected_crystallize_bomb";
    }
}