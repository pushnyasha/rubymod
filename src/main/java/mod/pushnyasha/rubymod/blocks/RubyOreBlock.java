package mod.pushnyasha.rubymod.blocks;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
public class RubyOreBlock extends DropExperienceBlock {
    public RubyOreBlock(BlockBehaviour.Properties properties) {
        super(UniformInt.of(3, 7), properties);
    }
}