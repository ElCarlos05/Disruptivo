package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Quad;

public class Main extends SimpleApplication {

    // Tamaño de cada tile del mapa.
    // Como es 32, cada cuadrito mide 32x32.
    private final float TILE = 32f;

    // Tamaño del jugador.
    private final float PLAYER_SIZE = 32f;

    // Velocidad de movimiento del jugador.
    private final float PLAYER_SPEED = 350f;

    // Geometry representa al jugador en pantalla.
    private Geometry player;

    // Node donde se guardan todos los tiles del mapa.
    // Sirve para borrar el escenario actual cuando cambiamos de habitación.
    private Node mapNode = new Node("Map");

    // Variables para saber qué tecla está presionada.
    private boolean left, right, up, down;

    // Escena actual donde está el jugador.
    private String currentScene = "RUPERT_ROOM";

    /*
     * Significado de los números del mapa:
     * -1 = vacío / no dibujar nada
     *  0 = piso / se puede caminar
     *  1 = pared / bloquea al jugador
     *  2 = puerta / se puede caminar e interactuar
     */

    // Mapa de la habitación inicial de Rupert.
    private int[][] rupertRoom = {
            {1,1,1,1,1,1,1,1,1,1},
            {1,0,0,0,0,0,0,0,2,1},
            {1,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,1},
            {1,1,1,1,1,1,1,1,1,1}
    };

    // Mapa del pasillo principal.
    // Tiene varias puertas para entrar a otras habitaciones.
    private int[][] pasillo = {
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,0,0,0,2,0,0,0,2,0,0,0,2,0,0,0,2,0,0,0,2,0,0,0,2,0,0,0,2,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
    };

    private int[][] habitacion2 = {
            {1,1,1,1,1,1,1,1},
            {1,2,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,1},
            {1,1,1,1,1,1,1,1}
    };

    private int[][] habitacion3 = {
            {1,1,1,1,1,1,1,1},
            {1,2,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,1},
            {1,1,1,1,1,1,1,1}
    };

    private int[][] habitacion4 = {
            {1,1,1,1,1,1,1,1},
            {1,2,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,1},
            {1,1,1,1,1,1,1,1}
    };

    private int[][] enfermeria = {
            {1,1,1,1,1,1,1,1,1,1},
            {1,2,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,1},
            {1,1,1,1,1,1,1,1,1,1}
    };

    private int[][] oficina1 = {
            {1,1,1,1,1,1,1,1},
            {1,2,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,1},
            {1,1,1,1,1,1,1,1}
    };

    private int[][] oficina2 = {
            {1,1,1,1,1,1,1,1},
            {1,2,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,1},
            {1,1,1,1,1,1,1,1}
    };

    private int[][] recepcion = {
            {1,1,1,1,1,1,1,1,1,1},
            {1,2,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,1},
            {1,1,1,1,1,1,1,1,1,1}
    };

    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    @Override
    public void simpleInitApp() {

        // Desactiva la cámara libre de jMonkey.
        flyCam.setEnabled(false);
        viewPort.setBackgroundColor(ColorRGBA.Black);

        // Agrega el nodo del mapa al mundo principal.
        rootNode.attachChild(mapNode);

        // Configura los controles del teclado.
        initKeys();

        // Carga la primera escena.
        loadScene("RUPERT_ROOM");
    }

    // Carga una escena nueva.
    // Este metodo sirve para cargar una escena cuando se cambia a una habitacion difernete. 
    private void loadScene(String sceneName) {

        // Guarda el nombre de la escena actual.
        currentScene = sceneName;

        // Borra todos los tiles del mapa anterior.
        mapNode.detachAllChildren();

        // Busca el mapa correspondiente.
        int[][] selectedMap = getMap(sceneName);

        // Dibuja el nuevo mapa.
        createTileMap(selectedMap);

        // Crea al jugador dentro de la nueva escena.
        createPlayerForScene(sceneName);

        // Ajusta la cámara.
        updateCamera();
    }

    // Regresa el mapa que corresponde al nombre de escena.
    private int[][] getMap(String sceneName) {
        switch (sceneName) {
            case "PASILLO":
                return pasillo;
            case "HABITACION_2":
                return habitacion2;
            case "HABITACION_3":
                return habitacion3;
            case "HABITACION_4":
                return habitacion4;
            case "ENFERMERIA":
                return enfermeria;
            case "OFICINA_1":
                return oficina1;
            case "OFICINA_2":
                return oficina2;
            case "RECEPCION":
                return recepcion;
            default:
                return rupertRoom;
        }
    }

    // Recorre el arreglo del mapa y dibuja cada tile.
    private void createTileMap(int[][] map) {

        for (int y = 0; y < map.length; y++) {

            for (int x = 0; x < map[y].length; x++) {

                int tile = map[y][x];

                // Si es -1, no dibuja nada.
                if (tile == -1) {
                    continue;
                }

                // Piso.
                if (tile == 0) {
                    createTile(x, y, ColorRGBA.Gray, 0);
                }

                // Pared.
                if (tile == 1) {
                    createTile(x, y, ColorRGBA.DarkGray, 0);
                }

                // Puerta.
                if (tile == 2) {
                    createTile(x, y, ColorRGBA.Brown, 0);
                }
            }
        }
    }

    // Crea un tile individual.
    private void createTile(int x, int y, ColorRGBA color, float z) {

        // Quad es un rectángulo plano.
        Quad quad = new Quad(TILE, TILE);
        Geometry tile = new Geometry("Tile", quad);
        Material mat = new Material(
                assetManager,
                "Common/MatDefs/Misc/Unshaded.j3md"
        );
        
        mat.setColor("Color", color);
        tile.setMaterial(mat);

        // Posición del tile.
        // x * TILE lo mueve horizontalmente.
        // -y * TILE lo baja, porque el mapa usa filas hacia abajo.
        tile.setLocalTranslation(x * TILE, -y * TILE, z);

        // Agrega el tile al nodo del mapa.
        mapNode.attachChild(tile);
    }

    // Crea al jugador cada vez que cambia de escena.
    private void createPlayerForScene(String sceneName) {

        // Si ya existe un jugador anterior, lo borra.
        if (player != null) {
            rootNode.detachChild(player);
        }

        // Crea un cuadrado para Rupert.
        Quad quad = new Quad(PLAYER_SIZE, PLAYER_SIZE);
        player = new Geometry("Rupert", quad);

        // Material blanco temporal.
        Material mat = new Material(
                assetManager,
                "Common/MatDefs/Misc/Unshaded.j3md"
        );

        mat.setColor("Color", ColorRGBA.White);
        player.setMaterial(mat);

        // Posición inicial del jugador.
        float spawnX = 2 * TILE;
        float spawnY = -2 * TILE;

        // De momento el pasillo usa el mismo spawn.
        if (sceneName.equals("PASILLO")) {
            spawnX = 2 * TILE;
            spawnY = -2 * TILE;
        }

        // Se resta la mitad del tamaño para centrar al jugador en el tile.
        player.setLocalTranslation(
                spawnX - PLAYER_SIZE / 2,
                spawnY - PLAYER_SIZE / 2,
                10
        );

        // Agrega al jugador al mundo.
        rootNode.attachChild(player);
    }

    // Obtiene el centro real del jugador.
    private Vector3f getPlayerCenter() {

        Vector3f pos = player.getLocalTranslation();

        return new Vector3f(
                pos.x + PLAYER_SIZE / 2,
                pos.y + PLAYER_SIZE / 2,
                pos.z
        );
    }

    // Configura y actualiza la cámara.
    private void updateCamera() {

        // Hace que la cámara sea 2D.
        cam.setParallelProjection(true);

        // Calcula proporción de pantalla.
        float aspect = (float) cam.getWidth() / cam.getHeight();

        // Define el tamaño visible de la cámara.
        cam.setFrustum(
                -1000,
                1000,
                -180 * aspect,
                180 * aspect,
                180,
                -180
        );

        Vector3f center = getPlayerCenter();

        // Si está en el pasillo, usa cámara por secciones.
        if (currentScene.equals("PASILLO")) {
            updateHallwayCamera();
        } else {

            // En habitaciones, la cámara queda centrada en Rupert.
            cam.setLocation(new Vector3f(center.x, center.y, 100));
            cam.lookAt(new Vector3f(center.x, center.y, 0), Vector3f.UNIT_Y);
        }
    }

    // Cámara especial para el pasillo.
    // No sigue libremente al jugador, sino que cambia por secciones.
    private void updateHallwayCamera() {

        Vector3f center = getPlayerCenter();
        float camX;

        if (center.x < 8 * TILE) {
            camX = 5 * TILE;
        } else if (center.x < 16 * TILE) {
            camX = 13 * TILE;
        } else if (center.x < 24 * TILE) {
            camX = 21 * TILE;
        } else {
            camX = 27 * TILE;
        }

        float camY = -2 * TILE;

        cam.setLocation(new Vector3f(camX, camY, 100));
        cam.lookAt(new Vector3f(camX, camY, 0), Vector3f.UNIT_Y);
    }

    // Revisa si el jugador está parado sobre una puerta.
    private void checkDoor() {

        Vector3f center = getPlayerCenter();

        // Convierte la posición del jugador en coordenadas del mapa.
        int tileX = Math.round(center.x / TILE);
        int tileY = Math.round(-center.y / TILE);

        int[][] map = getMap(currentScene);

        // Evita errores si el jugador está fuera del mapa.
        if (tileY < 0 || tileY >= map.length || tileX < 0 || tileX >= map[tileY].length) {
            return;
        }

        // Si no está sobre una puerta, no pasa nada.
        if (map[tileY][tileX] != 2) {
            return;
        }

        // Si está en el cuarto inicial, la puerta lleva al pasillo.
        if (currentScene.equals("RUPERT_ROOM")) {
            loadScene("PASILLO");

        } else if (currentScene.equals("PASILLO")) {

            // Dependiendo de la posición de la puerta del pasillo,
            // carga una habitación diferente.
            if (tileX == 4) {
                loadScene("HABITACION_2");
            } else if (tileX == 8) {
                loadScene("HABITACION_3");
            } else if (tileX == 12) {
                loadScene("HABITACION_4");
            } else if (tileX == 16) {
                loadScene("ENFERMERIA");
            } else if (tileX == 20) {
                loadScene("OFICINA_1");
            } else if (tileX == 24) {
                loadScene("OFICINA_2");
            } else if (tileX == 28) {
                loadScene("RECEPCION");
            }

        } else {

            // Si está en cualquier otra habitación,
            // la puerta lo regresa al pasillo.
            loadScene("PASILLO");
        }
    }

    // Configura las teclas.
    private void initKeys() {

        inputManager.addMapping(
                "Left",
                new KeyTrigger(KeyInput.KEY_A),
                new KeyTrigger(KeyInput.KEY_LEFT)
        );

        inputManager.addMapping(
                "Right",
                new KeyTrigger(KeyInput.KEY_D),
                new KeyTrigger(KeyInput.KEY_RIGHT)
        );

        inputManager.addMapping(
                "Up",
                new KeyTrigger(KeyInput.KEY_W),
                new KeyTrigger(KeyInput.KEY_UP)
        );

        inputManager.addMapping(
                "Down",
                new KeyTrigger(KeyInput.KEY_S),
                new KeyTrigger(KeyInput.KEY_DOWN)
        );

        inputManager.addMapping(
                "Interact",
                new KeyTrigger(KeyInput.KEY_E)
        );

        inputManager.addListener(
                actionListener,
                "Left", "Right", "Up", "Down", "Interact"
        );
    }

    // Detecta si una tecla fue presionada o soltada.
    private final ActionListener actionListener =
            (name, isPressed, tpf) -> {

                if (name.equals("Left")) {
                    left = isPressed;
                }

                if (name.equals("Right")) {
                    right = isPressed;
                }

                if (name.equals("Up")) {
                    up = isPressed;
                }

                if (name.equals("Down")) {
                    down = isPressed;
                }

                // E sirve para interactuar con puertas.
                if (name.equals("Interact") && isPressed) {
                    checkDoor();
                }
            };

    // Revisa si el jugador puede moverse a una posición.
    private boolean canMoveTo(Vector3f nextPos) {

        int[][] map = getMap(currentScene);

        // Coordenadas de las 4 esquinas del jugador.
        float leftX = nextPos.x;
        float rightX = nextPos.x + PLAYER_SIZE - 1;
        float topY = nextPos.y;
        float bottomY = nextPos.y - PLAYER_SIZE + 1;

        // Convierte las esquinas a coordenadas de tile.
        int leftTile = (int) Math.floor(leftX / TILE);
        int rightTile = (int) Math.floor(rightX / TILE);

        int topTile = (int) Math.floor(-topY / TILE);
        int bottomTile = (int) Math.floor(-bottomY / TILE);

        // Solo permite moverse si las 4 esquinas están sobre tiles caminables.
        return isWalkable(map, leftTile, topTile)
                && isWalkable(map, rightTile, topTile)
                && isWalkable(map, leftTile, bottomTile)
                && isWalkable(map, rightTile, bottomTile);
    }

    // Dice si un tile se puede caminar.
    private boolean isWalkable(int[][] map, int x, int y) {

        // Si está fuera del mapa, no se puede caminar.
        if (y < 0 || y >= map.length || x < 0 || x >= map[y].length) {
            return false;
        }

        int tile = map[y][x];

        // Solo piso y puerta son caminables.
        return tile == 0 || tile == 2;
    }

    // Se ejecuta muchas veces por segundo.
    // Aquí va la lógica del movimiento.
    @Override
    public void simpleUpdate(float tpf) {

        Vector3f currentPos = player.getLocalTranslation();

        // Movimiento horizontal.
        Vector3f nextX = currentPos.clone();

        if (left) {
            nextX.x -= PLAYER_SPEED * tpf;
        }

        if (right) {
            nextX.x += PLAYER_SPEED * tpf;
        }

        // Si no choca, aplica movimiento horizontal.
        if (canMoveTo(nextX)) {
            player.setLocalTranslation(nextX);
        }

        // Movimiento vertical.
        Vector3f nextY = player.getLocalTranslation().clone();

        if (up) {
            nextY.y += PLAYER_SPEED * tpf;
        }

        if (down) {
            nextY.y -= PLAYER_SPEED * tpf;
        }

        // Si no choca, aplica movimiento vertical.
        if (canMoveTo(nextY)) {
            player.setLocalTranslation(nextY);
        }

        // Si está en el pasillo, actualiza la cámara por secciones.
        if (currentScene.equals("PASILLO")) {
            updateHallwayCamera();
        }
    }
}