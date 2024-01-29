package me.wolfii.moddetectionpreventer.client;

import me.wolfii.moddetectionpreventer.text.TranslationFilter;
import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModDetectionPreventerClient implements ClientModInitializer {
    public static Logger LOGGER = LoggerFactory.getLogger("ModDetectionPreventer");
    public void onInitializeClient() {
        TranslationFilter.loadTranslations();
    }
}
