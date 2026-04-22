import javax.swing.*;//包含和Swing相关的所有类，如JFrame、JButton等
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::showMainMenu);//把showMainMenu方法放在GUI线程中执行，而不是在主线程中直接调用
    }

    /* 显示主菜单 */
    private static JFrame createFrame(){
        JFrame frame = new JFrame("Link Up");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(360, 300);
        frame.setLocationRelativeTo(null);//窗口居中
        frame.setLayout(new BorderLayout(10, 10));
        return frame;
    }

    /* 添加标题 */
    private static void createTitle(JFrame frame){
        JLabel title = new JLabel("连连看", SwingConstants.CENTER);
        title.setFont(new Font("Microsoft YaHei", Font.BOLD, 28));
        frame.add(title, BorderLayout.NORTH);
    }

    /* 创建主菜单界面 */
    private static void createButton(JFrame frame){
        JButton guestMode = new JButton("游客模式");
        JButton loginMode = new JButton("登录模式");
        JButton registerMode = new JButton("注册模式");
        JButton exitMode = new JButton("退出");

        /* 监听游客，登录，注册和退出按钮 */
        guestMode.addActionListener(e -> {
            frame.dispose();
            new GameFrame();
        });

        loginMode.addActionListener(e -> {
            frame.dispose();
            Login.showLoginWindow();//接入登录窗口
        });

        registerMode.addActionListener(e -> {
            frame.dispose();
            Register.showRegisterWindow();//接入注册窗口
        });

        exitMode.addActionListener(e -> System.exit(0));

        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 12, 12));//4行1列，间距设置为12像素
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
        frame.setVisible(true);
    }
}
