import 'package:dartz/dartz.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:mockito/mockito.dart';
import 'package:notes_application/core/error/failures.dart';
import 'package:notes_application/core/usecases/usecase.dart';
import 'package:notes_application/features/login/domain/entities/login_response.dart';
import 'package:notes_application/features/login/domain/usecases/login.dart';
import 'package:notes_application/features/login/presentation/bloc/bloc.dart';

class MockLogin extends Mock implements Login {}
class MockLoginResponse extends Mock implements LoginResponse {}

void main() {
  SignInBloc bloc;
  MockLogin mockLogin;
  MockLoginResponse mockLoginResponse;

  void setUpLoginSuccess(MockLogin mockLogin, LoginParams params) {
    when(mockLogin(params))
        .thenAnswer((_) async => Right(mockLoginResponse));
  }

  setUp((){
    mockLogin = MockLogin();
    mockLoginResponse = MockLoginResponse();

    bloc = SignInBloc(
      login: mockLogin
    );

  });

  test('initialState should be Empty', () {
    // assert
    expect(bloc.state, equals(InitialSignInState()));
  });

  group('Login', (){
    LoginParams params = LoginParams();
    test(
      'should get data from the concrete use case',
          () async {
        // arrange
        setUpLoginSuccess(mockLogin, params);
        // act
        bloc.add(SignInUserEvent(params));
        await untilCalled(mockLogin(any));
        // assert
        verify(mockLogin(params));
      },
    );

    test(
      'should start with InitialState and emit [Loading, Loaded] when data is gotten successfully',
          () async {
        // arrange
            setUpLoginSuccess(mockLogin, params);
        // assert later
        final expected = [
          SignInLoading(),
          SignInLoaded(mockLoginResponse),
        ];
        expect(bloc.state, InitialSignInState());
        expectLater(bloc, emitsInOrder(expected));
        // act
        bloc.add(SignInUserEvent(params));
      },
    );

    test(
      'should emit [Loading, Error] when getting data fails',
          () async {
        // arrange
            when(mockLogin(params))
                .thenAnswer((_) async => Left(ServerFailure()));
        // assert later
        final expected = [
          SignInLoading(),
          SignInError(ServerFailure()),
        ];
            expect(bloc.state, InitialSignInState());
            expectLater(bloc, emitsInOrder(expected));
            // act
            bloc.add(SignInUserEvent(params));
      },
    );
  });
}