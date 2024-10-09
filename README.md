# ⚓ Battleship

## 📖 Overview

Welcome to the **Battleship Game**! 🛳️ This is a **console-based game** written in Java. The goal is to sink all of your opponent's ships before they sink yours. Guess the locations of the hidden ships, and may the best player win! 🎯

---

![Banner](https://i.postimg.cc/7L6JjS3H/Battleship-Terminal.png)

---

## 🕹️ How to Play

- The game is played on a **7x12 grid** 📊.
- Each player gets **3 ships** 🚢 randomly placed on the grid.
- Take turns guessing the locations of your opponent’s ships using coordinates.
- The first player to sink all the ships is the **winner**! 🏆

### 🎮 Game Flow:
1. **Ship Placement**: Ships are automatically placed on the grid for both players 🗺️.
2. **Turns**: Players alternate turns guessing ship locations by entering coordinates (e.g., `5,4`) 📍.
3. **Hit/Miss Feedback**:
   - A hit is marked with `"="` 💥.
   - A miss is marked with `"♒︎"` 🌊.
4. **Winning**: Sink all your opponent's ships to win! 🏁

---

## 🌟 Features

- **🎲 Random Ship Placement**: Ships are randomly positioned horizontally, so no two games are the same!
- **👨‍🚀 Turn-based Play**: Players take turns guessing ship locations.
- **💥 Hit and Miss Indicators**: Successful hits are shown as `"="`, while misses are marked as `"♒︎"`.
- **🧹 Clear Terminal**: The screen is cleared between each turn to maintain suspense (works on most systems).
- **✅ Input Validation**: Ensures player inputs are valid within the grid dimensions.

---

## 🖥️ System Requirements

- **Java 8+** is required to run the game ☕.
- Works on **Windows, macOS, and Linux**.

---

## ⚙️ Setup Instructions

### 1. Clone the Repository
First, clone the repository to your local machine:
```bash
git clone https://github.com/yourusername/battleship-game-java.git
```

### 2. Compile the Code
Navigate to the project directory and compile the source files:
```bash
javac Project/Battleship.java
```

### 3. Run the Game
Run the game with the following command:
```bash
java Project.Battleship
```

---

## 📜 How to Play (Detailed Instructions)

1. **Game Start**: 
   - Ships are automatically placed for both players 🎲.
   
2. **Input Format**:
   - Enter the row and column in the format `row,column` (e.g., `5,4`) 📝.
   - Rows range from **1 to 7**, and columns range from **1 to 12**.

3. **Turn-based Gameplay**:
   - Players take turns guessing, and the board updates after each guess 🎯.
   - You’ll see hits (`=`) and misses (`♒︎`) after each turn.

4. **Winning**:
   - The game ends when one player sinks all of their opponent’s ships 💥.

---

## 🛠️ Customization Options

### 1. Grid Size
To change the grid size, modify the array dimensions in the code. The current size is **7x12** 🗺️.

### 2. Number of Ships
You can adjust the number of ships by changing the value in the `generateArray()` method (default is **3 ships**) 🚢.

### 3. Ship Placement
The current version only supports horizontal ship placements. You can modify the ship generation logic to support vertical or diagonal placements as well 🛠️.

---

## 💡 Example Output

```
=============================================
 ♒︎ ♒︎ ♒︎ ♒︎  1  1  1  1  1 ♒︎ ♒︎ ♒︎
 ♒︎ ♒︎ ♒︎ ♒︎ ♒︎ ♒︎ ♒︎ ♒︎ ♒︎ ♒︎ ♒︎ ♒︎
 ♒︎ ♒︎ ♒︎ ♒︎ ♒︎ ♒︎ ♒︎ ♒︎ ♒︎ ♒︎ ♒︎ ♒︎
 ♒︎ ♒︎ ♒︎ ♒︎ ♒︎ ♒︎ ♒︎ ♒︎ ♒︎ ♒︎ ♒︎ ♒︎
 ♒︎ ♒︎ ♒︎  1  1  1  1  1  1  1  1 ♒︎
 ♒︎ ♒︎ ♒︎ ♒︎ ♒︎ ♒︎  1  1  1  1  1  1
 ♒︎ ♒︎ ♒︎ ♒︎ ♒︎ ♒︎ ♒︎ ♒︎ ♒︎ ♒︎ ♒︎ ♒︎
=============================================
 ♒︎ ♒︎ ♒︎ ♒︎ ♒︎ ♒︎ ♒︎ ♒︎ ♒︎ ♒︎ ♒︎ ♒︎
 ♒︎ ♒︎ ♒︎ ♒︎ ♒︎ ♒︎ ♒︎ ♒︎ ♒︎ ♒︎ ♒︎ ♒︎
 ♒︎ ♒︎ ♒︎ ♒︎  2  2  2  2  2  2  2  2
 ♒︎ ♒︎ ♒︎ ♒︎ ♒︎ ♒︎ ♒︎ ♒︎ ♒︎ ♒︎ ♒︎ ♒︎
 ♒︎ ♒︎ ♒︎ ♒︎ ♒︎ ♒︎ ♒︎ ♒︎ ♒︎  2  2  2
 ♒︎ ♒︎ ♒︎ ♒︎ ♒︎ ♒︎ ♒︎ ♒︎ ♒︎ ♒︎ ♒︎ ♒︎
  2  2  2  2  2  2  2 ♒︎ ♒︎ ♒︎ ♒︎ ♒︎
=============================================

Player 1: Enter row and column number: 1,7
Player 2 found a ship!
Player 2: Enter row and column number: 1,5
No ship found...
```

ℹ️ Ship numbers are shown for demonstration purposes.

---

## 🚀 Future Improvements

- **AI Opponent**: Add a single-player mode where you can play against the computer 🤖.
  **Gameplay features**: Adding multiple obstacles, home screen etc 🕹️ ...
- **Vertical and Diagonal Ships**: Implement vertical and diagonal ship placements 🚢.
- **GUI Version**: Use JavaFX or Swing to build a graphical interface 🖼️.
- **Save and Load**: Implement save/load functionality so players can pause and resume games 💾.

---

## 👩‍💻 Contributing

Feel free to fork this project, add features, or fix bugs! Contributions are welcome 🛠️.

---

## 📜 License

This project is licensed under the **MIT License**. You are free to use, modify, and distribute the game 🔓.
[![MIT License](https://img.shields.io/badge/License-MIT-green.svg)](https://choosealicense.com/licenses/mit/)

---

Have fun and enjoy playing Battleship! 🎮

---
