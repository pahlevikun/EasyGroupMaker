package id.pahlevikun.easygroupmaker.model.database.grouplist;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class GroupListTable {
    @PrimaryKey(autoGenerate = true)
    private int _id;
    @NonNull
    private String name;
    private String description;
    @NonNull
    private String team;
    private String createdAt;
    private String updatedAt;

    public GroupListTable(@NonNull String name, String description, @NonNull String team, String createdAt, String updatedAt) {
        this.name = name;
        this.description = description;
        this.team = team;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NonNull
    public String getTeam() {
        return team;
    }

    public void setTeam(@NonNull String team) {
        this.team = team;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
