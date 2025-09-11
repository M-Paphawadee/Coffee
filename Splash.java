package ray;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Splash extends JFrame {

    public Splash() {
        setTitle("COFFEELINE - Splash");
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
        logo.setBounds(200, 120, 400, 120);
        panel.add(logo);

        // ปุ่มเข้าสู่ระบบ → เปิดหน้า Login
        OvalButton btn = new OvalButton("เข้าสู่ระบบ");
        btn.setBounds(320, 300, 160, 60);
        btn.addActionListener((ActionEvent e) -> {
            dispose();              // ปิดหน้าปัจจุบัน
            new LoginPage();        // เปิดหน้าล็อกอิน
        });
        panel.add(btn);

        add(panel);
        setVisible(true);
    }

    // โลโก้รูปวงรี
    class OvalLabel extends JComponent {
        private final String text;
        public OvalLabel(String text) {
            this.text = text;
        }
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Splash::new);
    }
}

