package me.wolfii.moddetectionpreventer.text;

import java.util.List;

public class KeybindFilter {
    private static final List<String> vanillaKeybindings = List.of("key.sneak", "key.sprint", "key.forward",
            "key.left", "key.back", "key.right", "key.jump", "key.sneak", "key.sprint", "key.inventory",
            "key.swapOffhand", "key.drop", "key.use", "key.attack", "key.pickItem", "key.chat", "key.playerlist",
            "key.command", "key.socialInteractions", "key.screenshot", "key.togglePerspective", "key.smoothCamera",
            "key.fullscreen", "key.spectatorOutlines", "key.advancements", "key.hotbar.1", "key.hotbar.2",
            "key.hotbar.3", "key.hotbar.4", "key.hotbar.5", "key.hotbar.6", "key.hotbar.7", "key.hotbar.8",
            "key.hotbar.9", "key.saveToolbarActivator", "key.loadToolbarActivator");

    public static boolean isVanillaKeybinding(String keyBinding) {
        return vanillaKeybindings.contains(keyBinding);
    }
}
