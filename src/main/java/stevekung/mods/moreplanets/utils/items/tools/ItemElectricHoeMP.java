package stevekung.mods.moreplanets.utils.items.tools;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import micdoodle8.mods.galacticraft.api.item.ElectricItemHelper;
import micdoodle8.mods.galacticraft.api.item.IItemElectric;
import micdoodle8.mods.galacticraft.core.energy.EnergyDisplayHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.utils.BlocksItemsRegistry;
import stevekung.mods.moreplanets.utils.items.EnumSortCategoryItem;
import stevekung.mods.moreplanets.utils.items.ISortableItem;

public class ItemElectricHoeMP extends ItemHoe implements IItemElectric, ISortableItem
{
    public float transferMax = 200;
    private float maxPower;

    public ItemElectricHoeMP(String name, ToolMaterial material, float maxPower)
    {
        super(material);
        this.setMaxDamage(100);
        this.setNoRepair();
        this.setUnlocalizedName(name);
        this.maxPower = maxPower;
    }

    @Override
    public float getMaxElectricityStored(ItemStack itemStack)
    {
        return this.maxPower;
    }

    @Override
    public boolean hitEntity(ItemStack itemStack, EntityLivingBase living, EntityLivingBase holder)
    {
        if (this.getElectricityStored(itemStack) > 0.0F)
        {
            this.setElectricity(itemStack, this.getElectricityStored(itemStack) - 10.0F);
            return true;
        }
        return false;
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack itemStack = player.getHeldItem(hand);

        if (this.getElectricityStored(itemStack) > 0.0F)
        {
            if (!player.canPlayerEdit(pos.offset(facing), facing, itemStack))
            {
                return EnumActionResult.FAIL;
            }
            else
            {
                int hook = ForgeEventFactory.onHoeUse(itemStack, player, world, pos);
                IBlockState iblockstate = world.getBlockState(pos);
                Block block = iblockstate.getBlock();

                if (hook != 0)
                {
                    return hook > 0 ? EnumActionResult.SUCCESS : EnumActionResult.FAIL;
                }
                if (facing != EnumFacing.DOWN && world.isAirBlock(pos.up()))
                {
                    if (block == Blocks.GRASS || block == Blocks.GRASS_PATH)
                    {
                        this.setBlock(itemStack, player, world, pos, Blocks.FARMLAND.getDefaultState());
                        return EnumActionResult.SUCCESS;
                    }
                    if (block == Blocks.DIRT)
                    {
                        switch (iblockstate.getValue(BlockDirt.VARIANT))
                        {
                        case DIRT:
                        default:
                            this.setBlock(itemStack, player, world, pos, Blocks.FARMLAND.getDefaultState());
                            return EnumActionResult.SUCCESS;
                        case COARSE_DIRT:
                            this.setBlock(itemStack, player, world, pos, Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.DIRT));
                            return EnumActionResult.SUCCESS;
                        }
                    }
                }
                return EnumActionResult.PASS;
            }
        }
        return EnumActionResult.PASS;
    }

    @Override
    protected void setBlock(ItemStack itemStack, EntityPlayer player, World world, BlockPos pos, IBlockState state)
    {
        world.playSound(player, pos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);

        if (!world.isRemote)
        {
            world.setBlockState(pos, state, 11);
            this.setElectricity(itemStack, this.getElectricityStored(itemStack) - 10.0F);
        }
    }

    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack itemStack)
    {
        return this.getElectricityStored(itemStack) > 0.0F ? super.getAttributeModifiers(slot, itemStack) : HashMultimap.create();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, @Nullable World world, List<String> list, ITooltipFlag flag)
    {
        TextFormatting color = null;
        float joules = this.getElectricityStored(itemStack);

        if (joules <= this.getMaxElectricityStored(itemStack) / 3)
        {
            color = TextFormatting.DARK_RED;
        }
        else if (joules > this.getMaxElectricityStored(itemStack) * 2 / 3)
        {
            color = TextFormatting.DARK_GREEN;
        }
        else
        {
            color = TextFormatting.GOLD;
        }
        list.add(color + EnergyDisplayHelper.getEnergyDisplayS(joules) + "/" + EnergyDisplayHelper.getEnergyDisplayS(this.getMaxElectricityStored(itemStack)));
    }

    @Override
    public void onCreated(ItemStack itemStack, World world, EntityPlayer player)
    {
        this.setElectricity(itemStack, 0);
    }

    @Override
    public float recharge(ItemStack itemStack, float energy, boolean doReceive)
    {
        float rejectedElectricity = Math.max(this.getElectricityStored(itemStack) + energy - this.getMaxElectricityStored(itemStack), 0);
        float energyToReceive = energy - rejectedElectricity;

        if (energyToReceive > this.transferMax)
        {
            rejectedElectricity += energyToReceive - this.transferMax;
            energyToReceive = this.transferMax;
        }
        if (doReceive)
        {
            this.setElectricity(itemStack, this.getElectricityStored(itemStack) + energyToReceive);
        }
        return energyToReceive;
    }

    @Override
    public float discharge(ItemStack itemStack, float energy, boolean doTransfer)
    {
        float energyToTransfer = Math.min(Math.min(this.getElectricityStored(itemStack), energy), this.transferMax);

        if (doTransfer)
        {
            this.setElectricity(itemStack, this.getElectricityStored(itemStack) - energyToTransfer);
        }
        return energyToTransfer;
    }

    @Override
    public int getTierGC(ItemStack itemStack)
    {
        return 1;
    }

    @Override
    public void setElectricity(ItemStack itemStack, float joules)
    {
        if (!itemStack.hasTagCompound())
        {
            itemStack.setTagCompound(new NBTTagCompound());
        }
        float electricityStored = Math.max(Math.min(joules, this.getMaxElectricityStored(itemStack)), 0);
        itemStack.getTagCompound().setFloat("Electricity", electricityStored);
        itemStack.setItemDamage((int) (100 - electricityStored / this.getMaxElectricityStored(itemStack) * 100));
    }

    @Override
    public float getTransfer(ItemStack itemStack)
    {
        return Math.min(this.transferMax, this.getMaxElectricityStored(itemStack) - this.getElectricityStored(itemStack));
    }

    @Override
    public float getElectricityStored(ItemStack itemStack)
    {
        if (!itemStack.hasTagCompound())
        {
            itemStack.setTagCompound(new NBTTagCompound());
        }

        float energyStored = 0f;

        if (itemStack.getTagCompound().hasKey("Electricity"))
        {
            NBTBase obj = itemStack.getTagCompound().getTag("Electricity");

            if (obj instanceof NBTTagDouble)
            {
                energyStored = ((NBTTagDouble) obj).getFloat();
            }
            else if (obj instanceof NBTTagFloat)
            {
                energyStored = ((NBTTagFloat) obj).getFloat();
            }
        }
        itemStack.setItemDamage((int) (100 - energyStored / this.getMaxElectricityStored(itemStack) * 100));
        return energyStored;
    }

    @Override
    public void getSubItems(CreativeTabs creativeTabs, NonNullList<ItemStack> list)
    {
        if (BlocksItemsRegistry.isItemTab(creativeTabs))
        {
            list.add(ElectricItemHelper.getWithCharge(new ItemStack(this), this.getMaxElectricityStored(new ItemStack(this))));
        }
    }

    @Override
    public CreativeTabs getCreativeTab()
    {
        return MorePlanetsMod.ITEM_TAB;
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
    {
        return false;
    }

    @Override
    public EnumSortCategoryItem getItemCategory()
    {
        return EnumSortCategoryItem.HOE;
    }
}