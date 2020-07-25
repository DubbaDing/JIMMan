package jimman;

import java.awt.Color;
import javax.imageio.ImageIO;
import java.awt.image.*;
import java.io.*;
import java.util.Random;
import java.util.Scanner;

/**
 * @author CarlCaldwell
 */
public class JIMMan {
    
    Scanner sc = new Scanner(System.in);
    

    public static void main(String[] args) {
        
        // check if running headless
        if(args.length > 0){
            if(args[0].equals("help")){
                System.out.println("\nWelcome to JIMMan\n");
                System.out.println("Usage:\tjava -jar JimMan [filepath] [command]\n");
                System.out.println("Available Commands:");
                System.out.println("\thelp\t\tshow this help menu");
                System.out.println("\tinvert\t\tInvert the colors of every pixel");
                System.out.println("\tgreyscale\tGreyscale all pixels");
                System.out.println("\tred\t\tGreyscale, but in shades of red\n");
                System.out.println("Example: java -jar JimMan.jar image.jpg invert\n");
                
            }else{
                String filename = "";
                String command = "";

                for(int i=0 ; i < args.length ; i++){
                    if(i==0){
                        filename = args[i];
                    }else if(i==1){
                        command = args[i];
                    }
                }
                try{
                    runHeadless(filename, command);
                }catch(IOException e){
                    System.out.println(e.getMessage()+ " Type 'java -jar JimMan help' for help");
                    return;
                }catch(Exception e){
                    System.out.println(e.getMessage() + " Type 'java -jar JimMan help' for help");
                }
            }
            
            
            
        }else{
        
            JIMMan j = new JIMMan();
            System.out.println("Welcome to JIMMan");
            System.out.println("Jave IMage MANipulator\n");

            boolean noImage = true;
            BufferedImage img = null;
            String newImagePath = "";
            String userImagePath = "";

            while(noImage){
                j.printMainMenu();
                userImagePath = j.sc.next();

                try{
                    img = j.getImage(userImagePath);
                    System.out.println("Your image has been uploaded successfully!");
                    noImage = false;
                }catch(IOException e){
                    System.out.println("Could not upload your image. Please try again.");
                }
            }

            System.out.println("What do you want to do to your image?\n");
            System.out.println("1. Invert Colors");
            System.out.println("2. Greyscale");
            System.out.println("3. Reg");
            System.out.println("4. Green");
            System.out.println("5. Blue");
            System.out.println("6. Distorted");
            System.out.print("Selection: ");
            int userOption = j.sc.nextInt();

            switch (userOption) {
                case 1:
                    img = j.invertColors(img);
                    newImagePath = userImagePath.replaceFirst("[.][^.]+$", "")+"_inverted";
                    break;
                case 2:
                    img = j.greyscale(img);
                    newImagePath = userImagePath+"_greyscale";
                    break;
                case 3:
                    img = j.red(img);
                    newImagePath = userImagePath+"_red";
                    break;
                case 4:
                    img = j.green(img);
                    newImagePath = userImagePath+"_green";
                    break;
                case 5:
                    img = j.blue(img);
                    newImagePath = userImagePath+"_blue";
                    break;
                case 6:
                    img = j.distorted(img);
                    newImagePath = userImagePath+"_distorted";
                    break;    
                case 7:
                    img = j.greyscale(img);
                    newImagePath = userImagePath+"_todo";
                    break;    
                    
                default:
                    break;
            }

            try{
                j.saveImage(img, newImagePath);
            }catch(IOException e){
                System.out.println("Could not save image. Please try again.");
            }
            
            System.out.println("\nDone. Thanks for using JIMMan\n");
           
        }
        
    }
    
    
    /**********************************************************/
    /************ IMAGE I/O FUNCTIONS *************************/
    /**********************************************************/
    
    public void saveImage(BufferedImage imageToSave, String fileName) throws IOException {
        try{
            File imgFile = new File(fileName + ".jpg");
            ImageIO.write(imageToSave, "jpeg", imgFile);
        }catch(IOException e){
            throw new IOException(e);
        }
    }
    
    private BufferedImage getImage(String imageStr) throws IOException{
        File file = new File(imageStr);
        
        BufferedImage image = null;
        try{
            image = ImageIO.read(file);
        }catch(IOException e){
            throw new IOException(e);
        }
        return image;
    }
    
    
    
    /***************************************************************/
    /***************** MENU PRINTERS *******************************/
    /***************************************************************/
    
    private void printMainMenu(){
        System.out.println("--------------------------");
        System.out.println("Sample images:");
        System.out.println("src/img/mountains.jpg:");
        System.out.println("src/img/trashcan.jpeg");
        System.out.println("--------------------------");
        System.out.print("Type a file path to an image: ");
    }
    
    /**************************************************************/
    /*********** IMAGE MANIPULATION *******************************/
    /**************************************************************/
    
    private BufferedImage invertColors(BufferedImage image){
        
        // image coords
        int width = image.getWidth();
        int height = image.getHeight();
        
        for(int x=0 ; x<width ; x++){
            for(int y=0 ; y<height ; y++){
                
                int pixel = image.getRGB(x, y);
                
                Color c = new Color(pixel, true);
                int alpha = 255 - c.getAlpha();
                int red = 255 - c.getRed();
                int green = 255 - c.getGreen();
                int blue = 255 - c.getBlue();
                
                int p = 0;
			p = p | (alpha << 24);
			p = p | (red << 16);
			p = p | (green << 8);
			p = p | blue;
                image.setRGB(x, y, p);
            }
        }
        return image;
    }
    
    private BufferedImage greyscale(BufferedImage image){
        
        // image coords
        int width = image.getWidth();
        int height = image.getHeight();
        
        for(int x=0 ; x<width ; x++){
            for(int y=0 ; y<height ; y++){
                
                int pixel = image.getRGB(x, y);
                
                Color c = new Color(pixel, true);
                int alpha = c.getAlpha();
                int red = c.getRed();
                int green = c.getGreen();
                int blue = c.getBlue();
                
                int newPixel = (red+green+blue)/3;
                
                int p = 0;
			p = p | (alpha << 24);
			p = p | (newPixel << 16);
			p = p | (newPixel << 8);
			p = p | newPixel;
                image.setRGB(x, y, p);
            }
        }
        return image;
    }
    
    private BufferedImage red(BufferedImage image){
        
        // image coords
        int width = image.getWidth();
        int height = image.getHeight();
        
        for(int x=0 ; x<width ; x++){
            for(int y=0 ; y<height ; y++){
                
                int pixel = image.getRGB(x, y);
                
                Color c = new Color(pixel, true);
                int alpha = c.getAlpha();
                int red = c.getRed();
                int green = c.getGreen()-c.getGreen();
                int blue = c.getBlue()-c.getBlue();
                
                
                int p = 0;
			p = p | (alpha << 24);
			p = p | (red << 16);
			p = p | (green << 8);
			p = p | blue;
                image.setRGB(x, y, p);
            }
        }
        return image;
    }
    private BufferedImage green(BufferedImage image){
        
        // image coords
        int width = image.getWidth();
        int height = image.getHeight();
        
        for(int x=0 ; x<width ; x++){
            for(int y=0 ; y<height ; y++){
                
                int pixel = image.getRGB(x, y);
                
                Color c = new Color(pixel, true);
                int alpha = c.getAlpha();
                int red = c.getRed()-c.getRed();
                int green = c.getGreen();
                int blue = c.getBlue()-c.getBlue();
                
                
                int p = 0;
			p = p | (alpha << 24);
			p = p | (red << 16);
			p = p | (green << 8);
			p = p | blue;
                image.setRGB(x, y, p);
            }
        }
        return image;
    }
    private BufferedImage blue(BufferedImage image){
        
        // image coords
        int width = image.getWidth();
        int height = image.getHeight();
        
        for(int x=0 ; x<width ; x++){
            for(int y=0 ; y<height ; y++){
                
                int pixel = image.getRGB(x, y);
                
                Color c = new Color(pixel, true);
                int alpha = c.getAlpha();
                int red = c.getRed()-c.getRed();
                int green = c.getGreen()-c.getGreen();
                int blue = c.getBlue();
                
                
                int p = 0;
			p = p | (alpha << 24);
			p = p | (red << 16);
			p = p | (green << 8);
			p = p | blue;
                image.setRGB(x, y, p);
            }
        }
        return image;
    }
    private BufferedImage mixup(BufferedImage image){
        
        // image coords
        int width = image.getWidth();
        int height = image.getHeight();
        
        for(int x=0 ; x<width ; x++){
            for(int y=0 ; y<height ; y++){
                
                int pixel = image.getRGB(x, y);
                
                Color c = new Color(pixel, true);
                int alpha = c.getAlpha();
                int red = c.getRed();
                int green = c.getGreen();
                int blue = c.getBlue();
                
                
                int p = 0;
			p = p | (alpha << 24);
			p = p | (blue << 16);
			p = p | (red << 8);
			p = p | green;
                image.setRGB(x, y, p);
            }
        }
        return image;
    }
    
    private BufferedImage distorted(BufferedImage image){
        
        // image coords
        int width = image.getWidth();
        int height = image.getHeight();
        
        for(int x=0 ; x<width ; x++){
            for(int y=0 ; y<height ; y++){
                
                int pixel = image.getRGB(x, y);
                
                Color c = new Color(pixel, true);
                Random rand = new Random();
                int n = rand.nextInt(254) + 1;
                
                int alpha = (c.getAlpha()+n)%255;
                int red = (c.getRed()+n)%255;
                int green = (c.getGreen()+n)%255;
                int blue = (c.getBlue()+n)%255;
                
                
                int p = 0;
			p = p | (alpha << 24);
			p = p | (red << 16);
			p = p | (blue << 8);
			p = p | green;
                image.setRGB(x, y, p);
            }
        }
        return image;
    }
    
    
    
    
    
    /**********************************************************************/
    /**************** HEADLESS ********************************************/
    /**********************************************************************/
    
    private static void runHeadless(String filename, String command) throws IOException, Exception{
        
        JIMMan j = new JIMMan();
        BufferedImage img = null;
        
        try{
            img = j.getImage(filename);
        }catch(IOException e){
            throw new IOException("Could not open image. Please try again.");
        }
        
        switch(command){
            case "invert":
                try{
                    j.saveImage(j.invertColors(img), filename.replaceFirst("[.][^.]+$", "")+"_inverted");
                }catch(IOException r){
                    throw new IOException("Could not save image. Please try again.");
                }
                break;
            case "greyscale":
                try{
                    j.saveImage(j.greyscale(img), filename.replaceFirst("[.][^.]+$", "")+"_greyscale");
                }catch(IOException r){
                    throw new IOException("Could not save image. Please try again.");
                }
                break;
            case "red":
                try{
                    j.saveImage(j.red(img), filename.replaceFirst("[.][^.]+$", "")+"_red");
                }catch(IOException r){
                    throw new IOException("Could not save image. Please try again.");
                }
                break;
            case "green":
                try{
                    j.saveImage(j.green(img), filename.replaceFirst("[.][^.]+$", "")+"_green");
                }catch(IOException r){
                    throw new IOException("Could not save image. Please try again.");
                }
                break;
            case "blue":
                try{
                    j.saveImage(j.blue(img), filename.replaceFirst("[.][^.]+$", "")+"_blue");
                }catch(IOException r){
                    throw new IOException("Could not save image. Please try again.");
                }
                break;    
            case "distorted":
                try{
                    j.saveImage(j.distorted(img), filename.replaceFirst("[.][^.]+$", "")+"_distorted");
                }catch(IOException r){
                    throw new IOException("Could not save image. Please try again.");
                }
                break;
            case "mixup":
                try{
                    j.saveImage(j.mixup(img), filename.replaceFirst("[.][^.]+$", "")+"_mixup");
                }catch(IOException r){
                    throw new IOException("Could not save image. Please try again.");
                }
                break;
                
                
            default:
                throw new Exception("Command not recognized. Please try again.");
        }
        
        System.out.println("done.");
        
    }
    
}
