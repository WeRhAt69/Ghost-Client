# Minecraft Fabric Mod - Ghost Client Menu GUI

Dieses Projekt ist eine Minecraft Fabric Mod für die Version 1.21 (Fabric API Build 26.1.2), die ein hochmodernes, mathematisch zentriertes Menü-System bereitstellt. Das Design basiert auf dem minimalistischen, horizontalen Layout von **Vape Lite**.

## ✨ Features
* **Modernes Vape-Lite GUI:** Dunkles Anthrazit-Design mit einer vertikalen Sidebar links für Kategorien und breiten Modul-Zeilen rechts mit animierten Toggle-Schaltern.
* **Erweitertes Einstellungs-System:** Ein Rechtsklick auf ein Modul klappt ein Untermenü aus. Unterstützt stufenlose Schieberegler (`SliderSetting`) und Schalter (`BooleanSetting`).
* **Keybind Support:** Das Menü öffnet sich im Spiel standardmäßig über die Taste **Right Shift** (Rechte Umschalttaste).
* **Mathematische Algorithmen:** Eingebaute Schutzmechanismen wie Gauß-Verteilungen (Box-Muller-Transformation) für menschliche Klick-Intervalle und sphärische Vektor-Interpolation für weiche Kamerabewegungen.

## 📋 Modul-Übersicht & Status

### ⚔️ Combat (Kampf)
* **AutoClicker** (`Vollständig`): Automatische Linksklicks mit normalverteilten, organischen Verzögerungen.
* **AimAssist** (`Vollständig`): Sanfte Fadenkreuz-Annäherung mit einstellbarer Reichweite, Geschwindigkeit und einem mathematischen FOV-Sichtkreis.
* **Reach** (`Strukturell fertig`): Erhöht die Reichweite über ein konfigurierbares Slider-Setting (Standard: 3.6 Blöcke).
* *TriggerBot* (`Platzhalter`): Automatische Interaktion bei Fadenkreuz-Fokus (Code-Skelett aktiv).
* *Teams* (`Platzhalter`): Ignoriert Teammitglieder bei Berechnungen (Code-Skelett aktiv).

### 🏃 Movement (Bewegung)
* **Velocity** (`Strukturell fertig`): Reduziert den Rückstoß prozentual über horizontale und vertikale Schieberegler.
* *Sprint* (`Platzhalter`): Hält den Charakter im dauerhaften Sprint-Zustand (Code-Skelett aktiv).
* *Fly* (`Platzhalter`): Modifikation lokaler Raumkoordinaten (Code-Skelett aktiv).
* *Speed* (`Platzhalter`): Verstärkung horizontaler Bewegungsvektoren (Code-Skelett aktiv).

### 👁️ Render (Visuelles)
* *ESP / Chams / Fullbright* (`Platzhalter`): Visuelle Overlines, Shader-Dämpfungen und Helligkeits-Optionen (Code-Skelette aktiv).

### 🛠️ Utility (Hilfsmittel)
* **Search / Nametags** (`Strukturell fertig`): Als funktionstüchtige Ein/Aus-Schalter in der Benutzeroberfläche registriert.
* *FastPlace / AntiAFK* (`Platzhalter`): Reduzierung von Klick-Cooldowns und Idle-Vermeidung (Code-Skelette aktiv).

## 📂 Ordnerstruktur

```text
src/client/java/com/example/
├── GhostClientMod.java          # Main Client Initializer (Keybind & Ticks)
├── gui/
│   └── GhostClientScreen.java    # Vape-Lite GUI Screen Layout & Maus-Handler
└── module/
    ├── Category.java             # Enum für die 4 Bereiche (Combat, Movement, Render, Utility)
    ├── Module.java               # Abstrakte Modul-Basisklasse
    ├── ModuleManager.java        # Central Singleton Registry & Platzhalter-Verwaltung
    ├── Setting.java              # Basisklasse für Einstellungen
    ├── SliderSetting.java        # Schieberegler für double-Werte
    ├── BooleanSetting.java       # Ja/Nein-Umschalter
    │
    ├── combat/                   # AutoClicker.java, AimAssist.java, Reach.java
    ├── movement/                 # Velocity.java
    └── utility/                  # Search.java, Nametags.java
```

## 🚀 Installation & Kompilierung
1. Öffne das Projekt in deinem GitHub Codespace.
2. Kompiliere die Modifikation über das Terminal mit dem Befehl: `./gradlew build`
3. Die fertige Datei liegt nach erfolgreichem Build unter `build/libs/`.
4. Füge die `.jar`-Datei in deinen Minecraft `mods`-Ordner ein.
5. Drücke im Spiel **Right Shift**, um das Menü zu öffnen.

## 📄 Lizenz
Creative Commons Zero v1.0 Universal (CC0 1.0) - Public Domain Dedication
