package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;

public class World {
    private int width;
    private int height;


    private TETile[][] tiles;

    private MapGenerator gen;

    public World(int w, int h, MapGenerator m) {
        width = w;
        height = h;
        tiles = new TETile[w][h];
        gen = m;
        gen.mapGen(tiles);
    }

    public void render(TERenderer ter) {
        ter.renderFrame(tiles);
    }


    public static void main(String[] args) {
        MapGenerator generator = new RoomMapGenerator();
        World world = new World(100, 60, generator);
        TERenderer ter = new TERenderer();
        ter.initialize(world.width, world.height);
        world.render(ter);
    }

}
