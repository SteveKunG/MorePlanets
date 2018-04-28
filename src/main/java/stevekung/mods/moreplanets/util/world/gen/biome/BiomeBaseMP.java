package stevekung.mods.moreplanets.util.world.gen.biome;

import static net.minecraftforge.common.BiomeDictionary.Type.COLD;
import static net.minecraftforge.common.BiomeDictionary.Type.DEAD;
import static net.minecraftforge.common.BiomeDictionary.Type.DRY;

import micdoodle8.mods.galacticraft.api.world.BiomeGenBaseGC;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.init.MPBiomes;

public class BiomeBaseMP extends BiomeGenBaseGC
{
    private String singleName = "";

    public BiomeBaseMP(BiomeProperties properties)
    {
        super(properties, true);
        MPBiomes.biomeList.add(this);
    }

    public BiomeBaseMP(String singleName, BiomeProperties properties)
    {
        this(properties);
        this.singleName = singleName;
    }

    @Override
    public void registerTypes(Biome biome)
    {
        if (this.singleName.equals("diona"))
        {
            MorePlanetsMod.COMMON_REGISTRY.registerBiomeType(biome, COLD, DEAD, DRY);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getGrassColorAtPos(BlockPos pos)
    {
        return 747097;
    }
}