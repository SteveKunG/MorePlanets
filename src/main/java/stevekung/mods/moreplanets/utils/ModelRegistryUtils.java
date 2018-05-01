package stevekung.mods.moreplanets.utils;

import java.util.List;

import micdoodle8.mods.galacticraft.core.wrappers.ModelTransformWrapper;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.client.renderer.OBJLoaderMP;
import stevekung.mods.moreplanets.utils.client.model.ModelBipedTranslucent;

@SideOnly(Side.CLIENT)
public class ModelRegistryUtils
{
    public static ModelBiped getTranclucentArmorModel(EntityEquipmentSlot armorSlot, ModelBiped defaultModel)
    {
        if (armorSlot != EntityEquipmentSlot.LEGS)
        {
            ModelBipedTranslucent model = new ModelBipedTranslucent(1.0F);
            model.isChild = false;
            model.setModelAttributes(defaultModel);
            return model;
        }
        else
        {
            ModelBipedTranslucent model = new ModelBipedTranslucent(0.5F);
            model.isChild = false;
            model.setModelAttributes(defaultModel);
            return model;
        }
    }

    public static void registerOBJModel(ModelBakeEvent event, String name, String file, List<String> visibleGroups, Class<? extends ModelTransformWrapper> clazz, IModelState parentState)
    {
        ModelResourceLocation modelResourceLocation = new ModelResourceLocation("moreplanets:" + name, "inventory");
        IBakedModel object = event.getModelRegistry().getObject(modelResourceLocation);

        if (object != null)
        {
            IBakedModel newModel;

            try
            {
                newModel = OBJLoaderMP.getModelFromOBJ(new ResourceLocation("moreplanets:" + file + ".obj"), visibleGroups, parentState);

                if (clazz != null)
                {
                    newModel = clazz.getConstructor(IBakedModel.class).newInstance(newModel);
                }
            }
            catch (Exception e)
            {
                throw new RuntimeException(e);
            }
            event.getModelRegistry().putObject(modelResourceLocation, newModel);
        }
    }
}