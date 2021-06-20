package sample;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import javax.swing.*;


public class ControllerForG {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button button;

    @FXML
    private Label labelCount;

    @FXML
    private ListView<Integer> listView;

    public static Stage stage;

    public static ObservableList<Integer> observableList = FXCollections.observableArrayList();


    @FXML
    void onOk(ActionEvent event) {
        if(listView.getSelectionModel().getSelectedIndex()==-1){
            JOptionPane.showMessageDialog(null,
                    "Необходимо выбрать значение g.","Ошибка", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        Main.crypt.setG(observableList.get(listView.getSelectionModel().getSelectedIndex()));
        Main.afterG();
        //stage.hide();
    }

    @FXML
    void initialize() {
        observableList = Main.crypt.getListG();
        listView.setItems(observableList);
        labelCount.setText(String.valueOf(observableList.size()));
        //System.out.println(observableList.size());
    }

}