package Presentation.Controllers;

import Domain.Models.PercentPaymentPeriodType;
import Domain.Services.Calculators.CapitalisationDebtCalculator;
import Domain.Services.Calculators.SimpleDebtCalculator;
import Presentation.Formatters.IntegerFormatter;
import Presentation.ViewModels.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.util.converter.NumberStringConverter;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    public TextField annualPercent;
    @FXML
    public Spinner<Integer> periodInDays;
    @FXML
    public DatePicker startDate;
    @FXML
    public TextField initialAmount;
    @FXML
    public CheckBox hasCapitalisation;
    @FXML
    public ComboBox<PercentPaymentPeriodType> capitalisation;
    @FXML
    public VBox capitalisationOptions;
    @FXML
    public ListView<DepositChangeVm> additions;
    @FXML
    public ListView<DepositChangeVm> withdrawals;
    @FXML
    public TitledPane additionsPane;
    @FXML
    public TitledPane withdrawalsPane;

    private MainViewModel mainVm;

    private void InitListView(ListView<DepositChangeVm> listView, ObservableList<DepositChangeVm> items, TitledPane pane) {
        listView.itemsProperty().setValue(items);
        listView.setCellFactory(c -> new DepositeChangeCellVm());
    }

    private void AddDepositChange(ObservableList<DepositChangeVm> targetList, boolean isAddition) {
        targetList.add(new DepositChangeVm(isAddition));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mainVm = new MainViewModel();
        initialAmount.textProperty().bindBidirectional(mainVm.initialAmountProperty(), new NumberStringConverter());
        initialAmount.setTextFormatter(new IntegerFormatter());
        annualPercent.textProperty().bindBidirectional(mainVm.annualPercentProperty(),new NumberStringConverter());

        periodInDays.getEditor().setTextFormatter(new IntegerFormatter());
        periodInDays.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE));
        periodInDays.getValueFactory().valueProperty().bindBidirectional(mainVm.periodInDaysProperty().asObject());
        startDate.valueProperty().bindBidirectional(mainVm.startDateProperty());

        hasCapitalisation.selectedProperty().bindBidirectional(mainVm.hasCapitalisationProperty());
        capitalisationOptions.visibleProperty().bind(mainVm.hasCapitalisationProperty());
        capitalisation.getItems().setAll(FXCollections.observableArrayList(PercentPaymentPeriodType.values()));
        capitalisation.setValue(capitalisation.getItems().get(0));
        InitListView(additions, mainVm.getAdditions(), additionsPane);
        InitListView(withdrawals, mainVm.getWithdrawals(), withdrawalsPane);
    }

    public void addAddition() {
        AddDepositChange(mainVm.getAdditions(),true);
    }

    public void addWithdrawals() {
        AddDepositChange(mainVm.getWithdrawals(),false);
    }

    public void calculate(){

        var calculator = mainVm.isHasCapitalisation() ?
                new CapitalisationDebtCalculator(capitalisation.getValue()) :
                new SimpleDebtCalculator();

        var result =   calculator.CalculateDebt(mainVm.getInitialAmount(),
                mainVm.getAnnualPercent(),
                mainVm.getStartDate(),
                periodInDays.getValue(),
                mainVm.getUserDepositChanges());

        var alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Результаты расчета вклада");
        alert.setHeaderText("Состояние счета на "+mainVm.getStartDate().plusDays(periodInDays.getValue()));
        alert.setContentText("Всего выплат: "+result.getPercentsPaid()+"\nОстаток вклада: "+result.getTotalAmount());
        alert.show();
    }
}
