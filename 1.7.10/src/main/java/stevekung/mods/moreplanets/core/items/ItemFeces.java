package stevekung.mods.moreplanets.core.items;

import java.util.List;

import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.BonemealEvent;

public class ItemFeces extends ItemMorePlanet
{
    private static int DECOMPOSE = 1800 * 20;

    public ItemFeces(String name)
    {
        super();
        this.setTextureName("mpcore:feces");
        this.setMaxStackSize(1);
        this.setUnlocalizedName(name);
    }

    @Override
    public void onUpdate(ItemStack itemStack, World world, Entity entity, int slot, boolean selected)
    {
        if (!world.isRemote)
        {
            if (itemStack.hasTagCompound())
            {
                float time = itemStack.getTagCompound().getFloat("Decompose");

                if (time >= 0.5F)
                {
                    time -= 1.0F;
                    itemStack.getTagCompound().setFloat("Decompose", time);
                }
            }
            else
            {
                itemStack.setTagCompound(new NBTTagCompound());
                itemStack.getTagCompound().setFloat("Decompose", DECOMPOSE);
            }
        }
    }

    @Override
    public void onCreated(ItemStack itemStack, World world, EntityPlayer player)
    {
        if (!itemStack.hasTagCompound())
        {
            itemStack.setTagCompound(new NBTTagCompound());
        }
        itemStack.getTagCompound().setFloat("Decompose", DECOMPOSE);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean advanced)
    {
        float time = 0.0F;

        if (itemStack.hasTagCompound())
        {
            float time1 = itemStack.getTagCompound().getFloat("Decompose");
            time = Math.round(time1 / 10.0F) / 2.0F;
        }
        else
        {
            time = 1800.0F;
        }
        list.add(StatCollector.translateToLocal("item.decompose.name") + " " + (int)time + StatCollector.translateToLocal("gui.seconds"));
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int facing, float hitX, float hitY, float hitZ)
    {
        if (!player.canPlayerEdit(x, y, z, facing, itemStack))
        {
            return false;
        }
        else
        {
            if (this.applyBonemeal(itemStack, world, x, y, z, player))
            {
                if (!world.isRemote)
                {
                    world.playAuxSFX(2005, x, y, z, 0);
                }
                return true;
            }
            return false;
        }
    }

    private boolean applyBonemeal(ItemStack itemStack, World world, int x, int y, int z, EntityPlayer player)
    {
        Block block = world.getBlock(x, y, z);
        BonemealEvent event = new BonemealEvent(player, world, block, x, y, z);

        if (MinecraftForge.EVENT_BUS.post(event))
        {
            return false;
        }

        if (event.getResult() == Result.ALLOW)
        {
            return true;
        }

        if (block instanceof IGrowable)
        {
            IGrowable igrowable = (IGrowable)block;

            if (igrowable.func_149851_a(world, x, y, z, world.isRemote))
            {
                if (!world.isRemote)
                {
                    if (igrowable.func_149852_a(world, world.rand, x, y, z))
                    {
                        igrowable.func_149853_b(world, world.rand, x, y, z);
                    }
                    if (itemStack.getTagCompound().getFloat("Decompose") == 0.0F)
                    {
                        --itemStack.stackSize;
                    }
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean onEntityItemUpdate(EntityItem item)
    {
        if (item.getEntityItem().hasTagCompound())
        {
            float time = item.getEntityItem().getTagCompound().getFloat("Decompose");

            if (time >= 0.5F)
            {
                time -= 1.0F;
                item.getEntityItem().getTagCompound().setFloat("Decompose", time);
            }
        }
        else
        {
            item.getEntityItem().setTagCompound(new NBTTagCompound());
            item.getEntityItem().getTagCompound().setFloat("Decompose", DECOMPOSE);
        }
        return false;
    }
}