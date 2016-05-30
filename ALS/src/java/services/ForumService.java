/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;
import model.Forum;
import model.ForumPost;
import model.User;
import repositery.ForumRepositery;

/**
 *
 * @author DemiXsoft
 */
public class ForumService {
    
      ForumRepositery fr;
    
      public ForumService() {
        fr = new ForumRepositery();
    }
 
    public void save(Forum f){
        
        fr.CreateForum(f);
    }
    
    public List<ForumPost> getLatestComments()
    {
        return fr.getLatestComments();
    }
    
    public List<ForumPost> getTopCommentors()
    {
        return fr.getTopCommentors();
    }
    
    public List<User> getMembers(int uid)
    {
        return fr.getMembers(uid);
    }
    
    public void update(Forum f)
    {
        fr.update(f);
    }
    
    public List<Forum> getForum()
    {
        return fr.getForum();
    }
    
    public List<Forum> getForumOfCourse(String ID)
    {
        return fr.getForumOfCourse(ID);
    }
    
    public String getMembers(String cid)
    {
        return fr.getMembers(cid);
    }
    
    public void delete(Forum f)
    {
        fr.DeleteForum(f);
    }
    
    public String getTitle(int ID)
    {
        return fr.getTitle(ID);
    }
    
    public String getCourseID(int ID)
    {
        return fr.getCourseID(ID);
    }
    
    public List<ForumPost> getForumPosts(int forum_id)
    {
        return fr.getForumPosts(forum_id);
    }
    
    public String getTopic(int ID)
    {
        return fr.getTopic(ID);
    }
    
    public void resetComments(int fid, int comments)
    {
        fr.resetComments(fid, comments);
    }
    
    public String getCourseName(String CID)
    {
        return fr.getCourseName(CID);
    }
    
    public String getUserName(String UID)
    {
        return fr.getUserName(UID);
    }
    
    public String getUserID(int ID)
    {
        return fr.getUserID(ID);
    }
    
    public Forum getCurrentForum(int fid)
    {
        return fr.getCurrentForum(fid);
    }
    
    public Integer createPost(ForumPost fp)
    {
        return fr.CreatePost(fp);
    }
    
    public String getMostTopicsCourse()
    {
        return fr.getMostTopicsCourse();
    }
    
    public String getMostCommentedTopic()
    {
        return fr.getMostCommentedTopic();
    }
    
    public String getMostViewedTopic()
    {
        return fr.getMostViewedTopic();
    }
    
    public String getTodayComments(String cid)
    {
        return fr.getTodayComments(cid);
    }
    
    public String getTopicsOfCourse(String ID)
    {
        return fr.getTopicsOfCourse(ID);
    }
    
    public Forum getForumModel(Forum f)
    {
        return fr.getForumModel(f);
    }
}
