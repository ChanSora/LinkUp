import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Main {

    private static final int ROWS = 5;
    private static final int COLS = 5;
    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;

    public static void main(String[] args) {
        JFrame frame = new JFrame("网格中的图片");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocationRelativeTo(null);

        // 创建网格面板
        JPanel gridPanel = new JPanel(new GridLayout(ROWS, COLS, 5, 5));

        // 加载一张示例图片（可以换成你自己的图片
        String imageUrl = "./resources/1.png";
        ImageIcon originalIcon = new ImageIcon(imageUrl);


        // 缩放图片到合适大小（根据网格大小调整）
        Image scaledImage = originalIcon.getImage().getScaledInstance(WIDTH / COLS * 4 / 5, HEIGHT / ROWS * 4 / 5, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        // 创建网格
        for (int i = 0; i < ROWS * COLS; i++) {
            ImageGridCell cell = new ImageGridCell(scaledIcon);
            cell.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    cell.toggleRed();  // 点击变红
                }
            });
            gridPanel.add(cell);
        }

        frame.add(gridPanel);
        frame.setVisible(true);
    }

    // 创建占位图标（当没有图片时使用）
    private static ImageIcon createPlaceholderIcon() {
        BufferedImage img = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = img.createGraphics();
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillRect(0, 0, 100, 100);
        g2d.setColor(Color.BLACK);
        g2d.drawString("图片", 30, 50);
        g2d.dispose();
        return new ImageIcon(img);
    }
}

/**
 * 带图片的网格单元，支持点击变红
 */
class ImageGridCell extends JPanel {
    private ImageIcon icon;
    private final Color normalColor = new Color(240, 240, 240);
    private boolean isRed = false;

    public ImageGridCell(ImageIcon icon) {
        this.icon = icon;
        setLayout(new BorderLayout());
        setBackground(normalColor);
        setBorder(BorderFactory.createLineBorder(Color.GRAY));

        // 显示图片的标签
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