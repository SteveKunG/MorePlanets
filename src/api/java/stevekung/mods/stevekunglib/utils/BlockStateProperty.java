package stevekung.mods.stevekunglib.utils;

import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing.Axis;
import net.minecraft.util.IStringSerializable;

public class BlockStateProperty
{
    /** Used for Custom TNT */
    public static final PropertyBool EXPLODE = PropertyBool.create("explode");

    /** Used for Custom Basic Leaves */
    public static final PropertyBool DECAYABLE = PropertyBool.create("decayable");
    public static final PropertyBool CHECK_DECAY = PropertyBool.create("check_decay");

    /** Used for Custom Block Facing */
    public static final PropertyDirection FACING_HORIZON = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
    public static final PropertyDirection FACING_ALL = PropertyDirection.create("facing");

    /** Used for Custom Wood Log */
    public static final PropertyEnum<EnumAxis> AXIS = PropertyEnum.create("axis", EnumAxis.class);

    /** Used for Custom Cake */
    public static final PropertyInteger BITES = PropertyInteger.create("bites", 0, 6);

    /** Used for Custom Farmland */
    public static final PropertyInteger MOISTURE = PropertyInteger.create("moisture", 0, 1);

    /** Used for Custom Snow Layer */
    public static final PropertyInteger LAYERS = PropertyInteger.create("layers", 1, 8);

    /** Used for Custom Colored Block */
    @Deprecated //TODO Remove in 1.13
    public static final PropertyEnum<EnumDyeColor> COLOR = PropertyEnum.create("color", EnumDyeColor.class);

    /** Used for Custom Plant with tickable */
    public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 15);

    public static enum EnumAxis implements IStringSerializable
    {
        X,
        Y,
        Z,
        NONE;

        public static final EnumAxis[] values = EnumAxis.values();

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
        static final int[] AXIS_LOOKUP = new int[CachedEnum.axisValues.length];

        static
        {
            AXIS_LOOKUP[Axis.X.ordinal()] = 1;
            AXIS_LOOKUP[Axis.Y.ordinal()] = 2;
            AXIS_LOOKUP[Axis.Z.ordinal()] = 3;
        }
    }

    public static class SwitchEnumAxis
    {
        public static final int[] AXIS_LOOKUP = new int[EnumAxis.values.length];

        static
        {
            AXIS_LOOKUP[EnumAxis.X.ordinal()] = 1;
            AXIS_LOOKUP[EnumAxis.Z.ordinal()] = 2;
            AXIS_LOOKUP[EnumAxis.NONE.ordinal()] = 3;
        }
    }
}