# 🚀 Ray Tracing Engine – Java 17 🚀

> 🎨 A complete 3D Ray Tracing Engine built from scratch using **Java 17**  
> 💡 Developed as part of an Intro to Software Engineering course  
> 🧠 Built with strong OOP principles, clean architecture & rendering algorithms  

---

## 🧾 Project Overview 🧾

This project implements a complete **Ray Tracing Engine** written fully in **Java 17**.

It was developed as part of the *Intro to Software Engineering* practical course, focusing on:

- 🖥️ 3D computer graphics  
- 🔦 Ray tracing algorithms  
- 🧱 Clean Object-Oriented Design  
- 📐 Software engineering principles  
- 🧩 Design patterns  
- ➗ Mathematical modeling  
- 🧪 Test Driven Development (TDD)  

The engine supports 3D geometric primitives, advanced lighting models, camera operations, rendering logic, and image output — all implemented **without external libraries**.

---

## 🛠️ Technologies & Tools 🛠️

### 💻 Core Technologies
- ☕ Java 17  
- 🧪 JUnit (Unit Testing)  
- 📦 IntelliJ IDEA  

### 🧠 Engineering Principles Applied
- 🧪 Test Driven Development (TDD)  
- 🎯 Responsibility Driven Design (RDD)  
- 📏 Law of Demeter  
- 🔁 DRY – Don't Repeat Yourself  
- ✨ KISS – Keep It Simple  
- 🧱 Avoiding Hard-Coding  
- 🏗️ Clean Modular Architecture  

---

## 📂 Project Structure 📂

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

✨ The modular structure enables clear separation of responsibilities and easy extensibility.

---

## 🧪 Software Engineering Concepts 🧪

### 🔍 Test Driven Development (TDD)

Unit tests validate:

- ➗ Vector and point mathematics  
- 📐 Geometric intersections  
- 💡 Lighting calculations  
- 🎨 Rendering logic  
- 🎯 Ray accuracy  

📁 Tests are located under:

```plaintext
src/test/unittests/
```

---

### 🎯 Responsibility Driven Design (RDD)

Each component maintains a **single responsibility**:

- `Vector.java` → vector operations  
- `Ray.java` → ray construction & intersection logic  
- Geometry classes → shape-specific behavior  
- `Camera.java` → camera transformations & ray generation  
- `SimpleRayTracer.java` → rendering pipeline orchestration  

---

### 🧱 Abstraction & Encapsulation

Each module behaves as an isolated subsystem:

```plaintext
lighting/
geometries/
primitives/
renderer/
```

This separation enforces encapsulation, low coupling, and scalable architecture.

---

### 🧱 Avoiding Hard-Coding

All rendering parameters and scene configurations are adjustable via **setters**, ensuring flexibility and maintainability.

---

### 🧠 Law of Demeter

Classes interact only with their immediate collaborators, reducing coupling and increasing maintainability.

---

### 🔁 DRY – Don't Repeat Yourself

Shared logic (vectors, colors, materials, utilities) is centralized under:

```plaintext
src/primitives/
```

and reused throughout the engine.

---

### ✨ KISS – Keep It Simple

The implementation avoids unnecessary complexity, keeping the architecture readable, scalable, and clean.

---

## 🌍 3D Objects Supported 🌍

- 🟠 Sphere  
- 🧱 Cylinder  
- 🧪 Tube  
- 📐 Plane  
- 🔺 Triangle  
- 🔷 Polygon  
- 🧩 Composite geometries via `Geometries.java`

---

## 💡 Lighting Models Supported 💡

- 🌤️ Ambient Light  
- ☀️ Directional Light  
- 💡 Point Light  
- 🔦 Spot Light (with adjustable beam angle)

---

## 📷 Camera Features 📷

### 🎯 View Plane Configuration
- Width  
- Height  
- Distance  

### 🧭 Orientation Controls
- Yaw  
- Pitch  
- Roll  

### 🚶 Movement Controls
- Forward / Backward  
- Up / Down  
- Right / Left  

---

## 🎨 Rendering Pipeline 🎨

1️⃣ Camera generates rays per pixel  
2️⃣ Ray–object intersections are computed  
3️⃣ Lighting model evaluates shading  
4️⃣ Final color is calculated  
5️⃣ Pixel is written to the image using `ImageWriter`

### 🏗️ Renderer Implementation 🏗️
- `RayTracerBase.java` – abstract tracer  
- `SimpleRayTracer.java` – concrete implementation  

---

## ▶️ Running the Project ▶️

### 📥 Clone the repository

```bash
git clone https://github.com/HadarSarusi/java-ray-tracing-engine.git
```

### 💥 Run the project

1. Open the project in IntelliJ IDEA  
2. Set Java 17 as the Project SDK  
3. Build the project  
4. Run:

```plaintext
src/test/Main.java
```

---

## 👩‍💻 Authors & Credits 👩‍💻

<div align="center">

<table>
<tr>

<td align="center">

<img src="https://github.com/HadarSarusi.png" width="150" style="border-radius: 12px;" />

###  Hadar Sarusi 
[![GitHub](https://img.shields.io/badge/GITHUB-HADARSARUSI-1F6FEB?style=for-the-badge&logo=github&logoColor=white)](https://github.com/HadarSarusi)

</td>

<td align="center">

<img src="https://github.com/LeaHaim.png" width="150" style="border-radius: 12px;" />

### Lea Haim 
[![GitHub](https://img.shields.io/badge/GITHUB-LEAHAIM-1F6FEB?style=for-the-badge&logo=github&logoColor=white)](https://github.com/LeaHaim)

</td>

</tr>
</table>

</div>

---

> ✨ If you like this project – please leave a star! ✨
