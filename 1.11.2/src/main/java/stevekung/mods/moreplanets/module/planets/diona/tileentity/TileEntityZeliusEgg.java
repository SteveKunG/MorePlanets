package stevekung.mods.moreplanets.module.planets.diona.tileentity;

import stevekung.mods.moreplanets.util.tileentity.TileEntityRenderTickable;

public class TileEntityZeliusEgg extends TileEntityRenderTickable
{
    private boolean initialize = true;

    @Override
    public void update()
    {
        super.update();

        if (this.initialize)
        {
            this.renderTicks = this.renderTicks + this.world.rand.nextInt(100);
            this.initialize = false;
        }
    }
}