/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.koentus.blocks;

import java.util.List;
import java.util.Random;

import micdoodle8.mods.galacticraft.api.block.ITerraformableBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;
import stevekung.mods.moreplanets.core.MorePlanetsCore;

public class BlockCrystalDirt extends Block implements ITerraformableBlock
{
    private IIcon[] koentusDirtIcon;

    public BlockCrystalDirt(String name)
    {
        super(Material.ground);
        this.setStepSound(Block.soundTypeGravel);
        this.blockHardness = 0.55F;
        this.setBlockName(name);
    }

    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.koentusDirtIcon = new IIcon[2];
        this.koentusDirtIcon[0] = par1IconRegister.registerIcon("koentus:crystal_dirt");
        this.koentusDirtIcon[1] = par1IconRegister.registerIcon("koentus:coarse_crystal_dirt");
    }

    @Override
    public CreativeTabs getCreativeTabToDisplayOn()
    {
        return MorePlanetsCore.mpBlocksTab;
    }

    @Override
    public IIcon getIcon(int side, int meta)
    {
        return this.koentusDirtIcon[meta];
    }

    @Override
    public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection side, IPlantable plant)
    {
        return plant.getPlantType(world, x, y ,z) == EnumPlantType.Plains || super.canSustainPlant(world, x, y, z, side, plant);
    }

    @Override
    public Item getItemDropped(int par1, Random par2Random, int par3)
    {
        return Item.getItemFromBlock(KoentusBlocks.crystal_dirt);
    }

    @Override
    public boolean isTerraformable(World world, int x, int y, int z)
    {
        return !world.getBlock(x, y + 1, z).isOpaqueCube();
    }

    @Override
    public int getDamageValue(World world, int x, int y, int z)
    {
        return world.getBlockMetadata(x, y, z);
    }

    @Override
    public int damageDropped(int meta)
    {
        return meta;
    }

    @Override
    public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list)
    {
        for (int i = 0; i < 2; ++i)
        {
            list.add(new ItemStack(this, 1, i));
        }
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitVecX, float hitVecY, float hitVecZ)
    {
        if (player.getCurrentEquippedItem() != null)
        {
            if (player.getCurrentEquippedItem().getDisplayName().toLowerCase().contains("hoe"))
            {
                if (world.getBlockMetadata(x, y, z) == 0)
                {
                    Block farmland = KoentusBlocks.crystal_farmland;

                    world.playSoundEffect(x + 0.5F, y + 0.5F, z + 0.5F, farmland.stepSound.getStepResourcePath(), (farmland.stepSound.getVolume() + 1.0F) / 2.0F, farmland.stepSound.getPitch() * 0.8F);

                    if (!world.isRemote)
                    {
                        world.setBlock(x, y, z, farmland, 0, 2);
                    }
                    player.getCurrentEquippedItem().damageItem(1, player);
                }
                else if (world.getBlockMetadata(x, y, z) == 1)
                {
                    Block farmland = this;

                    world.playSoundEffect(x + 0.5F, y + 0.5F, z + 0.5F, farmland.stepSound.getStepResourcePath(), (farmland.stepSound.getVolume() + 1.0F) / 2.0F, farmland.stepSound.getPitch() * 0.8F);

                    if (!world.isRemote)
                    {
                        world.setBlock(x, y, z, farmland, 0, 2);
                    }
                    player.getCurrentEquippedItem().damageItem(1, player);
                }
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }
}