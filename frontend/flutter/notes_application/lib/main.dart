import 'package:flutter/material.dart';
import 'package:notes_application/core/navigation/route_generator.dart';
import 'injection_container.dart' as di;

void main() async {
  await di.init();
  runApp(NotesApp());
}

class NotesApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
        accentColor: Colors.green,
        visualDensity: VisualDensity.adaptivePlatformDensity,
      ),
      initialRoute: '/',
      onGenerateRoute: RouteGenerator.generateRoute,
    );
  }
}

class HomePage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Notes App"),
      ),
      body: Container(
        child: Column(
          children: <Widget>[
            RaisedButton(
              child: Text("Click to login!"),
              onPressed: (){
                Navigator.of(context).pushNamed('/login');
              },
            )
          ],
        ),
      )
    );
  }

}