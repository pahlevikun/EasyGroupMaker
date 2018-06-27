package id.pahlevikun.easygroupmaker.model.database;

import android.arch.persistence.room.Room;
import android.content.Context;

import id.pahlevikun.easygroupmaker.composer.config.DBConfig;

public class RoomInitializer {
    private static DatabaseImplementation databaseImplementation;

    public static DatabaseImplementation initDatabase(Context context) {
        if (databaseImplementation == null) {
            databaseImplementation = Room.databaseBuilder(context,
                    DatabaseImplementation.class, DBConfig.DATABASE_GROUP)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return databaseImplementation;
    }

    public static void destroyGroupList() {
        databaseImplementation = null;
    }

}
