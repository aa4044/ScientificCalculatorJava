package infix.to.postfix;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class InfixToPostfix {

  
    public static void main(String[] args) {
    
    Stack stack = new Stack();
    Stack stackForBrackets = new Stack();
        
    String postfixStr = "";
    ArrayList<String> postfix = new ArrayList<>();
    Scanner scan = new Scanner(System.in);
    
        String strInput = new String();
        System.out.print("Enter Postfix Expression: ");
        strInput = scan.next();
        System.out.println();
        
        String str1 = strInput.replaceAll("[()]","");
        String[] no = str1.split("[+^*/-]");
        String optStr = strInput.replaceAll("[0123456789.]","#");
        String[] opt = optStr.split("");
        
        
        String concatOpt = "";
        for(String x : opt){
            concatOpt = concatOpt + x;
        }
      
    
        String hash = "#";
        String[] hundredHashs = new String[100];
        for(int i=0; i<100; i++){
            hundredHashs[i] = hash;
            for(int j=0; j<i; j++){
                hundredHashs[i] = hundredHashs[i] + hash;
            }
        }

        for(int i=99; i>=0; i--){
             if(concatOpt.contains(hundredHashs[i])){
                concatOpt = concatOpt.replaceAll(hundredHashs[i], "#");
            }
        }
        
      
        String[] infix = concatOpt.split("");
        int j=0;
        
        for(int i=0; i<infix.length; i++){
            
            if(infix[i].equals("#")){
                infix[i] = no[j++];
            }
        
        }
        
        
        System.out.print("\nInfix: ");
        for(String x : infix){
            System.out.print(x + ".");
        }
        System.out.println();
        
    for(String x : infix){
      
        
        if(returnPreference(x) == 4){   
       
           while(!stackForBrackets.peek().equals("("))
            {
                
                String str = (String) stackForBrackets.pop();
                if(!str.equals("("))
                postfix.add(str);
            }
           stackForBrackets.removeAllElements();
         
           continue; // continue will take the control to the beginning of for loop so that closing bracket will not be adding to postfix expression
         
        }
        
        if(returnPreference(x) == 0){
            postfix.add(x);
        }

        else if(returnPreference(x) == 5){
            stackForBrackets.add(x);
        }
        
        else if(stackForBrackets.contains("(")){
        
        if(stackForBrackets.peek().equals("(")){
        stackForBrackets.add(x);
        }    
            
        else if(returnPreference(x) > returnPreference((String) stackForBrackets.peek())){
            stackForBrackets.add(x);
        }
        
        else if((returnPreference(x) == returnPreference((String) stackForBrackets.peek()))||(returnPreference(x) < returnPreference((String) stackForBrackets.peek()))){
        
            
            while(stackForBrackets.peek().equals("(") && ((returnPreference(x) == returnPreference((String) stackForBrackets.peek()))||(returnPreference(x) < returnPreference((String) stackForBrackets.peek())))){

                postfix.add((String) stackForBrackets.pop());
          
            if(stackForBrackets.isEmpty())
                break;
            }
            
            stackForBrackets.add(x);
            
        }
        
        }
        
        
        else if(stack.isEmpty()){
        
            if(!x.equals("("))
            stack.add(x);
        }
        
        
        else if(returnPreference(x) > returnPreference((String) stack.peek())){
            stack.add(x);
        }
        
        else if((returnPreference(x) == returnPreference((String) stack.peek()))||(returnPreference(x) < returnPreference((String) stack.peek()))){
        
         
            while((returnPreference(x) == returnPreference((String) stack.peek()))||(returnPreference(x) < returnPreference((String) stack.peek()))){
            postfix.add((String) stack.pop());
          
            if(stack.isEmpty())
                break;
            }
            
            stack.add(x);
            
        }
     /*   System.out.println("x " + x);
        //System.out.println("infix " + infix);
        System.out.println("postfix " + postfix);
        System.out.println("stack " + stack);
        System.out.println("stackforbracket " + stackForBrackets);
       
    */
    }
     
        for(int i=0; i<=stack.size(); i++){
        
        if(!stack.isEmpty())
        postfix.add((String) stack.pop());
        else
        break;
        }
     
       
    for(String x : postfix){
        
        postfixStr= postfixStr.concat(x);
    }
    
        System.out.println("Postfix Expression: " + postfixStr);

    
    PostfixCalculation postfixCalculationObj = new PostfixCalculation(postfix);
    postfixCalculationObj.calculatePostfix();
        System.out.println("The Answer is: " + postfixCalculationObj.getAnswer());
    }
    
    
    public static int returnPreference(String str){
    
        if(str.equals("("))
            return 5;
        else if(str.equals(")"))
            return 4;
        else if(str.equals("^"))
            return 3;
        else if(str.equals("*") || str.equals("/") || str.equals("%"))
            return 2;
        else if(str.equals("+") || str.equals("-"))
            return 1;
        else 
            return 0;
    }
    
}
