
package repositery;
import hibernate.HibernateUtil;
import java.util.List;
import model.Forum;
import model.ForumPost;
import model.Todo;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

public class todo_rep {

    private Session con;
    private Transaction tx;
    private List<Todo> tdl;
    private Todo td;
    private Query q;
    
    public List<Todo> getTodoList()
    {
        con = HibernateUtil.getSessionFactory().openSession();
        tx = con.beginTransaction();
        q = con.createSQLQuery("select * from todo");
        tdl =  q.list();
        tx.commit();
        con.close(); 
        return tdl;
    }
    
}
