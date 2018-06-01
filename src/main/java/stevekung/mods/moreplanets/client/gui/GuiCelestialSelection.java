package stevekung.mods.moreplanets.client.gui;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Nullable;

import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.galaxies.Moon;
import micdoodle8.mods.galacticraft.api.galaxies.Planet;
import micdoodle8.mods.galacticraft.api.prefab.world.gen.WorldProviderSpace;
import micdoodle8.mods.galacticraft.api.world.ISolarLevel;
import micdoodle8.mods.galacticraft.core.util.WorldUtil;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.WorldProvider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.utils.dimension.IDarkEnergyProvider;
import stevekung.mods.stevekunglib.utils.ColorUtils;
import stevekung.mods.stevekunglib.utils.LangUtils;

@SideOnly(Side.CLIENT)
public class GuiCelestialSelection extends GuiScreen
{
    private GuiButton doneButton;
    private GuiListCelestialSelection selectionList;

    @Override
    public void initGui()
    {
        this.selectionList = new GuiListCelestialSelection(this, this.width, this.height, 32, this.height - 36, 36);
        this.doneButton = this.addButton(new GuiButton(0, this.width / 2 - 100, this.height - 32, LangUtils.translate("gui.done")));
        this.doneButton.enabled = false;
    }

    @Override
    public void handleMouseInput() throws IOException
    {
        super.handleMouseInput();
        this.selectionList.handleMouseInput();
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException
    {
        if (button.enabled)
        {
            GuiListCelestialSelectionEntry entry = this.selectionList.getSelectedCelestial();

            if (button.id == 0 && entry != null)
            {
                entry.teleport();
            }
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        List<String> infoList = new LinkedList<>();

        if (this.selectionList != null)
        {
            this.selectionList.drawScreen(mouseX, mouseY, partialTicks);
            this.drawCenteredString(this.fontRenderer, "Select Celestial", this.width / 2, 20, 16777215);

            for (int i = 0; i < this.selectionList.getSize(); ++i)
            {
                if (this.selectionList.isSelected(i))
                {
                    CelestialBody celestial = this.selectionList.getSelectedCelestial().getCelestialBody();
                    infoList.add(ColorUtils.stringToRGB("149, 200, 237").toColoredFont() + "Basic Information:");

                    try
                    {
                        if (celestial.getDimensionID() != -1)
                        {
                            WorldProvider provider = WorldUtil.getProviderForDimensionClient(celestial.getDimensionID());

                            if (provider instanceof WorldProviderSpace)
                            {
                                WorldProviderSpace space = (WorldProviderSpace)provider;
                                String thermal = "";

                                try
                                {
                                    thermal = String.valueOf(space.getThermalLevelModifier());
                                }
                                catch (Exception e)
                                {
                                    thermal = TextFormatting.RED + "Unknown";
                                }

                                if (space.getDayLength() <= 0)
                                {
                                    infoList.add(this.format(ColorUtils.stringToRGB("135, 242, 230").toColoredFont() + "Day-night Cycle:", "No day-night cycle"));
                                }
                                else
                                {
                                    double dayDouble = space.getDayLength() / 24000;

                                    if (dayDouble % 1 == 0)
                                    {
                                        int dayInt = (int)(space.getDayLength() / 24000);
                                        infoList.add(this.format(ColorUtils.stringToRGB("135, 242, 230").toColoredFont() + "Day-night Cycle:", dayInt + (dayInt == 1 ? " Day" : " Days")));
                                    }
                                    else
                                    {
                                        infoList.add(this.format(ColorUtils.stringToRGB("135, 242, 230").toColoredFont() + "Day-night Cycle:", dayDouble + (dayDouble <= 1 ? " Day" : " Days")));
                                    }
                                }
                                infoList.add(this.format(ColorUtils.stringToRGB("135, 242, 230").toColoredFont() + "Gravity:", String.valueOf(space.getGravity()) + "g"));
                                infoList.add(this.format(ColorUtils.stringToRGB("135, 242, 230").toColoredFont() + "Wind:", String.valueOf(space.getWindLevel())));
                                infoList.add(this.format(ColorUtils.stringToRGB("135, 242, 230").toColoredFont() + "Thermal:", thermal));
                                infoList.add(this.format(ColorUtils.stringToRGB("135, 242, 230").toColoredFont() + "Breathable Atmosphere:", space.hasBreathableAtmosphere()));
                                infoList.add(this.format(ColorUtils.stringToRGB("135, 242, 230").toColoredFont() + "Corrode Armor:", space.shouldCorrodeArmor()));
                                infoList.add(this.format(ColorUtils.stringToRGB("135, 242, 230").toColoredFont() + "Sound Reduction:", String.valueOf(space.getSoundVolReductionAmount() / 1.0F) + "%"));
                            }
                            else if (provider instanceof ISolarLevel)
                            {
                                ISolarLevel solar = (ISolarLevel)provider;
                                infoList.add(this.format(ColorUtils.stringToRGB("135, 242, 230").toColoredFont() + "Solar Level:", String.valueOf(solar.getSolarEnergyMultiplier()) + "%"));
                            }
                            else if (provider instanceof IDarkEnergyProvider)
                            {
                                IDarkEnergyProvider space = (IDarkEnergyProvider)provider;
                                String darkEnergy = "";

                                try
                                {
                                    darkEnergy = String.valueOf(space.getDarkEnergyMultiplier(null, null)) + "%";
                                }
                                catch (Exception e)
                                {
                                    darkEnergy = TextFormatting.RED + "Unstable Dark Energy";
                                }
                                infoList.add(this.format(ColorUtils.stringToRGB("135, 242, 230").toColoredFont() + "Dark Energy Level:", darkEnergy));
                            }

                            if (celestial instanceof Planet)
                            {
                                Planet planet = (Planet)celestial;

                                if (!planet.atmosphere.composition.isEmpty())
                                {
                                    infoList.add(this.format(ColorUtils.stringToRGB("135, 242, 230").toColoredFont() + "Gas:", String.valueOf(planet.atmosphere.composition)));//TODO Get official name of gases
                                }
                                infoList.add(this.format(ColorUtils.stringToRGB("135, 242, 230").toColoredFont() + "Solar System:", planet.getParentSolarSystem().getLocalizedName()));
                                infoList.add(this.format(ColorUtils.stringToRGB("135, 242, 230").toColoredFont() + "Galaxy:", planet.getParentSolarSystem().getLocalizedParentGalaxyName()));
                            }
                            else if (celestial instanceof Moon)
                            {
                                Moon moon = (Moon)celestial;

                                if (!moon.atmosphere.composition.isEmpty())
                                {
                                    infoList.add(this.format(ColorUtils.stringToRGB("135, 242, 230").toColoredFont() + "Gas:", String.valueOf(moon.atmosphere.composition)));//TODO Get official name of gases
                                }
                                infoList.add(this.format(ColorUtils.stringToRGB("135, 242, 230").toColoredFont() + "Solar System:", moon.getParentPlanet().getParentSolarSystem().getLocalizedName()));
                                infoList.add(this.format(ColorUtils.stringToRGB("135, 242, 230").toColoredFont() + "Galaxy:", moon.getParentPlanet().getParentSolarSystem().getLocalizedParentGalaxyName()));
                                infoList.add(this.format(ColorUtils.stringToRGB("135, 242, 230").toColoredFont() + "Parent Planet:", moon.getParentPlanet().getLocalizedName()));
                            }
                        }
                        else
                        {
                            infoList.add(TextFormatting.RED + "No Basic Celestial Info");
                        }
                    }
                    catch (Exception e) {}

                    for (int textSize = 0; textSize < infoList.size(); textSize++)
                    {
                        String text = infoList.get(textSize);
                        int fontHeight = ColorUtils.coloredFontRenderer.FONT_HEIGHT + 2;
                        int y = 36 + fontHeight * textSize;
                        ColorUtils.coloredFontRenderer.drawString(text, this.width / 2 - 16, y, 16777215);
                    }
                }
            }
        }
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
    {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        this.selectionList.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state)
    {
        super.mouseReleased(mouseX, mouseY, state);
        this.selectionList.mouseReleased(mouseX, mouseY, state);
    }

    @Override
    public boolean doesGuiPauseGame()
    {
        return false;
    }

    public void selectCelestial(@Nullable GuiListCelestialSelectionEntry entry)
    {
        this.doneButton.enabled = entry != null && entry.isReachable();
    }

    private String format(String key, String value)
    {
        return key + " " + TextFormatting.RESET + value;
    }

    private String format(String key, boolean value)
    {
        return key + " " + TextFormatting.RESET + (value ? TextFormatting.GREEN : TextFormatting.RED) + value;
    }
}