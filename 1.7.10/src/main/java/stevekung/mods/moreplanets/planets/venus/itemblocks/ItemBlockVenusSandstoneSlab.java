package stevekung.mods.moreplanets.planets.venus.itemblocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import micdoodle8.mods.galacticraft.core.proxy.ClientProxyCore;
import net.minecraft.block.Block;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.planets.venus.blocks.BlockVenusSandstoneSlab;

public class ItemBlockVenusSandstoneSlab extends ItemSlab
{
    public ItemBlockVenusSandstoneSlab(Block block, BlockVenusSandstoneSlab singleSlab, BlockVenusSandstoneSlab doubleSlab)
    {
        super(block, singleSlab, doubleSlab, block == doubleSlab);
    }

    @Override
    public int getMetadata(int meta)
    {
        return meta & 7;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack par1ItemStack)
    {
        return ClientProxyCore.galacticraftItem;
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        BlockVenusSandstoneSlab slab = (BlockVenusSandstoneSlab)Block.getBlockFromItem(itemStack.getItem());
        return super.getUnlocalizedName() + "." + new StringBuilder().append(slab.func_150002_b(itemStack.getItemDamage())).toString();
    }
}