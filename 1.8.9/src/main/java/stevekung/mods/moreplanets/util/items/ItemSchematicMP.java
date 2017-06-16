package stevekung.mods.moreplanets.util.items;

import micdoodle8.mods.galacticraft.api.recipe.ISchematicItem;
import micdoodle8.mods.galacticraft.core.entities.EntityHangingSchematic;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemSchematicMP extends ItemBaseMP implements ISchematicItem
{
    public ItemSchematicMP()
    {
        this.setMaxStackSize(1);
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (side == EnumFacing.DOWN)
        {
            return false;
        }
        else if (side == EnumFacing.UP)
        {
            return false;
        }
        else
        {
            BlockPos blockpos = pos.offset(side);

            if (!player.canPlayerEdit(blockpos, side, itemStack))
            {
                return false;
            }
            else
            {
                EntityHangingSchematic hanging = this.createEntity(world, blockpos, side, this.getIndex(itemStack.getItemDamage()));

                if (hanging != null && hanging.onValidSurface())
                {
                    if (!world.isRemote)
                    {
                        world.spawnEntityInWorld(hanging);
                        hanging.sendToClient(world, blockpos);
                    }
                    --itemStack.stackSize;
                }
                return true;
            }
        }
    }

    private EntityHangingSchematic createEntity(World world, BlockPos pos, EnumFacing side, int index)
    {
        return new EntityHangingSchematic(world, pos, side, index);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return StatCollector.translateToLocal("item.nasa_workbench_schematic");
    }

    @Override
    public EnumSortCategoryItem getItemCategory(int meta)
    {
        return EnumSortCategoryItem.SCHEMATIC;
    }

    protected int getIndex(int damage)
    {
        return damage;
    }
}