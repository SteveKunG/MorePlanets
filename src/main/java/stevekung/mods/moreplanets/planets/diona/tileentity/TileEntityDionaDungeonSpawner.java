package stevekung.mods.moreplanets.planets.diona.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import stevekung.mods.moreplanets.planets.diona.entity.EntityInfectedPurloniteSlimeBoss;
import stevekung.mods.moreplanets.utils.tileentity.TileEntityDungeonSpawnerMP;

public class TileEntityDionaDungeonSpawner extends TileEntityDungeonSpawnerMP<EntityInfectedPurloniteSlimeBoss>
{
    public TileEntityDionaDungeonSpawner()
    {
        super(EntityInfectedPurloniteSlimeBoss.class);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        // backward compatibility
        String bossClass = nbt.getString("bossClass");

        if (!bossClass.equals("stevekung.mods.moreplanets.planets.diona.entity.EntityInfectedPurloniteSlimeBoss"))
        {
            bossClass = "stevekung.mods.moreplanets.planets.diona.entity.EntityInfectedPurloniteSlimeBoss";
        }

        try
        {
            this.bossClass = (Class<EntityInfectedPurloniteSlimeBoss>) Class.forName(bossClass);
        }
        catch (Exception e)
        {
            if (!this.world.isRemote)
            {
                e.printStackTrace();
            }
        }
        super.readFromNBT(nbt);
    }
}