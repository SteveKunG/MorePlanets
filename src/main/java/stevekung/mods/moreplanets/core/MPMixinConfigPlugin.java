package stevekung.mods.moreplanets.core;

import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

public class MPMixinConfigPlugin implements IMixinConfigPlugin
{
    private static final Logger LOGGER = LogManager.getLogger();
    private static boolean foundMagmaServer;

    static
    {
        foundMagmaServer = findAndDetectModClass("org.magmafoundation.magma.Magma", "Magma Server");
    }

    @Override
    public void onLoad(String mixinPackage)
    {}

    @Override
    public String getRefMapperConfig()
    {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName)
    {
        if (mixinClassName.equals("stevekung.mods.moreplanets.core.mixin.MixinEntity"))
        {
            return !foundMagmaServer;
        }
        return true;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets)
    {}

    @Override
    public List<String> getMixins()
    {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo)
    {}

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo)
    {}

    static boolean findAndDetectModClass(String classPath, String modName)
    {
        boolean found = Thread.currentThread().getContextClassLoader().getResourceAsStream(classPath.replace('.', '/') + ".class") != null;
        LOGGER.info(found ? modName + " detected!" : modName + " not detected!");
        return found;
    }
}