import 'package:dartz/dartz.dart';
import 'package:notes_application/core/error/exceptions.dart';
import 'package:notes_application/core/error/failures.dart';
import 'package:notes_application/core/usecases/usecase.dart';
import 'package:notes_application/features/login/data/datasources/login_remote_data_source.dart';
import 'package:notes_application/features/login/domain/entities/login_response.dart';
import 'package:notes_application/features/login/domain/repositories/login_repository.dart';

class LoginRepositoryImpl implements LoginRepository {
  final LoginRemoteDataSource remoteDataSource;

  LoginRepositoryImpl({
    this.remoteDataSource,
  });

  @override
  Future<Either<Failure, LoginResponse>> login(LoginParams loginParams) async {
    try {
      final loginResponse = await remoteDataSource.login(loginParams);
      return Right(loginResponse);
    } on ServerException {
      return Left(ServerFailure());
    }
  }

}