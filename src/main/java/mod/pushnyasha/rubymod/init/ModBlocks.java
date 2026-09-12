package mod.pushnyasha.rubymod.init;

import mod.pushnyasha.rubymod.RubyMod;
import mod.pushnyasha.rubymod.blocks.DeepslateRubyOreBlock;
import mod.pushnyasha.rubymod.blocks.RubyOreBlock;
import mod.pushnyasha.rubymod.utilityFunctions.ModElementsRegistrator;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
public class ModBlocks {
    
    public static final ResourceKey<PlacedFeature> RUBY_ORE_PLACED_KEY = ResourceKey.create(
        Registries.PLACED_FEATURE, 
        ResourceLocation.fromNamespaceAndPath(RubyMod.MOD_ID, "ruby_ore_block")
    );
    
    public static final RubyOreBlock RUBY_ORE_BLOCK = ModElementsRegistrator.registerBlock(new RubyOreBlock(BlockBehaviour.Properties.of().strength(2.75f).sound(SoundType.STONE).requiresCorrectToolForDrops()), "ruby_ore_block");
    public static final DeepslateRubyOreBlock DEEPSLATE_RUBY_ORE_BLOCK = ModElementsRegistrator.registerBlock(new DeepslateRubyOreBlock(BlockBehaviour.Properties.of().strength(4.25f).sound(SoundType.DEEPSLATE).requiresCorrectToolForDrops()), "deepslate_ruby_ore_block");
    
    public static void registerWorldGeneration() {
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Decoration.UNDERGROUND_ORES, RUBY_ORE_PLACED_KEY);
    }
    
    public static void registerModBlocks(){}
}