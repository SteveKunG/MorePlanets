package com.stevekung.moreplanets.modmenu;

import com.stevekung.moreplanets.config.MorePlanetsConfig;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

import me.shedaniel.autoconfig.AutoConfig;

public class ModMenuIntegrationMP implements ModMenuApi
{
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory()
    {
        return parent -> AutoConfig.getConfigScreen(MorePlanetsConfig.class, parent).get();
    }
}