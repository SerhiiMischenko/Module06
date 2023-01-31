package org.example;

public class Client {

    private final long id;
    private final String name;

    public Client(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", name='" + name;
    }
}
