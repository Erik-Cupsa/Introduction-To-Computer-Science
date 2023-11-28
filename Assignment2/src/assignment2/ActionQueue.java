package assignment2;

public class ActionQueue extends MyQueue<Direction>{
    private MyStack<String> stack;
    private String directions;
    private String numbers;

    public ActionQueue(){
        super();
        this.stack = new MyStack<String>();
        this.directions = "";
        this.numbers = "";
    }

    public void clear(){
        super.clear();
        this.stack.clear();
        this.directions = "";
        this.numbers = "";
    }

    public void loadFromEncodedString(String input){
        clear();
        boolean foundDigit = false;
        boolean foundLeftBracket = false;
        boolean foundDirection = false;
        Direction sentdirection = null;
        int numLeftBracket = 0;
        String previousDirections = "";
        for(int i=0; i < input.length() ; i++){
            char c = input.charAt(i);
            char next = i+1 < input.length() ? input.charAt(i+1) : 0;
            char doubleNext = i+2 < input.length() ? input.charAt(i+2) : 0;
            if((int)c >= 48 && (int)c <= 57){
                foundDigit = true;
                if((int)next >= 48 && (int)next <= 57){
                    this.numbers = "" + c + next;
                    this.stack.push(this.numbers);
                    this.numbers = "";
                    i++;
                }
                else {
                    this.numbers = "" + c;
                    this.stack.push(this.numbers);
                    this.numbers = "";
                }
            }
            else if(c == '[' && foundDigit){
                foundLeftBracket = true;
                numLeftBracket ++;
                this.stack.push("[");
            }
            else if((int)c >= 65 && (int)c <= 90){
                if((int)next >= 65 && (int)next <= 90 && !foundLeftBracket && doubleNext == 0){
                    if(next == 'N'){
                        foundDirection = true;
                        this.stack.push("N");
                    }
                    else if(next=='S'){
                        foundDirection = true;
                        this.stack.push("S");
                    }
                    else if(next == 'E'){
                        foundDirection = true;
                        this.stack.push("E");
                    }
                    else if(next=='W'){
                        foundDirection = true;
                        this.stack.push("W");
                    }
                    else{
                        throw new IllegalArgumentException("Invalid input syntax");
                    }
                    if(c == 'N'){
                        foundDirection = true;
                        this.stack.push("N");
                    }
                    else if(c=='S'){
                        foundDirection = true;
                        this.stack.push("S");
                    }
                    else if(c == 'E'){
                        foundDirection = true;
                        this.stack.push("E");
                    }
                    else if(c=='W'){
                        foundDirection = true;
                        this.stack.push("W");
                    }
                    else{
                        throw new IllegalArgumentException("Invalid input syntax");
                    }
                    i++;
                }
                else{
                    if(c == 'N'){
                        foundDirection = true;
                        this.stack.push("N");
                    }
                    else if(c=='S'){
                        foundDirection = true;
                        this.stack.push("S");
                    }
                    else if(c == 'E'){
                        foundDirection = true;
                        this.stack.push("E");
                    }
                    else if(c=='W'){
                        foundDirection = true;
                        this.stack.push("W");
                    }
                    else{
                        throw new IllegalArgumentException("Invalid input syntax");
                    }
                }
            }
            else if(c == ']' && foundLeftBracket && foundDirection){
                String test = this.stack.pop();
                while(!test.equals("[")){
                    this.directions = test + this.directions;
                    test = this.stack.pop();
                }
                numLeftBracket --;

                int x = Integer.parseInt(this.stack.pop());
                String actual = "";
                if(!this.stack.isEmpty()){
                    char remaining = this.stack.peek().charAt(0);
                    while(((int)remaining >= 65 && (int)remaining <= 90)){
                        actual = this.stack.pop() + actual ;
                        if(!this.stack.isEmpty()){
                            remaining = this.stack.peek().charAt(0);
                        }
                        else{
                            break;
                        }
                    }
                }
                previousDirections = actual;
                for(int z = 0; z<x; z++){
                    previousDirections = previousDirections + this.directions;
                }
                if(next == ']' && numLeftBracket != 0 && !this.stack.isEmpty()){
                  String test2 = this.stack.pop();
                   while(!test2.equals("[")){
                       previousDirections = test2 + previousDirections;
                   }
                    int y = 0;
                    if(!this.stack.isEmpty()) y = Integer.parseInt(this.stack.pop());
                    for(int f = 0; f<y; f++){
                        for(int e=0; e<previousDirections.length(); e++){
                            char a = previousDirections.charAt(e);
                            if(a == 'N'){
                                sentdirection = Direction.NORTH;
                            }
                            else if(a == 'S'){
                                sentdirection = Direction.SOUTH;
                            }
                            else if(a == 'E'){
                                sentdirection = Direction.EAST;
                            }
                            else if(a == 'W'){
                                sentdirection = Direction.WEST;
                            }
                            else{
                                throw new IllegalArgumentException("Invalid input syntax");
                            }
                            super.enqueue(sentdirection);
                        }
                    }
                    foundLeftBracket = false;
                    foundDirection = false;
                    this.directions = "";
                    this.numbers = "";
                    i++;
                }
                else {
                    for (int d = 0; d < previousDirections.length(); d++) {
                        char toSend = previousDirections.charAt(d);
                        if (toSend == 'N') {
                            sentdirection = Direction.NORTH;
                        } else if (toSend == 'S') {
                            sentdirection = Direction.SOUTH;
                        } else if (toSend == 'E') {
                            sentdirection = Direction.EAST;
                        } else if (toSend == 'W') {
                            sentdirection = Direction.WEST;
                        } else {
                            throw new IllegalArgumentException("Invalid input syntax");
                        }
                        super.enqueue(sentdirection);
                    }
                    foundLeftBracket = false;
                    foundDirection = false;
                    this.directions = "";
                    this.numbers = "";
                }
            }
            else{
                throw new IllegalArgumentException("Invalid input syntax");
            }
        }
        while(!this.stack.isEmpty()){
            char remaining = this.stack.pop().charAt(0);
            if (remaining == 'N') {
                sentdirection = Direction.NORTH;
            } else if (remaining == 'S') {
                sentdirection = Direction.SOUTH;
            } else if (remaining == 'E') {
                sentdirection = Direction.EAST;
            } else if (remaining == 'W') {
                sentdirection = Direction.WEST;
            } else {
                throw new IllegalArgumentException("Invalid input syntax");
            }
            super.enqueue(sentdirection);
        }
    }
}
