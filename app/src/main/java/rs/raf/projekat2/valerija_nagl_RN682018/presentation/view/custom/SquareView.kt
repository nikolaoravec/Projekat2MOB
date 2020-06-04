package rs.raf.projekat2.valerija_nagl_RN682018.presentation.view.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color.blue
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import rs.raf.projekat2.valerija_nagl_RN682018.R
import rs.raf.projekat2.valerija_nagl_RN682018.data.models.Note
import rs.raf.projekat2.valerija_nagl_RN682018.extensions.toPx
import timber.log.Timber

class SquareView : View {

    constructor(context: Context)
            : super(context)
    constructor(context: Context, attrs: AttributeSet?)
            : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr)

    private var rect: Rect = Rect()
    private val redStrokePaint: Paint = Paint().also {
        it.isAntiAlias = true
        it.color = ContextCompat.getColor(context, R.color.red)
        it.style = Paint.Style.STROKE
        it.strokeWidth = 4.toPx().toFloat()
    }
    private val blueFillPaint: Paint = Paint().also {
        it.isAntiAlias = true
        it.color = ContextCompat.getColor(context, R.color.blue)
        it.style = Paint.Style.FILL
    }

    private var heightDivider : MutableList<Int> = mutableListOf()

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        Timber.e("On measure")
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        Timber.e("On layout")
    }

    fun setData(list : MutableList<Int>){
        heightDivider = list
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        Timber.e("On draw")

        var left = 0
        var top = 0
        var bottom = height
        var right = width / 5

        var maxVal = 0
        for (i in 0 until heightDivider.size){
            if(heightDivider[i] > maxVal){
                maxVal = heightDivider[i]
            }
        }

        for (i in heightDivider.size-1 downTo 0){
            top = height - (heightDivider[i]/maxVal*height)
            rect.set(left, top, right, bottom)
            canvas?.drawRect(rect, blueFillPaint)
            canvas?.drawRect(rect, redStrokePaint)
            left = right
            right +=width/5
        }


    }

}