package stevekung.mods.moreplanets.core.config;

import java.util.Set;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fml.client.IModGuiFactory;
import net.minecraftforge.fml.client.config.GuiConfig;
import stevekung.mods.moreplanets.core.MorePlanetsCore;

public class ConfigGuiFactoryMP implements IModGuiFactory
{
    @Override
    public void initialize(Minecraft mc) {}

    @Override
    public Class<? extends GuiScreen> mainConfigGuiClass()
    {
        return ConfigGUI.class;
    }

    @Override
    public Set<RuntimeOptionCategoryElement> runtimeGuiCategories()
    {
        return null;
    }

    @Override
    public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement element)
    {
        return null;
    }

    public static class ConfigGUI extends GuiConfig
    {
        public ConfigGUI(GuiScreen gui)
        {
            super(gui, ConfigManagerMP.getConfigElements(), MorePlanetsCore.MOD_ID, false, false, StatCollector.translateToLocal("gui.config.mp"));
        }
    }
}