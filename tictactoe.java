import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class tictactoe extends JFrame implements ActionListener {
    private char currentPlayer = 'X';
    private JButton[][] buttons = new JButton[3][3];

    public tictactoe() {
        setTitle("Tic-Tac-Toe");
        setSize(300, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));
        initializeButtons();
        setVisible(true);
    }

    private void initializeButtons() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton("");
                buttons[row][col].setFont(new Font("Arial", Font.PLAIN, 48));
                buttons[row][col].setFocusPainted(false);
                buttons[row][col].addActionListener(this);
                add(buttons[row][col]);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton buttonClicked = (JButton) e.getSource();

        if (buttonClicked.getText().equals("") && currentPlayer != ' ') {
            buttonClicked.setText(String.valueOf(currentPlayer));
            buttonClicked.setForeground(currentPlayer == 'X' ? Color.BLUE : Color.RED);

            if (checkWin()) {
                JOptionPane.showMessageDialog(this, "Player " + currentPlayer + " wins!");
                resetBoard();
            } else if (isBoardFull()) {
                JOptionPane.showMessageDialog(this, "It's a draw!");
                resetBoard();
            } else {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }
        }
    }

    private boolean checkWin() {
        // Check rows, columns, and diagonals for a win
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(String.valueOf(currentPlayer)) &&
                    buttons[i][1].getText().equals(String.valueOf(currentPlayer)) &&
                    buttons[i][2].getText().equals(String.valueOf(currentPlayer)) ||
                    buttons[0][i].getText().equals(String.valueOf(currentPlayer)) &&
                            buttons[1][i].getText().equals(String.valueOf(currentPlayer)) &&
                            buttons[2][i].getText().equals(String.valueOf(currentPlayer)) ||
                    buttons[0][0].getText().equals(String.valueOf(currentPlayer)) &&
                            buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
                            buttons[2][2].getText().equals(String.valueOf(currentPlayer)) ||
                    buttons[0][2].getText().equals(String.valueOf(currentPlayer)) &&
                            buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
                            buttons[2][0].getText().equals(String.valueOf(currentPlayer)) ) {
                return true;
            }
        }
        return false;
    }

    private boolean isBoardFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (buttons[row][col].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void resetBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("");
            }
        }
        currentPlayer = 'X';
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new tictactoe();
        });
    }
}
