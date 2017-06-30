package stevekung.mods.moreplanets.module.planets.chalos.entity;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.module.planets.chalos.items.ChalosItems;
import stevekung.mods.moreplanets.util.entity.EntityTieredRocketMP;

public class EntityTier5Rocket extends EntityTieredRocketMP
{
    public EntityTier5Rocket(World world)
    {
        super(world);
        this.setSize(1.8F, 6F);
    }

    public EntityTier5Rocket(World world, double x, double y, double z, EnumRocketType type)
    {
        super(world, x, y, z);
        this.rocketType = type;
        this.stacks = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
    }

    public EntityTier5Rocket(World world, double x, double y, double z, EnumRocketType type, NonNullList<ItemStack> inv)
    {
        this(world, x, y, z, type);
        this.stacks = inv;
    }

    @Override
    public int getRocketTier()
    {
        return 5;
    }

    @Override
    public int getFuelTankCapacity()
    {
        return 2000;
    }

    @Override
    protected Item getRocketItem()
    {
        return ChalosItems.TIER_5_ROCKET;
    }
}