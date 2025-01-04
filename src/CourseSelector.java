import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class CourseSelector extends JFrame {
    private JTextArea courseInfoArea;

    public CourseSelector() {
        setTitle("Select Course");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        courseInfoArea = new JTextArea();
        add(new JScrollPane(courseInfoArea), BorderLayout.CENTER);

        loadCourses();
    }

    private void loadCourses() {
        try (Connection conn = DatabaseConnector.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT name FROM courses");

            while (rs.next()) {
                courseInfoArea.append(rs.getString("name") + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
