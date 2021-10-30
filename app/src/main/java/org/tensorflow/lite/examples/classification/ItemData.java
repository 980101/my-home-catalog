package org.tensorflow.lite.examples.classification;

public class ItemData {
    private String image;
    private String name;
    private String price;

    // 생성자
    public ItemData(){}

    public void setImage(String image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }
}
