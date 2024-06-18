# Balloon Shooting Game & Curve Drawing 

## Overview

The project consists of several Java packages, each implementing distinct functionalities related to graphics manipulation and interactive drawing. 
### balloon_shooting_game Package

The `balloon_shooting_game` package implements essential computer graphics transformation methods for a dynamic shooting game. These include:

- **Translation**: Positions bullets and balloons on the screen.
- **Rotation**: Adjusts the gun's orientation for precise aiming.
- **Scaling**: Dynamically resizes the gun to adapt to game dynamics.
- **Collision Detection**: Detects interactions between bullets and balloons.

### curve Package

The `curve` package is dedicated to implementing Bezier curves through user-defined control points, utilizing multi-threading for efficient calculation. Key features include:

- **Bezier Curve Generation**: Allows users to specify control points via mouse clicks to generate smooth curves.
- **Multi-Threading**: Enhances performance by distributing curve calculations across multiple threads.
- **Interactive User Interface**: Provides a responsive interface for interactive curve manipulation and visualization.

### curves_drawing Package

The `curves_drawing` package specializes in rendering various types of curves and shapes based on user-defined parameter values. It supports:


- DDA_Line
- Bresenham_Line
- Bresenham_Circle
- Mid_Point_Circle
- Quadratic_Bézier_curve
- Cubic_Bézier_curve
- Parametric_Line
- Parametric_Circle
- Parametric_Arc
- Parametric_Ellipse
- Parametric_Spiral

### drawShapes Package

The `drawShapes` package facilitates interactive drawing of shapes such as lines, circles, and free-hand sketches using mouse input. Key functionalities include:

- **Dynamic Drawing**: Allows users to draw shapes directly on the screen in real-time.
- **User Interaction**: Supports mouse-based input for creating and modifying shapes interactively.

#

