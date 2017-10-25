package stevekung.mods.moreplanets.module.planets.chalos.tileentity;

import java.util.ArrayList;
import java.util.List;

import micdoodle8.mods.galacticraft.core.client.sounds.GCSounds;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedCreeper;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedSkeleton;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedSpider;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedZombie;
import micdoodle8.mods.galacticraft.core.tile.TileEntityDungeonSpawner;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.SoundCategory;
import stevekung.mods.moreplanets.module.planets.chalos.entity.EntityCheeseCubeEyeBoss;

public class TileEntityChalosDungeonSpawner extends TileEntityDungeonSpawner<EntityCheeseCubeEyeBoss>
{
    public TileEntityChalosDungeonSpawner()
    {
        super(EntityCheeseCubeEyeBoss.class);
    }

    @Override
    public List<Class<? extends EntityLiving>> getDisabledCreatures()
    {
        List<Class<? extends EntityLiving>> list = new ArrayList<>();
        list.add(EntityEvolvedSkeleton.class);
        list.add(EntityEvolvedZombie.class);
        list.add(EntityEvolvedSpider.class);
        list.add(EntityEvolvedCreeper.class);
        return list;
    }

    @Override
    public void playSpawnSound(Entity entity)
    {
        this.world.playSound(null, entity.posX, entity.posY, entity.posZ, GCSounds.scaryScape, SoundCategory.AMBIENT, 9.0F, 1.4F);
    }
}