package mryurihi.rd.mixin;

import java.io.IOException;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import mryurihi.rd.Run;
import net.minecraft.server.MinecraftServer;


@Mixin(MinecraftServer.class)
public class HijackMain {
	@Inject(method = "main", at = @At("HEAD"), cancellable = true)
	private static void hijack(String[] args, CallbackInfo cb) throws IOException {
		Run.run(args[0], args[1]);
		cb.cancel();
	}
}
