package stevekung.mods.moreplanets.util.helper;

import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing.Axis;
import net.minecraft.util.IStringSerializable;
import stevekung.mods.moreplanets.util.CachedEnumUtil;

public class BlockStateHelper
{
    /** Used for Custom TNT */
    public static PropertyBool EXPLODE = PropertyBool.create("explode");

    /** Used for Custom Basic Leaves */
    public static PropertyBool DECAYABLE = PropertyBool.create("decayable");
    public static PropertyBool CHECK_DECAY = PropertyBool.create("check_decay");

    /** Used for Custom Block Facing */
    public static PropertyDirection FACING_HORIZON = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
    public static PropertyDirection FACING_ALL = PropertyDirection.create("facing");

    /** Used for Custom Wood Log */
    public static PropertyEnum<EnumAxis> AXIS = PropertyEnum.create("axis", EnumAxis.class);

    /** Used for Custom Cake */
    public static PropertyInteger BITES = PropertyInteger.create("bites", 0, 6);

    /** Used for Custom Farmland */
    public static PropertyInteger MOISTURE = PropertyInteger.create("moisture", 0, 1);

    /** Used for Custom Snow Layer */
    public static PropertyInteger LAYERS = PropertyInteger.create("layers", 1, 8);

    /** Used for Custom Colored Block */
    public static PropertyEnum<EnumDyeColor> COLOR = PropertyEnum.create("color", EnumDyeColor.class);

    /** Used for Custom Plant with tickable */
    public static PropertyInteger AGE = PropertyInteger.create("age", 0, 15);

    public static enum EnumAxis implements IStringSerializable
    {
        X,
        Y,
        Z,
        NONE;

        private static EnumAxis[] values = EnumAxis.values();

        public static EnumAxis[] valuesCached()
        {
            return EnumAxis.values;
        }

        @Override
        public String toString()
        {
            return this.name().toLowerCase();
        }

        @Override
        public String getName()
        {
            return this.name().toLowerCase();
        }

        public static EnumAxis fromFacingAxis(Axis axis)
        {
            switch (SwitchAxis.AXIS_LOOKUP[axis.ordinal()])
            {
            case 1:
                return X;
            case 2:
                return Y;
            case 3:
                return Z;
            default:
                return NONE;
            }
        }
    }

    public static class SwitchAxis
    {
        static int[] AXIS_LOOKUP = new int[CachedEnumUtil.valuesAxisCached().length];

        static
        {
            try
            {
                AXIS_LOOKUP[Axis.X.ordinal()] = 1;
            }
            catch (NoSuchFieldError e) {}

            try
            {
                AXIS_LOOKUP[Axis.Y.ordinal()] = 2;
            }
            catch (NoSuchFieldError e) {}

            try
            {
                AXIS_LOOKUP[Axis.Z.ordinal()] = 3;
            }
            catch (NoSuchFieldError e) {}
        }
    }

    public static class SwitchEnumAxis
    {
        public static int[] AXIS_LOOKUP = new int[EnumAxis.valuesCached().length];

        static
        {
            try
            {
                AXIS_LOOKUP[EnumAxis.X.ordinal()] = 1;
            }
            catch (NoSuchFieldError e) {}

            try
            {
                AXIS_LOOKUP[EnumAxis.Z.ordinal()] = 2;
            }
            catch (NoSuchFieldError e) {}

            try
            {
                AXIS_LOOKUP[EnumAxis.NONE.ordinal()] = 3;
            }
            catch (NoSuchFieldError e) {}
        }
    }
}