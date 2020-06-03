package rs.raf.projekat2.valerija_nagl_RN682018.modules

import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import rs.raf.projekat2.valerija_nagl_RN682018.data.datasources.local.SharedPreferancesUser
import rs.raf.projekat2.valerija_nagl_RN682018.data.datasources.local.UserDataSource
import rs.raf.projekat2.valerija_nagl_RN682018.data.repositories.UserRepository
import rs.raf.projekat2.valerija_nagl_RN682018.data.repositories.UserRepositoryImpl
import rs.raf.projekat2.valerija_nagl_RN682018.presentation.viewmodel.UserViewModel

val userModule = module {

    viewModel { UserViewModel(userRepository = get()) }

    single<UserRepository> { UserRepositoryImpl(userDataSource = get()) }

    single<UserDataSource> { SharedPreferancesUser(sharedPreferences = get()) }



}