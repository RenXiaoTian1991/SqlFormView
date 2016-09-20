package com.example.admin.formviewmy;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;


public class CustomImageView extends TextView
{
	/**
	 * �ؼ��Ŀ�
	 */
	private int mWidth;
	/**
	 * �ؼ��ĸ�
	 */
	private int mHeight;
	/**
	 * ͼƬ�Ľ���
	 */
	private String mTitle;
	/**
	 * �������ɫ
	 */
	private int mTextColor;
	/**
	 * ����Ĵ�С
	 */
	private int mTextSize = 14;

	private Paint mPaint;
	/**
	 * ���ı���Լ��
	 */
	private Rect mTextBound;
	/**
	 * �������岼��
	 */
	private Rect rect;

	public CustomImageView(Context context, AttributeSet attrs)
	{
		this(context, attrs, 0);
	}

	public CustomImageView(Context context)
	{
		this(context, null);
	}

	/**
	 * ��ʼ���������Զ�������
	 * 
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public CustomImageView(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
		rect = new Rect();
		mPaint = new Paint();
		mTextBound = new Rect();
		mPaint.setTextSize(mTextSize);
		mPaint.getTextBounds(getText().toString(), 0, getText().toString().length(), mTextBound);

	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		// super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		/**
		 * ���ÿ��
		 */
		int specMode = MeasureSpec.getMode(widthMeasureSpec);
		int specSize = MeasureSpec.getSize(widthMeasureSpec);

		if (specMode == MeasureSpec.EXACTLY)// match_parent , accurate
		{
			mWidth = specSize;
		} else
		{
			int desireByTitle = getPaddingLeft() + getPaddingRight() + mTextBound.width();

			if (specMode == MeasureSpec.AT_MOST)// wrap_content
			{
				mWidth = Math.min(desireByTitle, specSize);
			}
		}

		/***
		 * ���ø߶�
		 */

		specMode = MeasureSpec.getMode(heightMeasureSpec);
		specSize = MeasureSpec.getSize(heightMeasureSpec);
		if (specMode == MeasureSpec.EXACTLY)// match_parent , accurate
		{
			mHeight = specSize;
		} else
		{
			int desire = getPaddingTop() + getPaddingBottom() + mTextBound.height();
			if (specMode == MeasureSpec.AT_MOST)// wrap_content
			{
				mHeight = Math.min(desire, specSize);
			}
		}
		setMeasuredDimension(mWidth, mHeight);

	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		// super.onDraw(canvas);
		/**
		 * �߿�
		 */
		mPaint.setStrokeWidth(4);
		mPaint.setStyle(Style.STROKE);
		mPaint.setColor(Color.CYAN);
		canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);

		rect.left = getPaddingLeft();
		rect.right = mWidth - getPaddingRight();
		rect.top = getPaddingTop();
		rect.bottom = mHeight - getPaddingBottom();

		mPaint.setColor(mTextColor);
		mPaint.setStyle(Style.FILL);
		/**
		 * ��ǰ���õĿ��С��������Ҫ�Ŀ�ȣ��������Ϊxxx...
		 */
		if (mTextBound.width() > mWidth)
		{
			TextPaint paint = new TextPaint(mPaint);
			String msg = TextUtils.ellipsize(mTitle, paint, (float) mWidth - getPaddingLeft() - getPaddingRight(),
					TextUtils.TruncateAt.END).toString();
			canvas.drawText(msg, getPaddingLeft(), mHeight - getPaddingBottom(), mPaint);

		} else
		{
			//������������������
			canvas.drawText(mTitle, mWidth / 2 - mTextBound.width() * 1.0f / 2, mHeight - getPaddingBottom(), mPaint);
		}

		//ȡ��ʹ�õ��Ŀ�
		rect.bottom -= mTextBound.height();

	}

}
