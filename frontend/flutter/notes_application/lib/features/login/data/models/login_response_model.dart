import 'package:meta/meta.dart';
import 'package:notes_application/features/login/domain/entities/login_response.dart';

class LoginResponseModel extends LoginResponse {
  LoginResponseModel({
    @required accessToken,
    @required expiresIn,
    @required tokenType,
    @required scope,
  }) : super (
    scope: scope,
    accessToken: accessToken,
    tokenType: tokenType,
    expiresIn: expiresIn
  );

  factory LoginResponseModel.fromJson(Map<String, dynamic> json) {
    return LoginResponseModel(
      accessToken: json['access_token'],
      expiresIn: json['expires_in'],
      scope: json['scope'],
      tokenType: json['token_type']
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'access_token': accessToken,
      'expires_in': expiresIn,
      'scope': scope,
      'token_type': tokenType
    };
  }
}
