package mod.pushnyasha.rubymod.init;

import mod.pushnyasha.rubymod.RubyMod;
import mod.pushnyasha.rubymod.tabs.MainCreativeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;

public class ModTabs{
    public static final CreativeModeTab MAIN_TAB = Registry.register(
        BuiltInRegistries.CREATIVE_MODE_TAB,
        ResourceLocation.fromNamespaceAndPath(RubyMod.MOD_ID, "main_tab"),
        MainCreativeTab.build()
    );
    public static void registerModTabs(){}
}