package id.pahlevikun.easygroupmaker.model.database;

import android.arch.persistence.room.Room;
import android.content.Context;

import id.pahlevikun.easygroupmaker.composer.config.DBConfig;
import id.pahlevikun.easygroupmaker.model.database.usergroup.UserGroupDatabase;

public class RoomInitializer {
    private static UserGroupDatabase userGroupDatabaseInstance;

    public static UserGroupDatabase initProfile(Context context) {
        if (userGroupDatabaseInstance == null) {
            userGroupDatabaseInstance = Room.databaseBuilder(context,
                    UserGroupDatabase.class, DBConfig.DATABASE_GROUP)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return userGroupDatabaseInstance;
    }

    public static void destroyProfile() {
        userGroupDatabaseInstance = null;
    }

}
