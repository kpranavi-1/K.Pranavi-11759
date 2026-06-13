import java.util.Scanner;

// User Class
class User {
    private String username;
    private boolean isOnline;

    public User(String username) {
        this.username = username;
        this.isOnline = true;
    }

    public String getUsername() {
        return username;
    }

    public boolean getStatus() {
        return isOnline;
    }
}

// Message Class
class Message {
    private User sender;
    private String content;
    private String timestamp;

    public Message(User sender, String content, String timestamp) {
        this.sender = sender;
        this.content = content;
        this.timestamp = timestamp;
    }

    public String getMessageDetails() {
        return "[" + timestamp + "] " + sender.getUsername() + ": " + content;
    }
}

// ChatRoom Class
class ChatRoom {
    private String roomName;
    private Message[] messages;
    private User[] users;
    private int messageCount;
    private int userCount;

    public ChatRoom(String roomName, int maxUsers, int maxMessages) {
        this.roomName = roomName;
        users = new User[maxUsers];
        messages = new Message[maxMessages];
        userCount = 0;
        messageCount = 0;
    }

    public void addUser(String name) {
        if (userCount < users.length) {
            users[userCount] = new User(name);
            userCount++;
            System.out.println(name + " joined the chat room.");
        } else {
            System.out.println("User limit reached.");
        }
    }

    public void sendMessage(int userIndex, String text) {
        if (messageCount < messages.length && userIndex < userCount) {
            String time = java.time.LocalTime.now().withNano(0).toString();
            messages[messageCount] = new Message(users[userIndex], text, time);
            messageCount++;
            System.out.println("Message sent successfully.");
        } else {
            System.out.println("Unable to send message.");
        }
    }

    public void displayChatHistory() {
        System.out.println("\n----- Chat History -----");
        if (messageCount == 0) {
            System.out.println("No messages available.");
        } else {
            for (int i = 0; i < messageCount; i++) {
                System.out.println(messages[i].getMessageDetails());
            }
        }
    }

    public void displayUsers() {
        System.out.println("\nUsers:");
        for (int i = 0; i < userCount; i++) {
            System.out.println((i + 1) + ". " + users[i].getUsername());
        }
    }
}

// Main Class
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ChatRoom room = new ChatRoom("General", 10, 50);

        room.addUser("Alice");
        room.addUser("Bob");
        room.addUser("Charlie");

        int choice;

        do {
            System.out.println("\n===== CHAT APPLICATION =====");
            System.out.println("1. Send Message");
            System.out.println("2. View Chat History");
            System.out.println("3. Exit");
            System.out.print("Enter Choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    room.displayUsers();
                    System.out.print("Select User Number: ");
                    int userNo = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Message: ");
                    String msg = sc.nextLine();

                    room.sendMessage(userNo - 1, msg);
                    break;

                case 2:
                    room.displayChatHistory();
                    break;

                case 3:
                    System.out.println("Exiting Chat Application...");
                    break;

                default:
                    System.out.println("Invalid Choice.");
            }

        } while (choice != 3);

        sc.close();
    }
}