package mod.pushnyasha.rubymod.mixin;

import net.minecraft.core.NonNullList;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RecipeManager.class)
public abstract class CraftingRemainderMixin {

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Inject(method = "getRemainingItemsFor", at = @At("HEAD"), cancellable = true)
    public void blockAndDamagePickaxes(RecipeType<?> recipeType, RecipeInput inventory, Level level, CallbackInfoReturnable<NonNullList<ItemStack>> cir) {
        
        
        if (recipeType != RecipeType.CRAFTING) {
            return;
        }

        boolean hasInvalidPickaxe = false;
        boolean hasPickaxeAtAll = false;

        
        for (int i = 0; i < inventory.size(); ++i) {
            ItemStack stack = inventory.getItem(i);
            if (stack.isEmpty()) continue;

            if (stack.is(ItemTags.PICKAXES) && stack.getItem() instanceof TieredItem tieredItem) {
                hasPickaxeAtAll = true;
                var tier = tieredItem.getTier();
                
                
                if (tier == Tiers.WOOD || tier == Tiers.STONE || tier == Tiers.GOLD) {
                    hasInvalidPickaxe = true;
                }
            }
        }

        
        if (hasPickaxeAtAll && hasInvalidPickaxe) {
            cir.setReturnValue(NonNullList.withSize(inventory.size(), ItemStack.EMPTY));
            return;
        }

        
        if (hasPickaxeAtAll) {
            NonNullList<ItemStack> remaindersList = NonNullList.withSize(inventory.size(), ItemStack.EMPTY);

            for (int i = 0; i < inventory.size(); ++i) {
                ItemStack stack = inventory.getItem(i);
                
                if (stack.isEmpty()) continue;

                if (stack.is(ItemTags.PICKAXES)) {
                    if (stack.isDamageableItem()) {
                        ItemStack damagedCopy = stack.copy();
                        damagedCopy.setCount(1);
                        damagedCopy.setDamageValue(stack.getDamageValue() + 1);
                        
                        
                        if (damagedCopy.getDamageValue() >= damagedCopy.getMaxDamage()) {
                            remaindersList.set(i, ItemStack.EMPTY);
                        } else {
                            remaindersList.set(i, damagedCopy);
                        }
                    }
                } else {
                    Item remainderItem = stack.getItem().getCraftingRemainingItem();
                    remaindersList.set(i, (remainderItem != null) ? new ItemStack(remainderItem) : ItemStack.EMPTY);
                }
            }

            
            cir.setReturnValue(remaindersList);
        }
    }
}
