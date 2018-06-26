package id.pahlevikun.easygroupmaker.model.database;

import android.arch.persistence.room.Room;
import android.content.Context;

import id.pahlevikun.easygroupmaker.composer.config.DBConfig;
import id.pahlevikun.easygroupmaker.model.database.grouplist.GroupListDatabase;
import id.pahlevikun.easygroupmaker.model.database.usergroup.UserGroupDatabase;

public class RoomInitializer {
    private static UserGroupDatabase userGroupDatabaseInstance;
    private static GroupListDatabase groupListDatabaseInstance;

    public static UserGroupDatabase initUserGroup(Context context) {
        if (userGroupDatabaseInstance == null) {
            userGroupDatabaseInstance = Room.databaseBuilder(context,
                    UserGroupDatabase.class, DBConfig.DATABASE_GROUP)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return userGroupDatabaseInstance;
    }

    public static GroupListDatabase initGroupList(Context context) {
        if (groupListDatabaseInstance == null) {
            groupListDatabaseInstance = Room.databaseBuilder(context,
                    GroupListDatabase.class, DBConfig.DATABASE_GROUP)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return groupListDatabaseInstance;
    }

    public static void destroyUserGroup() {
        userGroupDatabaseInstance = null;
    }

    public static void destroyGroupList() {
        groupListDatabaseInstance = null;
    }

}
