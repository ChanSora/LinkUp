import javax.swing.*;
import java.awt.*;

public class Main {
    //主程序的函数入口
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::showMainMenu);
    }

    //创建主界面的框架，标题和按钮
    private static JFrame createFrame() {//创建主界面的框架
        JFrame frame = new JFrame("连连看");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 320);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout(10, 10));
        return frame;
    }

    //创建主界面的标题
    private static void createTitle(JFrame frame) {
        JLabel title = new JLabel("连连看", SwingConstants.CENTER);//标题居中
        title.setFont(new Font("Microsoft YaHei", Font.BOLD, 28));//字体
        frame.add(title, BorderLayout.NORTH);
    }

    //创建游客，用户和退出的按钮
    private static void createButton(JFrame frame) {
        JButton guestMode = new JButton("游客模式");
        JButton userMode = new JButton("注册用户模式");
        JButton exitMode = new JButton("退出");

        guestMode.addActionListener(e -> {//游客模式增加监听器，当用户点击的时候执行代码
            Boolean hardMode = DifficultySelector.chooseDifficulty(frame);
            if (hardMode != null) {
                WindowManager.switchTo(new GameFrame(hardMode));
            }
        });
        userMode.addActionListener(e -> {
            Integer result = UserModeSelector.chooseUserAction(frame);
            if (result == null) {
                return;
            }

            if (result == 0) {//0表示选择了登录，1表示选择注册
                Login.showLoginWindow();
            } else {
                Register.showRegisterWindow();
            }
        });
        exitMode.addActionListener(e -> System.exit(0));//如果用户点出退出，那就直接退出程序

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 12, 12)); //swing GUI组件，panel表示容器，GridLayout表示网格布局，3行1列，间距12像素
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 70, 30, 70));//设置按钮的边距
        buttonPanel.add(guestMode);
        buttonPanel.add(userMode);
        buttonPanel.add(exitMode);

        frame.add(buttonPanel, BorderLayout.CENTER);
    }

    // 显示主菜单
    private static void showMainMenu() {
        JFrame frame = createFrame();
        createTitle(frame);
        createButton(frame);
        WindowManager.setMainFrame(frame);
        WindowManager.showMainWindow();
    }
}
