import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import main.kotlin.IWebshop;
import main.kotlin.Webshop;
import main.kotlin.animal.AnimalFactory;
import main.kotlin.animal.Gender;
import util.ProductModel;
import util.WebshopObservor;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class FXMLController implements Initializable, WebshopObservor {


    public TextField catNameField;
    public TextField catHabitField;
    public ComboBox<Gender> catGenderBox;
    public TextField catPriceField;
    public Button addCatButton;

    public Button addDogButton;
    public TextField dogNameField;
    public TextField dogPriceField;
    public ComboBox<Gender> dogGenderBox;

    public Button removeAnimalButton;
    public Button reserveAnimalButton;
    public TextField reservorNameField;
    public Button saveShelterButton;

    public TableView<ProductModel> animalTable;
    public TableColumn<ProductModel, String> nameColumn;
    public TableColumn<ProductModel, Gender> genderColumn;
    public TableColumn<ProductModel, String> propertyColumn;
    public TableColumn<ProductModel, Double> priceColumn;

    private IWebshop webshop = new Webshop();
    private AnimalFactory animalFactory = new AnimalFactory();
    private ObservableList<ProductModel> products;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        webshop.observe(this);
        webshop.loadWebshop();
        newCatInit();
        newDogInit();
        cellFactoryInit();
        removeButtonInit();
        reservationInit();
        initSaveButton();
    }

    private void initSaveButton() {
        saveShelterButton.setOnAction(event -> webshop.saveWebshop());
    }

    private void reservationInit() {
        reserveAnimalButton.setOnAction(event -> reserveAnimal());
        animalTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                try {
                    reservorNameField.setText(animalTable.getSelectionModel().getSelectedItem().getReservor().getName());
                } catch (NullPointerException ignored) {
                    reservorNameField.clear();
                }
            }
        });
    }

    private void reserveAnimal() {
        if (animalTable.getSelectionModel().getSelectedItem().getReservor() == null) {
            webshop.reserveAnimal(animalTable.getSelectionModel().getSelectedItem(), reservorNameField.getText());
            animalListInit();
        }
    }

    private void removeButtonInit() {
        removeAnimalButton.setOnAction(event -> removeAnimal());
    }

    private void removeAnimal() {
        ProductModel removable = animalTable.getSelectionModel().getSelectedItem();
        webshop.removeProductModel(removable);
    }

    private void cellFactoryInit() {
        nameColumn.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );
        genderColumn.setCellValueFactory(
                new PropertyValueFactory<>("gender")
        );
        propertyColumn.setCellValueFactory(
                new PropertyValueFactory<>("property")
        );
        priceColumn.setCellValueFactory(
                new PropertyValueFactory<>("price")
        );
    }

    private void animalListInit(){
        products=FXCollections.observableArrayList(webshop.getProducts());
        animalListUpdate();
    }

    private void animalListUpdate() {
        animalTable.setItems(products);
    }


    private void newCatInit() {
        ObservableList<Gender> options =
                FXCollections.observableArrayList(Gender.values());
        catGenderBox.setItems(options.sorted());
        catGenderBox.getSelectionModel().selectFirst();

        addCatButton.setOnAction(event -> createNewCat());

    }

    private void newDogInit() {
        ObservableList<Gender> options =
                FXCollections.observableArrayList(Gender.values());
        dogGenderBox.setItems(options.sorted());
        dogGenderBox.getSelectionModel().selectFirst();

        addDogButton.setOnAction(event -> createNewDog());

    }

    private void createNewDog() {
        if (dogPriceField.getText().trim().isEmpty()) {
            webshop.addProduct(animalFactory.newDog(dogNameField.getText(), dogGenderBox.getSelectionModel().getSelectedItem()));
        } else {
            webshop.addProduct(animalFactory.newDog(dogNameField.getText(), dogGenderBox.getSelectionModel().getSelectedItem(), Double.parseDouble(dogPriceField.getText())));
        }

    }

    private void createNewCat() {
        if (catPriceField.getText().trim().isEmpty()) {
            webshop.addProduct(animalFactory.newCat(catNameField.getText(), catGenderBox.getSelectionModel().getSelectedItem(), catHabitField.getText()));
        } else {
            webshop.addProduct(animalFactory.newCat(catNameField.getText(), catGenderBox.getSelectionModel().getSelectedItem(), catHabitField.getText(), Double.parseDouble(catPriceField.getText())));
        }

    }

    public void Update(List<ProductModel> newValue) {
        products = FXCollections.observableArrayList(newValue);
        animalListUpdate();
    }

//    private void newAnimalTypeSelected() {
//        double anchorX = AnimalEditContainer.getLayoutX();
//        double anchorY = AnimalEditContainer.getLayoutY();
//        double baseShiftX = 10.0;
//        double basePrefWidth = 150;
//        double baseIncrementY = 30.0;
//
//        AnimalEditContainer.getChildren().clear();
//        createBaseAnimalUIElements(baseShiftX, basePrefWidth, baseIncrementY);
//
//        switch (NewAnimalCombo.getSelectionModel().getSelectedItem()) {
//            case "Cat":
//                createBadHabitField(baseShiftX, basePrefWidth, baseIncrementY);
//                NewAnimalButton.setOnAction(event -> createNewCat());
//                break;
//            case "Dog":
//                Label lastWalkLabel = new Label();
//                lastWalkLabel.setText("The dogs last walk is set automatically");
//
//        }
//
//    }
//    private void createBadHabitField(double baseShiftX, double basePrefWidth, double baseIncrementY) {
//        Label habitLabel = new Label();
//        habitLabel.setText("Bad habits:");
//        habitLabel.setLayoutX(baseShiftX);
//        habitLabel.setLayoutY(baseIncrementY * 3);
//        habitLabel.setPrefWidth(basePrefWidth);
//        AnimalEditContainer.getChildren().add(habitLabel);
//        TextField habitField = new TextField();
//        habitField.setLayoutX(baseShiftX + basePrefWidth);
//        habitField.setLayoutY(baseIncrementY * 3);
//        habitField.setPrefWidth(basePrefWidth * 2);
//        AnimalEditContainer.getChildren().add(habitField);
//        habitField.textProperty().addListener((observable, oldValue, newValue) -> {
//            animalProperties.remove(oldValue);
//            animalProperties.add(newValue);
//        });
//    }
//
//    private void createBaseAnimalUIElements(double baseShiftX, double basePrefWidth, double baseIncrementY) {
//        Label nameLabel = new Label();
//        nameLabel.setText("Name:");
//        nameLabel.setLayoutX(baseShiftX);
//        nameLabel.setLayoutY(baseIncrementY);
//        nameLabel.setPrefWidth(basePrefWidth);
//        AnimalEditContainer.getChildren().add(nameLabel);
//        TextField nameField = new TextField();
//        nameField.setLayoutX(baseShiftX + basePrefWidth);
//        nameField.setLayoutY(baseIncrementY);
//        nameField.setPrefWidth(basePrefWidth);
//        AnimalEditContainer.getChildren().add(nameField);
//        nameField.textProperty().addListener((observable, oldValue, newValue) -> {
//            animalProperties.remove(oldValue);
//            animalProperties.add(newValue);
//        });
//
//        Label genderLabel = new Label();
//        genderLabel.setText("Gender:");
//        genderLabel.setLayoutX(baseShiftX);
//        genderLabel.setLayoutY(baseIncrementY * 2);
//        genderLabel.setPrefWidth(basePrefWidth);
//        AnimalEditContainer.getChildren().add(genderLabel);
//        ComboBox<Gender> genderCombo = new ComboBox<>();
//        genderCombo.setLayoutX(baseShiftX + basePrefWidth);
//        genderCombo.setLayoutY(baseIncrementY * 2);
//        genderCombo.setPrefWidth(basePrefWidth);
//        ObservableList<Gender> options = FXCollections.observableArrayList();
//        Collections.addAll(options, Gender.values());
//        genderCombo.setItems(options);
//        AnimalEditContainer.getChildren().add(genderCombo);
//        genderCombo.getSelectionModel().selectFirst();
//        genderCombo.valueProperty().addListener((observable, oldValue, newValue) -> {
//            animalProperties.remove(oldValue);
//            animalProperties.add(newValue);
//        });
//    }

}