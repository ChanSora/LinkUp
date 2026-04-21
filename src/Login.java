import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Login {
    static public void main(String[] args){
        JFrame loginFrame = new JFrame("Login");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(400,200);
        loginFrame.setLayout(null);

        JTextField username = new JTextField();
        username.setSize(100,50);
        username.setLocation(50,50);

        JButton loginButton = new JButton("Login");
        loginButton.setSize(100,30);
        loginButton.setLocation(50,100);

        loginButton.addActionListener(e -> {
            String thisUser = username.getText();
            Scanner Users = null;
            try {
                Users = new Scanner(new File("src/Users.txt"));
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            ArrayList<String> users = new ArrayList<>();
            while (Users.hasNextLine()) {
                users.add(Users.nextLine());
            }
            boolean match = false;
            for (String user : users) {
                System.out.println(user);
                if (user.equals(thisUser)) {
                    match = true;
                    break;
                }
            }
            if (match) {
                JOptionPane.showMessageDialog(loginFrame,"Welcome!");
            } else {
                JOptionPane.showMessageDialog(loginFrame,"no record found.");
            }
        });
        loginFrame.add(username);
        loginFrame.add(loginButton);
        loginFrame.setVisible(true);
    }
}
