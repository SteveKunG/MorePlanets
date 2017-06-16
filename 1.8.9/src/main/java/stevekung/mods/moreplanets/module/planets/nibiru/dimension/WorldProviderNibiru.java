package stevekung.mods.moreplanets.module.planets.nibiru.dimension;

import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.recipe.SchematicRegistry;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.core.GCItems;
import micdoodle8.mods.galacticraft.core.entities.player.GCPlayerStats;
import micdoodle8.mods.galacticraft.planets.asteroids.items.AsteroidsItems;
import micdoodle8.mods.galacticraft.planets.mars.items.MarsItems;
import micdoodle8.mods.galacticraft.planets.venus.VenusItems;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.init.MPPlanets;
import stevekung.mods.moreplanets.init.MPPotions;
import stevekung.mods.moreplanets.items.capsule.CapsuleType;
import stevekung.mods.moreplanets.module.planets.diona.items.DionaItems;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.module.planets.nibiru.client.sky.CloudRendererNibiru;
import stevekung.mods.moreplanets.module.planets.nibiru.client.sky.SkyProviderNibiru;
import stevekung.mods.moreplanets.module.planets.nibiru.client.sky.WeatherRendererNibiru;
import stevekung.mods.moreplanets.module.planets.nibiru.world.gen.ChunkProviderNibiru;
import stevekung.mods.moreplanets.module.planets.nibiru.world.gen.WorldChunkManagerNibiru;
import stevekung.mods.moreplanets.util.dimension.WorldProviderMP;

public class WorldProviderNibiru extends WorldProviderMP
{
    @Override
    public Vector3 getFogColor()
    {
        float f = 0.55F - this.getStarBrightness(1.0F);
        return new Vector3(163F / 255F * f, 91F / 255F * f, 44F / 255F * f);
    }

    @Override
    public Vector3 getSkyColor()
    {
        float f = 0.6F - this.getStarBrightness(1.0F);
        return new Vector3(195 / 255F * f, 110 / 255F * f, 50 / 255F * f);
    }

    @Override
    public boolean hasSunset()
    {
        return true;
    }

    @Override
    public boolean canBlockFreeze(BlockPos pos, boolean byWater)
    {
        BiomeGenBase biomegenbase = this.getBiomeGenForCoords(pos);
        float f = biomegenbase.getFloatTemperature(pos);

        if (f > 0.15F)
        {
            return false;
        }
        else
        {
            if (pos.getY() >= 0 && pos.getY() < 256 && this.worldObj.getLightFor(EnumSkyBlock.BLOCK, pos) < 10)
            {
                IBlockState iblockstate = this.worldObj.getBlockState(pos);

                if (iblockstate == NibiruBlocks.INFECTED_WATER_FLUID_BLOCK.getDefaultState())
                {
                    if (!byWater)
                    {
                        return true;
                    }

                    boolean flag = this.isWater(pos.west()) && this.isWater(pos.east()) && this.isWater(pos.north()) && this.isWater(pos.south());

                    if (!flag)
                    {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    private boolean isWater(BlockPos pos)
    {
        return this.worldObj.getBlockState(pos).getBlock() == NibiruBlocks.INFECTED_WATER_FLUID_BLOCK;
    }

    @Override
    public boolean canSnowAt(BlockPos pos, boolean checkLight)
    {
        BiomeGenBase biomegenbase = this.getBiomeGenForCoords(pos);
        float f = biomegenbase.getFloatTemperature(pos);

        if (f > 0.15F)
        {
            return false;
        }
        else if (!checkLight)
        {
            return true;
        }
        else
        {
            if (pos.getY() >= 0 && pos.getY() < 256 && this.worldObj.getLightFor(EnumSkyBlock.BLOCK, pos) < 10)
            {
                Block block = this.worldObj.getBlockState(pos).getBlock();

                if (block.isAir(this.worldObj, pos) && NibiruBlocks.INFECTED_SNOW_LAYER.canPlaceBlockAt(this.worldObj, pos))
                {
                    return true;
                }
            }
            return false;
        }
    }

    @Override
    public boolean canDoLightning(Chunk chunk)
    {
        return true;
    }

    @Override
    public boolean canDoRainSnowIce(Chunk chunk)
    {
        return false;
    }

    @Override
    public long getDayLength()
    {
        return 144000L;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public float getStarBrightness(float partialTicks)
    {
        float angle = this.worldObj.getCelestialAngle(partialTicks);
        float value = 1.0F - (MathHelper.cos(angle * (float) Math.PI * 2.0F) * 2.0F + 0.25F);

        if (value < 0.0F)
        {
            value = 0.0F;
        }
        if (value > 0.95F)
        {
            value = 0.95F;
        }
        return value * value * 0.35F;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public float getSunBrightness(float partialTicks)
    {
        float angle = this.worldObj.getCelestialAngle(partialTicks);
        float value = 1.0F - (MathHelper.cos(angle * (float) Math.PI * 2.0F) * 2.0F + 0.2F);

        if (value < 0.0F)//day
        {
            value = 0.0F;
        }
        if (value > 1.0F)//night
        {
            value = 1.0F;
        }
        value = 1.0F - value;
        value = (float)(value * (1.0D - this.worldObj.getRainStrength(partialTicks) * 6.0F / 16.0D));
        value = (float)(value * (1.0D - this.worldObj.getThunderStrength(partialTicks) * 8.0F / 16.0D));
        return value * 1.0F;
    }

    @Override
    public double getSolarEnergyMultiplier()
    {
        return 1.25D;
    }

    @Override
    public float getGravity()
    {
        return 0.0125F;
    }

    @Override
    public double getMeteorFrequency()
    {
        return 0.0D;
    }

    @Override
    public boolean canSpaceshipTierPass(int tier)
    {
        return tier >= 6;
    }

    @Override
    public float getFallDamageModifier()
    {
        return 1.0F;
    }

    @Override
    public CelestialBody getCelestialBody()
    {
        return MPPlanets.NIBIRU;
    }

    @Override
    public float getThermalLevelModifier()
    {
        if (this.isDaytime())
        {
            return 1.5F;
        }
        else
        {
            return 0.5F;
        }
    }

    @Override
    public float getArrowGravity()
    {
        return 0.0475F;
    }

    @Override
    public int getDarkEnergyMultiplier(World world, BlockPos pos)
    {
        return 120;
    }

    @Override
    public void setup(EntityPlayerMP player)
    {
        GCPlayerStats stats = GCPlayerStats.get(player);
        SchematicRegistry.unlockNewPage(player, new ItemStack(GCItems.schematic, 1, 1)); //Knows how to build T2 rocket
        SchematicRegistry.unlockNewPage(player, new ItemStack(MarsItems.schematic, 1, 0)); //Knows how to build T3 rocket
        SchematicRegistry.unlockNewPage(player, new ItemStack(DionaItems.TIER_5_ROCKET_SCHEMATIC, 1, 0)); //Knows how to build T4 rocket
        SchematicRegistry.unlockNewPage(player, new ItemStack(DionaItems.TIER_5_ROCKET_SCHEMATIC, 1, 1)); //Knows how to build T5 rocket
        player.addPotionEffect(new PotionEffect(MPPotions.INFECTED_SPORE_PROTECTION.id, 36020, 0, true, true));
        stats.getExtendedInventory().setInventorySlotContents(0, new ItemStack(GCItems.oxMask, 1, 0));
        stats.getExtendedInventory().setInventorySlotContents(1, new ItemStack(GCItems.oxygenGear, 1, 0));
        stats.getExtendedInventory().setInventorySlotContents(2, new ItemStack(GCItems.oxTankHeavy, 1, 0));
        stats.getExtendedInventory().setInventorySlotContents(3, new ItemStack(GCItems.oxTankHeavy, 1, 0));
        stats.getExtendedInventory().setInventorySlotContents(5, new ItemStack(GCItems.basicItem, 1, 19));
        stats.getExtendedInventory().setInventorySlotContents(6, new ItemStack(VenusItems.thermalPaddingTier2, 1, 0));
        stats.getExtendedInventory().setInventorySlotContents(7, new ItemStack(VenusItems.thermalPaddingTier2, 1, 1));
        stats.getExtendedInventory().setInventorySlotContents(8, new ItemStack(VenusItems.thermalPaddingTier2, 1, 2));
        stats.getExtendedInventory().setInventorySlotContents(9, new ItemStack(VenusItems.thermalPaddingTier2, 1, 3));
        stats.getExtendedInventory().setInventorySlotContents(10, new ItemStack(VenusItems.basicItem, 1, 0));
        player.inventory.addItemStackToInventory(CapsuleType.getInfectedProtectionCapsule());
        player.inventory.addItemStackToInventory(new ItemStack(AsteroidsItems.canisterLOX));
        player.inventory.addItemStackToInventory(new ItemStack(AsteroidsItems.canisterLOX));
    }

    @Override
    protected void renderSky()
    {
        this.setSkyRenderer(new SkyProviderNibiru(this));
    }

    @Override
    protected void renderCloud()
    {
        this.setCloudRenderer(new CloudRendererNibiru());
    }

    @Override
    protected void renderWeather()
    {
        this.setWeatherRenderer(new WeatherRendererNibiru());
    }

    @Override
    public void registerWorldChunkManager()
    {
        this.worldChunkMgr = new WorldChunkManagerNibiru(this.worldObj.getSeed());
    }

    @Override
    public IChunkProvider createChunkGenerator()
    {
        return new ChunkProviderNibiru(this.worldObj, this.worldObj.getSeed());
    }
}