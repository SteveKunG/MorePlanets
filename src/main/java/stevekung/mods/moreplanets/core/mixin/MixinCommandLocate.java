package stevekung.mods.moreplanets.core.mixin;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.google.common.collect.Lists;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandLocate;

@Mixin(CommandLocate.class)
public class MixinCommandLocate
{
    @Redirect(method = "getTabCompletions", at = @At(value = "INVOKE", target = "net/minecraft/command/CommandLocate.getListOfStringsMatchingLastWord([Ljava/lang/String;[Ljava/lang/String;)Ljava/util/List;"))
    private List<String> moreplanets$addStructures(String[] args, String... possibilities)
    {
        List<String> newLoc = Lists.newArrayList("CrashedAlienShip", "AbandonedSatellite", "DionaMineshaft", "CheeseSporeHut", "NibiruDungeon", "NibiruVillage", "NibiruStronghold", "NibiruPyramid", "NibiruOceanMonument", "NibiruMineshaft", "NibiruJungleTemple", "NibiruIgloo", "RongHouse");
        List<String> mod = Arrays.stream(possibilities).collect(Collectors.toList());
        mod.addAll(newLoc);
        return CommandBase.getListOfStringsMatchingLastWord(args, mod);
    }
}