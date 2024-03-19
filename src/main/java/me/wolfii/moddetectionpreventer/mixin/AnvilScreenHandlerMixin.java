package me.wolfii.moddetectionpreventer.mixin;

import me.wolfii.moddetectionpreventer.text.CombinedFilter;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AnvilScreenHandler.class)
public class AnvilScreenHandlerMixin {
    @Redirect(method = "updateResult", at = @At(value = "INVOKE", target = "Lnet/minecraft/text/Text;getString()Ljava/lang/String;"))
    private String preventAnvilModDetection(Text instance) {
        return CombinedFilter.filterKeybindsRecursive(instance).getString();
    }
}
