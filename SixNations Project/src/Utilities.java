import java.util.ArrayList;

public class Utilities {

    public static boolean onlyContainsNumbers(String text) {
        return (text.matches("[0-9]+"));
    }

    public static boolean max30Chars(String string){
        return string.length() <= 30;
    }

    public static boolean validEmail(String email){
        return (email.contains("@") && email.contains("."));
    }
    public static boolean validNonNegative(int number){return (number>=0); }
    public static boolean validPositive(int number){return (number>0); }
    public static boolean validIndex(int index, ArrayList array){return ((index>=0)&&(index<array.size()));}
    public static Player validPlayer(String name, Club club) {
        Player plr = null;
        for(Player  players :club.getPlayers()){
            if (players.getName().equals(name)){
                plr = players;
            }
        }
    return plr;
    }
    public static Club validClub(String name, ArrayList<Country> countries){
        Club clb = null;
        for(Country country : countries){
            for(Club club: country.getClubs()) {
                if (club.getName().equals(name)) {
                    clb = club;
                }
            }
        }
        return clb;
    }
    public static boolean validIntRange(int start, int end, int value){
        return (value>=start)&&(value<=end);
    }

}