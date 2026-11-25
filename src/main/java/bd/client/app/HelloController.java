package bd.client.app;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private Label l;

    @FXML
    private TextField tf;

    private int result;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        l.setText(multiplicationGenerator());
        tf.requestFocus();
    }

    public void onHelloButtonClick() {
        int us = 0;
        try {
            us = Integer.parseInt(this.tf.getText());
        } catch (NumberFormatException e) {
            this.alertWindow("", "Eneter number!", "Error");
            this.tf.requestFocus();
            return;
        }
        if (us == result) {
            this.alertWindow("True!", this.l.getText() + " = " + us, "Result");
        } else {
            this.alertWindow("False!", "Right answer:\n" + this.l.getText() + " = " + this.result, "Result");
        }
        l.setText(multiplicationGenerator());
        tf.setText("");
        tf.requestFocus();
    }

    private String multiplicationGenerator() {
        int a = 0, b = 0;
        final Random random = new Random();
        do {
            a = random.nextInt(10);
            b = random.nextInt(10);
        } while (a == 0 || b == 0 || a == 1 || b == 1);
        this.result = a + b;
        return a + " " + b;
    }

    private void alertWindow(final String header, final String content, final String title) {
        final Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
