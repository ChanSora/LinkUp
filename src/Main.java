import javax.swing.*;
import java.awt.*;

// 修改后的 Main.java
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::showMainMenu);
    }

    private static JFrame createFrame() {
        JFrame frame = new JFrame("Link Up");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(360, 300);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout(10, 10));
        return frame;
    }

    private static void createTitle(JFrame frame) {
        JLabel title = new JLabel("连连看", SwingConstants.CENTER);
        title.setFont(new Font("Microsoft YaHei", Font.BOLD, 28));
        frame.add(title, BorderLayout.NORTH);
    }

    private static void createButton(JFrame frame) {
        JButton guestMode = new JButton("游客模式");
        JButton loginMode = new JButton("登录模式");
        JButton registerMode = new JButton("注册模式");
        JButton exitMode = new JButton("退出");

        guestMode.addActionListener(e -> {
            GameFrame gameFrame = new GameFrame();
            WindowManager.switchTo(gameFrame);
        });

        loginMode.addActionListener(e -> {
            Login.showLoginWindow();
            // 需要在 Login 中调用 WindowManager.switchTo
        });

        registerMode.addActionListener(e -> Register.showRegisterWindow());

        exitMode.addActionListener(e -> System.exit(0));

        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 12, 12));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 70, 30, 70));
        buttonPanel.add(guestMode);
        buttonPanel.add(loginMode);
        buttonPanel.add(registerMode);
        buttonPanel.add(exitMode);

        frame.add(buttonPanel, BorderLayout.CENTER);
    }

    private static void showMainMenu() {
        JFrame frame = createFrame();
        createTitle(frame);
        createButton(frame);
        WindowManager.setMainFrame(frame);  // 注册主窗口
//        frame.setVisible(true);
        WindowManager.showMainWindow();
    }
}