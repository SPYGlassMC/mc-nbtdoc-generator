package yurihaia.rd.mixin;

import java.io.IOException;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.server.Main;
import yurihaia.rd.Run;

@Mixin(Main.class)
public class HijackMain {
	@Inject(method = "main", at = @At("HEAD"), cancellable = true)
	private static void hijack(String[] args, CallbackInfo cb) throws IOException {
		Run.run("%MAPPINGS_FILE_PATH%", "%GENERATED_DIR_PATH%");
		cb.cancel();
	}
}
