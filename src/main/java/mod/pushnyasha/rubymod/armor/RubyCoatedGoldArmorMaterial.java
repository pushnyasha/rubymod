package mod.pushnyasha.rubymod.armor;

import java.util.EnumMap;

import mod.pushnyasha.rubymod.init.ModItems;
import mod.pushnyasha.rubymod.items.MoltenRubyBucketItem;
import mod.pushnyasha.rubymod.utilityFunctions.ModElementsRegistrator;
import net.fabricmc.fabric.api.recipe.v1.ingredient.FabricIngredient;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.SoundType;

public class RubyCoatedGoldArmorMaterial {
    public static final Holder<ArmorMaterial> RUBY_COATED_GOLD_ARMOR_MATERIAL = ModElementsRegistrator.registerArmorMaterial(
    Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
        map.put(ArmorItem.Type.HELMET, 3);
        map.put(ArmorItem.Type.CHESTPLATE, 8);
        map.put(ArmorItem.Type.LEGGINGS, 6);
        map.put(ArmorItem.Type.BOOTS, 3);
    }),
    15,
    SoundEvents.ARMOR_EQUIP_DIAMOND,
    2.5F,
    0.01F,
    () -> {
        ItemStack fullMoltenRubyBucket = new ItemStack(ModItems.MOLTEN_RUBY_BUCKET_ITEM);
        MoltenRubyBucketItem.setFluidAmount(fullMoltenRubyBucket, 64);
        return Ingredient.of(fullMoltenRubyBucket);
    },
    "ruby-coated_gold_armor"
    );
}