package id.pahlevikun.easygroupmaker.model.database.grouplist;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface RandomGroupListDaoAccess {
    @Insert
    void insertSingleUserGroup(RandomGroupListTable randomGroupListTable);

    @Insert
    void insertMultipleUserGroup(List<RandomGroupListTable> userGroupTableList);

    @Query("SELECT * FROM RandomGroupListTable")
    List<RandomGroupListTable> selectAllGroupList();

    @Query("SELECT * FROM RandomGroupListTable ORDER BY _id DESC LIMIT 1")
    RandomGroupListTable selectLastGroupList();

    @Query("DELETE FROM RandomGroupListTable")
    void deleteAllGroupList();

    @Query("DELETE FROM RandomGroupListTable WHERE _id =:userId")
    void deleteGroupListById(String userId);


    @Query("SELECT COUNT(*) FROM RandomGroupListTable")
    int countData();
}
