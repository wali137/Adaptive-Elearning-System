/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;
import model.Lesson;
import repositery.Lesson_Rep;

/**
 *
 * @author DemiXsoft
 */
public class Lesson_Service {
    
      Lesson_Rep fr = new Lesson_Rep();
      
      public int save(Lesson l)
      {
          return fr.save(l);
      }
      
      public void update(Lesson l)
      {
          fr.update(l);
      }
      
      public void delete(Lesson l)
      {
          fr.delete(l);
      }
      
      public List<Lesson> getLessons()
      {
          return fr.getLessons();
      }
      
      public List<Lesson> getLessons_Course(String cid)
      {
          return fr.getLessons_Course(cid);
      }
      
      public Lesson getLesson(int id)
      {
          return fr.getLesson(id);
      }
      
}
