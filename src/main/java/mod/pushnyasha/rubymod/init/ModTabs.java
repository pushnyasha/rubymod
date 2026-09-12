package mod.pushnyasha.rubymod.init;

import mod.pushnyasha.rubymod.RubyMod;
import mod.pushnyasha.rubymod.tabs.MainCreativeTab;
import mod.pushnyasha.rubymod.utilityFunctions.ModElementsRegistrator;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;

public class ModTabs{
    public static final CreativeModeTab MAIN_TAB = ModElementsRegistrator.registerTab(MainCreativeTab.build(), "main_tab");
    public static void registerModTabs(){}
}