package id.pahlevikun.easygroupmaker.model.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import id.pahlevikun.easygroupmaker.model.database.grouplist.GroupListDaoAccess;
import id.pahlevikun.easygroupmaker.model.database.grouplist.GroupListTable;
import id.pahlevikun.easygroupmaker.model.database.usergroup.UserGroupDaoAccess;
import id.pahlevikun.easygroupmaker.model.database.usergroup.UserGroupTable;

@Database(entities = {GroupListTable.class, UserGroupTable.class}, version = 1, exportSchema = false)
public abstract class DatabaseImplementation extends RoomDatabase {
    public abstract GroupListDaoAccess groupListDaoAccess();

    public abstract UserGroupDaoAccess userGroupDaoAccess();
}

