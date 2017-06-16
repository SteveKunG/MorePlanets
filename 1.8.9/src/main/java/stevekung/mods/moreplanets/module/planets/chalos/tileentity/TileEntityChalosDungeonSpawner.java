package stevekung.mods.moreplanets.module.planets.chalos.tileentity;

import java.util.List;

import com.google.common.collect.Lists;

import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedCreeper;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedSkeleton;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedSpider;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedZombie;
import micdoodle8.mods.galacticraft.core.tile.TileEntityDungeonSpawner;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import stevekung.mods.moreplanets.module.planets.chalos.entity.EntityCheeseCubeEyeBoss;

public class TileEntityChalosDungeonSpawner extends TileEntityDungeonSpawner
{
    public TileEntityChalosDungeonSpawner()
    {
        super(EntityCheeseCubeEyeBoss.class);
    }

    @Override
    public List<Class<? extends EntityLiving>> getDisabledCreatures()
    {
        List<Class<? extends EntityLiving>> list = Lists.newArrayList();
        list.add(EntityEvolvedSkeleton.class);
        list.add(EntityEvolvedZombie.class);
        list.add(EntityEvolvedSpider.class);
        list.add(EntityEvolvedCreeper.class);
        return list;
    }

    @Override
    public void playSpawnSound(Entity entity)
    {
        this.worldObj.playSoundAtEntity(entity, "galacticraftcore:ambience.scaryscape", 9.0F, 1.4F);
    }
}