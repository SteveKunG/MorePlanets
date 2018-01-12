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

    public static void addShapedRecipe(ItemStack output, Object... components)
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
                key.put(Character.toString(Character.toUpperCase(curKey)), serializeItem(obj));
                curKey = null;
            }
        }

        json.put("type", isOreDict ? "forge:ore_shaped" : "minecraft:crafting_shaped");
        json.put("pattern", pattern);
        json.put("key", key);
        json.put("result", serializeItem(output));

        // names the json the same name as the output's registry name
        // repeatedly adds _alt if a file already exists
        // janky I know but it works
        String suffix = output.getItem().getHasSubtypes() ? "_" + output.getItemDamage() : "";
        File file = new File(RECIPE_DIR, output.getItem().getRegistryName().getResourcePath() + suffix + ".json");

        while (file.exists())
        {
            suffix += "_alt";
            file = new File(RECIPE_DIR, output.getItem().getRegistryName().getResourcePath() + suffix + ".json");
        }

        writeAdvancements(output.getItem().getRegistryName().getResourcePath() + suffix);

        try (FileWriter writer = new FileWriter(file))
        {
            GSON.toJson(json, writer);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void addShapelessRecipe(ItemStack output, Object... components)
    {
        if (!ENABLE)
        {
            return;
        }

        setupDir();
        Map<String, Object> json = new LinkedHashMap<>();
        boolean isOreDict = false;
        List<Map<String, Object>> ingredients = new ArrayList<>();

        for (Object obj : components)
        {
            if (obj instanceof String)
            {
                isOreDict = true;
            }
            ingredients.add(serializeItem(obj));
        }

        json.put("type", isOreDict ? "forge:ore_shapeless" : "minecraft:crafting_shapeless");
        json.put("ingredients", ingredients);
        json.put("result", serializeItem(output));

        // names the json the same name as the output's registry name
        // repeatedly adds _alt if a file already exists
        // janky I know but it works
        String suffix = output.getItem().getHasSubtypes() ? "_" + output.getItemDamage() : "";
        File file = new File(RECIPE_DIR, output.getItem().getRegistryName().getResourcePath() + suffix + ".json");

        while (file.exists())
        {
            suffix += "_alt";
            file = new File(RECIPE_DIR, output.getItem().getRegistryName().getResourcePath() + suffix + ".json");
        }

        writeAdvancements(output.getItem().getRegistryName().getResourcePath() + suffix);

        try (FileWriter writer = new FileWriter(file))
        {
            GSON.toJson(json, writer);
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
        throw new IllegalArgumentException("Not a block, item, stack, or od name");
    }

    public static void writeAdvancements(String result)
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
        recipes.add("moreplanets:" + result);
        rewards.put("recipes", recipes);

        has_item.put("trigger", "minecraft:inventory_changed");
        conditions.put("items", new LinkedList<>());
        conditions2.put("recipe", result);
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