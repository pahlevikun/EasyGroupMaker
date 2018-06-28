package id.pahlevikun.easygroupmaker.presenter.implementation

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import id.pahlevikun.easygroupmaker.model.database.RoomInitializer
import id.pahlevikun.easygroupmaker.model.database.grouplist.GroupListTable
import id.pahlevikun.easygroupmaker.presenter.`interface`.QuickInterface
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.SimpleDateFormat
import java.util.*

class QuickPresenter : QuickInterface {
    override fun roundHalfToUp(value: Double): Int {
        return BigDecimal(value).setScale(0, RoundingMode.HALF_UP).toInt()
    }

    override fun beginRandomize(sumOfPerson: String, sumOfGroup: String, isSizeMethod: Boolean): Array<IntArray> {
        var groupIndex = 0
        var indexInsideGroup = 0
        val castedSumOfPerson: Int
        val castedSumOfGroup: Int
        val estimatedSumOfPersonInFroup: Int

        if (isSizeMethod) {
            castedSumOfPerson = sumOfPerson.toInt()
            val tempCastedSumOfGroup = sumOfGroup.toInt()
            castedSumOfGroup = (castedSumOfPerson / tempCastedSumOfGroup) + 1
            estimatedSumOfPersonInFroup = tempCastedSumOfGroup
        } else {
            castedSumOfPerson = sumOfPerson.toInt()
            castedSumOfGroup = sumOfGroup.toInt()
            estimatedSumOfPersonInFroup = roundHalfToUp(castedSumOfPerson.toDouble() / castedSumOfGroup) + 1
        }

        Log.d("HASIL", "GRUP $isSizeMethod, PERSON $castedSumOfPerson, GRUP $castedSumOfGroup, PERSON/GRUP $estimatedSumOfPersonInFroup")
        val declaredArrayBasedOnGroupAndPerson = Array(castedSumOfGroup) { IntArray(estimatedSumOfPersonInFroup) }

        val listOfPerson = mutableListOf<Int>()
        for (i in 1..castedSumOfPerson) {
            listOfPerson += i
        }
        listOfPerson.shuffle()

        for (singleItem in 0 until listOfPerson.size) {
            Log.d("HASIL", "PRINT $singleItem POSISI $groupIndex,$indexInsideGroup = ${listOfPerson[singleItem]}")
            declaredArrayBasedOnGroupAndPerson[groupIndex][indexInsideGroup] = listOfPerson[singleItem]
            if (groupIndex < castedSumOfGroup - 1) {
                groupIndex += 1
            } else {
                groupIndex = 0
                indexInsideGroup += 1
            }
        }
        Log.d("HASIL", "GROUPED WITH NEW \n${Arrays.deepToString(declaredArrayBasedOnGroupAndPerson)}")

        return declaredArrayBasedOnGroupAndPerson
    }

    override fun parseArrayToHumanReadable(value: Array<IntArray>): String {
        val stringBuffer = StringBuffer()
        for (singleGroup in 0 until value.size) {
            stringBuffer.append("Group ${singleGroup + 1}\n")
            for (singlePersonInGroup in 0 until value[singleGroup].size) {
                if (value[singleGroup][singlePersonInGroup] != 0) {
                    stringBuffer.append("${value[singleGroup][singlePersonInGroup]}\t")
                }
            }
            stringBuffer.append("\n\n")
        }
        return stringBuffer.toString()
    }

    @SuppressLint("SimpleDateFormat")
    override fun saveToDatabase(context: Context, name: String, description: String, savedValue: Array<IntArray>) {
        val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
        val date = Date()
        val createdAt = formatter.format(date)
        val room = RoomInitializer.initDatabase(context).groupListDaoAccess()
        val data = GroupListTable(name, description, Arrays.deepToString(savedValue), createdAt, createdAt)
        room.insertSingleUserGroup(data)
    }
}