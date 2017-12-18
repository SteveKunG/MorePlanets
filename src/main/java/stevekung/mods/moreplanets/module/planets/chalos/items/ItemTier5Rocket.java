package stevekung.mods.moreplanets.module.planets.chalos.items;

import java.util.List;

import micdoodle8.mods.galacticraft.api.entity.IRocketType.EnumRocketType;
import micdoodle8.mods.galacticraft.api.item.IHoldableItem;
import micdoodle8.mods.galacticraft.core.GCFluids;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.module.planets.chalos.entity.EntityTier5Rocket;
import stevekung.mods.moreplanets.util.items.ISortableItem;
import stevekung.mods.moreplanets.util.items.ItemRocketBaseMP;

public class ItemTier5Rocket extends ItemRocketBaseMP implements IHoldableItem, ISortableItem
{
    public ItemTier5Rocket(String name)
    {
        super();
        this.setUnlocalizedName(name);
    }

    @Override
    protected void spawnRocket(ItemStack itemStack, World world, EntityPlayer player, float centerX, float centerY, float centerZ)
    {
        EntityTier5Rocket rocket = new EntityTier5Rocket(world, centerX, centerY, centerZ, EnumRocketType.values()[itemStack.getItemDamage()]);

        rocket.rotationYaw += 45;
        rocket.setPosition(rocket.posX, rocket.posY + rocket.getOnPadYOffset(), rocket.posZ);
        world.spawnEntityInWorld(rocket);

        if (itemStack.hasTagCompound() && itemStack.getTagCompound().hasKey("RocketFuel"))
        {
            rocket.fuelTank.fill(new FluidStack(GCFluids.fluidFuel, itemStack.getTagCompound().getInteger("RocketFuel")), true);
        }

        if (!player.capabilities.isCreativeMode)
        {
            itemStack.stackSize--;

            if (itemStack.stackSize <= 0)
            {
                itemStack = null;
            }
        }
        if (rocket.getType().getPreFueled())
        {
            rocket.fuelTank.fill(new FluidStack(GCFluids.fluidFuel, rocket.getMaxFuel()), true);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    protected void addDescription(ItemStack itemStack, List list)
    {
        EntityTier5Rocket rocket = new EntityTier5Rocket(Minecraft.getMinecraft().theWorld, 0, 0, 0, EnumRocketType.values()[itemStack.getItemDamage()]);
        list.add(StatCollector.translateToLocal("gui.message.fuel.name") + ": " + itemStack.getTagCompound().getInteger("RocketFuel") + " / " + rocket.fuelTank.getCapacity());
    }
}