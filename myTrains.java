package javaprojecttheory;
import java.util.Scanner;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

//------------------------------------------------------------------------//
class Passenger {
    String passegr_id;
    String passengr_name;
    String passenger_Gender;
    String passenger_destination;
    String passenger_address;
    int passenger_phone_no;
    byte passeger_age;
    JFrame fram = new JFrame();
    Scanner input = new Scanner(System.in);
    BufferedWriter bf;

    void passeger_information() throws IOException {
        int min = 1;
        int max = 50;
        int j=0;
        j++;
      

        int random_int = (int) Math.floor(Math.random() * (max - min + 1) + min);

        // System.out.println("Seat no :" + random_int);
        JOptionPane.showMessageDialog(fram, "The  Seat number of  ["+j+"] Passenger  is : "+random_int, "MESSAGE  ", JOptionPane.INFORMATION_MESSAGE);
        // /==================================================
        System.out.println("Enter Passenger  ID number ");
        passegr_id = input.nextLine();
        passegr_id = input.nextLine();
        System.out.println("Enter Passenger   Name ");
        passengr_name = input.nextLine();
        System.out.println("Enter Passenger  Gender ");
        passenger_Gender = input.nextLine();
        System.out.println("Enter Address of passenger  ");
        passenger_address = input.nextLine();
        System.out.println("Enter passenger  Contact  Number  ");
        passenger_phone_no = input.nextInt();
        System.out.println("Enter passenger  Age ");
        passeger_age = input.nextByte();

        try {
            bf = new BufferedWriter(
                    new FileWriter("E:\\javaproject\\javaprojecttheory\\Train_Passenger_Information.txt", true));
    
            bf.write("___________________________________________\n");
            bf.write("passenger ID : " + passegr_id + "\n");
            bf.write("passenger name : " + passengr_name + "\n");
            bf.write("passenger phone : " + passenger_phone_no + "\n");
            bf.write("passenger age : " + passeger_age + "\n");
            bf.write("passenger Gender : " + passenger_Gender + "\n");
            bf.write("Passenger address " + passenger_address + "\n");
            bf.write("Passenger destination " + passenger_destination + "\n");
            bf.write("Seat Number is " + random_int + "\n");
            bf.write("___________________________________________\n");
            bf.newLine();
            bf.close();

        } catch (Exception e) {
            System.out.println("Exception occurs at passenger method file at write file ");
            e.printStackTrace();
        }
    }

}

// ------------------------------------------------------------------------//
class tickett {

    Passenger p;
    JFrame fram = new JFrame();

    tickett(Passenger p) {
        this.p = p;
    }

    synchronized void getticket(int ticket) {
        int total_tickets = 50;

        total_tickets = total_tickets - ticket;
        if (total_tickets > ticket) {

            System.out.println("---------------------------");
            System.out.println("you can book " + ticket + " ticket ");

            try {

                System.out.println("please Enter deatials of  passenger : ");

                for (int i = 0; i < ticket; i++) {
                    System.out.println("\n Enter the details of [" + (i + 1) + "] passenger \n ");

                    p.passeger_information();

                }
                JOptionPane.showMessageDialog(fram, " Data saved ", " MESSAGE ", JOptionPane.CLOSED_OPTION);

            } catch (IOException e) {
                System.out.println("Ecxeption at the ticket class method ");
                e.printStackTrace();

            }

        } else if (total_tickets < ticket) {
            System.out.print("SORRY! we have total tickets " + 50);

        }

    }
}
// ------------------------------------------------------------------------//

public class myTrains extends Thread {
    int seats;
    static tickett t;
    Scanner input = new Scanner(System.in);

    public void run() {
        System.out.println("Enter how many seats you want ");
        seats = input.nextInt();
        t.getticket(seats);

    }

    public static void main(String[] args) {

        JFrame frame = new JFrame();
        int air_option = 0;
        char ch = 'l';

        do {

            System.out.println("-----------------------------------------");
            System.out.println("  WELCOME TO KARACHI TRAIN STATION  :    ");
            System.out.println("-----------------------------------------");
            Scanner sc = new Scanner(System.in);
            System.out.println(" 1 -  Information of trains ");
            System.out.println(" 2 -  Book the  tickets for passenger  ");
            System.out.println(" 3 -  Search detials  any of passenger  ");
            System.out.println(" 4 -  Cancle ticket of any passenger  ");
            System.out.println(" 5 -  Search detials of all  passengers   \n");
            System.out.println(" *******************************************");
            System.out.print(" Please  Enter choice here  ");
            int option = sc.nextInt();

            int choice;
            switch (option) {

                case 1:
                    try {

                        File f = new File("E:\\javaproject\\javaprojecttheory\\trainsInformation.txt");
                        BufferedReader bf = new BufferedReader(new FileReader(f));
                        String s;
                        while ((s = bf.readLine()) != null) {
                            System.out.println(s + "   ");
                        }

                        bf.close();

                    }

                    catch (Exception e) {
                        System.out.println(" Train information file is not found ");
                        e.printStackTrace();
                    }

                    do {

                        System.out.println("Enter y to go back ");
                        ch = sc.next().charAt(0);
                        // use for console clear
                        ScreenClear.clrscr();
                        if (ch != 'y') {
                            JOptionPane.showMessageDialog(frame, "Please! Enter valid character", "WARRING",
                                    JOptionPane.WARNING_MESSAGE);
                        }
                    } while (ch != 'y');

                    break;
                // ==--------------------------------------------------------------------------==
                case 2:
                    do {
                        System.out.println(" where would you want to go : ");
                        System.out.println(" 1 - Quetta ");
                        System.out.println(" 2 - Rawalpendi ");
                        System.out.println(" 3 - islamabad ");
                        System.out.print("PLEASE! Enter values of your choice :   ");
                        choice = sc.nextInt();

                        if (choice > 3 || choice < 1) {
                            JOptionPane.showMessageDialog(frame, "You enter wrong digit ", " WARNING ",
                                    JOptionPane.WARNING_MESSAGE);
                        }
                    } while (choice > 3 || choice < 1);

                    if (choice == 1) {
      
                        Passenger p1 = new Passenger();
                        t = new tickett(p1);
                        myTrains a = new myTrains();
                        p1.passenger_destination="Quetta";

                        a.start();
                        try {
                            a.join();
                        } catch (InterruptedException e1) {
                            System.out.println("Exception at jion method in case 2 ");
                            e1.printStackTrace();
                        }

                    }
                    
                    else if(choice==2){

                        Passenger p2 = new Passenger();
                        t = new tickett(p2);
                        myTrains a = new myTrains();
                        p2.passenger_destination="Rawalpendi";
                        a.start();
                        try {
                            a.join();
                        } catch (InterruptedException e1) {
                            System.out.println("Exception at jion method in case 2");
                            e1.printStackTrace();
                        }



                    }
                    else if(choice==3){

                        Passenger p3 = new Passenger();
                        t = new tickett(p3);
                        myTrains a = new myTrains();
                        p3.passenger_destination="Islamabad";
                        a.start();
                        try {
                            a.join();
                        } catch (InterruptedException e1) {
                            System.out.println("Exception at jion method in case 2");
                            e1.printStackTrace();
                        }



                    }
                    
                 

                    do {
                        System.out.println();
                        System.out.println("------------->Enter y to go back-----------> ");
                        ch = sc.next().charAt(0);
                        // clearScreen
                        ScreenClear.clrscr();
                        if (ch != 'y') {
                            JOptionPane.showMessageDialog(frame, "PLEASE! Enter valid character ");
                        }
                    } while (ch != 'y');

                    break;
                // ==-------------------------------------------------------------===========
                case 3:
                    try {
                        System.out.println("  Enter passenger  id for search ");
                        String str = sc.next();

                        BufferedReader bfr = new BufferedReader(
                                new FileReader("E:\\javaproject\\javaprojecttheory\\Train_Passenger_Information.txt"));
                        String s;
                        System.out.print("\n              LOADING  ");
                        for (int i = 0; i < 3; i++) {
                            try {
                                Thread.sleep(800);
                                System.out.print(".");
                                Thread.yield();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        ScreenClear.clrscr();
                        System.out.println();
                        while ((s = bfr.readLine()) != null) {

                            if (s.contains(str)) {
                                while ((s = bfr.readLine()) != null) {

                                    System.out.println(s);

                                    if (s.charAt(0) == ' ') {
                                        break;
                                    }

                                }

                            }

                        }

                        bfr.close();

                    } catch (Exception e) {
                        e.getMessage();
                    }

                    do {

                        System.out.println("  Enter y to go back ");
                        ch = sc.next().charAt(0);
                        // clear screen
                        ScreenClear.clrscr();
                        if (ch != 'y') {
                            JOptionPane.showMessageDialog(frame, "PLEASE! Enter valid character ", " WARRNING ",
                                    JOptionPane.WARNING_MESSAGE);
                        }
                    } while (ch != 'y');

                    break;
                case 4:
                    Passenger p = new Passenger();
                    try {
                        System.out.println("Enter passenger  ID to remove ");
                        String str = sc.next();
                        BufferedReader bfr = new BufferedReader(
                                new FileReader("E:\\javaproject\\javaprojecttheory\\Train_Passenger_Information.txt"));

                        String s;
                        System.out.print("\n              LOADING  ");
                        for (int i = 0; i < 3; i++) {
                            try {
                                Thread.sleep(800);
                                System.out.print(".");
                                Thread.yield();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        ScreenClear.clrscr();
                        System.out.println();
                        
                        while ((s = bfr.readLine()) != null) {
                            if (s.contains(str)) {
                                JOptionPane.showMessageDialog(frame, "Passenger ticket cancled ");
                                while ((s = bfr.readLine()) != null) {
                                    
                                    if (s.charAt(0) == ' ') {

                                        break;
                                    }

                                }

                            }
                        }
                        bfr.close();
                        
                    } 
                    
                    catch (Exception e) {
                        e.getMessage();
                    }
                    do {

                        System.out.println("  Enter y to go back ");
                        ch = sc.next().charAt(0);
                        if (ch != 'y') {
                            JOptionPane.showMessageDialog(frame, "PLEASE! Enter valid character ", " WARRNING ",
                                    JOptionPane.WARNING_MESSAGE);

                        }
                    } while (ch != 'y');
                    break;
                case 5: {
                    try {
                        BufferedReader bfr = new BufferedReader(
                                new FileReader("E:\\javaproject\\javaprojecttheory\\Train_Passenger_Information.txt"));

                        String s;
                        System.out.print("\n      Loading ");

                        for (int i = 0; i <= 3; i++) {
                            try {
                                Thread.sleep(1000);
                                System.out.print(". ");
                                Thread.yield();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        ScreenClear.clrscr();

                        while ((s = bfr.readLine()) != null) {
                            System.out.println(s);

                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    do {

                        System.out.println("  Enter y to go back ");
                        ch = sc.next().charAt(0);
                        ScreenClear.clrscr();
                        if (ch != 'y') {
                            JOptionPane.showMessageDialog(frame, "PLEASE! Enter valid character ");
                        }
                    } while (ch != 'y');
                    break;
                }

                default:
                    JOptionPane.showMessageDialog(frame, " you have Entered the  wrong number   ", "WARNING",
                            JOptionPane.WARNING_MESSAGE);
                    System.out.println("Enter valid number ");
                    air_option = 4;

            }

        } while (air_option == 4 || ch == 'y');

    }
}

class ScreenClear {
    public static void clrscr() {

        try {

            if (System.getProperty("os.name").contains("Windows"))

                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();

            else

                Runtime.getRuntime().exec("clear");

        } catch (IOException | InterruptedException ex) {
        }

    }
}