import 'package:equatable/equatable.dart';

class LoginResponse extends Equatable {
  final String accessToken;
  final String expiresIn;
  final String tokenType;
  final String scope;

  LoginResponse({
    this.accessToken,
    this.expiresIn,
    this.tokenType,
    this.scope,
  }) : super([accessToken, scope]);
}