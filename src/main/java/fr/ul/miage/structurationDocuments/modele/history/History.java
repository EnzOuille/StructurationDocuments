package fr.ul.miage.structurationDocuments.modele.history;

/**
 * The type History.
 */
public class History {

    private String query;

    private String query_date;

    private String type;

    /**
     * Gets query.
     *
     * @return the query
     */
    public String getQuery() {
        return query;
    }

    /**
     * Sets query.
     *
     * @param query the query
     */
    public void setQuery(String query) {
        this.query = query;
    }

    /**
     * Gets query date.
     *
     * @return the query date
     */
    public String getQuery_date() {
        return query_date;
    }

    /**
     * Sets query date.
     *
     * @param query_date the query date
     */
    public void setQuery_date(String query_date) {
        this.query_date = query_date;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "History{" +
                "query='" + query + '\'' +
                ", query_date=" + query_date +
                ", type='" + type + '\'' +
                '}';
    }
}
