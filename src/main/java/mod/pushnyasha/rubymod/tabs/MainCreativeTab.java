package mod.pushnyasha.rubymod.tabs;

import mod.pushnyasha.rubymod.RubyMod;
import mod.pushnyasha.rubymod.init.ModBlocks;
import mod.pushnyasha.rubymod.init.ModItems;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class MainCreativeTab {
    public static CreativeModeTab build(){
        return FabricItemGroup.builder()
            .title(Component.translatable("tabs.rubymod.main_tab"))
            .icon(() -> new ItemStack(ModItems.RUBY_ITEM))
            .displayItems((itemDisplayParameters, output) -> {
                output.accept(ModItems.RUBY_ITEM);
                output.accept(ModItems.RUBY_POWDER_ITEM);
                output.accept(ModItems.RUBY_ORE_BLOCK_ITEM);
                output.accept(ModItems.DEEPSLATE_RUBY_ORE_BLOCK_ITEM);
            })
            .build();
    }
}