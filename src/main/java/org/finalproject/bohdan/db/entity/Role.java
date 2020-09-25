package org.finalproject.bohdan.db.entity;

public class Role {

    private int id;

    private String name;

    public static Role createRole(String name){
        Role role = new Role();
        role.setName(name);
        return role;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
