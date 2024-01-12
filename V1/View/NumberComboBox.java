package V1.View;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumberComboBox extends JPanel {

    private JComboBox<Integer> numberComboBox;
    private int n = 10;

    public NumberComboBox() {
        initializeUI();
        setOpaque(false);
    }

    private void initializeUI() {

        // Create an array of numbers from 2 to 12
        Integer[] numbers = new Integer[11];
        for (int i = 0; i < 11; i++) {
            numbers[i] = i + 2;
        }

        // Create a JComboBox with the array of numbers
        numberComboBox = new JComboBox<>(numbers);
    numberComboBox.setSelectedIndex(8);
        // Add ActionListener to handle selection events
        numberComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle selection event here if needed
                NumberComboBox.this.n = Integer.parseInt(numberComboBox.getSelectedItem().toString());
                
            }
        });

        this.add(numberComboBox);

    }
    public int get_n(){
        return n;
    }

    // public static void main(String[] args) {
    //     javax.swing.SwingUtilities.invokeLater(new Runnable() {
    //         public void run() {
    //             new NumberComboBox().setVisible(true);
    //         }
    //     });
    // }
}
