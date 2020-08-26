import 'dart:convert';
import 'package:flutter/cupertino.dart';
import 'package:notes_application/core/error/exceptions.dart';
import 'package:notes_application/core/usecases/usecase.dart';
import 'package:notes_application/features/login/data/models/login_response_model.dart';
import 'package:http/http.dart' as http;

abstract class LoginRemoteDataSource {
  /// calls the login endpoint
  ///
  /// Throws a [ServerException] for all error codes
  Future<LoginResponseModel> login(LoginParams loginParams);
}

class LoginRemoteDataSourceImpl implements LoginRemoteDataSource {
  final http.Client client;

  LoginRemoteDataSourceImpl({@required this.client});

  @override
  Future<LoginResponseModel> login(LoginParams loginParams) async {
    final url = 'http://10.0.2.2:80/oauth';

    Map<String, String> body = {
      'grant_type': 'client_credentials',
      'client_id': 'oAuthClient',
      'client_secret': '15FCE3AC-C999-4735-99FD-AB6A04F67415'
    };

    final response = await client.post(
      url,
      body: body
    );

    if (response.statusCode == 200) {
      return LoginResponseModel.fromJson(json.decode(response.body));
    } else {
      throw ServerException();
    }
  }
}
