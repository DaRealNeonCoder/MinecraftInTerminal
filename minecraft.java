import java.util.Random;
import java.util.Scanner;
//perlin noise algo: https://rtouti.github.io/graphics/perlin-noise-algorithm
// minecraft in c, used this as an outline for what to do: https://github.com/tarantino07/minecraft.c

public class minecraft {

    public static void main(String[] args) throws Exception {

        Scanner input = new Scanner(System.in);
        Random rand = new Random();
        int extraScale = 4;
        while(true)
        {
            System.out.println("Enter scale for rendering (0-6). Higher scale values give a better resolution,");
            System.out.println("at the cost of a longer rendering time (default is 4):");

            extraScale = input.nextInt();
            
            if(extraScale > 6 || extraScale < 1)
            {
                System.out.println("Input outside range.");
                System.out.println(" ");
                continue;
            }
            break;
        }
        
        int Y_PIXELS = 40 * extraScale;
        int X_PIXELS = 120 * extraScale;

        input.nextLine();
        
//texture used for rendering the objs.
         int[][] dirtTexture =  {
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
        int[][] grass_side = {
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

        int[][] grass_top = {
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

         int[][] leavesTexture = {
            {17,17,18,7,18,7,7,18,17,17,17,7,18,18,7,17},
            {7,18,18,18,17,17,17,7,19,7,17,7,18,7,7,7},
            {18,19,17,17,19,7,7,17,7,18,7,18,18,7,7,7},
            {18,7,17,17,17,18,7,7,7,17,18,18,18,18,7,19},
            {7,17,7,17,19,18,7,7,18,18,18,17,7,7,18,18},
            {7,7,17,18,18,7,17,17,17,17,18,18,17,17,7,18},
            {17,17,18,17,7,7,7,7,18,17,17,18,7,7,17,7},
            {17,17,17,18,7,7,7,20,17,17,17,7,18,7,17,7},
            {7,7,18,17,17,7,7,7,19,17,7,17,7,18,7,17},
            {7,18,18,18,17,17,17,17,7,18,7,7,7,18,7,7},
            {18,7,7,18,17,18,7,7,17,7,18,7,18,18,18,7},
            {7,17,17,17,18,18,18,7,7,7,18,17,7,18,18,18},
            {17,7,7,18,7,7,18,20,7,18,17,17,17,7,18,7},
            {7,18,18,7,17,17,17,18,18,17,18,7,7,17,7,7},
            {7,7,7,7,7,7,18,18,17,17,18,18,7,7,7,18},
            {7,7,7,7,17,18,18,17,17,17,17,18,19,7,18,7}
        };
        int[][] woodTexture = {
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

        int[][] leafTexture = {
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

        //world dimensions in blocks
        int Z_BLOCKS = 10;
        int Y_BLOCKS = 100;
        int X_BLOCKS = 100;

        // Height of the eyes on the player model.  
        double EYE_HEIGHT   = 1.5;
     
        double VIEW_HEIGHT  = 0.7;
        double VIEW_WIDTH   = 1.0;
     
        // how thick the border around each block should be.
        // this helps the player make sense of all the blocks
        double BLOCK_BORDER = 0.05;

        //picture is a double array of the "pixels" to display to the screen.
        char[][] picture = new char[Y_PIXELS][X_PIXELS];
        byte[][] colors = new byte[Y_PIXELS][X_PIXELS];
        int[] permutations = makePermutation();
        // three dimensional array of blocks.
        char[][][] blocks = new char[Z_BLOCKS][Y_BLOCKS][X_BLOCKS];

        // doubles regarding the players transform.
        // first three are position components.
        // last two are pitch-yaw angles representing the look direction.
        double[] player = new double[5];

        // player[0] = px
        // player[1] = py
        // player[2] = pz
        // player[3] = phi angles, in
        // player[4] = psi

        //Original implementation
        boolean[] keystate = new boolean[256];
        initBlocks(blocks, permutations, rand,  Z_BLOCKS, Y_BLOCKS, X_BLOCKS);
        initPosView(player, EYE_HEIGHT);
        initPicture(picture, Y_PIXELS, X_PIXELS);
        Scanner scanner = new Scanner(System.in);
        char curBlock = '1';
        clearScreen();
        
            getPicture(
                picture,
                colors,
                blocks,
                player,
                Y_PIXELS,
                X_PIXELS,
                Z_BLOCKS,
                Y_BLOCKS,
                X_BLOCKS,
                VIEW_HEIGHT,
                VIEW_WIDTH,
                BLOCK_BORDER, 
                grass_top,
                dirtTexture,
                grass_side, 
                woodTexture, 
                leafTexture
            );

            drawAscii(
                picture,
                colors,
                Y_PIXELS,
                X_PIXELS,
                player
            );

            while (true) {

                processInput(scanner, keystate);

                if (keystate['q'] == true) {
                    clearScreen();
                    System.exit(0);
                    break;
                }
                if(keystate['1'] == true) curBlock = '1';
                if(keystate['2'] == true) curBlock = '2';
                if(keystate['3'] == true) curBlock = '3';
                if(keystate['4'] == true) curBlock = '4';
                if(keystate['5'] == true) curBlock = '5';

                updatePosView(
                    player,
                    keystate,
                    blocks,
                    X_BLOCKS,
                    Y_BLOCKS,
                    Z_BLOCKS,
                    EYE_HEIGHT
                );

                double[] cur = getCurrentBlock(
                    blocks,
                    player,
                    X_BLOCKS,
                    Y_BLOCKS,
                    Z_BLOCKS
                );

                boolean haveCurrent = !rayOutside(
                    cur[0], cur[1], cur[2],
                    X_BLOCKS,
                    Y_BLOCKS,
                    Z_BLOCKS
                );

                int cbx = (int) cur[0];
                int cby = (int) cur[1];
                int cbz = (int) cur[2];

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
                        placeBlock(
                            cur,
                            curBlock,
                            blocks,
                            X_BLOCKS,
                            Y_BLOCKS,
                            Z_BLOCKS
                        );
                    }
                }

                  getPicture(
                    picture,
                    colors,
                    blocks,
                    player,
                    Y_PIXELS,
                    X_PIXELS,
                    Z_BLOCKS,
                    Y_BLOCKS,
                    X_BLOCKS,
                    VIEW_HEIGHT,
                    VIEW_WIDTH,
                    BLOCK_BORDER, 
                    grass_top,
                    dirtTexture,
                    grass_side, 
                    woodTexture, 
                    leafTexture
                );

                if (haveCurrent && !removed) {
                    blocks[cbz][cby][cbx] = savedChar;
                }

                drawAscii(
                    picture,
                    colors,
                    Y_PIXELS,
                    X_PIXELS,
                    player
                );
            }

    }




//---------
// INIT
//---------




//initializes each pixel in the picture to be empty. (java does not initialize the way I want, it uses \0 i think)
    public static void initPicture(
        char[][] picture,
        int Y_PIXELS,
        int X_PIXELS
    ) {
        for (int y = 0; y < Y_PIXELS; y++) {
            for (int x = 0; x < X_PIXELS; x++) {
                picture[y][x] = ' ';
            }
        }
    }




    public static void initBlocks(
        char[][][] blocks,
        int[] permutations,
        Random rand,
        int Z_BLOCKS,
        int Y_BLOCKS,
        int X_BLOCKS
    ) {

        //initialy we set the blocks to empty
        for (int z = 0; z < Z_BLOCKS; z++) {
            for (int y = 0; y < Y_BLOCKS; y++) {
                for (int x = 0; x < X_BLOCKS; x++) {
                    blocks[z][y][x] = ' ';
                }
            }
        }
        

        double scale = 0.05;
        // filling the blocks in.
        for (int x = 0; x < X_BLOCKS; x++) {
            for (int y = 0; y < Y_BLOCKS; y++) {
                
                double n = Noise2D(x * scale , y * scale, permutations);
                //input normalized to the range [0, 1)
                double normalized = (n + 1) / 2;
                int iter  = (int)((normalized * 5.0) + 1);
                for (int z = 0; z < 5; z++) {
                    if(z < iter)blocks[z][y][x] = '@';
                    else if (z < 2)blocks[z][y][x] = '6';
                }

                if(rand.nextDouble() < 0.005)createTree(blocks, x, y, iter);

            } 
        }
    }

    //setting the initial position of the player + angles.
    public static void initPosView(double[] player, double EYE_HEIGHT) {
        player[0] = 50;
        player[1] = 50;
        player[2] = 4 + EYE_HEIGHT;
        player[3] = 0;
        player[4] = 0;
    }
    
    public static void createTree(char[][][] blocks, int x, int y, int z)
    {
        for (int i = 0; i < 4; i++)
        {
            if (z + i >= 0 && z + i < blocks.length)
            {
                blocks[z + i][y][x] = '#';
            }
        }

        for (int ix = -2; ix < 3; ix++)
        {
            for (int iy = -2; iy < 3; iy++)
            {
                for (int iz = 0; iz < 2; iz++)
                {
                    int nz = z + 3 + iz;
                    int ny = y + iy;
                    int nx = x + ix;

                    if (nz < 0 || nz >= blocks.length)
                        continue;

                    if (ny < 0 || ny >= blocks[nz].length)
                        continue;

                    if (nx < 0 || nx >= blocks[nz][ny].length)
                        continue;

                    blocks[nz][ny][nx] = '%';
                }
            }
        }
        for (int ix = -1; ix < 2; ix++)
        {
            for (int iy = -1; iy < 2; iy++)
            {
                for (int iz = 0; iz < 2; iz++)
                {
                    int nz = z + 5 + iz;
                    int ny = y + iy;
                    int nx = x + ix;

                    if (nz < 0 || nz >= blocks.length)continue;

                    if (ny < 0 || ny >= blocks[nz].length) continue;

                    if (nx < 0 || nx >= blocks[nz][ny].length) continue;
                    if(!(nx == 0 || ny == 0) && nz == z + 6) continue;
                    blocks[nz][ny][nx] = '%';
                }
            }
        }
    }

//------
//INPUT
//------
    public static void processInput(
        Scanner scanner,
        boolean[] keystate
        )
    {
        for (int i = 0; i < 256; i++) keystate[i] = false;




        System.out.print("Input: ");
        String line = scanner.nextLine().toLowerCase();




        for (char c : line.toCharArray()) {
            if (c >= 0 && c < 256) keystate[(int) c] = true;
        }
    }
   
    public static int clamp(int res, int min, int max)
    {
        if(res < min) res = min;
        else if(res > max) res= max;
       
        return res;
    }
    //updates the players transform using keyboard input
    public static void updatePosView(
        double[] player,
        boolean[] keystate,
        char[][][] blocks,
        int X_BLOCKS,
        int Y_BLOCKS,
        int Z_BLOCKS,
        double EYE_HEIGHT
    ) {
        //eps stands for eplision, represents the smallest increment.




        double moveEps = 1.0;
        double tiltEps = 0.25;




        double px = player[0];
        double py = player[1];
        double pz = player[2];
        double phi = player[3];
        double psi = player[4];




        //clamp transform to be inside the world.
        //this ensure player doesnt walk off.
        int ix = clamp((int) px, 0, X_BLOCKS - 1);
        int iy = clamp((int) py, 0, Y_BLOCKS - 1);




        //standing z position, note that we have to clamp a lil extra, because the player has a height.
        int zStand = clamp(
            (int)(pz - EYE_HEIGHT + 0.01f),
            0,
            Z_BLOCKS - 1
        );



        //if the block we are in is solid, we push the player up.
        //this largely exists to allow the player to tower upwards.
        // depending on the scope of the project, I could add a jump and modify this code to push the player along its negative velocity
        // for collision handling.
        if (blocks[zStand][iy][ix] != ' ') {
            pz += 1;
        }

        int zFall = clamp(
            (int)(pz - EYE_HEIGHT - 0.01f),
            0,
            Z_BLOCKS - 1
        );

        //move the player down if we are in the air.
        if (blocks[zFall][iy][ix] == ' ') {
            pz -= 1;
        }

        //direction controls.
        if (keystate['i']) psi += tiltEps;
        if (keystate['k']) psi -= tiltEps;
        if (keystate['l']) phi += tiltEps;
        if (keystate['j']) phi -= tiltEps;




        double[] dir = anglesToVect(phi, psi);
        //the reason we use the look direction is so that pressing "I" for example, will always move the player where they are looking.
        //this is a common design choice for fps games.
        if (keystate['w']) {
            px += moveEps * dir[0];
            py += moveEps * dir[1];
        }




        if (keystate['s']) {
            px -= moveEps * dir[0];
            py -= moveEps * dir[1];
        }




        if (keystate['a']) {
            px += moveEps * dir[1];
            py -= moveEps * dir[0];
        }




        if (keystate['d']) {
            px -= moveEps * dir[1];
            py += moveEps * dir[0];
        }




        player[0] = px;
        player[1] = py;
        player[2] = pz;
        player[3] = phi;
        player[4] = psi;
    }

//---------------
//Vector helpers
//---------------

//unfortunately, since we are not permitted to use structs or clases, vectors are represented via double[n]
    public static double[] anglesToVect(double phi, double psi) {
        return new double[]{
            (double)(Math.cos(psi) * Math.cos(phi)),
            (double)(Math.cos(psi) * Math.sin(phi)),
            (double)Math.sin(psi)
        };
    }

    public static double[] vectAdd(double[] a, double[] b) {
        return new double[]{
            a[0] + b[0],
            a[1] + b[1],
            a[2] + b[2]
        };
    }

    public static double[] vectScale(double s, double[] v) {
        return new double[]{
            s * v[0],
            s * v[1],
            s * v[2]
        };
    }

    //invert the vec then add.
    public static double[] vectSub(double[] a, double[] b) {
        return new double[]{
            a[0] - b[0],
            a[1] - b[1],
            a[2] - b[2]
        };
    }
    //normalizes a vector by dividing itself with its magnitude.
    //required for directoins.
    public static void vectNormalize(double[] v) {
        double len = (double)Math.sqrt(
            v[0] * v[0] +
            v[1] * v[1] +
            v[2] * v[2]
        );

        if (len < 0.000001f) {
            return;
        }

        v[0] /= len;
        v[1] /= len;
        v[2] /= len;
    }
   
// the way rendering works is that we create a unit vector for each "pixel" that we are rendering.
// we then use this direction to shoot out rays to decide what block we are looking at  (if any),
// and draw it to the screen.
    public static double[][][] initDirections(
        double[] player,
        int Y_PIXELS,
        int X_PIXELS,
        double VIEW_HEIGHT,
        double VIEW_WIDTH
    ) {
        double phi = player[3];
        double psi = player[4];

        //compute base view directions.
        double[] screenDown = anglesToVect(
            phi,
            psi - VIEW_HEIGHT / 2.0f
        );
        double[] screenUp = anglesToVect(
            phi,
            psi + VIEW_HEIGHT / 2.0f
        );




        double[] screenLeft = anglesToVect(
            phi - VIEW_WIDTH / 2.0f,
            psi
        );




        double[] screenRight = anglesToVect(
            phi + VIEW_WIDTH / 2.0f,
            psi
        );




        //compute view directions used for the actual directions.
        // not entirely sure why we don't use the vectors above, though if I had to guess id think accuracy.




        double[] screenMidVert = vectScale(
            0.5f,
            vectAdd(screenUp, screenDown)
        );




        double[] screenMidHor = vectScale(
            0.5f,
            vectAdd(screenLeft, screenRight)
        );




        double[] midToLeft = vectSub(
            screenLeft,
            screenMidHor
        );




        double[] midToUp = vectSub(
            screenUp,
            screenMidVert
        );




        //array of three dimensional view vectors for each "pixel"
        double[][][] dir = new double[Y_PIXELS][X_PIXELS][];




        for (int y = 0; y < Y_PIXELS; y++) {
            for (int x = 0; x < X_PIXELS; x++) {




                double[] tmp = vectAdd(
                    vectAdd(screenMidHor, midToLeft),
                    midToUp
                );




                tmp = vectSub(
                    tmp,
                    vectScale(
                        ((double)x / (X_PIXELS - 1)) * 2,
                        midToLeft
                    )
                );




                tmp = vectSub(
                    tmp,
                    vectScale(
                        ((double)y / (Y_PIXELS - 1)) * 2,
                        midToUp
                    )
                );




                vectNormalize(tmp);




                dir[y][x] = tmp;
            }
        }




        return dir;
    }
   
//------------
//RAY TRACING
//------------

    //tells us if a ray is outside the world.
    public static boolean rayOutside(
        double x,
        double y,
        double z,
        int X_BLOCKS,
        int Y_BLOCKS,
        int Z_BLOCKS
    ) {
        return x < 0 || x >= X_BLOCKS
            || y < 0 || y >= Y_BLOCKS
            || z < 0 || z >= Z_BLOCKS;
    }

    // draws on the edges of the blocks.

    //each block occupies a unit cube, so block boundaries fall on integer coordinates.
    //the distance from any point to the nearest integer tells you how close you are to a face boundary.
    // for an edge to exist, it must be near the edge of the box on two axis

    public static boolean onBlockBorder(
        double x,
        double y,
        double z,
        double BLOCK_BORDER
    ) {
        int count = 0;

        if (Math.abs(x - Math.round(x)) < BLOCK_BORDER) count++;
        if (Math.abs(y - Math.round(y)) < BLOCK_BORDER) count++;
        if (Math.abs(z - Math.round(z)) < BLOCK_BORDER) count++;
        
        return count >= 2;
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
        double ox,
        double oy,
        double oz,
        double[] dir,
        char[][][] blocks,
        byte[][] colors,
        int X_BLOCKS,
        int Y_BLOCKS,
        int Z_BLOCKS,
        double BLOCK_BORDER, 
        int pixelY,
        int pixelX,
        int[][] grass_top, 
        int[][] dirtTexture, 
        int[][] grass_side,
        int[][] woodTexture,
        int[][] leafTexture) {
       
        double eps = 0.01f;

        double x = ox;
        double y = oy;
        double z = oz;

        while (!rayOutside(
            x, y, z,
            X_BLOCKS,
            Y_BLOCKS,
            Z_BLOCKS
        )) {

            char c = blocks[(int)z][(int)y][(int)x];

            //block border rendering
            if (c != ' ') {
                //texture mapping

                int bx = (int)x;
                int by = (int)y;
                int bz = (int)z;
                
                double[] dists = {
                    Math.abs(bx + 1 - x),
                    Math.abs(x - bx),
                    Math.abs(by + 1 - y),
                    Math.abs(y - by),
                    Math.abs(bz + 1 - z),
                    Math.abs(z - bz)
                };

                int best = 0;

                for (int i = 1; i < 6; i++) {
                    if (dists[i] < dists[best]) {
                        best = i;
                    }
                }
                double u = 0.0, v = 0.0;
                int faceID = 0;
                switch (best) {
                case 0: // hacked all of these in, would have been nice to have a more precise approach.
                    u = 1.0 - (z - bz);        
                    v = 1.0 - (y - by);
                    faceID = 0;
                    break;

                    case 1: 
                        u = 1.0 - (z - bz);
                        v = y - by;
                        faceID = 1;
                        break;

                    case 2: 
                        v = x - bx;
                        u = 1.0 - (z - bz);
                        faceID = 2;
                        break;

                    case 3: 
                        v = x - bx;
                        u = 1.0 - (z - bz);
                        faceID = 3;
                        break;

                    case 4: 
                        u = 1.0 - (x - bx);
                        v = y - by;
                        faceID = 4;
                        break;

                    case 5: 
                        u = x - bx;
                        v = y - by;
                        faceID = 5;
                        break;
                }
                colors[pixelY][pixelX] = getColorForBlock(c, faceID, u, v, grass_top, dirtTexture, grass_side, woodTexture, leafTexture);
                return onBlockBorder(x, y, z, BLOCK_BORDER) ? '-' : c;
            }
            
            double dist = 2.0f;

            //x axis checking.
            if (dir[0] > eps) {
                dist = Math.min(
                    dist,
                    ((int)(x + 1) - x) / dir[0]
                );
            } else if (dir[0] < -eps) {
                dist = Math.min(
                    dist,
                    ((int)x - x) / dir[0]
                );
            }

            //y axis checking
            if (dir[1] > eps) {
                dist = Math.min(
                    dist,
                    ((int)(y + 1) - y) / dir[1]
                );
            } else if (dir[1] < -eps) {
                dist = Math.min(
                    dist,
                    ((int)y - y) / dir[1]
                );
            }

            //z axis checking
            if (dir[2] > eps) {
                dist = Math.min(
                    dist,
                    ((int)(z + 1) - z) / dir[2]
                );
            } else if (dir[2] < -eps) {
                dist = Math.min(
                    dist,
                    ((int)z - z) / dir[2]
                );
            }

            //step the ray along the direction by how much we are allowed.
            x += (dist + eps) * dir[0];
            y += (dist + eps) * dir[1];
            z += (dist + eps) * dir[2];
        }

        return ' ';
    }
//--------
// PICTURE
//--------

//foreach pixel, traces a ray into the world.
    static void getPicture(
        char[][] picture,
        byte[][] colors,
        char[][][] blocks,
        double[] player,
        int Y_PIXELS,
        int X_PIXELS,
        int Z_BLOCKS,
        int Y_BLOCKS,
        int X_BLOCKS,
        double VIEW_HEIGHT,
        double VIEW_WIDTH,
        double BLOCK_BORDER,
        int[][] grass_top, 
        int[][] dirtTexture, 
        int[][] grass_side,
        int[][] woodTexture,
        int[][] leafTexture)
    {

          for (int y = 0; y < Y_PIXELS; y++) {
        for (int x = 0; x < X_PIXELS; x++) {
            colors[y][x] = 0;
        }
    }
        double[][][] directions = initDirections(
            player,
            Y_PIXELS,
            X_PIXELS,
            VIEW_HEIGHT,
            VIEW_WIDTH
        );

        for (int y = 0; y < Y_PIXELS; y++) {
            for (int x = 0; x < X_PIXELS; x++) {

                picture[y][x] = raytrace(
                    player[0],
                    player[1],
                    player[2],
                    directions[y][x],
                    blocks,
                    colors,
                    X_BLOCKS,
                    Y_BLOCKS,
                    Z_BLOCKS,
                    BLOCK_BORDER,
                    y,
                    x,
                    grass_top,
                    dirtTexture,   
                    grass_side, 
                    woodTexture, 
                    leafTexture
                );
                //colors[y][x] = getColorForBlock(picture[y][x]);
            }
        }
    }

//gets the block player is looking at
    public static double[] getCurrentBlock(
        char[][][] blocks,
        double[] player,
        int X_BLOCKS,
        int Y_BLOCKS,
        int Z_BLOCKS
    ) {
        double eps = 0.01f;


        double[] dir = anglesToVect(
            player[3],
            player[4]
        );

        double x = player[0];
        double y = player[1];
        double z = player[2];


        //runs a ray trace again, this time on the center of the screen (using the players rotation)
        while (!rayOutside(
            x, y, z,
            X_BLOCKS,
            Y_BLOCKS,
            Z_BLOCKS
        )) {

            if (blocks[(int)z][(int)y][(int)x] != ' ') {
                return new double[]{ x, y, z };
            }


            double dist = 2.0f;


            if (dir[0] > eps) {
                dist = Math.min(
                    dist,
                    ((int)(x + 1) - x) / dir[0]
                );
            } else if (dir[0] < -eps) {
                dist = Math.min(
                    dist,
                    ((int)x - x) / dir[0]
                );
            }

            if (dir[1] > eps) {
                dist = Math.min(
                    dist,
                    ((int)(y + 1) - y) / dir[1]
                );
            } else if (dir[1] < -eps) {
                dist = Math.min(
                    dist,
                    ((int)y - y) / dir[1]
                );
            }




            if (dir[2] > eps) {
                dist = Math.min(
                    dist,
                    ((int)(z + 1) - z) / dir[2]
                );
            } else if (dir[2] < -eps) {
                dist = Math.min(
                    dist,
                    ((int)z - z) / dir[2]
                );
            }




            x += (dist + eps) * dir[0];
            y += (dist + eps) * dir[1];
            z += (dist + eps) * dir[2];
        }




        return new double[]{ x, y, z };
    }

    //--------------------------------------------
    // places a block where the player is looking
    //--------------------------------------------
    public static void placeBlock(
        double[] pos,
        char block,
        char[][][] blocks,
        int X_BLOCKS,
        int Y_BLOCKS,
        int Z_BLOCKS
    ) {
        int bx = (int) pos[0];
        int by = (int) pos[1];
        int bz = (int) pos[2];




        //finds the best side we looking at.
        //a cube has six side so we use six sides.
        //same kinda logic for the block border checking
        
        double[] dists = {
            Math.abs(bx + 1 - pos[0]),
            Math.abs(pos[0] - bx),
            Math.abs(by + 1 - pos[1]),
            Math.abs(pos[1] - by),
            Math.abs(bz + 1 - pos[2]),
            Math.abs(pos[2] - bz)
        };

        int best = 0;

        for (int i = 1; i < 6; i++) {
            if (dists[i] < dists[best]) {
                best = i;
            }
        }

        switch (best) {

            case 0:
                if (bx + 1 < X_BLOCKS) {
                    blocks[bz][by][bx + 1] = block;
                }
                break;

            case 1:
                if (bx - 1 >= 0) {
                    blocks[bz][by][bx - 1] = block;
                }
                break;
           
            case 2:
                if (by + 1 < Y_BLOCKS) {
                    blocks[bz][by + 1][bx] = block;
                }
                break;


            case 3:
                if (by - 1 >= 0) {
                    blocks[bz][by - 1][bx] = block;
                }
                break;

            case 4:
                if (bz + 1 < Z_BLOCKS) {
                    blocks[bz + 1][by][bx] = block;
                }
                break;




            case 5:
                if (bz - 1 >= 0) {
                    blocks[bz - 1][by][bx] = block;
                }
                break;
        }
    }

    //rounds to two decimal places.
    public static double round2(double v) {
        return Math.round(v * 100.0) / 100.0;
    }
   
    public static double dot2(double[] v, double[] v2)
    {
        return v[0] * v2[0] + v[1] * v2[1];
    }
   
    public static int[] makePermutation() {
        int[] permutation = new int[512];


        // Fill first 256 values
        for (int i = 0; i < 256; i++) {
            permutation[i] = i;
        }


        // Shuffle
        Random rand = new Random();


        for (int i = 255; i > 0; i--) {
            int index = rand.nextInt(i + 1);


            int temp = permutation[i];
            permutation[i] = permutation[index];
            permutation[index] = temp;
        }


        // Duplicate the table
        for (int i = 0; i < 256; i++) {
            permutation[256 + i] = permutation[i];
        }


        return permutation;
    }
 
   
    
    public static double[] GetConstantVector(int v) {
        int h = v & 3;

        switch(h) {
            case 0: return new double[]{1.0, 1.0};
            case 1: return new double[]{-1.0, 1.0};
            case 2: return new double[]{-1.0, -1.0};
            default: return new double[]{1.0, -1.0};
        }
}
   
    public static double Fade(double t) {
    return ((6*t - 15)*t + 10)*t*t*t;
    }


    public static double Lerp(double t, double a1, double a2) {
            return a1 + t*(a2-a1);
    }
   
    public static double Noise2D(double x, double y, int[] permutation) {


    // Determine grid cell coordinates
        int X = (((int)Math.floor(x)) % 256 + 256) % 256;
        int Y = (((int)Math.floor(y)) % 256 + 256) % 256;


        // Relative x and y inside the cell
        double xf = x - Math.floor(x);
        double yf = y - Math.floor(y);


        // Corner distance vectors
        double[] topRight = new double[]{xf - 1.0, yf - 1.0};
        double[] topLeft = new double[]{xf, yf - 1.0};
        double[] bottomRight = new double[]{xf - 1.0, yf};
        double[] bottomLeft = new double[]{xf, yf};


        // Hash values from permutation table
        int valueTopRight = permutation[permutation[X + 1] + Y + 1];
        int valueTopLeft = permutation[permutation[X] + Y + 1];
        int valueBottomRight = permutation[permutation[X + 1] + Y];
        int valueBottomLeft = permutation[permutation[X] + Y];


        // Gradient vectors
        double[] gradTopRight = GetConstantVector(valueTopRight);
        double[] gradTopLeft = GetConstantVector(valueTopLeft);
        double[] gradBottomRight = GetConstantVector(valueBottomRight);
        double[] gradBottomLeft = GetConstantVector(valueBottomLeft);


        // Dot products
        double dotTopRight =
                topRight[0] * gradTopRight[0] +
                topRight[1] * gradTopRight[1];


        double dotTopLeft =
                topLeft[0] * gradTopLeft[0] +
                topLeft[1] * gradTopLeft[1];


        double dotBottomRight =
                bottomRight[0] * gradBottomRight[0] +
                bottomRight[1] * gradBottomRight[1];


        double dotBottomLeft =
                bottomLeft[0] * gradBottomLeft[0] +
                bottomLeft[1] * gradBottomLeft[1];


        // Fade curves
        double u = Fade(xf);
        double v = Fade(yf);


        // Interpolate
        return Lerp(
                u,
                Lerp(v, dotBottomLeft, dotTopLeft),
                Lerp(v, dotBottomRight, dotTopRight)
        );
    }

 
    //draws picture to screen.

    public static void drawAscii(
        char[][] picture,
        byte[][] color,    
        int Y_PIXELS,
        int X_PIXELS,
        double[] player
    ) {
            System.out.print("\u001B[0m");    // reset colors first
            System.out.print("\033[0;0H");    // then reposition




        StringBuilder sb = new StringBuilder();
        byte currentColor = -1;
        for (int y = 0; y < Y_PIXELS; y++) {
            sb.append("\u001B[0m\u001B[40m");   // reset + black bg at row start
            currentColor = -1;
            for (int x = 0; x < X_PIXELS; x++) {
                // NO reset here — removed
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




        sb.append("  pos:(").append(round2(player[0])).append(",")
        .append(round2(player[1])).append(",").append(round2(player[2]))
        .append(")  phi:").append(round2(player[3]))
        .append("  psi:").append(round2(player[4]))
        .append("   WASD=move  IJKL=look  X=break  Space=place  Q=quit 12345=select block");




        System.out.print(sb);
        System.out.flush();// whaaat.
    }
   // helper for getting colors. https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
    public static String ansiColor(int color) {
    switch(color) {
        case 1: return "\u001B[32m";        // green
        case 2: return "\u001B[33m";        // yellow/brown
        case 3: return "\u001B[34m";        // blue
        case 4: return "\u001B[37m";        // gray
        case 5: return "\u001B[48;5;95m";   // main dirt
        case 6: return "\u001B[48;5;137m";  // lighter dirt
        case 7: return "\u001B[48;5;58m";   // dark mossy dirt
        case 8: return "\u001B[48;5;244m";  // gray pebble
        case 9: return "\u001B[48;5;241m";  // dark pebble
        case 10: return "\u001B[48;5;107m"; // not naming the rest  
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
        case 29: return "\u001B[48;5;21m";  // blue
        default: return "\u001B[0m\u001B[40m";   // reset and  black background
    }
}
public static void visualizeDirtTexture(int[][] texture) {
    final String RESET = "\u001B[0m";

    for (int[] row : texture) {
        for (int cell : row) {
            String color = ansiColor(cell);
            char ch = switch (cell) {
                case 5 -> '.';  // most common, neutral
                case 6 -> 'o';  // slightly lighter variant
                case 7 -> '#';  // dark olive
                case 8 -> '+';  // gray speckle
                case 9 -> '*';  // dark gray pebble
                default -> '?';
            };
            System.out.print(color + ch + ch + RESET);
        }
        System.out.println();
    }
}
    public static byte getColorForBlock(char c, int faceID, double u, double v, int[][] grass_top, int[][] dirtTexture, int[][] grass_side, int[][] woodTexture, int[][] leafTexture) {
        //@ = grass block, # = tree block, % = leaf block 
        switch (c) {
            case '@': 
                if(faceID == 4) return (byte)grass_top[(int)(u * 16)][(int)(v * 16)]; 
                else if(faceID == 5) return (byte)dirtTexture[(int)(u * 16)][(int)(v * 16)];
                else  return (byte)grass_side[(int)(u * 16)][(int)(v * 16)];
            case '#':  
                return (byte)woodTexture[(int)(u * 16)][(int)(v * 16)];
            case 'A': return 4; // gray highlight
            case '-': return 4; // border
            case '%':
                return (byte)leafTexture[(int)(u * 16)][(int)(v * 16)];

            case '1': return 24;   
            case '2': return 25;   
            case '3': return 26;   
            case '4': return 27;   
            case '5': return 28;    
            case '6': return 29;    

            default:  return 0; // reset / no color
        }
    }
    //clears the terminal

    public static void clearScreen() {
        System.out.print("\033[2J\033[H");
        System.out.flush();
    }
}






