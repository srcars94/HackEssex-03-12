import com.sun.javafx.jmx.MXNodeAlgorithm;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by Matthew on 03/12/2016.
 */
public class ImageProcessor implements Runnable{

    private BufferedImage image = null;
    private List<RGB[]> imageContents = new ArrayList<>();

    public ImageProcessor(BufferedImage image){
        this.image = image;
    }

    public void run(){
        System.out.println("ANALYSING IMAGE");
        RGB[] line = new RGB[image.getHeight()];
        for(int y = 0; y < image.getHeight(); y++){
            for(int x = 0; x < image.getWidth(); x++){
                int pixel = image.getRGB(x, y);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;
                int avg = (red+green+blue)/3;

                line[x] = new RGB(avg, avg, avg);
            }
            imageContents.add(line);
            line = new RGB[image.getWidth()];
        }
        saveToFile();
    }

    public void outputGrayscale(){
        try{
            File f = new File(System.getProperty("user.dir") + "/1.png");
            ImageIO.write(image, "png", f);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void saveToFile() {
        try {
            PrintWriter out = new PrintWriter("output.txt");
            for(RGB[] line:imageContents){
                for(RGB s:line) {
                    out.print(s);
                }
                out.println();
            }
            out.close();

            out = new PrintWriter("output2.txt");
            for(RGB[] line:imageContents){
                for(RGB s:line) {
                    out.print(getAscii(s.getR()) + "" + getAscii(s.getR()));
                }
                out.println();
            }
            out.close();

        } catch (Exception e) {
            System.out.println("There was a problem outputting the results");
            e.printStackTrace();
        }
    }

    public int getRandomInt(int min, int max){
        Random rnd = new Random();
        return rnd.nextInt(max - min+1) + min;
    }

    public char getAscii(int value){
//        if(isBetween(0, 50, value)) return '#';
//        if(isBetween(51, 100, value)) return '@';
//        if(isBetween(101, 150, value)) return '%';
//        if(isBetween(151, 200, value)) return '&';
//        if(isBetween(201, 255, value)) return '*';

//        if(isBetween(0, 28, value)) return '#';
//        if(isBetween(29, 56, value)) return '@';
//        if(isBetween(57, 84, value)) return '%';
//        if(isBetween(85, 112, value)) return '&';
//        if(isBetween(113, 140, value)) return '?';
//        if(isBetween(141, 168, value)) return '"';
//        if(isBetween(169, 196, value)) return '~';
//        if(isBetween(197, 224, value)) return '*';
//        if(isBetween(225, 255, value)) return '\'';

        if(isBetween(0, 35, value)) return '#';
        if(isBetween(36, 72, value)) return '@';
        if(isBetween(72, 108, value)) return '&';
        if(isBetween(108, 143, value)) return ';';
        if(isBetween(144, 179, value)) return '"';
        if(isBetween(180, 215, value)) return '*';
        if(isBetween(216, 255, value)) return '\'';
        return ' ';
    }
    public boolean isBetween(int min, int max, int value){
        return min <= value && value <= max;
    }
}
