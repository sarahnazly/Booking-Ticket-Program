import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class BookingTicketApp {
    private List<User> userList;
    private User currentUser;
    
    private JFrame mainFrame;
    private JFrame signUpFrame;
    private JFrame loginFrame;
    private JFrame homeFrame;
    private JFrame bookingFrame;
    private JFrame scheduleFrame;
    private JFrame confirmationFrame;
    private JTextArea scheduleTextArea;
    private JTextArea confirmationTextArea;

    List<Passenger> passengers = new ArrayList<>();
    
    public BookingTicketApp() {
        userList = new ArrayList<>();
        currentUser = null;
        
        createMainFrame();
    }
    
    private void createMainFrame() {
        //halaman depan dari program pemesanan tiket
        mainFrame = new JFrame("Booking Ticket App");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(400, 300);
        mainFrame.setLayout(new GridBagLayout());
        GridBagConstraints layout = new GridBagConstraints();
        
        JLabel welcomeLabel = new JLabel("Selamat Datang di Kereta Api Jaya Jaya Jaya!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        layout.gridx = 0;
        layout.gridy = 0;
        layout.anchor = GridBagConstraints.CENTER;
        layout.insets = new Insets(10, 0, 10, 0);
        mainFrame.add(welcomeLabel, layout);
        
        JButton signUpButton = new JButton("Sign up");
        signUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showSignUpFrame();
            }
        });
        
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showLoginFrame();
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1,2,0,15));
        panel.add(signUpButton);     
        panel.add(loginButton);
        
        layout.gridx = 0;
        layout.gridy = 1;
        mainFrame.add(panel, layout);
        
        mainFrame.setVisible(true);
    }    
    
    private void showSignUpFrame() {
        //halaman regitrasi pengguna program pembelian tiket
        signUpFrame = new JFrame("Registration");
        signUpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        signUpFrame.setSize(300, 150);
        signUpFrame.setLayout(new GridBagLayout());
        GridBagConstraints layout = new GridBagConstraints();
        
        JLabel usernameLabel = new JLabel("Username:");
        layout.gridx = 0;
        layout.gridy = 0;
        layout.anchor = GridBagConstraints.WEST;
        layout.insets = new Insets(10, 10, 5, 5);
        signUpFrame.add(usernameLabel, layout);
        
        JTextField usernameField = new JTextField(15);
        layout.gridx = 1;
        layout.gridy = 0;
        layout.anchor = GridBagConstraints.WEST;
        layout.insets = new Insets(10, 0, 5, 10);
        signUpFrame.add(usernameField, layout);
        
        JLabel passwordLabel = new JLabel("Password:");
        layout.gridx = 0;
        layout.gridy = 1;
        layout.anchor = GridBagConstraints.WEST;
        layout.insets = new Insets(0, 10, 10, 5);
        signUpFrame.add(passwordLabel, layout);
        
        JPasswordField passwordField = new JPasswordField(15);
        layout.gridx = 1;
        layout.gridy = 1;
        layout.anchor = GridBagConstraints.WEST;
        layout.insets = new Insets(0, 0, 10, 10);
        signUpFrame.add(passwordField, layout);
        
        JButton signUpButton = new JButton("Sign up");
        signUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                //handling kondisi pada program
                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(signUpFrame, "Username and password are required!", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (isUsernameExists(username)) {
                    usernameField.setText("");
                    passwordField.setText("");
                    JOptionPane.showMessageDialog(signUpFrame, "Username already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    User user = new User(username, password);
                    userList.add(user);
                    JOptionPane.showMessageDialog(signUpFrame, "User registration successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    signUpFrame.dispose();
                }
            }
        });

        layout.gridx = 0;
        layout.gridy = 2;
        layout.gridwidth = 2;
        layout.anchor = GridBagConstraints.CENTER;
        layout.insets = new Insets(0, 10, 10, 10);
        signUpFrame.add(signUpButton, layout);
        
        signUpFrame.setVisible(true);
    }    
    //handling untuk memeriksa apakah username terdaftar
    protected boolean isUsernameExists(String username) {
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
    //halaman melakukan login akun pengguna program pembelian tiket
    private void showLoginFrame() {
        loginFrame = new JFrame("Login");
        loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loginFrame.setSize(300, 150);
        loginFrame.setLayout(new GridBagLayout());
        GridBagConstraints layout = new GridBagConstraints();
        
        JLabel usernameLabel = new JLabel("Username:");
        layout.gridx = 0;
        layout.gridy = 0;
        layout.anchor = GridBagConstraints.WEST;
        layout.insets = new Insets(10, 10, 5, 5);
        loginFrame.add(usernameLabel, layout);
        
        JTextField usernameField = new JTextField(15);
        layout.gridx = 1;
        layout.gridy = 0;
        layout.anchor = GridBagConstraints.WEST;
        layout.insets = new Insets(10, 0, 5, 10);
        loginFrame.add(usernameField, layout);
        
        JLabel passwordLabel = new JLabel("Password:");
        layout.gridx = 0;
        layout.gridy = 1;
        layout.anchor = GridBagConstraints.WEST;
        layout.insets = new Insets(0, 10, 10, 5);
        loginFrame.add(passwordLabel, layout);
        
        JPasswordField passwordField = new JPasswordField(15);
        layout.gridx = 1;
        layout.gridy = 1;
        layout.anchor = GridBagConstraints.WEST;
        layout.insets = new Insets(0, 0, 10, 10);
        loginFrame.add(passwordField, layout);
        
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                boolean loggedIn = false;
                for (User user : userList) {
                    if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                        currentUser = user;
                        loggedIn = true;
                        break;
                    }
                }
                if (loggedIn) {
                    showHomeFrame();
                    loginFrame.dispose();
                    mainFrame.dispose();
                } else {
                    usernameField.setText("");
                    passwordField.setText("");
                    JOptionPane.showMessageDialog(loginFrame, "Invalid username or password!");
                }
            }
        });
        layout.gridx = 0;
        layout.gridy = 2;
        layout.gridwidth = 2;
        layout.anchor = GridBagConstraints.CENTER;
        layout.insets = new Insets(0, 10, 10, 10);
        loginFrame.add(loginButton, layout);
        
        loginFrame.setVisible(true);
    }    
    //halaman utama untuk program pembelian tiket
    private void showHomeFrame() {
        homeFrame = new JFrame("Home");
        homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homeFrame.setSize(400, 300);
        homeFrame.setLayout(new GridBagLayout());
        GridBagConstraints layout = new GridBagConstraints();
        
        JButton bookTicketButton = new JButton("Pesan tiket");
        bookTicketButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showBookingFrame();
                homeFrame.dispose();
            }
        });
        layout.gridx = 0;
        layout.gridy = 0;
        layout.gridwidth = 2;
        layout.anchor = GridBagConstraints.CENTER;
        layout.insets = new Insets(10, 10, 5, 10);
        homeFrame.add(bookTicketButton, layout);
        
        JButton guideButton = new JButton("Panduan");
        guideButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showGuideFrame();
                homeFrame.dispose();
            }
        });
        layout.gridx = 0;
        layout.gridy = 1;
        layout.gridwidth = 2;
        layout.anchor = GridBagConstraints.CENTER;
        layout.insets = new Insets(0, 10, 5, 10);
        homeFrame.add(guideButton, layout);
        
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentUser = null;
                homeFrame.dispose();
                mainFrame.setVisible(true);
            }
        });
        layout.gridx = 0;
        layout.gridy = 2;
        layout.gridwidth = 2;
        layout.anchor = GridBagConstraints.CENTER;
        layout.insets = new Insets(0, 10, 10, 10);
        homeFrame.add(logoutButton, layout);
        
        homeFrame.setVisible(true);
    }    
    //halaman pemesanan tiket kereta api
    private void showBookingFrame() {
        bookingFrame = new JFrame("Booking Ticket");
        bookingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        bookingFrame.setSize(400, 300);
        bookingFrame.setLayout(new GridBagLayout());
        GridBagConstraints layout = new GridBagConstraints();
        
        JButton bookButton = new JButton("Pesan Kereta");
        bookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showConfirmationFrame();
                bookingFrame.dispose();
            }
        });
        layout.gridx = 0;
        layout.gridy = 0;
        layout.gridwidth = 2;
        layout.anchor = GridBagConstraints.CENTER;
        layout.insets = new Insets(10, 10, 5, 10);
        bookingFrame.add(bookButton, layout);

        JButton scheduleButton = new JButton("Jadwal Kereta");
        scheduleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showScheduleFrame();
                bookingFrame.dispose();
            }
        });        
        layout.gridx = 0;
        layout.gridy = 1;
        layout.gridwidth = 2;
        layout.anchor = GridBagConstraints.CENTER;
        layout.insets = new Insets(0, 10, 5, 10);
        bookingFrame.add(scheduleButton, layout);
        
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentUser = null;
                bookingFrame.dispose();
                mainFrame.setVisible(true);
            }
        });
        layout.gridx = 0;
        layout.gridy = 2;
        layout.gridwidth = 2;
        layout.anchor = GridBagConstraints.CENTER;
        layout.insets = new Insets(0, 10, 10, 10);
        bookingFrame.add(logoutButton, layout);
        
        bookingFrame.setVisible(true);
    }    
    //halaman list pilihan kereta api beserta harga tiketnya
    private void showScheduleFrame() {
        scheduleFrame = new JFrame("Train Schedule");
        scheduleFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        scheduleFrame.setSize(400, 300);
        scheduleFrame.setLayout(new GridBagLayout());
        GridBagConstraints layout = new GridBagConstraints();
        
        scheduleTextArea = new JTextArea(10, 30);
        scheduleTextArea.setEditable(false);
        scheduleTextArea.setText("=================================\n" +
                "Kereta Argo Lawu\n" +
                "=================================\n" +
                "Jakarta - Jogja\n" +
                "Harga Rp250.000\n\n" +
                "=================================\n" +
                "Kereta Kencana\n" +
                "=================================\n" +
                "Jakarta - Surabaya\n" +
                "Harga Rp300.000\n\n" +
                "=================================\n" +
                "Kereta Papandayan\n" +
                "=================================\n" +
                "Jakarta - Bandung\n" +
                "Harga Rp150.000\n\n" +
                "=================================\n" +
                "Kereta Malabar\n" +
                "=================================\n" +
                "Jakarta - Semarang\n" +
                "Harga Rp250.000");
        JScrollPane scrollPane = new JScrollPane(scheduleTextArea);
        layout.gridx = 0;
        layout.gridy = 0;
        layout.gridwidth = 1;
        layout.anchor = GridBagConstraints.CENTER;
        layout.insets = new Insets(10, 10, 5, 10);
        scheduleFrame.add(scrollPane, layout);
        
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scheduleFrame.dispose();
            }
        });
        layout.gridx = 0;
        layout.gridy = 1;
        layout.gridwidth = 1;
        layout.anchor = GridBagConstraints.CENTER;
        layout.insets = new Insets(5, 10, 10, 10);
        scheduleFrame.add(okButton, layout);
        
        scheduleFrame.setVisible(true);
    }    
    //halaman konfirmasi data pemesanan tiket kereta api
    private void showConfirmationFrame() {
        confirmationFrame = new JFrame("Confirmation");
        confirmationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        confirmationFrame.setSize(400, 300);
        confirmationFrame.setLayout(new GridBagLayout());
        GridBagConstraints layout = new GridBagConstraints();
        
        JLabel passengerLabel = new JLabel("Jumlah Penumpang:");
        layout.gridx = 0;
        layout.gridy = 0;
        layout.anchor = GridBagConstraints.WEST;
        layout.insets = new Insets(10, 10, 5, 10);
        confirmationFrame.add(passengerLabel, layout);
        
        JComboBox<Integer> passengerComboBox = new JComboBox<>();
        for (int i = 1; i <= 5; i++) {
            passengerComboBox.addItem(i);
        }
        layout.gridx = 1;
        layout.gridy = 0;
        layout.anchor = GridBagConstraints.WEST;
        layout.insets = new Insets(10, 10, 5, 10);
        confirmationFrame.add(passengerComboBox, layout);
        
        JLabel trainLabel = new JLabel("Pilihan Kereta:");
        layout.gridx = 0;
        layout.gridy = 1;
        layout.anchor = GridBagConstraints.WEST;
        layout.insets = new Insets(5, 10, 5, 10);
        confirmationFrame.add(trainLabel, layout);
        
        JComboBox<String> trainComboBox = new JComboBox<>();
        trainComboBox.addItem("Kereta Argo Lawu");
        trainComboBox.addItem("Kereta Kencana");
        trainComboBox.addItem("Kereta Papandayan");
        trainComboBox.addItem("Kereta Malabar");
        layout.gridx = 1;
        layout.gridy = 1;
        layout.anchor = GridBagConstraints.WEST;
        layout.insets = new Insets(5, 10, 5, 10);
        confirmationFrame.add(trainComboBox, layout);
        
        JLabel classLabel = new JLabel("Pilihan Kelas:");
        layout.gridx = 0;
        layout.gridy = 2;
        layout.anchor = GridBagConstraints.WEST;
        layout.insets = new Insets(5, 10, 5, 10);
        confirmationFrame.add(classLabel, layout);
        
        JComboBox<String> classComboBox = new JComboBox<>();
        classComboBox.addItem("Ekonomi");
        classComboBox.addItem("Bisnis");
        classComboBox.addItem("Eksekutif");
        layout.gridx = 1;
        layout.gridy = 2;
        layout.anchor = GridBagConstraints.WEST;
        layout.insets = new Insets(5, 10, 5, 10);
        confirmationFrame.add(classComboBox, layout);
        
        JLabel timeLabel = new JLabel("Pilihan Jam Keberangkatan:");
        layout.gridx = 0;
        layout.gridy = 3;
        layout.anchor = GridBagConstraints.WEST;
        layout.insets = new Insets(5, 10, 5, 10);
        confirmationFrame.add(timeLabel, layout);
        
        JComboBox<String> timeComboBox = new JComboBox<>();
        timeComboBox.addItem("09.00-12.00");
        timeComboBox.addItem("12.00-15.00");
        timeComboBox.addItem("15.00-18.00");
        timeComboBox.addItem("18.00-21.00");
        layout.gridx = 1;
        layout.gridy = 3;
        layout.anchor = GridBagConstraints.WEST;
        layout.insets = new Insets(5, 10, 5, 10);
        confirmationFrame.add(timeComboBox, layout);
        
        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int passengerCount = (int) passengerComboBox.getSelectedItem();
                String train = (String) trainComboBox.getSelectedItem();
                String trainClass = (String) classComboBox.getSelectedItem();
                String time = (String) timeComboBox.getSelectedItem();
                showPassengerFrame(passengerCount, train, trainClass, time);
                confirmationFrame.dispose();
            }
        });
        layout.gridx = 0;
        layout.gridy = 4;
        layout.gridwidth = 2;
        layout.anchor = GridBagConstraints.CENTER;
        layout.insets = new Insets(10, 10, 10, 10);
        confirmationFrame.add(nextButton, layout);
        
        confirmationFrame.setVisible(true);
    }    
    //halaman detail data penumpang yang akan membeli tiket kereta api
    private void showPassengerFrame(int passengerCount, String train, String trainClass, String time) {
        JFrame passengerFrame = new JFrame("Passenger Details");
        passengerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        passengerFrame.setSize(300, (200*passengerCount));
        passengerFrame.setLayout(new GridBagLayout());
        GridBagConstraints layout = new GridBagConstraints();
        
        for (int i = 1; i <= passengerCount; i++) {
            JPanel passengerPanel = new JPanel();
            passengerPanel.setLayout(new GridBagLayout());
            GridBagConstraints panelLayout = new GridBagConstraints();

            JLabel penumpang = new JLabel("Penumpang " + i );
            penumpang.setFont(new Font("Arial", Font.BOLD, 14));
            panelLayout.gridx = 0;
            panelLayout.gridy = 0;
            panelLayout.anchor = GridBagConstraints.WEST;
            panelLayout.insets = new Insets(10, 10, 5, 10);
            passengerPanel.add(penumpang, panelLayout);
            
            JLabel nameLabel = new JLabel("Nama:");
            panelLayout.gridx = 0;
            panelLayout.gridy = 1;
            panelLayout.anchor = GridBagConstraints.WEST;
            panelLayout.insets = new Insets(10, 10, 5, 10);
            passengerPanel.add(nameLabel, panelLayout);
            
            JTextField nameField = new JTextField(15);
            panelLayout.gridx = 1;
            panelLayout.gridy = 1;
            panelLayout.anchor = GridBagConstraints.WEST;
            panelLayout.insets = new Insets(10, 0, 5, 10);
            passengerPanel.add(nameField, panelLayout);
            
            JLabel ageLabel = new JLabel("Usia:");
            panelLayout.gridx = 0;
            panelLayout.gridy = 2;
            panelLayout.anchor = GridBagConstraints.WEST;
            panelLayout.insets = new Insets(5, 10, 5, 10);
            passengerPanel.add(ageLabel, panelLayout);
            
            JTextField ageField = new JTextField(5);
            panelLayout.gridx = 1;
            panelLayout.gridy = 2;
            panelLayout.anchor = GridBagConstraints.WEST;
            panelLayout.insets = new Insets(5, 0, 5, 10);
            passengerPanel.add(ageField, panelLayout);
            
            passengers.add(new Passenger(nameField, ageField));
            
            layout.gridx = 0;
            layout.gridy = i - 1;
            layout.anchor = GridBagConstraints.WEST;
            layout.insets = new Insets(0, 0, 5, 0);
            passengerFrame.add(passengerPanel, layout);
        }
        
        JButton confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showReceiptFrame(passengerCount, train, trainClass, time);
                passengerFrame.dispose();
            }
        });
        layout.gridx = 0;
        layout.gridy = passengerCount;
        layout.gridwidth = 2;
        layout.anchor = GridBagConstraints.CENTER;
        layout.insets = new Insets(10, 10, 10, 10);
        passengerFrame.add(confirmButton, layout);
        
        passengerFrame.setVisible(true);
    }
    //halaman yang memberikan detail pemesanan tiket kereta api yang sudah dilakukan oleh penumpang
    private void showReceiptFrame(int passengerCount, String train, String trainClass, String time) {
        JFrame receiptFrame = new JFrame("Receipt");
        receiptFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        receiptFrame.setSize(400, 420);
        receiptFrame.setLayout(new GridBagLayout());
        GridBagConstraints layout = new GridBagConstraints();
    
        double totalCost = 0;
        StringBuilder strBuilder = new StringBuilder();
        
        strBuilder.append("===================================\n");
        strBuilder.append("\tStruk Pemesanan Tiket\n");
        strBuilder.append("===================================\n");
        
        for (int i = 0; i < passengerCount; i++) {
            Passenger passenger = passengers.get(i);
            String name = passenger.getNameField().getText();
            int age = Integer.parseInt(passenger.getAgeField().getText());
            
            double ticketCost = calculateTicketCost(train, trainClass, age);
            totalCost += ticketCost;
            
            strBuilder.append("--- Penumpang ").append(i + 1).append(" ---\n");
            strBuilder.append("Nama\t\t: ").append(name).append("\n");
            strBuilder.append("Usia\t\t: ").append(age).append(" tahun\n");
            strBuilder.append("Kereta\t\t: ").append(train).append("\n");
            strBuilder.append("Kelas\t\t: ").append(trainClass).append("\n");
            strBuilder.append("Jam Keberangkatan\t: ").append(time).append("\n");
            strBuilder.append("Biaya\t\t: Rp").append(ticketCost).append("\n\n");
        }
        
        strBuilder.append("Total Biaya\t\t: Rp").append(totalCost).append("\n");
        
        confirmationTextArea = new JTextArea(20, 30);
        confirmationTextArea.setText(strBuilder.toString());
        confirmationTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(confirmationTextArea);
        layout.gridx = 0;
        layout.gridy = 0;
        layout.anchor = GridBagConstraints.CENTER;
        layout.insets = new Insets(10, 10, 10, 10);
        receiptFrame.add(scrollPane, layout);
    
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showBookingFrame();
                receiptFrame.dispose();
            }
        });
        layout.gridx = 0;
        layout.gridy = 1;
        layout.anchor = GridBagConstraints.CENTER;
        layout.insets = new Insets(0, 0, 10, 0);
        receiptFrame.add(okButton, layout);
    
        receiptFrame.setVisible(true);
    }    
    //method untuk melakukan kalkulasi harga tiket kereta api yang dipesan
    private double calculateTicketCost(String train, String trainClass, int age) {
        double baseCost;
        if (train.equals("Kereta Argo Lawu")) {
            baseCost = 250000;
        } else if (train.equals("Kereta Kencana")) {
            baseCost = 300000;
        } else if (train.equals("Kereta Papandayan")) {
            baseCost = 150000;
        } else {
            baseCost = 250000;
        }
        
        if (trainClass.equals("Eksekutif")) {
            baseCost *= 1.5;
        } else if (trainClass.equals("Ekonomi")) {
            baseCost *= 0.8;
        }
        
        if (age <= 5) {
            return 0;
        } else {
            return baseCost;
        }
    }
    //halaman yang menunjukan panduan dalam menggunakan program
    private void showGuideFrame() {
        JFrame guideFrame = new JFrame("Panduan");
        guideFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guideFrame.setSize(600, 500);
        guideFrame.setLayout(new GridBagLayout());
        GridBagConstraints layout = new GridBagConstraints();
        
        JTextArea guideTextArea = new JTextArea(12, 30);
        guideTextArea.setEditable(false);
        guideTextArea.setText("===== Panduan Penggunaan Program Pemesanan Tiket Kereta Api =====\n" +
                "1. Anda diwajibkan untuk membuat akun dengan memasukkan username serta password untuk memesan tiket kereta\n" +
                "2. Setelah membuat akun, pilih menu login untuk masuk ke laman menu utama pemesanan tiket kereta\n" +
                "3. Pada laman menu utama anda dapat memilih menu :\n    1. pesan tiket\n    2. panduan\n    3. batalkan tiket\n    4. keluar\n" +
                "4. Untuk memesan tiket, anda dapat memilih menu 1 dan akan diarahkan ke laman pemesanan tiket\n" +
                "5. Untuk melihat panduan cara menggunakan program, maka anda dapat memilih menu 2 dan akan diarahkan ke laman ini\n" +
                "6. Untuk Membatalkan tiket, anda dapat memilih menu 3 dan akan diarahkan ke laman pembatalan tiket\n" +
                "7. Untuk mengakhiri program anda dapat memilih menu 4 dan program akan berakhir");
        JScrollPane scrollPane = new JScrollPane(guideTextArea);
        layout.gridx = 0;
        layout.gridy = 0;
        layout.gridwidth = 1;
        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.anchor = GridBagConstraints.CENTER;
        layout.insets = new Insets(10, 10, 5, 10);
        guideFrame.add(scrollPane, layout);
        
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guideFrame.dispose();
                showHomeFrame();
            }
        });
        layout.gridx = 0;
        layout.gridy = 1;
        layout.gridwidth = 1;
        layout.fill = GridBagConstraints.NONE;
        layout.anchor = GridBagConstraints.CENTER;
        layout.insets = new Insets(5, 10, 10, 10);
        guideFrame.add(okButton, layout);
        
        guideFrame.setVisible(true);
    }    
    
    private class User {
        private String username;
        private String password;
        
        public User(String username, String password) {
            this.username = username;
            this.password = password;
        }
        
        public String getUsername() {
            return username;
        }
        
        public String getPassword() {
            return password;
        }
    }
    
    private class Passenger {
        private JTextField nameField;
        private JTextField ageField;
        
        public Passenger(JTextField nameField, JTextField ageField) {
            this.nameField = nameField;
            this.ageField = ageField;
        }
        
        public JTextField getNameField() {
            return nameField;
        }
        
        public JTextField getAgeField() {
            return ageField;
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BookingTicketApp();
            }
        });
    }
}