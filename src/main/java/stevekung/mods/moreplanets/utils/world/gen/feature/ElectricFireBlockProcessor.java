package stevekung.mods.moreplanets.utils.world.gen.feature;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.template.ITemplateProcessor;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import stevekung.mods.moreplanets.init.MPBlocks;

public class ElectricFireBlockProcessor implements ITemplateProcessor
{
    private final float chance;
    private final Random rand;

    public ElectricFireBlockProcessor(BlockPos pos, PlacementSettings settings)
    {
        this.chance = settings.getIntegrity();
        this.rand = settings.getRandom(pos);
    }

    @Override
    @Nullable
    public Template.BlockInfo processBlock(World world, BlockPos pos, Template.BlockInfo info)
    {
        return this.chance < 1.0F && this.rand.nextFloat() > this.chance ? new Template.BlockInfo(pos, MPBlocks.ELECTRICAL_FIRE.getDefaultState(), null) : this.chance < 0.8F && this.rand.nextFloat() > this.chance ? null : info;
    }
}