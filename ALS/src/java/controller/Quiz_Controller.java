/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.Answers;
import model.Question;
import model.Quiz;
import model.QuizResult;
import model.QuizSubmission;
import repositery.Quiz_Rep;
import services.Quiz_Service;

/**
 *
 * @author DemiXsoft
 */
public class Quiz_Controller {
      
    public Quiz_Controller() {
       qs =new Quiz_Service();
       q=new Quiz();
       myAnswers = new ArrayList<Answers>();
    }
    
    // Getter / setter + Global Variables
    Quiz_Service qs;
    Quiz_Rep qr = new Quiz_Rep();
    Quiz q = new Quiz();
    int quiz_id=0;
    int max_id;
    String course_id;
    List<Quiz> quizList;
    int ID;
    String QuizTitle;
    String Marks;
    String Duration;

    public String getMarks() {
        return qr.getMarks(ID);
    }

    public void setMarks(String Marks) {
        this.Marks = Marks;
    }

    public String getDuration() {
        return qr.getDuration(ID);
    }

    public void setDuration(String Duration) {
        this.Duration = Duration;
    }

    public String getSection() {
        return qr.getSection(ID);
    }

    public void setSection(String Section) {
        this.Section = Section;
    }

    public String getTopic() {
        return qr.getTopic(ID);
    }
    
    List<Quiz> submissions;
    int uid;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
    

    public List<Quiz> getSubmissions() {
        return qs.submissions(uid, getCourse_id());
    }
    
    Answers ans = new Answers();
    String Answervalue;

    public String getAnswervalue() {
        return Answervalue;
    }

    public void setAnswervalue(String Answervalue) {
        this.Answervalue = Answervalue;
    }
    
    static List<Answers> myAnswers;
    
    boolean Enabled = true;

    public boolean isEnabled() {
        return Enabled;
    }

    public void setEnabled(boolean Enabled) {
        this.Enabled = Enabled;
    }
    
    String qtype;

    public String getQtype() {
        return qtype;
    }

    public void setQtype(String qtype) {
        this.qtype = qtype;
    }
    

    Answers a = new Answers();
    
    
    
    QuizResult res = new QuizResult();
    int student_id;

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }
    
    
    public void saveAnswer() throws IOException
    {
        nextQuestion(question_id);
        int obt_marks = 0;
        int xx = myAnswers.size();
        for(int i=0;i<myAnswers.size();i++)
        {
            a = myAnswers.get(i);
            if(a.getQtype().contains("mcq"))
            {
                String correct = qr.getCorrectMcq(a.getQuestionId());
                if (correct.contains(a.getAnswer()))
                {
                    String mark = qr.getQuestionMarks(a.getQuestionId());
                    int m = Integer.parseInt(mark);
                    obt_marks = obt_marks + m;
                    qr.saveAnswer(a);
                }
            }
            if(a.getQtype().contains("blank"))
            {
                String correct = qr.getCorrectBlank(a.getQuestionId());
                if (correct.contains(a.getAnswer()))
                {
                    String mark = qr.getQuestionMarks(a.getQuestionId());
                    int m = Integer.parseInt(mark);
                    obt_marks = obt_marks + m;
                    qr.saveAnswer(a);
                }
            }
            if(a.getQtype().contains("num"))
            {
                String correct = qr.getCorrectNum(a.getQuestionId());
                if (correct.contains(a.getAnswer()))
                {
                    String mark = qr.getQuestionMarks(a.getQuestionId());
                    int m = Integer.parseInt(mark);
                    obt_marks = obt_marks + m;
                    qr.saveAnswer(a);
                }
            }
        }
            //Saving submission
            qsub.setCourseId(qr.getCourseID(quiz_id));
            qsub.setQuizId(quiz_id);
            qsub.setStudentId(student_id);
            qsub.setSubmissionDate(new Date());
            int sub_id = qr.saveSubmission(qsub);
            //saving results
            res.setMarksObtained(obt_marks);
            String t1 = qr.getMarks(quiz_id);
            int tm = Integer.parseInt(t1);
            res.setTotakMarks(tm);
            res.setQuizSubmissionId(sub_id);
            res.setStudentId(student_id);
            res.setFeedback(feedback);
            qr.saveResult(res);
            myAnswers.clear();
            currentQuestion = 0;
        help.pop_msg("Quiz Attended Sucessfully!");
    }
    
    String feedback;

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
    
    QuizSubmission qsub = new QuizSubmission();

    public void setSubmissions(List<Quiz> submissions) {
        this.submissions = submissions;
    }

   
    public void setTopic(String Topic) {
        this.Topic = Topic;
    }
    
    public List<Question> getQuizQuestions()
    {
        return qr.getQuizQuestions(quiz_id);
    }
    
    String Section;
    String Topic;
    

    public String getQuizTitle() {
        setQuizTitle();
        return QuizTitle;
    }

    public void setQuizTitle() {
        this.QuizTitle = qs.getTitle(ID);
    }
    
    public void delete() throws IOException
    {
        q.setId(ID);
        qs.delete(q);
        jump("teacher_course_quiz_view.xhtml?cid=" + getCourse_id());
    }
    
    public void cancel_del() throws IOException
    {
        jump("teacher_course_quiz_view.xhtml?cid=" + getCourse_id());
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    
    Object mcqs = new Object();

    public Object getMcqs() {
        return mcqs;
    }

    public void setMcqs(int QID) {
        this.mcqs = qr.fetchMCQ(QID);
    }
    
    Question question = new Question();

    public Question getQuestion() {
        return qr.fetchQuestion(question_id);
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
    
    

    

    public List<Quiz> getQuizList() {
        quizList = qs.getQuiz();
        return quizList;
    }

    public void setQuizList(List<Quiz> quizList) {
        this.quizList = quizList;
    }
    
    
    public String getCourse_id()
    {
        return this.course_id;
    }
    
    public void setCourse_id(String course_id)
    {
        this.course_id = course_id;
    }
    
    Helper help = new Helper();
    
    public void update() throws IOException
    {
        qs.update(q);
        help.navigate("teacher_course_quiz_view.xhtml?cid=" + q.getCourseId());
    }

    public int getMax_id() {
        return Integer.parseInt(qs.getMaxID())+1;
    }

    public void setMax_id(int max_id) {
        this.max_id = max_id;
    }
    

    public int getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(int quiz_id) {
        if(this.quiz_id == 0)
        {
            this.quiz_id = quiz_id;
            q = qr.fetchQuiz(quiz_id);
        }
    }

    private static int question_id;

    public static int getQuestion_id() {
        return question_id;
    }

    public static void setQuestion_id(int question_id) {
        Quiz_Controller.question_id = question_id;
    }
    



    

    
    public Quiz getQ() {
        if(q==null)
        {
            q = new Quiz();
        }
        return q;
    }

   
    public void setQ(int ID) {
        this.q = qr.fetchQuiz(ID);
    }
   
    public void save() throws IOException
    {
        q.setCreatedDate(new Date());
        q.setCreatedBy(Integer.parseInt(getCurrentUserId()));
        q.setCourseId(course_id);
        
        int id = qs.save(q);
        quiz_id = id;
        
        jump("teacher_course_quiz_create_question.xhtml?ID=" + id);
    }
    
    
    
    public String getCurrentUserId()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
        String name = (String) session.getAttribute("id");
        return name;
    }
    
    private void jump(String pageName) throws IOException
    {
        FacesContext.getCurrentInstance().getExternalContext().redirect(pageName); 
    }
    
    
    // Attending the Quiz
    // Fresh Logic    
    
    List<Integer> quizQuestionIds = new ArrayList<Integer>();
    int currentQuestion = 0;
    int finish_size = -1;
    int currentQuestionCounter;

    public int getCurrentQuestionCounter() {
        return currentQuestionCounter;
    }

    public void setCurrentQuestionCounter(int currentQuestionCounter) {
        this.currentQuestionCounter = currentQuestionCounter;
    }

    public int getFinish_size() {
        int ret=100000;
        if(quizQuestionIds !=null)
        {
            if(quizQuestionIds.size()>0)
            {
                ret = quizQuestionIds.get(quizQuestionIds.size()-1);
            }
        }
        return ret;
    }

    public void setFinish_size(int finish_size) {
        this.finish_size = finish_size;
    }

    public int getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(int currentQuestion) {
        this.currentQuestion = currentQuestion;
    }
    
    public List<Integer> getQuizQuestionIds() {
        if(quizQuestionIds.size()<1)
        {
        List<Integer> ii= new ArrayList<Integer>();
        ii = qr.getQuizQuestionIds(quiz_id);
        currentQuestion = ii.get(0);
        currentQuestionCounter=1;
        quizQuestionIds=ii;
        }
        return quizQuestionIds;
    }

    public void setQuizQuestionIds(List<Integer> quizQuestionIds) {
        this.quizQuestionIds = quizQuestionIds;
    }
    
    public void nextQuestion(int QID) throws IOException
    {
        a.setQuestionId(QID);
        a.setAnswer(Answervalue);
        a.setQtype(qtype);
        myAnswers.add(a);  
        if(currentQuestion<getFinish_size())
        {
            currentQuestion = quizQuestionIds.get(currentQuestionCounter);
            currentQuestionCounter++;
            help.navigate("student_course_quiz_view_attend.xhtml?ID=" + currentQuestion);
        }        
    }
    
}
