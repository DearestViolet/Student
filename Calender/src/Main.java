import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
/*

public class Main {


    public static void printMemberPrimaryMenu() {
        System.out.println("Please select menu");
        System.out.println("1. Manage your events");
        System.out.println("2. Manage your memos");
        System.out.println("3. Manage your alerts");
        System.out.println("4. Log out");
    }


    public static void printMemberEventMenu() {
        System.out.println("1. Create new event");
        System.out.println("2. View all events");
        System.out.println("3. Search your events");
        System.out.println("4. Update your event");
        System.out.println("5. Delete your event");
        System.out.println("6. Return to main menu");

    }
    public static void printMemberEventSubMenu() {
        System.out.println("1. Search by date");
        System.out.println("2. Search by tag");
        System.out.println("3. Search by name");
    }

    public void printMemberAlertMenu() {
        System.out.println("1. View all alerts");
        System.out.println("2. Update an alert");
        System.out.println("3. Delete an alert");
    }

    public static void printMemberMemoMenu() {
        System.out.println("1. Create new memo");
        System.out.println("2. View your memos");
        System.out.println("3. Add events to memo");
        System.out.println("4. Add memo to memo");
        System.out.println("5. Update a memo");
        System.out.println("6. Delete a memo");
        System.out.println("7. Return to main menu");

    }

    
    public static void printGuestMenu() {
        System.out.println("What would you like to do? ");
        System.out.println("1. Login");
        System.out.println("2. Create new account");
        System.out.println("3. Exit program");
    }


    public static void main(String[] args) throws SQLException, IOException {
        System.out.println("Welcome to Calendar");
        String tempUsername = null;
        String tempPassword = null;

        boolean running = true;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Scanner in = new Scanner(System.in);
        UserManager userManager = null;
        try {
            userManager = new UserManager("Storage.csv");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }




        while (running) {
            boolean loggedIn = false;

            // Not signed in


            while (!loggedIn) {
                printGuestMenu();
                String beforeSignedInInput = in.nextLine();
                if (beforeSignedInInput.equals("1")) {
                    System.out.println("Enter your email: ");
                    String email = in.nextLine();
                    System.out.println("Enter your password: ");
                    String password = in.nextLine();


                    assert userManager != null;
                    User user = userManager.login(email, password);
                    //catch (Exception e) {
                    //    System.out.println("Database is not properly setup");
                    //    return;
                    //}
                    if (user == null) {
                        System.out.println("User does not exist. Please try again.");
                    } else loggedIn = true;
                    tempPassword = password;
                    tempUsername = email;
                }


            else if (beforeSignedInInput.equals("2")) {
                System.out.println("Enter your email: ");
                String email = in.nextLine();
                System.out.println("Enter your password: ");
                String password = in.nextLine();
                // Create new account
                User newUser = new User(1, email, password);
                    if(userManager.login(email,password) != null){
                        System.out.println("There already exists an account with these credentials.");
                    }
                    else {
                        userManager.add_user(newUser);
                    }
            }

                else if (beforeSignedInInput.equals("3")) {
                    System.out.println("You are now exiting <insert Program name here>");
                    running = false;
                    userManager.saveToFile("Storage.csv");
                    break;
                }
            }

            while (loggedIn) {
                User user = userManager.login(tempUsername,tempPassword);

                printMemberPrimaryMenu();
                String selectedMenu = in.nextLine();

                if (selectedMenu.equals("1")) {
                    // Manage events
                    boolean managingEvents = true;
                    //User currentUser deserialize
                    while (managingEvents) {

                        printMemberEventMenu();
                        String eventOption = in.nextLine();

                        if (eventOption.equals("1")) {

                            Reminder event = null;
                            while (event == null) {


                                System.out.println("Enter name of event: ");
                                String name = in.nextLine();

                                System.out.println("Please type comma separated tags of the event: ");
                                String tags = in.nextLine();

                                System.out.println("Enter start date and time in the format yyyy-MM-dd HH:mm ");
                                String start = in.nextLine();

                                System.out.println("Enter end date and time in the format yyyy-MM-dd HH:mm ");
                                String end = in.nextLine();

                                System.out.println("Would you like your event to repeat? y/n ");
                                String doesRepeat = in.nextLine();

                                if (doesRepeat.equals("n")) {
                                    try {
                                        Date startDate = format.parse(start);
                                        Date endDate = format.parse(end);
                                        event = user.getEm().create(name, Arrays.asList(tags.split(",")),
                                                startDate, endDate);
                                    } catch (ParseException e) {
                                        System.out.println("Dates are not in the right format");
                                    } catch (SQLException e) {
                                        System.out.println("Database setup error");
                                    }
                                } else if (doesRepeat.equals("y")) {
                                    System.out.println("How often do you want your event to repeat? ");
                                    System.out.println("1. Daily");
                                    System.out.println("2. Weekly");
                                    System.out.println("3. Monthly");
                                    String repeat = in.nextLine();

                                    System.out.println("Enter the number of occurrences of this event: ");
                                    int numOccurrences = in.nextInt();
                                    in.nextLine();
                                    Frequency frequency = new Frequency(repeat);
                                    try {
                                        Date startDate = format.parse(start);
                                        Date endDate = format.parse(end);
                                        event = user.getEsm().createSeries(name, Arrays.asList(tags.split(",")),
                                                startDate, endDate, frequency, numOccurrences);
                                    } catch (ParseException e) {
                                        System.out.println("Dates are not in format please enter again: ");
                                    } catch (SQLException e) {
                                        System.out.println("Database setup error");
                                    }
                                }
                            }
                            //Successfully created event
                            System.out.println("Here's your event: ");
                            System.out.println(event);


                            System.out.println("Would you like to create an alert for this event? y/n ");
                            String ans = in.nextLine();
                            if (ans.equals("y")) {
                                System.out.println("1. Create individual alert");
                                System.out.println("2. Create repeated alert");
                                String alertType = in.nextLine();

                                System.out.println("Enter alert date (yyyy-mm-dd hh:mm): ");
                                String alertDate = in.nextLine();
                                System.out.println("Enter alert message: ");
                                String message = in.nextLine();

                                if (alertType.equals("1")) {
                                    user.getAm().createAlert(LocalDateTime.parse(alertDate, formatter), message);
                                } else if (alertType.equals("2")) {
                                    System.out.println("How many alerts do you want to set? ");
                                    int numOccurrences = in.nextInt();
                                    System.out.println("At what intervals do you want your alerts to occur?");
                                    System.out.println("\nEnter interval duration: ");
                                    int duration = in.nextInt();
                                    System.out.println("\nEnter interval unit (min/hrs): ");
                                    in.nextLine();
                                    String unit = in.nextLine();
                                    user.getAm().createAlert(LocalDateTime.parse(alertDate, formatter), message,
                                            numOccurrences, duration, unit);


                            }
                            }
                        }

                        if (eventOption.equals("2")) {
                            // System.out.println("1. View Past Events");
//                            System.out.println("2. View Ongoing Events");
//                            System.out.println("3. View Future Events");
                            // EventManager manager = new EventManager();

                            List<Event> events = null;
                            events = user.getEm().getEvents();
                            if (events != null) {
                                int count = 1;
                                for (Event event : events) {

                                    System.out.println(count + ". " + event);
                                    count++;
                                }
                                System.out.println("Do you want to view an event's associated memos: y/n");
                                String viewAns = in.nextLine();
                                if (viewAns.equals("y")) {
                                    System.out.println("Select event: ");
                                    int eventNum = in.nextInt();
                                    in.nextLine();
                                    Event selectedEvent = events.get(eventNum - 1);
                                    Memo m = user.getEm().getAssociatedMemo(selectedEvent);
                                    System.out.println(user.getMm().findAssociatedMemos(m));
                                }

                            }
                        }
                        if (eventOption.equals("3")) {

                            printMemberEventSubMenu();
                            String searchOption = in.nextLine();

                            if (searchOption.equals("1")) {

                                System.out.println("Enter time in the format yyyy-MM-dd HH:mm ");
                                String dateInputForSearching = in.nextLine();
                                List<Date> searchByDate = new ArrayList<>();
                                Date date = null;
                                try {
                                    date = format.parse(dateInputForSearching);
                                } catch (ParseException e){
                                    System.out.println("DATE FORMAT NOT MATCHING");
                                }
                                if (date != null) {
                                    searchByDate.add(date);

                                    try {
                                        List<Event> events = user.getEs().searchByDates(searchByDate);
                                        System.out.println(events);
                                    } catch (SQLException e) {
                                        System.out.println(e);
                                    }
                                }
                            }

                            else if (searchOption.equals("2")) {
                                System.out.println("Please type comma separated tags of the event: ");
                                String tagForSearching = in.nextLine();
                                List<String> searchByTag = Arrays.asList(tagForSearching.split(","));

                                try {
                                    List<Event> events = user.getEs().searchByTags(searchByTag);
                                    System.out.println(events);
                                }

                                catch (SQLException e){
                                    System.out.println(e);
                                }
                            }

                            else if (searchOption.equals("3")) {
                                System.out.println("Please choose an index of the following memos: ");

                                List<Memo> listOfMemos = user.getMm().getMemos();

                                for (int i = 0; i < listOfMemos.size(); i++) {
                                    System.out.println(i + ": " + listOfMemos.get(i).toString());
                                }

                                Integer memoIndex = Integer.valueOf(in.nextLine());
                                Memo selectedMemo = listOfMemos.get(memoIndex);
                                String memoContent = selectedMemo.getName();
                                List<String> memoInList = new ArrayList<>();
                                memoInList.add(memoContent);

                                user.getEs().searchByMemos(memoInList);

                            }
                        }

                        // Update event
                        if (eventOption.equals("4")) {

                        }

                        // Delete event
                        if (eventOption.equals("5")) {

                        }

                        if (eventOption.equals("6")) {
                            managingEvents = false;
                        }
                    }
                }

                if (selectedMenu.equals("2")) {
                    // Manage memos
                    boolean managingMemos = true;
                    MemoManager currentMM = user.getMm();
                    while (managingMemos) {
                        printMemberMemoMenu();
                        String memoOption = in.nextLine();

                        //Memo creation
                        if (memoOption.equals("1")) {
                            System.out.println("Enter the content of your memo: ");
                            String memoContent = in.nextLine();
                            currentMM.createMemo(memoContent);
                        }

                        //
                        if (memoOption.equals("2")) {

                            currentMM.viewAll();
                            System.out.println("Do you want to view an individual memo? y/n");
                            String viewAns = in.nextLine();
                            if (viewAns.equals("y")) {
                                System.out.println("Select a memo: ");
                                int memoID = in.nextInt();
                                in.nextLine();
                                try {
                                    System.out.println(currentMM.getMemo(memoID - 1));
                                } catch (IndexOutOfBoundsException e){
                                    System.out.println("Incorrect input, please try again.");
                                }

                            }
                        }
                        if (memoOption.equals("3")) {
                            System.out.println("Select memo: ");
                            currentMM.viewAll();
                            int memo = in.nextInt();
                            in.nextLine();
                            if(memo < 0 || memo > currentMM.getMemos().size()){
                                System.out.println("Incorrect input, please try again");
                                break;
                            }

                            List<Event> allEvents = user.getEm().getEvents();
                            int count = 1;
                            System.out.println("Select events separated by comma: ");
                            for (Event e : allEvents) {
                                System.out.println(count + ". " + e.getName() + " / " + e.getTimes());
                                count++;
                            }
                            String events = in.nextLine();
                            String[] eventIDs = events.split(", ?");
                            ArrayList<Reminder> selectedEvents = new ArrayList<Reminder>();
                            Boolean input = true;
                            for (String id : eventIDs) {
                                if((Integer.parseInt(id) - 1) < 0 || (Integer.parseInt(id) - 1) > allEvents.size() ){
                                    System.out.print("Incorrect input, please try again.");
                                    input = false;
                                    break;
                                }
                                selectedEvents.add(allEvents.get(Integer.parseInt(id) - 1));
                            }
                            if(input == false){
                                break;
                            }
                            currentMM.addEvents(selectedEvents, memo - 1);
                            for (Reminder e : selectedEvents) {
                                user.getEm().addMemo(currentMM.getMemo(memo - 1), (Event) e);
                            }
                        }
                        if (memoOption.equals("4")) {
                            currentMM.viewAll();
                            System.out.println("Select the memo you are adding to: ");
                            int m2 = in.nextInt();
                            in.nextLine();
                            if(m2 < 0 || m2 > currentMM.getMemos().size()){
                                System.out.println("Incorrect input, please try again.");
                            }
                            System.out.println("Select the memo you want to add: ");
                            int m1 = in.nextInt();
                            in.nextLine();
                            if(m1 < 0 || m1 > currentMM.getMemos().size()){
                                System.out.println("Incorrect input, please try again.");
                            }
                            currentMM.addMemo(m2-1, m1-1);
                        }

                        if (memoOption.equals("5")) {

                        }

                        if (memoOption.equals("6")) {

                        }

                        if (memoOption.equals("7")) {
                            managingMemos = false;
                        }
                    }
                }

                if (selectedMenu.equals("3")) {
                    //Alerts
                    boolean managingAlerts = true;
                    AlertManager CurrentAM = user.getAm();
                    while (managingAlerts) {
                        System.out.println("1. View alerts");
                        System.out.println("2. Create Alert");
                        System.out.println("3. Return to main menu");
                        String alertOption = in.nextLine();
                        if (alertOption.equals("1")) {
                            System.out.println(CurrentAM);
                        }
                        //if (alertOption.equals("2")) {
                            //delete alert
                        //}
                        if (alertOption.equals("3")) {
                            managingAlerts = false;
                        }

                        if (alertOption.equals("2")) {
                            System.out.println("Enter alert date (yyyy-mm-dd hh:mm): ");
                            String alertDate = in.nextLine();
                            System.out.println("Enter alert message: ");
                            String message = in.nextLine();
                            user.getAm().createAlert(LocalDateTime.parse(alertDate, formatter), message);
                        }

                    }
                }

                if (selectedMenu.equals("4")) {
                    //Logging out
                    loggedIn = false;
                    user.getAm().cancelAll();

                }

            }
        }
    }
}
*/
