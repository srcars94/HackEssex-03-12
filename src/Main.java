import javax.imageio.ImageIO;
import java.io.File;

/**
 * Created by Matthew on 03/12/2016.
 */
public class Main {

    public static void main(String[] args) {
        try {
            if (args[0].equals("?")) System.out.println("Use: picEditor.jar FILELOCATION/FILENAME.FILEEXTENSION");

            if (!args[0].isEmpty()) {
                try {
                    String preceding = "";
                    try{
                        if(!args[1].isEmpty()){
                            if(args[1].equals("1")) preceding = System.getProperty("user.dir");
                        }
                    }catch (Exception e){
                        System.out.println("You must enter a second argument of \"1\" to use the working directory.");
                    }
                    File imageToLoad = new File(preceding + args[0]);
                    System.out.println(imageToLoad.toString());
                    ImageProcessor ip = new ImageProcessor(ImageIO.read(imageToLoad));
                    Thread ipThread = new Thread(ip);
                    ipThread.start();
                } catch (Exception e) {
                    System.out.println("There seems to of been a problem loading the image.");
                }
            }
        } catch (Exception e){
            System.out.println("You must enter the path to the file you want to use.");
        }
    }
}
