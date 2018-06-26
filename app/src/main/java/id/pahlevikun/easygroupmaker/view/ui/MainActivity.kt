package id.pahlevikun.easygroupmaker.view.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import id.pahlevikun.easygroupmaker.R
import id.pahlevikun.easygroupmaker.composer.util.ArrayConversion
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val sumOfPerson = 23
        val sumOfGroup = 5
        val estimatedSumOfPersonInFroup = roundHalfTop(sumOfPerson.toDouble() / sumOfGroup)
        val declaredArrayBasedOnGroupAndPerson = Array(sumOfGroup) { IntArray(estimatedSumOfPersonInFroup) }
        var groupIndex = 0
        var indexInsideGroup = 0
        val listOfPerson = mutableListOf<Int>()
        for (i in 1..sumOfPerson) {
            listOfPerson += i
        }
        listOfPerson.shuffle()

        for (singleItem in 0 until listOfPerson.size) {
            Log.d("HASIL", "PRINT $singleItem POSISI $groupIndex,$indexInsideGroup = ${listOfPerson[singleItem]}")
            declaredArrayBasedOnGroupAndPerson[groupIndex][indexInsideGroup] = listOfPerson[singleItem]
            if (groupIndex < sumOfGroup - 1) {
                groupIndex += 1
            } else {
                groupIndex = 0
                indexInsideGroup += 1
            }
        }
        Log.d("HASIL", "GROUPED WITH NEW \n${Arrays.deepToString(declaredArrayBasedOnGroupAndPerson)}")
        val stringBuffer = StringBuffer()
        for (singleGroup in 0 until declaredArrayBasedOnGroupAndPerson.size) {
            stringBuffer.append("Group ${singleGroup + 1}\n")
            for (singlePersonInGroup in 0 until declaredArrayBasedOnGroupAndPerson[singleGroup].size) {
                if (declaredArrayBasedOnGroupAndPerson[singleGroup][singlePersonInGroup] != 0) {
                    stringBuffer.append("${declaredArrayBasedOnGroupAndPerson[singleGroup][singlePersonInGroup]}\t")
                }
            }
            stringBuffer.append("\n\n")
        }
        Log.d("HASIL", "GROUPED IN STRING \n$stringBuffer")
    }

    private fun roundHalfTop(d: Double): Int {
        return BigDecimal(d).setScale(0, RoundingMode.HALF_UP).toInt()
    }

}
