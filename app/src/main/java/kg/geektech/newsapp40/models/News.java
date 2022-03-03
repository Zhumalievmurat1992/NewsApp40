package kg.geektech.newsapp40.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "news")
public class News implements Serializable {

    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "title")
    String title;
    @ColumnInfo(name = "created_at")
    long createdAt;
    @ColumnInfo(name = "description")
    public String description;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public News(String title, long createdAt,String description) {
        this.title = title;
        this.createdAt = createdAt;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }
}
