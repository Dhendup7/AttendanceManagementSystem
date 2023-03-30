import Model.Class;
import Model.DbHandler;
import Model.User;


import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Connection conn = DbHandler.connect();
        Scanner sc = new Scanner(System.in);
        List<User> user_list = DbHandler.display_user(conn);
        List<Class> class_list = DbHandler.display_class(conn);

//        System.out.println(conn);
        boolean end = false;

        while(!end){
            System.out.println("\n\n1. Insert User");
            System.out.println("2. Insert Classes");
            System.out.println("3. Insert Attendance");
            System.out.println("4. Display All Users");
            System.out.println("5. Display All Classes");
            System.out.println("6. End\n\n");
            int choice = sc.nextInt();

            switch(choice){
                case 1:
                    System.out.println("Enter your username:");
                    String username = sc.next();
                    System.out.println("Enter your desired password:");
                    String password = sc.next();
                    User user = new User(0,username,password);
                    DbHandler.insert_user(conn,user);

                    break;
                case 2:
                    System.out.println("Enter your classname:");
                    String classname = sc.next();
                    Class classobject = new Class(0,classname);
                    DbHandler.insert_class(conn,classobject);
                    break;
                case 3:
                    System.out.println("Enter your username:");
                    username = sc.next();


                    for (User u : user_list ) {
                        if(username.equals(u.getUsername())){
                            System.out.println("Enter your password:");
                            password = sc.next();
                            if( password.equals(u.getPassword())){
                                System.out.println("Enter your classname:");
                                classname = sc.next();

                                user = new User(0,username,password);
                                classobject = new Class(0,classname);
                                DbHandler.insert_attendence(conn,user,classobject);
                            }
                        }
                    }


                    break;
                case 4:
                    conn = DbHandler.connect();
                    user_list = DbHandler.display_user(conn);
                    for (User u : user_list ) {
                        System.out.println(u.getId()+"."+" Name: " + u.getUsername());
                    }
                    break;
                case 5:
                    conn = DbHandler.connect();
                    class_list = DbHandler.display_class(conn);
                    for (Class c : class_list ) {
                        System.out.println(c.getId()+"."+" Name: " + c.getClassname());
                    }
                    break;
                case 6:
                    end = true;
                    break;

                default:
                    System.out.println("Enter Valid Number.");
                    break;
            }
        }
    }
}