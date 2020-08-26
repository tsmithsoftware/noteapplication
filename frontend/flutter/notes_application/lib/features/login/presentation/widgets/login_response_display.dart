
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:notes_application/features/login/domain/entities/login_response.dart';

class LoginResponseDisplay extends StatelessWidget {
  final LoginResponse response;

  const LoginResponseDisplay({
    Key key,
    this.response,
  })  : assert(response != null),
        super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      color: Colors.white,
      height: MediaQuery.of(context).size.height / 2,
      child: Column(
        children: <Widget>[
          // Fixed size, doesn't scroll
          Text(
            "Response Received!",
            style: TextStyle(
              fontSize: 50,
              fontWeight: FontWeight.bold,
            ),
          ),
          // Expanded makes it fill in all the remaining space
          Expanded(
            child: Center(
              // Only the trivia "message" part will be scrollable
              child: SingleChildScrollView(
                child: Text(
                  response.toString(),
                  style: TextStyle(fontSize: 25),
                  textAlign: TextAlign.center,
                ),
              ),
            ),
          )
        ],
      ),
    );
  }
}