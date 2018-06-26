package id.pahlevikun.easygroupmaker.model.database.usergroup;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {UserGroupTable.class}, version = 1, exportSchema = false)
public abstract class UserGroupDatabase extends RoomDatabase {
    public abstract UserGroupDaoAccess daoAccess();
}

