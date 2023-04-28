package snownee.passablefoliage.mixin.forge;

import javax.annotation.Nullable;

import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockBehaviour.BlockStateBase;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraftforge.common.extensions.IForgeBlockState;
import snownee.passablefoliage.PassableFoliage;
import snownee.passablefoliage.PassableFoliageCommonConfig;

@Mixin(BlockStateBase.class)
public class BlockStateMixin implements IForgeBlockState {

	private BlockState self() {
		return (BlockState) (Object) this;
	}

	@Override
	public BlockPathTypes getBlockPathType(BlockGetter world, BlockPos pos, @Nullable Mob entity) {
		if (!PassableFoliageCommonConfig.playerOnly && PassableFoliageCommonConfig.modifyPathFinding && PassableFoliage.isPassable(self())) {
			if (entity == null || !PassableFoliage.hasLeafWalker(entity)) {
				return BlockPathTypes.OPEN;
			}
		}
		return self().getBlock().getBlockPathType(self(), world, pos, entity);
	}

}
