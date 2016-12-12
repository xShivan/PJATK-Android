package net.xshivan.excercise3.backend;

public class ProductBean {
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private Boolean isPurchased;

    public Boolean getIsPurchased() {
        return isPurchased;
    }

    public void setIsPurchased(Boolean purchased) {
        isPurchased = purchased;
    }
}
