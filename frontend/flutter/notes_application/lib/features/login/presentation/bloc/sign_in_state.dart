import 'package:equatable/equatable.dart';
import 'package:flutter/cupertino.dart';
import 'package:notes_application/core/error/failures.dart';
import 'package:notes_application/core/presentation/bloc/ErrorState.dart';
import 'package:notes_application/features/login/domain/entities/login_response.dart';

@immutable
abstract class SignInState extends Equatable {
  SignInState();
}

class InitialSignInState extends SignInState {}

class SignInLoading extends SignInState {}

class SignInLoaded extends SignInState {
  final LoginResponse response;

  SignInLoaded(this.response);
}

@immutable
class SignInError extends SignInState implements ErrorState{

  SignInError(this.failure);

  @override
  List<Object> get props => [failure];

  @override
  Failure failure;
}