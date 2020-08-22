import 'package:dartz/dartz.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:mockito/mockito.dart';
import 'package:notes_application/core/usecases/usecase.dart';
import 'package:notes_application/features/login/domain/entities/login_response.dart';
import 'package:notes_application/features/login/domain/repositories/login_repository.dart';
import 'package:notes_application/features/login/domain/usecases/Login.dart';

class MockLoginRepository extends Mock
    implements LoginRepository {}

void main() {
  Login usecase;
  MockLoginRepository mockLoginRepository;

  setUp(() {
    mockLoginRepository = MockLoginRepository();
    usecase = Login(mockLoginRepository);
  });

  final loginResponse = LoginResponse(
      accessToken: "accessToken",
      expiresIn: "3600",
      scope: "NotesApi.all",
      tokenType: "Bearer"
  );

  test('should login from the repository', () async {
    when(mockLoginRepository.login(any)).thenAnswer((_) async => Right(loginResponse));
    final loginParams = LoginParams();
    final result = await usecase(loginParams);
    expect(result, Right(loginResponse));
    verify(mockLoginRepository.login(loginParams));
    verifyNoMoreInteractions(mockLoginRepository);
  });
}