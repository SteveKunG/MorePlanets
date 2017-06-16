package stevekung.mods.moreplanets.asteroids.darkasteroids.blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import stevekung.mods.moreplanets.asteroids.darkasteroids.items.DarkAsteroidsItems;
import stevekung.mods.moreplanets.core.blocks.base.BlockBaseMP;

public class BlockAlphereOre extends BlockBaseMP
{
    public BlockAlphereOre(String name)
    {
        super(Material.rock);
        this.setHardness(3.0F);
        this.setResistance(5.0F);
        this.setBlockTextureName("mpcore:darkasteroids/alphere_ore");
        this.setBlockName(name);
    }

    @Override
    public Item getItemDropped(int meta, Random rand, int fortune)
    {
        return DarkAsteroidsItems.alphere;
    }

    @Override
    public int quantityDroppedWithBonus(int fortune, Random rand)
    {
        if (fortune > 0 && Item.getItemFromBlock(this) != this.getItemDropped(0, rand, fortune))
        {
            int j = rand.nextInt(fortune + 2) - 1;

            if (j < 0)
            {
                j = 0;
            }
            return this.quantityDropped(rand) * (j + 1);
        }
        else
        {
            return this.quantityDropped(rand);
        }
    }

    @Override
    public int getExpDrop(IBlockAccess world, int meta, int fortune)
    {
        if (this.getItemDropped(meta, new Random(), fortune) != Item.getItemFromBlock(this))
        {
            return MathHelper.getRandomIntegerInRange(new Random(), 3, 7);
        }
        return 0;
    }
}