
import java.util.Arrays;

public class Management {
    Car car1 = new Car("Ferrari", 10000, 1);
    Car car2 = new Car("Audi", 7000, 2);
    Car car3 = new Car("Mercedes", 9000, 3);
    Car car4 = new Car("BMW", 12000, 4);
    Car car5 = new Car("Kia", 95001, 5);
    Car car6 = new Car("Toyota", 11990, 6);
    Car car7 = new Car("Honda", 10950, 7);
    Car[] cars = {car1, car2, car3, car4, car5, car6, car7};
    User[] users = new User[0];
    Driver[] drivers = new Driver[0];

    public void addUser(User registerUser) {
        User[] newArray = Arrays.copyOf(users, users.length + 1);
        newArray[newArray.length - 1] = registerUser;
        users = newArray;
    }

    public void addCar(Car car) {
        Car[] newArray = Arrays.copyOf(cars, cars.length + 1);
        newArray[newArray.length - 1] = car;
        cars = newArray;
    }

    public void addDriver(Driver driver) {
        Driver[] newArray = Arrays.copyOf(drivers, drivers.length + 1);
        newArray[newArray.length - 1] = driver;
        drivers = newArray;
    }

    public void deleteDriver(String email) {
        Driver[] newProducts = new Driver[100];
        int newIndex = 0;

        for (Driver driver : drivers) {
            if (driver != null && !(driver.getEmail().equals(email))) {
                newProducts[newIndex++] = driver;
            }
        }
        drivers = newProducts;
    }

    public void deleteCar(int id) {
        Car[] newCars = new Car[100];
        int newIndex = 0;

        for (Car car : cars) {
            if (car != null && !(car.getId() == id)) {
                car.setId(newIndex + 1);
                newCars[newIndex++] = car;
            }
        }

        cars = Arrays.copyOf(newCars, cars.length - 1);
    }
}
