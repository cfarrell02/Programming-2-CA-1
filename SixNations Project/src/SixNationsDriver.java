import java.util.ArrayList;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;


public class SixNationsDriver {
    /**
     * The driver class contains an array of every country class
     * It also contains the menu system and a series of methods
     * that allow the user to interact with the six nations programme.
     *
     */

    private ArrayList<Country> countries;

    public static void main(String[] args) {
        new SixNationsDriver();
    }

    /**
     * A getter that returns the countries array
     * @return countries in the array
     */
    public ArrayList<Country> getCountries() {
        return countries;
    }

    /**
     * A setter that sets the countries array to the given parameters
     * @param countries
     */
    public void setCountries(ArrayList<Country> countries) {
        this.countries = countries;
    }
    private int mainMenu(){
        System.out.println("Six Nations Rugby Menu");
        System.out.println("---------");
        System.out.println("  1) Add a country to Six Nations");
        System.out.println("  2) Remove a country from the Six Nations");
        System.out.println("  3) Update a Six Nations country's information (manager only)");
        System.out.println("  --------------------");
        System.out.println("  Country Menu");
        System.out.println("  4) Add a player to a country (must already be a member of a club in that country");
        System.out.println("  5) Delete a player from a country");
        System.out.println("  6) Update a player from a country (phone number only)");
        System.out.println("  --------------------");
        System.out.println("  Club Menu");
        System.out.println("  7) Add a New Club to a country");
        System.out.println("  8) Delete a Club from a country");
        System.out.println("  9) Update a Club's Information (head coach only)");
        System.out.println("  --------------------");
        System.out.println("  Player Menu");
        System.out.println("  10) Add a player to a given Club");
        System.out.println("  11) Delete a player from a given Club");
        System.out.println("  12) Update the information on a club player (phone number only)");
        System.out.println("  --------------------");
        System.out.println("  Report Menu");
        System.out.println("  13) List all the Six Nations Countries");
        System.out.println("  14) List all clubs of a given country");
        System.out.println("  15) List all players of a given country");
        System.out.println("  16) List all players of a given club");
        System.out.println("  17) List all players in the system (club players)");
        System.out.println("  18) Calculate and print the club with the most members");
        System.out.println("  19) Calculate and print the country with the most national players");
        System.out.println("  --------------------");
        System.out.println("  20) Save to XML");
        System.out.println("  21) Load from XML");
        System.out.println("  --------------------");
        System.out.println("  0) Exit");
        return ScannerInput.readNextInt("==>>");
    }

    public SixNationsDriver() {
        countries = new ArrayList<>();
        runMenu();
    }

    /**
     * Lists out every country stored in the countries arraylist into a string
     * @return a string containing every country in the arraylist
     */
    public String listCountries(){
        if (!countries.isEmpty()){
            String str = "";
        for(int i = 0; i<countries.size();i++){
            str +=  i +( countries.get(i) + "\n");
        }
        return str;
        }
        else{
            return "No countries are in the system";
        }
    }

    /**
     * Allows a user to select a country and then lists every club in that country
     * @return string with every club in a country.
     */
    public String listClubByCountry(){
        System.out.println(listCountries());
        int index = ScannerInput.readNextInt("Pick a country ==>");

        if (!countries.get(index).getClubs().isEmpty()){
            String str = "";
            for(int i = 0; i<countries.get(index).getClubs().size();i++){
                str +=  i +(countries.get(index).getClubs().get(i) + "\n");
            }
            return str;
        }
        else{
            return "No clubs lol";
        }
    }

    /**
     * Allows a user to select a country and lists out every
     * national player for that country
     * @return a string that contains every national player in a country
     */
    public String listPlayersOfCountry(){
        System.out.println(listCountries());
        int index = ScannerInput.readNextInt("Pick a country ==>");

        if (!countries.get(index).getNationalPlayers().isEmpty()){
            String str = "";
            for(int i = 0; i<countries.get(index).getNationalPlayers().size();i++){
                str += i + countries.get(index).getNationalPlayers().get(i).getName() + "\n";
            }
            return str;
        }
        else{
            return "No national players in this country";
        }
    }

    /**
     * Allows a user to pick a country and then allows them to pick a club from
     * that country then lists out every player in that club
     * @return string with every player in a specific club
     */
    public String listPlayersByClub(){
        System.out.println(listCountries());
        int index1 = ScannerInput.readNextInt("Pick a country ==>");
        System.out.println(countries.get(index1).listOfClubs());
        int index2 = ScannerInput.readNextInt("Pick a club ==>");
        if (!countries.get(index1).getClubs().isEmpty()){
            String str = "";
            for(int i = 0; i<countries.get(index1).getClubs().size();i++){
                str += i + (countries.get(index1).getClubs().get(index2) + "\n");
            }
            return str;
        }
        else{
            return "No Players in this club";
        }
    }

    /**
     * List all players in the system
     * @return string with all players in the system
     */
    public String listAllClubPlayers(){
        if (!countries.isEmpty()){
            String str = "";
            for(int i = 0; i<countries.size();i++){
            for(int j = 0; j<countries.get(i).getClubs().size();j++){
                str += i +"."+ j +":"+ "Player: " +countries.get(i).getClubs().get(j).listOfPlayers() + "\n";
            }
            }
            return str;
        }
        else{
            return "No players in the system lol";
        }
    }

    /**
     * Goes through all the clubs in the system and determines which club is the biggest
     * It returns the club that is the largest
     * @return largest club Club object
     */
    public Club largestClub(){
        if (!countries.isEmpty()){
            Club largestClb = countries.get(0).largestClubInCountry();
            for (Country countries: countries){
                for(Club club: countries.getClubs()){
                if (club.getMembers() > largestClb.getMembers() ){
                    largestClb = club;}
            }}
            return largestClb;
        }
        else
            return null;
    }

    /**
     * goes through all the countries and determines which one has the most national players
     *
     * @return country with most national Players.
     */
    public Country countryWithMostPlayers(){
        if (!countries.isEmpty()){
            Country country = countries.get(0);

                for(Country countries :countries){
                if (countries.getNationalPlayers().size() > country.getNationalPlayers().size() )
                    country = countries;
            }
            return country;
        }
        else
            return null;
    }

    public SixNationsDriver(int x) {
        countries = new ArrayList<>();
    }

    /**
     * Adds a country to the country array
     */
    private void addCountry() {
        String countryName = ScannerInput.readNextLine("Enter the Country Name:  ");
        String managerName = ScannerInput.readNextLine("Enter the Manager's Name:  ");
        int maxClubs = ScannerInput.readNextInt("Enter the max number of clubs: ");

        countries.add(new Country(countryName, managerName, maxClubs));
    }

    /**
     * Asks the user what country they want to delete, and removes it from the array
     */
    private void deleteCountry() {
        //list the countries
        System.out.println(listCountries());

        if (countries.size() > 0) {
            //only ask the user to choose the product to delete if products exist
            //Ask the user to enter the index of the product they wish to delete
            int index = ScannerInput.readNextInt("Enter the index of the country to delete ==> ");

            if (Utilities.validIndex(index,countries)) {
                //if the index is valid, delete the product at the given index
                countries.remove(index);
                System.out.println("Country deleted.");
            } else {
                System.out.println("There is no country for this index number");
            }
        }
    }

    /**
     * Allows a user to pick a country and update its manager name.
     */
    private void updateCountryManager(){
        System.out.println(listCountries());

        int index = ScannerInput.readNextInt("Enter the index of the country to be updated ==> ");
        if(Utilities.validIndex(index,countries)){
        System.out.println("Enter in new managers name: ");
        countries.get(index).setManager(ScannerInput.readNextLine("Enter the Manager's Name:  "));
        System.out.println("Manager updated");}
        else{
            System.out.println("This is not a country");
        }
  }

    /**
     * Allows the user to pick a country and add a player to that country,
     * scans every club for a player with a matching name and adds that to
     * the countries national team.
     */
    private void addPlayerToCountry() {
        System.out.println(listCountries());
        int index = ScannerInput.readNextInt("Pick a country ==>");
        String name = ScannerInput.readNextLine("Enter in new players name: ");
        //String email = ScannerInput.readNextLine("Enter in new email: ");
        //String phone = ScannerInput.readNextLine("Enter in new phone number: ");

        for(Club club: countries.get(index).getClubs()){
            countries.get(index).addNationalPlayer(Utilities.validPlayer(name,club));
                }
            }




    /*
        System.out.println(listCountries());
        int index1 = ScannerInput.readNextInt("Pick a country ==>");
        if(Utilities.validIndex(index1,countries)){
        System.out.println(countries.get(index1).listOfClubs());
        int index2 = ScannerInput.readNextInt("Pick a Club ==>");
        if(Utilities.validIndex(index2,countries.get(index1).getClubs())){
        System.out.println(countries.get(index1).getClubs().get(index2).listOfPlayers());
        int index3 = ScannerInput.readNextInt("Pick a Player to add ==>");
        if(Utilities.validIndex(index3,countries.get(index1).getClubs().get(index2).getPlayers())){
            if (countries.get(index1).addNationalPlayer(countries.get(index1).getClubs().get(index2).getPlayers().get(index3))) {
                System.out.println("Player added to national team");
            } else {
                System.out.println("Unable to add player to national team");
            }
        }else{
            System.out.println("Invalid Player");
        }
        }else{
            System.out.println("Invalid Club");
        }
        }else{
            System.out.println("Invalid Country");
        }*/



       /* System.out.println("Enter in new players name: ");
        String name = input.nextLine();
        System.out.println("Enter in new players email: ");
        String email = input.nextLine();
        System.out.println("Enter in new players number: ");
        String phone = input.nextLine();*/

    /**
     * Allows the user to pick a country and then lists all national players in that
     * country and the user can pick which player they wanna delete
     */

    private void deletePlayerFromCountry(){
        System.out.println(listCountries());
        int index1 = ScannerInput.readNextInt("Pick a country ==>");
        if(Utilities.validIndex(index1,countries)){
        System.out.println(countries.get(index1).listOfNationalPlayers());
        int index2 = ScannerInput.readNextInt("Pick a player to be deleted ==>");
        if(Utilities.validIndex(index2,countries.get(index1).getNationalPlayers())){
        countries.get(index1).deleteNationalPlayer(index2);
    }   else{
            System.out.println("Invalid Player");
        }
        }else{
            System.out.println("Invalid Country");
        }
}

    /**
     * Allows the user to pick the country and then which player
     * they wanna update, then allows them to enter in a new
     * country.
     */

    private void updatePlayerByCountry() {
        System.out.println(listCountries());
            int index1 = ScannerInput.readNextInt("Pick a country ==>");
            if (Utilities.validIndex(index1, countries)) {
                System.out.println(countries.get(index1).listOfNationalPlayers());
                int index2 = ScannerInput.readNextInt("Pick a player to be updated ==>");
                if (Utilities.validIndex(index2, countries.get(index1).getNationalPlayers())) {
                    String phone = ScannerInput.readNextLine("Enter in new players number: ");
                    countries.get(index1).getNationalPlayers().get(index2).setPhone(phone);

                } else {
                    System.out.println("Invalid Player");
                }
            } else {
                System.out.println("Invalid Country");
            }
        }

    /**
     * Allows a user to pick a country to add a new club to, then enter in the details
     * to create a new club in that countries arraylist.
     */
    private void addNewClub() {
        System.out.println(listCountries());
        int index = ScannerInput.readNextInt("Pick a country ==>");
        String name = ScannerInput.readNextLine("Enter in a club name: ");
        String coach = ScannerInput.readNextLine("Enter in a head coach name: ");
        int members = ScannerInput.readNextInt("Enter in the amount of members of this club: ");
        countries.get(index).addClub(new Club(name, coach, members));
    }

    /**
     *
     */
    private void deleteClub(){
        System.out.println(listCountries());
        int index1 = ScannerInput.readNextInt("Pick a country ==>");
        if(Utilities.validIndex(index1,countries)){
            System.out.println(countries.get(index1).listOfClubs());
            int index2 = ScannerInput.readNextInt("Pick a club to be deleted ==>");
            if(Utilities.validIndex(index2,countries.get(index1).getClubs())){
                countries.get(index1).deleteClub(index2);
            }   else{
                System.out.println("Invalid Club");
            }
        }else{
            System.out.println("Invalid Country");
        }
    }
    private void updateClub() {


        System.out.println(listCountries());
        int index1 = ScannerInput.readNextInt("Pick a country ==>");
        if(Utilities.validIndex(index1,countries)){
            System.out.println(countries.get(index1).listOfClubs());
            int index2 = ScannerInput.readNextInt("Pick a club to be updated ==>");
            if(Utilities.validIndex(index2,countries.get(index1).getClubs())){
                String coach = ScannerInput.readNextLine("Enter in a head coach name: ");
                countries.get(index1).setManager(coach);
            }   else{
                System.out.println("Invalid Club");
            }
        }else{
            System.out.println("Invalid Country");
        }
    }
    private void addPlayerToClub() {
        System.out.println(listCountries());
        int index1 = ScannerInput.readNextInt("Pick a country ==>");
        if(Utilities.validIndex(index1,countries)){
            System.out.println(countries.get(index1).listOfClubs());
            int index2 = ScannerInput.readNextInt("Pick a club for a player to be added ==>");
            if(Utilities.validIndex(index2,countries.get(index1).getClubs())){
                String name = ScannerInput.readNextLine("Enter in a new player's name name: ");
                String email = ScannerInput.readNextLine("Enter in a new players email: ");
                String phone = ScannerInput.readNextLine("Enter in new players number: ");
                countries.get(index1).getClubs().get(index2).addPlayer(new Player(name, email, phone));
            }   else{
                System.out.println("Invalid Club");
            }
        }else{
            System.out.println("Invalid Country");
        }
    }
    private void deletePlayerFromClub(){
        System.out.println(listCountries());
        int index1 = ScannerInput.readNextInt("Pick a country ==>");
        if(Utilities.validIndex(index1,countries)){
            System.out.println(countries.get(index1).listOfNationalPlayers());
            int index2 = ScannerInput.readNextInt("Pick a club ==>");
            if(Utilities.validIndex(index2,countries.get(index1).getClubs())) {
                int index3 = ScannerInput.readNextInt("Pick a player to be deleted ==>");
                if (Utilities.validIndex(index3, countries.get(index1).getClubs().get(index2).getPlayers())) {
                    countries.get(index1).getClubs().get(index2).deletePlayer(index3);

                } else {
                    System.out.println("Invalid Player");
                }
            }else
                {
                System.out.println("Invalid Club");
            }
        }else{
            System.out.println("Invalid Country");
        }
    }
    private void updatePlayerFromClub(){
        System.out.println(listCountries());
        int index1 = ScannerInput.readNextInt("Pick a country ==>");
        if(Utilities.validIndex(index1,countries)){
            System.out.println(countries.get(index1).listOfNationalPlayers());
            int index2 = ScannerInput.readNextInt("Pick a club ==>");
            if(Utilities.validIndex(index2,countries.get(index1).getClubs())) {
                int index3 = ScannerInput.readNextInt("Pick a player to be updated ==>");
                if (Utilities.validIndex(index3, countries.get(index1).getClubs().get(index2).getPlayers())) {
                    String phone = ScannerInput.readNextLine("Enter in new players number: ");
                    countries.get(index1).getClubs().get(index2).getPlayers().get(index3).setPhone(phone);

                } else {
                    System.out.println("Invalid Player");
                }
            }else
            {
                System.out.println("Invalid Club");
            }
        }else{
            System.out.println("Invalid Country");
        }
    }
    @SuppressWarnings("unchecked")
    public void load() throws Exception
    {
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("countries.xml"));
        countries = (ArrayList<Country>) is.readObject();
        is.close();
    }

    public void save() throws Exception
    {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("countries.xml"));
        out.writeObject(countries);
        out.close();
    }

    private void runMenu(){
        int option = mainMenu();
        while (option != 0){
            switch (option) {
                case 1:
                    addCountry();
                    break;
                case 2:
                    deleteCountry();
                    break;
                case 3:
                    updateCountryManager();
                    break;
                case 4:
                    addPlayerToCountry();
                    break;
                case 5:
                    deletePlayerFromCountry();
                    break;
                case 6:
                    updatePlayerByCountry();
                    break;
                case 7:
                    addNewClub();
                    break;
                case 8:
                    deleteClub();
                    break;
                case 9:
                    updateClub();
                    break;
                case 10:
                    addPlayerToClub();
                    break;
                case 11:
                    deletePlayerFromClub();
                    break;
                case 12:
                    updatePlayerFromClub();
                    break;
                case 13:
                    System.out.println(listCountries());
                    break;
                case 14:
                    System.out.println(listClubByCountry());
                    break;
                case 15:
                    System.out.println(listPlayersOfCountry());
                    break;
                case 16:
                    System.out.println(listPlayersByClub());
                    break;
                case 17:
                    System.out.println(listAllClubPlayers());
                    break;
                case 18:
                    System.out.println(largestClub());
                    break;
                case 19:
                    System.out.println(countryWithMostPlayers());
                    break;
                case 20:
                    try{
                        save();
                    }
                    catch (Exception e){
                        System.err.println("Error writing to file: "+ e);
                    }
                    break;
                case 21:
                    try{
                        load();
                    }
                    catch (Exception e){
                        System.err.println("Error loading from file: "+ e);
                    }
                    break;
                default:
                    System.out.println("Invalid option entered: " + option);
                    break;

            }
            ScannerInput.readNextLine("\nPress any key to continue...");
                option = mainMenu();

            }
        }
    }

