import 'package:equatable/equatable.dart';
import 'package:flutter/cupertino.dart';

class LoginResponse extends Equatable {
  final String accessToken;
  final int expiresIn;
  final String tokenType;
  final String scope;

  LoginResponse({
    @required this.accessToken,
    @required this.expiresIn,
    @required this.tokenType,
    @required this.scope,
  }) : super([accessToken, scope]);
}