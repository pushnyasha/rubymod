package mod.pushnyasha.rubymod.init;

import mod.pushnyasha.rubymod.RubyMod;
import mod.pushnyasha.rubymod.blocks.DeepslateRubyOreBlock;
import mod.pushnyasha.rubymod.blocks.RubyOreBlock;
import mod.pushnyasha.rubymod.utilityFunctions.ModElementsRegistrator;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class ModBlocks {
    public static final RubyOreBlock RUBY_ORE_BLOCK = ModElementsRegistrator.registerBlock(new RubyOreBlock(BlockBehaviour.Properties.of().strength(2.75f).sound(SoundType.STONE)), "ruby_ore_block");
    public static final DeepslateRubyOreBlock DEEPSLATE_RUBY_ORE_BLOCK = ModElementsRegistrator.registerBlock(new DeepslateRubyOreBlock(BlockBehaviour.Properties.of().strength(4.25f).sound(SoundType.DEEPSLATE)), "deepslate_ruby_ore_block");
    public static void registerModBlocks(){}
}