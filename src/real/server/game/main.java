package real.server.game;

import java.util.regex.Pattern;

public class main {
    public static void main(String[] args) {
        String s="3=>djjdjdj";

        int index=s.indexOf("=>");
        if (index!=-1){
            String ss=s.substring(index);
            String w= s.replaceAll(ss,"");
            int id=Integer.parseInt(w);
        }


    }
}
