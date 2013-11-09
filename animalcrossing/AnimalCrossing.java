package net.minecraft.src.nucleareal.animalcrossing;

import static cpw.mods.fml.relauncher.Side.CLIENT;

import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.client.multiplayer.NetClientHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.src.nucleareal.ConfigurationCreator;
import net.minecraft.src.nucleareal.IConfigurationLoader;
import net.minecraft.src.nucleareal.NBTTool;
import net.minecraft.src.nucleareal.NuclearealBase;
import net.minecraft.src.nucleareal.NullValue;
import net.minecraft.src.nucleareal.ObjectTriple;
import net.minecraft.src.nucleareal.Position;
import net.minecraft.src.nucleareal.animalcrossing.block.BlockColorableFlower;
import net.minecraft.src.nucleareal.animalcrossing.entity.EntityFallingTreePart;
import net.minecraft.src.nucleareal.animalcrossing.entity.EntityFloatingBalloon;
import net.minecraft.src.nucleareal.animalcrossing.entity.EntityFloatingChest;
import net.minecraft.src.nucleareal.animalcrossing.entity.EntityPachinkoBullet;
import net.minecraft.src.nucleareal.animalcrossing.item.ItemColorableFlower;
import net.minecraft.src.nucleareal.animalcrossing.item.ItemFlowerDyePowder;
import net.minecraft.src.nucleareal.animalcrossing.item.ItemNugget;
import net.minecraft.src.nucleareal.animalcrossing.item.ItemPachinko;
import net.minecraft.src.nucleareal.animalcrossing.item.ItemWateringCan;
import net.minecraft.src.nucleareal.animalcrossing.recipe.RecipeNugget;
import net.minecraft.src.nucleareal.animalcrossing.recipe.RecipePachinko;
import net.minecraft.src.nucleareal.animalcrossing.recipe.RecipeRoseDye;
import net.minecraft.src.nucleareal.animalcrossing.recipe.RecipeWateringCan;
import net.minecraft.src.nucleareal.animalcrossing.render.RenderRose;
import net.minecraft.world.World;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid = "AnimalCrossing", name = "AnimalCrossing Mod", version = "1.0.0Final")
@NetworkMod(clientSideRequired = true, serverSideRequired = true)
public class AnimalCrossing extends NuclearealBase
{
	@Mod.Instance("AnimalCrossing")
	public static AnimalCrossing instance;

	public static final boolean DEBUG = false;
	public static final String SPX = DEBUG ? "net.minecraft.src." : "";
	public static final String CSD = SPX + "nucleareal.animalcrossing.client.ClientProxy";
	public static final String SSD = SPX + "nucleareal.animalcrossing.CommonProxy";

	@SidedProxy(clientSide = CSD, serverSide = SSD)
	public static CommonProxy proxy;

	public static int WideSearchMax = 1000;

	private static int NO_MEANING_VALUE = Integer.MAX_VALUE;
	private static String DefaultAxe = "258, 271, 275, 279, 286, 24242, 24247, 24252, 24257, 24262";
	private static String DefaultWood = "17, 1231";
	private static String DefaultLeaf = "18, 1234";

	private static HashMap<String, NullValue> AxeList;
	private static HashMap<String, NullValue> WoodList;
	private static HashMap<String, NullValue> LeafList;
	private static HashMap<String, Integer> TreeCountingMap;

	public static Block Rose; public static Item RoseItem; public static int RoseID = 1220; public static int RoseRenderID = -1;
	public static Item RoseDyeItem; public static int RoseDyeID = 24290;
	public static Item Nuggets; public static int NuggetsID = 0;
	public static Block RoseWither; public static Item RoseWitherItem; public static int RoseWitherID = 1221;
	public static Item WateringCanGold; public static int WateringCanGoldID = 24300;
	public static Item PachinkoGold; public static int PachinkoGoldID = 24301;

	public static HashMap<String, ObjectTriple<ArrayList<ItemStack>, ArrayList<ItemStack>, Integer>> OreDictMap;

	public static NBTTool NBT;

	public static int RoseSpawnChance = 384;
	public static int RoseWiltChance = 512;
	public static int RoseDeadChance = 768;
	public static int BlackToGoldChance = 16;
	public static int SprintingDespawnChance = 96;
	public static int BalloonSpawnChance = 8192;
	public static boolean AddRecipeInkVial = false;

	private static int Entity_FallingBlockID = 0;
	private static int Entity_FloatingBalloonnID = 1;
	private static int Entity_FloatingChestID = 2;
	private static int Entity_PachinkoBulletID = 3;

	public static CreativeTabs AnimalCrossingTab;

	private static TickHandler Handler;

	@Override
	public void load()
	{
	}

	@Override
	protected String Version()
	{
		return "Alice For 1.6.2";
	}

	@Mod.PreInit
	public void preInit(FMLPreInitializationEvent event)
	{
		NBT = new NBTTool("AnimalCrossing");

		Handler = new TickHandler();

		AxeList = new HashMap<String, NullValue>();
		WoodList = new HashMap<String, NullValue>();
		LeafList = new HashMap<String, NullValue>();
		TreeCountingMap = new HashMap<String, Integer>();

		GameRegistry.registerPlayerTracker(Handler);
		TickRegistry.registerScheduledTickHandler(Handler, Side.CLIENT);

		ConfigurationCreator.create(event, new IConfigurationLoader()
		{
			@Override
			public void onLoad(Configuration conf)
			{
				loadEffectives(conf, "AxeIds", DefaultAxe, AxeList);
				loadEffectives(conf, "WoodIds", DefaultWood, WoodList);
				loadEffectives(conf, "LeafIds", DefaultLeaf, LeafList);

				RoseID = conf.get(conf.CATEGORY_BLOCK, "RoseID", RoseID).getInt();
				RoseWitherID = conf.get(conf.CATEGORY_BLOCK, "RoseWitherID", RoseWitherID).getInt();

				RoseDyeID = conf.get(conf.CATEGORY_ITEM, "RoseDyeID", RoseDyeID).getInt();
				NuggetsID = conf.get(conf.CATEGORY_ITEM, "NuggetsID", NuggetsID).getInt();

				WateringCanGoldID = conf.get(conf.CATEGORY_ITEM, "WateringCanGoldID", WateringCanGoldID).getInt();
				PachinkoGoldID = conf.get(conf.CATEGORY_ITEM, "PachinkoGoldID", PachinkoGoldID).getInt();

				RoseSpawnChance = conf.get("Chance", "RoseSpawnDenominator", RoseSpawnChance).getInt();
				RoseDeadChance = conf.get("Chance", "RoseDespawnDenominator", RoseDeadChance).getInt();
				RoseWiltChance = conf.get("Chance", "RoseWiltDenominator", RoseWiltChance).getInt();
				BlackToGoldChance = conf.get("Chance", "RoseBlackToGoldDenominator", BlackToGoldChance).getInt();
				SprintingDespawnChance = conf.get("Chance", "RoseSprintDespawnDenominator", SprintingDespawnChance).getInt();
				BalloonSpawnChance = conf.get("Chance", "FloatingBalloonSpawnChanceDenominator", BalloonSpawnChance).getInt();

				AddRecipeInkVial = conf.get(conf.CATEGORY_GENERAL, "EnableInkVialRecipe", AddRecipeInkVial).getBoolean(AddRecipeInkVial);
				WideSearchMax = conf.get(conf.CATEGORY_GENERAL, "TreeCutSearchMax", WideSearchMax).getInt();
			}
		});

		MinecraftForge.EVENT_BUS.register(new OreDictionaryHandler());
		MinecraftForge.EVENT_BUS.register(new PickupHandler());
		MinecraftForge.EVENT_BUS.register(new FloatingBalloonAttackedHandler());

		GameRegistry.registerCraftingHandler(new CreateWateringCanHandler());
		GameRegistry.registerCraftingHandler(new CraftingHandler());
	}

	@Mod.Init
	public void init(FMLInitializationEvent event)
	{
		AnimalCrossingTab = new AnimalCrossingTab("Animal Crossing");
		LanguageRegistry.instance().addStringLocalization("itemGroup.Animal Crossing", "en_US", "Animal Crossing");
		LanguageRegistry.instance().addStringLocalization("itemGroup.Animal Crossing", "ja_JP", "どうぶつの森");

		if(isValidBlockID(RoseID))
		{
			Rose = new BlockColorableFlower(RoseID).setUnlocalizedName("BlockRose").setTextureName("nc:B_Rose").setStepSound(Block.soundGrassFootstep);
			GameRegistry.registerBlock(Rose, ItemColorableFlower.class, "FlowerRose", "AnimalCrossing");
			RoseItem = new ItemColorableFlower(RoseID - 256, Rose).setUnlocalizedName("ItemRose").setTextureName("nc:I_Rose");
			GameRegistry.registerItem(RoseItem, "Rose", "AnimalCrossing");

			GameRegistry.registerWorldGenerator(new GeneratorRose());

			for(int i = 0; i < RoseColor.getAllColors().size(); i++)
			{
				LanguageRegistry.instance().addNameForObject(new ItemStack(RoseItem, 1, i), "en_US", RoseColor.RoseColorNames[i]+" Rose");
				LanguageRegistry.instance().addNameForObject(new ItemStack(RoseItem, 1, i), "ja_JP", RoseColor.RoseColorNames_jaJP[i]+"のバラ");
			}
			RoseRenderID = RenderingRegistry.getNextAvailableRenderId();
			RenderingRegistry.registerBlockHandler(new RenderRose());
		}

		if(isValidBlockID(RoseWitherID))
		{
			RoseWither = new BlockColorableFlower(RoseWitherID).setUnlocalizedName("BlockRoseWither").setTextureName("nc:B_Rose").setStepSound(Block.soundGrassFootstep);
			GameRegistry.registerBlock(RoseWither, ItemColorableFlower.class, "FlowerRoseWither", "AnimalCrossing");
			RoseWitherItem = new ItemColorableFlower(RoseWitherID - 256, RoseWither).setUnlocalizedName("ItemRoseWither").setTextureName("nc:I_RoseWither");
			GameRegistry.registerItem(RoseWitherItem, "RoseWither", "AnimalCrossing");

			for(int i = 0; i < RoseColor.getAllColors().size(); i++)
			{
				LanguageRegistry.instance().addNameForObject(new ItemStack(RoseWitherItem, 1, i), "en_US", "Wilted "+RoseColor.RoseColorNames[i]+" Rose");
				LanguageRegistry.instance().addNameForObject(new ItemStack(RoseWitherItem, 1, i), "ja_JP", "枯れた"+RoseColor.RoseColorNames_jaJP[i]+"のバラ");
			}
			if(RoseRenderID == -1)
			{
				RoseRenderID = RenderingRegistry.getNextAvailableRenderId();
				RenderingRegistry.registerBlockHandler(new RenderRose());
			}
		}

		if(isValidItemID(RoseDyeID))
		{
			RoseDyeItem = new ItemFlowerDyePowder(RoseDyeID - 256).setUnlocalizedName("ItemDyeRose").setTextureName("nc:I_Dye");
			GameRegistry.registerItem(RoseDyeItem, "RoseDye", "AnimalCrossing");
			for(int i = 0; i < RoseColor.getAllColors().size(); i++)
			{
				LanguageRegistry.instance().addNameForObject(new ItemStack(RoseDyeItem, 1, i), "en_US", RoseColor.RoseColorNames[i]+" Dye Powder");
				LanguageRegistry.instance().addNameForObject(new ItemStack(RoseDyeItem, 1, i), "ja_JP", RoseColor.RoseColorNames_jaJP[i]+"の染料");
			}

			if(isValidBlockID(RoseID))
			{
				new RecipeRoseDye(RoseItem, RoseDyeItem).RegisterAll();
			}
		}

		if(isValidItemID(WateringCanGoldID))
		{
			WateringCanGold = new ItemWateringCan(WateringCanGoldID - 256, WateringCanMaterial.Gold).setUnlocalizedName("ItemWarteringCanGold").setTextureName("nc:I_WateringGold");
			ItemWateringCan cnv = (ItemWateringCan)WateringCanGold;
			GameRegistry.registerItem(WateringCanGold, "WateringCanGold", "AnimalCrossing");

			String type = WateringCanMaterial.Names_enUS[cnv.getMaterial().ordinal()];
			String enUS = type;
			String jaJP = WateringCanMaterial.Names_jaJP[cnv.getMaterial().ordinal()];

			LanguageRegistry.instance().addNameForObject(new ItemStack(WateringCanGold, 1, 0), "en_US", enUS+" Watering Can Part");
			LanguageRegistry.instance().addNameForObject(new ItemStack(WateringCanGold, 1, 0), "ja_JP", jaJP+"のジョウロパーツ");
			for(int i = 1; i <= cnv.getMaterial().getMaxUse(); i++)
			{
				LanguageRegistry.instance().addNameForObject(new ItemStack(WateringCanGold, 1, i), "en_US", enUS+" Watering Can");
				LanguageRegistry.instance().addNameForObject(new ItemStack(WateringCanGold, 1, i), "ja_JP", jaJP+"のジョウロ");
			}

			RecipeWateringCan recipe = new RecipeWateringCan(cnv);
			recipe.registerAllRecipes();
			GameRegistry.addRecipe(recipe);
		}

		if(isValidItemID(PachinkoGoldID))
		{
			PachinkoGold = new ItemPachinko(PachinkoGoldID - 256, PachinkoMaterial.Gold).setUnlocalizedName("ItemPachinkoGold").setTextureName("nc:I_PachinkoGold");
			ItemPachinko cnv = (ItemPachinko)PachinkoGold;
			GameRegistry.registerItem(PachinkoGold, "PachinkoGold", "AnimalCrossing");

			String type = PachinkoMaterial.Names_enUS[cnv.getMaterial().ordinal()];
			String enUS = type;
			String jaJP = PachinkoMaterial.Names_jaJP[cnv.getMaterial().ordinal()];

			LanguageRegistry.instance().addNameForObject(new ItemStack(PachinkoGold, 1, 0), "en_US", enUS+" Pachinko Bullet");
			LanguageRegistry.instance().addNameForObject(new ItemStack(PachinkoGold, 1, 0), "ja_JP", jaJP+"のパチンコの弾");
			for(int i = 1; i <= cnv.getMaterial().getMaxUse(); i++)
			{
				LanguageRegistry.instance().addNameForObject(new ItemStack(PachinkoGold, 1, i), "en_US", enUS+" Pachinko");
				LanguageRegistry.instance().addNameForObject(new ItemStack(PachinkoGold, 1, i), "ja_JP", jaJP+"のパチンコ");
			}

			RecipePachinko recipe = new RecipePachinko(cnv);
			recipe.registerAllRecipes();
			GameRegistry.addRecipe(recipe);
		}

		if(isValidItemID(NuggetsID))
		{
			Nuggets = new ItemNugget(NuggetsID).setUnlocalizedName("ItemNugget").setTextureName("nc:I_Nugget_");
			GameRegistry.registerItem(Nuggets, "Nuggets", "AnimalCrossing");
			for(int i = 0; i < NuggetValue.getAllElements().size(); i++)
			{
				LanguageRegistry.instance().addNameForObject(new ItemStack(Nuggets, 1, i), "en_US", NuggetValue.of(i).name()+" Nugget");
				LanguageRegistry.instance().addNameForObject(new ItemStack(Nuggets, 1, i), "ja_JP", NuggetValue.NamesjaJP[i]+"の破片");
			}
		}

		EntityRegistry.registerModEntity(EntityFallingTreePart.class, "TreePart", Entity_FallingBlockID, this, 250, 5, true);
		EntityRegistry.registerModEntity(EntityFloatingBalloon.class, "FloatingBalloon", Entity_FloatingBalloonnID, this, 250, 5, true);
		EntityRegistry.registerModEntity(EntityFloatingChest.class, "FloatingChest", Entity_FloatingChestID, this, 250, 5, true);
		EntityRegistry.registerModEntity(EntityPachinkoBullet.class, "PachinkoBullet", Entity_PachinkoBulletID, this, 250, 5, true);

		proxy.registerRenderers();

		LanguageRegistry.instance().addStringLocalization("entity.TreePart.name", "en_US", "FallingTreePart");
		LanguageRegistry.instance().addStringLocalization("entity.FloatingBalloon.name", "en_US", "FloatingBalloon");
		LanguageRegistry.instance().addStringLocalization("entity.FloatingChest.name", "en_US", "FloatingChest");
		LanguageRegistry.instance().addStringLocalization("entity.PachinkoBullet.name", "en_US", "PachinkoBullet");

		new Achievements("AnimalCrossing").doRegister();
	}

	public static boolean isValidBlockID(int ID)
	{
		return 0 < ID && ID < Block.blocksList.length;
	}

	public static boolean isValidItemID(int ID)
	{
		return 0 < ID && ID < Item.itemsList.length;
	}

	@Mod.PostInit
	public void postInit(FMLPostInitializationEvent ev)
	{
		if(isValidBlockID(RoseID) && isValidItemID(NuggetsID))
		{
			OreDictionary.registerOre("goldNugget", new ItemStack(Item.goldNugget));
			OreDictionary.registerOre("ingotGold", new ItemStack(Item.ingotGold));
			OreDictionary.registerOre("ingotIron", new ItemStack(Item.ingotIron));
			OreDictionary.registerOre("ingotDiamond",new ItemStack(Item.diamond));
			OreDictionary.registerOre("ingotRedstone", new ItemStack(Item.redstone));

			for(int i = 0; i < NuggetValue.getAllElements().size(); i++)
			{
				String key = NuggetValue.of(i).name();
				String par = String.valueOf(key.charAt(0)).toLowerCase() + key.substring(1);
				OreDictionary.registerOre(par + "Nugget", new ItemStack(Nuggets, 1, i));
			}

			OreDictMap = new HashMap<String, ObjectTriple<ArrayList<ItemStack>, ArrayList<ItemStack>, Integer>>();
			for(int i = 0; i < NuggetValue.getAllElements().size(); i++)
			{
				getAndRegisterOre(NuggetValue.of(i).name(), i);
			}

			RecipeNugget recipe = new RecipeNugget();
			recipe.RegisterAll();
		}
	}

	private void getAndRegisterOre(String key, int index)
	{
		String keyNugget  = String.valueOf(key.charAt(0)).toLowerCase() + key.substring(1) + "Nugget";
		String keyIngot = "ingot" + key;

		ArrayList<ItemStack> nuggets = OreDictionary.getOres(keyNugget);
		ArrayList<ItemStack> ingots  = OreDictionary.getOres(keyIngot);
		if(nuggets.size() > 0 && ingots.size() > 0)
		{
			OreDictMap.put(key, new ObjectTriple<ArrayList<ItemStack>, ArrayList<ItemStack>, Integer>(nuggets, ingots, index));
		}
	}

	private void loadEffectives(Configuration conf, String desc, String defValue, HashMap<String, NullValue> list)
	{
		String ids = conf.get(Configuration.CATEGORY_GENERAL, desc, defValue).getString();
		String[] idArray = ids.replace(" ", "").split(",");
		for(String id : idArray)
		{
			if(id == null || id.isEmpty()) continue;

			String res = id;
			if(!id.contains(":"))
			{
				res += ":" + NO_MEANING_VALUE;
			}
			list.put(res, NullValue.get());
		}
	}

	public static boolean counting(World world, int x, int y, int z)
	{
		Position pos = new Position(x, y, z);
		String key = pos.getVisualityValue();

		if(TreeCountingMap.containsKey(key))
		{
			Integer i = TreeCountingMap.get(key);
			if(++i == 3)
			{
				TreeCountingMap.remove(key);
				return true;
			}
			TreeCountingMap.put(key, i);
		}
		else
		{
			TreeCountingMap.put(key, 1);
		}
		return false;
	}

	public static void reset(World world, int x, int y, int z)
	{
		Position pos = new Position(x, y, z);
		String key = pos.getVisualityValue();

		if(TreeCountingMap.containsKey(key))
		{
			TreeCountingMap.remove(key);
		}
	}

	private static boolean isEffective(int id, int meta, HashMap<String, NullValue> list)
	{
		return 	list.containsKey(String.format("%d:%d", id, NO_MEANING_VALUE)) ||
				list.containsKey(String.format("%d:%d", id, meta));
	}

	public static boolean isAxeTool(ItemStack ist)
	{
		return 	isEffective(ist.itemID, ist.getItemDamage(), AxeList);
	}

	public static boolean isWood(int id, int meta)
	{
		return 	isEffective(id, meta, WoodList);
	}

	public static boolean isLeaf(int id, int meta)
	{
		System.out.println(String.format("isLeaf? (%d,%d)", id, meta));

		return	isEffective(id, meta, LeafList);
	}

	@SideOnly(CLIENT)
    public void clientCustomPayload(NetClientHandler handler, Packet250CustomPayload packet)
    {
		System.out.println("on Packet in Mod.");
    }
}
