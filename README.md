# Minecraft Fabric Mod - Ghost Client Menu GUI

Dieses Projekt ist eine Minecraft Fabric Mod, die ein modernes Menu-System für einen Client-Mod bereitstellt.

## Features

- **Modulares Design**: Kategorisierte Module (Combat, Render, Utility)
- **Modern GUI**: Dark-themed Interface mit Toggle-Schaltern
- **Keybind Support**: Menü mit Taste 'P' öffnen
- **Module Management**: Einfaches Aktivieren/Deaktivieren von Features

## Module

### Combat
- AutoClicker
- AimAssist

### Render
- Reach
- Velocity

### Utility
- Search
- Nametags

## Installation

1. Stelle sicher, dass du Fabric Loader installiert hast
2. Füge die Mod in den `mods` Ordner ein
3. Starte Minecraft
4. Drücke 'P' um das Menu zu öffnen

## Struktur

```
src/client/java/com/example/
├── GhostClientMod.java          # Main Client Initializer
├── gui/
│   └── GhostClientScreen.java    # GUI Screen Rendering
└── module/
    ├── Category.java             # Module Categories
    ├── Module.java               # Module Base Class
    └── ModuleManager.java        # Module Manager (Singleton)
```

## Lizenz

Creative Commons Zero v1.0 Universal