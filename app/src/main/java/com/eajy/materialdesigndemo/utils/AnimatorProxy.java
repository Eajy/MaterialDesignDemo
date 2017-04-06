package com.eajy.materialdesigndemo.utils;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.os.Build;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

/**
 * Created by ANTVR-24 on 17/04/06.
 */

public class AnimatorProxy  extends Animation {
    public static final boolean NEEDS_PROXY = Build.VERSION.SDK_INT < 11;
    private static final WeakHashMap<View, AnimatorProxy> PROXIES = new WeakHashMap();
    private final WeakReference<View> mView;
    private final Camera mCamera = new Camera();
    private boolean mHasPivot;
    private float mAlpha = 1.0F;
    private float mPivotX;
    private float mPivotY;
    private float mRotationX;
    private float mRotationY;
    private float mRotationZ;
    private float mScaleX = 1.0F;
    private float mScaleY = 1.0F;
    private float mTranslationX;
    private float mTranslationY;
    private final RectF mBefore = new RectF();
    private final RectF mAfter = new RectF();
    private final Matrix mTempMatrix = new Matrix();

    public static AnimatorProxy wrap(View view) {
        AnimatorProxy proxy = (AnimatorProxy)PROXIES.get(view);
        if(proxy == null || proxy != view.getAnimation()) {
            proxy = new AnimatorProxy(view);
            PROXIES.put(view, proxy);
        }

        return proxy;
    }

    private AnimatorProxy(View view) {
        this.setDuration(0L);
        this.setFillAfter(true);
        view.setAnimation(this);
        this.mView = new WeakReference(view);
    }

    public float getAlpha() {
        return this.mAlpha;
    }

    public void setAlpha(float alpha) {
        if(this.mAlpha != alpha) {
            this.mAlpha = alpha;
            View view = (View)this.mView.get();
            if(view != null) {
                view.invalidate();
            }
        }

    }

    public float getPivotX() {
        return this.mPivotX;
    }

    public void setPivotX(float pivotX) {
        if(!this.mHasPivot || this.mPivotX != pivotX) {
            this.prepareForUpdate();
            this.mHasPivot = true;
            this.mPivotX = pivotX;
            this.invalidateAfterUpdate();
        }

    }

    public float getPivotY() {
        return this.mPivotY;
    }

    public void setPivotY(float pivotY) {
        if(!this.mHasPivot || this.mPivotY != pivotY) {
            this.prepareForUpdate();
            this.mHasPivot = true;
            this.mPivotY = pivotY;
            this.invalidateAfterUpdate();
        }

    }

    public float getRotation() {
        return this.mRotationZ;
    }

    public void setRotation(float rotation) {
        if(this.mRotationZ != rotation) {
            this.prepareForUpdate();
            this.mRotationZ = rotation;
            this.invalidateAfterUpdate();
        }

    }

    public float getRotationX() {
        return this.mRotationX;
    }

    public void setRotationX(float rotationX) {
        if(this.mRotationX != rotationX) {
            this.prepareForUpdate();
            this.mRotationX = rotationX;
            this.invalidateAfterUpdate();
        }

    }

    public float getRotationY() {
        return this.mRotationY;
    }

    public void setRotationY(float rotationY) {
        if(this.mRotationY != rotationY) {
            this.prepareForUpdate();
            this.mRotationY = rotationY;
            this.invalidateAfterUpdate();
        }

    }

    public float getScaleX() {
        return this.mScaleX;
    }

    public void setScaleX(float scaleX) {
        if(this.mScaleX != scaleX) {
            this.prepareForUpdate();
            this.mScaleX = scaleX;
            this.invalidateAfterUpdate();
        }

    }

    public float getScaleY() {
        return this.mScaleY;
    }

    public void setScaleY(float scaleY) {
        if(this.mScaleY != scaleY) {
            this.prepareForUpdate();
            this.mScaleY = scaleY;
            this.invalidateAfterUpdate();
        }

    }

    public int getScrollX() {
        View view = (View)this.mView.get();
        return view == null?0:view.getScrollX();
    }

    public void setScrollX(int value) {
        View view = (View)this.mView.get();
        if(view != null) {
            view.scrollTo(value, view.getScrollY());
        }

    }

    public int getScrollY() {
        View view = (View)this.mView.get();
        return view == null?0:view.getScrollY();
    }

    public void setScrollY(int value) {
        View view = (View)this.mView.get();
        if(view != null) {
            view.scrollTo(view.getScrollX(), value);
        }

    }

    public float getTranslationX() {
        return this.mTranslationX;
    }

    public void setTranslationX(float translationX) {
        if(this.mTranslationX != translationX) {
            this.prepareForUpdate();
            this.mTranslationX = translationX;
            this.invalidateAfterUpdate();
        }

    }

    public float getTranslationY() {
        return this.mTranslationY;
    }

    public void setTranslationY(float translationY) {
        if(this.mTranslationY != translationY) {
            this.prepareForUpdate();
            this.mTranslationY = translationY;
            this.invalidateAfterUpdate();
        }

    }

    public float getX() {
        View view = (View)this.mView.get();
        return view == null?0.0F:(float)view.getLeft() + this.mTranslationX;
    }

    public void setX(float x) {
        View view = (View)this.mView.get();
        if(view != null) {
            this.setTranslationX(x - (float)view.getLeft());
        }

    }

    public float getY() {
        View view = (View)this.mView.get();
        return view == null?0.0F:(float)view.getTop() + this.mTranslationY;
    }

    public void setY(float y) {
        View view = (View)this.mView.get();
        if(view != null) {
            this.setTranslationY(y - (float)view.getTop());
        }

    }

    private void prepareForUpdate() {
        View view = (View)this.mView.get();
        if(view != null) {
            this.computeRect(this.mBefore, view);
        }

    }

    private void invalidateAfterUpdate() {
        View view = (View)this.mView.get();
        if(view != null && view.getParent() != null) {
            RectF after = this.mAfter;
            this.computeRect(after, view);
            after.union(this.mBefore);
            ((View)view.getParent()).invalidate((int)Math.floor((double)after.left), (int)Math.floor((double)after.top), (int)Math.ceil((double)after.right), (int)Math.ceil((double)after.bottom));
        }
    }

    private void computeRect(RectF r, View view) {
        float w = (float)view.getWidth();
        float h = (float)view.getHeight();
        r.set(0.0F, 0.0F, w, h);
        Matrix m = this.mTempMatrix;
        m.reset();
        this.transformMatrix(m, view);
        this.mTempMatrix.mapRect(r);
        r.offset((float)view.getLeft(), (float)view.getTop());
        float f;
        if(r.right < r.left) {
            f = r.right;
            r.right = r.left;
            r.left = f;
        }

        if(r.bottom < r.top) {
            f = r.top;
            r.top = r.bottom;
            r.bottom = f;
        }

    }

    private void transformMatrix(Matrix m, View view) {
        float w = (float)view.getWidth();
        float h = (float)view.getHeight();
        boolean hasPivot = this.mHasPivot;
        float pX = hasPivot?this.mPivotX:w / 2.0F;
        float pY = hasPivot?this.mPivotY:h / 2.0F;
        float rX = this.mRotationX;
        float rY = this.mRotationY;
        float rZ = this.mRotationZ;
        if(rX != 0.0F || rY != 0.0F || rZ != 0.0F) {
            Camera sX = this.mCamera;
            sX.save();
            sX.rotateX(rX);
            sX.rotateY(rY);
            sX.rotateZ(-rZ);
            sX.getMatrix(m);
            sX.restore();
            m.preTranslate(-pX, -pY);
            m.postTranslate(pX, pY);
        }

        float sX1 = this.mScaleX;
        float sY = this.mScaleY;
        if(sX1 != 1.0F || sY != 1.0F) {
            m.postScale(sX1, sY);
            float sPX = -(pX / w) * (sX1 * w - w);
            float sPY = -(pY / h) * (sY * h - h);
            m.postTranslate(sPX, sPY);
        }

        m.postTranslate(this.mTranslationX, this.mTranslationY);
    }

    protected void applyTransformation(float interpolatedTime, Transformation t) {
        View view = (View)this.mView.get();
        if(view != null) {
            t.setAlpha(this.mAlpha);
            this.transformMatrix(t.getMatrix(), view);
        }

    }
}
