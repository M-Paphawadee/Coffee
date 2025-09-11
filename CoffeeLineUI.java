package ray;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

public class CoffeeLineUI extends JFrame {
	private final List<JLabel> menuItems = new ArrayList<>();
    private JLabel activeItem = null;

    public CoffeeLineUI() {
        setTitle("COFFEELINE");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ---------------- Header ----------------
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(150, 90, 50)); // น้ำตาล
        header.setPreferredSize(new Dimension(0, 64));

        JLabel logo = new JLabel("  COFFEELINE");
        logo.setFont(new Font("Arial Black", Font.BOLD, 28));
        logo.setForeground(Color.BLACK);

        JButton userBtn = new JButton("👤");
        userBtn.setFocusPainted(false);
        userBtn.setBorderPainted(false);
        userBtn.setBackground(new Color(150, 90, 50));
        userBtn.setFont(new Font("Arial", Font.PLAIN, 20));
        userBtn.setOpaque(true);

        header.add(logo, BorderLayout.WEST);
        header.add(userBtn, BorderLayout.EAST);
        add(header, BorderLayout.NORTH);

        // ---------------- Sidebar ----------------
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(Color.WHITE);

        sidebar.add(makeMenuTitle("การจัดการวัตถุดิบ"));
        sidebar.add(makeMenuSub("บันทึกข้อมูลการเก็บเกี่ยว"));
        sidebar.add(makeMenuSub("ตรวจสอบคุณภาพเมล็ด"));
        sidebar.add(makeMenuSub("ติดตามปริมาณวัตถุดิบเข้า"));

        sidebar.add(Box.createVerticalStrut(6));
        sidebar.add(makeMenuTitle("กระบวนการผลิต"));
        sidebar.add(makeMenuSub("บันทึกการทำความสะอาด"));
        sidebar.add(makeMenuSub("คัดแยกเมล็ด"));
        sidebar.add(makeMenuSub("บันทึกช่วงเวลาการพักเมล็ด"));
        sidebar.add(makeMenuSub("การกระเทาะเปลือกและคั่ว"));

        sidebar.add(Box.createVerticalStrut(6));
        sidebar.add(makeMenuTitle("การควบคุมคุณภาพ"));
        sidebar.add(makeMenuSub("ตรวจสอบคุณภาพ"));
        sidebar.add(makeMenuSub("บันทึกผลตรวจสอบ"));
        sidebar.add(makeMenuSub("จัดการการคืน"));

        sidebar.add(Box.createVerticalStrut(6));
        sidebar.add(makeMenuTitle("คลังสินค้าและบรรจุภัณฑ์"));
        sidebar.add(makeMenuSub("บันทึกบรรจุภัณฑ์"));
        sidebar.add(makeMenuSub("บันทึกหมายเลขล็อตสินค้า"));
        sidebar.add(makeMenuSub("ติดตามวันหมดอายุ"));

        sidebar.add(Box.createVerticalStrut(6));
        sidebar.add(makeMenuTitle("จัดการคำสั่งซื้อและเอกสาร"));
        sidebar.add(makeMenuSub("เอกสารการส่งออก/ใบกำกับภาษี"));
        sidebar.add(makeMenuSub("จัดการคำสั่งซื้อ"));
        sidebar.add(makeMenuSub("อัปเดตสถานะการสั่งซื้อ"));

        sidebar.add(Box.createVerticalStrut(6));
        sidebar.add(makeMenuTitle("การขนส่งและโลจิสติกส์"));
        sidebar.add(makeMenuSub("รถขนส่งรับเมล็ดกาแฟ"));
        sidebar.add(makeMenuSub("บันทึกเส้นทางและวันที่จัดส่ง"));
        sidebar.add(makeMenuSub("ยืนยันการส่งให้โรงงาน"));

        sidebar.add(Box.createVerticalStrut(6));
        sidebar.add(makeMenuTitle("รายงานและวิเคราะห์"));
        sidebar.add(makeMenuSub("ส่งรายงานผล"));
        sidebar.add(makeMenuSub("รายงานปริมาณการผลิตและการขาย"));
        sidebar.add(makeMenuSub("วิเคราะห์สินค้าขายดีและคุณภาพ"));

        JScrollPane sidebarScroll = new JScrollPane(sidebar);
        sidebarScroll.setPreferredSize(new Dimension(300, 0));
        add(sidebarScroll, BorderLayout.WEST);

        // ---------------- Content ----------------
        JPanel content = new JPanel(new BorderLayout());
        content.setBackground(new Color(250, 240, 230));

        // Search Box
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 12));
        searchPanel.setOpaque(false);

        JTextField searchField = new JTextField("ค้นหา", 20);
        searchField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        JButton searchBtn = new JButton("ค้นหา");
        searchBtn.setFont(new Font("SansSerif", Font.PLAIN, 14));
        searchBtn.setBackground(new Color(170, 130, 100));
        searchBtn.setForeground(Color.WHITE);
        searchBtn.setFocusPainted(false);

        searchPanel.add(searchField);
        searchPanel.add(searchBtn);

        content.add(searchPanel, BorderLayout.NORTH);

        // โลโก้ใหญ่ตรงกลาง
        JLabel bigLogo = new JLabel("COFFEELINE", SwingConstants.CENTER);
        bigLogo.setFont(new Font("Arial Black", Font.BOLD, 48));
        bigLogo.setForeground(new Color(60, 30, 18));
        content.add(bigLogo, BorderLayout.CENTER);

        add(content, BorderLayout.CENTER);

        setVisible(true);
    }

    // ---------- เมธอดช่วยสร้างเมนู ----------
    private JLabel makeMenuTitle(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("TH Sarabun New", Font.BOLD, 20));
        label.setBorder(BorderFactory.createEmptyBorder(10, 12, 6, 12));
        return label;
    }

    private JLabel makeMenuSub(String text) {
        JLabel label = new JLabel("  " + text);
        label.setFont(new Font("TH Sarabun New", Font.PLAIN, 18));
        label.setBorder(BorderFactory.createEmptyBorder(4, 20, 4, 12));
        label.setForeground(Color.BLACK);

        // เก็บไว้ในลิสต์เพื่อล้าง active ได้ทีหลัง
        menuItems.add(label);

        // listener เวลา mouse click
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setActiveItem(label);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (label != activeItem) {
                    label.setForeground(new Color(120, 60, 180)); // hover สีม่วงอ่อน
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (label != activeItem) {
                    label.setForeground(Color.BLACK); // กลับเป็นดำถ้าไม่ active
                }
            }
        });

        return label;
    }

    private void setActiveItem(JLabel label) {
        if (activeItem != null) {
            activeItem.setForeground(Color.BLACK);
        }
        activeItem = label;
        activeItem.setForeground(new Color(100, 0, 160)); // สีม่วงเข้มตอนเลือก
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CoffeeLineUI::new);
    }
}