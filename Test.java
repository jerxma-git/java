import search.*;

import java.util.Scanner;

import queue.*;
public class Test {
    public static void main(String[] args) {
        Queue q = new LinkedQueue();
        Scanner scan = new Scanner(System.in);
        String command;
        while(!(command = scan.next()).isEmpty()) {
            switch (command) {
            case "e":
                String next = scan.next();
                q.enqueue(next);
                break;
            case "d":
                q.dequeue();
                break;
            case "c":
                q.clear();
                break;
            }
            System.out.println(q.toStr());
        }
        scan.close();
    }
}