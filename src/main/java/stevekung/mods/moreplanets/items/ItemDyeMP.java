package stevekung.mods.moreplanets.items;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.module.planets.nibiru.entity.EntityShlime;
import stevekung.mods.moreplanets.util.items.ItemBaseMP;

public class ItemDyeMP extends ItemBaseMP
{
    public ItemDyeMP(String name)
    {
        this.setUnlocalizedName(name);
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack itemStack, EntityPlayer player, EntityLivingBase target)
    {
        if (target instanceof EntitySheep)
        {
            EntitySheep entitysheep = (EntitySheep)target;
            EnumDyeColor enumdyecolor = EnumDyeColor.BLUE;

            if (!entitysheep.getSheared() && entitysheep.getFleeceColor() != enumdyecolor)
            {
                entitysheep.setFleeceColor(enumdyecolor);
                --itemStack.stackSize;
            }
            return true;
        }
        if (target instanceof EntityShlime)
        {
            EntityShlime entitysheep = (EntityShlime)target;
            EnumDyeColor enumdyecolor = EnumDyeColor.BLUE;

            if (!entitysheep.getSheared() && entitysheep.getFleeceColor() != enumdyecolor)
            {
                entitysheep.setFleeceColor(enumdyecolor);
                --itemStack.stackSize;
            }
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public String getName()
    {
        return "dye_mp";
    }
}