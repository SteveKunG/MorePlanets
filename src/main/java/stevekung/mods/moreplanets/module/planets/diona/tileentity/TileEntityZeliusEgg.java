package stevekung.mods.moreplanets.module.planets.diona.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class TileEntityZeliusEgg extends TileEntity implements ITickable
{
    public int age = 0;

    @Override
    public void update()
    {
        ++this.age;
        this.age = this.age + this.worldObj.rand.nextInt(100);
    }
}