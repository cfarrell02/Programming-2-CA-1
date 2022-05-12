
import java.util.ArrayList;
public class Club {
    /**
     * The Club class consists of an Array of each player
     * The club class object makes up each country
     *
     * @author Cian Farrell
     * @Version 1.0
     */
    private String name;
    private String headCoach;
    private int members;
    private ArrayList<Player> players;

    public Club(String name, String headCoach, int members) {
        if(Utilities.max30Chars(name)) {
            this.name = name;
        }
        else{
            this.name = name.substring(0,30);
        }
        if(Utilities.max30Chars(headCoach)) {
            this.headCoach = headCoach;
        }
        else {
            this.headCoach = headCoach.substring(0,30);
        }
        if(Utilities.validNonNegative(members)) {
            this.members = members;
        }
        else{
            this.members = 0;
        }
        this.players = new ArrayList<Player>();
    }

    /**
     * Gets the name variable of a club
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * A setter that sets the name of the club if its under 30 characters
     * @param name
     */
    public void setName(String name) {
        if(Utilities.max30Chars(name)) {
            this.name = name;
        }
    }

    /**
     * Gets the headcoach variable from the club
     * @return
     */
    public String getHeadCoach() {
        return headCoach;
    }

    /**
     * Sets the headcoach variable if its under 30 characters
     * @param headCoach
     */
    public void setHeadCoach(String headCoach) {
        if(Utilities.max30Chars(headCoach)) {
            this.headCoach = headCoach;
        }
    }

    /**
     * Gets the members variable from the club
     * @return
     */
    public int getMembers() {
        return members;
    }

    /**
     * Sets the member variable if its a non negative value
     * @param members
     */
    public void setMembers(int members) {
        if(Utilities.validNonNegative(members)) {
            this.members = members;
        }
    }

    /**
     * Gets the player arraylist from the club class
     * @return
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * Sets the arraylist of players
     * @param players
     */
    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    @Override
    public String toString() {
        return "Club{" +
                "name='" + name  + '\n' +
                "headCoach='" + headCoach + '\n' +
                "members=" + members + '\n'+
                "players=" + players + '\n'+
                '}';
    }

    /**
     * Adds a player to the player arraylist of the club and returns true
     * @param player
     * @return
     */
    public boolean addPlayer(Player player){
    players.add (player);
    return true;
    }

    /**
     * deletes a player from the player arraylist of the club and returns the player that gets deleted
     * if an invalid index is passed through then null is returned.
     * @param index
     * @return
     */
    public Player deletePlayer(int index){
        if(Utilities.validIndex(index,players)){
            Player plr = players.get(index);
            players.remove(index);
            return plr;
        }
        else {
            return null;
        }
    }

    /**
     * Returns a string containing every player in a given club
     * @return
     */
    public String listOfPlayers(){
        String allPlayers = "";
        if(players.isEmpty()){
            return "There are no players registered in this club";
        }
        else {
            for (int i = 0; i < players.size(); i++) {
                allPlayers += i + "Player: " + players.get(i)  +'\n';
            }
            return allPlayers;
        }
    }

    /**
     * Checks if a player is in a club and returns true, returns false if the player is not in the club
     * @param player
     * @return
     */
    public boolean isPlayerInClub(Player player){
    boolean playerInClub = false;
    for(Player players : players){
        if (player.equals(players)){
            playerInClub = true;
     }
         }
    return playerInClub;
    }
}
