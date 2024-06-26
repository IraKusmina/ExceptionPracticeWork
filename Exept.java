/*Напишите приложение, которое будет запрашивать у пользователя следующие 
данные в произвольном порядке, разделенные пробелом:
Фамилия Имя Отчество датарождения номертелефона пол

Форматы данных:
фамилия, имя, отчество - строки
дата_рождения - строка формата dd.mm.yyyy
номер_телефона - целое беззнаковое число без форматирования
пол - символ латиницей f или m.

Приложение должно проверить введенные данные по количеству. 
Если количество не совпадает с требуемым, вернуть код ошибки, 
обработать его и показать пользователю сообщение, что он ввел меньше и больше данных, чем требуется.

Приложение должно попытаться распарсить полученные значения и выделить из них требуемые параметры. 
Если форматы данных не совпадают, нужно бросить исключение, соответствующее типу проблемы. 
Можно использовать встроенные типы java и создать свои. Исключение должно быть корректно обработано, 
пользователю выведено сообщение с информацией, что именно неверно.

Если всё введено и обработано верно, должен создаться файл с названием, равным фамилии, 
в него в одну строку должны записаться полученные данные, вида

<Фамилия><Имя><Отчество><датарождения> <номертелефона><пол>

Однофамильцы должны записаться в один и тот же файл, в отдельные строки.

Не забудьте закрыть соединение с файлом.

При возникновении проблемы с чтением-записью в файл, исключение должно быть корректно обработано, 
пользователь должен увидеть стектрейс ошибки.
*/


import java.io.FileWriter;
import java.io.IOException;
import java.time.Year;
import java.util.Scanner;

public class Exept {
    
    public static void main(String[] args) throws Exception {
        Object[] info = getInfo();
    
        for (Object string : info) {
            System.out.println(string);
        }
        String fileName = "C:/Education/Exception/FinalWork/" + String.valueOf(info[0]) + ".txt";
        System.out.println(fileName);
        write(fileName, info);
    }

    private static Object[] getInfo() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите через пробел: Фамилию, Имя, Отчество, дата рождения формата dd.mm.yyyy, номер телефоона, пол буквой m/f (m-мужчина, f-женщина)");
        String text = scanner.nextLine();
        String [] info = text.split(" ");
        try {
            checkDate(info[3]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int tel = getTel(info[4]);
        String male = getMale(info[5]);
        scanner.close();
        checkArray(info);
        String surname = info[0];
        String name = info[1];
        String secondName = info[2];
        String date = info[3];
        Object[]newInfo = {surname, name, secondName, date, tel, male};
        return newInfo;
    }

    private static void checkArray(String[] info) throws Exception {
        try {
            if (info.length != 6){
                throw new Exception();
            }
        }
        catch (Exception e){
            throw new Exception("Неверно введены запрашиваемые данные. Введно больше или меньше значений!");

        }
    }

    private static String getMale (String male) throws Exception{
        try {
            if ((male.equals("m")) || (male.equals("f"))){
                return male;
            }
            else {
                throw new Exception();
            }
                }
        catch (Exception e){
                throw new Exception("Неверно введён пол");
        }
    }

    private static int getTel(String info) {
        try{
            int tel = Integer.parseInt(info);
            return tel;
        }
        catch (NumberFormatException e){
            throw new NumberFormatException("Неверно введен номер телефона. Запись должны быть числовой");
        }
    }

    private static void checkDate(String info) throws Exception{
        try {
            String[] newDate = info.split("\\.");
            int day = Integer.parseInt(newDate[0]);
            int month = Integer.parseInt(newDate[1]);
            int year = Integer.parseInt(newDate[2]);
            if (day > 0 && day < 32){
                if (month > 0 && month <= 12){
                    if (year > 1900 && year < Year.now().getValue()){
                    }   
                    else{
                        throw new Exception();
                    }
                }
                else{
                    throw new Exception();
                }
            }
            else{
                throw new Exception();
            }
            
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException("Неверно введены день, месяц и год! Необходимы три числа, разделенные точкой!");
        }
        catch (NumberFormatException e){
            throw new NumberFormatException("Неверный ввод даты. Запись должна быть числовой!");
        }
        catch(Exception e){
            throw new Exception("День, месяц или год находятся за пределами допустимых значений");
        }
    }
    
    public static void write (String filename, Object [] info) throws IOException {
        
        try {
            FileWriter text = new FileWriter(filename, true);
       
            for (Object object : info) {
                text.write(object + " ");              } 

            text.write("\n");
            text.close();
        }
        catch (IOException e){
            throw new IOException("Ошибка при работе с файлом");

        }
    }    
}
