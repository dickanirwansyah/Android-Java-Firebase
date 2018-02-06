package dicka.com.androideat.model;

/**
 * Created by java-spring on 02/02/18.
 */

public class Category {

    private String nama;
    private String image;

    public Category(){}

    public Category(String nama, String image){
        this.nama=nama;
        this.image=image;
    }

    public String getNama(){
        return nama;
    }

    public void setNama(String nama){
        this.nama = nama;
    }

    public String getImage(){
        return image;
    }

    public void setImage(String image){
        this.image=image;
    }
}
