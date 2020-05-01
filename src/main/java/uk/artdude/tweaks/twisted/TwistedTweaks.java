package uk.artdude.tweaks.twisted;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uk.artdude.tweaks.twisted.common.ProxyCommon;
import uk.artdude.tweaks.twisted.common.achievement.TTTriggers;
import uk.artdude.tweaks.twisted.common.addons.TTAddons;
import uk.artdude.tweaks.twisted.common.blocks.TTBlocks;
import uk.artdude.tweaks.twisted.common.command.CommandTT;
import uk.artdude.tweaks.twisted.common.configuration.TTConfiguration;
import uk.artdude.tweaks.twisted.common.creativetabs.TTCreativeTab;

/* Set up the mods core settings. */
@Mod(TwistedTweaks.modID)
public class TwistedTweaks {

    /* Core Mod References */
    public static final String modID = "twistedtweaks";
    /* Set up the CreativeTabs */
    public static ItemGroup creativeTab = new ItemGroup(modID) {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(TTBlocks.LIQUID_VOID);
        }
    };

    /* Set the logger variable */
    public static Logger logger = LogManager.getLogger(modID);

    public TwistedTweaks(){
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, SERVER_SPEC);
    }

    public static final TTConfiguration.ServerConfig SERVER;
    public static final ForgeConfigSpec SERVER_SPEC;
    //public static final ClientConfig CLIENT;
    //public static final ForgeConfigSpec CLIENT_SPEC;

    static {
        final Pair<TTConfiguration.ServerConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(TTConfiguration.ServerConfig::new);
        SERVER_SPEC = specPair.getRight();
        SERVER = specPair.getLeft();
       // final Pair<ClientConfig, ForgeConfigSpec> specPair2 = new ForgeConfigSpec.Builder().configure(ClientConfig::new);
       // CLIENT_SPEC = specPair2.getRight();
        //CLIENT = specPair2.getLeft();
    }

    @Mod.EventHandler
    public void preInit(FMLCommonSetupEvent event) {
        /* Get our logger */
        /* Initialize CreativeTabs */
        creativeTab = new TTCreativeTab(CreativeTabs.getNextID(), modID);

        TTTriggers.init();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        /* Load out addons. */
        TTAddons.init();
    }

    @Mod.EventHandler
    public void serverLoad(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandTT());
    }
}