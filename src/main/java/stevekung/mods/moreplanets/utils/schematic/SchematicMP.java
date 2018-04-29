package stevekung.mods.moreplanets.utils.schematic;

import micdoodle8.mods.galacticraft.api.recipe.ISchematicPage;

public abstract class SchematicMP implements ISchematicPage
{
    @Override
    public int compareTo(ISchematicPage page)
    {
        if (this.getPageID() > page.getPageID())
        {
            return 1;
        }
        else
        {
            return -1;
        }
    }
}