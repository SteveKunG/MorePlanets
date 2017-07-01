package stevekung.mods.moreplanets.module.planets.nibiru.blocks;

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
import stevekung.mods.moreplanets.util.DamageSourceMP;
import stevekung.mods.moreplanets.util.blocks.BlockVinesMP;
import stevekung.mods.moreplanets.util.blocks.EnumSortCategoryBlock;
import stevekung.mods.moreplanets.util.entity.ISpaceMob;
import stevekung.mods.moreplanets.util.entity.ISpaceMob.EnumMobType;

public class BlockInfectedVines extends BlockVinesMP
{
    public BlockInfectedVines(String name)
    {
        super();
        this.setUnlocalizedName(name);
    }

    @Override
    public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity)
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
                    if (!(inventory.armorInventory.get(i) != null && inventory.armorInventory.get(i).getItem() instanceof ItemArmor))
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
    public EnumSortCategoryBlock getBlockCategory(int meta)
    {
        return EnumSortCategoryBlock.DECORATION_NON_BLOCK;
    }

    @Override
    public String getName()
    {
        return "infected_vines";
    }
}