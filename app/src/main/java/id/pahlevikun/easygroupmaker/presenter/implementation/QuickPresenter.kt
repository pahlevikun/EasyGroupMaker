package id.pahlevikun.easygroupmaker.presenter.implementation

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.support.v4.content.FileProvider
import android.util.Log
import android.view.View
import id.pahlevikun.easygroupmaker.BuildConfig
import id.pahlevikun.easygroupmaker.R
import id.pahlevikun.easygroupmaker.composer.util.RandomManager
import id.pahlevikun.easygroupmaker.model.database.RoomInitializer
import id.pahlevikun.easygroupmaker.model.database.grouplist.GroupListTable
import id.pahlevikun.easygroupmaker.presenter.`interface`.QuickInterface
import id.pahlevikun.easygroupmaker.presenter.`interface`.ScreenShotInterface
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.SimpleDateFormat
import java.util.*

class QuickPresenter : QuickInterface, ScreenShotInterface {

    override fun takeScreenshot(view: View): Bitmap {
        view.isDrawingCacheEnabled = true
        return view.drawingCache
    }

    @SuppressLint("SimpleDateFormat")
    override fun saveScreenshot(context: Context, bitmap: Bitmap): File {
        val formatter = SimpleDateFormat("dd_MM_yyyy_HH_mm_ss")
        val date = Date()
        val createdAt = formatter.format(date)
        val name = "egm_image_quick_$createdAt.png"

        val dirPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString() +
                "/" + context.getString(R.string.app_name) + "/"
        val newDirectory = File(dirPath)
        newDirectory.mkdirs()

        try {
            val output = FileOutputStream("$dirPath$name")
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, output)
            output.close()
        } catch (e: FileNotFoundException) {
            Log.e("GREC", e.message, e)
        } catch (e: IOException) {
            Log.e("GREC", e.message, e)
        }
        return File("$dirPath$name")
    }

    override fun shareScreenshot(context: Context, imagePath: File) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val uri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".provider", imagePath)

            val sharingIntent = Intent(Intent.ACTION_SEND)
            sharingIntent.type = "image/*"
            val shareBody = "RandomGroup Screenshot"
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Random Group")
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody)
            sharingIntent.putExtra(Intent.EXTRA_STREAM, uri)
            context.startActivity(Intent.createChooser(sharingIntent, "Share via"))
        } else {
            val uri = Uri.fromFile(imagePath)
            val sharingIntent = Intent(Intent.ACTION_SEND)
            sharingIntent.type = "image/*"
            val shareBody = "RandomGroup Screenshot"
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Random Group")
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody)
            sharingIntent.putExtra(Intent.EXTRA_STREAM, uri)
            context.startActivity(Intent.createChooser(sharingIntent, "Share via"))
        }


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
        val stringBuffer = StringBuffer("Result :\n\n")
        for (singleGroup in 0 until value.size) {
            stringBuffer.append("Group ${singleGroup + 1}\n")
            for (singlePersonInGroup in 0 until value[singleGroup].size) {
                if (value[singleGroup][singlePersonInGroup] != 0) {
                    stringBuffer.append("${value[singleGroup][singlePersonInGroup]}. ")
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