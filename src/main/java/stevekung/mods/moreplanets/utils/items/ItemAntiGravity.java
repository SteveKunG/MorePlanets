package stevekung.mods.moreplanets.utils.items;

import micdoodle8.mods.galacticraft.core.TransformerHooks;
import net.minecraft.entity.item.EntityItem;

public class ItemAntiGravity extends ItemBaseMP
{
    public ItemAntiGravity(String name)
    {
        super();
        this.setUnlocalizedName(name);
    }

    @Override
    public boolean onEntityItemUpdate(EntityItem entityItem)
    {
        entityItem.motionY += TransformerHooks.getItemGravity(entityItem);
        return false;
    }
}