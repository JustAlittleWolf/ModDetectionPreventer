package me.wolfii.moddetectionpreventer.text;

import me.wolfii.moddetectionpreventer.client.ModDetectionPreventerClient;
import net.fabricmc.loader.api.FabricLoader;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.zip.GZIPInputStream;

public class TranslationFilter {
    private static final Map<String, Set<String>> vanillaTranslations = new HashMap<>();
    private static final String fileLocation = "data" + File.separator + "vanillaTranslationKeys.gzip";

    public static void loadTranslations() {
        FabricLoader.getInstance().getModContainer("moddetectionpreventer").ifPresentOrElse(
                modContainer -> modContainer.findPath(fileLocation).ifPresentOrElse(TranslationFilter::readTranslationsFile,
                        () -> ModDetectionPreventerClient.LOGGER.warn("Could not read translations file. Disabling all translations on signs.")),
                () -> ModDetectionPreventerClient.LOGGER.warn("Could not get modContainer. Disabling all translations on signs."));
    }

    private static void readTranslationsFile(Path translationsPath) {
        try {
            InputStream inputStream = Files.newInputStream(translationsPath);
            GZIPInputStream gzipInputStream = new GZIPInputStream(new BufferedInputStream(inputStream));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(gzipInputStream));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split("\\.", 2);
                Set<String> category = vanillaTranslations.getOrDefault(parts[0], new HashSet<>());
                if (parts.length > 1) category.add(parts[1]);
                vanillaTranslations.put(parts[0], category);
            }
        } catch (Exception e) {
            ModDetectionPreventerClient.LOGGER.warn("There has been an error loading the vanilla translations. Disabling all translations on signs.");
            ModDetectionPreventerClient.LOGGER.warn(e.getMessage(), e);
        }
    }

    public static boolean isVanillaTranslation(String translationKey) {
        String[] parts = translationKey.split("\\.", 2);
        if (parts.length == 1) return vanillaTranslations.containsKey(parts[0]);
        return vanillaTranslations.getOrDefault(parts[0], Set.of()).contains(parts[1]);
    }
}
