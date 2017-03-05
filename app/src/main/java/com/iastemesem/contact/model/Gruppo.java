package com.iastemesem.contact.model;

/**
 * Created by Gianfranco on 03/03/2017.
 */

public class Gruppo {

    private int idGroup;
    private String groupName;

    //COSTRUCTOR
    public Gruppo(String groupName) {
        this.groupName = groupName;
    }


    //GETTER E SETTER
    public int getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(int idGroup) {
        this.idGroup = idGroup;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
