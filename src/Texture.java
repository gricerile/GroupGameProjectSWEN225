import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Texture {
	public int[] pixels;
	private String loc;
	public final int SIZE;
	
	public Texture(String location, int size) {
		loc = location;
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		load();
	}
	
	private void load() {
		try {
			BufferedImage image = ImageIO.read(new File(loc));
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0, 0, w, h, pixels, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Texture wood = new Texture("res/sky.png", 65);        // That value represents the pixel size, so the image needs to be symmetrical
	public static Texture brick = new Texture("sad/redbrick.jpg", 64);
	public static Texture bluestone = new Texture("sad/bluestone.jpg", 64);
	public static Texture stone = new Texture("sad/greystone.jpg", 64);
}