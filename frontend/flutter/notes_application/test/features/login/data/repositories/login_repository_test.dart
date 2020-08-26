import 'package:dartz/dartz.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:mockito/mockito.dart';
import 'package:notes_application/core/error/exceptions.dart';
import 'package:notes_application/core/error/failures.dart';
import 'package:notes_application/core/usecases/usecase.dart';
import 'package:notes_application/features/login/data/datasources/login_remote_data_source.dart';
import 'package:notes_application/features/login/data/models/login_response_model.dart';
import 'package:notes_application/features/login/data/repositories/login_repository.dart';

class MockLoginRemoteDataSource extends Mock implements LoginRemoteDataSource {}
class MockLoginResponseModel extends Mock implements LoginResponseModel {}

void main() {
  LoginRepositoryImpl repository;
  MockLoginRemoteDataSource mockLoginRemoteDataSource;
  MockLoginResponseModel mockLoginResponse;
  final loginParams = LoginParams();

  setUp((){
    mockLoginRemoteDataSource = MockLoginRemoteDataSource();
    mockLoginResponse = MockLoginResponseModel();
    repository = LoginRepositoryImpl(
      remoteDataSource: mockLoginRemoteDataSource
    );
  });

  group(
    'device is online', () {

      test('should return remote data when the call to remote data source is successful', () async {
        when(mockLoginRemoteDataSource.login(loginParams)).thenAnswer((_) async => mockLoginResponse);
        final result = await repository.login(loginParams);
        verify(mockLoginRemoteDataSource.login(loginParams));
        expect(result, equals(Right(mockLoginResponse)));
      });

      test(
        'should return server failure when the call to remote data source is unsuccessful',
            () async {
          // arrange
              when(mockLoginRemoteDataSource.login(loginParams))
              .thenThrow(ServerException());
          // act
          final result = await repository.login(loginParams);
          // assert
          verify(mockLoginRemoteDataSource.login(loginParams));
          expect(result, equals(Left(ServerFailure())));
        },
      );


  }
  );
}