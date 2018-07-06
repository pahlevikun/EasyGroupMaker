package id.pahlevikun.easygroupmaker.model.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import id.pahlevikun.easygroupmaker.model.database.grouplist.RandomGroupListDaoAccess;
import id.pahlevikun.easygroupmaker.model.database.grouplist.RandomGroupListTable;
import id.pahlevikun.easygroupmaker.model.database.usergroup.UserGroupDaoAccess;
import id.pahlevikun.easygroupmaker.model.database.usergroup.UserGroupTable;

@Database(entities = {RandomGroupListTable.class, UserGroupTable.class}, version = 2, exportSchema = false)
public abstract class DatabaseImplementation extends RoomDatabase {
    public abstract RandomGroupListDaoAccess randomGroupListDaoAccess();

    public abstract UserGroupDaoAccess userGroupDaoAccess();
}

