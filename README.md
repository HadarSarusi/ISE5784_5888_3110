Ray Tracing Engine – Java 17
Project Overview

This project implements a full Ray Tracing Engine in pure Java 17.
It was developed as part of the Intro to Software Engineering practical course, and its purpose is to explore advanced concepts in:

Computer graphics

3D geometry

Ray tracing algorithms

Clean OOP design

Software engineering principles

Design patterns

Unit testing & responsibility-driven architecture

The engine supports 3D primitives, lighting models, camera transformations, pixel rendering, and image output — all written without any external libraries.

Author

Hadar Sarusi

Project Structure
./src
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

Software Engineering Concepts Used
✔ TDD – Test Driven Development

All core components have dedicated JUnit test folders under:

test/unittests/


This ensures correctness of:

vector math

intersections

geometric primitives

lighting

rendering behavior

✔ RDD – Responsibility Driven Design

Each class has a clear responsibility.
Examples from your implementation:

Vector calculations → Vector.java

Ray logic & intersections → Ray.java

3D object behavior → geometry classes

Camera behavior & transformations → Camera.java

✔ Abstraction & Encapsulation

Each module (lighting, geometries, primitives, renderer) behaves as a black box, allowing clean architecture and easy debugging.

✔ Avoiding Hard-Coding

Features like improvements, picture tuning, and scene parameters are controlled via setters to keep the engine flexible.

✔ Law of Demeter

Classes interact only with their direct neighbors to keep the design decoupled.

✔ DRY – Don't Repeat Yourself

Shared operations (vectors, dot products, colors, materials) appear only once in the primitives package.

✔ KISS – Keep It Simple, Stupid

The implementation avoids unnecessary complexity and follows clean, readable structure.

3D Objects Supported

Your engine supports the following primitives:

Sphere

Cylinder

Tube

Plane

Polygon

Triangle

Geometries (Composite of multiple shapes)

Lighting Models Supported

Ambient Light

Directional Light

Point Light

Spot Light (with beam angle support)

Camera Features
View Plane Control

Width

Height

Distance

Orientation

Yaw

Pitch

Roll

Movement

Forward / Backward

Up / Down

Right / Left

Ray Construction

The Camera class generates rays per pixel and sends them to the renderer.

Renderer

Your renderer supports:

Simple Ray Tracer

Pixel-by-pixel color calculation

Image output using ImageWriter

The rendering pipeline:

Camera generates rays

Rays intersect with objects

Lighting model evaluates shading

Pixel color is written to the output image

Running the Project
Clone
git clone https://github.com/HadarSarusi/ISE5784_5888_3110.git

Run

Open in IntelliJ IDEA

Set SDK to Java 17

Build → Run Main.java (under /test)

Rendered image output will be produced by ImageWriter

Future Improvements

Anti-aliasing

Reflections / refractions

Adaptive super sampling

Multi-threaded rendering
