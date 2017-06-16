/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.europa.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import stevekung.mods.moreplanets.core.MorePlanetsCore;

public class BlockEuropaPlanks extends Block
{
    private static final String[] woodTypes = new String[] {
            "europa_planks"
    };
    private IIcon[] textures;

    public BlockEuropaPlanks(String name)
    {
        super(Material.wood);
        this.setHardness(2.0F);
        this.setStepSound(Block.soundTypeWood);
        this.setBlockName(name);
    }

    @Override
    public CreativeTabs getCreativeTabToDisplayOn()
    {
        return MorePlanetsCore.mpBlocksTab;
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.textures = new IIcon[BlockEuropaPlanks.woodTypes.length];

        for (int i = 0; i < BlockEuropaPlanks.woodTypes.length; ++i)
        {
            this.textures[i] = iconRegister.registerIcon("europa:" + BlockEuropaPlanks.woodTypes[i]);
        }
    }

    @Override
    public IIcon getIcon(int side, int meta)
    {
        if (meta < 0 || meta >= this.textures.length)
        {
            meta = 0;
        }
        return this.textures[meta];
    }

    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list)
    {
        for (int i = 0; i < BlockEuropaPlanks.woodTypes.length; ++i)
        {
            list.add(new ItemStack(this, 1, i));
        }
    }

    @Override
    public int damageDropped(int meta)
    {
        return meta;
    }
}