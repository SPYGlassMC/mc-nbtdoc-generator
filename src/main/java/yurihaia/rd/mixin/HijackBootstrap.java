package yurihaia.rd.mixin;

import java.io.IOException;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.Bootstrap;
import yurihaia.rd.Run;

@Mixin(Bootstrap.class)
public class HijackBootstrap {
	@Inject(method = "initialize", at = @At("TAIL"), cancellable = true)
	private static void hijack(CallbackInfo cb) throws IOException {
		Run.run("%MAPPINGS_FILE_PATH%", "%GENERATED_DIR_PATH%");
		cb.cancel();
	}
}
