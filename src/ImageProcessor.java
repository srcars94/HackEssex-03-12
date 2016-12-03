import java.awt.image.BufferedImage;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matthew on 03/12/2016.
 */
public class ImageProcessor implements Runnable{

    private BufferedImage image = null;

    private List<String[]> imageContents = new ArrayList<>();

    public ImageProcessor(BufferedImage image){
        this.image = image;
    }

    public void run(){
        System.out.println("ANALYSING IMAGE");
        String[] line = new String[image.getWidth()];
        for(int y = 0; y < image.getHeight(); y++){
            for(int x = 0; x < image.getWidth(); x++){
                line[x] = image.getRGB(x, y) + "";
            }
            imageContents.add(line);
        }
        saveToFile();
    }

    public void saveToFile(){
        try{
            PrintWriter out = new PrintWriter("output.txt");
            for(String[] line:imageContents){
                for(String s:line) {
                    out.print(s);
                }
                out.println();
            }
            out.close();
        }catch (Exception e){
            System.out.println("There was a problem outputting the results");
            e.printStackTrace();
        }
    }
}
