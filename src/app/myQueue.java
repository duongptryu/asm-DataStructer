package app;

public class myQueue{
    private message head;
    private message last;


    public myQueue(){
        head = last = null;
    }

    public void enqueue(String title, String content, String time, String receiver){
        message n = new message(title, content, time, receiver);

        if(isEmpty()){
            head = last = n;
            return;
        }
        last.next = n;
        last = n;
    }

    public message dequeue() throws Exception{
        message tmp = head;
        if(isEmpty()){
            throw new Exception("Error, Linked list is empty");
        }else if (head == last){
            head = last = null;
            return tmp;
        }
        head = head.next;
        return tmp;
    }

    public boolean isEmpty(){
        if(head == null ){
            return true;
        }
        return false;

    }

    

    public void clear(){
        head = last = null;
    }

    








}