import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:share_app/share_app.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  // String _platformVersion = 'Unknown';

  @override
  void initState() {
    super.initState();
    // initPlatformState();
  }

  // Platform messages are asynchronous, so we initialize in an async method.
  // Future<void> initPlatformState() async {
  //   String platformVersion;
  //   // Platform messages may fail, so we use a try/catch PlatformException.
  //   try {
  //     platformVersion = await ShareApp.platformVersion;
  //   } on PlatformException {
  //     platformVersion = 'Failed to get platform version.';
  //   }

  //   // If the widget was removed from the tree while the asynchronous platform
  //   // message was in flight, we want to discard the reply rather than calling
  //   // setState to update our non-existent appearance.
  //   if (!mounted) return;

  //   setState(() {
  //     _platformVersion = platformVersion;
  //   });
  // }
  String msg = 'hello,this is my github:https://github.com/lizhuoyuan';

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Container(
            width: double.infinity,
            child: Column(
                crossAxisAlignment: CrossAxisAlignment.center,
                children: <Widget>[
                  // Image.memory(
                  //   base64.decode(base64Image.split(',')[1]),
                  //   height: 312,
                  //   width: 175.3,
                  //   fit: BoxFit.fill,
                  //   gaplessPlayback: true,
                  // ),
                  SizedBox(height: 30),
                  RaisedButton(
                    child: Text('share to twitter'),
                    onPressed: () async {
                      var response = await ShareApp.shareFacebook(
                          url: 'https://github.com/lizhuoyuan', msg: msg);
                      if (response == 'success') {
                        print('分享回调：' + response);
                      }
                    },
                  ),
                ])),
      ),
    );
  }
}
