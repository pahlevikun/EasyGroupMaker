package id.pahlevikun.easygroupmaker.model.database.usergroup;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface UserGroupDaoAccess {
    @Insert
    void insertSingleUserGroup(UserGroupTable userGroupTable);

    @Insert
    void insertMultipleUserGroup(List<UserGroupTable> userGroupTableList);

    @Query("SELECT * FROM UserGroupTable")
    List<UserGroupTable> selectAllUserGroup();

    @Query("SELECT * FROM UserGroupTable ORDER BY _id DESC LIMIT 1")
    UserGroupTable selectLastUserGroup();

    @Query("DELETE FROM UserGroupTable")
    void deleteAllUserGroup();

    @Query("DELETE FROM UserGroupTable WHERE _id =:userId")
    void deleteUserGroupById(String userId);
}
