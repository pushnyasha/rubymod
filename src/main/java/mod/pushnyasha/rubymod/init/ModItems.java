package mod.pushnyasha.rubymod.init;

import mod.pushnyasha.rubymod.RubyMod;
import mod.pushnyasha.rubymod.items.RubyItem;
import mod.pushnyasha.rubymod.items.RubyPowderItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;

public class ModItems {
    public static final Item RUBY_ITEM = Registry.register(
            BuiltInRegistries.ITEM,
            ResourceLocation.fromNamespaceAndPath(RubyMod.MOD_ID, "ruby"),
            new RubyItem(new Item.Properties()));
    public static final BlockItem RUBY_ORE_BLOCK_ITEM = Registry.register(
        BuiltInRegistries.ITEM,
        ResourceLocation.fromNamespaceAndPath(RubyMod.MOD_ID, "ruby_ore_block"),
        new BlockItem(ModBlocks.RUBY_ORE_BLOCK, new Item.Properties())
    );
    public static final BlockItem DEEPSLATE_RUBY_ORE_BLOCK_ITEM = Registry.register(
            BuiltInRegistries.ITEM,
            ResourceLocation.fromNamespaceAndPath(RubyMod.MOD_ID, "deepslate_ruby_ore_block"),
            new BlockItem(ModBlocks.DEEPSLATE_RUBY_ORE_BLOCK, new Item.Properties())
    );
    public static final Item RUBY_POWDER_ITEM = Registry.register(
            BuiltInRegistries.ITEM,
            ResourceLocation.fromNamespaceAndPath(RubyMod.MOD_ID, "ruby_powder"),
            new RubyPowderItem(new Item.Properties()));

    public static void registerModItems() {
    }
}