package stevekung.mods.moreplanets.utils.items;

import micdoodle8.mods.galacticraft.api.recipe.ISchematicItem;
import micdoodle8.mods.galacticraft.core.entities.EntityHangingSchematic;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import stevekung.mods.stevekunglib.utils.LangUtils;

public class ItemSchematicMP extends ItemBaseMP implements ISchematicItem
{
    public ItemSchematicMP()
    {
        this.setMaxStackSize(1);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack itemStack = player.getHeldItem(hand);
        BlockPos blockpos = pos.offset(facing);

        if (facing != EnumFacing.DOWN && facing != EnumFacing.UP && player.canPlayerEdit(blockpos, facing, itemStack))
        {
            EntityHangingSchematic entityhanging = this.createEntity(world, blockpos, facing, this.getIndex(itemStack.getItemDamage()));

            if (entityhanging != null && entityhanging.onValidSurface())
            {
                if (!world.isRemote)
                {
                    world.spawnEntity(entityhanging);
                    entityhanging.sendToClient(world, blockpos);
                }
                itemStack.shrink(1);
            }
            return EnumActionResult.SUCCESS;
        }
        else
        {
            return EnumActionResult.FAIL;
        }
    }

    @Override
    public String getTranslationKey(ItemStack itemStack)
    {
        return LangUtils.translate("item.nasa_workbench_schematic");
    }

    @Override
    public EnumSortCategoryItem getItemCategory()
    {
        return EnumSortCategoryItem.SCHEMATIC;
    }

    protected int getIndex(int damage)
    {
        return damage;
    }

    private EntityHangingSchematic createEntity(World world, BlockPos pos, EnumFacing side, int index)
    {
        return new EntityHangingSchematic(world, pos, side, index);
    }
}