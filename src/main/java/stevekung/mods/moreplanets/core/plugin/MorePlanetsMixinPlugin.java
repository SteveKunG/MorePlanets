package stevekung.mods.moreplanets.core.plugin;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.spongepowered.asm.lib.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import net.minecraft.launchwrapper.Launch;

public class MorePlanetsMixinPlugin implements IMixinConfigPlugin
{
    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName)
    {
        if (mixinClassName.equals("stevekung.mods.moreplanets.core.mixin.WorldUtilMixin"))
        {
            return this.classExists("micdoodle8.mods.galacticraft.core.util.WorldUtil");
        }
        return true;
    }

    @Override
    public List<String> getMixins()
    {
        return null;
    }

    @Override
    public String getRefMapperConfig()
    {
        return null;
    }

    @Override
    public void onLoad(String mixinPackage) {}

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {}

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}

    private boolean classExists(String name)
    {
        try
        {
            return Launch.classLoader.getClassBytes(name) != null;
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}