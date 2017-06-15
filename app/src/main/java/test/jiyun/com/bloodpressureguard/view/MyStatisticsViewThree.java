package test.jiyun.com.bloodpressureguard.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.icu.util.Measure;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static android.os.Build.VERSION_CODES.M;


/**
 * Created by 韩志军 on 2017/6/11.
 */

public class MyStatisticsViewThree extends View {
    //-------------View相关-------------
    //View自身的宽和高
    private int mHeight;
    private int mWidth;

    //-------------统计图相关-------------
    //x轴的条目
    private int xNum = 6;
    //y轴的条目
    private int yNum = 6;
    //y轴条目之间的距离
    private int ySize;
    //x轴条目之间的距离
    private int xSize;

    //-------------必须给的资源相关-------------

    /*血压记录的时间*/
    private int[] xjj = {};

    private String[] xStr = {};
    private String[] yStr = new String[]{" ", "40", "80", "120", "160", "200"};
    private String str = "每月血压统计表";

    //高压的真实值
    private int[] yValue = {};
    //低压的真实值
    private int[] yValuea = {};

    //-------------画笔相关-------------
    //边框的画笔
    private Paint borderPaint;
    //文字的画笔
    private Paint textPaint;
    //血压的画笔
    //黑点的画笔
    private Paint pointPaint;

    /*高 低压圆点的画笔*/
    private Paint pointPaintGY;
    private Paint pointPaintDY;

    //-------------颜色相关-------------
    //边框颜色
    private int mColor = 0xFF888888;
    //文字颜色
    private int textColor = 0xFF888888;
    //黑点颜色
    private int pointColor = 0xFF000000;
    /*高低压颜色*/
    private int pointColorGY = 0xFF0000FF;
    private int pointColorDY = 0xFF9B9BCD;
    private int day;

    /*画折线的画笔*/
    private Paint linePaint;


    public MyStatisticsViewThree(Context context) {
        super(context);
    }

    public MyStatisticsViewThree(Context context, AttributeSet attrs) {
        super(context, attrs);
        SimpleDateFormat formatter = new SimpleDateFormat("dd");
        Date curDate = new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);
        day = Integer.parseInt(str);

        xStr = new String[]{day - 5 + "日", day - 4 + "日", day - 3 + "日", day - 2 + "日", day - 1 + "日", "今天", day + 1 + "日"};

    }

    public MyStatisticsViewThree(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setShuJu(List gaoya, List diya, List time) {
        /*当集合为空的时候  return*/
        if (gaoya.size() <= 0 || diya.size() <= 0)
            return;
        /*高压和低压的长度必须的相同*/
        if (gaoya.size() != diya.size() && gaoya.size() != time.size())
            return;

        /*将集合里的数据添加到数组里*/  /*高压数据*/
        int size = gaoya.size();
        int[] a = new int[size];
        for (int i = 0; i < a.length; i++) {
            a[i] = (int) gaoya.get(i);
        }
        this.yValue = a;

        /*将集合里的数据添加到数组里*/  /*低压数据*/
        int size1 = diya.size();
        int[] b = new int[size1];
        for (int i = 0; i < b.length; i++) {
            b[i] = (int) diya.get(i);
        }
        this.yValuea = b;

         /*将集合里的数据添加到数组里*/  /*时间数据*/
        int size2 = time.size();
        int[] c = new int[size2];
        for (int i = 0; i < c.length; i++) {
            c[i] = (int) time.get(i);
        }
        this.xjj = c;

        /*请求布局*/
        requestLayout();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        /*得到宽和高*/
        mWidth = getWidth();
        mHeight = getHeight();

        /*设置x轴和y轴之间的间距*/
        xSize = getWidth() / (xStr.length);
        ySize = getHeight() / (yStr.length);
        super.onLayout(changed, left, top, right, bottom);
        Log.d("MyStatisticsView", "mWidth:" + mWidth);
        Log.d("MyStatisticsView", "mHeight:" + mHeight);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(widthMeasureSpec,heightMeasureSpec);
        int mode = MeasureSpec.getMode(widthMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //初始化画笔
        initPaint();
        //画布移到左下角，留出100的空间给予文字填充
        canvas.translate(100, mHeight - 100);
        //画边框
        drawBorder(canvas);
        //画黑点
        drawPoint(canvas);
        //画文字
        drawText(canvas);
        //画高压
        drawLine(canvas);
        //画低压
        drawLineA(canvas);
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        //边框画笔
        borderPaint = new Paint();
        borderPaint.setAntiAlias(true);
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setColor(mColor);
        borderPaint.setStrokeWidth(1);
        //文字画笔
        textPaint = new Paint();
        textPaint.setTextSize(50);
        textPaint.setColor(textColor);
        textPaint.setAntiAlias(true);
        //黑点画笔
        pointPaint = new Paint();
        pointPaint.setAntiAlias(true);
        pointPaint.setStyle(Paint.Style.FILL);
        pointPaint.setColor(pointColor);

        //画折线
        linePaint = new Paint();
        linePaint.setColor(0x7f0100ff);
        linePaint.setAntiAlias(true);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeWidth(2);


        /*画高压*/
        pointPaintGY = new Paint();
        pointPaintGY.setAntiAlias(true);
        pointPaintGY.setStrokeWidth(10);
        pointPaintGY.setStyle(Paint.Style.STROKE);
        pointPaintGY.setColor(pointColorGY);

        /*画低压*/
        pointPaintDY = new Paint();
        pointPaintDY.setAntiAlias(true);
        /*设置画笔的大小*/
        pointPaintDY.setStrokeWidth(10);
        pointPaintDY.setStyle(Paint.Style.STROKE);
        pointPaintDY.setColor(pointColorDY);


    }

    /**
     * 画边框
     *
     * @param canvas
     */
    private void drawBorder(Canvas canvas) {
        Path path = new Path();
        for (int i = 0; i < yNum; i++) {
            //一条竖直的线
            if (i == 0) {
                path.moveTo(0, -i * ySize);
                path.lineTo(0, -(yNum - 1) * ySize);
            }
            //循环水平的线
            path.moveTo(0, -i * ySize);
            path.lineTo(xNum * xSize, -i * ySize);
            canvas.drawPath(path, borderPaint);
        }
    }

    /**
     * 画黑点
     *
     * @param canvas
     */
    private void drawPoint(Canvas canvas) {
        for (int i = 0; i <= xNum; i++) {
            canvas.drawCircle(i * xSize, 0, 5, pointPaint);
        }
    }

    /**
     * 画文字
     *
     * @param canvas
     */
    private void drawText(Canvas canvas) {
        //事先说明：文字排版为了好看，这里的20，都为20px的边距
        //x轴的文字
        for (int i = 0; i < xStr.length; i++) {
            //测量文字的宽高
            Rect rect = new Rect();
            textPaint.getTextBounds(xStr[i], 0, xStr[i].length(), rect);
            float textWidth = rect.width();
            float textHeight = rect.height();
            canvas.drawText(xStr[i], i * xSize - textWidth / 2, textHeight + 20, textPaint);
        }
        //y轴的文字
        for (int i = 0; i < yStr.length; i++) {
            //测量文字的宽高
            Rect rect = new Rect();
            textPaint.getTextBounds(yStr[i], 0, yStr[i].length(), rect);
            float textWidth = rect.width();
            float textHeight = rect.height();
            canvas.drawText(yStr[i], -textWidth - 20, i * (-ySize) + (textHeight / 2), textPaint);
        }
        //顶部文字
        canvas.drawText(str, 0, (-ySize) * (yStr.length - 1), textPaint);
    }

    /*画高压*/
    private void drawLine(Canvas canvas) {
        Path mPath = new Path();
        for (int i = 0; i < yValue.length; i++) {
            String s = xStr[0];
            String substring = s.substring(0, xStr[0].length() - 1);
//            float position = -(yValue[i]/40*ySize);
            float position = -(yValue[i] * 5 * ySize / 200);
            float positiona = ((xjj[i] - Integer.parseInt(substring)) * xSize);
            if (i == 0) {
                mPath.moveTo(positiona, position);
            } else {
                mPath.lineTo(positiona, position);
            }
            canvas.drawPath(mPath, linePaint);
            //画黑点
            canvas.drawCircle(positiona, position, 5, pointPaintGY);
        }
    }

    /*画低压*/
    private void drawLineA(Canvas canvas) {
        Path mPath = new Path();
        for (int i = 0; i < yValuea.length; i++) {
            String s = xStr[0];
            String substring = s.substring(0, xStr[0].length() - 1);
            float position = -(yValuea[i] * 5 * ySize / 200);
            float positiona = ((xjj[i] - Integer.parseInt(substring)) * xSize);
            Log.d("MyStatisticsViewTwo", "positiona:" + positiona);
            if (i == 0) {
                mPath.moveTo(positiona, position);
            } else {
                mPath.lineTo(positiona, position);
            }
            canvas.drawPath(mPath, linePaint);
            //画黑点
            canvas.drawCircle(positiona, position, 5, pointPaintDY);
        }
    }
}
