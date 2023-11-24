
import java.util.Scanner;

public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private  String phoneNumber;
    private  String address;
    private Bank bank = new Bank();

    public double getCardBalance() {
        return bank.getBalance();
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public boolean isHasBookedCar() {
        return hasBookedCar;
    }

    public void setHasBookedCar(boolean hasBookedCar) {
        this.hasBookedCar = hasBookedCar;
    }

    private boolean hasBookedCar;

    private Roles role;

    public User() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Bank getBank() {
        return bank;
    }

    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }



    public static User register(User newUser) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите имя: ");
        newUser.setFirstName(scanner.nextLine());
        System.out.print("Фамилия: ");
        newUser.setLastName(scanner.nextLine());
        System.out.print("Введите номер телефона: ");
        while (true) {
            String num = scanner.nextLine();
            if (num.length() == 10 && num.matches("\\d+")) {
                newUser.setPhoneNumber(num);
                break;
            } else {
                System.out.println("Введите правильный номер телефона!");
            }

        }
        System.out.print("Введите домашний адрес: ");
        newUser.setAddress(scanner.nextLine());
        System.out.print("Введите email: ");
        newUser.setEmail(scanner.nextLine());


        if (newUser.getFirstName().isEmpty() || newUser.getLastName().isEmpty() || newUser.getEmail().isEmpty()) {
            System.out.println("Поля не должны быть пустыми!❌");
        } else if (!newUser.getEmail().contains("@gmail.com")) {
            System.out.println("Не корректый адрес эл.почты!❌");
        } else {
            System.out.print("Введите пароль: ");
            newUser.setPassword(scanner.nextLine());
            if (newUser.getPassword().length() < 4) {
                System.out.println("Пароль долден быть не менее 4 символа!❌");
                return null;

            } else {
                while (true){
                    System.out.println("Хотите быть администратором: (1)Да - (2)Нет");
                    if(scanner.hasNextInt()){
                        int num = scanner.nextInt();
                        if(num == 1){
                            newUser.setRole(Roles.ADMIN);
                            break;
                        } else if(num == 2) {
                            newUser.setRole(Roles.USER);
                            break;
                        } else {
                            System.out.println("Введите правильное число!");
                        }
                    } else {
                        System.out.println("Введите число!");
                        scanner.nextLine();
                    }
                }
                System.out.println("Аккаунт успешно создан!✅");
                return newUser;

            }
        }
        return null;


    }
    public static User login(User [] users){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите email: ");
        String email = scanner.nextLine();
        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();
        for (User user : users) {
            if (user != null && email.equals(user.getEmail()) && password.equals(user.getPassword())) {
                System.out.println("Вы успешно вошли в аккаунт!✅");
                return user;
            }
        }
        System.out.println("Не верный пароль или логин❌");
        return null;

    }}
