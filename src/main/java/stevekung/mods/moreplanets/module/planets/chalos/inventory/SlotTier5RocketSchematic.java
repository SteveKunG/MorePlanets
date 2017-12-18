package stevekung.mods.moreplanets.module.planets.chalos.inventory;

import micdoodle8.mods.galacticraft.planets.asteroids.items.AsteroidsItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import stevekung.mods.moreplanets.module.planets.chalos.items.ChalosItems;
import stevekung.mods.moreplanets.module.planets.diona.items.DionaItems;
import stevekung.mods.moreplanets.util.inventory.SlotRocketSchematicMP;

public class SlotTier5RocketSchematic extends SlotRocketSchematicMP
{
    private int index;

    public SlotTier5RocketSchematic(IInventory inv, int index, int xDisplay, int yDisplay, BlockPos pos, EntityPlayer player)
    {
        super(inv, index, xDisplay, yDisplay, pos, player);
        this.index = index;
    }

    @Override
    public boolean isItemValid(ItemStack itemStack)
    {
        Item item = itemStack.getItem();
        int meta = itemStack.getItemDamage();

        switch (this.index)
        {
        case 1:
            return item == DionaItems.TIER_4_ROCKET_PART && meta == 2 ? true : false; // Nose Cone
        case 2:
            return item == ChalosItems.TIER_5_ROCKET_PART && meta == 0 ? true : false; // Rocket Plate
        case 3:
            return item == ChalosItems.TIER_5_ROCKET_PART && meta == 0 ? true : false; // Rocket Plate
        case 4:
            return item == ChalosItems.TIER_5_ROCKET_PART && meta == 0 ? true : false; // Rocket Plate
        case 5:
            return item == ChalosItems.TIER_5_ROCKET_PART && meta == 0 ? true : false; // Rocket Plate
        case 6:
            return item == ChalosItems.TIER_5_ROCKET_PART && meta == 0 ? true : false; // Rocket Plate
        case 7:
            return item == ChalosItems.TIER_5_ROCKET_PART && meta == 0 ? true : false; // Rocket Plate
        case 8:
            return item == ChalosItems.TIER_5_ROCKET_PART && meta == 0 ? true : false; // Rocket Plate
        case 9:
            return item == ChalosItems.TIER_5_ROCKET_PART && meta == 0 ? true : false; // Rocket Plate
        case 10:
            return item == ChalosItems.TIER_5_ROCKET_PART && meta == 0 ? true : false; // Rocket Plate
        case 11:
            return item == ChalosItems.TIER_5_ROCKET_PART && meta == 0 ? true : false; // Rocket Plate
        case 12:
            return item == DionaItems.TIER_4_ROCKET_PART && meta == 3 ? true : false; // Booster
        case 13:
            return item == AsteroidsItems.basicItem && meta == 2 ? true : false; // Rocket Fins
        case 14:
            return item == AsteroidsItems.basicItem && meta == 2 ? true : false; // Rocket Fins
        case 15:
            return item == DionaItems.TIER_4_ROCKET_PART && meta == 1 ? true : false; // Rocket Engine
        case 16:
            return item == DionaItems.TIER_4_ROCKET_PART && meta == 3 ? true : false; // Booster
        case 17:
            return item == AsteroidsItems.basicItem && meta == 2 ? true : false; // Rocket Fins
        case 18:
            return item == AsteroidsItems.basicItem && meta == 2 ? true : false; // Rocket Fins
        case 19:
            return true;
        case 20:
            return true;
        case 21:
            return true;
        }
        return false;
    }
}