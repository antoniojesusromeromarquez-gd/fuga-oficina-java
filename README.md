# 🏢 Fuga de la Oficina (v4.5) - RPG de Texto en Java

Un mini-videojuego RPG de texto e interactivo desarrollado de forma nativa en **Java** utilizando la consola corporativa. El juego aborda de forma humorística la misión de un desarrollador de software que intenta escapar de la oficina un viernes por la tarde a las 17:50, gestionando su nivel de estrés y esquivando reuniones de última hora o incidencias en producción.

Este proyecto ha sido desarrollado de forma autónoma como práctica de consolidación de los conceptos clave aprendidos durante el primer año del Ciclo Formativo de Grado Superior en **Desarrollo de Aplicaciones Multiplataforma (DAM)**.

---

## 🛠️ Conceptos de DAM Aplicados

Para la arquitectura del juego se han evitado estructuras planas y acopladas, buscando aplicar buenas prácticas de ingeniería de software y programación estructurada/orientada a objetos:

* **Programación Orientada a Objetos (POO):** Modelado de entidades mediante clases estáticas internas (`static class Bug`). Las incidencias ya no son simples cadenas de texto, sino objetos con atributos propios (descripción y nivel de impacto de daño por estrés).
* **Estructuras de Datos Lineales (Colas - FIFO):** Implementación de una cola (`Queue` mediante `LinkedList`) para gestionar la bandeja de entrada de tareas pendientes. Sigue estrictamente la filosofía *First In, First Out* (el primer bug en entrar es el primero que debe resolverse).
* **Arquitectura Modular y Estados:** Separación por completo de la lógica de las pantallas/habitaciones mediante métodos independientes que retornan punteros de navegación (Strings) coordinados por una estructura de control `switch` central en el bucle principal (`Game Loop`).
* **Colecciones Dinámicas:** Uso de `ArrayList` para gestionar un sistema de inventario interactivo capaz de añadir, comprobar existencias (`.contains()`) y consumir/eliminar ítems (`.remove()`).
* **Algoritmia y Azar:** Implementación de eventos aleatorios y encuentros probabilísticos mediante la API `Math.random()` para simular la asignación imprevista de bugs y encuentros con compañeros pesados.

---

## 🕹️ Mecánicas del Juego & "Agencia del Jugador"

* **El Motor de Estrés:** Si se acumulan 3 o más objetos `Bug` en la cola, al inicio de cada turno el sistema calculará el daño total acumulado de las incidencias y restará salud mental (vida) al jugador.
* **Libertad de Movimiento:** El jugador tiene el control táctico de su ruta. Si detecta que la cola de bugs está colapsando en el pasillo o la cafetería, puede elegir de forma proactiva `volver a la mesa` para depurar código y vaciar la cola antes de que el bucle principal le penalice.
* **Condiciones de Victoria/Derrota:** Se gana alcanzando la puerta de salida (con la contraseña correcta obtenida al registrar la mesa). Se pierde si la salud mental llega a 0 debido al colapso de deuda técnica o las intercepciones de los jefes.

---

## 🚀 Cómo Ejecutar el Proyecto

Al ser un proyecto nativo en Java estándar que utiliza únicamente librerías de la SDK (`java.util`), no requiere de ningún entorno pesado o IDE local. 

1. Clona este repositorio o descarga el archivo `Main.java`.
2. Compila el archivo desde tu consola de comandos:
   ```bash
   javac Main.java
