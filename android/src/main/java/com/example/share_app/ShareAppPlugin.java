package com.example.share_app;

import android.app.Activity;
import android.net.Uri;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/** ShareAppPlugin */
public class ShareAppPlugin implements MethodCallHandler {

  private Activity activity;
  private static CallbackManager callbackManager;
  private Registrar registrar;

  /**
   * Plugin registration.
   */
  private ShareAppPlugin(Registrar registrar) {
    this.activity = registrar.activity();
    this.registrar = registrar;
  }

  /** Plugin registration. */
  public static void registerWith(Registrar registrar) {
    final MethodChannel channel = new MethodChannel(registrar.messenger(), "share_app");
    channel.setMethodCallHandler(new ShareAppPlugin(registrar));
    callbackManager = CallbackManager.Factory.create();

  }

  @Override
  public void onMethodCall(MethodCall call, Result result) {
//    if (call.method.equals("getPlatformVersion")) {
//      result.success("Android " + android.os.Build.VERSION.RELEASE);
//    } else {
//      result.notImplemented();
//    }
    String url, msg;

    switch (call.method){
      case "shareFacebook":{
        url = call.argument("url");
        msg = call.argument("msg");
        shareToFacebook(url, msg, result);

      }break;
      default:
        result.notImplemented();
        break;
    }
  }

  /**
   * share to Facebook
   *
   * @param url    String
   * @param msg    String
   * @param result Result
   */
  private void shareToFacebook(String url, String msg, Result result) {

    ShareDialog shareDialog = new ShareDialog(activity);
    // this part is optional
    shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
      @Override
      public void onSuccess(Sharer.Result result) {
        System.out.println("--------------------success");
      }

      @Override
      public void onCancel() {
        System.out.println("-----------------onCancel");
      }

      @Override
      public void onError(FacebookException error) {
        System.out.println("---------------onError");
      }
    });

    ShareLinkContent content = new ShareLinkContent.Builder()
            .setContentUrl(Uri.parse(url))
            .setQuote(msg)
            .build();
    if (ShareDialog.canShow(ShareLinkContent.class)) {
      shareDialog.show(content);
      result.success("success");
    }else{
      result.success("打不开");

    }

  }
}
