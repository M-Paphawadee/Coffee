package ray;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class Main extends JFrame {

	public Main() {
        setTitle("COFFEELINE");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // จออยู่ตรงกลาง

        // พื้นหลัง
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                setBackground(new Color(250, 241, 234)); // สีพื้นหลังอ่อนๆ
            }
        };
        mainPanel.setLayout(null); // ใช้ absolute layout จะได้จัดตำแหน่งอิสระ

        // วงรีโลโก้
        OvalLabel logo = new OvalLabel("COFFEELINE");
        logo.setBounds(200, 120, 400, 120); // x, y, width, height
        mainPanel.add(logo);

        // ปุ่มวงรีเข้าสู่ระบบ
        OvalButton loginButton = new OvalButton("เข้าสู่ระบบ");
        loginButton.setBounds(320, 300, 160, 60);
        mainPanel.add(loginButton);

        add(mainPanel);
        setVisible(true);
    }

    // คลาสสำหรับวาดวงรีที่มีข้อความ (โลโก้)
    class OvalLabel extends JComponent {
        private final String text;
        public OvalLabel(String text) {
            this.text = text;
        }
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // วาดวงรี
            g2.setColor(new Color(200, 180, 160));
            g2.fillOval(0, 0, getWidth(), getHeight());

            // ข้อความ
            g2.setColor(new Color(60, 40, 30));
            g2.setFont(new Font("Arial Black", Font.BOLD, 36));

            FontMetrics fm = g2.getFontMetrics();
            int textWidth = fm.stringWidth(text);
            int textHeight = fm.getAscent();

            int x = (getWidth() - textWidth) / 2;
            int y = (getHeight() + textHeight) / 2 - 8;

            g2.drawString(text, x, y);
        }
    }

    // คลาสสำหรับปุ่มโค้งมน
    class OvalButton extends JButton {
        public OvalButton(String text) {
            super(text);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
            setFont(new Font("Tahoma", Font.PLAIN, 20));
            setForeground(new Color(60, 40, 30));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // วาดปุ่มวงรี
            g2.setColor(new Color(180, 150, 130));
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);

            // วาดข้อความตรงกลาง
            FontMetrics fm = g2.getFontMetrics(getFont());
            int textWidth = fm.stringWidth(getText());
            int textHeight = fm.getAscent();

            int x = (getWidth() - textWidth) / 2;
            int y = (getHeight() + textHeight) / 2 - 4;

            g2.setColor(getForeground());
            g2.setFont(getFont());
            g2.drawString(getText(), x, y);

            g2.dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}