import 'package:data_connection_checker/data_connection_checker.dart';
import 'package:get_it/get_it.dart';
import 'package:notes_application/core/network/network_info.dart';
import 'package:notes_application/features/login/data/datasources/login_remote_data_source.dart';
import 'package:notes_application/features/login/data/repositories/login_repository.dart';
import 'package:notes_application/features/login/domain/repositories/login_repository.dart';
import 'package:notes_application/features/login/presentation/bloc/bloc.dart';
import 'package:http/http.dart' as http;

import 'domain/usecases/login.dart';

final sl = GetIt.instance;

void init() {
  sl.registerFactory(
          () => SignInBloc(
              login: sl()
          )
  );
  // Use Cases
  sl.registerLazySingleton(() => Login(sl()));
  // Repository
  sl.registerLazySingleton<LoginRepository>(
          () => LoginRepositoryImpl(
            remoteDataSource: sl()
          )
  );
  // Data Sources
  sl.registerLazySingleton<LoginRemoteDataSource>(() => LoginRemoteDataSourceImpl(client: sl()));

  // Core
  sl.registerLazySingleton<NetworkInfo>(() => NetworkInfoImpl(sl()));

  // External
  sl.registerLazySingleton(() => http.Client());
  sl.registerLazySingleton(() => DataConnectionChecker());
}