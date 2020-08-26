import 'dart:convert';

import 'package:flutter_test/flutter_test.dart';
import 'package:notes_application/features/login/data/models/login_response_model.dart';
import 'package:notes_application/features/login/domain/entities/login_response.dart';

import '../../../../fixtures/fixture_reader.dart';

void main() {
  final loginResponseModel = LoginResponseModel(
    tokenType: "Bearer",
    scope: "notesApi.all",
    expiresIn: 3600,
    accessToken: "eyJhbGciOiJSUzI1NiIsImtpZCI6IjJCNUQzRDg2MTVENjNGNEY0MThGRkYxNDE5NEZBQzdBODBCRjcyNDRSUzI1NiIsInR5cCI6ImF0K2p3dCIsIng1dCI6IksxMDloaFhXUDA5QmpfOFVHVS1zZW9DX2NrUSJ9.eyJuYmYiOjE1OTgxOTc2NDYsImV4cCI6MTU5ODIwMTI0NiwiaXNzIjoiaHR0cDovL2F1dGhzZXJ2ZXIiLCJhdWQiOiJub3Rlc0FwaSIsImNsaWVudF9pZCI6Im9BdXRoQ2xpZW50IiwianRpIjoiQ0M3NkQxMjRDMUE0MzVCOUM4NzRERUU1ODA2Qjg3MkUiLCJpYXQiOjE1OTgxOTc2NDYsInNjb3BlIjpbIm5vdGVzQXBpLmFsbCJdfQ.hXN7eI3JW-00-uX2C3-Rs1fblEBrdM-sHjab-tvzgkehqule3akeL8q_uAqW6pPt_4m8RPxdJn5SQ0e9F6leM6s5C0xxi_0VrQgxRs4efva6Ygnlj4hVfT-mSGxk63P5_h-a3BXAl0j8AV1w_wouWjfIuyErzCyF_nX0IAOJziIhhG7dwNFOaL5IdA6jczUMfLoY54fRU710OmmQQGmLPILktCZPEVOXe7WAtcSR-Wzxp9JebuMC47StdM-rpPMtq32Dq2H71fGzt_WhWN4HmzlFK1Sm1RB6IUFq8ARtGfrFS4t2fAIJE6aDUS-MIi8vU1R-XbAyDhP1PupPbTYHNA"
  );

  test(
    'should be a subclass of LoginResponse entity',
      () async {
      expect(loginResponseModel, isA<LoginResponse>());
      }
  );

  group('fromJson', () {
    test(
      'should return a valid model',
          () async {
        // arrange
        final Map<String, dynamic> jsonMap =
        json.decode(fixture('login_response.json'));
        // act
        final result = LoginResponseModel.fromJson(jsonMap);
        // assert
        expect(result, loginResponseModel);
      },
    );
  });

  group('toJson',(){
    test('shoudl return a JSON map containing the proper data', () async {
      final result = loginResponseModel.toJson();
      final expectedJsonMap = {
        "access_token": "eyJhbGciOiJSUzI1NiIsImtpZCI6IjJCNUQzRDg2MTVENjNGNEY0MThGRkYxNDE5NEZBQzdBODBCRjcyNDRSUzI1NiIsInR5cCI6ImF0K2p3dCIsIng1dCI6IksxMDloaFhXUDA5QmpfOFVHVS1zZW9DX2NrUSJ9.eyJuYmYiOjE1OTgxOTc2NDYsImV4cCI6MTU5ODIwMTI0NiwiaXNzIjoiaHR0cDovL2F1dGhzZXJ2ZXIiLCJhdWQiOiJub3Rlc0FwaSIsImNsaWVudF9pZCI6Im9BdXRoQ2xpZW50IiwianRpIjoiQ0M3NkQxMjRDMUE0MzVCOUM4NzRERUU1ODA2Qjg3MkUiLCJpYXQiOjE1OTgxOTc2NDYsInNjb3BlIjpbIm5vdGVzQXBpLmFsbCJdfQ.hXN7eI3JW-00-uX2C3-Rs1fblEBrdM-sHjab-tvzgkehqule3akeL8q_uAqW6pPt_4m8RPxdJn5SQ0e9F6leM6s5C0xxi_0VrQgxRs4efva6Ygnlj4hVfT-mSGxk63P5_h-a3BXAl0j8AV1w_wouWjfIuyErzCyF_nX0IAOJziIhhG7dwNFOaL5IdA6jczUMfLoY54fRU710OmmQQGmLPILktCZPEVOXe7WAtcSR-Wzxp9JebuMC47StdM-rpPMtq32Dq2H71fGzt_WhWN4HmzlFK1Sm1RB6IUFq8ARtGfrFS4t2fAIJE6aDUS-MIi8vU1R-XbAyDhP1PupPbTYHNA",
        "expires_in": 3600,
        "token_type": "Bearer",
        "scope": "notesApi.all"
      };
      expect(result, expectedJsonMap);
    });
  });
}