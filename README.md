# Disruptivo

## Descripción

**Disruptivo** es un videojuego 2D de aventura, exploración y resolución de acertijos desarrollado en Java utilizando JMonkeyEngine.

El jugador controla a **Rupert**, un joven internado en un hospital psiquiátrico donde extraños tratamientos alteran su percepción de la realidad. A través de la exploración de habitaciones, la interacción con personajes y la resolución de puzzles, deberá descubrir los secretos del hospital y encontrar una forma de escapar.

El juego utiliza una perspectiva **top-down**, gráficos **pixel-art 32×32 px** y una ambientación enfocada en el misterio y el terror psicológico.

---

# Características Principales

## Exploración

- Múltiples habitaciones interconectadas.
- Sistema de puertas
- Exploración libre del hospital.

## Interacción

- Interacción con objetos mediante la tecla `E`.
- Lectura de notas y documentos.
- Conversación con pacientes.
- Sistema de indicadores visuales para objetos interactivos.

## Progresión

- Sistema de llaves para desbloquear nuevas áreas.
- Obtención de objetos clave para avanzar.
- Exploración guiada por objetivos y pistas.

## Puzzles

- Puzzle de medicamentos en enfermería.
- Puzzle de observación en sala de terapia.
- Búsqueda de llaves ocultas.
- Resolución de acertijos para acceder a nuevas zonas.

## Enemigos

- Enfermeros con rutas de patrullaje.
- Sistema de detección mediante campo de visión.
- Mecánica de persecución.
- Sistema de escondites debajo de camas.

## Ambientación

- Música ambiental.
- Efectos de sonido para interacción y eventos.
- Estética pixel-art

---

# Tecnologías

## Motor de Juego

- Java
- JMonkeyEngine 3

## Herramientas

- Tiled Map Editor
- LibreSprite
- Git
- GitHub

---

# Características Técnicas

## Sistema de Mapas

Integración de mapas creados en Tiled:

- Archivos `.tmx`
- Tilesets `.tsx`
- Capas de suelo
- Capas de objetos
- Capas de colisión
- Triggers de interacción
- Zonas de transición
- Puntos de aparición

## Renderizado 2D sobre JME

- Cámara ortográfica.
- Uso del plano XY para simulación 2D.
- Jerarquía de nodos para organización de escena.
- Escalado y posicionamiento mediante transformaciones de JME.

## Texturas y Materiales

- Texturas PNG.
- Sprite Sheets para animaciones.

## Movimiento y Colisiones

- Movimiento basado en InputManager.
- Colisiones de mapa.
- Colisiones de mobiliario.
- Hitbox personalizada para el jugador.
- Transiciones entre habitaciones.

## Inteligencia Artificial

Las enfermeras implementan:

- Patrullaje.
- Detección del jugador.
- Campo de visión.
- Persecución.
- Búsqueda en habitaciones.

---

# Controles

| Tecla | Acción |
|---------|---------|
| W | Mover arriba |
| S | Mover abajo |
| A | Mover izquierda |
| D | Mover derecha |
| E | Interactuar |
| Enter | Confirmar selección |
| Click Izquierdo | Seleccionar elementos en puzzles |

---

# Estructura del Proyecto

```text
/assets
│
├── sprites
├── tilesets
├── audio
├── ui
└── maps

/src
│
├── HospitalGame.java
├── GameScenes.java
├── GameCollisions.java
├── NurseEnemy.java
└── utilidades y componentes auxiliares
```

---

# Estado del Proyecto

Versión prototipo funcional completada.

Características implementadas:

- Sistema de habitaciones.
- Movimiento del jugador.
- Colisiones.
- Interacciones.
- Sistema de llaves.
- Puzzles.
- Enemigos con patrullaje y persecución.
- Sistema de escondites.
- Transiciones entre habitaciones.
- Sonido y música ambiental.

---

# Objetivo del Juego

Resolver los acertijos del hospital, obtener las llaves necesarias para desbloquear nuevas áreas y escapar del establecimiento evitando ser capturado por los enfermeros.

---

# Créditos

### Desarrollo

- Cristian Eduardo Boyain y Goytia Luna (profesor)
- Carlos Alberto Núñez López
- Héctor Jesús Martínez Basurto
- Evelyn Daniela Castillo Rodríguez

### Universidad Autónoma de Zacatecas

Unidad Académica de Ingeniería Eléctrica  
Ingeniería de Software  
Fundamentos de Videojuegos

---

# Capturas



---

# Licencia

Proyecto desarrollado con fines académicos.
