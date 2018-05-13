package stevekung.mods.moreplanets.utils.debug;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import com.google.common.collect.ImmutableMap;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import stevekung.mods.stevekunglib.utils.JsonUtils;

//You can include this in your mod/a pack/whatever you want, as long as that work follows the Mojang EULA.
//The original source is viewable at https://gist.github.com/williewillus/a1a899ce5b0f0ba099078d46ae3dae6e
public class JSONRecipe
{
    // This is a janky JSON generator, for porting from below 1.12 to 1.12.
    // Simply replace calls to GameRegistry.addShapeless/ShapedRecipe with these methods, which will dump it to a json in RECIPE_DIR
    // Also works with OD, replace GameRegistry.addRecipe(new ShapedOreRecipe/ShapelessOreRecipe with the same calls
    // After you are done, call generateConstants()
    // Note that in many cases, you can combine multiple old recipes into one, since you can now specify multiple possibilities for an ingredient without using the OD. See vanilla for examples.

    //Credit 1: https://gist.github.com/P3pp3rF1y/ea85fa337c9082e95336b1b61d1c3cb5 - for NBT recipe support
    //Credit 2: https://gist.github.com/Draco18s/6398d3b94a4c07ded26eb641639a2ce2 - for Advancements (recipes unlocked)

    private static File RECIPE_DIR = null;
    private static File ADVANCE_DIR = null;
    private static final Set<String> USED_OD_NAMES = new TreeSet<>();
    public static final boolean ENABLE = false;

    private static void setupDir()
    {
        if (RECIPE_DIR == null)
        {
            RECIPE_DIR = Minecraft.getMinecraft().mcDataDir.toPath().resolve("../src/main/resources/assets/moreplanets/recipes/").toFile();
        }
        if (!RECIPE_DIR.exists())
        {
            RECIPE_DIR.mkdir();
        }
    }

    private static void setupAdvDir()
    {
        if (ADVANCE_DIR == null)
        {
            ADVANCE_DIR = Minecraft.getMinecraft().mcDataDir.toPath().resolve("../src/main/resources/assets/moreplanets/advancements/recipes/").toFile();
        }
        if (!ADVANCE_DIR.exists())
        {
            ADVANCE_DIR.mkdir();
        }
    }

    public static void addShapedRecipe(ItemStack output, Object... components)
    {
        JSONRecipe.addShapedRecipe(output, null, null, components);
    }

    public static void addShapedRecipe(ItemStack output, String group, Object... components)
    {
        JSONRecipe.addShapedRecipe(output, group, null, components);
    }

    public static void addShapedRecipe(ItemStack output, String group, String altName, Object... components)
    {
        if (!ENABLE)
        {
            return;
        }

        setupDir();
        Map<String, Object> json = new LinkedHashMap<>();
        List<String> pattern = new ArrayList<>();
        int i = 0;

        while (i < components.length && components[i] instanceof String)
        {
            pattern.add(((String) components[i]).toUpperCase());
            i++;
        }

        boolean isOreDict = false;
        Map<String, Map<String, Object>> key = new HashMap<>();
        Character curKey = null;
        List<Map<String, Object>> ingredients = new ArrayList<>();

        for (; i < components.length; i++)
        {
            Object obj = components[i];

            if (obj instanceof Character)
            {
                if (curKey != null)
                {
                    throw new IllegalArgumentException("Provided two char keys in a row");
                }
                curKey = (Character) obj;
            }
            else
            {
                if (curKey == null)
                {
                    throw new IllegalArgumentException("Providing object without a char key");
                }
                if (obj instanceof String)
                {
                    isOreDict = true;
                }
                ingredients.add(serializeItemAdv(obj));
                key.put(Character.toString(Character.toUpperCase(curKey)), serializeItem(obj));
                curKey = null;
            }
        }

        if (group != null)
        {
            json.put("group", group);
        }
        json.put("type", isOreDict ? "forge:ore_shaped" : "minecraft:crafting_shaped");
        json.put("pattern", pattern);
        json.put("key", key);
        json.put("result", serializeItem(output));

        // names the json the same name as the output's registry name
        // janky I know but it works
        String suffix = output.getItem().getHasSubtypes() ? "_" + output.getItemDamage() : "";
        String name = altName != null ? altName : output.getItem().getRegistryName().getResourcePath() + suffix;
        File file = new File(RECIPE_DIR, name + ".json");

        writeAdvancements(name, ingredients);

        try (FileWriter writer = new FileWriter(file))
        {
            JsonUtils.toJson(json, writer);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void addShapelessRecipe(ItemStack output, Object... components)
    {
        JSONRecipe.addShapelessRecipe(output, null, null, components);
    }

    public static void addShapelessRecipe(ItemStack output, String group, Object... components)
    {
        JSONRecipe.addShapelessRecipe(output, group, null, components);
    }

    public static void addShapelessRecipe(ItemStack output, String group, String altName, Object... components)
    {
        if (!ENABLE)
        {
            return;
        }

        setupDir();
        Map<String, Object> json = new LinkedHashMap<>();
        boolean isOreDict = false;
        List<Map<String, Object>> ingredients = new ArrayList<>();
        List<Map<String, Object>> ingredients2 = new ArrayList<>();

        for (Object obj : components)
        {
            if (obj instanceof String)
            {
                isOreDict = true;
            }
            ingredients2.add(serializeItemAdv(obj));
            ingredients.add(serializeItem(obj));
        }

        if (group != null)
        {
            json.put("group", group);
        }
        json.put("type", isOreDict ? "forge:ore_shapeless" : "minecraft:crafting_shapeless");
        json.put("ingredients", ingredients);
        json.put("result", serializeItem(output));

        // names the json the same name as the output's registry name
        // janky I know but it works
        String suffix = output.getItem().getHasSubtypes() ? "_" + output.getItemDamage() : "";
        String name = altName != null ? altName : output.getItem().getRegistryName().getResourcePath() + suffix;
        File file = new File(RECIPE_DIR, name + ".json");

        writeAdvancements(name, ingredients2);

        try (FileWriter writer = new FileWriter(file))
        {
            JsonUtils.toJson(json, writer);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private static Map<String, Object> serializeItem(Object obj)
    {
        if (obj instanceof Item)
        {
            return serializeItem(new ItemStack((Item) obj));
        }
        if (obj instanceof Block)
        {
            return serializeItem(new ItemStack((Block) obj));
        }
        if (obj instanceof ItemStack)
        {
            ItemStack itemStack = (ItemStack) obj;
            Map<String, Object> ret = new LinkedHashMap<>();

            if (itemStack.hasTagCompound())
            {
                ret.put("type", "minecraft:item_nbt");
            }

            ret.put("item", itemStack.getItem().getRegistryName().toString());

            if (itemStack.getItem().getHasSubtypes() || itemStack.getItemDamage() != 0)
            {
                ret.put("data", itemStack.getItemDamage());
            }
            if (itemStack.hasTagCompound())
            {
                ret.put("nbt", itemStack.getTagCompound().toString());
            }
            if (itemStack.getCount() > 1)
            {
                ret.put("count", itemStack.getCount());
            }
            return ret;
        }
        if (obj instanceof String)
        {
            Map<String, Object> ret = new HashMap<>();
            USED_OD_NAMES.add((String) obj);
            ret.put("item", "#" + ((String) obj).toUpperCase(Locale.ROOT));
            return ret;
        }
        throw new IllegalArgumentException("Not a Block, Item, ItemStack, or OreDictionary Name: " + obj + " " + obj.getClass());
    }

    private static Map<String, Object> serializeItemAdv(Object obj)
    {
        if (obj instanceof Item)
        {
            return serializeItemAdv(new ItemStack((Item) obj));
        }
        if (obj instanceof Block)
        {
            return serializeItemAdv(new ItemStack((Block) obj));
        }
        if (obj instanceof ItemStack)
        {
            ItemStack itemStack = (ItemStack) obj;
            Map<String, Object> ret = new LinkedHashMap<>();

            ret.put("item", itemStack.getItem().getRegistryName().toString());

            if (itemStack.getItem().getHasSubtypes() || itemStack.getItemDamage() != 0)
            {
                ret.put("data", itemStack.getItemDamage());
            }
            if (itemStack.hasTagCompound())
            {
                ret.put("nbt", itemStack.getTagCompound().toString());
            }
            if (itemStack.getCount() > 1)
            {
                ret.put("count", itemStack.getCount());
            }
            return ret;
        }
        if (obj instanceof String)
        {
            if (OreDictionary.getOres((String) obj).isEmpty())
            {
                return null;
            }
            ItemStack itemStack = OreDictionary.getOres((String) obj).get(0);
            return serializeItemAdv(itemStack);
        }
        throw new IllegalArgumentException("Not a Block, Item, ItemStack, or OreDictionary Name: " + obj + " " + obj.getClass());
    }

    public static void writeAdvancements(String name, List<Map<String, Object>> ingredients)
    {
        if (!ENABLE)
        {
            return;
        }

        setupAdvDir();
        Map<String, Object> json = new LinkedHashMap<>();
        Map<String, Object> rewards = new LinkedHashMap<>();
        List<String> recipes = new LinkedList<>();
        Map<String, Map<String, Object>> criteria = new LinkedHashMap<>();
        Map<String, Object> has_item = new LinkedHashMap<>();
        Map<String, Object> conditions = new LinkedHashMap<>();
        Map<String, Object> conditions2 = new LinkedHashMap<>();
        Map<String, Object> has_the_recipe = new LinkedHashMap<>();
        LinkedList<LinkedList<String>> requirements = new LinkedList<>();
        LinkedList<String> reqs = new LinkedList<>();

        json.put("parent", "minecraft:recipes/root");
        recipes.add("moreplanets:" + name);
        rewards.put("recipes", recipes);

        has_item.put("trigger", "minecraft:inventory_changed");
        conditions.put("items", ingredients);
        conditions2.put("recipe", "moreplanets:" + name);
        has_the_recipe.put("trigger", "minecraft:recipe_unlocked");
        has_the_recipe.put("conditions", conditions2);

        has_item.put("conditions", conditions);
        criteria.put("has_item", has_item);
        criteria.put("has_the_recipe", has_the_recipe);

        reqs.add("has_item");
        reqs.add("has_the_recipe");
        requirements.add(reqs);

        json.put("rewards", rewards);
        json.put("criteria", criteria);
        json.put("requirements", requirements);

        File file = new File(ADVANCE_DIR, name + ".json");

        try (FileWriter writer = new FileWriter(file))
        {
            JsonUtils.toJson(json, writer);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    // Call this after you are done generating
    public static void generateConstants()
    {
        if (!ENABLE)
        {
            return;
        }

        setupDir();
        List<Map<String, Object>> json = new ArrayList<>();

        USED_OD_NAMES.forEach(name ->
        {
            Map<String, Object> entry = new HashMap<>();
            entry.put("name", name.toUpperCase(Locale.ROOT));
            entry.put("ingredient", ImmutableMap.of("type", "forge:ore_dict", "ore", name));
            json.add(entry);
        });

        try (FileWriter w = new FileWriter(new File(RECIPE_DIR, "_constants.json")))
        {
            JsonUtils.toJson(json, w);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    // EXPERIMENTAL: JSONs generated will definitely not work in 1.12.2 and below, and may not even work when 1.13 comes out
    // When Forge 1.13 is fully released, I will fix this to be correct
    // Usage: Replace calls to GameRegistry.addSmelting with this
    public static void addSmelting(ItemStack in, ItemStack result, float xp)
    {
        addSmelting(in, result, xp, 200);
    }

    public static void addSmelting(ItemStack in, ItemStack result, float xp, int cookTime)
    {
        setupDir();
        Map<String, Object> json = new HashMap<>();
        json.put("type", "minecraft:smelting");
        json.put("ingredient", serializeItem(in));
        json.put("result", serializeItem(result)); // vanilla jsons just have a string?
        json.put("experience", xp);
        json.put("cookingtime", cookTime);

        // names the json the same name as the output's registry name
        // janky I know but it works
        String suffix = result.getItem().getHasSubtypes() ? "_" + result.getItemDamage() : "";
        File file = new File(RECIPE_DIR, result.getItem().getRegistryName().getResourcePath() + suffix + ".json");

        try (FileWriter writer = new FileWriter(file))
        {
            JsonUtils.toJson(json, writer);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}