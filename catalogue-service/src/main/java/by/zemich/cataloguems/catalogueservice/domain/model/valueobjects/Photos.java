package by.zemich.cataloguems.catalogueservice.domain.model.valueobjects;

import java.util.List;

public class Photos {
    private List<Photo> photoList;

    public Photos(List<Photo> photoList) {
        this.photoList = photoList;
    }

    public int getCount(){
        return this.photoList.size();
    }

    public List<Photo> getPhotoList(){
        return this.photoList;
    }

}
