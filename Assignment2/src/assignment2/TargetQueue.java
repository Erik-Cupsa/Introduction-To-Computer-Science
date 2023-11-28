package assignment2;

public class TargetQueue extends MyQueue<Position>{
    public static void main(String[] args) {
        TargetQueue test = new TargetQueue();
        test.addTargets("(0400,05)");
        System.out.println(test.dequeue().getX());
    }
    private MyStack<String> stack;

    public TargetQueue(){
        super();
        this.stack = new MyStack<String>();
    }

    public void clear(){
        super.clear();
        this.stack.clear();
    }

    public void addTargets(String input){
      String num = "";
      boolean foundComma = false;
      boolean foundLeftBracket = false;
      boolean foundPeriod = false;

      for(int i=0; i< input.length(); i++){
          char c = input.charAt(i);
          char next = i+1 < input.length() ? input.charAt(i+1) : 0;
          if(c == '('){
              if(this.stack.isEmpty() && num.equals("") && !foundLeftBracket && input.contains(")")){
                  this.stack.push("(");
                  foundLeftBracket = true;
              }
              else{
                  throw new IllegalArgumentException("Invalid input syntax");
              }
          }
          else if((int)c >= 48 && (int)c <= 57){
              if((int)next >= 48 && (int)next <= 57){
                  num = "" + c + next;
                  next = i+2 < input.length() ? input.charAt(i+2) : 0;
                  if(Character.isDigit(next)){
                      num = num + next;
                      next = i+3 < input.length() ? input.charAt(i+3) : 0;
                      if(Character.isDigit(next)){
                          num = num+next;
                          i+=3;
                      }
                      else{
                          i+=2;
                      }
                  }
                  else{
                      i++;
                  }
              }
              else if(num.equals("")){
                  num = "" + c;
              }
              else if(next == 0){
                  throw new IllegalArgumentException("Invalid input syntax");
              }
          }
          else if (c == ','){
              if(num.equals("") || foundComma || !foundLeftBracket){
                  throw new IllegalArgumentException("Invalid input syntax");
              }
              else if(!((int)next >= 48 && (int)next <= 57)){
                  throw new IllegalArgumentException("Invalid input syntax");
              }
              else{
                  this.stack.push(num);
                  this.stack.push(",");
                  num = "";
                  foundComma = true;
              }
          }
          else if (c == ')'){
              if(this.stack.getSize() != 3) {
                  throw new IllegalArgumentException("Invalid input syntax");
              }
              if(!num.equals("") && this.stack.pop().equals(",")){
                  String num1 = this.stack.pop();
                  int x = Integer.parseInt(num1);
                  int y = Integer.parseInt(num);
                  if(this.stack.pop().equals("(")){
                      super.enqueue(new Position(x, y));
                      num = "";
                  }
                  else{
                      throw new IllegalArgumentException("Invalid input syntax");
                  }
              }
              else{
                  throw new IllegalArgumentException("Invalid input syntax");
              }
          }
          else if (c == '.'){
              if(this.stack.getSize() != 0 && !num.equals("")){
                  throw new IllegalArgumentException("Invalid input syntax");
              }
              else if(next == '.'){
                  throw new IllegalArgumentException("Invalid input syntax");
              }
              else{
                  foundComma = false;
                  foundLeftBracket = false;
              }
          }
          else{
              throw new IllegalArgumentException("Invalid input syntax");
          }
      }
    }
}
