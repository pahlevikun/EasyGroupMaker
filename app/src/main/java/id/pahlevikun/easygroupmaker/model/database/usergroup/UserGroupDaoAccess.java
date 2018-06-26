package id.pahlevikun.easygroupmaker.model.database.usergroup;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface UserGroupDaoAccess {
    @Insert
    void insertSingleProfile(UserGroupTable userGroupTable);

    @Insert
    void insertMultipleProfile(List<UserGroupTable> userGroupTableList);

    @Query("SELECT * FROM UserGroupTable")
    List<UserGroupTable> selectAllUserGroup();

    @Query("SELECT * FROM UserGroupTable ORDER BY _id DESC LIMIT 1")
    UserGroupTable selectLastUserGroup();

    @Query("DELETE FROM UserGroupTable")
    void deleteAllProfile();

    @Query("DELETE FROM UserGroupTable WHERE _id =:userId")
    void deleteProfileById(String userId);
}
