import 'package:equatable/equatable.dart';
import 'package:flutter/cupertino.dart';
import 'package:notes_application/core/usecases/usecase.dart';


@immutable
abstract class SignInEvent extends Equatable {
  SignInEvent();
}

class SignInUserEvent extends SignInEvent {
  final LoginParams signInParams;

  SignInUserEvent(this.signInParams);

  @override
  List<Object> get props => [signInParams];
}