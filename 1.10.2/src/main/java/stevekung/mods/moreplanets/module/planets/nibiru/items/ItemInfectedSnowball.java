package stevekung.mods.moreplanets.module.planets.nibiru.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.module.planets.nibiru.entity.projectile.EntityInfectedSnowball;
import stevekung.mods.moreplanets.util.items.EnumSortCategoryItem;
import stevekung.mods.moreplanets.util.items.ItemBaseMP;

public class ItemInfectedSnowball extends ItemBaseMP
{
    public ItemInfectedSnowball(String name)
    {
        super();
        this.setMaxStackSize(16);
        this.setUnlocalizedName(name);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
        if (!player.capabilities.isCreativeMode)
        {
            --itemStack.stackSize;
        }

        world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
        player.swingItem();

        if (!world.isRemote)
        {
            world.spawnEntityInWorld(new EntityInfectedSnowball(world, player));
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
        return "infected_snowball";
    }
}