# Ray Tracing Engine – Java 17

## Project Overview
This project implements a complete **Ray Tracing Engine** written fully in **Java 17**.

It was developed as part of the *Intro to Software Engineering* practical course, focusing on:

- 3D computer graphics  
- Ray tracing algorithms  
- Clean Object-Oriented Design  
- Software engineering principles  
- Design patterns  
- Mathematical modeling  
- Test Driven Development (TDD)

The engine supports 3D geometric primitives, lighting, camera operations, rendering logic, and image output — all without external libraries.

---
# Authors
- **Lea Haim & Hadar Sarusi**


# Project Structure

```plaintext
src/
├── geometries/
│   ├── Cylinder.java
│   ├── Geometries.java
│   ├── Geometry.java
│   ├── Intersectable.java
│   ├── Plane.java
│   ├── Polygon.java
│   ├── RadialGeometry.java
│   ├── Sphere.java
│   ├── Triangle.java
│   └── Tube.java
│
├── lighting/
│   ├── AmbientLight.java
│   ├── DirectionalLight.java
│   ├── Light.java
│   ├── LightSource.java
│   ├── PointLight.java
│   └── SpotLight.java
│
├── primitives/
│   ├── Color.java
│   ├── Double3.java
│   ├── Material.java
│   ├── Point.java
│   ├── Ray.java
│   ├── TargetArea.java
│   ├── Util.java
│   └── Vector.java
│
├── renderer/
│   ├── Camera.java
│   ├── ImageWriter.java
│   ├── Pixel.java
│   ├── RayTracerBase.java
│   └── SimpleRayTracer.java
│
├── scene/
│   └── Scene.java
│
└── test/
    ├── Main.java
    └── unittests/
        ├── finalScene/
        ├── geometries/
        ├── lighting/
        ├── primitives/
        └── renderer/
```

---

# Software Engineering Concepts

## Test Driven Development (TDD)

Unit tests validate:

- Vector and point math  
- Geometric intersections  
- Lighting models  
- Rendering logic  
- Ray accuracy  

**Tests are located under:**

```plaintext
src/test/unittests/
```

---

## Responsibility Driven Design (RDD)

Each component has a single responsibility:

- `Vector.java` → vector operations  
- `Ray.java` → ray construction and intersection logic  
- Geometry classes → shape-specific behavior  
- `Camera.java` → camera transformations and ray creation  
- `SimpleRayTracer.java` → rendering pipeline  

---

## Avoiding Hard-Coding

All scene settings, improvements, and rendering options are configurable using **setters**, enabling maximum flexibility.

---

## Abstraction & Encapsulation

Each module behaves as an isolated subsystem:

```plaintext
lighting/
geometries/
primitives/
renderer/
```

This modularity allows clean architecture and simplifies extension.

---

## Law of Demeter

Classes interact only with their immediate collaborators, ensuring low coupling and better maintainability.

---

## DRY – Don't Repeat Yourself

Shared logic (vectors, colors, materials, utilities) exists once inside:

```plaintext
src/primitives/
```

and is reused throughout the system.

---

## KISS – Keep It Simple

The project avoids unnecessary complexity and remains clean, readable, and maintainable.

---

# 3D Objects Supported

- Sphere  
- Cylinder  
- Tube  
- Plane  
- Triangle  
- Polygon  
- Composite objects via `Geometries.java`

---

# Lighting Models Supported

- Ambient Light  
- Directional Light  
- Point Light  
- Spot Light (with adjustable beam angle)

---

# Camera Features

## View Plane Configuration
- Width  
- Height  
- Distance  

## Orientation Controls
- Yaw  
- Pitch  
- Roll  

## Movement Controls
- Forward / Backward  
- Up / Down  
- Right / Left  

---

# Renderer

The rendering pipeline includes:

1. Camera generates rays per pixel  
2. Ray–object intersections are calculated  
3. Lighting model evaluates shading  
4. Color is computed  
5. Pixel is written to the image using `ImageWriter`  

## Renderer Implementation
- `RayTracerBase.java` – abstract tracer  
- `SimpleRayTracer.java` – concrete implementation  

---

# Running the Project

## Clone the repository

```bash
git clone https://github.com/HadarSarusi/ISE5784_5888_3110.git
```

## Run the project

1. Open the project in **IntelliJ IDEA**  
2. Set **Java 17** as the Project SDK  
3. Build the project  
4. Run:

```plaintext
src/test/Main.java
```
