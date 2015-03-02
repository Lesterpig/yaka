import java.util.Stack;

public class Expression {
    
    private static Stack<TypeList> types;
    private static Stack<Character> operateurs;

    private static String errorLog;

    public static void initPiles() {
        types = new Stack<TypeList>();
        operateurs = new Stack<Character>();
    }

}