/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.todo;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelGiantAlienWorm extends ModelBase
{
    public ModelRenderer bottom1;
    public ModelRenderer behind1;
    public ModelRenderer top1;
    public ModelRenderer top2;
    public ModelRenderer top3;
    public ModelRenderer top4;
    public ModelRenderer top5;
    public ModelRenderer side1;
    public ModelRenderer side2;
    public ModelRenderer side3;
    public ModelRenderer side4;
    public ModelRenderer side5;
    public ModelRenderer side6;
    public ModelRenderer side7;
    public ModelRenderer side8;
    public ModelRenderer side9;
    public ModelRenderer side10;
    public ModelRenderer side11;
    public ModelRenderer side12;
    public ModelRenderer side13;
    public ModelRenderer side14;
    public ModelRenderer side15;
    public ModelRenderer side16;
    public ModelRenderer side17;
    public ModelRenderer side18;
    public ModelRenderer side19;
    public ModelRenderer side20;
    public ModelRenderer side21;
    public ModelRenderer side22;
    public ModelRenderer side23;
    public ModelRenderer side24;
    public ModelRenderer side25;
    public ModelRenderer side26;
    public ModelRenderer tooth;

    public ModelGiantAlienWorm()
    {
        this.bottom1 = new ModelRenderer(this, 0, 0);
        this.bottom1.addBox(0F, 0F, -12F, 35, 1, 24, 0F);
        this.bottom1.setRotationPoint(-8F, 23F, 0F);

        this.behind1 = new ModelRenderer(this, 0, 0);
        this.behind1.addBox(0F, 0F, -11F, 1, 4, 24, 0F);
        this.behind1.setRotationPoint(26F, 20F, -1F);
        this.behind1.rotateAngleZ = -0.06981317F;

        this.top1 = new ModelRenderer(this, 0, 0);
        this.top1.addBox(0F, 0F, -12F, 12, 1, 24, 0F);
        this.top1.setRotationPoint(-3F, 10F, 0F);
        this.top1.rotateAngleZ = 0.3490658F;

        this.top2 = new ModelRenderer(this, 0, 0);
        this.top2.addBox(0F, 0F, -12F, 5, 1, 24, 0F);
        this.top2.setRotationPoint(-7.9F, 9F, 0F);
        this.top2.rotateAngleZ = 0.1745329F;

        this.top3 = new ModelRenderer(this, 0, 0);
        this.top3.addBox(0F, 0F, -12F, 12, 1, 24, 0F);
        this.top3.setRotationPoint(8F, 14F, 0F);
        this.top3.rotateAngleZ = 0.1396263F;

        this.top4 = new ModelRenderer(this, 0, 0);
        this.top4.addBox(0F, 0F, -12F, 5, 1, 24, 0F);
        this.top4.setRotationPoint(19.5F, 15.7F, 0F);
        this.top4.rotateAngleZ = 0.3141593F;

        this.top5 = new ModelRenderer(this, 0, 0);
        this.top5.addBox(0F, 0F, -12F, 4, 1, 24, 0F);
        this.top5.setRotationPoint(24F, 17.3F, 0F);
        this.top5.rotateAngleZ = 0.6981317F;

        this.side1 = new ModelRenderer(this, 0, 0);
        this.side1.addBox(0F, 0F, 0F, 16, 6, 1, 0F);
        this.side1.setRotationPoint(8F, 18F, -12F);

        this.side2 = new ModelRenderer(this, 0, 0);
        this.side2.addBox(0F, 0F, 0F, 3, 10, 1, 0F);
        this.side2.setRotationPoint(5F, 14F, -12F);

        this.side3 = new ModelRenderer(this, 0, 0);
        this.side3.addBox(0F, 0F, 0F, 3, 10, 1, 0F);
        this.side3.setRotationPoint(5F, 14F, 11F);

        this.side4 = new ModelRenderer(this, 0, 0);
        this.side4.addBox(0F, 0F, 0F, 16, 6, 1, 0F);
        this.side4.setRotationPoint(8F, 18F, 11F);

        this.side5 = new ModelRenderer(this, 0, 0);
        this.side5.addBox(0F, 0F, 0F, 3, 4, 1, 0F);
        this.side5.setRotationPoint(24F, 20F, -12F);

        this.side6 = new ModelRenderer(this, 0, 0);
        this.side6.addBox(0F, 0F, 0F, 4, 1, 1, 0F);
        this.side6.setRotationPoint(22F, 19F, 11F);

        this.side7 = new ModelRenderer(this, 0, 0);
        this.side7.addBox(0F, 0F, 0F, 3, 1, 1, 0F);
        this.side7.setRotationPoint(21.7F, 18F, 11F);

        this.side8 = new ModelRenderer(this, 0, 0);
        this.side8.addBox(0F, 0F, 0F, 3, 4, 1, 0F);
        this.side8.setRotationPoint(24F, 20F, 11F);

        this.side9 = new ModelRenderer(this, 0, 0);
        this.side9.addBox(0F, 0F, 0F, 4, 1, 1, 0F);
        this.side9.setRotationPoint(22F, 19F, -12F);

        this.side10 = new ModelRenderer(this, 0, 0);
        this.side10.addBox(0F, 0F, 0F, 3, 1, 1, 0F);
        this.side10.setRotationPoint(21.7F, 18F, -12F);

        this.side11 = new ModelRenderer(this, 0, 0);
        this.side11.addBox(0F, 0F, 0F, 12, 2, 1, 0F);
        this.side11.setRotationPoint(8F, 16F, 11F);

        this.side12 = new ModelRenderer(this, 0, 0);
        this.side12.addBox(0F, 0F, 0F, 12, 2, 1, 0F);
        this.side12.setRotationPoint(8F, 16F, -12F);

        this.side13 = new ModelRenderer(this, 0, 0);
        this.side13.addBox(0F, 0F, 0F, 7, 2, 1, 0F);
        this.side13.setRotationPoint(8F, 15F, -12F);

        this.side14 = new ModelRenderer(this, 0, 0);
        this.side14.addBox(0F, 0F, 0F, 7, 2, 1, 0F);
        this.side14.setRotationPoint(8F, 15F, 11F);

        this.side15 = new ModelRenderer(this, 0, 0);
        this.side15.addBox(0F, 0F, 0F, 6, 2, 1, 0F);
        this.side15.setRotationPoint(18F, 16F, -12F);
        this.side15.rotateAngleZ = 0.1745329F;

        this.side16 = new ModelRenderer(this, 0, 0);
        this.side16.addBox(0F, 0F, 0F, 6, 2, 1, 0F);
        this.side16.setRotationPoint(18F, 16F, 11F);
        this.side16.rotateAngleZ = 0.1745329F;

        this.side17 = new ModelRenderer(this, 0, 0);
        this.side17.addBox(0F, 0F, 0F, 2, 14, 1, 0F);
        this.side17.setRotationPoint(-5F, 10F, 11F);
        this.side17.rotateAngleZ = 0.03490658F;

        this.side18 = new ModelRenderer(this, 0, 0);
        this.side18.addBox(0F, 0F, 0F, 2, 13, 1, 0F);
        this.side18.setRotationPoint(-2F, 11.2F, 11F);
        this.side18.rotateAngleZ = 0.03490658F;

        this.side19 = new ModelRenderer(this, 0, 0);
        this.side19.addBox(0F, 0F, 0F, 2, 12, 1, 0F);
        this.side19.setRotationPoint(1F, 12.2F, 11F);
        this.side19.rotateAngleZ = 0.06981317F;

        this.side20 = new ModelRenderer(this, 0, 0);
        this.side20.addBox(0F, 0F, 0F, 2, 11, 1, 0F);
        this.side20.setRotationPoint(4F, 13.2F, 11F);
        this.side20.rotateAngleZ = 0.06981317F;

        this.side21 = new ModelRenderer(this, 0, 0);
        this.side21.addBox(0F, 0F, 0F, 4, 14, 1, 0F);
        this.side21.setRotationPoint(-8F, 10F, 11F);

        this.side22 = new ModelRenderer(this, 0, 0);
        this.side22.addBox(0F, 0F, 0F, 2, 14, 1, 0F);
        this.side22.setRotationPoint(-5F, 10F, -12F);
        this.side22.rotateAngleZ = 0.03490658F;

        this.side23 = new ModelRenderer(this, 0, 0);
        this.side23.addBox(0F, 0F, 0F, 2, 13, 1, 0F);
        this.side23.setRotationPoint(-2F, 11.2F, -12F);
        this.side23.rotateAngleZ = 0.03490658F;

        this.side24 = new ModelRenderer(this, 0, 0);
        this.side24.addBox(0F, 0F, 0F, 2, 12, 1, 0F);
        this.side24.setRotationPoint(1F, 12.2F, -12F);
        this.side24.rotateAngleZ = 0.06981317F;

        this.side25 = new ModelRenderer(this, 0, 0);
        this.side25.addBox(0F, 0F, 0F, 2, 11, 1, 0F);
        this.side25.setRotationPoint(4F, 13.2F, -12F);
        this.side25.rotateAngleZ = 0.06981317F;

        this.side26 = new ModelRenderer(this, 0, 0);
        this.side26.addBox(0F, 0F, 0F, 4, 14, 1, 0F);
        this.side26.setRotationPoint(-8F, 10F, -12F);

        this.tooth = new ModelRenderer(this, 0, 0);
        this.tooth.addBox(0F, -1F, -6F, 24, 2, 12, 0F);
        this.tooth.setRotationPoint(0F, 22F, 0F);
        this.tooth.rotateAngleZ = 0.06981317F;
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5);
        this.bottom1.render(f5);
        this.behind1.render(f5);
        this.top1.render(f5);
        this.top2.render(f5);
        this.top3.render(f5);
        this.top4.render(f5);
        this.top5.render(f5);
        this.side1.render(f5);
        this.side2.render(f5);
        this.side3.render(f5);
        this.side4.render(f5);
        this.side5.render(f5);
        this.side6.render(f5);
        this.side7.render(f5);
        this.side8.render(f5);
        this.side9.render(f5);
        this.side10.render(f5);
        this.side11.render(f5);
        this.side12.render(f5);
        this.side13.render(f5);
        this.side14.render(f5);
        this.side15.render(f5);
        this.side16.render(f5);
        this.side17.render(f5);
        this.side18.render(f5);
        this.side19.render(f5);
        this.side20.render(f5);
        this.side21.render(f5);
        this.side22.render(f5);
        this.side23.render(f5);
        this.side24.render(f5);
        this.side25.render(f5);
        this.side26.render(f5);
        this.tooth.render(f5);
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, null);
    }
}