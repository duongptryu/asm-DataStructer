package app;

public class message{
    String title;
    String content;
    String time;
    String sender;
    String receiver;
    message next;

    //constructor
    public message(String title, String content, String time, String receiver){
        this.title = title;
        this.content = content;
        this.time = time;
        this.sender = "Rÿu";
        this.receiver = receiver;
        this.next = null;
    }
}