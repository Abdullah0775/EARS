package com.algoma.ears;
import com.algoma.ears.util.*;
public class Jobs implements DataTransferObject{
    private long id;
    private String title;
    private String description;
    
    @Override
    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}