package me.wolfii.moddetectionpreventer.text;

import net.minecraft.text.*;

public class CombinedFilter {
    public static Text filterKeybindsRecursive(Text message) {
        MutableText filtered = MutableText.of(message.getContent());
        if (message.getContent() instanceof KeybindTextContent keybindTextContent) {
            String keybind = keybindTextContent.getKey();
            if (KeybindFilter.isVanillaKeybinding(keybind)) {
                filtered = MutableText.of(keybindTextContent);
            } else {
                filtered = MutableText.of(new PlainTextContent.Literal(keybind));
            }
        }
        if (message.getContent() instanceof TranslatableTextContent translatableTextContent) {
            String translationKey = translatableTextContent.getKey();
            if (TranslationFilter.isVanillaTranslation(translationKey)) {
                filtered = MutableText.of(translatableTextContent);
            } else {
                filtered = MutableText.of(new PlainTextContent.Literal(translationKey));
            }
        }
        filtered.setStyle(message.getStyle());
        for (Text sibling : message.getSiblings()) {
            filtered.append(filterKeybindsRecursive(sibling));
        }
        return filtered;
    }
}
