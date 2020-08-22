
// Parameters have to be put into a container object so that they can be
// included in this abstract base class method definition.
import 'package:dartz/dartz.dart';
import 'package:equatable/equatable.dart';
import 'package:notes_application/core/error/failures.dart';

abstract class UseCase<Type, Params> {
  Future<Either<Failure, Type>> call(Params params);
}

// This will be used by the code calling the use case whenever the use case
// doesn't accept any parameters.
class NoParams extends Equatable {}

class LoginParams extends Equatable {
  static final String grantType = "client_credentials";
  static final String clientId = "oAuthClient";
  static final String clientSecret = "15FCE3AC-C999-4735-99FD-AB6A04F67415";

  LoginParams() : super ([grantType, clientId, clientSecret]);
}