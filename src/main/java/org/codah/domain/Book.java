package org.codah.domain;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

import org.infinispan.protostream.annotations.ProtoFactory;
import org.infinispan.protostream.annotations.ProtoField;

public class Book {
    private final String title;
    private final String description;
    private final int publicationYear;
    private final Set<Author> authors;
    private final BigDecimal price;

    @ProtoFactory
    public Book(String title, String description, int publicationYear, Set<Author> authors, BigDecimal price) {
        this.title = Objects.requireNonNull(title);
        this.description = Objects.requireNonNull(description);
        this.publicationYear = publicationYear;
        this.authors = Objects.requireNonNull(authors);
        this.price = price;
    }
    //--- getters, hashCode, toString -----------------------------------------

    @ProtoField(number = 1)
    public String getTitle() {
        return title;
    }

    @ProtoField(number = 2)
    public String getDescription() {
        return description;
    }

    @ProtoField(number = 3, defaultValue = "-1")
    public int getPublicationYear() {
        return publicationYear;
    }

    @ProtoField(number = 4)
    public Set<Author> getAuthors() {
        return authors;
    }

    @ProtoField(number = 5)
    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Book [authors=" + authors + ", description=" + description + ", price=" + price + ", publicationYear="
                + publicationYear + ", title=" + title + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((authors == null) ? 0 : authors.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((price == null) ? 0 : price.hashCode());
        result = prime * result + publicationYear;
        result = prime * result + ((title == null) ? 0 : title.hashCode());
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
        Book other = (Book) obj;
        if (authors == null) {
            if (other.authors != null)
                return false;
        } else if (!authors.equals(other.authors))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (price == null) {
            if (other.price != null)
                return false;
        } else if (!price.equals(other.price))
            return false;
        if (publicationYear != other.publicationYear)
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        return true;
    }

    

}
