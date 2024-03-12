<h1 style="text-align: center">Mod Detection Preventer</h1>

<p align="center">
<img src="src/main/resources/assets/moddetectionpreventer/icon.png">
</p>

<p style="text-align: center">A simple mod that prevents a security vulnerability allowing servers to detect which mods are installed on
the client side.</p>

⚠️ Server-Admins have found a different way, to access Mod-Information. I am not motivated enough to work on this for
now, so your client's mod list can be read even with this mod installed. ⚠️

## The Vulnerability

Minecraft has a feature that allows text (in chat, on signs, or in the bossbar) to be specified by a keybind the user
has set, or a translation key. The Client will then replace the translation key, or the keybind with the stored value.
This can be abused by the server by serving the client a sign with such a placeholder (for example Sodium:
`sodium.option_impact.low`). By immediately closing the sign screen, the client sends the edited text to the server
without ever seeing a sign open screen. The server can then detect wether you have that specific mod installed, by
checking if your client replaced the placeholder with the corresponding text (`sodium.option_impact.low -> Low`). If
you don't have Sodium installed, the placeholder will stay there
(`sodium.option_impact.low -> sodium.option_impact.low`).

This detection method works for any mod that has custom translations.

## The Fix

This mod fixes this issue by simply not resolving any translation or keybind placeholders on signs, except vanilla
ones. This makes it impossible for the server to use this method to detect installed mods.

To verify this works you can test it in a [test world](https://github.com/JustAlittleWolf/ModDetectionPreventer/raw/1.20.4/testWorld.zip).

## Intentions

~~While this feature can be used to prevent harm by detecting cheaters early, it is implemented improperly on some
servers, including [Cytooxien](CytooxienDetectedMods.md). Immediately banning players upon joining, simply because they
have tweakeroo installed, is unacceptable.~~
After a discussion with the developer of Cytooxien, they told me that players won't get banned for using tweakeroo, only kicked repeatedly.
