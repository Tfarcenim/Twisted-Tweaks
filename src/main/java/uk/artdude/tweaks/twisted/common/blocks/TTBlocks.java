package uk.artdude.tweaks.twisted.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;
import uk.artdude.tweaks.twisted.common.blocks.tileentity.TileEntityTwistedTorch;
import uk.artdude.tweaks.twisted.common.blocks.tileentity.TileEntityTwistedTorchLit;
import uk.artdude.tweaks.twisted.common.configuration.TTConfiguration;
import uk.artdude.tweaks.twisted.common.creativetabs.TTCreativeTab;
import uk.artdude.tweaks.twisted.common.tileentity.TileEntityLiquidVoid;

@Mod.EventBusSubscriber
public class TTBlocks
{
    @ObjectHolder("twistedtweaks:liquid_void")
    public static Block LIQUID_VOID = Blocks.AIR;

    @ObjectHolder("minecraft:torch")
    static Block TWISTED_TORCH = Blocks.TORCH;

    @ObjectHolder("twistedtweaks:unlit_torch")
    public static Block TWISTED_UNLIT_TORCH = Blocks.TORCH;

    @ObjectHolder("twistedtweaks:glowstone_torch")
    private static Block GLOWSTONE_TORCH = Blocks.TORCH;

    @SubscribeEvent
    public static void onRegisterBlocks(RegistryEvent.Register<Block> event)
    {
        IForgeRegistry<Block> registry = event.getRegistry();

        // Check to see if the user has enabled the Liquid Void.
        registry.register(new LiquidVoid().setRegistryName("twistedtweaks", "liquid_void").setRegistryName("liquid_void"));
        GameRegistry.registerTileEntity(TileEntityLiquidVoid.class, new ResourceLocation("twistedtweaks", "liquid_void"));

        registry.register(new BlockTwistedTorch(true).setRegistryName("minecraft", "torch").setTranslationKey("torch"));
        registry.register(new BlockTwistedTorch(false).setRegistryName("twistedtweaks", "unlit_torch").setTranslationKey("unlit_torch"));
        registry.register(new BlockGlowstoneTorch().setRegistryName("twistedtweaks", "glowstone_torch").setTranslationKey("glowstone_torch"));
        GameRegistry.registerTileEntity(TileEntityTwistedTorch.class, new ResourceLocation("twistedtweaks", "torch"));
        GameRegistry.registerTileEntity(TileEntityTwistedTorchLit.class, new ResourceLocation("twistedtweaks", "torch_lit"));
    }

    @SubscribeEvent
    public static void onRegisterItemBlocks(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();{
            registry.register(new BlockItem(LIQUID_VOID).setRegistryName("twistedtweaks", "liquid_void"));
        }
        registry.register(new BlockItem(TWISTED_UNLIT_TORCH).setRegistryName("twistedtweaks", "unlit_torch"));
        registry.register(new BlockItem(GLOWSTONE_TORCH).setRegistryName("twistedtweaks", "glowstone_torch"));

    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event)
    {
        // Check to see if the user has enabled the Liquid Void.
        if (TTConfiguration.blocks.enableLiquidVoid)
        {
            registerBlock(new LiquidVoid(), "liquid_void");
            registry.register(new LiquidVoid().setRegistryName("twistedtweaks", "liquid_void").setRegistryName("liquid_void"));
            GameRegistry.registerTileEntity(TileEntityLiquidVoid.class, new ResourceLocation("twistedtweaks", "liquid_void"));
        }
    }

    public static Block registerBlock(Block block, String name)
    {
        BlockItem itemBlock = new BlockItem(block, new Item.Properties().group(TTCreativeTab.instance));
        block.setRegistryName(name);
        itemBlock.setRegistryName(name);
        ForgeRegistries.BLOCKS.register(block);
        ForgeRegistries.ITEMS.register(itemBlock);
        return block;
    }
}