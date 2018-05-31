package stevekung.mods.moreplanets.client.gui;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Nullable;

import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.galaxies.Moon;
import micdoodle8.mods.galacticraft.api.galaxies.Planet;
import micdoodle8.mods.galacticraft.api.prefab.world.gen.WorldProviderSpace;
import micdoodle8.mods.galacticraft.api.world.IGalacticraftWorldProvider;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.world.WorldProvider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.utils.dimension.WorldProviderMP;
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
                entry.teleportToPlanet();
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
                    infoList.add("Basic Information: ");

                    try
                    {
                        WorldProvider provider = celestial.getWorldProvider().newInstance();

                        if (provider instanceof WorldProviderSpace)
                        {
                            WorldProviderSpace space = (WorldProviderSpace)provider;
                            
                            if (space.getDayLength() <= 0)
                            {
                                infoList.add("Day-night Cycle: " + "No day-night cycle");
                            }
                            else
                            {
                                double dayDouble = space.getDayLength() / 24000;
                                infoList.add("Day-night Cycle: " + dayDouble + (dayDouble <= 1 ? " Day" : " Days"));
                            }
                        }
                        if (provider instanceof IGalacticraftWorldProvider)
                        {
                            IGalacticraftWorldProvider gcProvider = (IGalacticraftWorldProvider)provider;
                            infoList.add("Gravity: " + gcProvider.getGravity());
                            infoList.add("Wind: " + gcProvider.getWindLevel());
//                            infoList.add("Meteor Frequency: " + gcProvider.getMeteorFrequency());
                        }
                        if (provider instanceof WorldProviderMP)
                        {
                            WorldProviderMP gcProvider = (WorldProviderMP)provider;
//                            infoList.add("Thermal: " + gcProvider.getThermalLevelModifier());
                        }

                        if (celestial instanceof Planet)
                        {
                            Planet planet = (Planet)celestial;
                            infoList.add("Solar System: " + planet.getParentSolarSystem().getLocalizedName());
                            infoList.add("Galaxy: " + planet.getParentSolarSystem().getLocalizedParentGalaxyName());
                        }
                        else if (celestial instanceof Moon)
                        {
                            Moon moon = (Moon)celestial;
                            infoList.add("Solar System: " + moon.getParentPlanet().getParentSolarSystem().getLocalizedName());
                            infoList.add("Galaxy: " + moon.getParentPlanet().getParentSolarSystem().getLocalizedParentGalaxyName());
                            infoList.add("Parent Planet: " + moon.getParentPlanet().getLocalizedName());
                        }
                    }
                    catch (Exception e)
                    {
                        infoList.add("No Basic Celestial Info");
                    }

                    for (int textSize = 0; textSize < infoList.size(); textSize++)
                    {
                        String text = infoList.get(textSize);
                        int fontHeight = this.mc.fontRenderer.FONT_HEIGHT + 2;
                        int y = 36 + fontHeight * textSize;
                        this.mc.fontRenderer.drawString(text, this.width / 2 - 16, y, 16777215);
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
}