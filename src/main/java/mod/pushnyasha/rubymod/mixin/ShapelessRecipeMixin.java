package mod.pushnyasha.rubymod.mixin;

import mod.pushnyasha.rubymod.init.ModItems;
import mod.pushnyasha.rubymod.items.MoltenRubyBucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.ShapelessRecipe;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ShapelessRecipe.class)
public class ShapelessRecipeMixin {

    @Inject(method = "matches", at = @At("TAIL"), cancellable = true)
    private void strictRubyBucketCheck(CraftingInput input, Level level, CallbackInfoReturnable<Boolean> cir) {
        
        if (!cir.getReturnValueZ()) return;

        boolean hasRubyBucket = false;
        boolean isBucketFull = false;
        
        for (int i = 0; i < input.size(); ++i) {
            ItemStack stack = input.getItem(i);
            if (stack.isEmpty()) continue;

            if (stack.is(ModItems.MOLTEN_RUBY_BUCKET_ITEM)) {
                hasRubyBucket = true;
                if (MoltenRubyBucketItem.getFluidAmount(stack) == 64) {
                    isBucketFull = true;
                }
                break;
            }
        }
        
        if (hasRubyBucket && !isBucketFull) {
            cir.setReturnValue(false);
        }
    }
}
