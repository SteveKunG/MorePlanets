/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.entities.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelRocketNoFlagMP extends ModelBase
{
    public ModelRenderer insideRoof;
    public ModelRenderer rocketBase1;
    public ModelRenderer rocketBase2;
    public ModelRenderer tip;
    public ModelRenderer wing4d;
    public ModelRenderer wing4c;
    public ModelRenderer wing4e;
    public ModelRenderer wing4b;
    public ModelRenderer wing4a;
    public ModelRenderer wing1a;
    public ModelRenderer wing1b;
    public ModelRenderer wing1c;
    public ModelRenderer wing1e;
    public ModelRenderer wing1d;
    public ModelRenderer wing2e;
    public ModelRenderer wing2d;
    public ModelRenderer wing2c;
    public ModelRenderer wing2b;
    public ModelRenderer wing2a;
    public ModelRenderer wing3e;
    public ModelRenderer wing3d;
    public ModelRenderer wing3c;
    public ModelRenderer wing3b;
    public ModelRenderer wing3a;
    public ModelRenderer top1;
    public ModelRenderer top2;
    public ModelRenderer top3;
    public ModelRenderer top4;
    public ModelRenderer top5;
    public ModelRenderer top6;
    public ModelRenderer top7;
    public ModelRenderer insideBottom;
    public ModelRenderer insideLeft;
    public ModelRenderer insidetop;
    public ModelRenderer rocketBase3;
    public ModelRenderer insideRight;
    public ModelRenderer insideSideLeft;
    public ModelRenderer insideSideRight;
    public ModelRenderer insideSideBack;
    public ModelRenderer insideFloor;
    public ModelRenderer booster1;
    public ModelRenderer booster2;
    public ModelRenderer booster3;
    public ModelRenderer booster4;
    public ModelRenderer booster5;
    public ModelRenderer booster6;
    public ModelRenderer booster7;
    public ModelRenderer booster8;

    public ModelRocketNoFlagMP()
    {
        this.textureWidth = 256;
        this.textureHeight = 256;

        this.insideRoof = new ModelRenderer(this, 0, 59);
        this.insideRoof.addBox(0F, 23F, 0F, 18, 1, 18, 0F);
        this.insideRoof.setRotationPoint(-9F, -58F, -9F);
        this.insideRoof.mirror = false;

        this.rocketBase1 = new ModelRenderer(this, 0, 0);
        this.rocketBase1.addBox(0F, 24F, 0F, 14, 1, 14, 0F);
        this.rocketBase1.setRotationPoint(-7F, -1F, -7F);
        this.rocketBase1.mirror = false;

        this.rocketBase2 = new ModelRenderer(this, 0, 15);
        this.rocketBase2.addBox(0F, 24F, 0F, 12, 1, 12, 0F);
        this.rocketBase2.setRotationPoint(-6F, -2F, -6F);
        this.rocketBase2.mirror = false;

        this.tip = new ModelRenderer(this, 248, 144);
        this.tip.addBox(0F, 24F, 0F, 2, 18, 2, 0F);
        this.tip.setRotationPoint(-1F, -91F, -1F);
        this.tip.mirror = false;

        this.wing4d = new ModelRenderer(this, 66, 0);
        this.wing4d.addBox(0F, 24F, 0F, 2, 8, 2, 0F);
        this.wing4d.setRotationPoint(11F, -14F, -1F);
        this.wing4d.mirror = false;

        this.wing4c = new ModelRenderer(this, 66, 0);
        this.wing4c.addBox(0F, 24F, 0F, 2, 8, 2, 0F);
        this.wing4c.setRotationPoint(13F, -12F, -1F);
        this.wing4c.mirror = false;

        this.wing4e = new ModelRenderer(this, 66, 0);
        this.wing4e.addBox(0F, 24F, 0F, 2, 8, 2, 0F);
        this.wing4e.setRotationPoint(9.1F, -15F, -1F);
        this.wing4e.mirror = false;

        this.wing4b = new ModelRenderer(this, 66, 0);
        this.wing4b.addBox(0F, 24F, 0F, 2, 8, 2, 0F);
        this.wing4b.setRotationPoint(15F, -9F, -1F);
        this.wing4b.mirror = false;

        this.wing4a = new ModelRenderer(this, 74, 0);
        this.wing4a.addBox(0F, 24F, 0F, 1, 15, 2, 0F);
        this.wing4a.setRotationPoint(17F, -14F, -1F);
        this.wing4a.mirror = false;

        this.wing1a = new ModelRenderer(this, 60, 0);
        this.wing1a.addBox(0F, 24F, 0F, 2, 15, 1, 0F);
        this.wing1a.setRotationPoint(-1F, -14F, -18F);
        this.wing1a.mirror = false;

        this.wing1b = new ModelRenderer(this, 66, 0);
        this.wing1b.addBox(0F, 24F, 0F, 2, 8, 2, 0F);
        this.wing1b.setRotationPoint(-1F, -9F, -17F);
        this.wing1b.mirror = false;

        this.wing1c = new ModelRenderer(this, 66, 0);
        this.wing1c.addBox(0F, 24F, 0F, 2, 8, 2, 0F);
        this.wing1c.setRotationPoint(-1F, -12F, -15F);
        this.wing1c.mirror = false;

        this.wing1e = new ModelRenderer(this, 66, 0);
        this.wing1e.addBox(0F, 24F, 0F, 2, 8, 2, 0F);
        this.wing1e.setRotationPoint(-1F, -15F, -11.1F);
        this.wing1e.mirror = false;

        this.wing1d = new ModelRenderer(this, 66, 0);
        this.wing1d.addBox(0F, 24F, 0F, 2, 8, 2, 0F);
        this.wing1d.setRotationPoint(-1F, -14F, -13F);
        this.wing1d.mirror = false;

        this.wing2e = new ModelRenderer(this, 66, 0);
        this.wing2e.addBox(0F, 24F, 0F, 2, 8, 2, 0F);
        this.wing2e.setRotationPoint(-11.1F, -15F, -1F);
        this.wing2e.mirror = false;

        this.wing2d = new ModelRenderer(this, 66, 0);
        this.wing2d.addBox(0F, 24F, 0F, 2, 8, 2, 0F);
        this.wing2d.setRotationPoint(-13F, -14F, -1F);
        this.wing2d.mirror = false;

        this.wing2c = new ModelRenderer(this, 66, 0);
        this.wing2c.addBox(0F, 24F, 0F, 2, 8, 2, 0F);
        this.wing2c.setRotationPoint(-15F, -12F, -1F);
        this.wing2c.mirror = false;

        this.wing2b = new ModelRenderer(this, 66, 0);
        this.wing2b.addBox(0F, 24F, 0F, 2, 8, 2, 0F);
        this.wing2b.setRotationPoint(-17F, -9F, -1F);
        this.wing2b.mirror = false;

        this.wing2a = new ModelRenderer(this, 74, 0);
        this.wing2a.addBox(0F, 24F, 0F, 1, 15, 2, 0F);
        this.wing2a.setRotationPoint(-18F, -14F, -1F);
        this.wing2a.mirror = false;

        this.wing3e = new ModelRenderer(this, 66, 0);
        this.wing3e.addBox(0F, 24F, 0F, 2, 8, 2, 0F);
        this.wing3e.setRotationPoint(-1F, -15F, 9.1F);
        this.wing3e.mirror = false;

        this.wing3d = new ModelRenderer(this, 66, 0);
        this.wing3d.addBox(0F, 24F, 0F, 2, 8, 2, 0F);
        this.wing3d.setRotationPoint(-1F, -14F, 11F);
        this.wing3d.mirror = false;

        this.wing3c = new ModelRenderer(this, 66, 0);
        this.wing3c.addBox(0F, 24F, 0F, 2, 8, 2, 0F);
        this.wing3c.setRotationPoint(-1F, -12F, 13F);
        this.wing3c.mirror = false;

        this.wing3b = new ModelRenderer(this, 66, 0);
        this.wing3b.addBox(0F, 24F, 0F, 2, 8, 2, 0F);
        this.wing3b.setRotationPoint(-1F, -9F, 15F);
        this.wing3b.mirror = false;

        this.wing3a = new ModelRenderer(this, 60, 0);
        this.wing3a.addBox(0F, 24F, 0F, 2, 15, 1, 0F);
        this.wing3a.setRotationPoint(-1F, -14F, 17F);
        this.wing3a.mirror = false;

        this.top1 = new ModelRenderer(this, 192, 60);
        this.top1.addBox(0F, 24F, 0F, 16, 2, 16, 0F);
        this.top1.setRotationPoint(-8F, -61F, -8F);
        this.top1.mirror = false;

        this.top2 = new ModelRenderer(this, 200, 78);
        this.top2.addBox(0F, 24F, 0F, 14, 2, 14, 0F);
        this.top2.setRotationPoint(-7F, -63F, -7F);
        this.top2.mirror = false;

        this.top3 = new ModelRenderer(this, 208, 94);
        this.top3.addBox(0F, 24F, 0F, 12, 2, 12, 0F);
        this.top3.setRotationPoint(-6F, -65F, -6F);
        this.top3.mirror = false;

        this.top4 = new ModelRenderer(this, 216, 108);
        this.top4.addBox(0F, 24F, 0F, 10, 2, 10, 0F);
        this.top4.setRotationPoint(-5F, -67F, -5F);
        this.top4.mirror = false;

        this.top5 = new ModelRenderer(this, 224, 120);
        this.top5.addBox(0F, 24F, 0F, 8, 2, 8, 0F);
        this.top5.setRotationPoint(-4F, -69F, -4F);
        this.top5.mirror = false;

        this.top6 = new ModelRenderer(this, 232, 130);
        this.top6.addBox(0F, 24F, 0F, 6, 2, 6, 0F);
        this.top6.setRotationPoint(-3F, -71F, -3F);
        this.top6.mirror = false;

        this.top7 = new ModelRenderer(this, 240, 138);
        this.top7.addBox(0F, 24F, 0F, 4, 2, 4, 0F);
        this.top7.setRotationPoint(-2F, -73F, -2F);
        this.top7.mirror = false;

        this.insideBottom = new ModelRenderer(this, 85, 18);
        this.insideBottom.addBox(0F, 24F, 0F, 8, 30, 1, 0F);
        this.insideBottom.setRotationPoint(-3.9F, -35F, -8.9F);
        this.insideBottom.mirror = false;

        this.insideLeft = new ModelRenderer(this, 103, 0);
        this.insideLeft.addBox(0F, 24F, 0F, 5, 55, 1, 0F);
        this.insideLeft.setRotationPoint(3.9F, -59F, -8.9F);
        this.insideLeft.mirror = false;

        this.insidetop = new ModelRenderer(this, 85, 1);
        this.insidetop.addBox(0F, 24F, 0F, 8, 16, 1, 0F);
        this.insidetop.setRotationPoint(-3.9F, -58F, -8.9F);
        this.insidetop.mirror = false;

        this.rocketBase3 = new ModelRenderer(this, 0, 28);
        this.rocketBase3.addBox(0F, 24F, 0F, 10, 2, 10, 0F);
        this.rocketBase3.setRotationPoint(-5F, -4F, -5F);
        this.rocketBase3.mirror = false;

        this.insideRight = new ModelRenderer(this, 103, 55);
        this.insideRight.addBox(0F, 24F, 0F, 5, 54, 1, 0F);
        this.insideRight.setRotationPoint(-8.9F, -59F, -8.9F);
        this.insideRight.mirror = false;

        this.insideSideLeft = new ModelRenderer(this, 119, 73);
        this.insideSideLeft.addBox(0F, 24F, 0F, 1, 53, 17, 0F);
        this.insideSideLeft.setRotationPoint(8.1F, -58F, -7.9F);
        this.insideSideLeft.mirror = false;

        this.insideSideRight = new ModelRenderer(this, 120, 1);
        this.insideSideRight.addBox(0F, 24F, 0F, 1, 53, 16, 0F);
        this.insideSideRight.setRotationPoint(-8.9F, -58F, -7.9F);
        this.insideSideRight.mirror = false;

        this.insideSideBack = new ModelRenderer(this, 120, 142);
        this.insideSideBack.addBox(0F, 24F, 0F, 17, 53, 1, 0F);
        this.insideSideBack.setRotationPoint(-8.9F, -58F, 8.1F);
        this.insideSideBack.mirror = false;

        this.insideFloor = new ModelRenderer(this, 0, 40);
        this.insideFloor.addBox(0F, 23F, 0F, 18, 1, 18, 0F);
        this.insideFloor.setRotationPoint(-9F, -4F, -9F);
        this.insideFloor.mirror = false;

        this.booster1 = new ModelRenderer(this, 0, 103);
        this.booster1.addBox(0F, 0F, 0F, 5, 8, 5, 0F);
        this.booster1.setRotationPoint(6.5F, 12F, -10F);
        this.booster1.rotateAngleY = 0.8726646F;
        this.booster1.mirror = false;

        this.booster2 = new ModelRenderer(this, 0, 98);
        this.booster2.addBox(0F, 0F, 0F, 4, 1, 4, 0F);
        this.booster2.setRotationPoint(10.8F, 10F, 7.8F);
        this.booster2.rotateAngleY = -0.8726646F;
        this.booster2.mirror = false;

        this.booster3 = new ModelRenderer(this, 0, 103);
        this.booster3.addBox(0F, -1F, 0F, 5, 8, 5, 0F);
        this.booster3.setRotationPoint(10.8F, 12F, 7F);
        this.booster3.rotateAngleY = -0.8726646F;
        this.booster3.mirror = false;

        this.booster4 = new ModelRenderer(this, 0, 98);
        this.booster4.addBox(0F, 0F, 0F, 4, 1, 4, 0F);
        this.booster4.setRotationPoint(7.3F, 11F, -10.2F);
        this.booster4.rotateAngleY = 0.8726646F;
        this.booster4.mirror = false;

        this.booster5 = new ModelRenderer(this, 0, 103);
        this.booster5.addBox(0F, 0F, 0F, 5, 8, 5, 0F);
        this.booster5.setRotationPoint(-14F, 12F, -10F);
        this.booster5.rotateAngleY = 0.8726646F;
        this.booster5.mirror = false;

        this.booster6 = new ModelRenderer(this, 0, 98);
        this.booster6.addBox(0F, 0F, 0F, 4, 1, 4, 0F);
        this.booster6.setRotationPoint(-13.5F, 11F, -10.2F);
        this.booster6.rotateAngleY = 0.8726646F;
        this.booster6.mirror = false;

        this.booster7 = new ModelRenderer(this, 0, 103);
        this.booster7.addBox(0F, -1F, 0F, 5, 8, 5, 0F);
        this.booster7.setRotationPoint(-10F, 12F, 7F);
        this.booster7.rotateAngleY = -0.8726646F;
        this.booster7.mirror = false;

        this.booster8 = new ModelRenderer(this, 0, 98);
        this.booster8.addBox(0F, 0F, 0F, 4, 1, 4, 0F);
        this.booster8.setRotationPoint(-10.2F, 10F, 7.8F);
        this.booster8.rotateAngleY = -0.8726646F;
        this.booster8.mirror = false;
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.insideRoof.render(f5);
        this.rocketBase1.render(f5);
        this.rocketBase2.render(f5);
        this.tip.render(f5);
        this.wing4d.render(f5);
        this.wing4c.render(f5);
        this.wing4e.render(f5);
        this.wing4b.render(f5);
        this.wing4a.render(f5);
        this.wing1a.render(f5);
        this.wing1b.render(f5);
        this.wing1c.render(f5);
        this.wing1e.render(f5);
        this.wing1d.render(f5);
        this.wing2e.render(f5);
        this.wing2d.render(f5);
        this.wing2c.render(f5);
        this.wing2b.render(f5);
        this.wing2a.render(f5);
        this.wing3e.render(f5);
        this.wing3d.render(f5);
        this.wing3c.render(f5);
        this.wing3b.render(f5);
        this.wing3a.render(f5);
        this.top1.render(f5);
        this.top2.render(f5);
        this.top3.render(f5);
        this.top4.render(f5);
        this.top5.render(f5);
        this.top6.render(f5);
        this.top7.render(f5);
        this.insideBottom.render(f5);
        this.insideLeft.render(f5);
        this.insidetop.render(f5);
        this.rocketBase3.render(f5);
        this.insideRight.render(f5);
        this.insideSideLeft.render(f5);
        this.insideSideRight.render(f5);
        this.insideSideBack.render(f5);
        this.insideFloor.render(f5);
        this.booster1.render(f5);
        this.booster2.render(f5);
        this.booster3.render(f5);
        this.booster4.render(f5);
        this.booster5.render(f5);
        this.booster6.render(f5);
        this.booster7.render(f5);
        this.booster8.render(f5);
    }
}