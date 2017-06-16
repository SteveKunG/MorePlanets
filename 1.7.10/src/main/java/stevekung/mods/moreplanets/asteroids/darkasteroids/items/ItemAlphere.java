package stevekung.mods.moreplanets.asteroids.darkasteroids.items;

import stevekung.mods.moreplanets.core.items.IPowerCrystal;
import stevekung.mods.moreplanets.core.items.ItemMorePlanet;

public class ItemAlphere extends ItemMorePlanet implements IPowerCrystal
{
    public ItemAlphere(String name)
    {
        super();
        this.setTextureName("mpcore:alphere");
        this.setUnlocalizedName(name);
    }

    @Override
    public boolean isPowerCrystal(int meta)
    {
        return true;
    }

    @Override
    public int getPowerCrystalBurnTime(int meta)
    {
        return 650;
    }
}