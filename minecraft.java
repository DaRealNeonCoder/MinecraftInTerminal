import java.util.Random;
import java.util.Scanner;
//perlin noise algo: https://rtouti.github.io/graphics/perlin-noise-algorithm
// minecraft in c, used this as an outline for what to do: https://github.com/tarantino07/minecraft.c

public class minecraft {

  public static int[][] dirtTexture = {
            {6,5,5,5,5,6,5,5,5,5,7,5,5,6,5,6},
            {5,5,7,5,5,5,8,7,5,6,5,5,5,5,7,7},
            {6,5,5,7,6,5,5,5,6,5,5,5,7,7,6,5},
            {5,9,6,5,5,7,5,6,5,5,5,5,5,6,5,5},
            {5,5,5,6,7,5,5,5,5,7,5,9,5,5,7,5},
            {5,7,5,5,5,5,7,7,7,5,5,7,5,5,5,6},
            {6,7,5,5,8,5,5,6,6,5,6,6,5,5,5,5},
            {6,5,6,6,5,5,5,5,5,7,5,5,5,5,5,5},
            {5,5,5,5,5,5,5,7,5,5,5,5,5,5,7,5},
            {5,5,7,5,5,7,7,5,5,5,5,5,6,6,5,5},
            {5,5,5,6,6,5,6,5,7,6,6,5,5,5,8,5},
            {5,5,5,5,5,6,5,5,9,5,5,5,7,5,5,7},
            {5,7,5,5,5,5,6,5,5,5,5,5,5,5,6,6},
            {5,5,5,5,5,5,5,5,5,7,6,7,5,6,5,5},
            {5,5,7,6,5,7,5,7,5,5,5,7,5,5,5,5},
            {5,5,6,5,5,5,8,5,5,5,5,5,5,5,5,7}
        };
       public static int[][] grass_side = {
            {10,10,10,11,11,11,11,11,10,10,11,11,11,11,12,11},
            {10,11,10,10,10,7,11,11,11,10,10,10,11,11,11,11},
            {10,7,13,11,11,7,11,7,10,10,10,14,7,12,11,7},
            {7,9,7,7,11,7,7,7,11,7,11,7,5,7,7,5},
            {5,5,5,6,7,5,5,5,7,7,7,9,5,5,7,5},
            {5,7,5,5,5,5,7,7,7,5,5,7,5,5,5,6},
            {6,7,5,5,8,5,5,6,6,5,6,6,5,5,5,5},
            {6,5,6,6,5,5,5,5,5,7,5,5,5,5,5,5},
            {5,5,5,5,5,5,5,7,5,5,5,5,5,5,7,5},
            {5,5,7,5,5,7,7,5,5,5,5,5,6,6,5,5},
            {5,5,5,6,6,5,6,5,7,6,6,5,5,5,8,5},
            {5,5,5,5,5,6,5,5,9,5,5,5,7,5,5,7},
            {5,7,5,5,5,5,6,5,5,5,5,5,5,5,6,6},
            {5,5,5,5,7,5,5,5,5,7,6,7,5,6,5,5},
            {5,5,7,6,5,7,5,7,5,5,5,7,5,5,5,5},
            {5,5,6,5,5,5,8,5,5,5,5,5,5,5,5,7}
        };

       public static int[][] grass_top = {
            {10,10,10,11,11,11,11,11,10,10,11,11,11,11,12,11},
            {10,11,10,10,10,11,11,11,11,10,10,10,11,11,11,11},
            {10,13,13,11,11,11,11,11,10,10,10,14,11,12,11,13},
            {12,11,11,10,11,10,11,11,11,11,11,10,11,11,11,11},
            {10,11,14,11,14,14,11,14,15,12,11,10,14,11,11,11},
            {10,10,12,10,11,10,10,10,11,10,11,11,12,11,11,10},
            {11,10,10,11,10,11,11,11,11,11,13,14,11,10,10,10},
            {11,11,10,11,11,11,14,11,10,14,11,11,11,10,11,10},
            {11,10,13,14,14,11,14,10,13,14,11,10,11,10,11,11},
            {10,11,13,11,11,10,14,11,11,11,10,14,11,13,11,11},
            {10,10,10,10,11,13,11,10,15,16,14,11,11,11,11,11},
            {11,14,11,10,10,11,10,11,10,10,11,11,11,11,11,11},
            {15,10,11,11,10,11,11,16,12,11,11,11,10,10,16,11},
            {13,10,12,12,11,11,11,15,10,15,10,10,10,11,10,14},
            {10,10,11,11,13,14,10,10,11,10,10,11,10,10,11,10},
            {11,10,11,12,14,10,10,13,11,15,11,11,11,11,11,10}
        };
        public static int[][] woodTexture = {
            {22,21,23,21,21,22,21,23,21,22,21,23,21,21,22,21},
            {22,21,23,21,21,22,21,23,21,22,21,23,21,21,22,21},
            {22,21,23,21,22,22,21,23,21,22,21,23,21,21,6 ,21},
            {22,21,7 ,21,22,22,21,23,21,22,21,23,21,22,22,21},
            {22,21,7 ,21,22,6 ,21,23,21,22,23,23,21,22,22,21},
            {22,21,23,23,22,21,21,7 ,21,22,23,21,21,22,22,21},
            {6 ,21,23,23,22,21,21,7 ,23,22,21,21,21,22,6 ,21},
            {22,21,21,23,22,21,23,7 ,23,22,21,21,21,22,22,21},
            {22,23,21,21,6 ,21,23,23,21,6 ,21,21,21,21,22,21},
            {22,23,21,21,22,21,23,23,21,22,21,7 ,21,21,22,21},
            {22,21,21,21,22,21,21,23,21,22,21,7 ,23,21,22,21},
            {22,21,21,21,22,21,21,23,21,22,21,23,23,21,22,21},
            {22,21,21,21,22,21,21,23,21,22,21,23,21,21,22,21},
            {22,21,21,21,22,21,21,23,21,22,23,21,21,21,22,21},
            {22,21,21,21,22,21,21,23,21,22,23,21,21,21,22,21},
            {22,21,21,21,22,21,21,23,21,22,21,21,21,21,22,21},
        };

        public static int[][] leafTexture = {
            {11,11,11,11,11,11,11,11,11,11,11,11,11,11,12,11},
            {11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11},
            {11,13,13,11,11,11,11,11,11,11,11,14,11,12,11,13},
            {12,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11},
            {11,11,14,11,14,14,11,14,15,12,11,11,14,11,11,11},
            {11,11,12,11,11,11,11,11,11,11,11,11,12,11,11,11},
            {11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11},
            {11,11,11,11,11,11,14,11,11,14,11,11,11,11,11,11},
            {11,11,13,14,14,11,14,11,13,14,11,11,11,11,11,11},
            {11,11,13,11,11,11,14,11,11,11,11,14,11,13,11,11},
            {11,11,11,11,11,13,11,11,15,16,14,11,11,11,11,11},
            {11,14,11,11,11,11,11,11,11,11,11,11,11,11,11,11},
            {15,11,11,11,11,11,11,16,12,11,11,11,11,11,11,11},
            {13,11,12,12,11,11,11,15,11,15,11,11,11,11,11,14},
            {11,11,11,11,13,14,11,11,11,11,11,11,11,11,11,11},
            {11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11}
        };

    public static void main(String[] args) 
    {
        
        Scanner input = new Scanner(System.in);

        int extraScale = 4;


        while (true) {
            System.out.println("Enter scale for rendering (0-6). Higher scale values give a better resolution,");
            System.out.println("at the cost of a longer rendering time (default is 4):");

            extraScale = input.nextInt();

            if (extraScale > 6 || extraScale < 1) {
                System.out.println("Input outside range.");
                System.out.println(" ");
                continue;
            }
            break;
        }

        int Y_PIXELS = 40 * extraScale;
        int X_PIXELS = 120 * extraScale;

        
        //world dimensions in blocks
        int Z_BLOCKS = 10;
        int Y_BLOCKS = 100;
        int X_BLOCKS = 100;
        
        // Height of the eyes on the player model.
        double EYE_HEIGHT  = 1.5;

        Player player = initPosView(EYE_HEIGHT);

        double VIEW_HEIGHT = 0.7;
        double VIEW_WIDTH  = 1.0;

        // how thick the border around each block should be.
        // this helps the player make sense of all the blocks
        double BLOCK_BORDER  = 0.02;
        char curBlock = '1';

        //picture is a double array of the "pixels" to display to the screen.
        char[][] picture  = new char[Y_PIXELS][X_PIXELS];
        byte[][] colors   = new byte[Y_PIXELS][X_PIXELS];
        int[]  permutations = makePermutation();
        // three dimensional array of blocks.
        char[][][] blocks = new char[Z_BLOCKS][Y_BLOCKS][X_BLOCKS];

        boolean[] keystate = new boolean[256];

        initBlocks(blocks, permutations, Z_BLOCKS, Y_BLOCKS, X_BLOCKS);

        getPicture(picture, colors, blocks, player,
            Y_PIXELS, X_PIXELS, Z_BLOCKS, Y_BLOCKS, X_BLOCKS,
            VIEW_HEIGHT, VIEW_WIDTH, BLOCK_BORDER);


        drawAscii(picture, colors, Y_PIXELS, X_PIXELS, player);


         while (true) {

            processInput(input, keystate);

            if (keystate['1']) curBlock = '1';
            if (keystate['2']) curBlock = '2';
            if (keystate['3']) curBlock = '3';
            if (keystate['4']) curBlock = '4';
            if (keystate['5']) curBlock = '5';


            updatePosView(player, keystate, blocks, X_BLOCKS, Y_BLOCKS, Z_BLOCKS, EYE_HEIGHT);

            Vec3 cur = getCurrentBlock(blocks, player, X_BLOCKS, Y_BLOCKS, Z_BLOCKS);

            boolean haveCurrent = !rayOutside(cur.x, cur.y, cur.z, X_BLOCKS, Y_BLOCKS, Z_BLOCKS);

            int cbx = (int) cur.x;
            int cby = (int) cur.y;
            int cbz = (int) cur.z;

            char savedChar = ' ';
            boolean removed = false;

            if (haveCurrent) {
                savedChar = blocks[cbz][cby][cbx];
                blocks[cbz][cby][cbx] = 'o';

                if (keystate['x']) {
                    removed = true;
                    blocks[cbz][cby][cbx] = ' ';
                }


                if (keystate[' ']) {
                        placeBlock(cur, curBlock, blocks, X_BLOCKS, Y_BLOCKS, Z_BLOCKS);
                }
            }

            getPicture(picture, colors, blocks, player,
                Y_PIXELS, X_PIXELS, Z_BLOCKS, Y_BLOCKS, X_BLOCKS,
                VIEW_HEIGHT, VIEW_WIDTH, BLOCK_BORDER);


            drawAscii(picture, colors,  Y_PIXELS, X_PIXELS, player);
            
            if (haveCurrent && !removed) {
                blocks[cbz][cby][cbx] = savedChar;
            }


         }


    }




//---------
// INIT
//---------




    public static void initBlocks(char[][][] blocks, int[] permutations, int Z_BLOCKS, int Y_BLOCKS, int X_BLOCKS) {
        //initialy we set the blocks to empty
        for (int z = 0; z < Z_BLOCKS; z++)
            for (int y = 0; y < Y_BLOCKS; y++)
                for (int x = 0; x < X_BLOCKS; x++)
                    blocks[z][y][x] = ' ';

        Random rand = new Random();
        double scale = 0.05;
        // filling the blocks in.
        for (int x = 0; x < X_BLOCKS; x++) {
             for (int y = 0; y < Y_BLOCKS; y++) {
                double n = Noise2D(x * scale, y * scale, permutations);

                //input normalized to the range [0, 1)
                double normalized = (n + 1) / 2;
                
                int iter = (int) ((normalized * 5.0) + 1);
                for (int z = 0; z < 5; z++) {
                    if (z < iter) blocks[z][y][x] = '@';
                    else if (z < 2) blocks[z][y][x] = '6';
                }
                if (rand.nextDouble() < 0.005) createTree(blocks, x, y, iter);

            }
        }
    }

    //setting the initial position of the player + angles.
    public static Player initPosView(double EYE_HEIGHT) {
        return new Player(new Vec3(50, 50, 4 + EYE_HEIGHT), 0, 0);
    }


    public static void getPicture(
            char[][] picture, byte[][] colors,
            char[][][] blocks, Player player,
            int Y_PIXELS, int X_PIXELS,
            int Z_BLOCKS, int Y_BLOCKS, int X_BLOCKS,
            double VIEW_HEIGHT, double VIEW_WIDTH, double BLOCK_BORDER) {
        
        for (int y = 0; y < Y_PIXELS; y++)
            for (int x = 0; x < X_PIXELS; x++)
                colors[y][x] = 0;
        
        Vec3[][] directions = initDirections(player, Y_PIXELS, X_PIXELS, VIEW_HEIGHT, VIEW_WIDTH);

        // foreach pixel, traces a ray into the world.
        for (int y = 0; y < Y_PIXELS; y++) {
            for (int x = 0; x < X_PIXELS; x++) {
                picture[y][x] = raytrace(
                        player.pos, colors, directions[y][x],
                        blocks,
                        X_BLOCKS, Y_BLOCKS, Z_BLOCKS,
                        BLOCK_BORDER, y, x);
            }
        }

}
    
    public static Vec3 anglesToVect(double phi, double psi) {
        return new Vec3(
            Math.cos(psi) * Math.cos(phi),
            Math.cos(psi) * Math.sin(phi),
            Math.sin(psi)
        );
    }

    // the way rendering works is that we create a unit vector for each "pixel" that we are rendering.
    // we then use this direction to shoot out rays to decide what block we are looking at  (if any),
    // and draw it to the screen.
    public static Vec3[][] initDirections(Player player, int Y_PIXELS, int X_PIXELS,
                                           double VIEW_HEIGHT, double VIEW_WIDTH) {
        double phi = player.phi;
        double psi = player.psi;

        //compute base view directions.
        Vec3 screenDown  = anglesToVect(phi, psi - VIEW_HEIGHT / 2.0);
        Vec3 screenUp    = anglesToVect(phi, psi + VIEW_HEIGHT / 2.0);
        Vec3 screenLeft  = anglesToVect(phi - VIEW_WIDTH / 2.0, psi);
        Vec3 screenRight = anglesToVect(phi + VIEW_WIDTH / 2.0, psi);

        //compute view directions used for the actual directions.
        // not entirely sure why we don't use the vectors above, though if I had to guess id think accuracy.
        Vec3 screenMidVert = screenUp.add(screenDown).scale(0.5);
        Vec3 screenMidHor  = screenLeft.add(screenRight).scale(0.5);
        Vec3 midToLeft = screenLeft.sub(screenMidHor);
        Vec3 midToUp   = screenUp.sub(screenMidVert);

        //array of three dimensional view vectors for each "pixel"
        Vec3[][] dir = new Vec3[Y_PIXELS][X_PIXELS];

        for (int y = 0; y < Y_PIXELS; y++) {
            for (int x = 0; x < X_PIXELS; x++) {
                Vec3 tmp = screenMidHor.add(midToLeft).add(midToUp);
                tmp = tmp.sub(midToLeft.scale(((double) x / (X_PIXELS - 1)) * 2));
                tmp = tmp.sub(midToUp.scale(((double) y / (Y_PIXELS - 1)) * 2));
                tmp.normalize();
                dir[y][x] = tmp;
            }
        }

        return dir;
    }
    
    // heart of rendering.
    //shoots rays and checks if they intersect with the scene and where.
    // works by spliting the scene into planes for each axis and checking if the ray hits the axis individually.
    //conceptuallly for a ray r(t) = dt + o
    //where d = direction, o = origin.
    // we check on each axis like so: k = r(t)
    // k = dt + o
    //solving for t, t  = (k-o)/d
    // t will give us the distance to the axis on the ray direction.
    //  we can then fetch the "material" (just the char eg "@") for block inside blocks[][][] array,
    // using the original ray equation.

    //tldr: steps a ray throught the world until it hits a block
    public static char raytrace(
            Vec3 origin, byte[][] colors, Vec3 dir,
            char[][][] blocks,
            int X_BLOCKS, int Y_BLOCKS, int Z_BLOCKS,
            double BLOCK_BORDER, int pixelY, int pixelX) {

        double eps = 0.01;

        double x = origin.x, y = origin.y, z = origin.z;

        while (!rayOutside(x, y, z, X_BLOCKS, Y_BLOCKS, Z_BLOCKS)) {

            char c = blocks[(int) z][(int) y][(int) x];

            //block border rendering
            if (c != ' ') {
                //texture mapping
                Vec3 data = getUVs(x,y,z);

                colors[pixelY][pixelX] = getColorForBlock(c, (int)data.z, data.x, data.y);
                return onBlockBorder(x, y, z, BLOCK_BORDER) ? '-' : c;
            }

            double dist = 2.0;

            //x axis checking.
            if (dir.x > eps)       dist = Math.min(dist, ((int)(x + 1) - x) / dir.x);
            else if (dir.x < -eps) dist = Math.min(dist, ((int) x      - x) / dir.x);

            //y axis checking
            if (dir.y > eps)       dist = Math.min(dist, ((int)(y + 1) - y) / dir.y);
            else if (dir.y < -eps) dist = Math.min(dist, ((int) y      - y) / dir.y);

            //z axis checking
            if (dir.z > eps)       dist = Math.min(dist, ((int)(z + 1) - z) / dir.z);
            else if (dir.z < -eps) dist = Math.min(dist, ((int) z      - z) / dir.z);

            //step the ray along the direction by how much we are allowed.
            x += (dist + eps) * dir.x;
            y += (dist + eps) * dir.y;
            z += (dist + eps) * dir.z;
        }

        return ' ';
    }

    //tells us if a ray is outside the world.
    public static boolean rayOutside(double x, double y, double z,
                                      int X_BLOCKS, int Y_BLOCKS, int Z_BLOCKS) {
        return x < 0 || x >= X_BLOCKS || y < 0 || y >= Y_BLOCKS || z < 0 || z >= Z_BLOCKS;
    }

    // draws on the edges of the blocks.

    //each block occupies a unit cube, so block boundaries fall on integer coordinates.
    //the distance from any point to the nearest integer tells you how close you are to a face boundary.
    // for an edge to exist, it must be near the edge of the box on two axis
    public static boolean onBlockBorder(double x, double y, double z, double BLOCK_BORDER) {
        int count = 0;
        if (Math.abs(x - Math.round(x)) < BLOCK_BORDER) count++;
        if (Math.abs(y - Math.round(y)) < BLOCK_BORDER) count++;
        if (Math.abs(z - Math.round(z)) < BLOCK_BORDER) count++;
        return count >= 2;
    }


    //rounds to two decimal places.
    public static double round2(double v) {
        return Math.round(v * 100.0) / 100.0;
    }


    //draws picture to screen.
   public static void drawAscii(char[][] picture, byte[][] color,
                                  int Y_PIXELS, int X_PIXELS, Player player) {
        System.out.print("\u001B[0m");    // reset colors first
        System.out.print("\033[0;0H");    // then reposition

        StringBuilder sb = new StringBuilder();
        byte currentColor = -1;
        for (int y = 0; y < Y_PIXELS; y++) {
            sb.append("\u001B[0m\u001B[40m");   // reset + black bg at row start
            currentColor = -1;
            for (int x = 0; x < X_PIXELS; x++) {
                byte nextColor = color[y][x];
                if (nextColor != currentColor) {
                    sb.append(ansiColor(nextColor));
                    currentColor = nextColor;
                }
                char c = picture[y][x];
                if (c == '\0') c = ' ';
                sb.append(c);
            }
            sb.append("\u001B[0m");
            currentColor = -1;
            sb.append("\n");
        }

        sb.append("  pos:(")
          .append(round2(player.pos.x)).append(",")
          .append(round2(player.pos.y)).append(",")
          .append(round2(player.pos.z)).append(")")
          .append("  phi:").append(round2(player.phi))
          .append("  psi:").append(round2(player.psi))
          .append("   WASD=move  IJKL=look  X=break  Space=place  Q=quit 12345=select block");

        System.out.print(sb);
        System.out.flush();// whaaat.
    }

    public static void processInput(Scanner scanner, boolean[] keystate) {
        for (int i = 0; i < 256; i++) keystate[i] = false;
        System.out.print("Input: ");
        String line = scanner.nextLine().toLowerCase();
        for (char c : line.toCharArray())
            if (c >= 0 && c < 256) keystate[(int) c] = true;
    }

    public static int clamp(int res, int min, int max) {
        if (res < min) return min;
        if (res > max) return max;
        return res;
    }

    //updates the players transform using keyboard input
    public static void updatePosView(Player player, boolean[] keystate, char[][][] blocks,
                                      int X_BLOCKS, int Y_BLOCKS, int Z_BLOCKS, double EYE_HEIGHT) {
        //eps stands for eplision, represents the smallest increment.
        double moveEps = 1.0;
        double tiltEps = 0.25;

        double px  = player.pos.x;
        double py  = player.pos.y;
        double pz  = player.pos.z;
        double phi = player.phi;
        double psi = player.psi;

        //clamp transform to be inside the world.
        //this ensure player doesnt walk off.
        int ix = clamp((int) px, 0, X_BLOCKS - 1);
        int iy = clamp((int) py, 0, Y_BLOCKS - 1);

        //standing z position, note that we have to clamp a lil extra, because the player has a height.
        int zStand = clamp((int) (pz - EYE_HEIGHT + 0.01), 0, Z_BLOCKS - 1);

        //if the block we are in is solid, we push the player up.
        //this largely exists to allow the player to tower upwards.
        // depending on the scope of the project, I could add a jump and modify this code to push the player along its negative velocity
        // for collision handling.
        if (blocks[zStand][iy][ix] != ' ') pz += 1;

        int zFall = clamp((int) (pz - EYE_HEIGHT - 0.01), 0, Z_BLOCKS - 1);

        //move the player down if we are in the air.
        if (blocks[zFall][iy][ix] == ' ') pz -= 1;

        //direction controls.
        if (keystate['i']) psi += tiltEps;
        if (keystate['k']) psi -= tiltEps;
        if (keystate['l']) phi += tiltEps;
        if (keystate['j']) phi -= tiltEps;

        Vec3 dir = anglesToVect(phi, psi);
        //the reason we use the look direction is so that pressing "I" for example, will always move the player where they are looking.
        //this is a common design choice for fps games.
        if (keystate['w']) { px += moveEps * dir.x; py += moveEps * dir.y; }
        if (keystate['s']) { px -= moveEps * dir.x; py -= moveEps * dir.y; }
        if (keystate['a']) { px += moveEps * dir.y; py -= moveEps * dir.x; }
        if (keystate['d']) { px -= moveEps * dir.y; py += moveEps * dir.x; }

        player.pos.x = px;
        player.pos.y = py;
        player.pos.z = pz;
        player.phi   = phi;
        player.psi   = psi;
    }

    //--------------------------------------------
    // places a block where the player is looking
    //--------------------------------------------
    public static void placeBlock(Vec3 pos, char block, char[][][] blocks,
                                   int X_BLOCKS, int Y_BLOCKS, int Z_BLOCKS) {
        int bx = (int) pos.x, by = (int) pos.y, bz = (int) pos.z;

        //finds the best side we looking at.
        //a cube has six side so we use six sides.
        //same kinda logic for the block border checking
        double[] dists = {
            Math.abs(bx + 1 - pos.x), Math.abs(pos.x - bx),
            Math.abs(by + 1 - pos.y), Math.abs(pos.y - by),
            Math.abs(bz + 1 - pos.z), Math.abs(pos.z - bz)
        };

        int best = 0;
        for (int i = 1; i < 6; i++)
            if (dists[i] < dists[best]) best = i;

        switch (best) {
            case 0: if (bx + 1 < X_BLOCKS) blocks[bz][by][bx + 1] = block; break;
            case 1: if (bx - 1 >= 0)       blocks[bz][by][bx - 1] = block; break;
            case 2: if (by + 1 < Y_BLOCKS) blocks[bz][by + 1][bx] = block; break;
            case 3: if (by - 1 >= 0)       blocks[bz][by - 1][bx] = block; break;
            case 4: if (bz + 1 < Z_BLOCKS) blocks[bz + 1][by][bx] = block; break;
            case 5: if (bz - 1 >= 0)       blocks[bz - 1][by][bx] = block; break;
        }
    }

    //gets the block player is looking at
    public static Vec3 getCurrentBlock(char[][][] blocks, Player player,
                                        int X_BLOCKS, int Y_BLOCKS, int Z_BLOCKS) {
        double eps = 0.01;

        Vec3 dir = anglesToVect(player.phi, player.psi);

        double x = player.pos.x, y = player.pos.y, z = player.pos.z;

        //runs a ray trace again, this time on the center of the screen (using the players rotation)
        while (!rayOutside(x, y, z, X_BLOCKS, Y_BLOCKS, Z_BLOCKS)) {
            if (blocks[(int) z][(int) y][(int) x] != ' ')
                return new Vec3(x, y, z);

            double dist = 2.0;

            if (dir.x > eps)       dist = Math.min(dist, ((int)(x + 1) - x) / dir.x);
            else if (dir.x < -eps) dist = Math.min(dist, ((int) x      - x) / dir.x);

            if (dir.y > eps)       dist = Math.min(dist, ((int)(y + 1) - y) / dir.y);
            else if (dir.y < -eps) dist = Math.min(dist, ((int) y      - y) / dir.y);

            if (dir.z > eps)       dist = Math.min(dist, ((int)(z + 1) - z) / dir.z);
            else if (dir.z < -eps) dist = Math.min(dist, ((int) z      - z) / dir.z);

            x += (dist + eps) * dir.x;
            y += (dist + eps) * dir.y;
            z += (dist + eps) * dir.z;
        }

        return new Vec3(x, y, z);
    }

    
    public static int[] makePermutation() {
        int[] permutation = new int[512];

        // Fill first 256 values
        for (int i = 0; i < 256; i++) permutation[i] = i;

        // Shuffle
        Random rand = new Random();

        for (int i = 255; i > 0; i--) {
            int index = rand.nextInt(i + 1);
            int temp = permutation[i];
            permutation[i] = permutation[index];
            permutation[index] = temp;
        }

        // Duplicate the table
        for (int i = 0; i < 256; i++) permutation[256 + i] = permutation[i];
        return permutation;
    }

    public static Vec2 GetConstantVector(int v) {
        switch (v & 3) {
            case 0:  return new Vec2( 1.0,  1.0);
            case 1:  return new Vec2(-1.0,  1.0);
            case 2:  return new Vec2(-1.0, -1.0);
            default: return new Vec2( 1.0, -1.0);
        }
    }

    public static double Fade(double t) {
        return ((6 * t - 15) * t + 10) * t * t * t;
    }

    public static double Lerp(double t, double a1, double a2) {
        return a1 + t * (a2 - a1);
    }

    public static double Noise2D(double x, double y, int[] permutation) {
        // Determine grid cell coordinates
        int X = (((int) Math.floor(x)) % 256 + 256) % 256;
        int Y = (((int) Math.floor(y)) % 256 + 256) % 256;

        // Relative x and y inside the cell
        double xf = x - Math.floor(x);
        double yf = y - Math.floor(y);

        // Corner distance vectors
        Vec2 topRight    = new Vec2(xf - 1.0, yf - 1.0);
        Vec2 topLeft     = new Vec2(xf,        yf - 1.0);
        Vec2 bottomRight = new Vec2(xf - 1.0, yf);
        Vec2 bottomLeft  = new Vec2(xf,        yf);

        // Hash values from permutation table
        Vec2 gradTopRight    = GetConstantVector(permutation[permutation[X + 1] + Y + 1]);
        Vec2 gradTopLeft     = GetConstantVector(permutation[permutation[X]     + Y + 1]);
        Vec2 gradBottomRight = GetConstantVector(permutation[permutation[X + 1] + Y]);
        Vec2 gradBottomLeft  = GetConstantVector(permutation[permutation[X]     + Y]);

        // Dot products
        double dotTopRight    = topRight.dot(gradTopRight);
        double dotTopLeft     = topLeft.dot(gradTopLeft);
        double dotBottomRight = bottomRight.dot(gradBottomRight);
        double dotBottomLeft  = bottomLeft.dot(gradBottomLeft);

        // Fade curves
        double u = Fade(xf);
        double v = Fade(yf);

        // Interpolate
        return Lerp(u,
                Lerp(v, dotBottomLeft,  dotTopLeft),
                Lerp(v, dotBottomRight, dotTopRight));
    }


    // helper for getting colors. https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
    public static String ansiColor(byte color) {
        switch (color) {
            case 1:  return "\u001B[32m";        // green
            case 2:  return "\u001B[33m";        // yellow/brown
            case 3:  return "\u001B[34m";        // blue
            case 4:  return "\u001B[37m";        // gray
            case 5:  return "\u001B[48;5;95m";   // main dirt
            case 6:  return "\u001B[48;5;137m";  // lighter dirt
            case 7:  return "\u001B[48;5;58m";   // dark mossy dirt
            case 8:  return "\u001B[48;5;244m";  // gray pebble
            case 9:  return "\u001B[48;5;241m";  // dark pebble
            case 10: return "\u001B[48;5;107m";  // not naming the rest
            case 11: return "\u001B[48;5;71m";
            case 12: return "\u001B[48;5;64m";
            case 13: return "\u001B[48;5;149m";
            case 14: return "\u001B[48;5;113m";
            case 15: return "\u001B[48;5;150m";
            case 16: return "\u001B[48;5;65m";
            case 17: return "\u001B[48;5;22m";
            case 18: return "\u001B[48;5;16m";
            case 19: return "\u001B[48;5;233m";
            case 20: return "\u001B[48;5;234m";
            case 21: return "\u001B[48;5;59m";
            case 22: return "\u001B[48;5;101m";
            case 23: return "\u001B[48;5;52m";
            case 24: return "\u001B[48;5;34m";   // green
            case 25: return "\u001B[48;5;160m";  // red
            case 26: return "\u001B[48;5;166m";  // orange
            case 27: return "\u001B[48;5;91m";   // purple
            case 28: return "\u001B[48;5;232m";  // black
            case 29: return "\u001B[48;5;21m";   // blue
            default: return "\u001B[0m\u001B[40m";   // reset and  black background
        }
    }

    public static byte getColorForBlock(char c) {
        switch (c) {
            case '@': return 24;
            default:   return 0;
        }
    }
   
    // @ = grass block, # = tree block, % = leaf block
    public static byte getColorForBlock(char c, int faceID, double u, double v) {
        switch (c) {
            case '@':
                if      (faceID == 4) return (byte) grass_top[(int)(u * 16)][(int)(v * 16)];
                else if (faceID == 5) return (byte) dirtTexture[(int)(u * 16)][(int)(v * 16)];
                else                  return (byte) grass_side[(int)(u * 16)][(int)(v * 16)];
            case 'o':  return 0;
            case '%':  return (byte) leafTexture[(int)(u * 16)][(int)(v * 16)];
            case '#':  return (byte) woodTexture[(int)(u * 16)][(int)(v * 16)];
            case '1':  return 24;
            case '2':  return 25;
            case '3':  return 26;
            case '4':  return 27;
            case '5':  return 28;
            case '6':  return 29;
            default:   return 0;
        }
    }

    //Takes in a hit point and returns uv + the face hit
    public static Vec3 getUVs(double x, double y, double z)
    {
        int bx = (int) x, by = (int) y, bz = (int) z;

        double[] dists = {
            Math.abs(bx + 1 - x), Math.abs(x - bx),
            Math.abs(by + 1 - y), Math.abs(y - by),
            Math.abs(bz + 1 - z), Math.abs(z - bz)
        };

        int best = 0;
        for (int i = 1; i < 6; i++)
            if (dists[i] < dists[best]) best = i;

        double u = 0, v = 0;
        int faceID;
        switch (best) {
            case 0: u = 1.0 - (z - bz); v = 1.0 - (y - by); faceID = 0; break;
            case 1: u = 1.0 - (z - bz); v = y - by;         faceID = 1; break;
            case 2: v = x - bx;          u = 1.0 - (z - bz); faceID = 2; break;
            case 3: v = x - bx;          u = 1.0 - (z - bz); faceID = 3; break;
            case 4: u = 1.0 - (x - bx);  v = y - by;         faceID = 4; break;
            default: u = x - bx;         v = y - by;         faceID = 5; break;
        }

        return new Vec3(u, v, faceID);
    }

    
    public static void createTree(char[][][] blocks, int x, int y, int z) {
        for (int i = 0; i < 4; i++) {
            if (z + i >= 0 && z + i < blocks.length)
                blocks[z + i][y][x] = '#';
        }

        for (int ix = -2; ix < 3; ix++) {
            for (int iy = -2; iy < 3; iy++) {
                for (int iz = 0; iz < 2; iz++) {
                    int nz = z + 3 + iz, ny = y + iy, nx = x + ix;
                    if (nz < 0 || nz >= blocks.length) continue;
                    if (ny < 0 || ny >= blocks[nz].length) continue;
                    if (nx < 0 || nx >= blocks[nz][ny].length) continue;
                    blocks[nz][ny][nx] = '%';
                }
            }
        }

        for (int ix = -1; ix < 2; ix++) {
            for (int iy = -1; iy < 2; iy++) {
                for (int iz = 0; iz < 2; iz++) {
                    int nz = z + 5 + iz, ny = y + iy, nx = x + ix;
                    if (nz < 0 || nz >= blocks.length) continue;
                    if (ny < 0 || ny >= blocks[nz].length) continue;
                    if (nx < 0 || nx >= blocks[nz][ny].length) continue;
                    if (!(nx == 0 || ny == 0) && nz == z + 6) continue;
                    blocks[nz][ny][nx] = '%';
                }
            }
        }
    }
}

//unfortunately, since we are not permitted to use structs or clases, vectors are represented via double[n]
class Vec3 {
    public double x, y, z;

    public Vec3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vec3 add(Vec3 other) {
        return new Vec3(this.x + other.x, this.y + other.y, this.z + other.z);
    }

    //invert the vec then add.
    public Vec3 sub(Vec3 other) {
        return new Vec3(this.x - other.x, this.y - other.y, this.z - other.z);
    }

    public Vec3 scale(double s) {
        return new Vec3(s * this.x, s * this.y, s * this.z);
    }

    //normalizes a vector by dividing itself with its magnitude.
    //required for directoins.
    public void normalize() {
        double len = Math.sqrt(x * x + y * y + z * z);
        if (len < 0.000001) return;
        x /= len;
        y /= len;
        z /= len;
    }
}
class Vec2 {
    public double x, y;

    public Vec2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double dot(Vec2 other) {
        return this.x * other.x + this.y * other.y;
    }
}
class Player {
    public Vec3 pos;   // world position
    public double phi; // yaw
    public double psi; // pitch

    public Player(Vec3 pos, double phi, double psi) {
        this.pos = pos;
        this.phi = phi;
        this.psi = psi;
    }
}
