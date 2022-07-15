package com.interview.project.beans;

import java.util.Objects;

public class Item {
    private String productId;
    private String description;
    private Dimension dimension;
    private Item dependsOn;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public Item getDependsOn() {
        return dependsOn;
    }

    public void setDependsOn(Item dependsOn) {
        this.dependsOn = dependsOn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return productId.equals(item.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }
}
