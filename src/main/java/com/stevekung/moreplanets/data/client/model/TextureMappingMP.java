package com.stevekung.moreplanets.data.client.model;

import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.world.level.block.Block;

public class TextureMappingMP
{
    public static TextureMapping displayJar(Block displayJar)
    {
        return new TextureMapping().put(TextureSlotMP.TERRARIUM, TextureMapping.getBlockTexture(displayJar));
    }
}