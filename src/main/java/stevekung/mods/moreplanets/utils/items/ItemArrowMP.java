package stevekung.mods.moreplanets.utils.items;

import java.util.List;

import javax.annotation.Nullable;

import micdoodle8.mods.galacticraft.api.item.GCRarity;
import micdoodle8.mods.galacticraft.core.TransformerHooks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.init.MPItems;
import stevekung.mods.moreplanets.planets.diona.entity.projectile.EntityAntiGravityArrow;
import stevekung.mods.moreplanets.planets.diona.entity.projectile.EntityInfectedPurloniteArrow;
import stevekung.mods.moreplanets.planets.nibiru.entity.projectile.EntityInfectedArrow;
import stevekung.mods.moreplanets.utils.BlocksItemsRegistry;
import stevekung.mods.moreplanets.utils.client.renderer.IItemModelRender;
import stevekung.mods.moreplanets.utils.itemblocks.IItemRarity;
import stevekung.mods.stevekunglib.utils.ColorUtils;
import stevekung.mods.stevekunglib.utils.LangUtils;
import stevekung.mods.stevekunglib.utils.client.ClientUtils;

public class ItemArrowMP extends ItemArrow implements ISortableItem, IItemModelRender, IItemRarity, GCRarity
{
    private final ArrowType type;
    private ColorUtils.RGB rgb;
    private final String name;

    public ItemArrowMP(String name, ArrowType type)
    {
        this.type = type;
        this.name = name;
        this.setTranslationKey(name);
    }

    @Override
    public CreativeTabs getCreativeTab()
    {
        return MorePlanetsMod.ITEM_TAB;
    }

    @Override
    public EnumSortCategoryItem getItemCategory()
    {
        return EnumSortCategoryItem.ARROW;
    }

    @Override
    public EntityArrow createArrow(World world, ItemStack itemStack, EntityLivingBase shooter)
    {
        switch (this.type)
        {
        case INFECTED_PURLONITE:
            return new EntityInfectedPurloniteArrow(world, shooter);
        case INFECTED:
            return new EntityInfectedArrow(world, shooter);
        case ANTI_GRAVITY:
            return new EntityAntiGravityArrow(world, shooter);
        default:
            return super.createArrow(world, itemStack, shooter);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, @Nullable World world, List<String> list, ITooltipFlag flag)
    {
        if (this.type == ArrowType.INFECTED)
        {
            if (ClientUtils.isShiftKeyDown())
            {
                list.addAll(BlocksItemsRegistry.getDescription(this.getTranslationKey() + ".description"));
            }
            else
            {
                list.add(LangUtils.translate("desc.shift_info.name"));
            }
        }
    }

    @Override
    public boolean onEntityItemUpdate(EntityItem entityItem)
    {
        if (entityItem.getItem().getItem() == MPItems.ANTI_GRAVITY_ARROW)
        {
            entityItem.motionY += TransformerHooks.getItemGravity(entityItem);
        }
        return false;
    }

    @Override
    public String getName()
    {
        return this.name;
    }

    @Override
    public ColorUtils.RGB getRarity()
    {
        return this.rgb != null ? this.rgb : null;
    }

    @Override
    public String getItemStackDisplayName(ItemStack itemStack)
    {
        return this instanceof IItemRarity && ((IItemRarity)this).getRarity() != null ? ((IItemRarity)this).getRarity().toColoredFont() + super.getItemStackDisplayName(itemStack) : super.getItemStackDisplayName(itemStack);
    }

    public ItemArrowMP setRarityRGB(ColorUtils.RGB rgb)
    {
        this.rgb = rgb;
        return this;
    }

    public enum ArrowType
    {
        INFECTED_PURLONITE,
        INFECTED,
        ANTI_GRAVITY;
    }
}