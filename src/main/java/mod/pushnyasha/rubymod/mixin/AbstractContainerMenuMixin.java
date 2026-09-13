package mod.pushnyasha.rubymod.mixin;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.AbstractFurnaceMenu;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.Container;
import mod.pushnyasha.rubymod.items.MoltenRubyBucketItem;
import mod.pushnyasha.rubymod.init.ModItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractContainerMenu.class)
public class AbstractContainerMenuMixin {

    @Inject(method = "clicked", at = @At("HEAD"), cancellable = true)
    private void onSlotClicked(int slotId, int button, ClickType clickType, Player player, CallbackInfo ci) {
        AbstractContainerMenu menu = (AbstractContainerMenu) (Object) this;

        if (menu instanceof AbstractFurnaceMenu furnaceMenu) {
            if (slotId >= 0 && slotId < furnaceMenu.slots.size()) {
                Slot slot = furnaceMenu.getSlot(slotId);
                Container container = slot.container;

                ItemStack rawInput = container.getItem(0);
                ItemStack fuelStack = container.getItem(1);
                ItemStack resultStack = container.getItem(2);

                if (resultStack.getItem() instanceof MoltenRubyBucketItem) {
                    int currentAmount = MoltenRubyBucketItem.getFluidAmount(resultStack);

                    if (currentAmount > 0 && currentAmount < 64) {
                        int containerSlotIndex = slot.getContainerSlot();

                        if (containerSlotIndex == 1 || containerSlotIndex == 2) {
                            if (rawInput.isEmpty()) {
                                container.setItem(0, new ItemStack(ModItems.RUBY_POWDER_ITEM, currentAmount));
                            } else if (rawInput.is(ModItems.RUBY_POWDER_ITEM)) {
                                rawInput.grow(currentAmount);
                            }

                            container.setItem(1, ItemStack.EMPTY);
                            container.setItem(2, ItemStack.EMPTY);

                            // ЧИСТОЕ ОБНУЛЕНИЕ ПЕЧКИ БЕЗ ACCESS WIDENER
                            furnaceMenu.setData(0, 0); // Обнуляем litTime (печка тухнет)
                            furnaceMenu.setData(1, 0); // Обнуляем litDuration
                            furnaceMenu.setData(2, 0); // Обнуляем cookingProgress (стрелочка сбрасывается)

                            furnaceMenu.setCarried(new ItemStack(Items.LAVA_BUCKET));
                            furnaceMenu.broadcastChanges();

                            ci.cancel();
                        }
                    }
                }
            }
        }
    }
}
