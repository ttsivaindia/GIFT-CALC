# GIFT CALC - Multi-Mode Calculator

A professional Java desktop calculator application with three different modes: Standard, Business, and Scientific.

## Features

### 📊 Three Calculator Modes

#### **Standard Mode**
- Basic arithmetic operations (+, -, *, /)
- Percentage calculations
- Decimal support
- Clear and delete functions

#### **Business Mode**
- All Standard features
- Memory operations (M+, M-, MR, MC)
- Advanced percentage handling

#### **Scientific Mode**
- All Business features
- Trigonometric functions (sin, cos, tan)
- Logarithmic functions (log base 10, natural log)
- Square root (sqrt)
- Factorial (!)
- Mathematical constants (π, e)
- Power operator (^)
- Parentheses for complex expressions

## Technical Features

✅ **Windows Calculator-Style UI** - Dark theme with professional appearance
✅ **Proper Expression Evaluation** - Supports operator precedence
✅ **Error Handling** - Graceful error handling for invalid operations
✅ **Hover Effects** - Interactive button feedback
✅ **Color-Coded Buttons** - Easy identification of function types
✅ **Large Display** - Clear, readable output with history tracking

## Getting Started

### Prerequisites
- Java 8 or higher
- Git (for cloning the repository)

### Installation

1. Clone the repository:
```bash
git clone https://github.com/yourusername/GIFT-CALC.git
cd GIFT-CALC
```

2. Compile the program:
```bash
cd src
javac Main.java
```

3. Run the calculator:
```bash
java Main
```

## How to Use

1. **Launch** - Run the application using the command above
2. **Select Mode** - Click on "Standard", "Business", or "Scientific" tabs at the top
3. **Enter Expression** - Click buttons or use keyboard to input numbers and operators
4. **View Results** - Expression displays at top, large result shows at bottom
5. **Calculate** - Press "=" to evaluate the entire expression
6. **Clear** - Press "C" to clear all, "DEL" to delete the last character

### Example Calculations

**Standard Mode:**
- `5 + 3 = 8`
- `10 * 2.5 = 25`
- `20 / 4 = 5`

**Business Mode:**
- `100 * 15% = 15`
- Store values in memory with M+

**Scientific Mode:**
- `sin(30) = 0.5`
- `2^3 = 8`
- `sqrt(16) = 4`
- `log(100) = 2`
- `(5 + 3) * 2 = 16`

## Project Structure

```
GIFT-CALC/
├── src/
│   └── Main.java      # Main application file containing all classes
├── README.md          # This file
├── .gitignore         # Git ignore configuration
└── Gifts.iml          # IntelliJ IDEA project file
```

## Architecture

The application consists of four main classes:

1. **Main** - Entry point for the application
2. **CalculatorApp** - JFrame container for the calculator
3. **CalculatorPanel** - Main UI component with display and buttons
4. **Calculator** - Core calculation logic
5. **ExpressionEvaluator** - Mathematical expression parser and evaluator

## Color Scheme

- 🟢 **Green** (#00B45A) - Equals button
- 🔴 **Red** (#DC3232) - Clear button
- 🟠 **Orange** (#C86400) - Delete button
- 🔵 **Blue** (#0078D7) - Operators and functions
- ⚫ **Gray** (#646464) - Memory and constants
- ⚫ **Dark Gray** (#3C3C3C) - Number buttons

## License

This project is open source and available under the MIT License.

## Author

Created as a Java Swing GUI practice project.

## Version

Version 1.0 - Multi-Mode Calculator

---

**Enjoy using GIFT CALC!** 🧮

