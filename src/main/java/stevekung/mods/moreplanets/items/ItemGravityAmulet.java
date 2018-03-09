package stevekung.mods.moreplanets.items;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional;
import stevekung.mods.moreplanets.init.MPItems;
import stevekung.mods.moreplanets.util.CompatibilityManagerMP;
import stevekung.mods.moreplanets.util.items.ItemBaseMP;

@Optional.Interface(iface = "baubles.api.IBauble", modid = "baubles", striprefs = true)
public class ItemGravityAmulet extends ItemBaseMP implements IBauble
{
    public ItemGravityAmulet(String name)
    {
        this.setUnlocalizedName(name);
    }

    @Override
    public void onUpdate(ItemStack itemStack, World world, Entity entity, int slot, boolean isSelected)
    {
        if (!CompatibilityManagerMP.isBaubleLoaded())
        {
            this.updateMovement(entity);
        }
    }

    @Override
    @Optional.Method(modid = "baubles")
    public BaubleType getBaubleType(ItemStack itemStack)
    {
        return BaubleType.AMULET;
    }

    @Override
    @Optional.Method(modid = "baubles")
    public void onWornTick(ItemStack itemStack, EntityLivingBase living)
    {
        this.updateMovement(living);
    }

    @Override
    public String getName()
    {
        return "gravity_amulet";
    }

    private void updateMovement(Entity entity)
    {
        if (entity instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) entity;

            if (player.inventory.hasItemStack(new ItemStack(MPItems.GRAVITY_AMULET)))
            {
                player.capabilities.allowFlying = true;
            }
            else
            {
                player.capabilities.allowFlying = false;
            }
        }
    }
}