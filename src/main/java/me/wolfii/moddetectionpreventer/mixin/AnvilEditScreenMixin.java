package me.wolfii.moddetectionpreventer.mixin;

import me.wolfii.moddetectionpreventer.text.CombinedFilter;
import net.minecraft.client.gui.screen.ingame.AnvilScreen;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AnvilScreen.class)
public class AnvilEditScreenMixin {
    @Redirect(method = "onSlotUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/text/Text;getString()Ljava/lang/String;"))
    private String preventAnvilItemNameChangeModDetection(Text instance) {
        return CombinedFilter.filterKeybindsRecursive(instance).getString();
    }

    @Redirect(method = "onRenamed", at = @At(value = "INVOKE", target = "Lnet/minecraft/text/Text;getString()Ljava/lang/String;"))
    private String preventAnvilItemNameCheckModDetection(Text instance) {
        return CombinedFilter.filterKeybindsRecursive(instance).getString();
    }
}
