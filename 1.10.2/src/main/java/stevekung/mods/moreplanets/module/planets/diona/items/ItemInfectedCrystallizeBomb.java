package stevekung.mods.moreplanets.module.planets.diona.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
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
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStack, World world, EntityPlayer player, EnumHand hand)
    {
        if (!player.capabilities.isCreativeMode)
        {
            --itemStack.stackSize;
        }

        world.playSound((EntityPlayer)null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

        if (!world.isRemote)
        {
            EntityInfectedCrystallizeBomb bomb = new EntityInfectedCrystallizeBomb(world, player);
            bomb.setHeadingFromThrower(player, player.rotationPitch, player.rotationYaw, 0.0F, 1.5F, 1.0F);
            world.spawnEntityInWorld(bomb);
        }
        player.addStat(StatList.getObjectUseStats(this));
        return new ActionResult(EnumActionResult.SUCCESS, itemStack);
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