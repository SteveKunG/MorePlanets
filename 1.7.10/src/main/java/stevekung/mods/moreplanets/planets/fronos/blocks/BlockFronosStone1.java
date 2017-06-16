/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.blocks;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import micdoodle8.mods.galacticraft.api.block.IDetectableResource;
import micdoodle8.mods.galacticraft.core.items.GCItems;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import stevekung.mods.moreplanets.core.blocks.BlockBasicMP;

public class BlockFronosStone1 extends BlockBasicMP implements IDetectableResource
{
    private IIcon[] fronosBlockIcon;

    public BlockFronosStone1(String name)
    {
        super(Material.rock);
        this.setHardness(3.0F);
        this.setResistance(5.0F);
        this.setBlockName(name);
    }

    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.fronosBlockIcon = new IIcon[16];
        this.fronosBlockIcon[0] = par1IconRegister.registerIcon("fronos:fronos_gold_ore");
        this.fronosBlockIcon[1] = par1IconRegister.registerIcon("fronos:fronos_diamond_ore");
        this.fronosBlockIcon[2] = par1IconRegister.registerIcon("fronos:fronos_emerald_ore");
        this.fronosBlockIcon[3] = par1IconRegister.registerIcon("fronos:fronos_silicon_ore");
    }

    @Override
    public IIcon getIcon(int side, int meta)
    {
        return this.fronosBlockIcon[meta];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list)
    {
        for (int i = 0; i < 4; ++i)
        {
            list.add(new ItemStack(this, 1, i));
        }
    }

    @Override
    public Item getItemDropped(int meta, Random par2Random, int fortune)
    {
        if (meta == 1)
        {
            return Items.diamond;
        }
        if (meta == 2)
        {
            return Items.emerald;
        }
        if (meta == 3)
        {
            return GCItems.basicItem;
        }
        return Item.getItemFromBlock(this);
    }

    @Override
    public int damageDropped(int meta)
    {
        if (meta == 1 || meta == 2)
        {
            return 0;
        }
        if (meta == 3)
        {
            return 2;
        }
        return meta;
    }

    @Override
    public boolean isValueable(int metadata)
    {
        return true;
    }

    @Override
    public int getDungeonSpawnerMetadata()
    {
        return -1;
    }

    @Override
    public TileEntity getDungeonSpawner()
    {
        return null;
    }
}