import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:notes_application/core/usecases/usecase.dart';
import 'package:notes_application/features/login/presentation/bloc/bloc.dart';
import 'package:notes_application/features/login/presentation/bloc/sign_in_bloc.dart';

class LoginControls extends StatefulWidget {
  const LoginControls({
    Key key,
  }) : super(key: key);

  @override
  _LoginControlsState createState() => _LoginControlsState();
}

class _LoginControlsState extends State<LoginControls> {

  @override
  Widget build(BuildContext context) {
    return Column(
      children: <Widget>[
        SizedBox(height: 10),
        Row(
          children: <Widget>[
            Expanded(
              child: RaisedButton(
                child: Text('Get Login'),
                color: Theme.of(context).accentColor,
                textTheme: ButtonTextTheme.primary,
                onPressed: dispatchLogin,
              ),
            ),
            SizedBox(width: 10)
          ],
        )
      ],
    );
  }

  void dispatchLogin() {
    BlocProvider.of<SignInBloc>(context)
        .add(SignInUserEvent(LoginParams()));
  }
}