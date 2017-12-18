package stevekung.mods.moreplanets.util;

import java.lang.reflect.Field;

public class ReflectionUtils
{
    public static <T> void set(String name, T value, Class c, T obj)
    {
        try
        {
            Field f = c.getDeclaredField(name);
            f.setAccessible(true);
            f.set(c.cast(obj), value);
        }
        catch (NoSuchFieldException e)
        {
            e.printStackTrace();
        }
        catch (SecurityException e)
        {
            e.printStackTrace();
        }
        catch (IllegalArgumentException e)
        {
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
    }

    public static <T> void set(String nameObf, String nameDeObf, T value, Class c, T obj)
    {
        try
        {
            String name = fieldExists(nameObf, c, obj) ? nameObf : nameDeObf;
            Field f = c.getDeclaredField(name);
            f.setAccessible(true);
            f.set(c.cast(obj), value);
        }
        catch (NoSuchFieldException e)
        {
            MPLog.error("None of the two fields found: " + nameObf + " and " + nameDeObf);
            e.printStackTrace();
        }
        catch (SecurityException e)
        {
            e.printStackTrace();
        }
        catch (IllegalArgumentException e)
        {
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
    }

    public static <T> void setFinal(String name, T value, Class c, T obj)
    {
        try
        {
            Field f = c.getDeclaredField(name);
            f.setAccessible(true);
            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(f, f.getModifiers() & 0xFFFFFFEF);
            f.set(c.cast(obj), value);
        }
        catch (NoSuchFieldException e)
        {
            e.printStackTrace();
        }
        catch (SecurityException e)
        {
            e.printStackTrace();
        }
        catch (IllegalArgumentException e)
        {
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
    }

    public static <T> void setFinal(String nameObf, String nameDeObf, T value, Class c, T obj)
    {
        try
        {
            String name = fieldExists(nameObf, c, obj) ? nameObf : nameDeObf;
            Field f = c.getDeclaredField(name);
            f.setAccessible(true);
            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(f, f.getModifiers() & 0xFFFFFFEF);
            f.set(c.cast(obj), value);
        }
        catch (NoSuchFieldException e)
        {
            MPLog.error("None of the two fields found: " + nameObf + " and " + nameDeObf);
            e.printStackTrace();
        }
        catch (SecurityException e)
        {
            e.printStackTrace();
        }
        catch (IllegalArgumentException e)
        {
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
    }

    private static <T> boolean fieldExists(String name, Class c, T obj)
    {
        try
        {
            c.getDeclaredField(name);
        }
        catch (NoSuchFieldException e)
        {
            return false;
        }
        catch (SecurityException e)
        {
            e.printStackTrace();
        }
        catch (IllegalArgumentException e)
        {
            e.printStackTrace();
        }
        return true;
    }

    public static <T, A> A get(String name, Class c, T obj)
    {
        try
        {
            Field f = c.getDeclaredField(name);
            f.setAccessible(true);
            return (A)f.get(obj).getClass().cast(f.get(obj));
        }
        catch (NoSuchFieldException e)
        {
            e.printStackTrace();
        }
        catch (SecurityException e)
        {
            e.printStackTrace();
        }
        catch (IllegalArgumentException e)
        {
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
        catch (NullPointerException e)
        {
            return null;
        }
        return null;
    }

    public static <T, A> A get(String nameObf, String nameDeObf, Class c, T obj)
    {
        try
        {
            String name = fieldExists(nameObf, c, obj) ? nameObf : nameDeObf;
            Field f = c.getDeclaredField(name);
            f.setAccessible(true);
            return (A)f.get(obj).getClass().cast(f.get(obj));
        }
        catch (NoSuchFieldException e)
        {
            MPLog.error("None of the two fields found: " + nameObf + " and " + nameDeObf);
            e.printStackTrace();
        }
        catch (SecurityException e)
        {
            e.printStackTrace();
        }
        catch (IllegalArgumentException e)
        {
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
        catch (NullPointerException e)
        {
            return null;
        }
        return null;
    }
}