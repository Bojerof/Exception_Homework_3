import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
public class Commands {
    private Scanner in = new Scanner(System.in);
    public void input(){
        System.out.println("Введите ФИО и номер телефона через пробел!");
        String fio = in.nextLine();
        String[] text = fio.split(" ");
        try {
            checkLength(text);
            check(text);
            checkInt(text);
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            input();
        }
        saveText(text);
    }
    public void saveText(String[] text){
        String name = text[0] + ".txt";
        try (FileWriter writer = new FileWriter(name, true)){
            writer.write(String.join(" ", text));
            writer.write("\n");
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    public void checkLength(String[] text) throws MyException{
        if (text.length > 4){
            throw new MyException("Неверное количество вводимых данных. У вас на " + (text.length - 4) + " больше!");
        }
        else if (text.length < 4){
            throw new MyException("Неверное количество вводимых данных. У вас на " + (4 - text.length) + " меньше!");
        }
    }
    public void check(String[] text) throws MyException {
        String fio = String.join(" ", text[0], text[1], text[2]);
        if (!fio.matches("[A-Za-zа-яА-Я ]*$"))
            throw new MyException("ФИО вводиться символами");

    }
    public void checkInt(String[] text){
        int number;
        try {
            number = Integer.parseInt(text[3]);
        } catch (NumberFormatException e){
            throw new MyIntException("Введите набор чисел, для ввода номера!");
        }
    }
}
class MyException extends Exception{
    public MyException(String message){
        super(message);
    }
}

class MyIntException extends NumberFormatException{
    public MyIntException(String message){
        super(message);
    }
}

