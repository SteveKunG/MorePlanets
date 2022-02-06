package stevekung.mods.moreplanets.client.renderer;

import java.io.IOException;
import java.util.*;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ICustomModelLoader;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.client.model.obj.OBJModel;
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.common.model.TRSRTransformation;

public class OBJLoaderMP implements ICustomModelLoader
{
    public static final OBJLoaderMP INSTANCE = new OBJLoaderMP();
    private IResourceManager manager;
    private final Set<String> domains = new HashSet<>();
    private final Map<ResourceLocation, IModel> cache = new HashMap<>();

    static
    {
        ((IReloadableResourceManager) Minecraft.getMinecraft().getResourceManager()).registerReloadListener(INSTANCE);
    }

    public OBJLoaderMP()
    {
        this.domains.add("moreplanets");
    }

    @Override
    public void onResourceManagerReload(IResourceManager manager)
    {
        this.manager = manager;
        this.cache.clear();
    }

    @Override
    public boolean accepts(ResourceLocation modelLocation)
    {
        return this.domains.contains(modelLocation.getNamespace()) && modelLocation.getPath().endsWith(".obj");
    }

    @Override
    public IModel loadModel(ResourceLocation modelLocation) throws IOException
    {
        IModel model = null;

        if (this.cache.containsKey(modelLocation))
        {
            model = this.cache.get(modelLocation);
        }
        else
        {
            try
            {
                ResourceLocation file = new ResourceLocation(modelLocation.getNamespace(), "models/obj/" + modelLocation.getPath());
                IResource resource = this.manager.getResource(file);

                if (resource != null)
                {
                    OBJModel.Parser parser = new OBJModel.Parser(resource, this.manager);

                    try
                    {
                        model = parser.parse().process(ImmutableMap.of("flip-v", "true"));
                    }
                    finally
                    {
                        resource.getInputStream().close();
                        this.cache.put(modelLocation, model);
                    }
                }
            }
            catch (IOException e)
            {
                throw e;
            }
        }
        if (model == null)
        {
            return ModelLoaderRegistry.getMissingModel();
        }
        return model;
    }

    public static IBakedModel getModelFromOBJ(ResourceLocation modelLocation, List<String> visibleGroups) throws IOException
    {
        return OBJLoaderMP.getModelFromOBJ(modelLocation, visibleGroups, TRSRTransformation.identity());
    }

    @SuppressWarnings("deprecation")
    public static IBakedModel getModelFromOBJ(ResourceLocation modelLocation, List<String> visibleGroups, IModelState parentState) throws IOException
    {
        IModel model = OBJLoaderMP.INSTANCE.loadModel(modelLocation);
        Function<ResourceLocation, TextureAtlasSprite> spriteFunction = location -> Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(location.toString());
        return model.bake(new OBJModel.OBJState(visibleGroups, false, parentState), DefaultVertexFormats.ITEM, spriteFunction);
    }
}