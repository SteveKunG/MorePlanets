package stevekung.mods.moreplanets.planets.nibiru.tileentity;

import java.util.List;

import micdoodle8.mods.galacticraft.planets.venus.entities.EntityJuicer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.math.AxisAlignedBB;
import stevekung.mods.moreplanets.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.utils.tileentity.TileEntityRenderTickable;

public class TileEntityJuicerEgg extends TileEntityRenderTickable
{
    @Override
    public void update()
    {
        super.update();

        if (!this.world.isRemote)
        {
            double radius = 1.05D;
            double radiusPlayer = 5.0D;
            List<Entity> list = this.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(this.pos.getX() - radius, this.pos.getY() - radius, this.pos.getZ() - radius, this.pos.getX() + radius, this.pos.getY() + radius, this.pos.getZ() + radius));
            List<EntityPlayer> playerList = this.world.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(this.pos.getX() - radiusPlayer, this.pos.getY() - radiusPlayer, this.pos.getZ() - radiusPlayer, this.pos.getX() + radiusPlayer, this.pos.getY() + radiusPlayer, this.pos.getZ() + radiusPlayer));

            if (!list.isEmpty())
            {
                for (Entity entity : list)
                {
                    if (entity instanceof EntityArrow)
                    {
                        EntityArrow arrow = (EntityArrow) entity;

                        if (arrow.inTile == NibiruBlocks.JUICER_EGG)
                        {
                            arrow.setDead();
                            this.world.destroyBlock(this.pos, false);

                            if (this.world.rand.nextInt(5) == 0)
                            {
                                EntityJuicer juicer = new EntityJuicer(this.world);
                                juicer.setLocationAndAngles(this.pos.getX() + 0.5D, this.pos.getY() + 1.0D, this.pos.getZ() + 0.5D, 0.0F, 0.0F);
                                this.world.spawnEntity(juicer);
                            }

                            if (this.world.rand.nextInt(10) == 0)
                            {
                                if (!playerList.isEmpty())
                                {
                                    for (EntityPlayer player : playerList)
                                    {
                                        EntityJuicer juicer = new EntityJuicer(this.world);
                                        juicer.setLocationAndAngles(this.pos.getX() + 0.5D, this.pos.getY() + 1.0D, this.pos.getZ() + 0.5D, 0.0F, 0.0F);
                                        this.world.spawnEntity(juicer);
                                        juicer.startRiding(player);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}