// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import java.util.Scanner;

public class Main {
    private static final Management management = new Management();
    private static User currentUser = null;
    private static final User user = new User();
    private static final Scanner scanner = new Scanner(System.in);

    private static String inputString() {
        System.out.print("Введите название машины: ");
        String returnText;
        while (true) {
            String text = scanner.nextLine();

            if (text.isEmpty()) {
                System.out.println("Значение не должно быть пустым!");
            } else {
                returnText = text;
                break;
            }
        }
        return returnText;
    }

    private static int inputInt() {
        System.out.print("Введите цену машины: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Введите число!");
            scanner.nextLine();
        }
        return scanner.nextInt();
    }

    private static Driver getDriver() {
        Driver driver = new Driver();
        driver.setFirstName(currentUser.getFirstName());
        driver.setLastName(currentUser.getLastName());
        driver.setAddress(currentUser.getAddress());
        driver.setEmail(currentUser.getEmail());
        driver.setPhoneNumber(currentUser.getPhoneNumber());
        return driver;
    }

    public static void main(String[] args) {
        boolean isLoggedIn = false;

        boolean exit = false;
        boolean exitUser = false;
        user.setRole(Roles.USER);
        while (!exit) {
            while (!exitUser) {
                System.out.println("""
                        ДОБРО ПОЖАЛОВАТЬ!
                        1.РЕГИСТРАЦИЯ
                        2.ВОЙТИ
                        3.EXIT""");
                System.out.print("ВЫБЕРИТЕ КОМАНДУ : ");
                String num = scanner.nextLine();

                switch (num) {
                    case "1" -> {
                        User registerUser = User.register(user);
                        management.addUser(registerUser);
                    }
                    case "2" -> {
                        currentUser = User.login(management.users);
                        if (currentUser != null) {
                            exitUser = true;
                            isLoggedIn = true;
                        }
                    }
                    case "3" -> {
                        System.out.println("Вы вышли из программы!✅");
                        exitUser = true;
                        exit = true;

                    }
                }
            }
            if (currentUser != null && currentUser.getRole() == Roles.USER) {
                Inerrloop:
                while (isLoggedIn) {
                    System.out.println("""
                            1.Заказать машину
                            2.Отменить заказ
                            3.Проверить баланс
                            4.Добавить депозит
                            5.Выйти""");
                    if (scanner.hasNextInt()) {
                        int num = scanner.nextInt();
                        switch (num) {
                            case 1:
                                System.out.println("Все доступные машины");
                                for (int i = 0; i < management.cars.length; i++) {
                                    if (management.cars[i] != null) {
                                        Car currentCar = management.cars[i];
                                        String status = currentCar.getDriver() != null ? " (Машина забронирована)" : "";
                                        System.out.println((i + 1) + ". " + currentCar + status);
                                    }
                                }

                                while (true) {
                                    System.out.println("Ваш выбор: ");
                                    scanner.nextLine();

                                    if (scanner.hasNextInt()) {
                                        int indexCar = scanner.nextInt() - 1;
                                        if (!currentUser.isHasBookedCar()) {
                                            if (indexCar >= 0 && indexCar < management.cars.length && management.cars[indexCar] != null) {
                                                if (currentUser.getBank().getBalance() >= management.cars[indexCar].getPrice()) {
                                                    if (management.cars[indexCar].getDriver() == null) {
                                                        Driver driver = getDriver();
                                                        management.addDriver(driver);
                                                        currentUser.getBank().pay(management.cars[indexCar].getPrice());
                                                        management.cars[indexCar].setDriver(driver);
                                                        currentUser.setHasBookedCar(true);
                                                        System.out.println("Вы успешно забронировали машину!");
                                                        break;
                                                    } else {
                                                        System.out.println("Эта машина уже забронирована! Выберите другую.");
                                                    }
                                                } else {
                                                    System.out.println("У вас недостаточно средств!");
                                                    break;
                                                }
                                            } else {
                                                System.out.println("Введите правильный номер!");
                                            }
                                        } else {
                                            System.out.println("Вы уже забронировали машину!");
                                            break;
                                        }


                                    } else {
                                        System.out.println("Введите число!");
                                    }
                                }
                                break;
                            case 2:
                                Driver driver = null;
                                for (Driver driver1 : management.drivers) {
                                    if (driver1 != null && driver1.getEmail().equals(currentUser.getEmail())) {
                                        driver = driver1;
                                        break;
                                    }
                                }
                                if (driver == null) {
                                    System.out.println("Забранируйте машину прежде чем отменять бронь!");
                                } else {
                                    management.deleteDriver(driver.getEmail());
                                    for (Car car : management.cars) {
                                        if (car != null && car.getDriver() != null && car.getDriver().getEmail().equals(currentUser.getEmail())) {
                                            car.setDriver(null);
                                            currentUser.setHasBookedCar(false);
                                            break;
                                        }
                                    }
                                    System.out.println("Бронь упешно отменена!");
                                }
                                break;
                            case 3:
                                System.out.println("Ваш баланс составляет: " + currentUser.getCardBalance());
                                break;
                            case 4:
                                while (true) {
                                    System.out.print("Введите сумму: ");
                                    if (scanner.hasNextInt()) {
                                        int depositNum = scanner.nextInt();
                                        currentUser.getBank().deposit(depositNum);
                                        System.out.println("Вы успешно добавили " + depositNum + " долларов на ваш баланс!");
                                        break;
                                    } else {
                                        System.out.println("Введите число!");
                                    }
                                    scanner.nextLine();

                                }
                                break;
                            case 5:
                                isLoggedIn = false;
                                System.out.println("Вы успешно вышли!");


                                break;
                            default:
                                System.out.println("Введите правильное число!");
                                break;
                        }
                    } else {
                        System.out.println("Введите число!");
                        scanner.nextLine();
                    }
                }
            } else {
                boolean adminBoolean = false;
                while (!adminBoolean) {
                    System.out.println("""
                            1.Добавить машину
                            2.Удалить машину
                            3.Посмотреть все машины
                            4.Выйти""");
                    if (scanner.hasNextInt()) {
                        int num = scanner.nextInt();
                        switch (num) {
                            case 1:
                                Car car = new Car();
                                scanner.nextLine();
                                car.setModelName(inputString());
                                car.setPrice(inputInt());
                                car.setId(management.cars.length + 1);
                                management.addCar(car);
                                System.out.println("Машина успешно добавлена!");
                                break;
                            case 2:
                                if (management.cars.length > 0) {
                                    System.out.println("Все доступные машины");
                                    for (int i = 0; i < management.cars.length; i++) {
                                        if (management.cars[i] != null) {
                                            Car currentCar = management.cars[i];
                                            String status = currentCar.getDriver() != null ? " (Машина забронирована)" : "";
                                            System.out.println((i + 1) + ". " + currentCar + status);
                                        }
                                    }
                                    while (true) {
                                        System.out.print("Введите id машины чтобы удалить: ");
                                        String input = scanner.next();
                                        try {
                                            int modelName = Integer.parseInt(input);
                                            boolean foundCar = false;
                                            for (Car car1 : management.cars) {
                                                if (car1 != null && car1.getId() == modelName) {
                                                    foundCar = true;
                                                    break;
                                                }
                                            }
                                            if (foundCar) {
                                                management.deleteCar(modelName);
                                                System.out.println("Машина успешно удалена!");

                                            } else {

                                                System.out.println("Неправильное id");
                                            }
                                            break;

                                        } catch (NumberFormatException e) {
                                            System.out.println("Введите число!");
                                        }
                                    }
                                } else {
                                    System.out.println("Машин нету!");
                                }

                                break;
                            case 3:
                                int carIndex1 = 0;
                                for (int i = 0; i < management.cars.length; i++) {
                                    if (management.cars[i] != null) {
                                        carIndex1++;
                                    }
                                }
                                if (carIndex1 > 0) {
                                    System.out.println("Все доступные машины");
                                    for (int i = 0; i < management.cars.length; i++) {
                                        if (management.cars[i] != null) {
                                            Car currentCar = management.cars[i];
                                            String status = currentCar.getDriver() != null ? " (Машина забронирована)" : "";
                                            System.out.println((currentCar.getId()) + ". " + currentCar + status);
                                        }
                                    }
                                    System.out.println();
                                }
                                break;
                            case 4:
                                adminBoolean = true;
                                System.out.println("Вы успешно вышли!");

                                break;

                            default:
                                System.out.println("Введите правильное число!");
                                break;
                        }
                    } else {
                        System.out.println("Введите число!");
                        scanner.nextLine();
                    }
                }
            }
        }
    }
}