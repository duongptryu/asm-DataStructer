package app;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main extends Thread {
    private myQueue myQ = new myQueue();
    private myStack myS = new myStack(100);

    public static void main(String[] args) {

        Main test = new Main();

        test.menu();

    }

    private void transfer() {
        boolean flag = false;
        do {
            flag = false;
            // Clear screen
            clear();
            System.out.println("==========CREATE THE MESSAGE==========");
            System.out.println("INPUT THE TITLE OF THE MESSAGE - MAX 32 CHARACTER");
            String title = checkStringLength(32);
            System.out.println("===================================");
            System.out.println("");
            System.out.println("INPUT THE RECEIVER - MAX 32 CHARACTER");
            String receiver = checkStringLength(32).toLowerCase();
            System.out.println("===================================");
            System.out.println("INPUT THE CONTENT OF THE MESSAGE - MAX 250 CHARACTER");
            String content = checkStringLength(250);
            System.out.println("===================================");
            System.out.println("");
            System.out.println("==============CREATE MESSAGE SUCCESSFULL!==============");
            System.out.println("");
            System.out.format("+-----------------+-------------+%n");
            System.out.format("|        Transfer message       |%n");
            System.out.format("+-----------------+-------------+%n");
            System.out.format("|        Function      | Choice |%n");
            System.out.format("+----------------------+--------+%n");
            System.out.format("|  Send message        |   1    |%n");
            System.out.format("+----------------------+--------+%n");
            System.out.format("|  Reset message       |   2    |%n");
            System.out.format("+----------------------+--------+%n");
            System.out.format("|  Come back menu      |   0    |%n");
            System.out.format("+----------------------+--------+%n");

            System.out.println("Please choice");
            int choice = checkInput(0, 2);

            if (choice == 1) {
                String time = getDate();
                myQ.enqueue(title, content, time, receiver);
                clear();
                System.out.println("Transfering, wait a minutes!");

                // Effects for consoles
                // for (int i = 0; i < 50; i++) {
                // System.out.print("#");
                // try {
                // Thread.sleep(50);
                // } catch (InterruptedException e) {

                // e.printStackTrace();
                // }
                // }
                clear();
                System.out.println("==========================================================");
                System.out.println("|                Transfer successfull                     |");
                System.out.println("==========================================================");
                System.out.format("+----------------------+--------+%n");
                System.out.format("|  New message         |   1    |%n");
                System.out.format("+----------------------+--------+%n");
                System.out.format("|  Come back menu      |   0    |%n");
                System.out.format("+----------------------+--------+%n");
                int x = checkInput(0, 1);
                if (x == 1) {
                    transfer();
                } else {
                    break;
                }

            } else if (choice == 2) {
                flag = true;
            } else {
                break;
            }
        } while (flag == true);
    }

    private void menu() {
        boolean flag = false;
        do {

            if (flag == true) {
                clear();
            }
            flag = false;
            System.out.println("+-----------------+-------------+");
            System.out.println("|        =======Menu=======     |");
            System.out.println("+-----------------+-------------+");
            System.out.println("|        Function      | Choice |");
            System.out.println("+----------------------+--------+");
            System.out.println("|  Transfer messages   |   1    |");
            System.out.println("+----------------------+--------+");
            System.out.println("|  Process messages    |   2    |");
            System.out.println("+----------------------+--------+");
            System.out.println("|  Display messages    |   3    |");
            System.out.println("+----------------------+--------+");
            System.out.println("|  Clear data          |   4    |");
            System.out.println("+----------------------+--------+");
            System.out.println("|  Exit                |   0    |");
            System.out.println("+----------------------+--------+");

            System.out.println("Please, input your choose below");
            int choose = checkInput(0, 4);
            switch (choose) {
                case 1:
                    clear();
                    transfer();
                    flag = true;
                    break;
                case 2:
                    clear();
                    process();
                    flag = true;
                    break;
                case 3:
                    clear();
                    display();
                    flag = true;
                    break;
                case 4:
                    clear();
                    remove();
                    flag = true;
                    break;
                default:
                    System.exit(0);

            }
        } while (flag == true);
    }

    private String getDate() {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-m-d hh:mm:ss");
        String strDate = dateFormat.format(date);
        return strDate;
    }

    private int checkInput(int x, int y) {
        int select = 100;
        boolean flag = false;
        do {
            flag = false;
            Scanner scan = new Scanner(System.in);
            try {
                select = scan.nextInt();
                if (select < x || select > y) {
                    System.out.println("Number input error, Please input again!");
                    flag = true;
                }
            } catch (Exception e) {
                flag = true;
                System.out.println("Your choice must be integer number! Please input again");
            }
        } while (flag == true);
        return select;
    }

    private void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void process() {
        clear();
        if (myQ.isEmpty()) {
            System.out.println("Has 0 message is stored, can't process. Please waite a minutes to come back menu ");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return;
        }
        while (true) {
            message tmp = null;
            try {
                tmp = myQ.dequeue();
            } catch (Exception e) {
                System.out.println("Please wait a seconds to process messages");
                // Decorate the console
                // for (int i = 0; i < 50; i++) {
                // System.out.print("#");
                // try {
                // Thread.sleep(50);
                // } catch (InterruptedException k) {
                // k.printStackTrace();
                // }
                // }
                clear();
                System.out.println("Process messages successfull!");
                try {
                    Thread.sleep(50);
                } catch (InterruptedException k) {
                    k.printStackTrace();
                }
                return;
            }
            myS.push(tmp);
        }
    }

    private void display() {
        clear();
        if (myS.getTopNum() == -1) {
            System.out.println("Has 0 message to display, come back menu in 10 seconds!");
            try {
                Thread.sleep(2500);
            } catch (InterruptedException k) {
                k.printStackTrace();
            }
            return;
        }

        try {

            String format = "|%-3s |%-32s |%-32s |%-90.90s |%-6s |%-19s |%n";
            System.out.format("+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
            System.out.format("|                                                                                       DISPLAY MESSAGES                                                                                          |%n");
            System.out.format("+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
            System.out.format(
                "+----+---------------------------------+---------------------------------+-------------------------------------------------------------------------------------------+-------+--------------------+%n");
            System.out.format(format, "ID", "Title", "Receiver", "Content", "Sender", "Time");
            System.out.format(
                "+----+---------------------------------+---------------------------------+-------------------------------------------------------------------------------------------+-------+--------------------+%n");
            for (int i = myS.getTopNum(); i >= 0; i--) {
                message tmp = myS.pop();
                System.out.format(format, i + 1, tmp.title, tmp.receiver, tmp.content, tmp.sender, tmp.time);
                System.out.format(
                    "+----+---------------------------------+---------------------------------+-------------------------------------------------------------------------------------------+-------+--------------------+%n");
            }
        } catch (Exception e) {

        }
        System.out.println(" ");
        System.out.println("============================");
        System.out.println("|Input 0 to come back menu |");
        System.out.println("============================");
        int i = checkInput(0, 0);
        if (i == 0) {
            return;
        }

    }

    private void remove() {
        myQ.clear();
        myS.clear();
    }

    private String checkStringLength(int x) {
        Scanner scan = new Scanner(System.in);
        boolean flag = false;
        String input = " ";
        do {
            flag = false;
            input = scan.nextLine();
            if (input.length() + 1 > x) {
                System.out.println("Larger than the specified number of characters, Please input again!");
                flag = true;
            }
            if (input.length() == 0) {
                flag = true;
                System.out.println("Can not be empty, Please input again!");
            }
        } while (flag == true);
        return input;
    }

}
