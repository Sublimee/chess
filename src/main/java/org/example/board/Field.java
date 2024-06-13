package org.example.board;

import java.io.Serializable;
import java.util.Objects;

public class Field implements Serializable {
    private final File file;
    private final Integer rank;

    public Field(File file, Integer rank) {
        this.file = file;
        this.rank = rank;
    }

    public File getFile() {
        return file;
    }

    public Integer getRank() {
        return rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Field field = (Field) o;
        return file == field.file && Objects.equals(rank, field.rank);
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }
}