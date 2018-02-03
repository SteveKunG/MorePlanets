package stevekung.mods.moreplanets.util.world.gen.biome;

import micdoodle8.mods.galacticraft.api.world.BiomeGenBaseGC;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BiomeBaseMP extends BiomeGenBaseGC
{
    public BiomeBaseMP(BiomeProperties properties)
    {
        super(properties, true);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getGrassColorAtPos(BlockPos pos)
    {
        return 747097;
    }
}