package net.minecraft.src.nucleareal.animalcrossing;

import static cpw.mods.fml.relauncher.Side.CLIENT;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.nucleareal.ConfigurationCreator;
import net.minecraft.src.nucleareal.IConfigurationLoader;
import net.minecraft.src.nucleareal.NBTTool;
import net.minecraft.src.nucleareal.NuclearealBase;
import net.minecraft.src.nucleareal.NullValue;
import net.minecraft.src.nucleareal.ObjectTriple;
import net.minecraft.src.nucleareal.Position;
import net.minecraft.src.nucleareal.animalcrossing.block.BlockColorableFlower;
import net.minecraft.src.nucleareal.animalcrossing.block.BlockFurniture;
import net.minecraft.src.nucleareal.animalcrossing.block.tileentity.EnumFurniture;
import net.minecraft.src.nucleareal.animalcrossing.block.tileentity.FurniturePacketHandler;
import net.minecraft.src.nucleareal.animalcrossing.block.tileentity.TileEntityFurniture;
import net.minecraft.src.nucleareal.animalcrossing.entity.EntityFallingTreePart;
import net.minecraft.src.nucleareal.animalcrossing.entity.EntityFishingFloat;
import net.minecraft.src.nucleareal.animalcrossing.entity.EntityFloatingBalloon;
import net.minecraft.src.nucleareal.animalcrossing.entity.EntityFloatingChest;
import net.minecraft.src.nucleareal.animalcrossing.entity.EntityPachinkoBullet;
import net.minecraft.src.nucleareal.animalcrossing.item.ItemColorableFlower;
import net.minecraft.src.nucleareal.animalcrossing.item.ItemFish;
import net.minecraft.src.nucleareal.animalcrossing.item.ItemFishingBait;
import net.minecraft.src.nucleareal.animalcrossing.item.ItemFlowerDyePowder;
import net.minecraft.src.nucleareal.animalcrossing.item.ItemFurniture;
import net.minecraft.src.nucleareal.animalcrossing.item.ItemGreatFishingRod;
import net.minecraft.src.nucleareal.animalcrossing.item.ItemNugget;
import net.minecraft.src.nucleareal.animalcrossing.item.ItemPachinko;
import net.minecraft.src.nucleareal.animalcrossing.item.ItemWateringCan;
import net.minecraft.src.nucleareal.animalcrossing.recipe.RecipeFish;
import net.minecraft.src.nucleareal.animalcrossing.recipe.RecipeFishingBait;
import net.minecraft.src.nucleareal.animalcrossing.recipe.RecipeFishingRod;
import net.minecraft.src.nucleareal.animalcrossing.recipe.RecipeNugget;
import net.minecraft.src.nucleareal.animalcrossing.recipe.RecipePachinko;
import net.minecraft.src.nucleareal.animalcrossing.recipe.RecipeRoseDye;
import net.minecraft.src.nucleareal.animalcrossing.recipe.RecipeWateringCan;
import net.minecraft.src.nucleareal.animalcrossing.render.RenderFurniture;
import net.minecraft.src.nucleareal.animalcrossing.render.RenderRose;
import net.minecraft.world.World;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.client.registry.ClientRegistry;
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

@Mod(modid = "AnimalCrossing", name = "AnimalCrossing Mod", version = "1.0.0Final")
@NetworkMod(clientSideRequired = true, serverSideRequired = true, channels = "ACFurniture", packetHandler = FurniturePacketHandler.class)
public class AnimalCrossing extends NuclearealBase implements IConfigurationLoader
{
	@Mod.Instance("AnimalCrossing")
	public static AnimalCrossing instance;

	public static final boolean DEBUG = false;
	public static final String SPX = DEBUG ? "net.minecraft.src." : "";
	public static final String CSD = SPX + "nucleareal.animalcrossing.client.ClientProxy";
	public static final String SSD = SPX + "nucleareal.animalcrossing.CommonProxy";

	@SidedProxy(clientSide = CSD, serverSide = SSD)
	public static CommonProxy proxy;

	public static final String CATEGORY_EFFECTIVES = "Effectives";

	public static int WideSearchMax = 1000;

	private static int NO_MEANING_VALUE = Integer.MAX_VALUE;
	private static String DefaultAxe = "";
	private static String DefaultWood = "";
	private static String DefaultLeaf = "";
	private static String DefaultSword = "";
	private static List<String> axeClass;
	private static List<String> woodClass;
	private static List<String> leafClass;
	private static List<String> swordClass;

	private static HashMap<String, NullValue> AxeList;
	private static HashMap<String, NullValue> WoodList;
	private static HashMap<String, NullValue> LeafList;
	private static HashMap<String, NullValue> SwordList;
	private static HashMap<String, Integer> TreeCountingMap;

	public static Block Rose; public static Item RoseItem; public static int RoseID = 1220; public static int RoseRenderID = -1;
	public static Item RoseDyeItem; public static int RoseDyeID = 24290;
	public static Item Nuggets; public static int NuggetsID = 0;
	public static Block RoseWither; public static Item RoseWitherItem; public static int RoseWitherID = 1221;
	public static Item WateringCanGold; public static int WateringCanGoldID = 24300;
	public static Item PachinkoGold; public static int PachinkoGoldID = 24301;
	public static Item FishingRodGold; public static int FishingRodGoldID = 24302;
	public static Item FishingBait; public static int FishingBaitID = 0;
	public static Item Fish; public static int FishID = 24293;
	public static Block Furniture; public static Item FurnitureItem; public static int FurnitureID = 1222; public static int FurnitureRenderID = -1;

	public static HashMap<String, ObjectTriple<ArrayList<ItemStack>, ArrayList<ItemStack>, Integer>> OreDictMap;

	public static NBTTool NBT;

	public static int RoseSpawnChance = 384;
	public static int RoseWiltChance = 512;
	public static int RoseDeadChance = 768;
	public static int BlackToGoldChance = 16;
	public static int SprintingDespawnChance = 96;
	public static int BalloonSpawnChance = 8192;
	public static int AchievementBeginID = 1073741823;
	public static boolean AddRecipeInkVial = false;
	public static boolean isForceRegisterAchievement = true;
	public static boolean isNoDamageWithoutWeapon = true;
	public static boolean PachinkoUsesBullet = false;
	public static boolean FishingRodUsesBait = false;
	public static boolean isLampPoweredBySignal = true;

	private static int Entity_FallingBlockID = 0;
	private static int Entity_FloatingBalloonnID = 1;
	private static int Entity_FloatingChestID = 2;
	private static int Entity_PachinkoBulletID = 3;
	private static int Entity_FishingID = 4;

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
		SwordList = new HashMap<String, NullValue>();
		axeClass = new LinkedList<String>();
		woodClass = new LinkedList<String>();
		leafClass = new LinkedList<String>();
		swordClass = new LinkedList<String>();
		TreeCountingMap = new HashMap<String, Integer>();

		GameRegistry.registerPlayerTracker(Handler);
		TickRegistry.registerScheduledTickHandler(Handler, CLIENT);

		ConfigurationCreator.create(event, this);

		MinecraftForge.EVENT_BUS.register(new OreDictionaryHandler());
		MinecraftForge.EVENT_BUS.register(new PickupHandler());
		MinecraftForge.EVENT_BUS.register(new OnLivingAttackHandler());

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

		if(isValidBlockID(FurnitureID))
		{
			Furniture = new BlockFurniture(FurnitureID).setUnlocalizedName("BlockFurniture").setTextureName("nc:B_Furniture").setStepSound(Block.soundMetalFootstep).setHardness(1F/24F);
			GameRegistry.registerBlock(Furniture, ItemFurniture.class, "FurnitureBlock", "AnimalCrossing");
			FurnitureItem = new ItemFurniture(FurnitureID - 256, Furniture).setUnlocalizedName("ItemFurniture").setTextureName("nc:B_Furniture");
			GameRegistry.registerItem(FurnitureItem, "FurnitureItem", "AnimalCrossing");

			for(int i = 0; i < EnumFurniture.size(); i++)
			{
				LanguageRegistry.instance().addNameForObject(new ItemStack(FurnitureItem, 1, i), "en_US", EnumFurniture.of(i).getFurnitureName());
			}
			FurnitureRenderID = RenderingRegistry.getNextAvailableRenderId();

			RenderFurniture renderer = new RenderFurniture();

			RenderingRegistry.registerBlockHandler(renderer);

			ClientRegistry.registerTileEntity(TileEntityFurniture.class, "Furniture", renderer);
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

		if(isValidItemID(FishID))
		{
			Fish = new ItemFish(FishID-256).setUnlocalizedName("ItemFish").setTextureName("nc:I_Fish");
			GameRegistry.registerItem(Fish, "Fish", "AnimalCrossing");
			for(int i = 0; i < FishType.size(); i++)
			{
				LanguageRegistry.instance().addNameForObject(new ItemStack(Fish,1,i), "en_US", FishType.of(i).name());
				LanguageRegistry.instance().addNameForObject(new ItemStack(Fish,1,FishType.getRegisterLimit()+i), "en_US", "Cooked "+FishType.of(i).name());
			}
			RecipeFish recipe0 = new RecipeFish(Fish);
			recipe0.registerAllRecipes();

			if(isValidItemID(FishingRodGoldID))
			{
				FishingRodGold = new ItemGreatFishingRod(FishingRodGoldID - 256, FishingRodMaterial.Gold).setUnlocalizedName("ItemFishingRodGold").setTextureName("nc:I_FishingRodGold");
				ItemGreatFishingRod cnv = (ItemGreatFishingRod)FishingRodGold;
				GameRegistry.registerItem(FishingRodGold, "FishingRodGold", "AnimalCrossing");
				String type = FishingRodMaterial.Names_enUS[cnv.getMaterial().ordinal()];
				String enUS = type;
				String jaJP = FishingRodMaterial.Names_jaJP[cnv.getMaterial().ordinal()];
				LanguageRegistry.instance().addNameForObject(new ItemStack(FishingRodGold), "en_US", enUS+" FishingRod");
				LanguageRegistry.instance().addNameForObject(new ItemStack(FishingRodGold), "ja_JP", jaJP+"のつりざお");
				RecipeFishingRod recipe = new RecipeFishingRod(cnv);
				recipe.registerAllRecipes();
				GameRegistry.addRecipe(recipe);
			}
		}

		if(isValidItemID(FishingBaitID))
		{
			FishingBait = new ItemFishingBait(FishingBaitID - 256, 1, false).setUnlocalizedName("ItemFishingBait").setTextureName("nc:I_FishingBait");
			GameRegistry.registerItem(FishingBait, "FishingBait", "AnimalCrossing");
			for(int i = 0; i < Bait.size(); i++)
			{
				LanguageRegistry.instance().addNameForObject(new ItemStack(FishingBait, 1, i), "en_US", Bait.Names_enUS[i]+" Bait");
				LanguageRegistry.instance().addNameForObject(new ItemStack(FishingBait, 1, i), "ja_JP", Bait.Names_jaJP[i]+"魚の餌");
			}
			RecipeFishingBait recipe = new RecipeFishingBait(FishingBait);
			recipe.registerAllRecipes();
		}
		else
		{
			FishingRodUsesBait = false;
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
		EntityRegistry.registerModEntity(EntityFishingFloat.class, "FishingFloat", Entity_FishingID, this, 250, 5, true);

		proxy.registerRenderers();

		LanguageRegistry.instance().addStringLocalization("entity.TreePart.name", "en_US", "FallingTreePart");
		LanguageRegistry.instance().addStringLocalization("entity.FloatingBalloon.name", "en_US", "FloatingBalloon");
		LanguageRegistry.instance().addStringLocalization("entity.FloatingChest.name", "en_US", "FloatingChest");
		LanguageRegistry.instance().addStringLocalization("entity.PachinkoBullet.name", "en_US", "PachinkoBullet");
		LanguageRegistry.instance().addStringLocalization("entity.FishingFloat.name", "en_US", "FishingFloat");

		new Achievements("AnimalCrossing").doRegister();
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

	public static boolean isValidBlockID(int ID)
	{
		return 0 < ID && ID < Block.blocksList.length;
	}

	public static boolean isValidItemID(int ID)
	{
		return 0 < ID && ID < Item.itemsList.length;
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

	private List<String> loadEffectives(Configuration conf, String desc, String defValue, HashMap<String, NullValue> list)
	{
		String ids		= conf.get(CATEGORY_EFFECTIVES, desc, defValue).getString();
		String classes	= conf.get(CATEGORY_EFFECTIVES, desc.replace("Id", "Classe"), "").getString();
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


		String[] classArray = classes.replace(" ", "").split(",");
		List<String> res = new LinkedList<String>();
		for(String cz : classArray)
		{
			if(cz == null || cz.isEmpty()) continue;

			res.add(cz);
		}
		return res;
	}

	private static boolean isEffective(int id, int meta, HashMap<String, NullValue> list, List<String> cz, String clname)
	{
		return 	list.containsKey(String.format("%d:%d", id, NO_MEANING_VALUE))	||
				list.containsKey(String.format("%d:%d", id, meta))				||
				cz.contains(clname);
	}

	public static boolean isAxeTool(int id, int meta, String clanme)
	{
		return 	isEffective(id, meta, AxeList, axeClass, clanme);
	}

	public static boolean isWood(int id, int meta, String clanme)
	{
		return 	isEffective(id, meta, WoodList, woodClass, clanme);
	}

	public static boolean isLeaf(int id, int meta, String clanme)
	{
		return	isEffective(id, meta, LeafList, leafClass, clanme);
	}

	public static boolean isSword(int id, int meta, String clanme)
	{
		return	isEffective(id, meta, SwordList, swordClass, clanme);
	}

	public void onLoad(Configuration conf)
	{
		axeClass = loadEffectives(conf, "AxeIds", DefaultAxe, AxeList);
		woodClass = loadEffectives(conf, "WoodIds", DefaultWood, WoodList);
		leafClass = loadEffectives(conf, "LeafIds", DefaultLeaf, LeafList);
		swordClass = loadEffectives(conf, "SwordIds", DefaultSword, SwordList);

		RoseID = conf.get(conf.CATEGORY_BLOCK, "RoseID", RoseID).getInt();
		RoseWitherID = conf.get(conf.CATEGORY_BLOCK, "RoseWitherID", RoseWitherID).getInt();
		FurnitureID = conf.get(conf.CATEGORY_BLOCK, "FurnitureID", FurnitureID).getInt();

		RoseDyeID = conf.get(conf.CATEGORY_ITEM, "RoseDyeID", RoseDyeID).getInt();
		NuggetsID = conf.get(conf.CATEGORY_ITEM, "NuggetsID", NuggetsID).getInt();

		WateringCanGoldID = conf.get(conf.CATEGORY_ITEM, "WateringCanGoldID", WateringCanGoldID).getInt();
		PachinkoGoldID = conf.get(conf.CATEGORY_ITEM, "PachinkoGoldID", PachinkoGoldID).getInt();
		FishingRodGoldID = conf.get(conf.CATEGORY_ITEM, "FishingRodGoldID", FishingRodGoldID).getInt();

		FishingBaitID = conf.get(conf.CATEGORY_ITEM, "FishingBaitID", FishingBaitID).getInt();
		FishID = conf.get(conf.CATEGORY_ITEM, "FishID", FishID).getInt();

		RoseSpawnChance = conf.get("Chance", "RoseSpawnDenominator", RoseSpawnChance).getInt();
		RoseDeadChance = conf.get("Chance", "RoseDespawnDenominator", RoseDeadChance).getInt();
		RoseWiltChance = conf.get("Chance", "RoseWiltDenominator", RoseWiltChance).getInt();
		BlackToGoldChance = conf.get("Chance", "RoseBlackToGoldDenominator", BlackToGoldChance).getInt();
		SprintingDespawnChance = conf.get("Chance", "RoseSprintDespawnDenominator", SprintingDespawnChance).getInt();
		BalloonSpawnChance = conf.get("Chance", "FloatingBalloonSpawnChanceDenominator", BalloonSpawnChance).getInt();

		AchievementBeginID = conf.get("Achievement", "AchievementBeginID", AchievementBeginID).getInt();
		isForceRegisterAchievement = conf.get("Achievement", "isForceRegisterAchievement", isForceRegisterAchievement).getBoolean(isForceRegisterAchievement);

		AddRecipeInkVial = conf.get(conf.CATEGORY_GENERAL, "EnableInkVialRecipe", AddRecipeInkVial).getBoolean(AddRecipeInkVial);
		WideSearchMax = conf.get(conf.CATEGORY_GENERAL, "TreeCutSearchMax", WideSearchMax).getInt();
		isNoDamageWithoutWeapon = conf.get(conf.CATEGORY_GENERAL, "SetNoDamageWithoutWeapon", isNoDamageWithoutWeapon).getBoolean(isNoDamageWithoutWeapon);
		PachinkoUsesBullet = conf.get(conf.CATEGORY_GENERAL, "PachinkoUsesBullet", PachinkoUsesBullet).getBoolean(PachinkoUsesBullet);
		FishingRodUsesBait = conf.get(conf.CATEGORY_GENERAL, "FishingRodUsesBait", FishingRodUsesBait).getBoolean(FishingRodUsesBait);
		isLampPoweredBySignal = conf.get(conf.CATEGORY_GENERAL, "LampPoweredBySignal", isLampPoweredBySignal).getBoolean(isLampPoweredBySignal);
	}
}
