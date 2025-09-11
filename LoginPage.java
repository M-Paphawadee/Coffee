package ray;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPage extends JFrame {

    public LoginPage() {
        setTitle("COFFEELINE - Login");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(null) {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                setBackground(new Color(250, 241, 234));
            }
        };

        // โลโก้
        OvalLabel logo = new OvalLabel("COFFEELINE");
        logo.setBounds(200, 60, 400, 120);
        panel.add(logo);

        // ช่องกรอกชื่อ
        JTextField usernameField = new JTextField();
        usernameField.setBounds(280, 220, 240, 40);
        usernameField.setFont(new Font("Tahoma", Font.PLAIN, 18));
        usernameField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
        usernameField.setToolTipText("ชื่อผู้ใช้");
        panel.add(usernameField);

        // ช่องกรอกรหัสผ่าน
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(280, 280, 240, 40);
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 18));
        passwordField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
        passwordField.setToolTipText("รหัสผ่าน");
        panel.add(passwordField);

        // ปุ่มเข้าสู่ระบบ
        OvalButton btn = new OvalButton("เข้าสู่ระบบ");
        btn.setBounds(320, 360, 160, 60);
        panel.add(btn);

        add(panel);
        setVisible(true);
    }

    // วงรีโลโก้
    class OvalLabel extends JComponent {
        private final String text;
        public OvalLabel(String text) { this.text = text; }
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(new Color(200, 180, 160));
            g2.fillOval(0, 0, getWidth(), getHeight());
            g2.setColor(new Color(60, 40, 30));
            g2.setFont(new Font("Arial Black", Font.BOLD, 36));
            FontMetrics fm = g2.getFontMetrics();
            int x = (getWidth() - fm.stringWidth(text)) / 2;
            int y = (getHeight() + fm.getAscent()) / 2 - 8;
            g2.drawString(text, x, y);
        }
    }

    // ปุ่มโค้งมน
    class OvalButton extends JButton {
        public OvalButton(String text) {
            super(text);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
            setFont(new Font("Tahoma", Font.PLAIN, 20));
            setForeground(new Color(60, 40, 30));
        }
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(new Color(180, 150, 130));
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);
            FontMetrics fm = g2.getFontMetrics(getFont());
            int x = (getWidth() - fm.stringWidth(getText())) / 2;
            int y = (getHeight() + fm.getAscent()) / 2 - 4;
            g2.setColor(getForeground());
            g2.setFont(getFont());
            g2.drawString(getText(), x, y);
            g2.dispose();
        }
    }
}

