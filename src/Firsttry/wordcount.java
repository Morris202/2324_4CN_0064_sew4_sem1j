package Firsttry;

public class wordcount {
    public static void main(String[] args) {

    }
    public static int count(String s){
        String s1 = s.replaceAll("(<[A-z \\\\=\"]+>?)|[:.\"]", " ");
        if (s.isBlank() || s.isEmpty()){
            return 0;
        }

        int zaehler = 0;
        if (!s1.contains(" ")){
            return 1;
        }

        String array [] = s1.split(" ");
        for (int i = 0; i < array.length; i++) {
            if (!array[i].isBlank()){
                zaehler++;
            }
        }
        return zaehler;
    }
}
