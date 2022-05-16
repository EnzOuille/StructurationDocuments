package fr.ul.miage.structurationDocuments.modele.tag;

/**
 * The type Tag.
 */
public class Tag {

    private String name;
    private int total;
    private int reach;
    private WikiTag wiki;

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets total.
     *
     * @return the total
     */
    public int getTotal() {
        return total;
    }

    /**
     * Sets total.
     *
     * @param total the total
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * Gets reach.
     *
     * @return the reach
     */
    public int getReach() {
        return reach;
    }

    /**
     * Sets reach.
     *
     * @param reach the reach
     */
    public void setReach(int reach) {
        this.reach = reach;
    }

    /**
     * Gets wiki.
     *
     * @return the wiki
     */
    public WikiTag getWiki() {
        return wiki;
    }

    /**
     * Sets wiki.
     *
     * @param wiki the wiki
     */
    public void setWiki(WikiTag wiki) {
        this.wiki = wiki;
    }

    @Override
    public String toString() {
        return String.format("%s\nThe number of users that have used this tag: %s\n" +
                "The total number of times this tag has been used: %s\n" +
                "Description : %s\n",name,reach,total,wiki.getContent());
    }
}
