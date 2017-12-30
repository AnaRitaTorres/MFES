package Hokify.CLI;

import Hokify.*;
import Hokify.quotes.FullTimeQuote;
import Hokify.quotes.PartTimeQuote;
import com.sun.org.apache.xpath.internal.SourceTree;
import org.overture.codegen.runtime.VDMSet;

import java.util.Iterator;
import java.util.Scanner;

import static Hokify.HokifyTest.hokify;

public class Cli {

    public Cli() {
    }

    public static void main(String[] args) {

        Hokify hokify = new Hokify();
        Cli cli = new Cli();
        cli.populate();
        cli.mainMenu();
    }

    public void populate() {

        Employee e1 = new Employee("Rita");
        e1.addInterest("Circus");
        e1.addLocation("World");
        e1.addSkill("Acrobatic");

        Employee e2 = new Employee("Sofia");
        e2.addInterest("Teaching");
        e2.addLocation("World");
        e2.addSkill("Nursing");

        Employer r1 = new Employer("Sara");
        VDMSet set1 = new VDMSet();
        set1.add("Nursing");
        set1.add("Cooking");
        VDMSet set2 = new VDMSet();
        set2.add("Teaching");
        set2.add("Daycare");
        Job j1 = new Job("Babysitter", set1, set2, "London", PartTimeQuote.getInstance(), "Much Happy");
        r1.addJob(j1);

        hokify.createUser(e1);
        hokify.createUser(e2);
        hokify.createUser(r1);
        hokify.addJob(r1, j1);
        hokify.apply(e1,j1);

    }

    public void mainMenu() {

        System.out.println("WELCOME TO HOKIFY\n");
        System.out.println("1.Users Menu");
        System.out.println("2.Jobs Menu");

        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();

        if (i == 1) {
            userMenu();
        } else if (i == 2) {
            jobMenu();
        } else {
            System.out.println("Please select one of the numbers above referenced\n");
        }
    }

    public void userMenu() {

        boolean isEmployer = false, isEmployee = false;
        String user = askUser();

        isEmployer = hokify.getUserByName(user).getClass().equals(Employer.class);
        isEmployee = hokify.getUserByName(user).getClass().equals(Employee.class);

        if (isEmployee || isEmployer) {
            System.out.println("\nUSER\n");
            System.out.println("1.Create User");
            System.out.println("2.List Users");
            System.out.println("3.Edit User Information");
            System.out.println("4.Delete User");
        }
        if (isEmployee) {
            System.out.println("5.List Employee Job Applications");
        }
        System.out.println("0.Go Back\n");

        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();

        switch (i) {
            case 0:
                mainMenu();
                break;
            case 1:
                createUserMenu();
                break;
            case 2:
                listUsers();
                break;
            case 3:
                editUserMenu(user);
                break;
            case 4:
                deleteUserMenu();
                break;
            case 5:
                listEmployeeJobApps(user);
                break;
            default:
                break;
        }
    }

    public void jobMenu() {

        String user = askUser();
        boolean isEmployer = false, isEmployee = false;

        isEmployer = hokify.getUserByName(user).getClass().equals(Employer.class);
        isEmployee = hokify.getUserByName(user).getClass().equals(Employee.class);

        if (isEmployee || isEmployer) {
            System.out.println("\nJOB\n");
            System.out.println("1.List Jobs");
            System.out.println("2.Search");
        }
        if(isEmployee){
            System.out.println("3.Apply For a Job");
        }
        if (isEmployer) {
            System.out.println("4.Create Job");
            System.out.println("5.Edit Job Information");
            System.out.println("6.Delete Job");
        }
        System.out.println("0.Go Back\n");

        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();

        switch (i) {
            case 0:
                mainMenu();
                break;
            case 1:
                listJobs();
                break;
            case 2:
                searchJobs(user);
                break;
            case 3:
                jobApp(user);
                break;
            case 4:
                createJobMenu(user);
                break;
            case 5:
                editJobMenu(user);
                break;
            case 6:
                deleteJobMenu(user);
                break;
            default:
                break;
        }

    }

    public String askUser() {

        System.out.println("\nUser:");

        Scanner scanner = new Scanner(System.in);
        String user = scanner.nextLine();

        while (hokify.getUserByName(user) == null) {
            System.out.println("The user inserted does not exist.");
            System.out.println("User:");
            user = scanner.nextLine();
        }

        return user;
    }

    public int askJobId(){

        Scanner scanner = new Scanner(System.in);

        System.out.println("Insert Job ID:");
        int i = scanner.nextInt();

        return i;
    }

    public void createUserMenu() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("\nCREATE USER:\n");

        System.out.println("Employer(1) or Employee(2):");
        int type = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Name:");
        String name = scanner.nextLine();

        if(type == 1){

            Employer e = new Employer(name);
            hokify.createUser(e);
        }
        else if(type == 2){

            System.out.println("Skills:");
            String skills = scanner.nextLine();
            String[] skill = skills.split(",");

            System.out.println("Location:");
            String location = scanner.nextLine();

            System.out.println("Interests:");
            String interests = scanner.nextLine();
            String[] interest = interests.split(",");

            System.out.println("CV:");
            String cv = scanner.nextLine();

            Employee e = new Employee(name);

            for(int i=0; i < interest.length; i++){
                e.addInterest(interest[i]);
            }

            for(int i=0; i < skill.length; i++){
                e.addSkill(skill[i]);
            }

            e.addLocation(location);
            e.createCV(cv);

            hokify.createUser(e);


        }
        else{
            System.out.println("Insert a valid type.");
        }

        System.out.println("User was created!");
         //TODO:For Testing Purposes
        System.out.println(hokify.users);

        returnMenu();
    }

    public void listUsers(){

        System.out.println("Employees:");

        Iterator it1 = hokify.getEmployees().iterator();
        while(it1.hasNext()){
            System.out.println(it1.next().toString());
        }

        System.out.println("Employers:");

        Iterator it2 = hokify.getEmployers().iterator();
        while(it2.hasNext()){
            System.out.println(it2.next().toString());
        }

        returnMenu();
    }

    public void editUserMenu(String user){

        Scanner scanner = new Scanner(System.in);
        boolean isEmployee = false, isEmployer = false;

        isEmployee = hokify.getUserByName(user).getClass().equals(Employee.class);
        isEmployer = hokify.getUserByName(user).getClass().equals(Employer.class);

        System.out.println("\nEDIT USER INFORMATION:\n");
        System.out.println("1.Update Name");
        if(isEmployee){

            System.out.println("2.Update Location");
            System.out.println("3.Add Interests");
            System.out.println("4.Add Skills");
            System.out.println("5.Update CV");
        }
        System.out.println("0.Go Back");

        Employee e = (Employee)hokify.getUserByName(user);

        int i = scanner.nextInt();
        scanner.nextLine();


        switch(i){
            case 0:
                userMenu();
                break;
            case 1:
                System.out.println("Change Name:");
                String name = scanner.nextLine();
                if(isEmployee){
                    ((Employee) hokify.getUserByName(user)).changeName(name);
                }
                if(isEmployer){
                    ((Employer) hokify.getUserByName(user)).changeName(name);
                }

                break;
            case 2:
                System.out.println("Change Location:");
                String location = scanner.nextLine();
                e.addLocation(location);
                break;
            case 3:
                System.out.println("Add Interests:");
                String interests = scanner.nextLine();
                String[] interest = interests.split(",");
                for(int j=0; j < interest.length; j++){
                    e.addInterest(interest[j]);
                }
                break;
            case 4:
                System.out.println("Add Skills:");
                String skills = scanner.nextLine();
                String[] skill = skills.split(",");
                for(int j=0; j < skill.length; j++){
                    e.addInterest(skill[j]);
                }
                break;
            case 5:
                System.out.println("Change CV:");
                String cv = scanner.nextLine();
                e.createCV(cv);
                break;
            default:
                break;
        }

        //TODO:For Testing Purposes
        System.out.println(hokify.getUserByName(user));

        returnMenu();
    }

    public void deleteUserMenu(){

        Scanner scanner = new Scanner(System.in);
        System.out.println("User To Delete:");
        String delete = scanner.nextLine();

        if(hokify.getUserByName(delete).getClass().equals(Employee.class)){
            hokify.deleteUser((Employee)hokify.getUserByName(delete));
        }
        else if(hokify.getUserByName(delete).getClass().equals(Employer.class)){
            hokify.deleteUser((Employer)hokify.getUserByName(delete));
        }
        else{
            System.out.println("The user does not exist.");
        }

        //TODO:For Testing Purposes
        System.out.println(hokify.users);

        returnMenu();
    }

    public void  listEmployeeJobApps(String user){

        System.out.println("Employee-Job Applications:");

        Iterator it = hokify.getEmployeeApplications(hokify.getUserByName(user)).iterator();

        while(it.hasNext()){
            System.out.println(it.next().toString());
        }

        returnMenu();
    }

    public void listJobs(){

        System.out.println("Jobs:");

        Iterator it = hokify.jobs.iterator();

        while(it.hasNext()){
            System.out.println(it.next().toString());
        }

        returnMenu();
    }

    public void searchJobs(String user){

        Scanner scanner = new Scanner(System.in);
        Employee e = (Employee) hokify.getUserByName(user);

        System.out.println("1.Search Job by Skills");
        System.out.println("2.Search Job by Interests");
        System.out.println("3.Search Job by Location");
        System.out.println("4.Search Job by Name");
        System.out.println("0.Go Back\n");

        int i = scanner.nextInt();
        scanner.nextLine();

        switch(i){
            case 0:
                jobMenu();
                break;
            case 1:
                VDMSet skills = hokify.searchJobBySkills(e);
                System.out.println(skills.toString());
                break;
            case 2:
                VDMSet interests = hokify.searchJobByInterests(e);
                System.out.println(interests);
                break;
            case 3:
                System.out.println("Insert Location:");
                String location = scanner.nextLine();
                VDMSet locations = hokify.searchJobByLocation(location);
                System.out.println(locations.toString().replaceAll("\\{","").replaceAll("}",""));
                break;
            case 4:
                System.out.println("Insert Name:");
                String name = scanner.nextLine();
                VDMSet names = hokify.searchJobByName(name);
                System.out.println(names.toString().replaceAll("\\{","").replaceAll("}",""));
                break;
            default:
                break;
        }

        returnMenu();
    }

    public void jobApp(String user){

        int i = askJobId();

        hokify.apply((Employee)hokify.getUserByName(user),hokify.getJobById(i));

        //TODO:For Testing Purposes
        System.out.println(hokify.applications);

        returnMenu();
    }

    public void createJobMenu(String user) {

        Scanner scanner = new Scanner(System.in);
        Job job = null;
        System.out.println("\nCREATE JOB:\n");

        System.out.println("Job Name:\n");
        String name = scanner.nextLine();

        System.out.println("\nSkills:\n");
        String skills = scanner.nextLine();
        String[] skill = skills.split(",");
        VDMSet jobSkills = new VDMSet();

        for (int i = 0; i < skill.length; i++) {
            jobSkills.add(skill[i]);
        }

        System.out.println("\nAreas:\n");
        String areas = scanner.nextLine();
        String[] area = areas.split(",");
        VDMSet jobAreas = new VDMSet();
        for (int i = 0; i < area.length; i++) {
            jobAreas.add(area[i]);
        }

        System.out.println("\nLocation:\n");
        String location = scanner.nextLine();

        System.out.println("\nDescription:\n");
        String description = scanner.nextLine();

        System.out.println("\nFull-Time(1) or Part-Time(2):\n");
        int type = scanner.nextInt();
        if (type == 1) {
            job = new Job(name, jobSkills, jobAreas, location, FullTimeQuote.getInstance(), description);
        } else if (type == 2) {
            job = new Job(name, jobSkills, jobAreas, location, PartTimeQuote.getInstance(), description);
        }

        hokify.addJob((Employer) hokify.getUserByName(user), job);

        //TODO:For Testing Purposes
        System.out.println(hokify.jobs);

        returnMenu();
    }

    public void editJobMenu(String user) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("\nEDIT JOB INFORMATION\n");
        System.out.println("1.Update Skills");
        System.out.println("2.Update Area");
        System.out.println("3.Update Location");
        System.out.println("4.Update Type");
        System.out.println("5.Description");
        System.out.println("0.Go Back\n");

        int j = scanner.nextInt();
        scanner.nextLine();
        int x;

        switch(j){
            case 0:
                jobMenu();
                break;
            case 1:
                x = askJobId();
                System.out.println("Add a New Skill:");
                String skills = scanner.nextLine();
                String[] skill = skills.split(",");
                for(int z=0; z < skill.length; z++){
                    hokify.getJobById(x).addSkill(skill[z]);
                }
                break;
            case 2:
                x = askJobId();
                System.out.println("Add a New Area:");
                String areas = scanner.nextLine();
                String[] area = areas.split(",");
                for(int z=0; z < area.length; z++){
                    hokify.getJobById(x).addArea(area[z]);
                }
                break;
            case 3:
                x = askJobId();
                System.out.println("Change Location:");
                String location = scanner.nextLine();
                hokify.getJobById(x).changeLocation(location);
                break;
            case 4:
                x = askJobId();
                System.out.println("Change Type(full(1)/part(2)):");
                int type = scanner.nextInt();
                if(type==1){
                    hokify.getJobById(x).changeType(FullTimeQuote.getInstance());
                }
                else if(type==2){
                    hokify.getJobById(x).changeType(PartTimeQuote.getInstance());
                }
                break;
            case 5:
                x = askJobId();
                System.out.println("Change Description:");
                String description = scanner.nextLine();
                hokify.getJobById(x).changeDescription(description);
                break;
            default:
                break;
        }

        //TODO:For Testing Purposes
        System.out.println(hokify.jobs);

        returnMenu();
    }

    public void deleteJobMenu(String user){

        int x = askJobId();

        hokify.deleteJob((Employer)hokify.getUserByName(user),hokify.getJobById(x));

        //TODO:For Testing Purposes
        System.out.println(hokify.jobs);

        returnMenu();
    }

    public void returnMenu(){

        Scanner scanner = new Scanner(System.in);

        System.out.println("\n00.Main Menu");
        System.out.println("01.User Menu");
        System.out.println("02.Job Menu\n");

        int i = scanner.nextInt();

        switch (i){
            case 00:
                mainMenu();
                break;
            case 01:
                userMenu();
                break;
            case 02:
                jobMenu();
                break;
            default:
                break;
        }
    }
}