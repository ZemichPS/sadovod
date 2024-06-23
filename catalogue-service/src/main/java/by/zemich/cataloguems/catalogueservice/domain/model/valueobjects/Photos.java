package by.zemich.cataloguems.catalogueservice.domain.model.valueobjects;

import java.util.ArrayList;
import java.util.List;

public class Photos {

    private List<Photo> photoList;

    public Photos(List<Photo> photoList) {
        this.photoList = photoList;
    }

    public int getCount() {
        return this.photoList.size();
    }

    public List<Photo> getPhotoList() {
        return new ArrayList<>(this.photoList);
    }

    public boolean addPhoto(Photo photo) {
        return photoList.add(photo);
    }

    public boolean remove(Photo photo){
        return this.photoList.remove(photo);
    }


}
