package jimman;

import java.awt.Color;
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
            if(args[0].equals("help") || args[1].equals("help")){
                System.out.println("\nWelcome to JIMMan\n");
                System.out.println("Usage:\tjava -jar JimMan [filepath] [command]\n");
                System.out.println("Available Commands:");
                System.out.println("\thelp\t\tshow this help menu");
                System.out.println("\tinvert\t\tInvert the colors of every pixel");
                System.out.println("\tgreyscale\tGreyscale all pixels");
                System.out.println("\tred\t\tOnly show red pixels\n");
                System.out.println("\tblue\t\tOnly show blue pixels\n");
                System.out.println("\tgreen\t\tOnly show green pixels\n");
                System.out.println("\tmixup\t\tSwap the RGB pixels\n");
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
            j.printMainMenu();
            

            boolean noImage = true;
            BufferedImage img = null;
            String newImagePath = "";
            String userImagePath = "";

            while(noImage){
                
                System.out.print("Please enter an image path: ");
                userImagePath = j.sc.next();

                try{
                    img = Filer.getImage(userImagePath);
                    System.out.println("Success! Your image has been loaded!");
                    JIMMan.printLine();
                    noImage = false;
                }catch(IOException e){
                    System.out.println("\nERROR! Could not load image.");
                }
            }

            System.out.println("What do you want to do to your image?\n");
            System.out.println("1. Invert Colors");
            System.out.println("2. Greyscale");
            System.out.println("3. Reg");
            System.out.println("4. Green");
            System.out.println("5. Blue");
            System.out.println("6. Distorted");
            System.out.println("7. Mixup");
            System.out.print("Selection [0-7]: ");
            int userOption;
            try{
                userOption = j.sc.nextInt();
            }catch(Exception e){
                userOption = -1;
            }
            
            
            
            while(userOption < 1 || userOption > 7){
                System.out.println("\nInvalid option ");
                System.out.print("Selection [0-7]: ");
                try{
                    userOption = j.sc.nextInt();
                }catch(Exception e){
                    j.sc.next();
                    userOption = 0;
                }
            }

            switch (userOption) {
                case 1:
                    img = imgMan.invertColors(img);
                    newImagePath = userImagePath.replaceFirst("[.][^.]+$", "")+"_inverted";
                    break;
                case 2:
                    img = imgMan.greyscale(img);
                    newImagePath = userImagePath+"_greyscale";
                    break;
                case 3:
                    img = imgMan.red(img);
                    newImagePath = userImagePath+"_red";
                    break;
                case 4:
                    img = imgMan.green(img);
                    newImagePath = userImagePath+"_green";
                    break;
                case 5:
                    img = imgMan.blue(img);
                    newImagePath = userImagePath+"_blue";
                    break;
                case 6:
                    img = imgMan.distorted(img);
                    newImagePath = userImagePath+"_distorted";
                    break;    
                case 7:
                    img = imgMan.mixup(img);
                    newImagePath = userImagePath+"_mixup";
                    break;
                default:
                    break;
            }

            try{
                Filer.saveImage(img, newImagePath);
            }catch(IOException e){
                System.out.println("Could not save image. Please try again.");
            }
            
            JIMMan.printLine();
            System.out.println("Your image is saves as "+newImagePath+".jpg");
            System.out.println("Thanks for using JIMMan\n");
           
        }
        
    }
    
    
    /***************************************************************/
    /***************** MENU PRINTERS *******************************/
    /***************************************************************/
    
    private void printMainMenu(){
        System.out.println("\n\n       _ _____ __  __ __  __              ");
        System.out.println("      | |_   _|  \\/  |  \\/  |             ");
        System.out.println("      | | | | | \\  / | \\  / | __ _ _ __   ");
        System.out.println("  _   | | | | | |\\/| | |\\/| |/ _` | '_ \\  ");
        System.out.println(" | |__| |_| |_| |  | | |  | | (_| | | | | ");
        System.out.println("  \\____/|_____|_|  |_|_|  |_|\\__,_|_| |_| \n");
        System.out.println("                   Java IMage MANipulator");
        System.out.println("                        BY: Carl Caldwell");
        System.out.println("-----------------------------------------\n");                                  
    }
    
    private static void printLine(){
        System.out.println("-----------------------------------------\n");
    }
    
    /**********************************************************************/
    /**************** HEADLESS ********************************************/
    /**********************************************************************/
    
    private static void runHeadless(String filename, String command) throws IOException, Exception{
        
        JIMMan j = new JIMMan();
        BufferedImage img = null;
        
        try{
            img = Filer.getImage(filename);
        }catch(IOException e){
            throw new IOException("Could not open image. Please try again.");
        }
        
        switch(command){
            case "invert":
                try{
                    Filer.saveImage(imgMan.invertColors(img), filename.replaceFirst("[.][^.]+$", "")+"_inverted");
                }catch(IOException r){
                    throw new IOException("Could not save image. Please try again.");
                }
                break;
            case "greyscale":
                try{
                    Filer.saveImage(imgMan.greyscale(img), filename.replaceFirst("[.][^.]+$", "")+"_greyscale");
                }catch(IOException r){
                    throw new IOException("Could not save image. Please try again.");
                }
                break;
            case "red":
                try{
                    Filer.saveImage(imgMan.red(img), filename.replaceFirst("[.][^.]+$", "")+"_red");
                }catch(IOException r){
                    throw new IOException("Could not save image. Please try again.");
                }
                break;
            case "green":
                try{
                    Filer.saveImage(imgMan.green(img), filename.replaceFirst("[.][^.]+$", "")+"_green");
                }catch(IOException r){
                    throw new IOException("Could not save image. Please try again.");
                }
                break;
            case "blue":
                try{
                    Filer.saveImage(imgMan.blue(img), filename.replaceFirst("[.][^.]+$", "")+"_blue");
                }catch(IOException r){
                    throw new IOException("Could not save image. Please try again.");
                }
                break;    
            case "distorted":
                try{
                    Filer.saveImage(imgMan.distorted(img), filename.replaceFirst("[.][^.]+$", "")+"_distorted");
                }catch(IOException r){
                    throw new IOException("Could not save image. Please try again.");
                }
                break;
            case "mixup":
                try{
                    Filer.saveImage(imgMan.mixup(img), filename.replaceFirst("[.][^.]+$", "")+"_mixup");
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
