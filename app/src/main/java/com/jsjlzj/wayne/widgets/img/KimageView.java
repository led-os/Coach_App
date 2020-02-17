package com.jsjlzj.wayne.widgets.img;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;

import com.jsjlzj.wayne.R;

/**
 * Created by weiyang on 2017/6/16 0016.
 * 正方形 有圆角  描边 点击效果的imagview
 */

public class KimageView extends androidx.appcompat.widget.AppCompatImageView {
    // 图片的宽高
    private int width;
    private int height;

    // 定义 Bitmap 的默认配置
    private static final Bitmap.Config BITMAP_CONFIG = Bitmap.Config.ARGB_8888;
    private static final int COLORDRAWABLE_DIMENSION = 1;

    // 圆角半径
    private int radius;

    private boolean isGray = false;
    public static int SHPA_TYPE_QUADRILATERAL = 2;
    public static int SHPA_TYPE_CIRCULAR = 1;
    private ColorMatrix matrix;
    public final float[] BT_SELECTED_DARK = new float[]{1, 0, 0, 0, -50, 0, 1, 0, 0, -50, 0, 0, 1, 0, -50, 0, 0, 0, 1, 0};

    public KimageView(Context context) {
        this(context, null);
    }

    public KimageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public KimageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getDefaultSize(0, widthMeasureSpec),
                getDefaultSize(0, heightMeasureSpec));

        int childWidthSize = getMeasuredWidth();
        // 高度和宽度一样  
        heightMeasureSpec = widthMeasureSpec = MeasureSpec.makeMeasureSpec(childWidthSize, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    private void init(Context context, AttributeSet attrs) {
        radius = 4;//圆角

        // 获取控件的属性值
        if (attrs != null) {
            TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.KimageView);
            radius = array.getDimensionPixelOffset(R.styleable.KimageView_radius, radius);
            array.recycle();
        }

        setClickable(true);
        setDrawingCacheEnabled(true);
        setWillNotDraw(false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 获取当前控件的 drawable
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return;
        }
        // 这里 get 回来的宽度和高度是当前控件相对应的宽度和高度（在 xml 设置）
        if (getWidth() == 0 || getHeight() == 0) {
            return;
        }

        // 获取 bitmap，即传入 imageview 的 bitmap
        // Bitmap bitmap = ((BitmapDrawable) ((SquaringDrawable)
        // drawable).getCurrent()).getBitmap();
        // 这里参考赵鹏的获取 bitmap 方式，因为上边的获取会导致 Glide 加载的drawable 强转为 BitmapDrawable 出错
        Bitmap bitmap = getBitmapFromDrawable(drawable);
        //设置圆角
        drawRadius(canvas, bitmap);

    }

    /**
     * 实现圆角的绘制
     */
    @SuppressLint("WrongConstant")
    private void drawRadius(Canvas canvas, Bitmap bitmap) {
        // 画笔
        Paint paint = new Paint();
        // 颜色设置
        paint.setColor(0xffffffff);
        // 抗锯齿
        paint.setAntiAlias(true);
        //Paint 的 Xfermode，PorterDuff.Mode.SRC_IN 取两层图像的交集部门, 只显示上层图像。
        PorterDuffXfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
        // 标志
//        int saveFlags = Canvas.MATRIX_SAVE_FLAG
//                | Canvas.CLIP_SAVE_FLAG
//                | Canvas.HAS_ALPHA_LAYER_SAVE_FLAG
//                | Canvas.FULL_COLOR_LAYER_SAVE_FLAG
//                | Canvas.CLIP_TO_LAYER_SAVE_FLAG;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            canvas.saveLayer(0, 0, width, height, null);
        }

        // 当ShapeType == 2 时 图片为圆角矩形 （这里在宽高上 -1 是为了不让图片超出边框）
        RectF rectf = new RectF(1, 1, getWidth() - 1, getHeight() - 1);
        canvas.drawRoundRect(rectf, radius + 1, radius + 1, paint);

        paint.setXfermode(xfermode);

        // 空间的大小 / bitmap 的大小 = bitmap 缩放的倍数
        float scaleWidth = ((float) getWidth()) / bitmap.getWidth();
        float scaleHeight = ((float) getHeight()) / bitmap.getHeight();

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);

        //bitmap 缩放
        bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);

        //draw 上去
        canvas.drawBitmap(bitmap, 0, 0, paint);
        canvas.restore();
    }



    /**
     * 这里是参考其他开发者获取Bitmap内容的方法， 之前是因为没有考虑到 Glide 加载的图片
     * 导致drawable 类型是属于 SquaringDrawable 类型，导致强转失败
     * 这里是通过drawable不同的类型来进行获取Bitmap
     */
    private Bitmap getBitmapFromDrawable(Drawable drawable) {
        try {
            Bitmap bitmap;
            if (drawable instanceof BitmapDrawable) {
                return ((BitmapDrawable) drawable).getBitmap();
            } else if (drawable instanceof ColorDrawable) {
                bitmap = Bitmap.createBitmap(COLORDRAWABLE_DIMENSION, COLORDRAWABLE_DIMENSION, BITMAP_CONFIG);
            } else {
                bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(),
                        BITMAP_CONFIG);
            }
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmap;
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 设置倒角半径
     */
    public void setRadius(int radius) {
        this.radius = radius;
        invalidate();
    }
}
