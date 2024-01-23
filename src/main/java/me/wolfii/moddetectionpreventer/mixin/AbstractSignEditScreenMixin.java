package me.wolfii.moddetectionpreventer.mixin;

import me.wolfii.moddetectionpreventer.text.KeybindFilter;
import net.minecraft.client.gui.screen.ingame.AbstractSignEditScreen;
import net.minecraft.text.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.function.Function;
import java.util.stream.Stream;

@Mixin(AbstractSignEditScreen.class)
public class AbstractSignEditScreenMixin {

    @Redirect(method = "<init>(Lnet/minecraft/block/entity/SignBlockEntity;ZZLnet/minecraft/text/Text;)V", at=@At(value = "INVOKE", target = "Ljava/util/stream/Stream;map(Ljava/util/function/Function;)Ljava/util/stream/Stream;"))
    private Stream<String> removeCustomKeybinds(Stream<Text> instance, Function<? super Text, ? extends Text> function) {
        return instance.map(message -> filterKeybindsRecursive(message).getString());
    }

    @Unique
    private Text filterKeybindsRecursive(Text message) {
        MutableText filtered;
        if (message.getContent() instanceof KeybindTextContent keybindTextContent) {
            if (KeybindFilter.isVanillaKeybinding(keybindTextContent.getKey())) {
                filtered = MutableText.of(keybindTextContent);
            } else {
                filtered = MutableText.of(new LiteralTextContent(keybindTextContent.getKey()));
            }
        } else {
            filtered = MutableText.of(message.getContent());
        }
        filtered.setStyle(message.getStyle());
        for (Text sibling : message.getSiblings()) {
            filtered.append(filterKeybindsRecursive(sibling));
        }
        return filtered;
    }
}
