import 'dart:convert';
import 'package:http/http.dart' as http;
import 'package:flutter_test/flutter_test.dart';
import 'package:mockito/mockito.dart';
import 'package:notes_application/core/error/exceptions.dart';
import 'package:notes_application/core/usecases/usecase.dart';
import 'package:notes_application/features/login/data/datasources/login_remote_data_source.dart';
import 'package:notes_application/features/login/data/models/login_response_model.dart';
import 'package:matcher/matcher.dart';

import '../../../../fixtures/fixture_reader.dart';

class MockHttpClient extends Mock implements http.Client {}

void main() {
  LoginRemoteDataSourceImpl dataSource;
  MockHttpClient mockHttpClient;
  LoginParams loginParams = LoginParams();
  final Map<String, String> body = {
    'grant_type': 'client_credentials',
    'client_id': 'oAuthClient',
    'client_secret': '15FCE3AC-C999-4735-99FD-AB6A04F67415'
  };

  setUp(() {
    mockHttpClient = MockHttpClient();
    dataSource = LoginRemoteDataSourceImpl(client: mockHttpClient);
  });


  void setUpMockHttpClientSuccess200() {
    when(
        mockHttpClient.post(
            any,
            body: body
        )
    ).thenAnswer((_) async => http.Response(fixture('login_response.json'), 200));
  }

  void setUpMockHttpClientFailure404() {
    when(
        mockHttpClient.post(
            any,
            body: body
        )
    ).thenAnswer((_) async => http.Response('Something went wrong', 404),
    );
  }


  group('login', (){
    final tLoginResponseModel = LoginResponseModel.fromJson(json.decode(fixture('login_response.json')));

    test('should perform a POST request on a URL with the /oauth endpoint and with application/json header', (){
      setUpMockHttpClientSuccess200();
      dataSource.login(loginParams);

      verify(
          mockHttpClient.post(
              any,
              body: body
          )
      );
    });

    test(
      'should return Login when the response code is 200 (success)',
          () async {
        // arrange
        setUpMockHttpClientSuccess200();
        // act
        final result = await dataSource.login(loginParams);
        // assert
        expect(result, equals(tLoginResponseModel));
      },
    );

    test(
      'should perform a POST request with the correct form-unencoded request body',
        () async {
        setUpMockHttpClientSuccess200();
          // act
          final result = await dataSource.login(loginParams);
          // assert
          expect(result, equals(tLoginResponseModel));
        }
    );

    test(
      'should throw a ServerException when the response code is 404 or other',
          () async {
           setUpMockHttpClientFailure404();
        // act
        final call = dataSource.login;
        // assert
        expect(() => call(loginParams), throwsA(TypeMatcher<ServerException>()));
      },
    );


  });
}