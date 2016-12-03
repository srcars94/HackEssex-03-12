import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.image.ImageView ;
import jdk.nashorn.internal.runtime.regexp.joni.ast.Node;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.TextField;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Samuel on 03/12/2016.
 */
public class Main extends Application{

    private FileChooser fileChooser = new FileChooser();
    private Button btnImage  = new Button();
    private Button btnVid = new Button();
    private Pane root = new Pane();
    private Label myLabelImage = new Label();
    private Label myLabelVid = new Label();
    private Label listImageOutput = new Label();
    private Label imageOutput = new Label();
    private static int x = 1200;
    private static int prefWidthBtn = 150;
    private static int prefWifthHt = 10;
    private static int width, height;

    public static void main(String[] args) {
        try{
            if(args[0].toLowerCase().equals("-nogui")){
                if (args[1].equals("?")) System.out.println("Use: picEditor.jar FILELOCATION/FILENAME.FILEEXTENSION");
                if (!args[1].isEmpty()) {
                    try {
                        String preceding = "";
                        try {
                            if (!args[2].isEmpty()) {
                                if (args[2].equals("1")) preceding = System.getProperty("user.dir");
                            }
                            analyseImage(preceding, args[1]);
                        } catch (Exception e) {
                            System.out.println("You must enter a second argument of \"1\" to use the working directory.");
                        }

                    }catch (Exception e){
                        System.out.println("There was a problem loading the image");
                    }
                }
            }
        }catch (Exception e){
//            e.printStackTrace();
            launch(args);
        }

    }
    private static void analyseImage(String preceding, String imageLocation){
        try {
            File imageToLoad = new File(preceding + imageLocation);
            BufferedImage image = ImageIO.read(imageToLoad);
            //
            //Attempt at scaling image
            width = 64;
            height = 64;
            BufferedImage temp = new BufferedImage(width, height, image.getType());
            Graphics2D g2 = temp.createGraphics();
            g2.drawImage(image, 0, 0, width, height, null);
            g2.dispose();
            image = temp;
            //
            System.out.println(imageToLoad.toString());
            ImageProcessor ip = new ImageProcessor(image);
            Thread ipThread = new Thread(ip);
            ipThread.start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void guiOutput(){
        //read out of file
        //add contents of file into array
        //loop round array
        //add array to gui
        try {
            FileReader fileReader = new FileReader(System.getProperty("user.dir") + "/output2.txt");
            BufferedReader textReader = new BufferedReader(fileReader);
            System.out.println("1");
            List<String> textList = new ArrayList<>();
            for (int y = 0; y < height; y++) {
                textList.add(textReader.readLine());
            }
            for (String s : textList) {
                imageOutput.setText(imageOutput.getText() + s + "\n");
                //listImageOutput.setText(s);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.getIcons().add(new Image("catTest1.gif"));

        // load the image
        Image image = new Image("catTest.jpg");

        // simple displays ImageView the image as is
        ImageView iv1 = new ImageView();
        iv1.setImage(image);

        primaryStage.setTitle("Cat image to ASCII values converter");
        btnImage.setText("Select image");
        btnVid.setText("Select video input");

        //filtering what file extensions can be used
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter(".jpg", "*.jpg"),
                new FileChooser.ExtensionFilter(".png", "*.png"),
                new FileChooser.ExtensionFilter("Other", "*.*")
        );

        //action for what happens when the button is pressed
        btnImage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //open file and file selecting
                fileChooser.setTitle("Open File");
                //remove print statement
//                System.out.println(fileChooser.showOpenDialog(primaryStage));
                analyseImage("", fileChooser.showOpenDialog(primaryStage).toString());
                guiOutput();
            }
        });

        myLabelImage.setText("Select the\nimage you \nwant to convert \nto ASCII values");
        myLabelVid.setText("Select the\n video input \nyou wish to \nconvert into ASCII values");

        //layout and scene size
        btnImage.setLayoutX(x);
        btnImage.setPrefSize(prefWidthBtn, prefWifthHt);
        btnImage.setLayoutY(20);

        btnVid.setLayoutX(x);
        btnVid.setPrefSize(prefWidthBtn, prefWifthHt);
        btnVid.setLayoutY(200);

        myLabelImage.setLayoutX(x);
        myLabelImage.setLayoutY(50);

        myLabelVid.setLayoutX(x);
        myLabelVid.setLayoutY(230);

        listImageOutput.setLayoutX(10);
        listImageOutput.setLayoutY(100);

        root.getChildren().add(btnImage);
        root.getChildren().add(btnVid);
        root.getChildren().add(myLabelImage);
        root.getChildren().add(myLabelVid);
        root.getChildren().add(imageOutput);

        //messing around with image
      /*  HBox hBox = new HBox();
        hBox.getChildren().add(iv1);
        root.getChildren().addAll(hBox);*/

        primaryStage.setScene(new Scene(root,500, 500));
        primaryStage.show();
    }
}

