/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.util.PDFTextStripper;


/**
 *
 * @author DemiXsoft
 */
public class PDF_Controller {
    
   Timer timer = new Timer("Read");
   private Boolean disable = false;
   MyTask t = new MyTask();
   
   String imageFile;

    public String getImageFile() {
        //ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        String rpath = "data/lesson/slide.jpg";
        return rpath;
    }

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }
 
   public PDF_Controller()
   {
       timer.schedule(t, 0, 5000);
   }
   
   String filePath; 
   private static int lesson_id;

    public static int getLesson_id() {
        return lesson_id;
    }

    public static void setLesson_id(int lesson_id) {
        PDF_Controller.lesson_id = lesson_id;
    }
   

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    
    static int pages;

    public int getPages() throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        PDDocument pd;
        String rFile = ec.getRealPath(filePath);
        File input = new File(rFile);
        pd = PDDocument.load(input);
        return pd.getNumberOfPages();
    }

    public static void setPages(int pages) {
        PDF_Controller.pages = pages;
    }
    
    int pageNumber=0;

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

   
    
    
    public void next() throws IOException, InterruptedException
    {
        disable = true;
        //help.navigate("student_course_lesson_attend.xhtml?ID=" + getLesson_id());
        if(pageNumber < getPages())
        {
            if (t.times < 5)
            {
                help.pop_msg("Pop Question");
            }else{
  //              timer.cancel();
//                timer.schedule(t, 5000);
            }
            pageNumber = pageNumber +1;
            pageData(pageNumber);
            //pageData(pageNumber);
          //  help.navigate("student_course_lesson_attend.xhtml?ID=" + getLesson_id());
         //   disable = false;
        }else{
            help.pop_msg("You are on last page of slide!");
            pageData(pageNumber);
            //help.navigate("student_course_lesson_attend.xhtml?ID=" + getLesson_id());           
        }
    }
    
    public void back() throws IOException, InterruptedException
    {
        if(pageNumber < 1)
        {
            help.pop_msg("You are on the first page of sldie!");
            pageData(pageNumber);
            //help.navigate("student_course_lesson_attend.xhtml?ID=" + getLesson_id());
        }else{
            pageNumber = pageNumber -1;
            pageData(pageNumber);
            pageData(pageNumber);
            //help.navigate("student_course_lesson_attend.xhtml?ID=" + getLesson_id());
            
        }
    }
    
   
    
    
    Helper help = new Helper();
    
    public void pageData(int pageNumber) throws IOException, InterruptedException
    {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        PDDocument pd;
        String rFile = ec.getRealPath(filePath);
        File input = new File(rFile);
        pd = PDDocument.load(input);
        PDPage page = (PDPage)pd.getDocumentCatalog().getAllPages().get(pageNumber);
        BufferedImage bi = page.convertToImage();
        String path = ec.getRealPath("/data/lesson/slide.jpg");
        File outputfile = new File(path);
        
        ImageIO.write(bi, "jpg", outputfile);
        pd.close();
        
        disable = false;
       // disable = false;
    }

    /**
     * @return the disable
     */
    public Boolean getDisable() {
        
        return disable;
    }

    /**
     * @param disable the disable to set
     */
    public void setDisable(Boolean disable) {
        this.disable = disable;
    }
    
}
class MyTask extends TimerTask {
    //times member represent calling times.
    public int times = 0;
 
 
    public void run() {
        times++;
        if (times <= 5) {
            System.out.println("Student is reading...");
        } else {
            System.out.println("Student reading time is completed!");
 
            //Stop Timer.
            this.cancel();
        }
    }
}
