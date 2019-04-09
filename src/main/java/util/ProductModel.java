package util;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import main.kotlin.animal.Gender;
import main.kotlin.animal.Reservor;

public class ProductModel {
    private final SimpleStringProperty name;
    private final SimpleStringProperty property;
    private final SimpleDoubleProperty price;
    private final SimpleStringProperty gender;
    private Class type;
    private Reservor reservor=null;

    public ProductModel(String name, String property, Double price, Gender gender, Class type) {
        this.name = new SimpleStringProperty(name);
        this.property = new SimpleStringProperty(property);
        this.price = new SimpleDoubleProperty(price);
        this.gender=new SimpleStringProperty(gender.toString());
        this.type=type;
    }

    public Reservor getReservor() {
        return reservor;
    }

    public void setReservor(Reservor reservor) {
        this.reservor = reservor;
    }

    public Class getType() {
        return type;
    }

    public void setType(Class type) {
        this.type = type;
    }

    public String getName() {
        return name.get();
    }
    public void setName(String fName) {
        name.set(fName);
    }

    public String getGender() {
        return gender.get();
    }

    public Gender getGenderAsGender() {
        return Gender.valueOf(gender.get());
    }

    public void setGender(String gender) {
        this.gender.set(gender);
    }

    public String getProperty() {
        return property.get();
    }
    public void setProperty(String fName) {
        property.set(fName);
    }

    public Double getPrice() {
        return price.get();
    }
    public void setPrice(Double price) {
        this.price.set(price);
    }
}
