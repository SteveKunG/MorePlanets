package stevekung.mods.moreplanets.util.items;

import java.util.List;

import micdoodle8.mods.galacticraft.api.entity.IRocketType.EnumRocketType;
import micdoodle8.mods.galacticraft.api.item.IHoldableItem;
import micdoodle8.mods.galacticraft.core.GCBlocks;
import micdoodle8.mods.galacticraft.core.tile.TileEntityLandingPad;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.util.CachedEnumUtil;

public abstract class ItemRocketBaseMP extends ItemBaseMP implements IHoldableItem, ISortableItem
{
    public ItemRocketBaseMP()
    {
        super();
        this.setHasSubtypes(true);
        this.setMaxStackSize(1);
    }

    @Override
    public EnumActionResult onItemUse(ItemStack itemStack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        boolean padFound = false;
        TileEntity tile = null;

        if (world.isRemote)
        {
            return EnumActionResult.PASS;
        }
        else
        {
            float centerX = -1;
            float centerY = -1;
            float centerZ = -1;

            for (int i = -1; i < 2; i++)
            {
                for (int j = -1; j < 2; j++)
                {
                    BlockPos pos1 = pos.add(i, 0, j);
                    IBlockState state = world.getBlockState(pos1);
                    Block block = state.getBlock();
                    int meta = block.getMetaFromState(state);

                    if (block == GCBlocks.landingPadFull && meta == 0)
                    {
                        padFound = true;
                        tile = world.getTileEntity(pos1);
                        centerX = pos.getX() + i + 0.5F;
                        centerY = pos.getY() + 0.4F;
                        centerZ = pos.getZ() + j + 0.5F;
                        break;
                    }
                }
                if (padFound)
                {
                    break;
                }
            }

            if (padFound)
            {
                if (tile instanceof TileEntityLandingPad)
                {
                    if (((TileEntityLandingPad)tile).getDockedEntity() != null)
                    {
                        return EnumActionResult.PASS;
                    }
                }
                else
                {
                    return EnumActionResult.PASS;
                }
                this.spawnRocket(itemStack, world, player, centerX, centerY, centerZ);
            }
            else
            {
                return EnumActionResult.PASS;
            }
        }
        return EnumActionResult.SUCCESS;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs creativeTabs, List<ItemStack> list)
    {
        for (int i = 0; i < CachedEnumUtil.valuesRocketCached().length; i++)
        {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer player, List<String> list, boolean advanced)
    {
        EnumRocketType type;

        if (itemStack.getItemDamage() < 10)
        {
            type = CachedEnumUtil.valuesRocketCached()[itemStack.getItemDamage()];
        }
        else
        {
            type = CachedEnumUtil.valuesRocketCached()[itemStack.getItemDamage() - 10];
        }

        if (!type.getTooltip().isEmpty())
        {
            list.add(type.getTooltip());
        }
        if (type.getPreFueled())
        {
            list.add(TextFormatting.RED + "" + TextFormatting.ITALIC + GCCoreUtil.translate("gui.creative_only.desc"));
        }
        if (itemStack.hasTagCompound() && itemStack.getTagCompound().hasKey("RocketFuel"))
        {
            this.addDescription(itemStack, list);
        }
    }

    @Override
    public boolean shouldHoldLeftHandUp(EntityPlayer player)
    {
        return true;
    }

    @Override
    public boolean shouldHoldRightHandUp(EntityPlayer player)
    {
        return true;
    }

    @Override
    public boolean shouldCrouch(EntityPlayer player)
    {
        return true;
    }

    @Override
    public EnumSortCategoryItem getItemCategory(int meta)
    {
        return EnumSortCategoryItem.ROCKET;
    }

    protected abstract void spawnRocket(ItemStack itemStack, World world, EntityPlayer player, float centerX, float centerY, float centerZ);

    @SideOnly(Side.CLIENT)
    protected abstract void addDescription(ItemStack itemStack, List<String> list);
}