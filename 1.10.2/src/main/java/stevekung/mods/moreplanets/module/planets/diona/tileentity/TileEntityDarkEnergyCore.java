package stevekung.mods.moreplanets.module.planets.diona.tileentity;

import java.util.List;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.WorldServer;
import stevekung.mods.moreplanets.recipe.DarkEnergyRecipeData;
import stevekung.mods.moreplanets.util.tileentity.TileEntityRenderTickable;

public class TileEntityDarkEnergyCore extends TileEntityRenderTickable
{
    public int age = 0;
    private int produceTime;
    private boolean checkProduce = true;

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        this.produceTime = nbt.getInteger("ProduceTime");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        nbt.setInteger("ProduceTime", this.produceTime);
        return nbt;
    }

    @Override
    public void update()
    {
        super.update();
        ++this.age;
        this.age = this.age + this.worldObj.rand.nextInt(100);

        List<EntityItem> entityItemMain = this.worldObj.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(this.pos.getX(), this.pos.getY(), this.pos.getZ(), this.pos.getX() + 1.0D, this.pos.getY() + 1.5D, this.pos.getZ() + 1.0D));
        List<EntityItem> entityItemRequired = this.worldObj.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(this.pos.getX(), this.pos.getY(), this.pos.getZ(), this.pos.getX() + 1.0D, this.pos.getY() + 1.5D, this.pos.getZ() + 1.0D));

        for (EntityItem mainItem : entityItemMain)
        {
            for (DarkEnergyRecipeData data : DarkEnergyRecipeData.getRecipeList())
            {
                ItemStack mainItemStack = mainItem.getEntityItem();

                if (mainItemStack.getItem() == data.getInput().get(0).getItem())
                {
                    for (int recipeList = 1; recipeList < data.getInput().size(); recipeList++)
                    {
                        ItemStack dataStackRequired = data.getInput().get(recipeList);

                        for (EntityItem requiredItem : entityItemRequired)
                        {
                            ItemStack requiredItemStack = requiredItem.getEntityItem();

                            if (requiredItemStack.getItem() == dataStackRequired.getItem() && requiredItemStack.stackSize >= dataStackRequired.stackSize * mainItemStack.stackSize && requiredItemStack.getItemDamage() == dataStackRequired.getItemDamage())
                            {
                                for (int i = 0; i < 16; i++)
                                {
                                    mainItem.worldObj.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, mainItem.posX, mainItem.posY + 0.25D, mainItem.posZ, 0.0D, 0.0D, 0.0D);
                                    requiredItem.worldObj.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, requiredItem.posX, requiredItem.posY + 0.25D, requiredItem.posZ, 0.0D, 0.0D, 0.0D);
                                }
                                if (!this.worldObj.isRemote)
                                {
                                    if (this.checkProduce)
                                    {
                                        this.produceTime = mainItemStack.stackSize > 1 ? data.getTimeMultiplier() + mainItemStack.stackSize * 20 : data.getTimeMultiplier();
                                        this.checkProduce = false;
                                    }
                                    if (this.produceTime > 0)
                                    {
                                        this.produceTime--;
                                    }
                                    if (this.produceTime == 0)
                                    {
                                        this.worldObj.playSound(null, mainItem.posX + 0.5D, mainItem.posY + 0.5D, mainItem.posZ + 0.5D, SoundEvents.ENTITY_ZOMBIE_INFECT, SoundCategory.BLOCKS, 0.25F, (mainItem.worldObj.rand.nextFloat() - mainItem.worldObj.rand.nextFloat()) * 0.2F + 1.0F);
                                        ((WorldServer)mainItem.worldObj).spawnParticle(EnumParticleTypes.SMOKE_LARGE, mainItem.posX, mainItem.posY + 0.25D, mainItem.posZ, 24, 0.0D, 0.0D, 0.0D, 0.0D);
                                        ((WorldServer)requiredItem.worldObj).spawnParticle(EnumParticleTypes.SMOKE_LARGE, requiredItem.posX, requiredItem.posY + 0.25D, requiredItem.posZ, 24, 0.0D, 0.0D, 0.0D, 0.0D);
                                        mainItem.setEntityItemStack(new ItemStack(data.getOutput().getItem(), mainItemStack.stackSize, data.getOutput().getItemDamage()));
                                        requiredItem.setEntityItemStack(new ItemStack(requiredItemStack.getItem(), requiredItemStack.stackSize - dataStackRequired.stackSize * mainItemStack.stackSize, requiredItemStack.getItemDamage()));
                                        this.checkProduce = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}