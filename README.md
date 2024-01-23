<h1 style="text-align: center">Mod Detection Preventer</h1>

<p align="center">
<img src="src/main/resources/assets/moddetectionpreventer/icon.png">
</p>

<p style="text-align: center">A simple mod that prevents a security vulnerability allowing servers to detect which mods are installed on
the client side.</p>


## The Vulnerability

Minecraft has a feature that allows text (in chat, on signs, or in the bossbar) to be specified by a keybind the user
has set. The Client will then replace the translation key of the keybind with the actual key that is used. This can
be abused by the server by serving the client a sign, with such a keybind placeholder (for example Sodium:
`sodium.option_impact.low`). By immediately closing the sign screen, the client sends the edited text to the server,
without ever seeing a sign open screen. The server can then detect wether you have that
specific mod installed, by checking if your client replaced the translation key with the keybind you have set
(`sodium.option_impact.low -> Low`) . If you don't have Sodium installed, the translation key will stay there,
(`sodium.option_impact.low -> sodium.option_impact.low`).

Obviously this only works for mods that have a keybind set, and also very few servers use this technique.

## The Fix

This mod fixes this issue, by simply not resolving any keybinds on signs, except vanilla ones. This makes it impossible
for the server to use this method to detect installed mods.

## Intentions

While this feature can be used to prevent harm, by detecting cheaters early, it is implemented improperly on some
servers, including [Cytooxien](CytooxienDetectedMods.md). Immediately banning players upon joining, simply because they
have tweakeroo installed, is unacceptable.
