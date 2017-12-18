package stevekung.mods.moreplanets.module.planets.chalos.blocks;

import java.util.ArrayList;
import java.util.Random;

import com.google.common.collect.Lists;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.module.planets.chalos.items.ChalosItems;
import stevekung.mods.moreplanets.util.blocks.BlockBaseMP;

public class BlockCheeseSpore extends BlockBaseMP
{
    public BlockCheeseSpore(String name)
    {
        super(Material.wood);
        this.setHardness(0.2F);
        this.setUnlocalizedName(name);
        this.setStepSound(soundTypeCloth);
    }

    @Override
    public int quantityDropped(Random rand)
    {
        return 0;
    }

    @Override
    public ArrayList<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        Random rand = world instanceof World ? ((World)world).rand : RANDOM;
        ArrayList<ItemStack> ret = Lists.newArrayList();
        int chance = 20;

        if (fortune > 0)
        {
            chance -= 2 << fortune;

            if (chance < 10)
            {
                chance = 10;
            }
        }

        if (rand.nextInt(chance) == 0)
        {
            ret.add(new ItemStack(ChalosItems.CHEESE_SPORE, 1 + rand.nextInt(2)));
        }
        this.captureDrops(true);
        ret.addAll(this.captureDrops(false));
        return ret;
    }

    @Override
    public String getName()
    {
        return "cheese_spore_block";
    }
}