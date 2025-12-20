# Whac-A-Mole 🔨🐀

A high-performance, full-screen arcade game built with Java Swing. This project features dynamic rendering, realistic graphics, sound effects, and a responsive game loop.

## 🎮 DEMO
![game_review](/assets/game_preview.png)

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
    ├── hit_mole.png
    ├── hole.png
    ├── bomb.png
    ├── hammer.png
    ├── mole.wav
    ├── hit.wav
    ├── bomb.wav
    └── miss.wav

```

🚀 How to Run
Prerequisites
Java Development Kit (JDK) 8 or higher.

Running via Command Line
Navigate to the project directory:

Bash
```
cd WhackAMoleGame
```
Compile the source code:

Bash
```
javac -d bin src/**/*.java
```
Run the game:

Bash
```
java -cp bin main.GameLauncher
```

Running in VS Code / IntelliJ
Open the folder as a project.

Locate src/main/GameLauncher.java.

Click Run.

⚠️ Assets Note

Ensure your assets folder contains 16-bit PCM WAV files for audio. Standard MP3s or 32-bit Float WAVs may cause UnsupportedAudioFileException in Java.

