package stevekung.mods.moreplanets.module.planets.nibiru.blocks;

import java.util.Random;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.module.planets.nibiru.items.NibiruItems;
import stevekung.mods.moreplanets.utils.blocks.BlockBaseMP;

public class BlockInfectedSeaLantern extends BlockBaseMP
{
    public BlockInfectedSeaLantern(String name)
    {
        super(Material.GLASS);
        this.setSoundType(SoundType.GLASS);
        this.setHardness(0.3F);
        this.setLightLevel(1.0F);
        this.setUnlocalizedName(name);
    }

    @Override
    public int quantityDropped(Random rand)
    {
        return 2 + rand.nextInt(2);
    }

    @Override
    public int quantityDroppedWithBonus(int fortune, Random rand)
    {
        return MathHelper.clamp(this.quantityDropped(rand) + rand.nextInt(fortune + 1), 1, 5);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return NibiruItems.INFECTED_PRISMARINE_CRYSTALS;
    }

    @Override
    public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player)
    {
        return true;
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
    {
        return new ItemStack(this, 1, 0);
    }
}