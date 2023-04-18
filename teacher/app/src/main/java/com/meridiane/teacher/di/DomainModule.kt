package com.meridiane.teacher.di

import com.meridiane.teacher.data.DemoRepository
import com.meridiane.teacher.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideGetProfile(demoRepository: DemoRepository): GetProfile =
        GetProfileUseCaseImpl(interfaceGetProfileRepository = demoRepository)

    @Provides
    fun registrationUser(demoRepository: DemoRepository): Registration =
        RegistrationUseCaseImpl(interfaceRegistrationRepository = demoRepository)

    @Provides
    fun authorizationUser(demoRepository: DemoRepository): Authorization =
        AuthorizationUseCaseImpl(interfaceAuthorizationRepository = demoRepository)

    @Provides
    fun student(demoRepository: DemoRepository): GetStudents =
        GetStudentsUseCaseImpl(interfaceGetStudentsRepository = demoRepository)

    @Provides
    fun question(demoRepository: DemoRepository): GetQuestions =
        GetQuestionsUseCaseImpl(interfaceGetQuestionsRepository = demoRepository)

    @Provides
    fun lesson(demoRepository: DemoRepository): GetLessons =
        GetLessonsUseCaseImpl(interfaceGetLessonRepository = demoRepository)

}