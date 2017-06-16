package stevekung.mods.moreplanets.asteroids.darkasteroids.itemblocks;

import net.minecraft.block.Block;
import stevekung.mods.moreplanets.core.itemblocks.ItemBlockBaseMP;

public class ItemBlockDarkAsteroids extends ItemBlockBaseMP
{
    public ItemBlockDarkAsteroids(Block block)
    {
        super(block);
    }

    @Override
    public String[] getBlockVariantsName()
    {
        return new String[] { "rock", "rock", "rock", "aluminum_ore", "ilmenite_ore", "iron_ore", "meteoric_iron_ore", "silicon_ore", "diamond_ore", "emerald_ore", "lapis_ore" };
    }
}