package Library;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;
import Library.OperasiDasarMatrix;

// awokawoak ODG --> Orang Dalam Gangguan Jiwa
// Operasi Dasar Gambar

public class OperasiDasarGambar {
    public Matrix readImage(String imgname){
        OperasiDasarMatrix ODM = new OperasiDasarMatrix();

        BufferedImage img = null;
        Matrix m = new Matrix();
        try {
            File input_file = new File("test/" + imgname);
            img = ImageIO.read(input_file);

            int width = img.getWidth();
            int height = img.getHeight();
            // image file path create an object of
            // BufferedImage type and pass as parameter the
            // width,  height and image int
            // type. TYPE_INT_ARGB means that we are
            // representing the Alpha , Red, Green and Blue
            // component of the image pixel using 8 bit
            // integer value.

            // create Matrix
            ODM.createMatrix(m, height, width);

            System.out.println("Reading complete.");


            System.out.println(img);
        }
        catch (IOException e) {
            System.out.println("Error: " + e);
        }
        return m;
    }

    // public void writeImage(String imgname){
    //     // write
    //     try {
    //         // Output file path
    //         File output_file = new File("test/" + imgname);
  
    //         // Writing to file taking type and path as
    //         ImageIO.write(img, "png", output_file);
  
    //         System.out.println("Writing complete.");
    //     }
    //     catch (IOException e) {
    //         System.out.println("Error: " + e);
    //     }
    // }

    // public Matrix copyGambar(){

    // }
}