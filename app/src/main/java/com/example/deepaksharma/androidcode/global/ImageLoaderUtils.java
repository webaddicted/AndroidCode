package com.example.deepaksharma.androidcode.global;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.deepaksharma.androidcode.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;

public class ImageLoaderUtils {
    private static Context mContext = AppApplication.getInstance();

    //    {START UNIVERSAL IMAGE LOADER }
    public static ImageLoaderConfiguration getImageConfig() {
        String[] imageLoadersArray = mContext.getResources().getStringArray(R.array.image_loader);
        ImageLoaderConfiguration config = null;
        if (config == null) {
            config = new ImageLoaderConfiguration.Builder(AppApplication.getInstance())
//                    .memoryCacheSize(175 * 1024)
//                    .diskCacheSize(175 * 1024)
//                    .imageDecoder(new SvgDecoder().decode())
                    .imageDecoder(new BaseImageDecoder(true))
                    .defaultDisplayImageOptions(imageLoaders(imageLoadersArray[0]))
                    .build();
        }
        return config;
    }

    /**
     * show image using UIL
     *
     * @param imageView       image view
     * @param url             image url
     * @param imageLoaderType type of place holder show which is define in string
     */
    public static void loaderListImage(ImageView imageView, String url, String imageLoaderType) {
        ImageLoader.getInstance().displayImage(url, imageView, imageLoaders(imageLoaderType));
    }

    /**
     * show image using UIL
     *
     * @param imageView       image view
     * @param url             image url
     * @param imageLoaderType type of place holder show which is define in string
     */
    public static void loaderListImage(CircleImageView imageView, String url, String imageLoaderType) {
        ImageLoader.getInstance().displayImage(url, imageView, imageLoaders(imageLoaderType));
    }

    /**
     * @param imageLoaderType type of place holder show which is define in string
     */
    public static DisplayImageOptions imageLoaders(String imageLoaderType) {
        String[] imageLoadersArray = mContext.getResources().getStringArray(R.array.image_loader);
        DisplayImageOptions.Builder options = new DisplayImageOptions.Builder();
        if (imageLoadersArray[0].equals(imageLoaderType)) {
            options.showImageOnLoading(R.color.app_color);
            options.showImageOnFail(R.color.app_color);
        } else if (imageLoadersArray[1].equals(imageLoaderType)) {
            options.showImageOnLoading(R.drawable.logo);
            options.showImageOnFail(R.drawable.logo);
        } else {
            options.showImageOnLoading(R.color.app_color);
            options.showImageOnFail(R.color.app_color);
        }
        options.cacheInMemory(true);
        options.cacheOnDisk(true);
        options.bitmapConfig(Bitmap.Config.ARGB_8888);
        options.imageScaleType(ImageScaleType.EXACTLY);
        options.build();
        return options.build();
    }

    /**
     * @param imageLoaderType type of place holder show which is define in string
     */
    public Bitmap getBitmapUIL(String url, String imageLoaderType) {
        return ImageLoader.getInstance().loadImageSync(url, imageLoaders(imageLoaderType));
    }
    //    {END UNIVERSAL IMAGE LOADER }

//    {START SHOW IMAGE USING GLIDE}

    /**
     * show image using glide
     *
     * @param imagePath       image url
     * @param targetIv        image view
     * @param imageLoaderType type of place holder show which is define in string
     */
    public static void showImageUsingGLIDE(String imagePath, final ImageView targetIv, String imageLoaderType) {
        DrawableTypeRequest<String> drawableTypeRequest = Glide.with(targetIv.getContext())
                .load(imagePath);
        getPlaceHolder(drawableTypeRequest, imageLoaderType).into(targetIv);
    }

    /**
     * show image using glide
     *
     * @param imgUrl          local image file path
     * @param targetIv        image view
     * @param imageLoaderType type of place holder show which is define in string
     */
    public static void showImageUsingGLIDE(File imgUrl, final ImageView targetIv, String imageLoaderType) {
        DrawableTypeRequest<Uri> drawableTypeRequest = Glide.with(targetIv.getContext())
                .load(Uri.fromFile(imgUrl));
        getPlaceHolder(drawableTypeRequest, imageLoaderType).into(targetIv);
    }

    /**
     * apply different type of place holder
     *
     * @param drawableTypeRequest
     * @param imageLoaderType     type of place holder show
     * @param <T>
     * @return
     */
    private static <T> DrawableTypeRequest<T> getPlaceHolder(DrawableTypeRequest<T> drawableTypeRequest, String imageLoaderType) {
        String[] imageLoadersArray = mContext.getResources().getStringArray(R.array.image_loader);
        if (imageLoadersArray[0].equals(imageLoaderType)) {
            drawableTypeRequest.error(R.color.app_color);
            drawableTypeRequest.placeholder(R.color.app_color);
        } else if (imageLoadersArray[1].equals(imageLoaderType)) {
            drawableTypeRequest.error(R.drawable.logo);
            drawableTypeRequest.placeholder(R.drawable.logo);
        } else {
            drawableTypeRequest.error(R.color.app_color);
            drawableTypeRequest.placeholder(R.color.app_color);
        }
        return drawableTypeRequest;
    }

    //    {END SHOW IMAGE USING GLIDE}
}
