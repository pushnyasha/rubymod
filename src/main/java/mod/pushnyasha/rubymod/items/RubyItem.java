package mod.pushnyasha.rubymod.items;

import net.minecraft.world.item.Item;

public class RubyItem extends Item {
    
    public RubyItem(Properties properties) {
        super(properties.rarity(net.minecraft.world.item.Rarity.COMMON));
    }
}