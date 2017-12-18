package stevekung.mods.moreplanets.planets.fronos.blocks;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.blocks.base.BlockBaseMP;

public class BlockHugeSkyMushroom extends BlockBaseMP
{
    private static String[] field_149793_a = new String[] {"skin_brown", "skin_red"};
    @SideOnly(Side.CLIENT)
    private IIcon[] field_149794_M;
    @SideOnly(Side.CLIENT)
    private IIcon field_149795_N;
    @SideOnly(Side.CLIENT)
    private IIcon field_149796_O;
    private int field_149792_b = 0;

    public BlockHugeSkyMushroom(String name)
    {
        super(Material.wood);
        this.setHardness(0.2F);
        this.setStepSound(soundTypeWood);
        this.setBlockName(name);
    }

    @Override
    public CreativeTabs getCreativeTabToDisplayOn()
    {
        return null;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int meta, int side)
    {
        return side == 10 && meta > 1 ? this.field_149795_N : side >= 1 && side <= 9 && meta == 1 ? this.field_149794_M[this.field_149792_b ] : side >= 1 && side <= 3 && meta == 2 ? this.field_149794_M[this.field_149792_b] : side >= 7 && side <= 9 && meta == 3 ? this.field_149794_M[this.field_149792_b] : (side == 1 || side == 4 || side == 7) && meta == 4 ? this.field_149794_M[this.field_149792_b] : (side == 3 || side == 6 || side == 9) && meta == 5 ? this.field_149794_M[this.field_149792_b] : side == 14 ? this.field_149794_M[this.field_149792_b] : side == 15 ? this.field_149795_N : this.field_149796_O;
    }

    @Override
    public int quantityDropped(Random rand)
    {
        int i = rand.nextInt(10) - 7;

        if (i < 0)
        {
            i = 0;
        }
        return i;
    }

    @Override
    public Item getItemDropped(int meta, Random rand, int fortune)
    {
        return Item.getItemFromBlock(FronosBlocks.fronos_flower);
    }

    @Override
    public int damageDropped(int meta)
    {
        return 4;
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition moving, World world, int x, int y, int z)
    {
        return new ItemStack(FronosBlocks.fronos_flower, 1, 4);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_)
    {
        this.field_149794_M = new IIcon[field_149793_a.length];

        for (int i = 0; i < this.field_149794_M.length; ++i)
        {
            this.field_149794_M[i] = p_149651_1_.registerIcon("fronos:sky_mushroom_block");
        }
        this.field_149796_O = p_149651_1_.registerIcon("fronos:sky_mushroom_block_inside");
        this.field_149795_N = p_149651_1_.registerIcon("fronos:sky_mushroom_block_skin_stem");
    }
}