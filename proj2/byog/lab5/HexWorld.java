package byog.lab5;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 100;
    private static final int HEIGHT = 100;
    private static final Random RANDOM = new Random(3);


    public static int hexRowWidth(int s, int i) {
        int effectiveI = i;
        if (i >= s) {
            effectiveI = 2 * s - 1 - effectiveI;
        }
        return s + 2 * effectiveI;
    }

    public static int hexRowOffset(int s, int i) {
        int effectiveI = i;
        if (i >= s) {
            effectiveI = 2 * s - 1 - effectiveI;
        }
        return -effectiveI;
    }

    public static void addRow(TETile[][] world, Position p, int width, TETile t) {
        for (int xi = 0; xi < width; xi += 1) {
            int xCoord = p.x + xi;
            int yCoord = p.y;
            world[xCoord][yCoord] = TETile.colorVariant(t, 32, 32, 32, RANDOM);
        }
    }

    // p indicates left corner
    public static void addHexagon(TETile[][] world, Position p, int s, TETile t) {

        if (s < 2) {
            throw new IllegalArgumentException("Hexagon must be at least size 2.");
        }

        // hexagons have 2*s rows. this code iterates up from the bottom row,
        // which we call row 0.
        for (int yi = 0; yi < 2 * s; yi += 1) {
            int thisRowY = p.y + yi;

            int xRowStart = p.x + hexRowOffset(s, yi);
            Position rowStartP = new Position(xRowStart, thisRowY);

            int rowWidth = hexRowWidth(s, yi);

            addRow(world, rowStartP, rowWidth, t);

        }
    }


    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(7);
        switch (tileNum) {
            case 0:
                return Tileset.WALL;
            case 1:
                return Tileset.FLOWER;
            case 2:
                return Tileset.GRASS;
            case 3:
                return Tileset.LOCKED_DOOR;
            case 4:
                return Tileset.UNLOCKED_DOOR;
            case 5:
                return Tileset.SAND;
            case 6:
                return Tileset.WATER;
            default:
                return Tileset.PLAYER;
        }
    }

    private static Position nextIter(Position p, int s, int i) {
        if (i < s-1) {
            return new Position(p.x, p.y + 2 * s);
        } else {
            return new Position(p.x + 2 * s - 1, p.y + s);
        }
    }

    public static void tesselationHexs(TETile[][] world, int size, Position p) {
        int d = size ;
        for (int times = 0; times < 2 * size -1 ; times++) {
            addHexagons(world, size, p, d);
            p = nextIter(p, size, times);
            d = drawTimes(d,times,size);
        }
    }
    public static int drawTimes(int before,int i,int size) {
       if(i < size -1 ) {
           return before+1;
       }else {
           return before-1;
       }
    }
    private static void addHexagons(TETile[][] world, int size, Position p, int t) {
        for (int i = 0; i < t; i++) {
            addHexagon(world, p, size, randomTile());
            p = nextNeighbour(p, size);
        }
    }

    private static Position nextNeighbour(Position p, int s) {
        return new Position(p.x + 2 * s - 1, p.y - s);
    }


    public static void main(String[] args) {
        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        tesselationHexs(world,4, new Position(30, 55));

        // draws the world to the screen
        ter.renderFrame(world);

    }


   /* public static void main(String[] args) {
        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        addHexagon(world,new Position(30,10),4,Tileset.WALL);

        // draws the world to the screen
        ter.renderFrame(world);
    }*/
}
