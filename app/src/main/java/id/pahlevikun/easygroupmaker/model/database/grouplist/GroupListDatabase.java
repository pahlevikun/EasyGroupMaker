package id.pahlevikun.easygroupmaker.model.database.grouplist;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import id.pahlevikun.easygroupmaker.model.database.usergroup.UserGroupDaoAccess;

@Database(entities = {GroupListTable.class}, version = 1, exportSchema = false)
public abstract class GroupListDatabase extends RoomDatabase {
    public abstract UserGroupDaoAccess daoAccess();
}

