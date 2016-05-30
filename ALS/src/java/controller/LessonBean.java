/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

/**
 *
 * @author DemiXsoft
 */
public class LessonBean {
    
    String filename="";

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
    
    public LessonBean() throws IOException
    {
        convert(filename);
        
        //File file = new File(filename);
        //File dir = new File("\\Test2");

        //boolean success = file.renameTo(new File(dir, file.getName()));
        //if (!success) {
           //System.out.print("not good");
        //}
    }
    
    
    // Covert PDF File into images
    public void convert(String filename) throws IOException
    {
        PDDocument document = null;  
        document = PDDocument.load( filename );  
        List  pages = document.getDocumentCatalog().getAllPages(); 
        
        int startPage, endPage;
        startPage = Integer.parseInt(pages.get(0).toString());
        endPage = Integer.parseInt(pages.get(pages.size()).toString());
        
        //startPage = 0;
        //endPage = document.getDocumentCatalog().getAllPages().size();
        
        for( int i=startPage-1; i<endPage && i<pages.size(); i++ )  
        {  
            try  
            {  
                PDPage page = (PDPage)pages.get( i );  
                BufferedImage  image = page.convertToImage();  
            }
            catch(IOException e)
            {
                System.out.println(e.getMessage());
            }
        }  
        document.close(); 
    }
}
