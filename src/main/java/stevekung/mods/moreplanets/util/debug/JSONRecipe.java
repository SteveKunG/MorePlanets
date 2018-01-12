package stevekung.mods.moreplanets.util.debug;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;

//You can include this in your mod/a pack/whatever you want, as long as that work follows the Mojang EULA.
//The original source is viewable at https://gist.github.com/williewillus/a1a899ce5b0f0ba099078d46ae3dae6e
public class JSONRecipe
{
    // This is a janky JSON generator, for porting from below 1.12 to 1.12.
    // Simply replace calls to GameRegistry.addShapeless/ShapedRecipe with these methods, which will dump it to a json in RECIPE_DIR
    // Also works with OD, replace GameRegistry.addRecipe(new ShapedOreRecipe/ShapelessOreRecipe with the same calls

    //Credit 1: https://gist.github.com/P3pp3rF1y/ea85fa337c9082e95336b1b61d1c3cb5 - for NBT recipe support
    //Credit 2: https://gist.github.com/Draco18s/6398d3b94a4c07ded26eb641639a2ce2 - for Advancements (recipes unlocked)

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static File RECIPE_DIR = null;
    private static File ADVANCE_DIR = null;
    private static final Set<String> USED_OD_NAMES = new TreeSet<>();
    private static final boolean ENABLE = false;

    private static void setupDir()
    {
        if (RECIPE_DIR == null)
        {
            RECIPE_DIR = ConfigManagerMP.config.getConfigFile().toPath().resolve("../recipes/").toFile();
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
            ADVANCE_DIR = ConfigManagerMP.config.getConfigFile().toPath().resolve("../advancements/").toFile();
        }
        if (!ADVANCE_DIR.exists())
        {
            ADVANCE_DIR.mkdir();
        }
    }

    public static void addShapedRecipe(ItemStack result, Object... components)
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

        for (; i < components.length; i++)
        {
            Object o = components[i];

            if (o instanceof Character)
            {
                if (curKey != null)
                {
                    throw new IllegalArgumentException("Provided two char keys in a row");
                }
                curKey = (Character) o;
            }
            else
            {
                if (curKey == null)
                {
                    throw new IllegalArgumentException("Providing object without a char key");
                }
                if (o instanceof String)
                {
                    isOreDict = true;
                }
                key.put(Character.toString(Character.toUpperCase(curKey)), serializeItem(o));
                curKey = null;
            }
        }

        json.put("type", isOreDict ? "forge:ore_shaped" : "minecraft:crafting_shaped");
        json.put("pattern", pattern);
        json.put("key", key);
        json.put("result", serializeItem(result));

        // names the json the same name as the output's registry name
        // repeatedly adds _alt if a file already exists
        // janky I know but it works
        String suffix = result.getItem().getHasSubtypes() ? "_" + result.getItemDamage() : "";
        File f = new File(RECIPE_DIR, result.getItem().getRegistryName().getResourcePath() + suffix + ".json");

        while (f.exists())
        {
            suffix += "_alt";
            f = new File(RECIPE_DIR, result.getItem().getRegistryName().getResourcePath() + suffix + ".json");
        }

        writeAdvancements(result.getItem().getRegistryName().getResourcePath() + suffix);

        try (FileWriter w = new FileWriter(f))
        {
            GSON.toJson(json, w);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void addShapelessRecipe(ItemStack result, Object... components)
    {
        if (!ENABLE)
        {
            return;
        }

        setupDir();
        Map<String, Object> json = new LinkedHashMap<>();
        boolean isOreDict = false;
        List<Map<String, Object>> ingredients = new ArrayList<>();

        for (Object o : components)
        {
            if (o instanceof String)
            {
                isOreDict = true;
            }
            ingredients.add(serializeItem(o));
        }

        json.put("type", isOreDict ? "forge:ore_shapeless" : "minecraft:crafting_shapeless");
        json.put("ingredients", ingredients);
        json.put("result", serializeItem(result));

        // names the json the same name as the output's registry name
        // repeatedly adds _alt if a file already exists
        // janky I know but it works
        String suffix = result.getItem().getHasSubtypes() ? "_" + result.getItemDamage() : "";
        File f = new File(RECIPE_DIR, result.getItem().getRegistryName().getResourcePath() + suffix + ".json");

        while (f.exists())
        {
            suffix += "_alt";
            f = new File(RECIPE_DIR, result.getItem().getRegistryName().getResourcePath() + suffix + ".json");
        }

        writeAdvancements(result.getItem().getRegistryName().getResourcePath() + suffix);

        try (FileWriter w = new FileWriter(f))
        {
            GSON.toJson(json, w);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private static Map<String, Object> serializeItem(Object thing)
    {
        if (thing instanceof Item)
        {
            return serializeItem(new ItemStack((Item) thing));
        }
        if (thing instanceof Block)
        {
            return serializeItem(new ItemStack((Block) thing));
        }
        if (thing instanceof ItemStack)
        {
            ItemStack stack = (ItemStack) thing;
            Map<String, Object> ret = new LinkedHashMap<>();

            if (stack.hasTagCompound())
            {
                ret.put("type", "minecraft:item_nbt");
            }

            ret.put("item", stack.getItem().getRegistryName().toString());

            if (stack.getItem().getHasSubtypes() || stack.getItemDamage() != 0)
            {
                ret.put("data", stack.getItemDamage());
            }
            if (stack.hasTagCompound())
            {
                ret.put("nbt", stack.getTagCompound().toString());
            }
            if (stack.getCount() > 1)
            {
                ret.put("count", stack.getCount());
            }
            return ret;
        }
        if (thing instanceof String)
        {
            Map<String, Object> ret = new HashMap<>();
            USED_OD_NAMES.add((String) thing);
            ret.put("item", "#" + ((String) thing).toUpperCase(Locale.ROOT));
            return ret;
        }
        throw new IllegalArgumentException("Not a block, item, stack, or od name");
    }

    public static void writeAdvancements(String result)
    {
        if (!ENABLE)
        {
            return;
        }

        setupAdvDir();
        Map<String, Object> json = new HashMap<>();
        Map<String, Object> rewards = new HashMap<>();
        List<String> recipes = new ArrayList<>();
        Map<String, Map<String, Object>> criteria = new HashMap<>();
        Map<String, Object> has_item = new HashMap<>();
        Map<String, Object> conditions = new HashMap<>();
        Map<String, Object> conditions2 = new HashMap<>();
        Map<String, Object> has_the_recipe = new HashMap<>();
        ArrayList<ArrayList<String>> requirements = new ArrayList<>();
        ArrayList<String> reqs = new ArrayList<>();

        json.put("parent", "minecraft:recipes/root");
        recipes.add("moreplanets:" + result);
        rewards.put("recipes", recipes);

        has_item.put("trigger", "minecraft:inventory_changed");
        conditions.put("items", new ArrayList<>());
        conditions2.put("recipe", result);
        has_the_recipe.put("trigger", "minecraft:recipe_unlocked");
        has_the_recipe.put("conditions", conditions2);

        has_item.put("conditions", conditions);
        criteria.put("has_item", has_item);
        criteria.put("has_the_recipe", has_the_recipe);

        reqs.add("has_item");
        reqs.add("has_the_recipe");
        requirements.add(reqs);

        json.put("requirements", requirements);
        json.put("criteria", criteria);
        json.put("rewards", rewards);

        String suffix = "";
        File f = new File(ADVANCE_DIR, result + suffix + ".json");

        while (f.exists())
        {
            suffix += "_alt";
            f = new File(ADVANCE_DIR, result + suffix + ".json");
        }

        try (FileWriter w = new FileWriter(f))
        {
            GSON.toJson(json, w);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    // Call this after you are done generating
    public static void generateConstants()
    {
        setupDir();
        List<Map<String, Object>> json = new ArrayList<>();

        for (String s : USED_OD_NAMES)
        {
            Map<String, Object> entry = new HashMap<>();
            entry.put("name", s.toUpperCase(Locale.ROOT));
            entry.put("ingredient", ImmutableMap.of("type", "forge:ore_dict", "ore", s));
            json.add(entry);
        }

        try (FileWriter w = new FileWriter(new File(RECIPE_DIR, "_constants.json")))
        {
            GSON.toJson(json, w);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}