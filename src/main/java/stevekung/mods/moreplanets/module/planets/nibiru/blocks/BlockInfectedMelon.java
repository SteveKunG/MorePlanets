package stevekung.mods.moreplanets.module.planets.nibiru.blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.module.planets.nibiru.items.ItemNibiruFruits;
import stevekung.mods.moreplanets.module.planets.nibiru.items.NibiruItems;
import stevekung.mods.moreplanets.util.blocks.BlockBaseMP;

public class BlockInfectedMelon extends BlockBaseMP
{
    protected BlockInfectedMelon(String name)
    {
        super(Material.gourd);
        this.setUnlocalizedName(name);
        this.setHardness(1.0F);
        this.setStepSound(soundTypeWood);
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, BlockPos pos, EntityPlayer player)
    {
        return new ItemStack(this, 1, 0);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return NibiruItems.NIBIRU_FRUITS;
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return ItemNibiruFruits.ItemType.INFECTED_MELON.ordinal();
    }

    @Override
    public int quantityDropped(Random rand)
    {
        return 3 + rand.nextInt(5);
    }

    @Override
    public int quantityDroppedWithBonus(int fortune, Random rand)
    {
        return Math.min(9, this.quantityDropped(rand) + rand.nextInt(1 + fortune));
    }

    @Override
    public String getName()
    {
        return "infected_melon_block";
    }
}