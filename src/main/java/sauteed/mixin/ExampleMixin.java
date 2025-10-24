package sauteed.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.minecraft.block.LeavesBlock.PERSISTENT;

@Mixin(LeavesBlock.class)
public class ExampleMixin {

	@Inject(at = @At("HEAD"), method = "randomTick", cancellable = true)
	private void tick(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
		if (state.get(PERSISTENT) == false)
			world.setBlockState(pos, state.with(PERSISTENT, true));
		ci.cancel();
	}
}