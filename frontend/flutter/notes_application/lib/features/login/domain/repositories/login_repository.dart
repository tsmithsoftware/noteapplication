import 'package:dartz/dartz.dart';
import 'package:notes_application/core/error/failures.dart';
import 'package:notes_application/core/usecases/usecase.dart';
import 'package:notes_application/features/login/domain/entities/login_response.dart';

abstract class LoginRepository {
  Future<Either<Failure,LoginResponse>> login(LoginParams loginParams);
}