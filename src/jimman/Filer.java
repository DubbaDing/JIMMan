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

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author carl
 */
public class Filer {
    
    
    public static void saveImage(BufferedImage imageToSave, String fileName) throws IOException {
        try{
            File imgFile = new File(fileName + ".jpg");
            ImageIO.write(imageToSave, "jpeg", imgFile);
        }catch(IOException e){
            throw new IOException(e);
        }
    }
    
    public static BufferedImage getImage(String imageStr) throws IOException{
        File file = new File(imageStr);
        
        BufferedImage image = null;
        try{
            image = ImageIO.read(file);
        }catch(IOException e){
            throw new IOException(e);
        }
        return image;
    }
    
}
