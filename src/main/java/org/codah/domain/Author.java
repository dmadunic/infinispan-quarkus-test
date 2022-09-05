package org.codah.domain;

import java.util.Objects;

import org.infinispan.protostream.annotations.ProtoFactory;
import org.infinispan.protostream.annotations.ProtoField;

public class Author {
    private final String name;
    private final String surname;
 
    @ProtoFactory
    public Author(String name, String surname) {
       this.name = Objects.requireNonNull(name);
       this.surname = Objects.requireNonNull(surname);
    }

    @ProtoField(number = 1)
    public String getName() {
        return name;
    }

    @ProtoField(number = 2)
    public String getSurname() {
        return surname;
    }

    //--- getters, hashCode, toString -----------------------------------------

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((surname == null) ? 0 : surname.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Author other = (Author) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (surname == null) {
            if (other.surname != null)
                return false;
        } else if (!surname.equals(other.surname))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Author [name=" + name + ", surname=" + surname + "]";
    }
    
 }