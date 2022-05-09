package fr.ul.miage.structurationDocuments.modele.tag;

public class Tag {

    private String name;
    private int total;
    private int reach;
    private WikiTag wiki;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getReach() {
        return reach;
    }

    public void setReach(int reach) {
        this.reach = reach;
    }

    public WikiTag getWiki() {
        return wiki;
    }

    public void setWiki(WikiTag wiki) {
        this.wiki = wiki;
    }

    @Override
    public String toString() {
        return String.format("name: %s\ntotal: %s\nreach: %s\nwiki: %s",name,total,reach,wiki.toString());
    }
}
