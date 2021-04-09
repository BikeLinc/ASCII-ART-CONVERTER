import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Renderer {

    // Image To Render Path
    int scale = 30;
    int[][] texture = null;
    Texture[][] text = null;

    public void start() {
        try {
            BufferedImage image = ImageIO.read(new File("ImageToRender/dgo.png"));
            convert(image);
            writeData(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void convert(BufferedImage image) {
        Texture[][] text = new Texture[image.getHeight()][image.getWidth()];
        int[][] texture = new int[image.getHeight()][image.getWidth()];
        int w = image.getWidth();
        int h = image.getHeight();
        System.out.println(w + ", " + h);
        System.out.println(w/scale + ", " + h/scale);
        
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int pixel = image.getRGB(x, y);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;
                text[y][x] = new Texture(red, green, blue);
                texture[y][x] = red + green + blue;
            }
        }
        this.text = text;
        this.texture = texture;
    }
                                                                                                                                            
    private void writeData(BufferedImage image) {
        String character;
        int colorRange = 765;
        for (int y = 0; y < image.getHeight(); y += scale * 2) {
        	System.out.println();
            for (int x = 0; x < image.getWidth(); x += scale) {
                if (texture[y][x] < colorRange / 21 * 1) {
                    character = " ";
                } else if (texture[y][x] < colorRange / 21 * 2) {
                    character = ",";
                } else if (texture[y][x] < colorRange / 21 * 3) {
                    character = ".";
                } else if (texture[y][x] < colorRange / 21 * 4) {
                    character = "\"";
                } else if (texture[y][x] < colorRange / 21 * 5) {
                    character = "~";
                } else if (texture[y][x] < colorRange / 21 * 6) {
                    character = "!";
                } else if (texture[y][x] < colorRange / 21 * 7) {
                    character = "+";
                } else if (texture[y][x] < colorRange / 21 * 8) {
                    character = ":";
                } else if (texture[y][x] < colorRange / 21 * 9) {
                    character = "v";
                } else if (texture[y][x] < colorRange / 21 * 10) {
                    character = "c";
                } else if (texture[y][x] < colorRange / 21 * 11) {
                    character = "I";
                } else if (texture[y][x] < colorRange / 21 * 12) {
                    character = "0";
                } else if (texture[y][x] < colorRange / 21 * 13) {
                    character = "w";
                } else if (texture[y][x] < colorRange / 21 * 14) {
                    character = "0";
                } else if (texture[y][x] < colorRange / 21 * 15) {
                    character = "X";
                } else if (texture[y][x] < colorRange / 21 * 16) {
                    character = "P";
                } else if (texture[y][x] < colorRange / 21 * 17) {
                    character = "$";
                } else if (texture[y][x] < colorRange / 21 * 17) {
                    character = "#";
                } else if (texture[y][x] < colorRange / 21 * 19) {
                    character = "*";
                } else if (texture[y][x] < colorRange / 21 * 20) {
                    character = "R";
                } else if (texture[y][x] < colorRange / 21 * 21) {
                    character = "B";
                } else {
                    character = "@";
                }
                
                System.out.print(Texture.checkMainColor(text[y][x]) + character + Texture.ANSI_RESET);
            }
            //System.out.println();
        }
    }
    
}

class Texture {
	
	public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RED_BACKGROUND = "\u001b[41m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    
    public Texture(int r, int g, int b) {
    	this.r = r;
    	this.g = g;
    	this.b = b;
    }
	
    public int r;
    public int g;
    public int b;

    public static String checkMainColor(Texture tex) {
    	int r = tex.r;
    	int g = tex.g;
    	int b = tex.b;
    	
    	
    	
        if (r > b && r > g) {
            return ANSI_RED_BACKGROUND + ANSI_BLACK;
        } else if (g > b && g > r) {
            return ANSI_GREEN;
        } else if (b > r && b > g) {
            return ANSI_BLUE;
        }
        return "";
    }
}