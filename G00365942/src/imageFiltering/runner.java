package imageFiltering;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import static java.lang.System.out;
import java.io.*;

public class runner {

	public static void main(String[] args) {
		runner r =  new runner();
		r.name();
	}

	public int apply(int pixel, int x, int y) {
		int element = pixel;
		int filter[][] = null;

		for (int row = 0; row < filter.length; row++) {
			for (int col = 0; col < filter[row].length; col++) {
				try {
					// This will cause an exception if we overrun the edges of the image
					// element = image.getRGB(x + col, y + row);
					// Do lots of cool stuff here…. Maybe use some of the code above…
				} catch (Exception e) {
					continue; // Ignore any exception and keep going. It’s good enough J
				}
			}
		}
		return element;
	}

	public void name() {
		// Read in an image and convert to a BufferedImage

		BufferedImage image = null;
		try {
			File input = new File("C:\\data\\picture.png");
			image = ImageIO.read(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(image); // This writes out a lot of useful meta-data about the image.
		;
		for (int y = 0; y < image.getHeight(); y++) { // Loop over the 2D image pixel-by-pixel
			for (int x = 0; x < image.getWidth(); x++) {
				int pixel = image.getRGB(x, y); // Get the pixel at an (x, y) coordinate
				image.setRGB(x, y, 0x00FF0000); // Set the pixel colour at (x, y) to red
				// We can get the RGB colour channels out of a 32-bit int as follows:
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = pixel & 0xff;
				// We can re-create a 32-bit RGB pixel from the channels as follows;
				int rgb = 0;
				rgb = rgb | (red << 16);
				rgb = rgb | (green << 8);
				rgb = rgb | blue;

			}
			
		}
		try {
			ImageIO.write(image, "png", new File("C:\\data\\out.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
