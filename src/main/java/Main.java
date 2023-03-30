import Model.Class;
import Model.User;


import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Connection conn = DbHandler.connect();

        Scanner scanner = new Scanner(System.in);


        List<User> user_list = DbHandler.userdata(conn);
        List<Class> class_list = DbHandler.classdata(conn);


            System.out.println("1. User");
            System.out.println("2. Classes");
            System.out.println("3.Attendance");
            System.out.println("4. Users data");
            System.out.println("5. Classes data");

            int choice = scanner.nextInt();

            switch(choice){
                case 1:
                    System.out.println("Enter your username:");
                    String username = scanner.next();
                    System.out.println("Enter your desired password:");
                    String password = scanner.next();
                    User user = new User(1,username,password);
                    DbHandler.users(conn,user);
                    System.out.println("Data inserted");

                    break;
                case 2:
                    System.out.println("Enter your classname:");
                    String classname = scanner.next();
                    Class classd = new Class(1,classname);
                    DbHandler.classes(conn,classd);
                    System.out.println("Data inserted");
                    break;
                case 3:
                    System.out.println("Enter your username:");
                    username = scanner.next();


                    for (User u : user_list ) {
                        if(username.equals(u.getUsername())){
                            System.out.println("Enter your password:");
                            password = scanner.next();
                            if( password.equals(u.getPassword())){
                                System.out.println("Enter your classname:");
                                classname = scanner.next();

                                user = new User(1,username,password);
                                classd = new Class(1,classname);
                                DbHandler.attendences(conn,user,classd);

                                System.out.println("Completed");
                            }
                        }
                    }


                    break;
                case 4:
                    conn = DbHandler.connect();
                    user_list = DbHandler.userdata(conn);
                    for (User u : user_list ) {
                        System.out.println(u.getId()+"."+" Name: " + u.getUsername());
                    }
                    break;
                case 5:
                    conn = DbHandler.connect();
                    class_list = DbHandler.classdata(conn);
                    for (Class c : class_list ) {
                        System.out.println(c.getId()+"."+" Name: " + c.getClassname());
                    }
                    break;

                default:
                    System.out.println("Enter Valid Number.");
                    break;
            }
        }
    }
