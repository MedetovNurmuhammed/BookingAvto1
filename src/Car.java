public class Car {
    private String modelName;
    private int price;
    private Driver driver = null;
    private int id;

    public Car() {
    }

    public Car(String modelName, int price,  int id) {
        this.modelName = modelName;
        this.price = price;
        this.id = id;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return "Машина " +
                modelName + ", стоит: " + price +
                '$';
    }
}

