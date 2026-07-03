# Angry Birds-style Game

## Overview

This project is a clone of the popular Angry Birds game, developed using the libGDX framework. The game allows players to launch birds at structures to defeat pigs, providing engaging gameplay through various levels and mechanics. This project serves not only as an enjoyable game but also as a learning tool for aspiring game developers.

### Goals of the Project

- **Replication of Core Mechanics**: The primary aim is to replicate the core mechanics of Angry Birds, including the physics-based launching system, destructible environments, and various bird types.
- **Learning Experience**: The project provides an opportunity to learn game development concepts, specifically using libGDX, a powerful game development framework.
- **Object-Oriented Design**: It explores principles of object-oriented programming and design patterns, promoting good software design practices.
- **Skill Enhancement**: The project aims to enhance Java programming skills while developing expertise in graphics rendering and game physics.

## Features
### Core Features
- **Basic Gameplay**:
  - Players drag and release birds from a catapult to destroy structures and pigs.
  - Birds follow realistic trajectories based on angle and speed.
  - Pigs and blocks respond dynamically to hits, simulating real-world physics.

- **Bird Types**:
  - **Red Bird**: Standard bird with basic damage.
  - **Bomb Bird**: Explodes on command, causing massive area damage.
  - **Yellow Bird**: Faster bird that can accelerate mid-flight for greater impact.

- **Materials**:
  - Structures are built using materials such as **wood**, **glass**, and **steel**, each with unique durability and destruction mechanics.

- **Levels**:
  - A total of **three levels** are implemented, each with unique layouts, pig placements, and bird selections.
  - Levels increase in difficulty, encouraging strategy development and skill progression.

### Advanced Features
1. **Serialization**:
   - Save and load the current game state, including bird positions, structure integrity, and pig health.
   - Restore progress from saved games using an intuitive menu system.

2. **Special Abilities**:
   - Birds have unique abilities, such as the **Bomb Bird's explosion**, activated mid-flight.
   - Double-click to trigger abilities, adding depth to gameplay.

3. **Physics Simulation**:
   - Powered by **Box2D**, enabling realistic interactions between birds, pigs, and structures.

4. **Winning and Losing Conditions**:
   - Win by eliminating all pigs in the level.
   - Lose when all available birds are used without clearing the level.

5. **Interactive UI**:
   - Intuitive menus for starting new games, resuming saved games, and navigating between levels.

6. **Health and Damage**:
   - Pigs have health indicators displayed above their heads, with different damage thresholds based on their size.
   - Blocks transmit damage to structures above them upon collapse.

## Table of Contents

- [Technologies](#technologies)
- [Installation](#installation)
- [Usage](#usage)
- [Game Structure](#game-structure)
    - [Classes](#classes)
    - [Game Screens](#game-screens)
    - [Gameplay Mechanics](#gameplay-mechanics)
    - [Level Design](#level-design)
- [Contributing](#contributing)
- [Future Enhancements](#future-enhancements)
- [Acknowledgments](#acknowledgments)

## Technologies

- **Java**: The primary programming language used for development, known for its versatility and portability.
- **libGDX**: A cross-platform game development framework that simplifies game creation and deployment, allowing developers to focus on game logic rather than boilerplate code.
- **SpriteBatch**: A libGDX class used for rendering images efficiently, minimizing draw calls for improved performance, particularly in graphics-intensive games.
- **Box2D**: A physics engine integrated with libGDX, used for simulating physics-based interactions in the game, providing realistic movement and collision detection.

## Installation

To get the project up and running on your local machine, follow these steps:

1. **Clone the Repository**:

   ```bash
   clone the git repo
   ```

2. **Set Up Your Environment**:
    - Ensure you have the Java Development Kit (JDK) installed. Preferably JDK 8 or higher.
    - Install an Integrated Development Environment (IDE) like IntelliJ IDEA or Eclipse to manage your project effectively.

3. **Import the Project**:
    - Open your IDE and import the project as a Gradle project, which will automatically resolve dependencies.

4. **Build the Project**:
    - Run the build command in your IDE or from the command line to compile the project:

   ```bash
   ./gradlew build
   ```

5. **Run the Game**:
    - Execute the main class to start the game:

   ```bash
   ./gradlew run
   ```

6. **Explore Game Assets**:
    - Familiarize yourself with the assets used in the game, located in the `core/assets` directory. This includes images, sounds, and other resources necessary for gameplay.

## Usage

- Launch the game to access the main menu, where you can choose to start a new game or resume a previously saved session.
- **New Game**: Select this option to start from Level 1, where you can familiarize yourself with the controls and mechanics.
- **Resume Game**: If you have a saved game, you can continue from where you left off.
- The sliders are used for setting the angle and strength of the launch, providing a more controlled and precise experience. The angle and strength of your launch will determine the outcome.
- Each level has a distinct setup of structures and pigs to defeat, encouraging players to develop and refine their strategies.

## Game Structure

### Classes

1. **AngryBirds**: The main game class responsible for initializing the game, managing the game state, and rendering graphics.
2. **Screens**:
    - **MainMenuScreen**: Displays the main menu with options to start a new game, resume a saved game, or exit.
    - **PlayScreen**: Contains the game logic and visual elements for each level, handling user input, physics, and gameplay mechanics.
    - **LevelSelection**: Allows players to choose a specific level, presenting a list of available levels to play.
    - **ResumeScreen**: Displays saved game slots, enabling players to load previously saved progress and continue their game.
    - **WinningScreen**: Shows a congratulatory message upon successfully completing a level, with options to proceed to the next level or return to the main menu.
    - **LosingScreen**: Displays a retry option when all birds are used, but the pigs remain undefeated.
    - **PauseMenuScreen**: Allows players to pause the game during play, providing options to resume, restart the level, or return to the main menu.

### Game Screens
### Levels

- **Level1**: 
  - Contains the setup for the first level, featuring:
    - **Birds**: 3 Red Birds.
    - **Blocks**: Glass blocks that break easily.
    - **Pigs**: Mechanic pigs with moderate health.
  - This level introduces basic gameplay mechanics, including bird launching and block destruction.

- **Level2**: 
  - Introduces new elements and increased difficulty:
    - **Birds**: 2 Red Birds and 1 Bomb Bird (explodes mid-flight).
    - **Blocks**: A mix of materials, including stronger blocks.
    - **Pigs**: Big pigs with higher health, requiring multiple hits to defeat.
  - Players must adapt their strategy to deal with tougher blocks and pigs.

- **Level3**: 
  - The most challenging level, featuring:
    - **Birds**: 1 Red Bird and 2 Bomb Birds.
    - **Blocks**: A mix of materials, with more complex structures.
    - **Pigs**: Angry pigs with high health and resilience, requiring precise attacks to defeat.
  - This level demands careful planning and effective use of bird abilities to succeed.


### Gameplay Mechanics

- **Bird Types**: Each bird type has unique abilities. For example, a blue bird can split into three smaller birds mid-flight, while a black bird explodes on impact, adding strategic depth to gameplay.
- **Pigs**: Different pig types are present, with varying health levels. Some pigs are protected by blocks that players must destroy first, introducing a puzzle-solving aspect.
- **Physics**: The game uses Box2D for physics simulation, allowing for realistic interactions between birds, pigs, and structures, providing a more immersive experience.

### Level Design

- Levels are designed to gradually increase in difficulty, ensuring that players develop their skills as they progress.
- Each level has specific layouts that require different strategies to complete, promoting replayability and encouraging players to experiment with different approaches.
- Players earn stars based on performance, with achievements tied to score milestones, encouraging competition and exploration of all game mechanics.


### Code of Conduct

Please adhere to the project's code of conduct, ensuring a welcoming and respectful environment for all contributors.

## Future Enhancements

- **More Levels**: Adding additional levels with varying challenges to keep the gameplay fresh and engaging.
- **Power-ups**: Introducing power-ups that players can collect during gameplay to enhance their birds' abilities and add strategic depth.
- **Multiplayer Mode**: Developing a multiplayer mode where players can compete against each other, fostering a competitive community.
- **Enhanced Graphics**: Improving visual elements and animations for a more engaging experience, possibly incorporating more advanced graphical techniques.
- **User Customization**: Allowing players to customize their birds or catapult skins to enhance personalization and engagement.

### Bonus Features

1. **Loading Screen with GIF and Progress Bar**:
   - A visually appealing loading screen is implemented, featuring an animated GIF and a progress bar.
   - This enhances the user experience by providing feedback while the game or a level is loading.

2. **Special Bomb Bird Ability**:
   - The Bomb Bird has a unique ability to explode mid-flight, causing massive area damage.
   - Players can trigger the explosion by double-clicking during the bird's flight, adding a layer of strategy to gameplay.
   - This feature not only increases the game’s fun factor but also differentiates it from the original Angry Birds.

## GITHUB

- Github link:- https://github.com/krinew/AngryBirdsGame

## Acknowledgments

- Special thanks to the libGDX community for their extensive documentation and support, which made this project feasible.
- Inspired by the original Angry Birds game and the principles of physics-based gameplay that make it enjoyable.
- The `GifDecoder` class used in this project was sourced from [Stack Overflow](https://stackoverflow.com) and has been adapted to fit the needs of the game.

---

Feel free to reach out with any questions, feedback, or suggestions for improvement. Happy gaming!
```

### Instructions to Save

1. Copy the above Markdown content.
2. Open your preferred text editor or IDE.
3. Create a new file and name it `README.md`.
4. Paste the copied content into the file.
5. Save the file.

Let me know if you need any further adjustments or additional sections!
