package stevekung.mods.moreplanets.util.client.gui;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.ChatAllowedCharacters;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.util.NumberUtil;

@SideOnly(Side.CLIENT)
public class GuiNumberField extends GuiTextField
{
    public GuiNumberField(int id, FontRenderer font, int x, int y, int width, int height)
    {
        super(id, font, x, y, width, height);
    }

    @Override
    public boolean textboxKeyTyped(char typedChar, int keyCode)
    {
        if (!this.isFocused())
        {
            return false;
        }
        else if (GuiScreen.isKeyComboCtrlA(keyCode))
        {
            this.setCursorPositionEnd();
            this.setSelectionPos(0);
            return true;
        }
        else if (GuiScreen.isKeyComboCtrlC(keyCode))
        {
            GuiScreen.setClipboardString(this.getSelectedText());
            return true;
        }
        else if (GuiScreen.isKeyComboCtrlV(keyCode))
        {
            if (this.isEnabled)
            {
                if (NumberUtil.isNumber(typedChar))
                {
                    this.writeText(GuiScreen.getClipboardString());
                }
            }
            return true;
        }
        else if (GuiScreen.isKeyComboCtrlX(keyCode))
        {
            GuiScreen.setClipboardString(this.getSelectedText());

            if (this.isEnabled)
            {
                this.writeText("");
            }
            return true;
        }
        else
        {
            switch (keyCode)
            {
            case 14:

                if (GuiScreen.isCtrlKeyDown())
                {
                    if (this.isEnabled)
                    {
                        this.deleteWords(-1);
                    }
                }
                else if (this.isEnabled)
                {
                    this.deleteFromCursor(-1);
                }
                return true;
            case 199:
                if (GuiScreen.isShiftKeyDown())
                {
                    this.setSelectionPos(0);
                }
                else
                {
                    this.setCursorPositionZero();
                }
                return true;
            case 203:
                if (GuiScreen.isShiftKeyDown())
                {
                    if (GuiScreen.isCtrlKeyDown())
                    {
                        this.setSelectionPos(this.getNthWordFromPos(-1, this.getSelectionEnd()));
                    }
                    else
                    {
                        this.setSelectionPos(this.getSelectionEnd() - 1);
                    }
                }
                else if (GuiScreen.isCtrlKeyDown())
                {
                    this.setCursorPosition(this.getNthWordFromCursor(-1));
                }
                else
                {
                    this.moveCursorBy(-1);
                }
                return true;
            case 205:
                if (GuiScreen.isShiftKeyDown())
                {
                    if (GuiScreen.isCtrlKeyDown())
                    {
                        this.setSelectionPos(this.getNthWordFromPos(1, this.getSelectionEnd()));
                    }
                    else
                    {
                        this.setSelectionPos(this.getSelectionEnd() + 1);
                    }
                }
                else if (GuiScreen.isCtrlKeyDown())
                {
                    this.setCursorPosition(this.getNthWordFromCursor(1));
                }
                else
                {
                    this.moveCursorBy(1);
                }
                return true;
            case 207:
                if (GuiScreen.isShiftKeyDown())
                {
                    this.setSelectionPos(this.getText().length());
                }
                else
                {
                    this.setCursorPositionEnd();
                }
                return true;
            case 211:
                if (GuiScreen.isCtrlKeyDown())
                {
                    if (this.isEnabled)
                    {
                        this.deleteWords(1);
                    }
                }
                else if (this.isEnabled)
                {
                    this.deleteFromCursor(1);
                }
                return true;
            default:
                if (ChatAllowedCharacters.isAllowedCharacter(typedChar))
                {
                    if (this.isEnabled)
                    {
                        if (NumberUtil.isNumber(typedChar))
                        {
                            this.writeText(Character.toString(typedChar));
                        }
                    }
                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
    }
}