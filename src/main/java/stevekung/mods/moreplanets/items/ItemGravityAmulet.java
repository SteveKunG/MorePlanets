package stevekung.mods.moreplanets.items;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional;
import stevekung.mods.moreplanets.util.CompatibilityManagerMP;
import stevekung.mods.moreplanets.util.items.ItemBaseMP;

@Optional.Interface(iface = "baubles.api.IBauble", modid = CompatibilityManagerMP.baublesModId, striprefs = true)
public class ItemGravityAmulet extends ItemBaseMP implements IBauble
{
    public ItemGravityAmulet(String name)
    {
        this.setUnlocalizedName(name);
    }

    @Override
    public void onUpdate(ItemStack itemStack, World world, Entity entity, int slot, boolean isSelected)
    {
        if (!CompatibilityManagerMP.isBaubleLoaded)
        {
            if (entity instanceof EntityPlayerMP)
            {
                EntityPlayerMP player = (EntityPlayerMP) entity;
                this.updateMovement(player, player.inventory.hasItemStack(new ItemStack(this)));
            }
        }
    }

    @Override
    public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player)
    {
        player.capabilities.isFlying = false;
        player.sendPlayerAbilities();
        return true;
    }

    @Override
    @Optional.Method(modid = CompatibilityManagerMP.baublesModId)
    public BaubleType getBaubleType(ItemStack itemStack)
    {
        return BaubleType.AMULET;
    }

    @Override
    @Optional.Method(modid = CompatibilityManagerMP.baublesModId)
    public void onWornTick(ItemStack itemStack, EntityLivingBase living)
    {
        if (living instanceof EntityPlayerMP)
        {
            EntityPlayerMP player = (EntityPlayerMP) living;
            this.updateMovement(player, player.inventory.hasItemStack(new ItemStack(this)));
        }
    }

    private void updateMovement(EntityPlayerMP player, boolean hasItem)
    {
        if (player.isSpectator())
        {
            return;
        }
        if (player.capabilities.isCreativeMode)
        {
            hasItem = true;
        }

        if (player.capabilities.allowFlying != hasItem)
        {
            player.capabilities.allowFlying = hasItem;

            if (!hasItem)
            {
                player.capabilities.isFlying = false;
            }
            player.sendPlayerAbilities();
        }
    }
}