package stevekung.mods.moreplanets.utils;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class StackSorted
{
    private final Item item;

    public StackSorted(Block block)
    {
        this(Item.getItemFromBlock(block));
    }

    public StackSorted(Item item)
    {
        this.item = item;
    }

    public Item getItem()
    {
        return this.item;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (!(obj instanceof StackSorted))
        {
            return false;
        }
        if (obj == this)
        {
            return true;
        }
        StackSorted other = (StackSorted) obj;
        return new EqualsBuilder().append(this.item, other.item).isEquals();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(this.item).toHashCode();
    }

    @Override
    public String toString()
    {
        return "Item:(" + this.item + ")";
    }
}