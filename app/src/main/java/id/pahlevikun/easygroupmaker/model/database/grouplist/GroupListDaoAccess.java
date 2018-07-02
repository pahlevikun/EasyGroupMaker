package id.pahlevikun.easygroupmaker.model.database.grouplist;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface GroupListDaoAccess {
    @Insert
    void insertSingleUserGroup(GroupListTable groupListTable);

    @Insert
    void insertMultipleUserGroup(List<GroupListTable> userGroupTableList);

    @Query("SELECT * FROM GroupListTable")
    List<GroupListTable> selectAllGroupList();

    @Query("SELECT * FROM GroupListTable ORDER BY _id DESC LIMIT 1")
    GroupListTable selectLastGroupList();

    @Query("DELETE FROM GroupListTable")
    void deleteAllGroupList();

    @Query("DELETE FROM GroupListTable WHERE _id =:userId")
    void deleteGroupListById(String userId);


    @Query("SELECT COUNT(*) FROM GroupListTable")
    int countData();
}
