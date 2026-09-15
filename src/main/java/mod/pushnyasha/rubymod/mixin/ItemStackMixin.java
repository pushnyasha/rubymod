package mod.pushnyasha.rubymod.mixin;

import mod.pushnyasha.rubymod.init.ModItems;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import java.util.function.Consumer;

@Mixin(ItemStack.class)
public class ItemStackMixin {
    @Inject(
        method = "hurtAndBreak", 
        at = @At("HEAD")
    )
    private void catchRubyCoatedGoldArmorBreak(int amount, ServerLevel level, ServerPlayer player, Consumer<ServerPlayer> onBreak, CallbackInfo ci) {
        ItemStack stack = (ItemStack) (Object) this;

        if (player != null && (stack.getDamageValue() + amount) >= stack.getMaxDamage()) {
            net.minecraft.world.item.Item goldReplacement = null;

            if (stack.is(ModItems.RUBY_COATED_GOLD_HELMET)) {
                goldReplacement = Items.GOLDEN_HELMET;
            } else if (stack.is(ModItems.RUBY_COATED_GOLD_CHESTPLATE)) {
                goldReplacement = Items.GOLDEN_CHESTPLATE;
            } else if (stack.is(ModItems.RUBY_COATED_GOLD_LEGGINGS)) {
                goldReplacement = Items.GOLDEN_LEGGINGS;
            } else if (stack.is(ModItems.RUBY_COATED_GOLD_BOOTS)) {
                goldReplacement = Items.GOLDEN_BOOTS;
            }

            
            if (goldReplacement != null) {
                ItemStack goldStack = new ItemStack(goldReplacement);
                
                EquipmentSlot slot = player.getEquipmentSlotForItem(stack);
                
                if (slot.isArmor()) {
                    player.setItemSlot(slot, goldStack);
                } else {
                    player.getInventory().add(goldStack);
                }
            }
        }
    }
}
