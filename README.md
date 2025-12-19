# Whac-A-Mole 🔨🐀

A high-performance, full-screen arcade game built with Java Swing. This project features dynamic rendering, realistic graphics, sound effects, and a responsive game loop.

## 🎮 Game Features
* **Full Screen Experience:** Automatically detects screen resolution for immersive gameplay.
* **Dynamic Spawning:** Moles and Bombs "dig" their own holes randomly across the map (no fixed grid).
* **Realistic Graphics:** Uses 3D-style assets for moles, bombs, and terrain.
* **Audio Engine:** Includes sound effects for hits, misses, spawning, and explosions.
* **Game States:** Features a Start Menu, Active Gameplay, and Game Over screen.
* **Score & Leveling:** Difficulty increases (speed up) as you level up.

## 🛠️ Project Structure
```text
WhackAMoleGame/
├── src/
│   ├── game/
│   │   ├── GameFrame.java      # Main Window & Fullscreen logic
│   │   ├── GamePanel.java      # Core Game Loop & Rendering
│   │   └── GameConfig.java     # Global Settings & Dynamic Grid Math
│   ├── model/
│   │   ├── Mole.java           # Entity Logic
│   │   └── Bomb.java           # Trap Logic
│   ├── util/
│   │   ├── SoundPlayer.java    # Audio System (16-bit PCM WAV)
│   │   └── GameTimer.java      # Countdown Logic
│   └── main/
│       └── GameLauncher.java   # Entry Point
└── assets/                     # Graphics & Audio Files
    ├── bg.png
    ├── mole.png
    ├── bomb.png
    ├── hole.png
    ├── hammer.png
    ├── mole.wav
    ├── hit.wav
    ├── bomb.wav
    └── miss.wav