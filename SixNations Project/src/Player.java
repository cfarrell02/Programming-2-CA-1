public class Player {
    /**
     * The Player class generates a player object which
     * makes up each club and national team
     *
     * @author Cian Farrell
     * @Version 1.0
     */
    private String name;
    private String email;
    private String phone;

    public Player(String name, String email, String phone) {
        if(Utilities.max30Chars(name)){
            this.name = name;
        }
        else {
            this.name = name.substring(0,30);
        }

        if(Utilities.validEmail(email)){
            this.email = email;
        }
        else {
            this.email = "invalid format email";
        }
        if(Utilities.onlyContainsNumbers(phone)){
            this.phone = phone;
        }
        else {
            this.phone = "unknown";
        }
    }

    /**
     * A getter that returns the name of the player
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of a player if the name is less than 30 characters long
     * @param name
     */
    public void setName(String name) {
        if(Utilities.max30Chars(name)){
            this.name = name;
        }
    }

    /**
     * Gets the email of a player
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the name of a player if the email contains an "@" and "." characters
     * @param email
     */
    public void setEmail(String email) {
        if(Utilities.validEmail(email)){
            this.email = email;
        }
    }

    /**
     * Gets the phone number of a player
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the phone number of a player if the string of numbers only contains numbers
     * @param phone
     */
    public void setPhone(String phone) {
        if(Utilities.onlyContainsNumbers(phone)){
            this.phone = phone;
        }
    }

    /**
     * Checks if this player class is equal to the player class
     * passed in through the parameters,
     * @param otherPlayer
     * @return returns boolean true if players match, boolean false if
     * the players do not match.
     */
    public boolean equals(Player otherPlayer){
        return  (this.name.equals(otherPlayer.getName()) &&
                this.email.equals(otherPlayer.getEmail())  &&
                this.phone.equals(otherPlayer.getPhone()) );
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name +  '\n' +
                "email='" + email + '\n' +
                "phone='" + phone + '\n' +
                '}';
    }
}
