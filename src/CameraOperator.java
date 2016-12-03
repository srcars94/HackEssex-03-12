//import com.github.sarxos.webcam.Webcam;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by Matthew on 03/12/2016.
// */
//public class CameraOperator implements Runnable{
//    public Webcam wc;
//    public ImageProcessor ip;
//    public JTextArea txt;
//
//    public CameraOperator(Webcam wc){
//        this.wc = wc;
//        wc.open();
//        JPanel panel = new JPanel();
//        txt = new JTextArea();
//        txt.setColumns(64);
//        txt.setRows(64);
//        panel.add(txt);
//        JFrame window = new JFrame("TESTING STUFF");
//        window.add(panel);
//        window.setResizable(true);
//        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        window.pack();
//        window.setVisible(true);
//    }
//
//    public void run(){
//
//        while(true) {
//            BufferedImage image = wc.getImage();
//            int imageType = image.getType();
//            if (imageType == 0) imageType = 5;
//            int width = 64, height = 64;
//            BufferedImage temp = new BufferedImage(width, height, imageType);
//            Graphics2D g2 = temp.createGraphics();
//            g2.drawImage(image, 0, 0, width, height, null);
//            g2.dispose();
//            image = temp;
//
//            ip = new ImageProcessor(image);
//            ip.setOutput(txt);
//            Thread ipThread = new Thread(ip);
//            ipThread.start();
//
//
//            List<String> output = ip.getFinal();
////            System.out.println(output);
////            setText(file);
//            try {
//                Thread.sleep(1000);
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//
//        }
//
//
//    }
//    synchronized public List<String> getOutput(){
//        return ip.returnFile();
//    }
//
//
//    private void setText(List<String> lst){
//        for(String s:lst){
//            txt.setText(txt.getText() + s + "\n");
////            System.out.println(s);
//        }
//    }
//}
