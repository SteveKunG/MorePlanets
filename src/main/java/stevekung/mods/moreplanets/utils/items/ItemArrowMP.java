package stevekung.mods.moreplanets.utils.items;

import java.util.List;

import javax.annotation.Nullable;

import micdoodle8.mods.galacticraft.core.TransformerHooks;
import micdoodle8.mods.galacticraft.core.proxy.ClientProxyCore;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.init.MPItems;
import stevekung.mods.moreplanets.planets.diona.entity.projectile.EntityAntiGravityArrow;
import stevekung.mods.moreplanets.planets.diona.entity.projectile.EntityInfectedCrystallizedArrow;
import stevekung.mods.moreplanets.planets.nibiru.entity.projectile.EntityInfectedArrow;
import stevekung.mods.moreplanets.utils.BlocksItemsRegistry;
import stevekung.mods.moreplanets.utils.client.renderer.IItemModelRender;
import stevekung.mods.moreplanets.utils.itemblocks.IItemRarity;
import stevekung.mods.stevekunglib.utils.ColorUtils;
import stevekung.mods.stevekunglib.utils.LangUtils;
import stevekung.mods.stevekunglib.utils.client.ClientUtils;

public class ItemArrowMP extends ItemArrow implements ISortableItem, IItemModelRender, IItemRarity
{
    private ArrowType type;
    private ColorUtils.RGB rgb;
    private String name;

    public ItemArrowMP(String name, ArrowType type)
    {
        this.type = type;
        this.name = name;
        this.setUnlocalizedName(name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack itemStack)
    {
        return ClientProxyCore.galacticraftItem;
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
        case INFECTED_CRYSTALLIZED:
            return new EntityInfectedCrystallizedArrow(world, shooter);
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
                list.addAll(BlocksItemsRegistry.getDescription(this.getUnlocalizedName() + ".description"));
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
        return this.rgb != null ? this.rgb : ColorUtils.stringToFullRGB(IItemRarity.DEFAULT);
    }

    @Override
    @SideOnly(Side.CLIENT)
    @Nullable
    public FontRenderer getFontRenderer(ItemStack itemStack)
    {
        return ColorUtils.coloredFontRenderer;
    }

    @Override
    public String getItemStackDisplayName(ItemStack itemStack)
    {
        return this instanceof IItemRarity ? ((IItemRarity)this).getRarity().toColoredFont() + super.getItemStackDisplayName(itemStack) : super.getItemStackDisplayName(itemStack);
    }

    public ItemArrowMP setRarityRGB(ColorUtils.RGB rgb)
    {
        this.rgb = rgb;
        return this;
    }

    public static enum ArrowType
    {
        INFECTED_CRYSTALLIZED,
        INFECTED,
        ANTI_GRAVITY;
    }
}