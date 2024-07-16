package model;

/**
 * The Guest class represents a guest.
 * This includes details such as guest name, and the reservations they made.
 */
public class Guest {
    private String name;

    /**
     * Constructor for the Guest
     * 
     * @param name name of the guest
     */
    public Guest(String name) {
        this.name = name;
    }

    /**
     * Gets the name of the guest.
     * 
     * @return name of the guest
     */
    public String getName() {
        return name;
    }
}

