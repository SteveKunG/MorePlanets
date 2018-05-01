package stevekung.mods.moreplanets.module.moons.koentus.itemblocks;

import micdoodle8.mods.galacticraft.core.TransformerHooks;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemBlock;

public class ItemBlockAntiGravity extends ItemBlock
{
    public ItemBlockAntiGravity(Block block)
    {
        super(block);
    }

    @Override
    public boolean onEntityItemUpdate(EntityItem entityItem)
    {
        entityItem.motionY += TransformerHooks.getItemGravity(entityItem);
        return false;
    }
}