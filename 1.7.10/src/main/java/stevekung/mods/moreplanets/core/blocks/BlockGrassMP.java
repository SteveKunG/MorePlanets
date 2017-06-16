/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.blocks;

import micdoodle8.mods.galacticraft.api.block.ITerraformableBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;
import stevekung.mods.moreplanets.core.blocks.base.BlockBaseMP;
import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;
import stevekung.mods.moreplanets.planets.fronos.blocks.IFronosGrass;
import stevekung.mods.moreplanets.planets.nibiru.blocks.BlockInfectedGrass;
import stevekung.mods.moreplanets.planets.nibiru.blocks.NibiruBlocks;

public abstract class BlockGrassMP extends BlockBaseMP implements ITerraformableBlock
{
    public BlockGrassMP()
    {
        super(Material.grass);
        this.setTickRandomly(true);
        this.setStepSound(Block.soundTypeGrass);
        this.setHardness(0.6F);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitVecX, float hitVecY, float hitVecZ)
    {
        if (player.getCurrentEquippedItem() != null)
        {
            if (player.getCurrentEquippedItem().getDisplayName().toLowerCase().contains("hoe"))
            {
                Block farmland = this.getFarmlandBlock();

                world.playSoundEffect(x + 0.5F, y + 0.5F, z + 0.5F, farmland.stepSound.getStepResourcePath(), (farmland.stepSound.getVolume() + 1.0F) / 2.0F, farmland.stepSound.getPitch() * 0.8F);

                if (!world.isRemote)
                {
                    world.setBlock(x, y, z, farmland, 0, 2);
                }
                player.getCurrentEquippedItem().damageItem(1, player);
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

    @Override
    public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection side, IPlantable plant)
    {
        Block block = plant.getPlant(world, x, y, z);

        if (this instanceof IFronosGrass)
        {
            return block == FronosBlocks.candy_flower || block == FronosBlocks.dandelion || block == FronosBlocks.fronos_flower || block == FronosBlocks.fronos_sapling || block == FronosBlocks.fronos_tall_grass || block == FronosBlocks.poppy || block == Blocks.deadbush || block == Blocks.reeds || plant.getPlantType(world, x, y ,z) == EnumPlantType.Plains;
        }
        if (this instanceof BlockInfectedGrass)
        {
            return block == NibiruBlocks.nibiru_sapling || plant.getPlantType(world, x, y ,z) == EnumPlantType.Plains;
        }
        return true && super.canSustainPlant(world, x, y, z, side, plant) || plant.getPlantType(world, x, y ,z) == EnumPlantType.Plains;
    }

    @Override
    public boolean isTerraformable(World world, int x, int y, int z)
    {
        return !world.getBlock(x, y + 1, z).isOpaqueCube();
    }

    public abstract Block getFarmlandBlock();
}