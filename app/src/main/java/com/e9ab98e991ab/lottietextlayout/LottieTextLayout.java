package com.e9ab98e991ab.lottietextlayout;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.airbnb.lottie.LottieAnimationView;


/** 
 * @author gaoxin 2020/5/23 15:24
 * @version V1.0.0
 * @name LottieTextLayout
 * @mail godfeer@aliyun.com
 * @description  TODO
 */
public class LottieTextLayout extends FrameLayout {
    private TextView mTitle;
    private LottieAnimationView mLottieView;
    private LottieAnimatorUpdateListener mAnimatorUpdateListener;
    private TextView mitemView;
    private View view;
    private int lottie_text;
    private int lottie_json;
    private int lottie_new_text;
    private int lottie_view_width;
    private int lottie_view_height;
    private int lottie_new_style;
    private int lottie_new_position_x;
    private int lottie_new_position_y;
    private int lottie_text_size;
    private int lottie_text_color;
    private int lottie_text_color_select;

    public LottieTextLayout(@NonNull Context context) {
        super(context,null);
    }

    public LottieTextLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
        initAttrs(context,attrs);
    }

    public LottieTextLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
        initAttrs(context,attrs);
    }


    //初始化控件
    protected void initView( Context context) {
        View v = LayoutInflater.from(context).inflate(R.layout.layout_nav_item, this,true);
        mTitle = v.findViewById(R.id.title);
        mLottieView = v.findViewById(R.id.icon);
        mitemView = v.findViewById(R.id.item_view);
        view = v.findViewById(R.id.view);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LottieTextLayout);
        this.lottie_text = typedArray.getResourceId(R.styleable.LottieTextLayout_set_lottie_text,-1);
        this.lottie_text_size = typedArray.getDimensionPixelSize(R.styleable.LottieTextLayout_set_lottie_text_size,10);
        this.lottie_text_color = typedArray.getColor(R.styleable.LottieTextLayout_set_lottie_text_color,-1);
        this.lottie_text_color_select = typedArray.getColor(R.styleable.LottieTextLayout_set_lottie_text_color_select,-1);
        this.lottie_json = typedArray.getResourceId(R.styleable.LottieTextLayout_set_lottie_view_json,-1);
        this.lottie_new_style = typedArray.getResourceId(R.styleable.LottieTextLayout_set_lottie_new_style,-1);
        this.lottie_new_text = typedArray.getInt(R.styleable.LottieTextLayout_set_lottie_new_text,-1);
        this.lottie_view_width = typedArray.getInt(R.styleable.LottieTextLayout_set_lottie_view_width,-1);
        this.lottie_view_height = typedArray.getInt(R.styleable.LottieTextLayout_set_lottie_view_height,-1);
        this.lottie_new_position_x = typedArray.getInt(R.styleable.LottieTextLayout_set_lottie_new_position_x,-1);
        this.lottie_new_position_y = typedArray.getInt(R.styleable.LottieTextLayout_set_lottie_new_position_y,-1);

        typedArray.recycle();
        mTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX,lottie_text_size);
        if (lottie_text_color!=-1){
            mTitle.setTextColor(lottie_text_color);
        }
        setLottieNewStyle(lottie_new_style);
        setSelected(lottie_json,lottie_text);
        setNewsSelected(lottie_new_text);
        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
        setOnClickListener(v -> {
            setChecked();
        });
    }

    public void setSelectAnimator(boolean isSelected, NavItem navItem) {
        if (isSelected) {
            mLottieView.playAnimation();
            if (mAnimatorUpdateListener == null) {
                mAnimatorUpdateListener = new LottieAnimatorUpdateListener(navItem);
            }
            //设置动画监听事件
            mLottieView.addAnimatorUpdateListener(mAnimatorUpdateListener);
//            if (navItem.getTitleResId() == R.string.bottom_bar_discovery) {
            mLottieView.setSpeed(1.5f);
//            }
            return;
        }
        mLottieView.setProgress(0.0f);
        mLottieView.cancelAnimation();
        if (navItem.getTextColor() != 0) {
            mTitle.setTextColor(getResources().getColor(navItem.getTextColor()));
        }
    }

    public void setNewsSelected(int num) {
        boolean show = num > 0;
        mitemView.setVisibility(show ? View.VISIBLE : View.GONE);
        view.setVisibility(show ? View.VISIBLE : View.GONE);
        String text = show ? (num > 99 ? "99+" : String.valueOf(num)) : "";
        mitemView.setText(text);
//        requestLayout();
    }

    //设置选中状态
    protected void setSelected(int rawResId, int titleResId) {
        mLottieView.setVisibility(View.VISIBLE);
        mLottieView.setAnimation(rawResId);
        if (titleResId == -1){
            setViewLayoutParams(mLottieView,lottie_view_width,lottie_view_height);
            mTitle.setVisibility(View.GONE);
        }else {
            setViewLayoutParams(mLottieView,lottie_view_width,lottie_view_height);
            mTitle.setText(titleResId);
            mTitle.setVisibility(View.VISIBLE);
        }
    }

    public void setChecked() {
        if (getTag() ==null || !(boolean)getTag()){
            setTag(true);
            mTitle.setSelected(true);
            mLottieView.playAnimation();
            mTitle.setTextColor(lottie_text_color_select);
        }else {
            setTag(false);
            mTitle.setSelected(false);
            mLottieView.setProgress(0.0f);
            mLottieView.cancelAnimation();
            mTitle.setTextColor(lottie_text_color);
        }
    }


    class LottieAnimatorUpdateListener implements ValueAnimator.AnimatorUpdateListener {

        private NavItem navItem;

        LottieAnimatorUpdateListener(NavItem navItem) {
            this.navItem = navItem;
        }

        /**
         * 动画进度监听事件
         *
         * @param valueAnimator
         */
        @Override
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            if (valueAnimator.getAnimatedFraction() >= 0.3f) {
                //获取导航栏标题字体颜色资源ID
                if (navItem.getSelectedTextColor() != 0) {
                    mTitle.setTextColor(getResources().getColor(navItem.getSelectedTextColor()));
                }
            }
        }
    }

    public void setLottieSize(int nWidth ,int nHeight) {
        setViewLayoutParams(mLottieView,nWidth,nHeight);
    }
    public void setLottieSize(int nWidthOrnHeight) {
        setViewLayoutParams(mLottieView,nWidthOrnHeight,nWidthOrnHeight);
    }

    public void setLottieTextSize(int textSize) {
        mTitle.setTextSize(textSize);
    }
    public void setLottieNewSize(int nWidthOrnHeight) {
        setViewLayoutParams(mitemView,nWidthOrnHeight,nWidthOrnHeight);
    }
    public void setLottieNewVisibility(boolean visibility) {
        mitemView.setVisibility(visibility?View.VISIBLE:View.GONE);
    }
    public void setLottieNewPosX(int x) {
        mitemView.setPadding(x,0,0,0);
    }
    public void setLottieNewPosY(int y) {
        mitemView.setPadding(0,y,0,0);
    }


    public void setLottieNewStyle(int style) {
        if (style != -1){
            mitemView.setBackgroundResource(style);
        }
    }


    /**
     * 重设 view 的宽高
     */
    public static void setViewLayoutParams(View view, int nWidth ,int nHeight) {
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        if (lp.height != nHeight || lp.width != nWidth) {
            lp.width = nWidth;
            lp.height = nHeight;
            view.setLayoutParams(lp);
        }
    }
}

