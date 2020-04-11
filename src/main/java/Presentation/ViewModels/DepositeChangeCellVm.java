package Presentation.ViewModels;

import Presentation.Formatters.IntegerFormatter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class DepositeChangeCellVm extends ListCell<DepositChangeVm> {

    @FXML
    private DatePicker date;

    @FXML
    private Spinner<Double> amount;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button removeButton;

    @FXML
    private void remove(DepositChangeVm itemToRemove){
        getListView().getItems().remove(itemToRemove);
    }

    @Override
    protected void updateItem(DepositChangeVm depositChangeVm, boolean empty) {
        super.updateItem(depositChangeVm, empty);
        if (empty || depositChangeVm == null) {

            setText(null);
            setGraphic(null);

        } else {
            initialize(depositChangeVm);
        }
    }

    private void initialize(DepositChangeVm depositChangeVm) {
        var xmLLoader = new FXMLLoader(getClass().getResource("/Views/DepositChange.fxml"));
        xmLLoader.setController(this);

        try {
            xmLLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        amount.getEditor().setTextFormatter(new IntegerFormatter());
        amount.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0,100000000));
        amount.getValueFactory().valueProperty().bindBidirectional(depositChangeVm.amountProperty().asObject());
        date.valueProperty().bindBidirectional(depositChangeVm.dateProperty());

        removeButton.setOnAction(e -> remove(depositChangeVm));

        setText(null);
        setGraphic(anchorPane);
    }
}
