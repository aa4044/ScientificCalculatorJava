package infix.to.postfix;

import java.util.ArrayList;
import java.util.Stack;

public class PostfixCalculation {
    
    public Stack stack = new Stack();
    public double ro = 0.0;
    public double lo = 0.0;
    public double ans = 0.0;
    public ArrayList<String> postfix = null;
    
    public PostfixCalculation(ArrayList postfix){
        this.postfix = postfix;
    }
    
    public void calculatePostfix(){
        

        for(String x : postfix){
            

        if(InfixToPostfix.returnPreference(x) == 0){
            stack.add(Double.parseDouble(x));
        }
        
        else if(InfixToPostfix.returnPreference(x)!= 0 && !stack.isEmpty()){
            ro =  (double) stack.pop();
            lo =  (double) stack.pop();
            
            
            
            switch(x){
                case "+":
                    ans = lo + ro;
                break;
            
                case "-":
                    ans = lo - ro;
                break;
            
                case "*":
                    ans = lo * ro;
                break;
            
                case "/":
                    ans = lo / ro;
                break;
                    
                case "%":
                    ans = lo % ro;
                break;
                    
                case "^":
                    ans = (float) Math.pow(lo, ro);
                break;
                    
            }
            
            stack.add(ans);
                 
        }
    
        }
        

    
    
    }

    public double getAnswer(){
        return ans;
    }
          
    
        
        
}
