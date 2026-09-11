package mod.pushnyasha.rubymod;

import mod.pushnyasha.rubymod.init.ModBlocks;
import mod.pushnyasha.rubymod.init.ModItems;
import mod.pushnyasha.rubymod.init.ModTabs;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RubyMod implements ModInitializer {
    public static final String MOD_ID = "rubymod";
    @Override
    public void onInitialize() {
        ModBlocks.registerModBlocks();
        ModItems.registerModItems();
        ModTabs.registerModTabs();
    }
}
