package jeresources.api.utils.restrictions;

import net.minecraftforge.common.DimensionManager;

import java.util.*;

public class DimensionRegistry
{
    private static Map<BlockRestriction,Set<Integer>> registry = new HashMap<BlockRestriction, Set<Integer>>();
    private static Map<Integer, DimInfo> altDimensions = new TreeMap<Integer, DimInfo>();

    private static class DimInfo
    {
        private int dimId;
        private String name;
        private boolean age;

        private DimInfo(int id)
        {
            this(id, false);
        }

        private DimInfo(int id, boolean age)
        {
            this(id, null, age);
        }

        private DimInfo(int id, String name, boolean age)
        {
            this.dimId = id;
            this.name = name;
            this.age = age;
        }

        private String getName()
        {
            if (name == null && DimensionManager.getWorld(dimId) != null && DimensionManager.getProvider(dimId) != null)
            {
                name = DimensionManager.getProvider(dimId).getDimensionName();
                if (age && !name.startsWith("Age")) name += " (Age)";
            }
            return name == null ? String.valueOf(dimId) : name;
        }

        private int getDimId()
        {
            return dimId;
        }

        private boolean isAge()
        {
            return age;
        }
    }

    static
    {
        registerDimension(BlockRestriction.NETHER, -1);
        registerDimension(BlockRestriction.STONE, 0);
        registerDimension(BlockRestriction.END, 1);
    }

    public static void registerDimension(BlockRestriction block, int dim)
    {
        registerDimension(block, dim, false);
    }

    public static void registerDimension(BlockRestriction block, int dim, boolean mystAge)
    {
        Set<Integer> saved = registry.get(block);
        if (saved == null)
            saved = new TreeSet<Integer>();
        saved.add(dim);
        altDimensions.put(dim, new DimInfo(dim, mystAge));
        registry.put(block,saved);
    }

    public static void registerDimension(BlockRestriction block, Integer... dims)
    {
        registerDimension(block,Arrays.asList(dims));
    }

    public static void registerDimension(BlockRestriction block, List<Integer> dims)
    {
        Set<Integer> saved = registry.get(block);
        if (saved == null)
            saved = new TreeSet<Integer>();
        saved.addAll(dims);
        for (Integer dim : dims)
            altDimensions.put(dim, new DimInfo(dim));
        registry.put(block,saved);
    }

    public static Set<Integer> getDimensions(BlockRestriction block)
    {
        if (registry.containsKey(block)) return registry.get(block);
        return null;
    }

    public static Set<Integer> getAltDimensions()
    {
        return altDimensions.keySet();
    }

    public static String getDimensionName(int dim)
    {
        return altDimensions.get(dim).getName();
    }

    public static boolean contains(int dimId)
    {
        return altDimensions.containsKey(dimId);
    }
}
