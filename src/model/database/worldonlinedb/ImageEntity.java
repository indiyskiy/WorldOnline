package model.database.worldonlinedb;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 31.10.13
 * Time: 2:31
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "Image", schema = "", catalog = "worldonline")
@Entity
public class ImageEntity {
    @javax.persistence.Column(name = "ImageID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long imageID;

    public Long getImageID() {
        return imageID;
    }

    public void setImageID(Long imageID) {
        this.imageID = imageID;
    }

    @javax.persistence.Column(name = "ImageFilePath")
    @Basic
    private String imageFilePath;

    public String getImageFilePath() {
        return imageFilePath;
    }

    public void setImageFilePath(String ImageFilePath) {
        this.imageFilePath = ImageFilePath;
    }

    @javax.persistence.Column(name = "ImageMD5Hash")
    @Basic
    private String imageMD5Hash;

    public String getImageMD5Hash() {
        return imageMD5Hash;
    }

    public void setImageMD5Hash(String imageMD5Hash) {
        this.imageMD5Hash = imageMD5Hash;
    }

    @javax.persistence.Column(name = "ImageHeight")
    @Basic
    private Integer imageHeight;

    public Integer getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(Integer imageHeight) {
        this.imageHeight = imageHeight;
    }

    @javax.persistence.Column(name = "ImageWidth")
    @Basic
    private Integer imageWidth;

    public Integer getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(Integer imageWidth) {
        this.imageWidth = imageWidth;
    }

    @javax.persistence.Column(name = "ImageFileSize")
    @Basic
    private Long imageFileSize;

    public Long getImageFileSize() {
        return imageFileSize;
    }

    public void setImageFileSize(Long imageFileSize) {
        this.imageFileSize = imageFileSize;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ImageEntity that = (ImageEntity) o;

        if (imageID != null ? !imageID.equals(that.imageID) : that.imageID != null)
            return false;
        if (imageFilePath != null ? !imageFilePath.equals(that.imageFilePath) : that.imageFilePath != null)
            return false;
        if (imageMD5Hash != null ? !imageMD5Hash.equals(that.imageMD5Hash) : that.imageMD5Hash != null)
            return false;
        if (imageHeight != null ? !imageHeight.equals(that.imageHeight) : that.imageHeight != null)
            return false;
        if (imageWidth != null ? !imageWidth.equals(that.imageWidth) : that.imageWidth != null)
            return false;
        if (imageFileSize != null ? !imageFileSize.equals(that.imageFileSize) : that.imageFileSize != null)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = imageID != null ? imageID.hashCode() : 0;
        result = 31 * result + (imageFilePath != null ? imageFilePath.hashCode() : 0);
        result = 31 * result + (imageMD5Hash != null ? imageMD5Hash.hashCode() : 0);
        result = 31 * result + (imageHeight != null ? imageHeight.hashCode() : 0);
        result = 31 * result + (imageWidth != null ? imageWidth.hashCode() : 0);
        result = 31 * result + (imageFileSize != null ? imageFileSize.hashCode() : 0);
        return result;
    }

    public ImageEntity() {
    }

    public ImageEntity(String imageFilePath, Integer imageHeight, Integer imageWidth, Long imageFileSize, String imageMD5Hash) {
        this.imageFilePath = imageFilePath;
        this.imageHeight = imageHeight;
        this.imageWidth = imageWidth;
        this.imageFileSize = imageFileSize;
        this.imageMD5Hash = imageMD5Hash;
    }
}
