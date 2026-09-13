package mod.pushnyasha.rubymod.init;

import mod.pushnyasha.rubymod.RubyMod;
import mod.pushnyasha.rubymod.items.MoltenRubyBucketItem;
import mod.pushnyasha.rubymod.items.RubyItem;
import mod.pushnyasha.rubymod.items.RubyPowderItem;
import mod.pushnyasha.rubymod.utilityFunctions.ModElementsRegistrator;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;

public class ModItems {
    public static final Item RUBY_ITEM = ModElementsRegistrator.registerItem(new RubyItem(new Item.Properties()), "ruby");
    public static final BlockItem RUBY_ORE_BLOCK_ITEM = ModElementsRegistrator.registerItem(new BlockItem(ModBlocks.RUBY_ORE_BLOCK, new Item.Properties()), "ruby_ore_block");
    public static final BlockItem DEEPSLATE_RUBY_ORE_BLOCK_ITEM = ModElementsRegistrator.registerItem(new BlockItem(ModBlocks.DEEPSLATE_RUBY_ORE_BLOCK, new Item.Properties()), "deepslate_ruby_ore_block");
    public static final Item RUBY_POWDER_ITEM = ModElementsRegistrator.registerItem(new RubyPowderItem(new Item.Properties()), "ruby_powder");
    public static final Item MOLTEN_RUBY_BUCKET_ITEM = ModElementsRegistrator.registerItem(new MoltenRubyBucketItem(new Item.Properties()), "molten_ruby_bucket");
    public static void registerModItems() {
    }
}