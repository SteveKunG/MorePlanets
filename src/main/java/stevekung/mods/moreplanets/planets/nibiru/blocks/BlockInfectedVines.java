package stevekung.mods.moreplanets.planets.nibiru.blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemArmor;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.utils.DamageSourceMP;
import stevekung.mods.moreplanets.utils.blocks.BlockVinesMP;
import stevekung.mods.moreplanets.utils.blocks.EnumSortCategoryBlock;
import stevekung.mods.moreplanets.utils.entity.ISpaceMob;
import stevekung.mods.moreplanets.utils.entity.ISpaceMob.EnumMobType;

public class BlockInfectedVines extends BlockVinesMP
{
    public BlockInfectedVines(String name)
    {
        this.setTranslationKey(name);
    }

    @Override
    public void onEntityCollision(World world, BlockPos pos, IBlockState state, Entity entity)
    {
        if (entity instanceof EntityLivingBase)
        {
            EntityLivingBase living = (EntityLivingBase) entity;

            if (living instanceof EntityPlayer)
            {
                EntityPlayer player = (EntityPlayer) entity;
                InventoryPlayer inventory = player.inventory;

                for (int i = 0; i < 4; i++)
                {
                    if (inventory.armorInventory.get(i).isEmpty() || !(inventory.armorInventory.get(i).getItem() instanceof ItemArmor))
                    {
                        if (!player.capabilities.isCreativeMode)
                        {
                            player.attackEntityFrom(DamageSourceMP.INFECTED_GAS, (int) (4.0D * 0.1D + 1.0D));
                            player.addPotionEffect(new PotionEffect(MobEffects.POISON, 50, 1));
                        }
                    }
                }
            }
            if (!(living instanceof EntityPlayer) && !(entity instanceof ISpaceMob && ((ISpaceMob)entity).getMobType() == EnumMobType.NIBIRU))
            {
                living.attackEntityFrom(DamageSourceMP.INFECTED_GAS, (int) (4.0D * 0.1D + 1.0D));
                living.addPotionEffect(new PotionEffect(MobEffects.POISON, 50, 1));
            }
        }
    }

    @Override
    public EnumSortCategoryBlock getBlockCategory()
    {
        return EnumSortCategoryBlock.DECORATION_NON_BLOCK;
    }
}