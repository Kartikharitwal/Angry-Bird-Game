# Angry Birds Game

A Java desktop game inspired by Angry Birds, built with libGDX and Box2D. The game lets players launch birds from a catapult, hit block structures, damage pigs, clear levels, save progress, and resume saved games.

## Project Details

- Project type: Desktop 2D physics game
- Language: Java
- Framework: libGDX
- Physics engine: Box2D
- Desktop backend: LWJGL3
- Build tool: Gradle
- Roll No: 2023275

## Features

- Loading screen with animated GIF and progress bar
- Main menu with new game, resume, and exit options
- Level selection screen
- Three playable levels
- Physics-based bird launching
- Power and angle sliders for controlled shots
- Trajectory preview before launch
- Multiple bird types with different behavior
- Multiple pig types with health system
- Destructible blocks and structures
- Collision-based damage using Box2D contact callbacks
- Win and loss screens
- Pause menu
- Save and load system with multiple save slots
- JSON-based save files for restoring game state
- Desktop launch through Gradle and LWJGL3

## Technologies Used

| Technology | Purpose |
| --- | --- |
| Java | Main programming language |
| libGDX | Game framework, rendering, input, UI, screen lifecycle |
| Box2D | Physics simulation, gravity, collisions, impulses |
| LWJGL3 | Desktop backend for running the game on PC |
| Gradle | Build automation and dependency management |
| Scene2D | UI widgets, stages, actors, buttons, sliders |
| JSON Serialization | Save/load game state |

## Requirements

- Java JDK 8 or higher
- Gradle wrapper included in the project
- Windows, Linux, or macOS desktop environment

The project includes `gradlew.bat`, so installing Gradle separately is not required on Windows.

## How To Run

Open PowerShell in the project folder:

```powershell
cd "C:\Users\karti\OneDrive\Desktop\Documents\Angry-Bird-Game\angry birds\Angry_Birds"
```

Run the desktop game:

```powershell
.\gradlew.bat lwjgl3:run
```

Compile only:

```powershell
.\gradlew.bat compileJava
```

Build the full project:

```powershell
.\gradlew.bat build
```

## Project Structure

```text
Angry-Bird-Game/
  angry birds/
    Angry_Birds/
      assets/
      core/
        src/main/java/io/github/AngryBird_2023275/
      lwjgl3/
        src/main/java/io/github/AngryBird_2023275/lwjgl3/
      build.gradle
      settings.gradle
      gradlew.bat
```

## Main Code Flow

```text
Lwjgl3Launcher.main()
  -> creates desktop application
  -> AngryBirds.create()
  -> PlayScreen loading screen
  -> mainMenuScreen
  -> LevelSelection
  -> Level1 / Level2 / Level3
  -> gameplay loop
  -> win, lose, pause, save, or resume screens
```

## Important Classes

| Class | Responsibility |
| --- | --- |
| `Lwjgl3Launcher` | Starts the desktop application |
| `AngryBirds` | Main libGDX game class and screen manager |
| `PlayScreen` | Loading screen with progress bar and GIF |
| `mainMenuScreen` | Main menu screen |
| `LevelSelection` | Allows the player to select a level |
| `Level1`, `Level2`, `Level3` | Playable game levels |
| `BaseBird` | Common bird physics, state, rendering, and launch behavior |
| `RedBird` | Standard bird |
| `BombBird` | Bird with explosion ability |
| `YellowBird` | Bird with speed boost ability |
| `BasePig` | Common pig health, physics, and rendering behavior |
| `MechanicPig`, `BigPig`, `AngryPig` | Different pig types |
| `BaseBlock` | Common block physics, health, and destruction behavior |
| `GlassBlock`, `WoodenBlock`, `IronBlock` | Different block types |
| `MyContactListener` | Handles Box2D collisions and damage calculation |
| `GameState` | Serializable state model |
| `SaveLoadMenu` | Save slot screen |
| `ResumeScreen` | Loads saved games |
| `PauseMenuScreen` | Pause, resume, restart, and menu navigation |

## Gameplay

The player selects a bird, sets launch power and angle, and launches it toward pigs and blocks. The game uses Box2D physics to simulate gravity, movement, collisions, falling blocks, and impact damage.

### Basic Gameplay Steps

1. Start the game.
2. Wait for the loading screen to finish.
3. Open the main menu.
4. Start a new game or resume a saved game.
5. Select a level.
6. Select a bird.
7. Adjust power and angle sliders.
8. Click launch.
9. Destroy pigs using birds and falling blocks.
10. Win by eliminating all pigs before all birds are used.

## Bird Types

### Red Bird

The standard bird. It has normal collision damage and is useful for basic attacks.

### Bomb Bird

A special bird that can explode after being launched. The explosion increases its effect area for a short duration and helps damage structures and pigs.

### Yellow Bird

A special bird that can activate a speed boost after launch. This increases its velocity and impact potential.

## Pig Types

- `MechanicPig`: Basic pig type used in early levels
- `BigPig`: Stronger pig with higher survivability
- `AngryPig`: Tough pig type used for harder gameplay

Each pig has health. When health reaches zero, the pig is removed from the level.

## Block Types

- `GlassBlock`: Easier to break
- `WoodenBlock`: Medium durability
- `WoodenBlock2`: Alternate wooden block asset
- `IronBlock`: Stronger block material

Blocks have health and can be destroyed by bird impacts or structural collisions.

## Physics And Collision System

The game creates a Box2D world with gravity:

```java
world = new World(new Vector2(0, -9.8f), true);
```

Every frame, the physics simulation is updated:

```java
world.step(1 / 60f, 8, 3);
```

Collisions are handled by `MyContactListener`. It checks the type of objects involved in a collision and applies damage accordingly.

Examples of collision handling:

- Bird hits pig: pig loses health based on bird speed and bird type
- Bird hits block: block loses health based on impact force
- Block hits pig: pig takes damage from falling or moving block
- Block hits block: damage can transfer through structures

## Launching System

The player controls launch using power and angle sliders. The launch force is calculated using trigonometry:

```java
forceX = power * cos(angle);
forceY = power * sin(angle);
```

The bird is launched using a Box2D linear impulse:

```java
birdBody.applyLinearImpulse(force, position, true);
```

## Save And Load System

The game supports multiple save slots:

```text
save_slot_1.json
save_slot_2.json
save_slot_3.json
save_slot_4.json
```

The save system stores logical game state such as:

- Bird position
- Bird type
- Bird launch status
- Pig position
- Pig health
- Block position
- Block health
- Block type

When loading, the game recreates runtime objects such as Box2D bodies, textures, and actors from the saved data.

## Win And Loss Conditions

### Win

The player wins when all pigs in the level are destroyed.

### Loss

The player loses when all birds are used and at least one pig is still alive.

## Object-Oriented Concepts Used

- Inheritance: `BaseBird`, `BasePig`, and `BaseBlock` are extended by specific game objects.
- Polymorphism: Lists store base types like `BaseBird`, while runtime objects can be `RedBird`, `BombBird`, or `YellowBird`.
- Encapsulation: Health, position, physics bodies, and textures are managed inside classes.
- Abstraction: Common behavior is placed in abstract base classes.
- Interfaces: The `Pig` interface defines hit behavior.

## Assets

The `assets` folder contains:

- Background images
- Bird and pig textures
- Block textures
- Catapult image
- UI buttons
- Loading animation
- Font files
- Skin files
- Texture atlas files
- Audio files

## Testing

The project includes JUnit and Mockito dependencies. Test classes are present for game objects and level behavior. The libGDX headless backend is included to support testing without launching the full desktop window.

## Future Improvements

- Refactor repeated level logic into a common base level class
- Use libGDX `AssetManager` for better asset loading and disposal
- Add more levels
- Improve collision balancing and scoring
- Add sound effects for collisions and launches
- Add more bird abilities
- Improve UI responsiveness
- Add more unit tests
- Add packaged desktop builds for distribution

## Repository

GitHub repository:

```text
https://github.com/Kartikharitwal/Angry-Bird-Game
```

## Author

Roll No: 2023275

## Acknowledgement

This project is inspired by the Angry Birds gameplay concept and was developed for learning Java game development, libGDX rendering, Box2D physics, object-oriented design, and desktop game architecture.
