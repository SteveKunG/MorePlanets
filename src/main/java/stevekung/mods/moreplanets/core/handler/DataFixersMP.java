package stevekung.mods.moreplanets.core.handler;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.IFixableData;
import net.minecraftforge.common.util.ModFixs;
import net.minecraftforge.fml.common.FMLCommonHandler;
import stevekung.mods.moreplanets.core.MorePlanetsMod;

public class DataFixersMP
{
    private static final int DATA_FIXER_VERSION = 1;

    public static void init()
    {
        ModFixs fix = FMLCommonHandler.instance().getDataFixer().init(MorePlanetsMod.MOD_ID, DataFixersMP.DATA_FIXER_VERSION);
        fix.registerFix(FixTypes.BLOCK_ENTITY, new TileEntityIDFixer());
    }

    private static class TileEntityIDFixer implements IFixableData
    {
        private final Map<String, String> tileEntityNames;
        {
            ImmutableMap.Builder<String, String> nameMap = ImmutableMap.builder();
            nameMap.put("moreplanets:large_infected_crystallized", "moreplanets:infected_crystallized_crystal");
            this.tileEntityNames = nameMap.build();
        }

        @Override
        public int getFixVersion()
        {
            return 1;
        }

        @Override
        public NBTTagCompound fixTagCompound(NBTTagCompound nbt)
        {
            String tileId = nbt.getString("id");
            nbt.setString("id", this.tileEntityNames.getOrDefault(tileId, tileId));
            return nbt;
        }
    }
}