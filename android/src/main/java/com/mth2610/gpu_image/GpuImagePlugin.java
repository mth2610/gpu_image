package com.mth2610.gpu_image;

import android.graphics.SurfaceTexture;
import java.util.Map;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;
import io.flutter.view.TextureRegistry;
import android.view.View;
import android.opengl.GLSurfaceView;

import com.mth2610.gpu_image.filter.GPUImage3x3ConvolutionFilter;
import com.mth2610.gpu_image.filter.GPUImage3x3TextureSamplingFilter;
import com.mth2610.gpu_image.filter.GPUImageAddBlendFilter;
import com.mth2610.gpu_image.filter.GPUImageAlphaBlendFilter;
import com.mth2610.gpu_image.filter.GPUImageBilateralBlurFilter;
import com.mth2610.gpu_image.filter.GPUImageBoxBlurFilter;
import com.mth2610.gpu_image.filter.GPUImageBrightnessFilter;
import com.mth2610.gpu_image.filter.GPUImageBulgeDistortionFilter;
import com.mth2610.gpu_image.filter.GPUImageCGAColorspaceFilter;
import com.mth2610.gpu_image.filter.GPUImageChromaKeyBlendFilter;
import com.mth2610.gpu_image.filter.GPUImageColorBalanceFilter;
import com.mth2610.gpu_image.filter.GPUImageColorBlendFilter;
import com.mth2610.gpu_image.filter.GPUImageColorBurnBlendFilter;
import com.mth2610.gpu_image.filter.GPUImageColorDodgeBlendFilter;
import com.mth2610.gpu_image.filter.GPUImageColorInvertFilter;
import com.mth2610.gpu_image.filter.GPUImageColorMatrixFilter;
import com.mth2610.gpu_image.filter.GPUImageContrastFilter;
import com.mth2610.gpu_image.filter.GPUImageCrosshatchFilter;
import com.mth2610.gpu_image.filter.GPUImageDarkenBlendFilter;
import com.mth2610.gpu_image.filter.GPUImageDifferenceBlendFilter;
import com.mth2610.gpu_image.filter.GPUImageDilationFilter;
import com.mth2610.gpu_image.filter.GPUImageDirectionalSobelEdgeDetectionFilter;
import com.mth2610.gpu_image.filter.GPUImageDissolveBlendFilter;
import com.mth2610.gpu_image.filter.GPUImageEmbossFilter;
import com.mth2610.gpu_image.filter.GPUImageExclusionBlendFilter;
import com.mth2610.gpu_image.filter.GPUImageExposureFilter;
import com.mth2610.gpu_image.filter.GPUImageFalseColorFilter;
import com.mth2610.gpu_image.filter.GPUImageFilter;
import com.mth2610.gpu_image.filter.GPUImageGammaFilter;
import com.mth2610.gpu_image.filter.GPUImageGaussianBlurFilter;
import com.mth2610.gpu_image.filter.GPUImageGlassSphereFilter;
import com.mth2610.gpu_image.filter.GPUImageGrayscaleFilter;
import com.mth2610.gpu_image.filter.GPUImageHalftoneFilter;
import com.mth2610.gpu_image.filter.GPUImageHazeFilter;
import com.mth2610.gpu_image.filter.GPUImageHighlightShadowFilter;
import com.mth2610.gpu_image.filter.GPUImageHueFilter;
import com.mth2610.gpu_image.filter.GPUImageKuwaharaFilter;
import com.mth2610.gpu_image.filter.GPUImageLaplacianFilter;
import com.mth2610.gpu_image.filter.GPUImageLevelsFilter;
import com.mth2610.gpu_image.filter.GPUImageLightenBlendFilter;
import com.mth2610.gpu_image.filter.GPUImageLinearBurnBlendFilter;
import com.mth2610.gpu_image.filter.GPUImageLookupFilter;
import com.mth2610.gpu_image.filter.GPUImageLuminanceFilter;
import com.mth2610.gpu_image.filter.GPUImageLuminanceThresholdFilter;
import com.mth2610.gpu_image.filter.GPUImageMixBlendFilter;
import com.mth2610.gpu_image.filter.GPUImageMonochromeFilter;
import com.mth2610.gpu_image.filter.GPUImageMultiplyBlendFilter;
import com.mth2610.gpu_image.filter.GPUImageNonMaximumSuppressionFilter;
import com.mth2610.gpu_image.filter.GPUImageNormalBlendFilter;
import com.mth2610.gpu_image.filter.GPUImageOpacityFilter;
import com.mth2610.gpu_image.filter.GPUImageOverlayBlendFilter;
import com.mth2610.gpu_image.filter.GPUImagePixelationFilter;
import com.mth2610.gpu_image.filter.GPUImagePosterizeFilter;
import com.mth2610.gpu_image.filter.GPUImageRGBDilationFilter;
import com.mth2610.gpu_image.filter.GPUImageRGBFilter;
import com.mth2610.gpu_image.filter.GPUImageSaturationBlendFilter;
import com.mth2610.gpu_image.filter.GPUImageSaturationFilter;
import com.mth2610.gpu_image.filter.GPUImageScreenBlendFilter;
import com.mth2610.gpu_image.filter.GPUImageSepiaToneFilter;
import com.mth2610.gpu_image.filter.GPUImageSharpenFilter;
import com.mth2610.gpu_image.filter.GPUImageSketchFilter;
import com.mth2610.gpu_image.filter.GPUImageSmoothToonFilter;
import com.mth2610.gpu_image.filter.GPUImageSobelThresholdFilter;
import com.mth2610.gpu_image.filter.GPUImageSoftLightBlendFilter;
import com.mth2610.gpu_image.filter.GPUImageSolarizeFilter;
import com.mth2610.gpu_image.filter.GPUImageSourceOverBlendFilter;
import com.mth2610.gpu_image.filter.GPUImageSphereRefractionFilter;
import com.mth2610.gpu_image.filter.GPUImageSubtractBlendFilter;
import com.mth2610.gpu_image.filter.GPUImageSwirlFilter;
import com.mth2610.gpu_image.filter.GPUImageThresholdEdgeDetectionFilter;
import com.mth2610.gpu_image.filter.GPUImageToneCurveFilter;
import com.mth2610.gpu_image.filter.GPUImageToonFilter;
import com.mth2610.gpu_image.filter.GPUImageTransformFilter;
import com.mth2610.gpu_image.filter.GPUImageTwoInputFilter;
import com.mth2610.gpu_image.filter.GPUImageTwoPassFilter;
import com.mth2610.gpu_image.filter.GPUImageTwoPassTextureSamplingFilter;
import com.mth2610.gpu_image.filter.GPUImageVibranceFilter;
import com.mth2610.gpu_image.filter.GPUImageVignetteFilter;
import com.mth2610.gpu_image.filter.GPUImageWeakPixelInclusionFilter;
import com.mth2610.gpu_image.filter.GPUImageWhiteBalanceFilter;
import com.mth2610.gpu_image.filter.GPUImageZoomBlurFilter;

import android.media.ExifInterface;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;


/** GpuImagePlugin */
public class GpuImagePlugin implements MethodCallHandler {
  private final TextureRegistry textures;
  private final Registrar mRegistrar;
  private final GPUImageFilter[] FILTERS = {
          new GPUImage3x3ConvolutionFilter(),
          new GPUImage3x3TextureSamplingFilter(),
          new GPUImageAddBlendFilter(),
          new GPUImageAlphaBlendFilter(),
          new GPUImageBilateralBlurFilter(),
          new GPUImageBoxBlurFilter(),
          new GPUImageBrightnessFilter(),
          new GPUImageBulgeDistortionFilter(),
          new GPUImageCGAColorspaceFilter(),
          new GPUImageChromaKeyBlendFilter(),
          new GPUImageColorBalanceFilter(),
          new GPUImageColorBlendFilter(),
          new GPUImageColorBurnBlendFilter(),
          new GPUImageColorDodgeBlendFilter(),
          new GPUImageColorInvertFilter(),
          new GPUImageColorMatrixFilter(),
          new GPUImageContrastFilter(),
          new GPUImageCrosshatchFilter(),
          new GPUImageDarkenBlendFilter(),
          new GPUImageDifferenceBlendFilter(),
          new GPUImageDilationFilter(),
          new GPUImageDirectionalSobelEdgeDetectionFilter(),
          new GPUImageDissolveBlendFilter(),
          new GPUImageEmbossFilter(),
          new GPUImageExclusionBlendFilter(),
          new GPUImageExposureFilter(),
          new GPUImageFalseColorFilter(),
          new GPUImageGammaFilter(),
          new GPUImageGaussianBlurFilter(),
          new GPUImageGlassSphereFilter(),
          new GPUImageGrayscaleFilter(),
          new GPUImageHalftoneFilter(),
          new GPUImageHighlightShadowFilter(),
          new GPUImageHueFilter(),
          new GPUImageHazeFilter(),
          new GPUImageKuwaharaFilter(),
          new GPUImageLaplacianFilter(),
          new GPUImageLevelsFilter(),
          new GPUImageLightenBlendFilter(),
          new GPUImageLinearBurnBlendFilter(),
          new GPUImageLookupFilter(),
          new GPUImageLuminanceFilter(),
          new GPUImageLuminanceThresholdFilter(),
          new GPUImageLightenBlendFilter(),
          //new GPUImageMixBlendFilter(),
          new GPUImageMonochromeFilter(),
          new GPUImageMultiplyBlendFilter(),
          new GPUImageNonMaximumSuppressionFilter(),
          new GPUImageNormalBlendFilter(),
          new GPUImageOpacityFilter(),
          new GPUImageOverlayBlendFilter(),
          new GPUImagePixelationFilter(),
          new GPUImagePosterizeFilter(),
          new GPUImageRGBDilationFilter(),
          new GPUImageRGBFilter(),
          new GPUImageSaturationBlendFilter(),
          new GPUImageSaturationFilter(),
          new GPUImageScreenBlendFilter(),
          new GPUImageSepiaToneFilter(),
          new GPUImageSharpenFilter(),
          new GPUImageSketchFilter(),
          new GPUImageSmoothToonFilter(),
          new GPUImageSobelThresholdFilter(),
          new GPUImageSobelThresholdFilter(),
          new GPUImageSoftLightBlendFilter(),
          new GPUImageSolarizeFilter(),
          new GPUImageSourceOverBlendFilter(),
          new GPUImageSphereRefractionFilter(),
          new GPUImageSubtractBlendFilter(),
          new GPUImageSwirlFilter(),
          new GPUImageThresholdEdgeDetectionFilter(),
          new GPUImageToneCurveFilter(),
          new GPUImageToonFilter(),
          new GPUImageTransformFilter(),
          //new GPUImageTwoInputFilter(),
          //new GPUImageTwoPassFilter(),
          //new GPUImageTwoPassTextureSamplingFilter(),
          new GPUImageVibranceFilter(),
          new GPUImageVignetteFilter(),
          new GPUImageWeakPixelInclusionFilter(),
          new GPUImageWhiteBalanceFilter(),
          new GPUImageZoomBlurFilter(),

  };


  public GpuImagePlugin(TextureRegistry textures, Registrar registrar) {
    this.textures = textures;
    this.mRegistrar = registrar;
  }

  /** Plugin registration. */
  public static void registerWith(Registrar registrar) {
    final MethodChannel channel = new MethodChannel(registrar.messenger(), "gpu_image");
    channel.setMethodCallHandler(new GpuImagePlugin(registrar.textures(), registrar));
  }

  @Override
  public void onMethodCall(MethodCall call, Result result) {
    Map<String, Number> arguments = (Map<String, Number>) call.arguments;
    if (call.method.equals("create")) {
      TextureRegistry.SurfaceTextureEntry entry = textures.createSurfaceTexture();
      SurfaceTexture surfaceTexture = entry.surfaceTexture();
      int width = arguments.get("width").intValue();
      int height = arguments.get("height").intValue();
      surfaceTexture.setDefaultBufferSize(width, height);
      result.success(entry.id());
    } else if(call.method.equals("process")) {
      TextureRegistry.SurfaceTextureEntry entry = textures.createSurfaceTexture();
      SurfaceTexture surfaceTexture = entry.surfaceTexture();
      int width = arguments.get("width").intValue();
      int height = arguments.get("height").intValue();
      int filter = arguments.get("filter").intValue();
//      String
      String inputFilePath = call.argument("inputFilePath");
      String outputFilePath = call.argument("outputFilePath");
      surfaceTexture.setDefaultBufferSize(width, height);
      GPUImage gpuImage = new GPUImage(mRegistrar.context());
      Bitmap inputBitmap = BitmapFactory.decodeFile(inputFilePath);
      String outputFileName = String.valueOf(System.currentTimeMillis()) + ".png";
      gpuImage.setFilter(FILTERS[filter]);
      try {
          ExifInterface inputExif = new ExifInterface(inputFilePath);
          gpuImage.saveToPictures(inputBitmap, outputFilePath, outputFileName, null, result, inputExif);
      }catch (Exception e){
        result.error("error", "error", e.toString());
        inputBitmap.recycle();
      }catch (Error e){
        result.error("error", "error", e.toString());
        inputBitmap.recycle();
      }
      Log.i("test4", "test4");
    }
  }

}
