package id.pahlevikun.easygroupmaker.presenter.implementation

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.util.Log
import android.view.View
import id.pahlevikun.easygroupmaker.composer.util.RandomManager
import id.pahlevikun.easygroupmaker.model.database.RoomInitializer
import id.pahlevikun.easygroupmaker.model.database.grouplist.GroupListTable
import id.pahlevikun.easygroupmaker.presenter.`interface`.ResultGroupInterface
import id.pahlevikun.easygroupmaker.presenter.`interface`.ScreenShotInterface
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.SimpleDateFormat
import java.util.*

class ResultGroupPresenter : ResultGroupInterface, ScreenShotInterface {

    override fun takeScreenshot(view: View): Bitmap {
        view.isDrawingCacheEnabled = true
        return view.drawingCache
    }

    override fun saveScreenshot(context: Context, bitmap: Bitmap): File {
        val imagePath = File("${Environment.getExternalStorageDirectory()}/scrnshot.png")
        val fos: FileOutputStream?
        try {
            fos = FileOutputStream(imagePath)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)
            fos.flush()
            fos.close()
        } catch (e: FileNotFoundException) {
            Log.e("GREC", e.message, e)
        } catch (e: IOException) {
            Log.e("GREC", e.message, e)
        }
        return imagePath
    }

    override fun shareScreenshot(context: Context, imagePath: File) {
        val uri = Uri.fromFile(imagePath)
        val sharingIntent = Intent(Intent.ACTION_SEND)
        sharingIntent.type = "image/*"
        val shareBody = "My highest score with screen shot"
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "My Catch score")
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody)
        sharingIntent.putExtra(Intent.EXTRA_STREAM, uri)

        context.startActivity(Intent.createChooser(sharingIntent, "Share via"))
    }

    override fun isFieldEmpty(name: String, desc: String): Boolean {
        return name.isEmpty() || desc.isEmpty()
    }

    override fun countRandom(context: Context) {
        RandomManager(context).addRandomed()
    }

    override fun roundHalfToUp(value: Double): Int {
        return BigDecimal(value).setScale(0, RoundingMode.HALF_UP).toInt()
    }

    override fun beginRandomize(sumOfPerson: Array<String>, sumOfGroup: String, isSizeMethod: Boolean): Array<Array<String>> {
        var groupIndex = 0
        var indexInsideGroup = 0
        val castedSumOfPerson: Int
        val castedSumOfGroup: Int
        val estimatedSumOfPersonInFroup: Int

        if (isSizeMethod) {
            castedSumOfPerson = sumOfPerson.size
            val tempCastedSumOfGroup = sumOfGroup.toInt()
            castedSumOfGroup = (castedSumOfPerson / tempCastedSumOfGroup) + 1
            estimatedSumOfPersonInFroup = tempCastedSumOfGroup
        } else {
            castedSumOfPerson = sumOfPerson.size
            castedSumOfGroup = sumOfGroup.toInt()
            estimatedSumOfPersonInFroup = roundHalfToUp(castedSumOfPerson.toDouble() / castedSumOfGroup) + 1
        }

        Log.d("HASIL", "GRUP $isSizeMethod, PERSON $castedSumOfPerson, GRUP $castedSumOfGroup, PERSON/GRUP $estimatedSumOfPersonInFroup")
        val declaredArrayBasedOnGroupAndPerson = Array(castedSumOfGroup) { Array(estimatedSumOfPersonInFroup) { "" } }

        val listOfPerson = sumOfPerson.toMutableList()
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

    override fun parseArrayToHumanReadable(value: Array<Array<String>>): String {
        val stringBuffer = StringBuffer("Result :\n\n")
        for (singleGroup in 0 until value.size) {
            stringBuffer.append("Group ${singleGroup + 1}\n")
            for (singlePersonInGroup in 0 until value[singleGroup].size) {
                if (value[singleGroup][singlePersonInGroup] != "") {
                    stringBuffer.append("${value[singleGroup][singlePersonInGroup]}. ")
                }
            }
            stringBuffer.append("\n\n")
        }
        return stringBuffer.toString()
    }

    @SuppressLint("SimpleDateFormat")
    override fun saveToDatabase(context: Context, name: String, description: String, savedValue: Array<Array<String>>) {
        val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
        val date = Date()
        val createdAt = formatter.format(date)
        val room = RoomInitializer.initDatabase(context).groupListDaoAccess()
        val data = GroupListTable(name, description, Arrays.deepToString(savedValue), createdAt, createdAt)
        room.insertSingleUserGroup(data)
    }
}