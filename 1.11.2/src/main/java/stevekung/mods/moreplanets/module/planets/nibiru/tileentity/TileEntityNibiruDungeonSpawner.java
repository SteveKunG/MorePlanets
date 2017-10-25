package stevekung.mods.moreplanets.module.planets.nibiru.tileentity;

import java.util.List;

import com.google.common.collect.Lists;

import micdoodle8.mods.galacticraft.core.client.sounds.GCSounds;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedCreeper;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedSkeleton;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedSpider;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedZombie;
import micdoodle8.mods.galacticraft.core.tile.TileEntityDungeonSpawner;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
import stevekung.mods.moreplanets.module.planets.nibiru.entity.EntityMiniVeinFloater;

public class TileEntityNibiruDungeonSpawner extends TileEntityDungeonSpawner<EntityMiniVeinFloater>
{
    public TileEntityNibiruDungeonSpawner()
    {
        super(EntityMiniVeinFloater.class);
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
        this.world.playSound(null, entity.posX, entity.posY, entity.posZ, GCSounds.scaryScape, SoundCategory.AMBIENT, 9.0F, 1.4F);
    }

    @Override
    public void update()
    {
        super.update();
        List<EntityPlayer> playerList = this.world.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(this.getPos().getX() - 16.0D, this.getPos().getY() - 16.0D, this.getPos().getZ() - 16.0D, this.getPos().getX() + 16.0D, this.getPos().getY() + 16.0D, this.getPos().getZ() + 16.0D));

        if (this.spawned && ConfigManagerMP.enableNightVisionEffect)
        {
            for (EntityPlayer player : playerList)
            {
                player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 240));
            }
        }
    }
}