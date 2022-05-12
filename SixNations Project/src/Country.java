import java.util.ArrayList;

public class Country {
    /**
     * The Country class consists of an Arraylist of each club and of
     * each national player for that country
     * @author Cian Farrell
     * @Version 1.0
     */
    private String name;
    private String manager;
    private int maxNumOfClubs;
    private ArrayList<Player> nationalPlayers;
    private ArrayList<Club> clubs;

    public Country(String name, String manager, int maxNumOfClubs) {
        if(Utilities.max30Chars(name)){
        this.name = name;}
        else{
            this.name = name.substring(0,30);
        }
        if(Utilities.max30Chars(manager)){
        this.manager = manager;}
        else{
            this.manager = manager.substring(0,30);
        }
        if(Utilities.validPositive(maxNumOfClubs)){
        this.maxNumOfClubs = maxNumOfClubs;}
        else{
            this.maxNumOfClubs = 10;
        }
        this.nationalPlayers = new ArrayList<>();
        this.clubs = new ArrayList<>();
    }

    /**
     * Gets the name of the given country
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of a country if it is less than 30 characters
     * @param name
     */
    public void setName(String name) {
        if(Utilities.max30Chars(name)){
            this.name = name;}
    }

    /**
     * Gets the name of the manager of a given country
     * @return manager
     */
    public String getManager() {
        return manager;
    }

    /**
     * Sets the name of the manager if its less than 30 characters
     * @param manager
     */
    public void setManager(String manager) {
        if(Utilities.max30Chars(manager)){
            this.manager = manager;}
    }

    /**
     * Gets the max number of clubs in a given country
     * @return maxNumOfClubs
     */
    public int getMaxNumOfClubs() {
        return maxNumOfClubs;
    }

    /**
     * Sets the maximum number of clubs in a given country if the max number of clubs given
     * is a positive number.
     * @param maxNumOfClubs
     */
    public void setMaxNumOfClubs(int maxNumOfClubs) {
        if(Utilities.validPositive(maxNumOfClubs)){
            this.maxNumOfClubs = maxNumOfClubs;}
    }

    /**
     * Gets the national players arraylist of a given country
     * @return nationalPlayers
     */
    public ArrayList<Player> getNationalPlayers() {
        return nationalPlayers;
    }

    /**
     * Sets the national players arraylist of a given country if each player is eligible
     * @param nationalPlayers
     * @return is national player is successful
     */
    public boolean setNationalPlayers(ArrayList<Player> nationalPlayers) {
        boolean Player = false;
        for(Player player: nationalPlayers){
        if(isEligible(player)){
        this.nationalPlayers = nationalPlayers;
        Player = true;
        }
        else{
            Player = false;
            break;
        }
    }
        return Player;
    }

    /**
     * Gets the arraylist of clubs from a given country
     * @return clubs
     */
    public ArrayList<Club> getClubs() {
        return clubs;
    }

    /**
     * Sets the club arraylist in a given country
     * @param clubs
     */
    public void setClubs(ArrayList<Club> clubs) {
        this.clubs = clubs;
    }

    /**
     * gets the national player at a specific index in a given country
     * @param index
     * @return Player at index
     */
    public Player getNationalPlayer(int index){
        return nationalPlayers.get(index);
    }

    /**
     * Cycles through each club in a country and checks if the player is in that club,
     * if so then the player is eligible and true is returned otherwise false is returned.
     * @param player
     * @return boolean if the player is eligible
     */
    public boolean isEligible(Player player){
        boolean playerEligible = false;
        for(Club clubs: clubs){
            if (clubs.isPlayerInClub(player)){
                playerEligible = true;
            }
        }
        return playerEligible;
    }

    /**
     * Gets the number of clubs in a given country and returns it.
     * @return size of clubs of a country
     */
    public int numberOfClubs(){
        return clubs.size();
    }

    /**
     * Adds a national player to the national player array in a given country
     * if the player is eligible. Returns true if successful and false if not.
     * @param player
     * @return true if successful, false if not
     */
    public boolean addNationalPlayer(Player player){
    if(isEligible(player)){
        nationalPlayers.add(player);
        return true;
    }
    else {
        return false;
    }

    }

    /**
     * Removes the national player at the index given, returns true if
     * successful and false if not
     * @param index
     * @return boolean if deletion is successful
     */
    public boolean deleteNationalPlayer(int index) {
        if (Utilities.validIndex(index,nationalPlayers)) {
            nationalPlayers.remove(index);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns a string with a list of all national players in a given country
     * @return string containing all players
     */
    public String listOfNationalPlayers(){
        String allPlayers = "";
        if(nationalPlayers.isEmpty()){
            return "There are no players registered for the country";
        }
        else{
            for (int i = 0; i < nationalPlayers.size(); i++) {
                allPlayers += i + "Name: " + nationalPlayers.get(i).getName() +'\n';
            }
            return allPlayers;
        }
    }

    /**
     * Adds a club to a given country if the number of clubs is less than the max number of clubs
     * returns true if successful and false if not.
     * @param club
     * @return true if clubs are added, false if not
     */
    public boolean addClub(Club club){
        if(numberOfClubs()<maxNumOfClubs){
            clubs.add(club);
            return true;}
        else{
            return false;
        }
    }

    /**
     * Deletes a club from a given country at the index provided, returns the club that
     * was deleted if successful, returns null if not.
     * @param index
     * @return deleted club if successful, null if not
     */
    public Club deleteClub(int index){
        if(Utilities.validIndex(index,clubs)){
            Club clb = clubs.get(index);
            clubs.remove(index);
            return clb;
        }
        else {
            return null;
        }
    }

    /**
     * Cycles through each club in a country and checks if the current one is bigger than the last one, updates a variable if
     * so. Returns the variable containing the largest club in a country. If there are no clubs
     * returns null.
     * @return largest club, null if empty
     */
    public Club largestClubInCountry()
    {
        if (!clubs.isEmpty()){
            Club largestClub = clubs.get(0);
            for (Club clubs : clubs){
                if (clubs.getMembers() > largestClub.getMembers() )
                    largestClub = clubs;
            }
            return largestClub;
        }
        else
            return null;
    }

    /**
     * Returns a string containing every club in a given country
     * @return string with list of clubs
     */
    public String listOfClubs(){
        if(clubs.isEmpty()){
            return "There are no clubs registered for the country";
        }
        else {
            String allClubs = "";
            for (int i=0; i < clubs.size();i++) {
                allClubs +=   i + " Club: " +clubs.get(i).getName() +"\n";
            }
            return allClubs;
        }
    }

    /**
     * Creates a list of national players and the club that they're in and returns it in a string.
     * Returns "There are no players registered for the national team" if there are no players in
     * the arraylist.
     * @return string with list of clubs and players
     */
    public String listOfNationalPlayersByClub(){
        String str="";
        for(Player player: nationalPlayers) {
            for (Club club: clubs) {
                if (club.isPlayerInClub(player)) {
                    str += "Club: "+club.getName()+" Player: " + player.getName() + "\n";
                }
            }
        }
        if(str!=""){
            return str;
        }
        else{
            return "There are no players registered for the national team";
        }
    }

    /**
     * If the club is in the given country, cycles through the players of the given club
     * and counts each player in the club if they are a national player for that country
     * @param club
     * @return int with number of national players in a club
     */
    public int noOfNationalPlayersByClub(Club club){

        int counter=0;
        if(isClubInCountry(club)){
     for(int i = 0; i<nationalPlayers.size();i++){
         if(club.isPlayerInClub(nationalPlayers.get(i))){
             counter ++;
     }}
     }
     if(counter>0){
     return counter;
     }
     else{
         return -1;
     }

    }

    /**
     * A helper method that returns true or false if a specified club
     * is in the given country.
     * @param clb
     * @return true if a club is in a country, false otherwise
     */
    public boolean isClubInCountry(Club clb) {
        boolean ans = false;
        for (Club club : clubs) {
            if (club.equals(clb)) {
                ans = true;
            }
        }
        return ans;
    }


    @Override
    public String toString() {
        String np = "There are no clubs in this country";
        String cl = "There are no clubs in this country";
        if(!nationalPlayers.isEmpty()){
            np = "\n" + listOfNationalPlayers();
        }
        if(!clubs.isEmpty()){
            cl = "\n" + listOfClubs();
        }
        return "Country{" + '\n' +
                "name= " + name + '\n' +
                "manager= " + manager + '\n' +
                "maxNumOfClubs= " + maxNumOfClubs + '\n'+
                "nationalPlayers= " + np + '\n'+
                "clubs= " + cl + '\n' +
                '}';
    }

    public boolean equals(Country otherCountry){
        return this.name.equals(otherCountry.getName());
   }

}

