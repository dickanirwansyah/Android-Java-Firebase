package dicka.com.androideat.model;

/**
 * Created by java-spring on 06/02/18.
 */

public class Food {

    private String nama;
    private String description;
    private String image;
    private String price;
    private String discount;
    private String categoryid;

    public Food(){}

    public Food(String nama, String description, String image, String price, String discount, String categoryid){
        this.nama = nama;
        this.description = description;
        this.image = image;
        this.price = price;
        this.discount = discount;
        this.categoryid = categoryid;
    }

    public String getNama(){
        return nama;
    }

    public void setNama(String nama){
        this.nama = nama;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getImage(){
        return image;
    }

    public void setImage(String image){
        this.image = image;
    }

    public String getPrice(){
        return price;
    }

    public void setPrice(String price){
        this.price=price;
    }

    public String getDiscount(){
        return discount;
    }

    public void setDiscount(String discount){
        this.discount = discount;
    }

    public String getCategoryid(){
        return categoryid;
    }

    public void setCategoryid(String categoryid){
        this.categoryid = categoryid;
    }
}
