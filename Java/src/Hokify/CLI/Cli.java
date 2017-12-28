package Hokify.CLI;

import Hokify.*;
import Hokify.quotes.PartTimeQuote;
import org.overture.codegen.runtime.VDMSet;

import java.util.Scanner;

import static Hokify.HokifyTest.hokify;

public class Cli {

    public Cli(){}

    public static void main(String[] args){

        Hokify hokify = new Hokify();
        Cli cli = new Cli();
        cli.populate();
        cli.mainMenu();
    }

    public void populate(){

        Employee e1 = new Employee("Rita");
        e1.addInterest("Circus");
        e1.addLocation("World");
        e1.addSkill("Acrobatic");

        Employer r1 = new Employer("Sara");
        VDMSet set1 = new VDMSet();
        set1.add("Nursing");
        set1.add("Cooking");
        VDMSet set2 = new VDMSet();
        set2.add("Teaching");
        set2.add("Daycare");
        Job j1 = new Job("Babysitter",set1,set2,"London", PartTimeQuote.getInstance(),"Much Happy");
        r1.addJob(j1);

        hokify.createUser(e1);
        hokify.createUser(r1);
        hokify.addJob(r1,j1);

    }

    public void mainMenu(){

        System.out.println("WELCOME TO HOKIFY\n");
        System.out.println("1.Users Menu");
        System.out.println("2.Jobs Menu");

        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();

        if(i == 1){
            userMenu();
        }
        else if(i == 2){
            jobMenu();
        }
        else{
            System.out.println("Please select one of the numbers above referenced\n");
        }
    }

    public void userMenu(){

        boolean isEmployer = false, isEmployee = false;
        String user = askUser();

        isEmployer = hokify.getUserByName(user).getClass().equals(Employer.class);
        isEmployee = hokify.getUserByName(user).getClass().equals(Employee.class);

        if(isEmployee || isEmployer) {
            System.out.println("USER\n");
            System.out.println("1.Create User");
            System.out.println("2.List Users");
            System.out.println("3.Search");
            System.out.println("4.Edit User Information");
            System.out.println("5.Delete User");
        }
        if (isEmployee) {
            System.out.println("6.List Employee Job Applications");
        }
        System.out.println("0.Go Back");

        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();

        switch (i) {
            case 0:
                mainMenu();
                break;
            default:
                break;
        }
    }


    public void jobMenu(){

        String user = askUser();
        boolean isEmployer = false, isEmployee = false;

        isEmployer = hokify.getUserByName(user).getClass().equals(Employer.class);
        isEmployee = hokify.getUserByName(user).getClass().equals(Employee.class);

        if(isEmployee || isEmployer) {
            System.out.println("JOB\n");
            System.out.println("1.List Jobs");
            System.out.println("2.Search");
            System.out.println("3.Apply For a Job");
        }
        if(isEmployer) {
            System.out.println("4.Create Job");
            System.out.println("5.Edit Job Information");
            System.out.println("6.Delete Job");
        }
        System.out.println("0.Go Back");

        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();

        switch (i) {
            case 0:
                mainMenu();
                break;
            default:
                break;

        }

    }

    public String askUser(){

        System.out.println("User?\n");

        Scanner scanner = new Scanner(System.in);
        String user = scanner.nextLine();

        return user;
    }

     public boolean isEmployer(){

        System.out.println("User?\n");

        Scanner scanner = new Scanner(System.in);
        String user = scanner.nextLine();

        return hokify.getUserByName(user).getClass().equals(Employer.class);
    }

    public boolean isEmployee(){

        System.out.println("User?\n");

        Scanner scanner = new Scanner(System.in);
        String user = scanner.nextLine();

        return hokify.getUserByName(user).getClass().equals(Employee.class);
    }
}
