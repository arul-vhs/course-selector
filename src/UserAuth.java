import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class UserAuth extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public UserAuth() {
        setTitle("Login/Sign Up");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));
        
        JLabel userLabel = new JLabel("Username:");
        usernameField = new JTextField();
        JLabel passLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new LoginAction());
        
        JButton signupButton = new JButton("Sign Up");
        signupButton.addActionListener(new SignupAction());
        
        panel.add(userLabel);
        panel.add(usernameField);
        panel.add(passLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(signupButton);
        
        add(panel);
    }

    private class LoginAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            try {
                Connection conn = DatabaseConnector.getConnection();
                String query = "SELECT * FROM users WHERE username = ? AND password = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, username);
                stmt.setString(2, password);
                ResultSet rs = stmt.executeQuery();
                
                if (rs.next()) {
                    JOptionPane.showMessageDialog(UserAuth.this, "Login successful!");
                    // Proceed to next page or functionality (e.g., course selector)
                } else {
                    JOptionPane.showMessageDialog(UserAuth.this, "Invalid login details.");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(UserAuth.this, "Database error: " + ex.getMessage());
            }
        }
    }

    private class SignupAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            try {
                Connection conn = DatabaseConnector.getConnection();
                String query = "INSERT INTO users (username, password) VALUES (?, ?)";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, username);
                stmt.setString(2, password);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(UserAuth.this, "Sign-up successful!");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(UserAuth.this, "Database error: " + ex.getMessage());
            }
        }
    }
}
