package byog.Core;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import java.util.ArrayList;
import java.util.Random;

public class RoomMapGenerator implements MapGenerator {
    private int width;
    private int height;
    private final int MAX_ROOMS = 30;
    private final int MAX_SIZE = 10;
    private final int MIN_SIZE = 6;

    private final long SEED = 28880;

    private Random rng = new Random();

    // only for debug
    private TERenderer engine = null;

    public RoomMapGenerator() {
    }

    public RoomMapGenerator(TERenderer engine) {
        this.engine = engine;
    }

    private void init(TETile[][] tiles) {
        width = tiles.length;
        height = tiles[0].length;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                tiles[i][j] = Tileset.WALL;
            }
        }
    }

    @Override
    public void mapGen(TETile[][] tiles) {
        init(tiles);
        ArrayList<Room> rooms = new ArrayList<>();
        for (int i = 0; i < MAX_ROOMS; i++) {
            int w = RandomUtils.uniform(rng, MIN_SIZE, MAX_SIZE);
            int h = RandomUtils.uniform(rng, MIN_SIZE, MAX_SIZE);
            int x = RandomUtils.uniform(rng, 1, width - w) - 1;
            int y = RandomUtils.uniform(rng, 1, height - h) - 1;
            Room newRoom = new Room(
                    new Position(x, y),
                    new Position(x + w, y + h)
            );
            boolean ok = true;
            for (Room otherRoom : rooms) {
                if (newRoom.isOverlap(otherRoom)) {
                    ok = false;
                }
            }

            if (ok) {
                applyRoom(newRoom, tiles);
                if (!rooms.isEmpty()) {
                    Position newP = newRoom.center();
                    Position prevP = rooms.get(rooms.size() - 1).center();
                    if (RandomUtils.uniform(rng, 0, 2) == 1) {
                        applyHorizontalTunnel(tiles, prevP.getX(), newP.getX(), prevP.getY());
                        applyVerticalTunnel(tiles, prevP.getY(), newP.getY(), newP.getX());
                    } else {
                        applyVerticalTunnel(tiles, prevP.getY(), newP.getY(), prevP.getX());
                        applyHorizontalTunnel(tiles, prevP.getX(), newP.getX(), newP.getY());
                    }
                }
            }
            rooms.add(newRoom);
        }
        clean(tiles);
    }

    // todo fixbugs
    private void applyRoom(Room r, TETile[][] tiles) {
/*
        for (int i = r.leftBottom.getX(); i <= r.rightTop.getX(); i++) {
            tiles[i][r.leftBottom.getY()] = Tileset.WALL;
            tiles[i][r.rightTop.getY()] = Tileset.WALL;
        }
        for (int i = r.leftBottom.getY(); i <= r.rightTop.getY(); i++) {
            tiles[r.leftBottom.getX()][i] = Tileset.WALL;
            tiles[r.rightTop.getX()][i] = Tileset.WALL;
        }
*/
        for (int j = r.leftBottom.getY(); j <= r.rightTop.getY(); j++) {
            for (int i = r.leftBottom.getX(); i <= r.rightTop.getX(); i++) {
                tiles[i][j] = Tileset.FLOOR;
            }
        }
        //  engine.renderFrame(tiles);
    }

    private void applyHorizontalTunnel(TETile[][] tiles, int x1, int x2, int y) {
        int startX = Integer.min(x1, x2);
        int endX = Integer.max(x1, x2);
        for (int i = startX; i <= endX; i++) {
            // may  test if valid
            tiles[i][y] = Tileset.FLOOR;
        }
        //   engine.renderFrame(tiles);
    }

    private void applyVerticalTunnel(TETile[][] tiles, int y1, int y2, int x) {
        int startY = Integer.min(y1, y2);
        int endY = Integer.max(y1, y2);
        for (int i = startY; i <= endY; i++) {
            tiles[x][i] = Tileset.FLOOR;
        }
        //    engine.renderFrame(tiles);
    }

    private void clean(TETile[][] tiles) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (!aroundFloor(j, i,tiles)) {
                   tiles[j][i] = Tileset.NOTHING;
                }
            }
        }
    }

    private boolean aroundFloor(int x, int y, TETile[][] tiles) {
        return isFloor(x - 1, y, tiles)
                || isFloor(x + 1, y, tiles)
                || isFloor(x, y - 1, tiles)
                || isFloor(x, y + 1, tiles);
    }

    private boolean isFloor(int x, int y, TETile[][] tiles) {
        x = Integer.max(0, x);
        x = Integer.min(x, width-1);
        y = Integer.max(0, y);
        y = Integer.min(y, height-1);
        return tiles[x][y].equals(Tileset.FLOOR);
    }


    public static void main(String[] args) {
        TETile[][] tiles = new TETile[100][60];
        TERenderer eng = new TERenderer();
        eng.initialize(100, 60);
        MapGenerator gen = new RoomMapGenerator(eng);
        gen.mapGen(tiles);
    }
}
