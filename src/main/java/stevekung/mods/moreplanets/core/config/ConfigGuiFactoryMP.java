package stevekung.mods.moreplanets.core.config;

import java.util.Set;

import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.IModGuiFactory;
import net.minecraftforge.fml.client.config.GuiConfig;
import stevekung.mods.moreplanets.core.MorePlanetsCore;

public class ConfigGuiFactoryMP implements IModGuiFactory
{
    @Override
    public void initialize(Minecraft mc) {}

    @Override
    public Set<RuntimeOptionCategoryElement> runtimeGuiCategories()
    {
        return null;
    }

    public static class ConfigGUI extends GuiConfig
    {
        public ConfigGUI(GuiScreen gui)
        {
            super(gui, ConfigManagerMP.getConfigElements(), MorePlanetsCore.MOD_ID, false, false, GCCoreUtil.translate("gui.config.mp"));
        }
    }

    @Override
    public boolean hasConfigGui()
    {
        return true;
    }

    @Override
    public GuiScreen createConfigGui(GuiScreen parentScreen)
    {
        return new ConfigGUI(parentScreen);
    }
}