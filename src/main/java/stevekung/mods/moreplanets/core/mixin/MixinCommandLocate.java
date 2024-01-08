package stevekung.mods.moreplanets.core.mixin;

import java.util.Arrays;

import org.apache.commons.lang3.ArrayUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import net.minecraft.command.CommandLocate;

@Mixin(CommandLocate.class)
public class MixinCommandLocate
{
    @ModifyArg(method = "getTabCompletions", at = @At(value = "INVOKE", target = "net/minecraft/command/CommandLocate.getListOfStringsMatchingLastWord([Ljava/lang/String;[Ljava/lang/String;)Ljava/util/List;"), index = 1)
    private String[] moreplanets$addStructures(String[] original)
    {
        String[] moreplanetsStructures = new String[] { "CrashedAlienShip", "AbandonedSatellite", "DionaMineshaft", "CheeseSporeHut", "NibiruDungeon", "NibiruVillage", "NibiruStronghold", "NibiruPyramid", "NibiruOceanMonument", "NibiruMineshaft", "NibiruJungleTemple", "NibiruIgloo", "RongHouse" };
        String[] modified = ArrayUtils.addAll(original, moreplanetsStructures);
        Arrays.sort(modified);
        return modified;
    }
}