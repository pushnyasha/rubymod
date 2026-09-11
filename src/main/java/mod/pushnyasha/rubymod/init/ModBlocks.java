package mod.pushnyasha.rubymod.init;

import mod.pushnyasha.rubymod.RubyMod;
import mod.pushnyasha.rubymod.blocks.DeepslateRubyOreBlock;
import mod.pushnyasha.rubymod.blocks.RubyOreBlock;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class ModBlocks {
    public static final RubyOreBlock RUBY_ORE_BLOCK = Registry.register(
        BuiltInRegistries.BLOCK,
        ResourceLocation.fromNamespaceAndPath(RubyMod.MOD_ID, "ruby_ore_block"),
        new RubyOreBlock(BlockBehaviour.Properties.of().strength(2.75f).sound(SoundType.STONE))
    );
    public static final DeepslateRubyOreBlock DEEPSLATE_RUBY_ORE_BLOCK = Registry.register(
        BuiltInRegistries.BLOCK,
        ResourceLocation.fromNamespaceAndPath(RubyMod.MOD_ID, "deepslate_ruby_ore_block"),
        new DeepslateRubyOreBlock(BlockBehaviour.Properties.of().strength(4.25f).sound(SoundType.DEEPSLATE))
    );
    public static void registerModBlocks(){}
}