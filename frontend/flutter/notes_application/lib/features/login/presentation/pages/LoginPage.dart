import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:notes_application/features/login/presentation/bloc/bloc.dart';
import 'package:notes_application/features/login/presentation/widgets/note_widgets.dart';

import '../../../../injection_container.dart';

class LoginPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return BlocProvider(
        create: (_) => sl<SignInBloc>(),
      child: Center(
        child: Padding(
          padding: const EdgeInsets.all(10),
          child: Column(
            children: <Widget>[
              SizedBox(height: 10),
              // Top half
              BlocBuilder<SignInBloc, SignInState>(
                builder: (context, state) {
                  if (state is InitialSignInState) {
                    return MessageDisplay(
                      message: "Login!",
                    );
                  } else if (state is SignInLoading) {
                    return LoadingWidget();
                  } else if (state is SignInLoaded) {
                    return LoginResponseDisplay(
                      response: state.response
                    );
                  }
                  else if (state is SignInError) {
                    return MessageDisplay(
                      message: "Signin error! Add Message to Failure here!",
                    );
                  }
                  return Container();
                },
              ),
              SizedBox(height: 20),
              // Bottom half
              LoginControls(),
            ],
          ),
        ),
      ),
      );
  }
}