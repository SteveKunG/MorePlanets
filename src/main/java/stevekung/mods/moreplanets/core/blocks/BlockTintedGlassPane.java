package stevekung.mods.moreplanets.core.blocks;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import micdoodle8.mods.galacticraft.api.block.IPartialSealableBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPane;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.core.init.MPBlocks;
import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;

public class BlockTintedGlassPane extends BlockPane implements IPartialSealableBlock
{
    private static IIcon[] field_150106_a = new IIcon[16];

    public BlockTintedGlassPane(String name)
    {
        super("glass", "mpcore:tinted_glass_pane_top", Material.glass, false);
        this.setHardness(0.5F);
        this.setResistance(20.0F);
        this.setStepSound(soundTypeGlass);
        this.setBlockName(name);
    }

    @Override
    public CreativeTabs getCreativeTabToDisplayOn()
    {
        return MorePlanetsCore.mpBlocksTab;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon func_149735_b(int p_149735_1_, int p_149735_2_)
    {
        return field_150106_a[p_149735_2_ % field_150106_a.length];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int p_149691_2_)
    {
        return this.func_149735_b(p_149691_1_, ~p_149691_2_ & 15);
    }

    @Override
    public int damageDropped(int p_149692_1_)
    {
        return p_149692_1_;
    }

    @SideOnly(Side.CLIENT)
    public static int func_150103_c(int p_150103_0_)
    {
        return p_150103_0_ & 15;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item p_149666_1_, CreativeTabs p_149666_2_, List p_149666_3_)
    {
        for (int i = 0; i < field_150106_a.length; ++i)
        {
            p_149666_3_.add(new ItemStack(this, 1, i));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass()
    {
        return 1;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_)
    {
        super.registerBlockIcons(p_149651_1_);

        for (int i = 0; i < field_150106_a.length; ++i)
        {
            field_150106_a[i] = p_149651_1_.registerIcon("mpcore:tinted_glass_pane_" + ItemDye.field_150921_b[func_150103_c(i)]);
        }
    }

    @Override
    public boolean canPaneConnectTo(IBlockAccess world, int x, int y, int z, ForgeDirection dir)
    {
        Block block = world.getBlock(x, y, z);
        return block == FronosBlocks.cheese_glass || block == MPBlocks.tinted_glass || super.canPaneConnectTo(world, x, y, z, dir);
    }

    @Override
    public boolean isSealed(World world, int x, int y, int z, ForgeDirection direction)
    {
        return true;
    }
}