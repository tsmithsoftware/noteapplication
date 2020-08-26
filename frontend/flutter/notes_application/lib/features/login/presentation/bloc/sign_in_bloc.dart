import 'package:notes_application/core/error/failures.dart';
import 'package:notes_application/features/login/domain/usecases/login.dart';
import 'package:notes_application/features/login/presentation/bloc/bloc.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

class SignInBloc extends Bloc<SignInEvent, SignInState> {
  final Login login;

  SignInBloc({this.login})
      : assert (login != null),
        super(InitialSignInState());

  @override
  Stream<SignInState> mapEventToState(SignInEvent event) async* {
    if (event is SignInUserEvent) {
      yield SignInLoading();
      final failureOrLogin = await login(event.signInParams);
      yield failureOrLogin.fold(
              (failure) => SignInError(ServerFailure()),
              (login) => SignInLoaded(login)
      );
    }
  }
  
}