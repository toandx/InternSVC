package com.svc.toandx.tictactoekotlin

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;
class PlayBoard:View{
    var xColor:Int=0
    var oColor:Int=0
    var color:Int=0
    var numCol:Int=0
    var signToWin:Int=0
    var paint:Paint = Paint()
    var strokeWidth: Float= 0f
    var colWidth: Float = 0f
    var pad: Float = 0f
    var absSize: Float = 0f
    var status:Int = 0
    var lastX:Int = 0
    var lastY:Int = 0
    var countPlay:Int = 0
    var matrix=Array<Array<Int>>(100) {
        Array<Int>(100) {0}
    }
    fun init()
    {
        countPlay=0;
            for(i in 0..numCol-1)
            for(j in 0..numCol-1) matrix[i][j]=0;
            paint= Paint()
            paint.setColor(color);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(strokeWidth);
    }
    constructor(context: Context):super(context)
    constructor(context : Context, attrs : AttributeSet):super(context,attrs)
    {
        var typedArray : TypedArray = context.obtainStyledAttributes(attrs,
        R.styleable.PlayBoard);
        var count : Int = typedArray.getIndexCount();
        try{

            for (i in 0..count-1) {

                var attr : Int = typedArray.getIndex(i);
                // the attr corresponds to the title attribute
                if(attr == R.styleable.PlayBoard_color) {

                    // set the text from the layout
                    color = typedArray.getInteger(attr,0);
                } else if(attr == R.styleable.PlayBoard_xColor) {
                    // set the color of the attr "setColor"
                    xColor = typedArray.getInteger(attr, 0);
                } else if(attr == R.styleable.PlayBoard_oColor) {
                    // set the color of the attr "setColor"
                    oColor = typedArray.getInteger(attr, 0);
                } else if(attr == R.styleable.PlayBoard_numCol) {
                    // set the color of the attr "setColor"
                    numCol = typedArray.getInteger(attr, 0);
                } else if(attr == R.styleable.PlayBoard_strokeWidth) {
                    // set the color of the attr "setColor"
                    strokeWidth = typedArray.getFloat(attr, 0f);
                } else if(attr == R.styleable.PlayBoard_pad) {
                    // set the color of the attr "setColor"
                    pad = typedArray.getFloat(attr, 0f);
                } else if(attr == R.styleable.PlayBoard_signToWin) {
                    // set the color of the attr "setColor"
                    signToWin = typedArray.getInteger(attr, 0);
                }
            }
            init();
        }

        // the recycle() will be executed obligatorily
        finally {
            // for reuse
            typedArray.recycle();
        }

    }
    override fun onMeasure(widthMeasureSpec : Int, heightMeasureSpec:Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        var widthSize : Int = MeasureSpec.getSize(widthMeasureSpec);
        var heightSize : Int= MeasureSpec.getSize(heightMeasureSpec);
        if (widthSize<heightSize) this.setMeasuredDimension(widthSize,widthSize); else
            this.setMeasuredDimension(heightSize,heightSize);
    }
    fun drawGridLine(canvas: Canvas )
    {
        //if (getMeasuredWidth()<getMeasuredHeight()) absSize=getMeasuredWidth(); else
        absSize=getMeasuredHeight().toFloat();
        absSize=absSize-2*pad;
        colWidth=absSize/numCol;
        if (absSize<0) return;
        for(i in 0..numCol)
        {
            canvas.drawLine(pad+i*colWidth,pad,pad+i*colWidth,absSize+pad,paint);
            canvas.drawLine(pad,pad+i*colWidth,absSize+pad,pad+i*colWidth,paint);
        }
    }
    fun drawSign(canvas : Canvas)
    {
        for(i in 0..numCol-1)
        for(j in 0..numCol-1)
        {
            if (matrix[i][j]==1)
            {
                paint.setColor(xColor);
                canvas.drawLine(pad+i*colWidth,pad+j*colWidth,pad+(i+1)*colWidth,pad+(j+1)*colWidth,paint);
                canvas.drawLine(pad+(i+1)*colWidth,pad+j*colWidth,pad+i*colWidth,pad+(j+1)*colWidth,paint);
            }
            if (matrix[i][j]==2)
            {
                paint.setColor(oColor);
                canvas.drawCircle(pad+i*colWidth+colWidth/2,pad+j*colWidth+colWidth/2,colWidth/2,paint);
            }
        }
        paint.setColor(color);
    }
    fun checkWinner()
    {
        var count:Int;
        var i:Int;
        var j:Int
        count=0;
        i=lastX; j=lastY;
        while ((i-1>=0))
        {
            if (matrix[i-1][j]==matrix[lastX][lastY])
            {
                ++count;
                i=i-1;
            }
            else break;
        }
        i=lastX; j=lastY;
        while ((i+1<numCol))
        {
            if (matrix[i+1][j]==matrix[lastX][lastY])
            {
                ++count;
                i=i+1;
            } else break;
        }
        if (count+1==signToWin) {status=matrix[lastX][lastY]+2;return;}
        count=0;
        i=lastX; j=lastY;
        while ((j-1>=0))
        {
            if (matrix[i][j-1]==matrix[lastX][lastY])
            {
                ++count;
                j=j-1;
            } else break;
        }
        i=lastX; j=lastY;
        while ((j+1<numCol))
        {
            if (matrix[i][j+1]==matrix[lastX][lastY])
            {
                ++count;
                j=j+1;
            } else break;
        }
        if (count+1==signToWin) {status=matrix[lastX][lastY]+2;return;}
        count=0;
        i=lastX; j=lastY;
        while ((j-1>=0) && (i-1>=0))
        {
            if (matrix[i-1][j-1]==matrix[lastX][lastY])
            {
                ++count;
                j=j-1;
                i=i-1;
            } else break;
        }
        i=lastX; j=lastY;
        while ((j+1<numCol) && (i+1<numCol))
        {
            if (matrix[i+1][j+1]==matrix[lastX][lastY])
            {
                ++count;
                j=j+1;
                i=i+1;
            } else break;
        }
        if (count+1==signToWin) {status=matrix[lastX][lastY]+2;return;}
        count=0;
        i=lastX; j=lastY;
        while ((i+1<numCol) &&(j-1>=0))
        {
            if (matrix[i+1][j-1]==matrix[lastX][lastY])
            {
                ++count;
                j=j-1;
                i=i+1;
            } else break;
        }
        i=lastX; j=lastY;
        while ((j+1<numCol) && (i-1>=0))
        {
            if (matrix[i-1][j+1]==matrix[lastX][lastY])
            {
                ++count;
                j=j+1;
                i=i-1;
            } else break;
        }
        if (count+1==signToWin) {status=matrix[lastX][lastY]+2;return;}
    }
    override fun onDraw(canvas : Canvas)
    {
        super.onDraw(canvas);
        //canvas.drawLine(0,0,getMeasuredWidth(),getMeasuredHeight(),paint);
        drawGridLine(canvas);
        drawSign(canvas);
    }
    override fun onTouchEvent(event:MotionEvent) : Boolean{
        super.onTouchEvent(event);
        var eventAction : Int= event.getAction();
        if (eventAction!=MotionEvent.ACTION_UP) return(true);
        // you may need the x/y location
        var x :Int = event.getX().toInt();
        var y : Int= event.getY().toInt();
        var colX:Int= ((x-pad)/colWidth+1).toInt();
        var colY:Int= ((y-pad)/colWidth+1).toInt();
        if ((colX<1) || (colX>numCol) || (colY<1) || (colY>numCol)) return(true);
        if (matrix[colX-1][colY-1]!=0) return(true);
        countPlay=((countPlay+1)%2);
        matrix[colX-1][colY-1]=(-countPlay+2);
        lastX=colX-1;
        lastY=colY-1;
        status=-countPlay+2;
        checkWinner();
        invalidate();
        // tell the View that we handled the event
        return true;

    }
    fun reset()
    {
        for(i in 0..numCol-1)
        for(j in 0..numCol-1)
        matrix[i][j]=0;
        countPlay=0;
        invalidate();
    }
}