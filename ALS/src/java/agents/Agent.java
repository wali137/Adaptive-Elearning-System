package agents;

public enum Agent {
    
    
   
    ANALYZER(1), 
    DBWRAPPER(2), 
    ONTOLOGY(3), 
    STUDENTTEACHER(4), 
    KILL(5),
    SEND(6),
    SQLINSERT(7),
    SQLDELETE(8);
 
    private int code;
 
    private Agent(int c) 
    {
        code = c;
    }
 
    public int getCode()
    {
        return code;
    }
 
}
