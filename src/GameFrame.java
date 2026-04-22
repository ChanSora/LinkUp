import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class GameFrame extends JFrame {
    private static final int ROWS = 5;
    private static final int COLS = 5;
    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;

    /* 构造函数，设置游戏界面，而不是在Main类中 */
    public GameFrame() {
        super("连连看游戏");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);

        JPanel gridPanel = new JPanel(new GridLayout(ROWS, COLS, 5, 5));

        String imageUrl = "./resources/1.png";//图片路径
        ImageIcon originalIcon = new ImageIcon(imageUrl);
        ImageIcon scaledIcon;//缩放后的图标

        /*
            检查图片是否能够正常加载
            getImageLoadStatus()获取加载状态
            MediaTracker.COMPLETE表示“加载完成” 
        */
        if (originalIcon.getImageLoadStatus() == MediaTracker.COMPLETE) {
            //将图片缩放到适合网格单元格的大小，保持4/5的比例以留出边距
            Image scaledImage = originalIcon.getImage().getScaledInstance(
                    WIDTH / COLS * 4 / 5,
                    HEIGHT / ROWS * 4 / 5,
                    Image.SCALE_SMOOTH//平滑算法
            );
            scaledIcon = new ImageIcon(scaledImage);
        } else {
            scaledIcon = createPlaceholderIcon();//如果图片加载失败，使用占位图标
        }

        for (int i = 0; i < ROWS * COLS; i++) {
            ImageGridCell cell = new ImageGridCell(scaledIcon);
            cell.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    cell.toggleRed();
                }
            });
            gridPanel.add(cell);
        }

        add(gridPanel);
        setVisible(true);
    }

    //上面有一个占位图标，这里创建一个简单的占位图标，显示“Image”字样，颜色是灰色
    private static ImageIcon createPlaceholderIcon() {
        BufferedImage img = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = img.createGraphics();
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillRect(0, 0, 100, 100);
        g2d.setColor(Color.BLACK);
        g2d.drawString("Image", 30, 50);
        g2d.dispose();
        return new ImageIcon(img);
    }
}

class ImageGridCell extends JPanel {
    private final Color normalColor = new Color(240, 240, 240);
    private boolean isRed = false;

    public ImageGridCell(ImageIcon icon) {
        setLayout(new BorderLayout());
        setBackground(normalColor);
        setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JLabel imageLabel = new JLabel(icon);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(imageLabel, BorderLayout.CENTER);
    }

    public void toggleRed() {
        isRed = !isRed;
        setBackground(isRed ? Color.RED : normalColor);
        repaint();
    }
}
