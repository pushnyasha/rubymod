package mod.pushnyasha.rubymod.mixin;

import mod.pushnyasha.rubymod.init.ModItems;
import mod.pushnyasha.rubymod.items.MoltenRubyBucketItem;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.inventory.ItemCombinerMenu;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AnvilMenu.class)
public abstract class AnvilMenuMixin extends ItemCombinerMenu {
    @Shadow DataSlot cost;
    public AnvilMenuMixin(net.minecraft.world.inventory.MenuType<?> type, int containerId, net.minecraft.world.entity.player.Inventory playerInventory, net.minecraft.world.inventory.ContainerLevelAccess access) {
        super(type, containerId, playerInventory, access);
    }

    @Inject(method = "createResult", at = @At("HEAD"), cancellable = true)
    private void checkRubyBucketRepair(CallbackInfo ci) {
        ItemStack leftSlot = this.inputSlots.getItem(0);
        ItemStack rightSlot = this.inputSlots.getItem(1);
        if (leftSlot.isEmpty() || rightSlot.isEmpty()) return;
        boolean isRubyArmor = leftSlot.is(ModItems.RUBY_COATED_GOLD_HELMET) || leftSlot.is(ModItems.RUBY_COATED_GOLD_CHESTPLATE) || leftSlot.is(ModItems.RUBY_COATED_GOLD_LEGGINGS) || leftSlot.is(ModItems.RUBY_COATED_GOLD_BOOTS);
        if (isRubyArmor && rightSlot.is(ModItems.MOLTEN_RUBY_BUCKET_ITEM)) {
            int amount = MoltenRubyBucketItem.getFluidAmount(rightSlot);
            if (amount < 64) {
                this.resultSlots.setItem(0, ItemStack.EMPTY);
                this.setData(0,0);
                ci.cancel();
                return;
            }
            if (leftSlot.getDamageValue() > 0) {
                ItemStack repairedArmor = leftSlot.copy();
                repairedArmor.setDamageValue(0);
                this.resultSlots.setItem(0, repairedArmor);
                this.setData(0, 2);
                ci.cancel();
            }
        }
    }
        @Inject(method = "onTake", at = @At("HEAD"))
    private void returnEmptyBucketOnTake(net.minecraft.world.entity.player.Player player, ItemStack stack, CallbackInfo ci) {
        ItemStack leftSlot = this.inputSlots.getItem(0);
        ItemStack rightSlot = this.inputSlots.getItem(1);
        if (!leftSlot.isEmpty() && !rightSlot.isEmpty() && rightSlot.is(ModItems.MOLTEN_RUBY_BUCKET_ITEM)) {
            int amount = MoltenRubyBucketItem.getFluidAmount(rightSlot);
            if (amount == 64) {
                this.inputSlots.setItem(1, new ItemStack(net.minecraft.world.item.Items.BUCKET));
            }
        }
    }
}
