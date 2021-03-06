/*
 * The MIT License
 *
 * Copyright 2020 carl.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package jimman;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 *
 * @author carl
 */
public class imgMan {
    
    public static BufferedImage invertColors(BufferedImage image){
        
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
    public static BufferedImage greyscale(BufferedImage image){
        
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
    
    public static BufferedImage red(BufferedImage image){
        
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
    
    public static BufferedImage green(BufferedImage image){
        
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
    
    public static BufferedImage blue(BufferedImage image){
        
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
    
    public static BufferedImage mixup(BufferedImage image){
        
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
    
    public static BufferedImage distorted(BufferedImage image){
        
        // image coords
        int width = image.getWidth();
        int height = image.getHeight();
        
        for(int x=0 ; x<width ; x++){
            for(int y=0 ; y<height ; y++){
                
                int pixel = image.getRGB(x, y);
                
                Color c = new Color(pixel, true);
                Random rand = new Random();
                int n = rand.nextInt(225) + 1;
                
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
    
    
    
}
