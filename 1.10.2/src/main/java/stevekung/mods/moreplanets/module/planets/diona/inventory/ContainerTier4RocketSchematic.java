package stevekung.mods.moreplanets.module.planets.diona.inventory;

import micdoodle8.mods.galacticraft.core.inventory.SlotRocketBenchResult;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.util.math.BlockPos;
import stevekung.mods.moreplanets.module.planets.diona.recipe.Tier4RocketRecipes;
import stevekung.mods.moreplanets.util.inventory.ContainerRocketSchematicMP;

public class ContainerTier4RocketSchematic extends ContainerRocketSchematicMP
{
    public ContainerTier4RocketSchematic(InventoryPlayer inv, BlockPos pos)
    {
        super(inv);
        int change = 27;
        this.addSlotToContainer(new SlotRocketBenchResult(inv.player, this.craftMatrix, this.craftResult, 0, 142, 18 + 69 + change));
        int i;
        int l;

        // Cone
        this.addSlotToContainer(new SlotTier4RocketSchematic(this.craftMatrix, 1, 48, -8 + change, pos, inv.player));

        // Body
        for (i = 0; i < 5; ++i)
        {
            this.addSlotToContainer(new SlotTier4RocketSchematic(this.craftMatrix, 2 + i, 39, -6 + i * 18 + 16 + change, pos, inv.player));
        }

        // Body Right
        for (i = 0; i < 5; ++i)
        {
            this.addSlotToContainer(new SlotTier4RocketSchematic(this.craftMatrix, 7 + i, 57, -6 + i * 18 + 16 + change, pos, inv.player));
        }

        // Left fins
        this.addSlotToContainer(new SlotTier4RocketSchematic(this.craftMatrix, 12, 21, 64 + change, pos, inv.player));
        this.addSlotToContainer(new SlotTier4RocketSchematic(this.craftMatrix, 13, 21, 82 + change, pos, inv.player));
        this.addSlotToContainer(new SlotTier4RocketSchematic(this.craftMatrix, 14, 21, 100 + change, pos, inv.player));

        // Engine
        this.addSlotToContainer(new SlotTier4RocketSchematic(this.craftMatrix, 15, 48, 100 + change, pos, inv.player));

        // Right fins
        this.addSlotToContainer(new SlotTier4RocketSchematic(this.craftMatrix, 16, 75, 64 + change, pos, inv.player));
        this.addSlotToContainer(new SlotTier4RocketSchematic(this.craftMatrix, 17, 75, 82 + change, pos, inv.player));
        this.addSlotToContainer(new SlotTier4RocketSchematic(this.craftMatrix, 18, 75, 100 + change, pos, inv.player));

        // Addons
        for (i = 0; i < 3; i++)
        {
            this.addSlotToContainer(new SlotTier4RocketSchematic(this.craftMatrix, 19 + i, 93 + i * 26, -15 + change, pos, inv.player));
        }

        // Player inv:
        for (int i2 = 0; i2 < 3; ++i2)
        {
            for (l = 0; l < 9; ++l)
            {
                this.addSlotToContainer(new Slot(inv, l + i2 * 9 + 9, 8 + l * 18, 129 + i2 * 18 + change));
            }
        }

        for (i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(inv, i, 8 + i * 18, 18 + 169 + change));
        }
        this.onCraftMatrixChanged(this.craftMatrix);
    }

    @Override
    public void onCraftMatrixChanged(IInventory inv)
    {
        this.craftResult.setInventorySlotContents(0, Tier4RocketRecipes.findMatchingRocketRecipe(this.craftMatrix));
    }
}