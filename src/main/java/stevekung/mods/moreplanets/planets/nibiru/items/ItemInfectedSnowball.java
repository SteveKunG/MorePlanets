package stevekung.mods.moreplanets.planets.nibiru.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.planets.nibiru.entity.projectile.EntityInfectedSnowball;
import stevekung.mods.moreplanets.utils.items.EnumSortCategoryItem;
import stevekung.mods.moreplanets.utils.items.ItemBaseMP;

public class ItemInfectedSnowball extends ItemBaseMP
{
    public ItemInfectedSnowball(String name)
    {
        super();
        this.setMaxStackSize(16);
        this.setUnlocalizedName(name);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
    {
        ItemStack itemStack = player.getHeldItem(hand);

        if (!player.capabilities.isCreativeMode)
        {
            itemStack.shrink(1);
        }

        world.playSound(null, player.getPosition(), SoundEvents.ENTITY_EGG_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
        player.swingArm(hand);

        if (!world.isRemote)
        {
            EntityInfectedSnowball ball = new EntityInfectedSnowball(world, player);
            ball.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 1.5F, 1.0F);
            world.spawnEntity(ball);
        }
        player.addStat(StatList.getObjectUseStats(this));
        return new ActionResult<>(EnumActionResult.SUCCESS, itemStack);
    }

    @Override
    public EnumSortCategoryItem getItemCategory()
    {
        return EnumSortCategoryItem.PROJECTILE;
    }
}