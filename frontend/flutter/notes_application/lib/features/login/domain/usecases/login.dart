import 'package:dartz/dartz.dart';
import 'package:notes_application/core/error/failures.dart';
import 'package:notes_application/core/usecases/usecase.dart';
import 'package:notes_application/features/login/domain/entities/login_response.dart';
import 'package:notes_application/features/login/domain/repositories/login_repository.dart';

class Login extends UseCase<LoginResponse, LoginParams> {
  final LoginRepository repository;

  Login(this.repository);

  @override
  Future<Either<Failure, LoginResponse>> call(LoginParams params) {
    return repository.login(params);
  }

}