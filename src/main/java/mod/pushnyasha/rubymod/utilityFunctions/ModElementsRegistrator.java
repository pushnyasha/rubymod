package mod.pushnyasha.rubymod.utilityFunctions;

import mod.pushnyasha.rubymod.RubyMod;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModElementsRegistrator {
    public static <T extends CreativeModeTab> T registerTab(T tab, String id) {
        ResourceLocation TabID = ResourceLocation.fromNamespaceAndPath(RubyMod.MOD_ID, id);
        T registeredTab = (T) Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, TabID, tab);
        return registeredTab;
    }
    public static <T extends Item> T registerItem(T item, String id) {
        ResourceLocation ItemID = ResourceLocation.fromNamespaceAndPath(RubyMod.MOD_ID, id);
        T registeredItem = (T) Registry.register(BuiltInRegistries.ITEM, ItemID, item);
        return registeredItem;        
    }

    public static <T extends Block> T registerBlock(T block, String id) {
        ResourceLocation BlockID = ResourceLocation.fromNamespaceAndPath(RubyMod.MOD_ID, id);
        T registeredBlock = (T) Registry.register(BuiltInRegistries.BLOCK, BlockID, block);
        return registeredBlock;
    }
}