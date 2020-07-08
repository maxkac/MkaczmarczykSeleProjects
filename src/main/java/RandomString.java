import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

public class RandomString {

    private static String chars = "qwertyuiopasdfghjklzxcvbnm";
    private static String specialChars = "-=_+{}[]:;,./<>?!@#$%^&*()";
    private static Random random = new Random();

    public static void main(String[] args) {
        String s = "Login" + randomString(10);
        ArrayList<String> list = new ArrayList<>();
        String string = "";
        for (int i = 0 ; i < 100; i++){
            string = "String" + randomString(10);
            list.add(string);
        }
//        list.add(list.get(1));

        ArrayList list2 = (ArrayList) list.stream().distinct().collect(Collectors.toList());
        boolean allMatch = list.stream().allMatch(e -> e.length() == 15);
        boolean elementIsDistinct = list.size() == list2.size();

        System.out.println("All size is" + 10 + ": " + allMatch);
        System.out.println("All strings is unique: " + elementIsDistinct + "\nList 1 size = " + list.size() + "; List 2 size = " + list2.size());

    }

    public static String randomString(int size){
        StringBuilder result = new StringBuilder();
        for (int i = 0 ; i < size; i++){
            String singleChar = String.valueOf(chars.charAt(random.nextInt(chars.length())));
            if(random.nextInt(6) == 3) {
                String specialChar = String.valueOf(specialChars.charAt(random.nextInt(specialChars.length())));
                result.append(specialChar);
            }else if(random.nextInt(2) == 1){
                result.append(singleChar.toUpperCase());
            }else {
                result.append(singleChar);
            }
        }
        return result.toString();
    }
}
